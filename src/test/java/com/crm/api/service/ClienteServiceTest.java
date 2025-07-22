package com.crm.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crm.api.exception.EntityNotFoundException;
import com.crm.api.model.Cliente;
import com.crm.api.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void saveCliente_deveSalvarClienteNoRepositorio() {
        // Crie o cliente com todos os campos obrigatórios
        Cliente cliente = criarClienteValido();

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = clienteService.saveCliente(cliente);

        assertNotNull(resultado);
        verify(clienteRepository, times(1)).save(cliente);
    }

    private Cliente criarClienteValido() {
        Cliente cliente = new Cliente();
        cliente.setNome("João Silva");
        cliente.setCpf("12345678901");
        cliente.setIdade(30L);
        cliente.setLogradouro("Rua Teste");
        cliente.setNumero(123L);
        cliente.setBairro("Centro");
        cliente.setCidade("São Paulo");
        cliente.setUf("SP");
        return cliente;
    }

    @Test
    void list_deveRetornarTodosClientes() {
        // Arrange
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);

        when(clienteRepository.findAll()).thenReturn(clientes);

        // Act
        List<Cliente> resultado = clienteService.list();

        // Assert
        assertEquals(2, resultado.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void update_quandoClienteExiste_deveAtualizarCampos() {
        // Arrange
        Cliente clienteExistente = criarClienteValido();
        Cliente clienteAtualizado = criarClienteValido();
        clienteExistente.setId(1L);
        clienteAtualizado.setId(1L);
        clienteAtualizado.setNome("Novo Nome");

        when(clienteRepository.findById(1L)).thenReturn(clienteExistente);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteExistente);

        // Act
        Cliente resultado = clienteService.update(clienteAtualizado);

        // Assert
        assertEquals("Novo Nome", resultado.getNome());
        verify(clienteRepository, times(1)).save(clienteExistente);
    }

    @Test
    void update_quandoClienteNaoExiste_deveLancarExcecao() {
        // Arrange
        Cliente cliente = criarClienteValido();
        cliente.setId(99L);

        when(clienteRepository.findById(99L)).thenReturn(null);

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            clienteService.update(cliente);
        });

        assertEquals("Cliente com ID 99 não encontrado para atualização", exception.getMessage());
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    void del_deveChamarDeleteNoRepositorio() {
        // Arrange
        doNothing().when(clienteRepository).deleteById(anyLong());

        // Act
        String resultado = clienteService.del(1L);

        // Assert
        assertEquals("Cliente excluido com sucesso!", resultado);
        verify(clienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void del_quandoFalhaAoExcluir_deveRetornarMensagemSucessoMesmoComErro() {
        // Arrange
        doThrow(new RuntimeException("Erro no banco")).when(clienteRepository).deleteById(anyLong());

        // Act
        String resultado = clienteService.del(1L);

        // Assert
        assertEquals("Cliente excluido com sucesso!", resultado);
        verify(clienteRepository, times(1)).deleteById(1L);
    }
}
