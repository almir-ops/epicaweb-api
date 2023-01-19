package br.com.epicaweb.assembler;

import br.com.epicaweb.domain.model.ClienteModel;
import br.com.epicaweb.domain.model.ContratoModel;
import br.com.epicaweb.ws.response.ClienteResponse;
import br.com.epicaweb.ws.response.ContratoResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ClienteAssembler {


    private ModelMapper modelMapper;

    public ClienteResponse toModel(ClienteModel cliente) {

        return modelMapper.map(cliente, ClienteResponse.class);
    }

    public List<ClienteResponse> toCollectionModel(List<ClienteModel> cliente){
        return cliente.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }


}
