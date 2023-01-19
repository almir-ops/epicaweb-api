package br.com.epicaweb.security.repository.helper.usuario;

import br.com.epicaweb.domain.model.PermissaoModel;

import java.util.List;

public interface PermissaoQueries {

    public List<PermissaoModel> getPermissaoByLogin(String login);
}
