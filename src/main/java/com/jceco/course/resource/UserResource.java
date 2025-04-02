package com.jceco.course.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jceco.course.entities.User;
import org.springframework.web.bind.annotation.GetMapping;



@RestController  
@RequestMapping(value = "/users") 
public class UserResource {

	
	@GetMapping
	public ResponseEntity<User> findAll() {
		User u = new User(1L, "Jeter123", "jeter@gmail.com", "47 998250912", "senha123");
		return ResponseEntity.ok().body(u);
	}
	
	
}
