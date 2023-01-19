package br.com.epicaweb.ws.request;


import br.com.epicaweb.domain.model.ScannerArquivoModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ScannerSaveRequest {

    private Long codigo;
    private String cliente;
    private String cpf;
    private String email;
    private String quadra;
    private String setor;
    private String lote;
    private String data_reg;
    private String proposta;
    private String falecimento;
}
