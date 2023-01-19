package br.com.epicaweb.assembler;

import br.com.epicaweb.domain.model.EnvioSMSModel;
import br.com.epicaweb.ws.response.EnvioSMSResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EnvioSMSAssembler {

    private ModelMapper modelMapper;

    public EnvioSMSResponse toModel(EnvioSMSModel envioSMSModel){
        return modelMapper.map(envioSMSModel, EnvioSMSResponse.class);
    }

    public List<EnvioSMSResponse> toCollectionModel(List<EnvioSMSModel> envioSMSModel ){
        return envioSMSModel.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
