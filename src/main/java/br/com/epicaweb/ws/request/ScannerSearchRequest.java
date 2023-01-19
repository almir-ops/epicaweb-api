package br.com.epicaweb.ws.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScannerSearchRequest {

    private String Proposta;
    private String cliente;
    private String quadra;
    private String lote;
    private String Setor;
    private String dtFalecimento;

}
