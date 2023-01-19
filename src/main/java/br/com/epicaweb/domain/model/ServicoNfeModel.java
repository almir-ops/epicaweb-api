package br.com.epicaweb.domain.model;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class ServicoNfeModel {

    @Id
    private Long codigo;
    private String descricao_servico;
    private String aliquota;
    private String codigo_servico;
}

