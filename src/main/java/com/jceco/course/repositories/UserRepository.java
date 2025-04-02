package com.jceco.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jceco.course.entities.User;


public interface UserRepository extends JpaRepository<User, Long>{
	
	
	
}
