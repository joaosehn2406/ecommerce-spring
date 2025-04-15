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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/categories")
@Tag(name = "Categories", description = "Endpoints para gerenciamento de categorias")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @Autowired
    private ProductRepository productRepo;

    @Operation(summary = "Buscar todas as categorias", description = "Este endpoint retorna todas as categorias cadastradas no sistema.")
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Buscar categoria por ID", description = "Este endpoint retorna uma categoria específica baseado no ID informado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria encontrada"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @Operation(summary = "Cadastrar uma nova categoria", description = "Este endpoint cria uma nova categoria no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso")
    })
    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody Category cat) {
        cat = service.insert(cat);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId()).toUri();
        return ResponseEntity.created(uri).body(cat);
    }

    @Operation(summary = "Atualizar categoria", description = "Este endpoint atualiza uma categoria existente no sistema com base no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category cat) {
        cat = service.update(id, cat);
        return ResponseEntity.ok().body(cat);
    }

    @Operation(summary = "Atualizar parcialmente uma categoria", description = "Este endpoint permite atualizar parcialmente os dados de uma categoria existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria atualizada parcialmente com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Category> patch(@PathVariable Long id, @RequestBody Category cat) {
        cat = service.patch(id, cat);
        return ResponseEntity.ok().body(cat);
    }

    @Operation(summary = "Deletar categoria", description = "Este endpoint exclui uma categoria do sistema. Não é possível excluir categorias que tenham produtos associados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Categoria excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada ou existem produtos associados a ela")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (productRepo.existsByCategories_Id(id)) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Não é possível deletar a categoria pois existem produtos associados a ela."
            );
        }

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
