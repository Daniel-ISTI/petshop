package br.com.tt.petshop.model;

import br.com.tt.petshop.model.vo.Cpf;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME_CLIENTE")
    private String nome;

    @Embedded
    private Cpf cpf;

//    @Column(name = "cpf_cliente")
//    private String cpf;

    @Column(name = "INADIMPLENTE")
    private Boolean inadimplente;

    @OneToMany(mappedBy = "cliente")
    private List<Animal> animais;

    @ManyToOne
    @JoinColumn(name = "ID_UNIDADE")
    private Unidade unidade;

    public Cliente(){
        this.inadimplente = Boolean.FALSE;
    }

    public Cliente(Long id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = new Cpf(cpf);
        this.inadimplente = Boolean.FALSE;
    }

//    public Cliente() {
//    }

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//    public String getCpf() {
//        return cpf;
//    }

//    public void setCpf(String cpf) {
//        this.cpf = cpf;
//    }

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

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }
}
