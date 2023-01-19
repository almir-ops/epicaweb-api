package br.com.epicaweb.ws.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResponse {

    private Long codigo;
    private String nome;
    private String razaoSocial;
    private String tipoPessoa;
    private String contato;
    private String rg;
    private String cnpj;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String telefone;
    private String email;
    private String celular;
    private String codigoCidade;
    private String complemento;
    private String cpfCnpj;
}
