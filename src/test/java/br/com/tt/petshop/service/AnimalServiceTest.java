package br.com.tt.petshop.service;


import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.repository.AnimalRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


import static br.com.tt.petshop.enums.EspecieEnum.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class AnimalServiceTest {

    private AnimalService animalService;

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private ClienteService clienteService;
    @Mock
    private ModelMapper modelMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        animalRepository = Mockito.mock(AnimalRepository.class);
        clienteService = Mockito.mock(ClienteService.class);
        animalService = new AnimalService(animalRepository, clienteService, modelMapper);
    }

    @Test
    public void deveriaRetornarListaVazia(){
        //List<Animal> animais = animalRepository.listar(1L);
        List<Animal> animais = animalRepository.findByClienteId(1L);
        Assert.assertEquals("A lista deveria retornar nenhum clientes.",0, animais.size());

        //Assert - Verificação
        //Assert.assertNotNull("A lista não deveria ser nula.", clientes);
        //Assert.assertEquals("A lista deveria retornar nenhum clientes.",0, clientes.size());
        //verify(AnimalRepository, times(1)).findAll();
    }

    @Test
    public void deveriaRetornarListaComAnimais(){
        //Arrange
        List<Animal> listaCliente01 = Arrays.asList(
                new Animal("Rex", LocalDate.now(), EspecieEnum.MAMIFERO,01L),
                new Animal("Totó", LocalDate.now().minusYears(1), EspecieEnum.MAMIFERO,01L)
        );

        List<Animal> listaCliente02 = Arrays.asList(
                new Animal("Rex", LocalDate.now(), EspecieEnum.MAMIFERO,01L)
        );

        //Mockito.when(animalRepository.listar(1L)).thenReturn(listaCliente01);
        //Mockito.when(animalRepository.listar(2L)).thenReturn(listaCliente02);
        Mockito.when(animalRepository.findByClienteId(1L)).thenReturn(listaCliente01);
        Mockito.when(animalRepository.findByClienteId(2L)).thenReturn(listaCliente02);

        //Act
        List<Animal> animalCliente01 = animalService.listar(1L);
        List<Animal> animalCliente02 = animalService.listar(2L);
        animalService.listar(null);

        //Asserts
        assertEquals("Deveria retornar o animal Rex do Client Id 01", Long.valueOf(1L), animalCliente01.get(0).getCliente().getId());
        assertEquals("Deveria retornar o animal Rex do Client Id 02", listaCliente02, animalCliente02);
    }

    @Test
    public void deveriaRetornarEspecies(){
        List<String> especies = animalService.listarEspecies();
        assertArrayEquals("Deveria ter o mesmo array de espécies",
                especies.toArray(),
                new String[]{REPTIL.name(), MAMIFERO.name(), PEIXE.name()});
    }

    @Test
    public void deveriaAdicionarAnimalComSucesso() throws BusinessException {
        Animal animal = new Animal("Rex", LocalDate.now(), EspecieEnum.MAMIFERO, 1L);

        animalService.adicionar(animal);
        Mockito.verify(animalRepository).save(animal);
    }

    @Test
    public void deveriaFalharAoAdicionarAnimal() throws BusinessException {
        Animal animal = new Animal("Rex", null, null,null);
        try{
            animalService.adicionar(animal);
            fail("Deveria ter adicionado um animal!");
        } catch (BusinessException e) {
            Assert.assertEquals("A data de nascimento deve ser anterior ou igual a hoje!", e.getMessage());
            }
        }

//    @Test
//    public void deveriaFalharPorNaoTerNome() {
//        Animal animal = new Animal(null, LocalDate.now(), EspecieEnum.MAMIFERO,1L);
//        try {
//            animalService.adicionar(animal);
//            fail("Deveria ter lançado exceção por não ter Nome!");
//        } catch (BusinessException e) {
//            Assert.assertEquals("Informe o Nome!", e.getMessage());
//        }
//    }

//    @Test
//    public void deveriaFalharPorNaoTerDataNascimento() {
//        Animal animal = new Animal("Rex", null, EspecieEnum.MAMIFERO,1L);
//        try {
//            animalService.adicionar(animal);
//            fail("Deveria ter lançado exceção por não ter Data Nascimento!");
//        } catch (BusinessException e) {
//            Assert.assertEquals("Informe a data de Nascimento!", e.getMessage());
//        }
//    }

//    @Test(expected = BusinessException.class)
//    public void deveriaFalharClienteInadimplente() throws BusinessException {
//        //when(clienteService.validarSeAdimplente(01L)).thenThrow(BusinessException.class);
//        //Em métodos VOID para retornar exceção deve se usar o doThrow
//        doThrow(BusinessException.class).when(clienteService).validarSeAdimplente(1L);
//    }
}
