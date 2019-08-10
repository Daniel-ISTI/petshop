package br.com.tt.petshop.repository;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.model.vo.DataNascimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Repository
//public class AnimalRepository {
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByClienteId(Long clientId);
    List<Animal> findByNome(String nome);
    List<Animal> findByDataNascimentoDataNascBetweenAndEspecieIs(LocalDate dataNascimentoInicial, LocalDate dataNascimentoFinal, EspecieEnum especie);

//    List<Animal> animais = new ArrayList<>(Arrays.asList(
//            new Animal("Rex", LocalDate.now(), EspecieEnum.MAMIFERO, 1L),
//            new Animal("Nemo", LocalDate.now().minusMonths(1), EspecieEnum.PEIXE, 2L)
//    ));
//
//    public List<Animal> listar(Long clienteId){
//        List<Animal> animaisDoCliente = new ArrayList<>();
//        for (Animal animal: animais) {
//            if(animal.getClientId().equals(clienteId)){
//                animaisDoCliente.add(animal);
//            }
//        }
//        return animaisDoCliente;
//    }
//
//    public void save(Animal animal){
//        /*try{
//            animal.setId(SecureRandom.getInstanceStrong().nextLong());
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }*/
//        animais.add(animal);
//    }
}
