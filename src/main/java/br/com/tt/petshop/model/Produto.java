package br.com.tt.petshop.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "TB_PRODUTO")


public class Produto {

    //@OneToMany(mappedBy = "cliente")
    //private List<Animal> animais;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column (name = "DESCRICAO")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "ID_ANIMAL")
    private Animal animal;

    public Produto(BigDecimal valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
