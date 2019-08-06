package br.com.tt.petshop.model;

import javax.persistence.*;

@Entity
@Table(name = "TB_UNIDADE")

public class Unidade {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;
    @Column(name = "ENDERECO")
    private String endereco;

//    public Unidade(String nome, String endereco) {
//        this.nome = nome;
//        this.endereco = endereco;
//    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
