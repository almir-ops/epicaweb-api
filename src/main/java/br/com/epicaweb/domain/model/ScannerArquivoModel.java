package br.com.epicaweb.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="arquivos")
public class ScannerArquivoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String arquivo_destino;
    private String path_destino;
    private String path_sys;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="codigo_documento")
    private ScannerDocumentoModel documentos;

}
