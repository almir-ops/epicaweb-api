package br.com.epicaweb.controller;

import java.util.List;

import br.com.epicaweb.domain.Service.NfeService;
import br.com.epicaweb.ws.request.NfeRequest;
import br.com.epicaweb.ws.response.ServicoNfeResponse;
import org.springframework.web.bind.annotation.*;

import br.com.epicaweb.assembler.NfeListarAssembler;
import br.com.epicaweb.ws.response.NfeEmitirResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/nfe")
public class NfeController {

	private NfeService nfeService;
	private NfeListarAssembler nfeListarAssembler;

	/*
	 * @GetMapping("/{startDate}/{endDate}/{codigo}")
	 * 
	 * public List<NfeEmitirResponse>
	 * getNfeEmitir(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate
	 * startDate,
	 * 
	 * @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
	 * 
	 * @RequestParam String codigo){
	 */
	
	@GetMapping("/{startDate}/{endDate}/{codigo}")
	public List<NfeEmitirResponse> getNfeListar(@PathVariable String startDate, @PathVariable String endDate, @PathVariable String codigo) {
    
		List<NfeEmitirResponse> retorno = nfeListarAssembler
				.toCollectionModel(nfeService.getNfeEmitir(startDate, endDate, codigo));
		return retorno;
	}

	@PostMapping("/send")
	public String putNfeEmitir(@RequestBody NfeRequest nfe) {

		String ret = nfeService.sendNfeWs(nfe);
		return ret;
	}


	@GetMapping("/servico")
	public List<ServicoNfeResponse> getSevicoNfe(){
		List<ServicoNfeResponse> retorno =  nfeListarAssembler.toCollectionModelServ(nfeService.getNfeServico());
		return retorno;
	}



}
