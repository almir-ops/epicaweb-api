package br.com.epicaweb.domain.Service;

import br.com.epicaweb.domain.model.NfeEmitirModel;
import br.com.epicaweb.domain.model.ServicoNfeModel;

import java.util.List;

public interface NfeService {

    List<NfeEmitirModel> getNfeEmitir(String datade, String dataAte, String codigo);
    List<ServicoNfeModel> getNfeServico();

}
