package br.com.tt.petshop.service;

import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void remover(Long id){
        //TODO alterar no JPA
        Cliente cliente = new Cliente();
        cliente.setId(id);
        //clienteRepository.delete(new Cliente());
        clienteRepository.delete(cliente);
    }

    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    public void adicionar(Cliente novoCliente) throws BusinessException {
        validaNome(novoCliente);
        validaCpf(novoCliente);
        clienteRepository.save(novoCliente);
    }

    private void validaNome(Cliente novoCliente) throws BusinessException{
        if(Objects.isNull(novoCliente) || Objects.isNull(novoCliente.getNome())) {
            throw new BusinessException("Nome deve ser informado!");
        }

        String[] partes = novoCliente.getNome().split("");
        if(partes.length < 2) {
            throw new BusinessException("Informe o nome completo!");
        }
        for (String parte : partes){
            if(partes.length < 2){
                throw new BusinessException("Informe seu nome sem abreviações!");
            }
        }
    }

    private void validaCpf(Cliente novoCliente) throws BusinessException{
        if(Objects.isNull(novoCliente) || Objects.isNull(novoCliente.getCpf())){
            throw new BusinessException("Informe seu CPF!");
        }
        String cpf = novoCliente.getCpf().replaceAll("\\D", "");
        if(cpf.length() != 11){
            throw new BusinessException("Informe seu CPF com 11 digitos!");
        }else{
            novoCliente.setCpf(cpf);
        }
    }

    private void verificaInadimplencia(Cliente novoCliente) throws BusinessException{
        if(novoCliente())
    }

    public void delete(Cliente cliente){
        cliente.remove(cliente);
    }
}
