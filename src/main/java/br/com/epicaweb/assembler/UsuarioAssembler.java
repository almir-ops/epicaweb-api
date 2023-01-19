package br.com.epicaweb.assembler;



import br.com.epicaweb.domain.model.ContratoModel;
import br.com.epicaweb.domain.model.PermissaoModel;
import br.com.epicaweb.ws.response.ContratoResponse;
import br.com.epicaweb.ws.response.PermissaoResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UsuarioAssembler {

    private ModelMapper modelMapper;

    public List<PermissaoResponse> toCollectionModel(List<PermissaoModel> permissao){
        return permissao.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public PermissaoResponse toModel(PermissaoModel permissao) {
        return modelMapper.map(permissao, PermissaoResponse.class);
    }


    public List<PermissaoResponse> toCollectionModelStr(List<String> permissao){
        return permissao.stream()
                .map(this::toModelStr)
                .collect(Collectors.toList());
    }

    public PermissaoResponse toModelStr(String permissao) {
        return modelMapper.map(permissao, PermissaoResponse.class);
    }









}
