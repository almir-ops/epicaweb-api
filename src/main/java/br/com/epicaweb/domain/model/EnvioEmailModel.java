package br.com.epicaweb.domain.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class EnvioEmailModel {

    @Id
    @Column(name = "NUM_PROP")
    private String proposta;

    @Column(name = "DESCRICAO")
    private String cliente;

    @Column(name = "VALOR")
    private String valor;

    @Column(name = "TIPO_DOC")
    private String tipoDoc;

    @Column(name = "PRODUTO")
    private String produto;

    @Column(name = "DATA_VENCTO")
    private String vencimento;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "QTD_DEB_ANT")
    private String qtdDebAnt;


}
