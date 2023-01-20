package br.com.epicaweb.domain.Service.impl.nfe;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import br.com.epicaweb.domain.Service.ClienteService;
import br.com.epicaweb.domain.Service.NfeService;
import br.com.epicaweb.domain.model.ClienteModel;
import br.com.epicaweb.domain.model.NfeEmitirModel;
import br.com.epicaweb.domain.model.ServicoNfeModel;
import br.com.epicaweb.security.repository.NfeRepository;
import br.com.epicaweb.ws.request.NfeRequest;
import org.springframework.stereotype.Service;

import br.com.epicaweb.domain.model.NfeListarModel;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class NfeImpl implements NfeService {
	
	@PersistenceContext
	private EntityManager manager;
	private ClienteService clienteService;
	private NfeRepository nfeRepository;

	public List<NfeListarModel> getNfeEmitir(String datade, String dataAte, String codigo) {
		
		StoredProcedureQuery getNfe =  manager.createStoredProcedureQuery("SP_GET_RECEB_NFE", NfeListarModel.class);
		
		getNfe.registerStoredProcedureParameter("datade",String.class, ParameterMode.IN);
		getNfe.registerStoredProcedureParameter("dataAte",String.class, ParameterMode.IN);
		getNfe.registerStoredProcedureParameter("codigo",String.class, ParameterMode.IN);
		
		getNfe.setParameter("datade", datade);
		getNfe.setParameter("dataAte", dataAte);
		getNfe.setParameter("codigo", codigo);
		
		List<NfeListarModel> resultado = getNfe.getResultList();
		
		return resultado;
	}


	public List<ServicoNfeModel> getNfeServico() {
		StoredProcedureQuery getNfe =  manager.createStoredProcedureQuery("SP_GET_COD_SERVICO",ServicoNfeModel.class);
		List<ServicoNfeModel> resultado = getNfe.getResultList();
		return resultado;
	}


	@Transactional
	public String sendNfeWs(NfeRequest nfeRequest){
		try {

			ClienteModel cliente  = new ClienteModel();
			cliente.setCodigo(nfeRequest.getCodigo_cliente());
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

			NfeEmitirModel nfeEmite = new NfeEmitirModel();
			nfeEmite.setCodigo(nfeEmite.getCodigo());
			nfeEmite.setCod_serv_atividade(nfeRequest.getCod_serv_atividade());
			nfeEmite.setValor_nfe(nfeRequest.getValor_nfe());
			nfeEmite.setDescricao_servico(nfeRequest.getDescricao_servico());
			nfeEmite.setAliquota(nfeRequest.getAliquota());
			nfeEmite.setClientes(cliente);
			nfeRepository.save(nfeEmite);

			System.out.println("Codigo da nota " + nfeEmite.getCodigo());
            Long codNfe = nfeEmite.getCodigo();
		  //Retorna XML completo
			String arqXml = getXMLEnvioRps(codNfe);

			return arqXml;


/*

		  //Assina digitalmente e envia xml ao WS, retorna xml de retorno
			String retorno = envioWs.conectaWS(arqXml, "envioRps");

		  //Atualiza tabela receb com informações da geração da nota
			nfews.setGeraLog(codReceb, lote, rps);

		  //Consulta o numero da nota por RPS
			consultaRps(rps);
//			* System.out.println("Código é : " + codReceb);
//			* System.out.println("Lote é   : " + lote); System.out.println("Rps é    : " +
//			* rps); System.out.println("Código é : " + arqXml);
//			* System.out.println("Código é : " + retorno);


*/
		} catch (Throwable e) {
			throw new RuntimeException("PRECISA HAVER UM TRATAMENTO AQUI : " + e.getMessage());
		}
	}


	public String getXMLEnvioRps(Long codigoNfe) {

		try {

			StoredProcedureQuery getXML = manager.createStoredProcedureQuery("SP_GET_XML_NFE");
			getXML.registerStoredProcedureParameter("codigoNfe", Long.class,ParameterMode.IN);
			getXML.registerStoredProcedureParameter("saida", String.class, ParameterMode.OUT);

			getXML.setParameter("codigoNfe",codigoNfe);
			getXML.execute();
			String ret = (String) getXML.getOutputParameterValue("saida");

			return ret;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}





}
