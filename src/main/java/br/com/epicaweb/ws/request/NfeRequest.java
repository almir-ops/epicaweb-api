package br.com.epicaweb.ws.request;


import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class NfeRequest {

    private Long codigo;
    private Number cod_serv_atividade;
    private BigDecimal valor_nfe;
    private String descricao_servico;
    private Number aliquota;

    private Long codigo_cliente;
    private String nomeCliente;
    private String razao_social;
    private String tipo_pessoa;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String telefone;
    private String email;
    private String celular;
    private String complemento;
    private String cpf_cnpj;
    private Number codigo_cidade;

}
