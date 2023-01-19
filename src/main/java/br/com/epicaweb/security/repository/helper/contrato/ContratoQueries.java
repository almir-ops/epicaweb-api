package br.com.epicaweb.security.repository.helper.contrato;

import java.util.List;


import br.com.epicaweb.domain.model.ContratoModel;

public interface ContratoQueries {	
	
	public List<ContratoModel> getContratoByCpfCnpj(String cpfCnpj);	
	public List<String> getByCpfCnpj(String cpfCnpj);
	
}
