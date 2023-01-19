package br.com.epicaweb.ws.request;

import lombok.Getter;

@Getter
public class EnvioEmailRequest {
    private String proposta;
    private String vencimento;
    private String total;
    private Number top;
    private String ambiente;
}
