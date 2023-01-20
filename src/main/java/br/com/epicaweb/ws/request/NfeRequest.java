package br.com.epicaweb.ws.request;

import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class NfeRequest {

    private Long codigo;
    private Number cod_serv_atividade;
    private BigDecimal valor_nfe;
    private String descricao_servico;
    private BigDecimal aliquota;

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
    private int codigo_cidade;

}
