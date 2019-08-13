package br.com.tt.petshop.service;

import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.repository.ClienteRepository;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public Cliente adicionar(Cliente novoCliente) throws BusinessException {
        validaNome(novoCliente);
        validaCpf(novoCliente);
        return clienteRepository.save(novoCliente);
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
        if(Objects.isNull(novoCliente) || Objects.isNull(novoCliente.getCpf().getValor())){
            throw new BusinessException("Informe seu CPF!");
        }
        //String cpf = novoCliente.getCpf().replaceAll("\\D", "");
        if(!novoCliente.getCpf().isValid()){
            throw new BusinessException("Informe seu CPF com 11 digitos!");
//        }else {
//            novoCliente.setCpf(cpf);
          }
    }

    public void validarSeAdimplente(Long clientId) throws BusinessException {
        Cliente cliente = clienteRepository.getOne(clientId);

        if(cliente.isInadimplente()){
            throw new BusinessException("Cliente não está adimplente!");
        }
    }

//    private void verificaInadimplencia(Cliente novoCliente) throws BusinessException{
//        //if(novoCliente())
//    }

    public void delete(Cliente cliente){
        cliente.remove(cliente);
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public void update(Long id, Cliente cliente) throws NotFound {
        Optional<Cliente> clienteOptional = this.findById(id);

        if(clienteOptional.isPresent()){
            Cliente clienteSalvo = clienteOptional.get();
            clienteSalvo.setNome(cliente.getNome());
            clienteSalvo.setInadimplente(cliente.isInadimplente());
            clienteRepository.save(cliente);
        }
        //TODO throw notfound...
    }
}
