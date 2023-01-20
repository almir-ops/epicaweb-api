package br.com.epicaweb.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="cliente")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private int codigoCidade;
    private String complemento;
    private String cpfCnpj;
}
