package br.com.epicaweb.ws.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicoNfeResponse {
    private Long codigo;
    private String descricao_servico;
    private String aliquota;
    private String codigo_servico;
}

