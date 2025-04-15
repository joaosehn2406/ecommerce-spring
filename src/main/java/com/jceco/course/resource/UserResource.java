package com.jceco.course.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jceco.course.entities.User;
import com.jceco.course.services.UserService;

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
@RequestMapping(value = "/users") 
@Tag(name = "Users", description = "Endpoints para gerenciamento de usuários")
public class UserResource {

    @Autowired
    private UserService service;

    @Operation(summary = "Buscar todos os usuários", description = "Este endpoint retorna todos os usuários cadastrados no sistema.")
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Buscar usuário por ID", description = "Este endpoint retorna um usuário específico baseado no ID informado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @Operation(summary = "Cadastrar um novo usuário", description = "Este endpoint cria um novo usuário no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    })
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @Operation(summary = "Deletar usuário", description = "Este endpoint exclui um usuário do sistema baseado no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleter(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar usuário", description = "Este endpoint atualiza um usuário existente no sistema com base no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @Operation(summary = "Atualizar parcialmente um usuário", description = "Este endpoint permite atualizar parcialmente os dados de um usuário existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado parcialmente com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PatchMapping(value = "/{id}")
    public ResponseEntity<User> patch(@PathVariable Long id, @RequestBody User obj) {
        obj = service.patch(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
