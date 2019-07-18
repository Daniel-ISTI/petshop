package br.com.tt.petshop.service;

import br.com.tt.petshop.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ClienteService {

    public List<Cliente> listar(){
        List<Cliente> listaClientes = Arrays.asList(
                new Cliente("Cliente A", "00000000001"),
                new Cliente("Cliente B", "00000000002"),
                new Cliente("Cliente C", "00000000003")
        );
        return listaClientes;
    }
}
