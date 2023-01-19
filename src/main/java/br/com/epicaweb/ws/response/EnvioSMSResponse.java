package br.com.epicaweb.ws.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnvioSMSResponse {

    private String proposta;
    private String cliente;
    private String valor;
    private String desc_deb;
    private String debito;
    private String vencimento;
    private String email;
    private String fone;

}
