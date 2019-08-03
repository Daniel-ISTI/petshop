package br.com.tt.petshop.model.vo;

import br.com.tt.petshop.exception.BusinessException;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class DataNascimento {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNasc;

    public boolean isValid() throws BusinessException {
        return (Objects.isNull(dataNasc) || LocalDate.now().isBefore(dataNasc));
    }

    public DataNascimento(){
    }

    public DataNascimento(LocalDate dataNascimento){
        this.dataNasc = dataNascimento;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNascimento) {
        this.dataNasc = dataNascimento;
    }
}
