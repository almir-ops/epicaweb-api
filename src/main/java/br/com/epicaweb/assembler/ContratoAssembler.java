package br.com.epicaweb.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.epicaweb.domain.model.ContratoModel;
import br.com.epicaweb.ws.response.ContratoResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ContratoAssembler {
	
	private ModelMapper modelMapper;
	
	public ContratoResponse toModel(ContratoModel contrato) {		

		return modelMapper.map(contrato, ContratoResponse.class);
	}
	
	public List<ContratoResponse> toCollectionModel(List<ContratoModel> contrato){		
		return contrato.stream()
				.map(this::toModel)
				.collect(Collectors.toList());				
	}

	/*
	public ContratoModel toEntity(ContratoRequest contratoRequest) {		
		return modelMapper.map(contratoRequest, ContratoModel.class);		
	}
	*/


	public ContratoResponse toModelA(String contrato) {
		return modelMapper.map(contrato, ContratoResponse.class);
	}
	
	public List<ContratoResponse> toCollectionModelA(List<String> contrato){
		return contrato.stream()
				.map(this::toModelA)
				.collect(Collectors.toList());
	}

}
