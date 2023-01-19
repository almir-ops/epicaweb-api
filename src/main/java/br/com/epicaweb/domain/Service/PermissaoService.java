package br.com.epicaweb.domain.Service;

import br.com.epicaweb.domain.model.PermissaoModel;
import br.com.epicaweb.security.repository.helper.usuario.PermissaoQueries;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class PermissaoService {

    @Autowired
    private PermissaoQueries permissaoQueries;

    public List<PermissaoModel> getPermissaoAcesso(String login){
        return permissaoQueries.getPermissaoByLogin(login);
    }

}
