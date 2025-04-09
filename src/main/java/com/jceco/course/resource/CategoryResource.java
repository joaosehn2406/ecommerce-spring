package com.jceco.course.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jceco.course.entities.Category;
import com.jceco.course.repositories.ProductRepository;
import com.jceco.course.services.CategoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@Autowired
	private ProductRepository productRepo;

	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Category> insert(@RequestBody Category cat) {
		cat = service.insert(cat);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId()).toUri();
		return ResponseEntity.created(uri).body(cat);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category cat) {
		
		cat = service.update(id, cat);
		return ResponseEntity.ok().body(cat);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Category> patch (@PathVariable Long id, @RequestBody Category cat) {
		cat = service.patch(id, cat);
		return ResponseEntity.ok().body(cat);
		
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		
		if (productRepo.existsByCategories_Id(id)) {
			throw new ResponseStatusException(
		
			HttpStatus.NOT_FOUND, 
			"Não é possível deletar a categoria pois existem produtos associados a ela.");
		};
		
		service.delete(id);
		return ResponseEntity.noContent().build();	
		
	}

}
