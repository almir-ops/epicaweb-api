package br.com.epicaweb.security.repository;

import br.com.epicaweb.domain.model.PermissaoModel;
import br.com.epicaweb.security.repository.helper.usuario.PermissaoQueries;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PermissaoRepository extends PermissaoQueries {
     List<PermissaoModel> getPermissaoByLogin(String login);
}
