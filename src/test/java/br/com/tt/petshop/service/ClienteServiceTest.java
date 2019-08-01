package br.com.tt.petshop.service;

import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.repository.ClienteRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class ClienteServiceTest {

    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Before
    public void setUp(){
        //clienteRepository = mock
        clienteService = new ClienteService(clienteRepository);
    }

    @Test
    public void deveriaRetornarListaVazia(){
        //Arrange - Setup

        //Act - Execução
        List<Cliente> clientes = clienteService.listar();

        //Assert - Verificação
        Assert.assertNotNull("A lista não deveria ser nula.", clientes);
        Assert.assertEquals("A lista deveria retornar nenhum clientes.",0, clientes.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    public void deveriaRetornarListaComClientes(){
        //Arrange - Setup
        List<Cliente> listaClientes = new ArrayList<>();
                listaClientes.add(new Cliente(1L, "Cliente Aa", "00000000001"));
                listaClientes.add(new Cliente(2L, "Cliente Be", "00000000002"));
        Mockito.when(clienteRepository.findAll()).thenReturn(listaClientes);

        //Act - Execução
        List<Cliente> clientes = clienteService.listar();

        //Assert - Verificação
        Assert.assertEquals("Deveria retornar 2 clientes.", 2, clientes.size());
        Assert.assertEquals("Deveria retornar o Fulano", "Fulano", clientes.get(0).getNome());
    }

    @Test
    public void deveriaRemoverComSucesso(){
        //Arrange
        Cliente clienteDeletado = new Cliente(2L, null, null);

        //Act
        clienteService.remover(2L);

        //Assert
        Mockito.verify(clienteRepository).delete(clienteDeletado);
        verifyNoMoreInteractions(clienteRepository);
    }

    @Test
    public void deveriaAdicionarComSucesso() throws BusinessException{
        Cliente cliente = new Cliente(3L, "Cliente Ce", "000.000.000-03");
        clienteService.adicionar(cliente);
        Mockito.verify(clienteRepository).save(cliente);
    }

    @Test
    public void deveriaFalharPorNaoTerNome() {
        Cliente cliente = new Cliente(3L, null, "000.000.000-03");
        try {
            clienteService.adicionar(cliente);
            fail("Deveria ter lançado exceção por não ter nome!");
        } catch (BusinessException e) {
            assertEquals("Nome deve ser informado!", e.getMessage());
        }
    }

    @Test
    public void deveriaFalharPorNaoTerNomeCompleto() {
        Cliente cliente = new Cliente(3L, "Cliente", "000.000.000-03");
        try {
            clienteService.adicionar(cliente);
            fail("Deveria ter lançado exceção por não ter o nome completo!");
        } catch (BusinessException e) {
            assertEquals("Informe seu nome completo!", e.getMessage());
        }
    }

    @Test
    public void deveriaFalharPorTerNomeAbreviado() {
        Cliente cliente = new Cliente(3L, "Cliente C", "000.000.000-03");
        try {
            clienteService.adicionar(cliente);
            fail("Deveria ter lançado exceção por ter abreviações!");
        } catch (BusinessException e) {
            assertEquals("Informe seu nome sem abreviações!", e.getMessage());
        }
    }

    @Test
    public void deveriaFalharPorNaoTerCPF() {
        Cliente cliente = new Cliente(3L, "Cliente Ce", null);
        try {
            clienteService.adicionar(cliente);
            fail("Deveria ter lançado exceção por não ter CPF!");
        } catch (BusinessException e) {
            assertEquals("Informe seu CPF!", e.getMessage());
        }
    }

    @Test
    public void deveriaFalharPorTerCPFInvalido() {
        Cliente cliente = new Cliente(3L, "Cliente Ce", "000.000.000");
        try {
            clienteService.adicionar(cliente);
            fail("Deveria ter lançado exceção por não ter CPF válido!");
        } catch (BusinessException e) {
            assertEquals("Informe seu CPF com 11 dígitos!", e.getMessage());
        }
    }

    @Test
    public void deveriaValidarSeAdimplente() throws BusinessException {
        Cliente cliente = new Cliente(3L, "Cliente Ce", "000.000.000");
        when(clienteRepository.getOne(3L)).thenReturn(cliente);

        clienteService.validarSeAdimplente(3L);

        verify(clienteRepository).getOne(3L);
    }

    @Test
    public void deveriaFalharSeInadimplente() throws BusinessException {
        Cliente cliente = new Cliente(3L, "Cliente Ce", "000.000.000");
        cliente.setInadimplente(true);
        when(clienteRepository.getOne(3L)).thenReturn(cliente);

        try {
            clienteService.validarSeAdimplente(3L);
            fail("Deveria ter falhado pois é inadimplente!");
        }catch (BusinessException e){
            assertEquals("Cliente não está adimplente!", e.getMessage());
        }

        verify(clienteRepository).getOne(3L);
    }

//    @Test
//    public void deveriaLancarBussinesException(){
//        try{
//           clienteService.adicionar(null);
//           fail("Deveria ter lançado excecão.");
//        } catch (BusinessException e) {
//            Assert.assertEquals("O nome deveria ter 2 partes.", e.getMessage());
//        }
//
//    }

//    @Test
//    public void deveriaLancarBussinesExceptionValidaNome() throws BusinessException{
//        Cliente cliente = new Cliente(3L, "Cliente C", "000.000.000-03");
//        clienteService.adicionar(cliente);
//        try{
//            clienteService;
//            fail("Deveria ter lançado excecão.");
//        } catch (BusinessException e) {
//            Assert.assertEquals("O nome deveria ter 2 partes.", e.getMessage());
//        }
//    }
}