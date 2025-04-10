package com.jceco.course.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jceco.course.entities.Category;
import com.jceco.course.repositories.CategoryRepository;
import com.jceco.course.services.exceptions.DataBaseException;
import com.jceco.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		
		return repository.findAll();
	}
	
	
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Category insert (Category cat) {
		return repository.save(cat);
	}
	
	public Category update(Long id, Category cat) {
		try {
			Category entity = repository.getReferenceById(id);
			updateData(entity, cat);
			return repository.save(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
		
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	
	public Category patch(Long id, Category cat) {
		Category entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		
		if (cat.getName() != null) {
			entity.setName(cat.getName());
		}
		
		return repository.save(entity);
	}
	
	
	public void updateData(Category c, Category cat) {
		c.setName(cat.getName());
	}
	
}
