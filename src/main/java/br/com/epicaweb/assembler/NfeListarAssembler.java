package br.com.epicaweb.assembler;

import java.util.List;
import java.util.stream.Collectors;

import br.com.epicaweb.domain.model.ServicoNfeModel;
import br.com.epicaweb.ws.response.ServicoNfeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import br.com.epicaweb.domain.model.NfeListarModel;
import br.com.epicaweb.ws.response.NfeEmitirResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class NfeListarAssembler {
		
	private ModelMapper modelMapper;	
	
	public NfeEmitirResponse toModel(NfeListarModel nfe) {
		return modelMapper.map(nfe, NfeEmitirResponse.class);
	}
	
	public List<NfeEmitirResponse> toCollectionModel(List<NfeListarModel> nfe){
		return nfe.stream()
				.map(this::toModel)
				.collect(Collectors.toList());				
	}

	public ServicoNfeResponse toModelServ(ServicoNfeModel nfeServ) {
		return modelMapper.map(nfeServ, ServicoNfeResponse.class);
	}

	public List<ServicoNfeResponse> toCollectionModelServ(List<ServicoNfeModel> nfeServico){
		return nfeServico.stream()
				.map(this::toModelServ)
				.collect(Collectors.toList());
	}


}
