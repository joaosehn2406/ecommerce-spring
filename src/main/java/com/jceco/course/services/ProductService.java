package com.jceco.course.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jceco.course.entities.Product;
import com.jceco.course.repositories.ProductRepository;
import com.jceco.course.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll(){
		
		return repository.findAll();
	}
	
	
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Product insert(Product prod) {
		return repository.save(prod);
	}
	
	public void delete(Long id) {
	    repository.deleteById(id);
	}
	
	
	public Product update(Long id, Product prod) {
		Product entity = repository.getReferenceById(id);
		updateData(entity, prod);
		return repository.save(entity);
	}
	
	
	public Product patch(Long id, Product p) {
	    Product entity = repository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException(id));

	    if (p.getName() != null) {
	        entity.setName(p.getName());
	    }

	    if (p.getDescription() != null) {
	        entity.setDescription(p.getDescription());
	    }

	    if (p.getPrice() != null) {
	        entity.setPrice(p.getPrice());
	    }

	    if (p.getImgUrl() != null) {
	        entity.setImgUrl(p.getImgUrl());
	    }

	    if (p.getCategories() != null && !p.getCategories().isEmpty()) {
	        entity.setCategories(p.getCategories());
	    }

	    return repository.save(entity);
	}

	
	
	public void updateData(Product entity, Product p) {
		entity.setDescription(p.getDescription());
		entity.setImgUrl(p.getImgUrl());
		entity.setName(p.getName());
		entity.setPrice(p.getPrice());
		entity.setCategories(p.getCategories());
	}

}
