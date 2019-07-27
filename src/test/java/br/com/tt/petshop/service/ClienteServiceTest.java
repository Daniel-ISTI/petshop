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
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

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
        clienteRepository = new ClienteRepository();
        //clienteService = new ClienteService(clienteRepository);

        //Act - Execução
        List<Cliente> clientes = clienteService.listar();

        //Assert - Verificação
        Assert.assertNotNull("A lista não deveria ser nula.", clientes);
        Assert.assertEquals("A lista deveria retornar nenhum clientes.",0, clientes.size());
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
        //Assert.
    }

    @Test
    public void deveriaRemoverComSucesso(){
        //Arrange
        clienteService.remover(2L);

        //Asset
        Cliente clienteDeletado = new Cliente(2L, null, null);
        Mockito.verify(clienteRepository).delete(clienteDeletado);
    }

    @Test
    public void deveriaAdicionarComSucesso() throws BusinessException{
        Cliente cliente = new Cliente(3L, "Cliente Ce", "000.000.000-03");

        clienteService.adicionar(cliente);

        //Cliente clienteAdicionado = new Cliente(3L, null, null);
        Mockito.verify(clienteRepository).save(cliente);
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

    @Test
    public void deveriaLancarBussinesExceptionValidaNome() throws BusinessException{
        Cliente cliente = new Cliente(3L, "Cliente C", "000.000.000-03");
        clienteService.adicionar(cliente);
        try{
            clienteService;
            fail("Deveria ter lançado excecão.");
        } catch (BusinessException e) {
            Assert.assertEquals("O nome deveria ter 2 partes.", e.getMessage());
        }
    }
}