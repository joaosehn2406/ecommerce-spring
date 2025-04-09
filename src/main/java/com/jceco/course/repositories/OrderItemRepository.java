package com.jceco.course.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jceco.course.entities.OrderItem;
import com.jceco.course.entities.pk.OrderItemPk;


public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPk>{
	
	Set<OrderItem> findByIdOrderId(Long id);
	
}
