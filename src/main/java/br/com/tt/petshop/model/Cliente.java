package br.com.tt.petshop.model;

import java.util.Objects;

public class Cliente {

    private Long id;
    private String nome;
    private String cpf;
    private Boolean inadimplente;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Cliente() {
    }

    public Cliente(Long id, String nome, String cpf, Boolean adimplente) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.inadimplente = inadimplente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void remove(Cliente cliente){
        cliente.remove(cliente);
    }

    public Boolean isInadimplente() {
        return Objects.nonNull(inadimplente) && Boolean.TRUE.equals(inadimplente);
    }

    public void setInadimplente(Boolean inadimplente) {
        this.inadimplente = inadimplente;
    }
}
