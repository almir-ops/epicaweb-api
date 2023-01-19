package br.com.epicaweb.domain.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class ContratoModel {

    @Id
    @Column(name="ID")
    private String ID;

    @Column(name="PROPOSTA")
    private String proposta;

    @Column(name="PRODUTO")
    private String produto;

    @Column(name="NOME")
    private String cliente;

    @Column(name="CPFCNPJ")
    private String cpfCnpj;


}
