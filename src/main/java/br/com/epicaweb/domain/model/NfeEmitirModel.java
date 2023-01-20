package br.com.epicaweb.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name="nfe")
public class NfeEmitirModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private Number cod_serv_atividade;
    private BigDecimal valor_nfe;
    private String descricao_servico;
    private Number aliquota;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="codigo_cliente")
    private ClienteModel clientes;

}
