package br.com.tt.petshop.service;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Animal;
import org.springframework.stereotype.Service;
import br.com.tt.petshop.repository.AnimalRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
public class AnimalService {

    private static final int TAMANHO_MINIMO_NOME = 3;

    private final AnimalRepository animalRepository;
    private final ClienteService clienteService;

    public AnimalService(AnimalRepository animalRepository){
        this.animalRepository = animalRepository;
    }

    public List<Animal> listar(Long clientId){
        return animalRepository.listar(clientId);
    }

    public List<String> listarEspecies(){
        List<String> especies = new ArrayList<>();
        for (EspecieEnum especie : EspecieEnum.values()){
            especies.add(especie.name());
        }
        return especies;
    }

    public void adicionar(Animal novoAnimal) throws BusinessException {
        //validaDataNascimento(novoAnimal);
        validarTamanhoMinimoNome(novoAnimal.getNome());
        //validaNome(novoAnimal);
        validarSeDataNAscimentoMenorOuIgualHoje(novoAnimal.getDataNascimento());
        clienteService.
        animalRepository.save(novoAnimal);
    }

    /*private void validaDataNascimento(Animal novoAnimal) throws BusinessException {
        if(Objects.isNull(novoAnimal) || Objects.isNull(novoAnimal.getDataNascimento())) {
            throw new BusinessException("A Data de Nascimento deve ser informada!");
        }

        if(LocalDate.now().isBefore(novoAnimal.getDataNascimento())) {
            throw new BusinessException("A Data de Nascimento deve ser anterior ou igual a data de hoje!");
        }
    }*/

    private void validarSeDataNAscimentoMenorOuIgualHoje(Animal novoAnimal) throws BusinessException {
        if(LocalDate.now().isBefore(novoAnimal.getDataNascimento())) {
            throw new BusinessException("A Data de Nascimento deve ser anterior ou igual a data de hoje!");
        }
    }

    /*private void validaNome(Animal novoAnimal) throws BusinessException {
        if(Objects.isNull(novoAnimal) || Objects.isNull(novoAnimal.getNome())) {
            throw new BusinessException("Nome deve ser informado!");
        }
        String[] partes = novoAnimal.getNome().split("");
        if(partes.length < 3) {
            throw new BusinessException("Informe o nome completo!");
        }
    }*/

    private void validarTamanhoMinimoNome(String nome) throws BusinessException{
        if(nome.length() < TAMANHO_MINIMO_NOME){
            throw new BusinessException("Informe o nome completo!");
        }
    }

    public void validarSeAdimplente(Long clientId){
        Cliente cliente = clienteRepository.find(clientId);

        Boolean adimplente = cliente.isAdimplente();
        if(Objects.isNull(adimplente) || Boolean.FALSE.equals(adimplente)){
            throw new BusinessException("Cliente não está adimplente!")
        }
    }
}
