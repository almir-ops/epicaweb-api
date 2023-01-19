package br.com.epicaweb.assembler;

import br.com.epicaweb.domain.model.EnvioEmailModel;
import br.com.epicaweb.ws.response.EnvioEmailResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EnvioEmailAssembler {


    private ModelMapper modelMapper;


    public EnvioEmailResponse toModel(EnvioEmailModel envioEmailModel){
        return modelMapper.map(envioEmailModel, EnvioEmailResponse.class);
    }

    public List<EnvioEmailResponse> toCollectionModel(List<EnvioEmailModel> envioEmailModels ){
        return envioEmailModels.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }




}
