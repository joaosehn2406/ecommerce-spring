package com.jceco.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jceco.course.entities.Category;


public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	
	
}
