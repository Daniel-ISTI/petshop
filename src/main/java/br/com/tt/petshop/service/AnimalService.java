package br.com.tt.petshop.service;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Animal;
import org.springframework.stereotype.Service;
import br.com.tt.petshop.repository.AnimalRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class AnimalService {

    private static final int TAMANHO_MINIMO_NOME = 3;

    private final AnimalRepository animalRepository;
    private final ClienteService clienteService;

    public AnimalService(AnimalRepository animalRepository, ClienteService clienteService){
        this.animalRepository = animalRepository;
        this.clienteService = clienteService;
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
        if(Objects.isNull(novoAnimal)){
            throw new IllegalArgumentException("Animal deve ser informado!");
        }
        validarSeDataNAscimentoMenorOuIgualHoje(novoAnimal.getDataNascimento());
        validarTamanhoMinimoNome(novoAnimal.getNome());
        clienteService.validarSeAdimplente(novoAnimal.getClientId());
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

    private void validarSeDataNAscimentoMenorOuIgualHoje(LocalDate dataNascimento) throws BusinessException {
        if(Objects.isNull(dataNascimento) ||
                LocalDate.now().isBefore(dataNascimento)){
            throw new BusinessException("A data de nascimento deve ser anterior ou igual a hoje!");
        }
    }

    private void validarTamanhoMinimoNome(String nome) throws BusinessException{
        if(nome.length() < TAMANHO_MINIMO_NOME){
            throw new BusinessException(String.format("O nome deve conter ao menos %d caracteres!", TAMANHO_MINIMO_NOME));
        }
    }

    public void validarSeAdimplente(Long clientId){
//        Cliente cliente = clienteRepository.find(clientId);
//
//        Boolean adimplente = cliente.isAdimplente();
//        if(Objects.isNull(adimplente) || Boolean.FALSE.equals(adimplente)){
//            throw new BusinessException("Cliente não está adimplente!")
//        }
    }
}
