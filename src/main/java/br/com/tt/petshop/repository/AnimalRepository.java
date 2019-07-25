package br.com.tt.petshop.repository;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.model.Animal;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class AnimalRepository {

    List<Animal> animais = new ArrayList<>(Arrays.asList(
            new Animal("Rex", LocalDate.now(), EspecieEnum.MAMIFERO, 1L),
            new Animal("Nemo", LocalDate.now().minusMonths(1), EspecieEnum.PEIXE, 2L)
    ));

    public List<Animal> listar(Long clienteId){
        List<Animal> animaisDoCliente = new ArrayList<>();
        for (Animal animal: animais) {
            if(animal.getClientId().equals(clienteId)){
                animaisDoCliente.add(animal);
            }
        }
        return animaisDoCliente;
    }

    public void save(Animal animal){
        /*try{
            animal.setId(SecureRandom.getInstanceStrong().nextLong());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }*/
        animais.add(animal);
    }
}
