package br.com.epicaweb.ws.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnvioEmailResponse {

    private String proposta;
    private String cliente;
    private String valor;
    private String tipoDoc;
    private String produto;
    private String vencimento;
    private String email;
    private String qtdDebAnt;


}
