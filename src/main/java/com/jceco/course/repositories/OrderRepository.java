package com.jceco.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jceco.course.entities.Order;


public interface OrderRepository extends JpaRepository<Order, Long>{
	
	
	
}
