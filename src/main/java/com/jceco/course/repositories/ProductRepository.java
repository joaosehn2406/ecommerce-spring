package com.jceco.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jceco.course.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{
	
	
	boolean existsByCategories_Id(Long categoryId);
}
