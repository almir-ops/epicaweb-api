package br.com.epicaweb.security.repository.helper.contrato;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.epicaweb.domain.model.ContratoModel;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContratoImpl implements ContratoQueries {

	@PersistenceContext
	private EntityManager manager;
	
    
	public List<ContratoModel> getContratoByCpfCnpj(String cpfCnpj) {
		
		StoredProcedureQuery finByCpfCnpj = manager.createStoredProcedureQuery("dbo.SP_RET_CLIENTE_POR_CPF" , ContratoModel.class);
		finByCpfCnpj.registerStoredProcedureParameter("cpfcnpj", String.class, ParameterMode.IN);
         
		finByCpfCnpj.setParameter("cpfcnpj", cpfCnpj);
		List<ContratoModel> contrato = finByCpfCnpj.getResultList();
        
		return contrato;
	}
	
	@Transactional
	public List<String> getByCpfCnpj(String cpfCnpj) {
		
		StoredProcedureQuery finByCpfCnpj = manager.createStoredProcedureQuery("dbo.SP_RET_CLIENTE_POR_CPF");
		finByCpfCnpj.registerStoredProcedureParameter("cpfcnpj", String.class, ParameterMode.IN);
		finByCpfCnpj.setParameter("cpfcnpj", cpfCnpj);
    
		List<String> contrato = finByCpfCnpj.getResultList();
      
		return contrato;
	}
	
}
