package com.jceco.course.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jceco.course.entities.User;
import com.jceco.course.repositories.UserRepository;
import com.jceco.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		
		return repository.findAll();
	}
	
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void deleter(Long id) {
		repository.deleteById(id);
	}
	
	public User update (Long id, User obj) {
		User entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}
	
	
	public User patch(Long id, User obj) {
	    User entity = repository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException(id));

	    if (obj.getName() != null) {
	        entity.setName(obj.getName());
	    }
	    if (obj.getEmail() != null) {
	        entity.setEmail(obj.getEmail());
	    }
	    if (obj.getPhone() != null) {
	        entity.setPhone(obj.getPhone());
	    }
	    if (obj.getPassword() != null && !obj.getPassword().isBlank()) {
	        entity.setPassword(obj.getPassword()); 
	    }

	    return repository.save(entity);
	}



	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		 
	}
}
