package com.jceco.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jceco.course.entities.OrderItem;
import com.jceco.course.entities.pk.OrderItemPk;


public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPk>{
	
	
	
}
