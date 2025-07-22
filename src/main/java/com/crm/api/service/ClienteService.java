package com.crm.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.crm.api.exception.EntityNotFoundException;
import com.crm.api.model.Cliente;
import com.crm.api.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private ClienteRepository clienteRepository;
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public List<Cliente> list() {
		return clienteRepository.findAll();
	}
	
	public Cliente update(Cliente cliente) {
		
		Cliente updateCliente = new Cliente();
		
		if (cliente != null) {
			
			updateCliente = clienteRepository.findById(cliente.getId());

			if (updateCliente == null) {
				throw new EntityNotFoundException("Cliente com ID " + cliente.getId() + " não encontrado para atualização");
			}
	
			if (updateCliente.getNome() != cliente.getNome()) {	updateCliente.setNome(cliente.getNome().toString()); }
			if (updateCliente.getCpf() != cliente.getCpf()) { updateCliente.setCpf(cliente.getCpf().toString()); }
			if (updateCliente.getIdade() != cliente.getIdade()) { updateCliente.setIdade(cliente.getIdade()); }
			if (updateCliente.getLogradouro() != cliente.getLogradouro()) {	updateCliente.setLogradouro(cliente.getLogradouro().toString()); }
			if (updateCliente.getNumero() != cliente.getNumero()) { updateCliente.setNumero(cliente.getNumero()); }
			if (updateCliente.getBairro() != cliente.getBairro()) { updateCliente.setBairro(cliente.getBairro()); }
			if (updateCliente.getCidade() != cliente.getCidade()) { updateCliente.setCidade(cliente.getCidade()); }
			if (updateCliente.getUf() != cliente.getUf()) { updateCliente.setUf(cliente.getUf()); }
			
			clienteRepository.save(updateCliente);

		}
		
		return updateCliente;
	}
	
	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public String del(@PathVariable Long id) {
		
		try {
			clienteRepository.deleteById(id);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		
		return "Cliente excluido com sucesso!";
	}

}
