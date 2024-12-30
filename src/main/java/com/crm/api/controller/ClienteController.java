package com.crm.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.crm.api.model.Cliente;
import com.crm.api.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/cliente")
public class ClienteController {
	
	
	private ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@Operation(description = "Operaço para listar informações dos cliente.")
	@GetMapping
	public List<Cliente> list() {
		return clienteService.list();
	}
	
	
	@PutMapping
	public Cliente update(@RequestBody @Valid Cliente cliente) {
		return clienteService.update(cliente);
	}
	
	@Operation(description = "Operaço para salva as informações do cliente, incluindo o endereço.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Cliente salvo com sucesso")
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente saveCliente(@RequestBody @Valid Cliente cliente) {
		clienteService.saveCliente(cliente);
		return cliente;
	}
	
	@Operation(description = "Operaço para excluir um cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente excluido com sucesso")
	})
	@DeleteMapping("{id}")
	public String del(@PathVariable Long id) {
		return clienteService.del(id);
	}
	
}
