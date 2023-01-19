package br.com.epicaweb.ws.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NfeEmitirResponse {
	   private String codigo;
	   private String cliente;
	   private String dataCredito;
	   private String proposta;
	   private String valorParcela;
	   private String parcela;
	   private String dataVencto;

}
