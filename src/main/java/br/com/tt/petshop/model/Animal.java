package br.com.tt.petshop.model;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.model.vo.DataNascimento;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "TB_ANIMAL")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //No ORACLE
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_animal");
    //@SequenceGenerator(schema = "schema", sequenceName = "sequence", name = "nome");
    @Column(name = "CODIGO")
    private Long id;

    private String nome;

    @Embedded
    private DataNascimento dataNascimento;

    @Enumerated(EnumType.STRING)
    private EspecieEnum especie;

//    @Column(name = "cliente_id", updatable = false, insertable = false)
//    private Long clientId;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "ID_UNIDADE")
    private Unidade unidade;

    @OneToMany(mappedBy = "id")
    private List<Produto> produtos;

    public Animal() {
        this.dataNascimento = new DataNascimento();
    }

    public Animal(String nome, LocalDate dataNascimento, EspecieEnum especie, Long clientId) {
        this.nome = nome;
        //this.dataNascimento = dataNascimento;
        this.dataNascimento = new DataNascimento(dataNascimento);
        this.especie = especie;
        this.cliente = new Cliente(clientId, null, null);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//    public LocalDate getDataNascimento() {
//        return dataNascimento;
//    }

//    public void setDataNascimento(LocalDate dataNascimento) {
//        this.dataNascimento = dataNascimento;
//    }

    public EspecieEnum getEspecie() {
        return especie;
    }

    public void setEspecie(EspecieEnum especie) {
        this.especie = especie;
    }

    public DataNascimento getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(DataNascimento dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
