package br.com.epicaweb.domain.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class PermissaoModel {

    @Id
    private Long id;
    private String  descricao;
    private Boolean ler;
    private Boolean incluir;
    private Boolean editar;
    private Boolean deletar;
    private Boolean listar;
}