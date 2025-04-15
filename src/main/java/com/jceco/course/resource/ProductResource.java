package com.jceco.course.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jceco.course.entities.Product;
import com.jceco.course.services.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController  
@RequestMapping(value = "/products") 
@Tag(name = "Products", description = "Endpoints para gerenciamento de produtos")
public class ProductResource {

    @Autowired
    private ProductService service;
    
    @Operation(summary = "Buscar todos os produtos", description = "Este endpoint retorna todos os produtos cadastrados no sistema.")
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    
    @Operation(summary = "Buscar produto por ID", description = "Este endpoint retorna um produto específico baseado no ID informado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto encontrado"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
    @Operation(summary = "Cadastrar um novo produto", description = "Este endpoint cria um novo produto no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Produto criado com sucesso")
    })
    @PostMapping
    public ResponseEntity<Product> insert (@RequestBody Product p) {
        p = service.insert(p);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(uri).body(p);
    }
    
    @Operation(summary = "Deletar produto", description = "Este endpoint exclui um produto do sistema baseado no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Atualizar produto", description = "Este endpoint atualiza um produto existente no sistema com base no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product p) {
        p = service.update(id, p);
        return ResponseEntity.ok().body(p);
    }
    
    @Operation(summary = "Atualizar parcialmente um produto", description = "Este endpoint permite atualizar parcialmente os dados de um produto existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto atualizado parcialmente com sucesso"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Product> patch(@PathVariable Long id, @RequestBody Product p) {
        p = service.patch(id, p);
        return ResponseEntity.ok().body(p);
    }
}
