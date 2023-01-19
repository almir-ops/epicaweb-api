package br.com.epicaweb.domain.Service.impl.nfe;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import br.com.epicaweb.domain.Service.ClienteService;
import br.com.epicaweb.domain.Service.NfeService;
import br.com.epicaweb.domain.model.ClienteModel;
import br.com.epicaweb.domain.model.ServicoNfeModel;
import br.com.epicaweb.ws.request.NfeRequest;
import org.springframework.stereotype.Service;

import br.com.epicaweb.domain.model.NfeEmitirModel;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NfeImpl implements NfeService {
	
	
	@PersistenceContext
	private EntityManager manager;
	private ClienteService clienteService;

	public List<NfeEmitirModel> getNfeEmitir(String datade, String dataAte, String codigo) {
		
		StoredProcedureQuery getNfe =  manager.createStoredProcedureQuery("SP_GET_RECEB_NFE",NfeEmitirModel.class);
		
		getNfe.registerStoredProcedureParameter("datade",String.class, ParameterMode.IN);
		getNfe.registerStoredProcedureParameter("dataAte",String.class, ParameterMode.IN);
		getNfe.registerStoredProcedureParameter("codigo",String.class, ParameterMode.IN);
		
		getNfe.setParameter("datade", datade);
		getNfe.setParameter("dataAte", dataAte);
		getNfe.setParameter("codigo", codigo);
		
		List<NfeEmitirModel> resultado = getNfe.getResultList();
		
		return resultado;
	}


	public List<ServicoNfeModel> getNfeServico() {
		StoredProcedureQuery getNfe =  manager.createStoredProcedureQuery("SP_GET_COD_SERVICO",ServicoNfeModel.class);
		List<ServicoNfeModel> resultado = getNfe.getResultList();
		return resultado;
	}

/*

	public void enviarNfeWs(NfeRequest nfeRequest){
		try {

			ClienteModel cliente  = new ClienteModel();
			cliente.setCodigo(nfeRequest.getCodigo());
			cliente.setNome(nfeRequest.getNomeCliente());
			cliente.setRazaoSocial(nfeRequest.getRazao_social());
			cliente.setTipoPessoa(nfeRequest.getTipo_pessoa());
			cliente.setLogradouro(nfeRequest.getLogradouro());
			cliente.setNumero(nfeRequest.getNumero());
			cliente.setBairro(nfeRequest.getBairro());
			cliente.setCep(nfeRequest.getCep());
			cliente.setTelefone(nfeRequest.getTelefone());
			cliente.setEmail(nfeRequest.getEmail());
			cliente.setCelular(nfeRequest.getCelular());
			cliente.setCodigoCidade(nfeRequest.getCodigo_cidade());
			cliente.setCpfCnpj(nfeRequest.getCpf_cnpj());
			clienteService.saveCliente(cliente);


			//Retorna XML completo
			String arqXml = nfews.getXMLEnvioRps(codReceb, lote, rps);

			//Assina digitalmente e envia xml ao WS, retorna xml de retorno
			String retorno = envioWs.conectaWS(arqXml, "envioRps");

			// Atualiza tabela receb com informações da geração da nota
			nfews.setGeraLog(codReceb, lote, rps);

			// Consulta o numero da nota por RPS
			consultaRps(rps);


//			 * System.out.println("Código é : " + codReceb);
//			 * System.out.println("Lote é   : " + lote); System.out.println("Rps é    : " +
//			 * rps); System.out.println("Código é : " + arqXml);
//			 * System.out.println("Código é : " + retorno);



		} catch (Throwable e) {
			throw new RuntimeException("PRECISA HAVER UM TRATAMENTO AQUI : " + e.getMessage());
		}
	}
*/

}
