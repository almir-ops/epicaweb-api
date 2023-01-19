package br.com.epicaweb.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="documento")
public class ScannerDocumentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotBlank(message = "Nome é obrigatório.")
    private String cliente;
    private String cpf;
    private String email;
    private String quadra;
    private String setor;
    private String lote;
    private LocalDateTime data_reg;
    private String proposta;
    private String falecimento;

    @OneToMany(mappedBy="documentos",cascade = CascadeType.ALL)
    private List<ScannerArquivoModel> arquivos;

}
