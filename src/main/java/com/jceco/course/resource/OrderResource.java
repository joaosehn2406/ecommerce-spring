package com.jceco.course.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jceco.course.entities.Order;
import com.jceco.course.services.OrderService;

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
@RequestMapping(value = "/orders") 
@Tag(name = "Orders", description = "Endpoints para gerenciamento de pedidos")
public class OrderResource {

    @Autowired
    private OrderService service;
    
    @Operation(summary = "Buscar todos os pedidos", description = "Este endpoint retorna todos os pedidos cadastrados no sistema.")
    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    
    @Operation(summary = "Buscar pedido por ID", description = "Este endpoint retorna um pedido específico baseado no ID informado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
    @Operation(summary = "Cadastrar um novo pedido", description = "Este endpoint cria um novo pedido no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso")
    })
    @PostMapping
    public ResponseEntity<Order> post(@RequestBody Order order) {
        order = service.insert(order);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).body(order);
    }
    
    @Operation(summary = "Atualizar parcialmente um pedido", description = "Este endpoint permite atualizar parcialmente os dados de um pedido existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido atualizado parcialmente com sucesso"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Order> patch(@RequestBody Order order, @PathVariable Long id) {
        order = service.patch(order, id);
        return ResponseEntity.ok().body(order);
    }
    
    @Operation(summary = "Atualizar um pedido", description = "Este endpoint atualiza um pedido existente no sistema com base no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<Order> put(@RequestBody Order order, @PathVariable Long id) {
        order = service.put(order, id);
        return ResponseEntity.ok().body(order);
    }
    
    @Operation(summary = "Deletar pedido", description = "Este endpoint exclui um pedido do sistema baseado no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pedido excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
