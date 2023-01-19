package br.com.epicaweb.ws.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermissaoResponse {
    private String  descricao;
    private Boolean ler;
    private Boolean incluir;
    private Boolean editar;
    private Boolean deletar;
    private Boolean listar;

}
