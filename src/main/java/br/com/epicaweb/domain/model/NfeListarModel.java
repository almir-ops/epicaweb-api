package br.com.epicaweb.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class NfeListarModel {
    @Id
    @Column(name="CODIGO")
    private String codigo;

    @Column(name="CLIENTE")
    private String cliente;

    @Column(name="DATA_CREDI")
    private String dataCredito;

    @Column(name="PROPOSTA")
    private String proposta;

    @Column(name="VLR_PARC")
    private String valorParcela;

    @Column(name="NUM_PARC")
    private String parcela;

    @Column(name="D_VENCTO")
    private String dataVEncto;

}
