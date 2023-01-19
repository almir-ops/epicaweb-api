package br.com.epicaweb.security.repository.helper.usuario;
import br.com.epicaweb.domain.model.PermissaoModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
@AllArgsConstructor
public class PermissaoImpl implements PermissaoQueries {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public List<PermissaoModel> getPermissaoByLogin(String login){

        StoredProcedureQuery permissao = manager.createStoredProcedureQuery("dbo.SP_GET_PERMISSAO_FUNCIONALIDADE",PermissaoModel.class);
        permissao.registerStoredProcedureParameter("login",String.class, ParameterMode.IN);
        permissao.setParameter("login",login);

        return (List<PermissaoModel>) permissao.getResultList();

    }


}
