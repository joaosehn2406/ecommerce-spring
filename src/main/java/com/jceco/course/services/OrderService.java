package com.jceco.course.services;


import java.time.Instant;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jceco.course.entities.Order;
import com.jceco.course.entities.OrderItem;
import com.jceco.course.entities.Payment;
import com.jceco.course.entities.User;
import com.jceco.course.repositories.OrderItemRepository;
import com.jceco.course.repositories.OrderRepository;
import com.jceco.course.repositories.UserRepository;
import com.jceco.course.services.exceptions.DataBaseException;
import com.jceco.course.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private UserRepository userRepository;

	
	public List<Order> findAll(){
		
		return repository.findAll();
	}
	
	
	public Order findById(Long id) {
	    return repository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException(id));
	}

	
	
	
	public Order insert(Order order) {

	    Order savedOrder = repository.save(order);
	    

	    for (OrderItem item : order.getItems()) {
	        item.setOrder(savedOrder);
	        orderItemRepository.save(item); 
	    }
	    
	    savedOrder.setPayment(new Payment(null, Instant.now(), order));
	    savedOrder = repository.save(order);
	    
	    return savedOrder;
	}
	
	
	public Order put(Order order, Long id) {
		Order entity = repository.getReferenceById(id);
		updateData(entity, order);
		return repository.save(entity);
	}
	
	public void delete (Long id) {
		Set<OrderItem> items = orderItemRepository.findByIdOrderId(id);
		
		try {
			orderItemRepository.deleteAll(items);
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	
	public Order patch(Order order, Long id) {
	    Order entity = repository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException(id));

	    if (order.getClient() != null && order.getClient().getId() != null) {
	        User client = userRepository.findById(order.getClient().getId())
	                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
	        entity.setClient(client);
	    }

	    if (order.getOrderStatus() != null) {
	        entity.setOrderStatus(order.getOrderStatus());
	    }

	    return repository.save(entity);
	}


	
	public void updateData(Order entity, Order o) {
		entity.setClient(o.getClient());
		entity.setOrderStatus(o.getOrderStatus());
		entity.setPayment(o.getPayment());
	}









	
}
