package br.com.epicaweb.domain.Service;

import br.com.epicaweb.domain.model.ClienteModel;

public interface ClienteService {

     ClienteModel getCliente(String cpfCnpj);
     void saveCliente(ClienteModel cliente);
}
