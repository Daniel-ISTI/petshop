package br.com.tt.petshop.repository;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.model.Cliente;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest

public class AnimalRepositoryIT {

    @Autowired
    private AnimalRepository animalRepository;
    private ClienteRepository clienteRepository;

    @Test
    public void deveriaRetornarListaVazia(){
        List<Animal> lista = animalRepository.findByClienteId(1L);
        Assert.assertEquals("Deveria ser lista vazia!", 0, lista.size());
    }

    @Before
    @Sql(value = "classpath:limpa.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void Setup(){
    }

    @Test
    @Sql("classpath:insere_rex.sql")
    public void deveriaRetornarUmAnimal(){
        List<Animal> list = animalRepository.findByClienteId(133L);
        Assert.assertEquals("Deveria retornar um animal!", 1, list.size());
        Animal rex = list.get(0);
        Assert.assertEquals("O nome deveria ser Rex!", "Rex", rex.getNome());
        Assert.assertEquals("O cliente deveria ser 0 133!", Long.valueOf(133), rex.getCliente().getId());
        Assert.assertEquals("Deveria ser um mamífero!", EspecieEnum.MAMIFERO, rex.getEspecie());
    }

    @Test
    @Sql("classpath:insere rex.sql")
    public void deveriaRetornarUmAnimalPorNome(){
        List<Animal> list = animalRepository.findByNome("Rex");
        Assert.assertEquals("Deveria retornar um animal!", 1, list.size());
        Animal rex = list.get(0);
        Assert.assertEquals("O nome deveria ser Rex!", "Rex", rex.getNome());
        Assert.assertEquals("O cliente deveria ser 0 133!", Long.valueOf(133), rex.getCliente().getId());
        Assert.assertEquals("Deveria ser um mamífero!", EspecieEnum.MAMIFERO, rex.getEspecie());
    }

}