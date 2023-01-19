package br.com.epicaweb.domain.Service.impl.cliente;

import br.com.epicaweb.domain.Service.ClienteService;
import br.com.epicaweb.domain.model.ClienteModel;
import br.com.epicaweb.security.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;


@Service
@AllArgsConstructor
public class ClienteImpl implements ClienteService {

    EntityManager manager;
    ClienteRepository clienteRepository;

    public ClienteModel getCliente(String cpfCnpj) {
        StoredProcedureQuery cliente =  manager.createStoredProcedureQuery("SP_GET_CLIENTE", ClienteModel.class);
        cliente.registerStoredProcedureParameter("cpf_cnpj",String.class, ParameterMode.IN);
        cliente.setParameter("cpf_cnpj", cpfCnpj);
        ClienteModel resultado = (ClienteModel) cliente.getSingleResult();
        return resultado;
    }

    public void saveCliente(ClienteModel cliente){
        clienteRepository.save(cliente);
    }


}
