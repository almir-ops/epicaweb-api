package br.com.epicaweb.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class EnvioSMSModel {

    @Id
    @Column(name = "PROPOSTA")
    private String proposta;

    @Column(name = "CLIENTE")
    private String cliente;

    @Column(name = "VALOR_TAXA")
    private String valor;

    @Column(name = "DESCR_DEB")
    private String desc_deb;

    @Column(name = "DEBITO")
    private String debito;

    @Column(name = "VENCTO")
    private String vencimento;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FONE")
    private String fone;


}
