package br.com.epicaweb.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.epicaweb.assembler.ContratoAssembler;
import br.com.epicaweb.security.repository.helper.contrato.ContratoQueries;
import br.com.epicaweb.ws.response.ContratoResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/contrato")
public class ContratoController {

	private ContratoQueries contratoQueries;
	private ContratoAssembler contratoAssembler;

	@GetMapping("/{cpfCnpj}")
	public List<ContratoResponse> buscaContrato(@PathVariable String cpfCnpj) {		
		List<ContratoResponse> retorno = contratoAssembler.toCollectionModel(contratoQueries.getContratoByCpfCnpj(cpfCnpj));		
		return retorno;
	}


	@GetMapping("/{cpfCnpj}/{NOVOTESTE}")
	public ResponseEntity<String> buscaContratoString(@PathVariable String cpfCnpj) {
		List<String> retorno = contratoQueries.getByCpfCnpj(cpfCnpj);
		return (ResponseEntity<String>) retorno;
	}

/*	
	@SuppressWarnings("unchecked")
	@GetMapping("/a/{cpfCnpj}")
	public ResponseEntity<String> buscaEntregaNew(@PathVariable String cpfCnpj) {

		List<String> ret = contratoQueries.getByCpfCnpj(cpfCnpj);
		return (ResponseEntity<String>) contratoAssembler.toCollectionModelA(ret);
	}
*/

}
