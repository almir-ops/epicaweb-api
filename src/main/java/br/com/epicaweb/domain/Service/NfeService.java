package br.com.epicaweb.domain.Service;

import br.com.epicaweb.domain.model.NfeListarModel;
import br.com.epicaweb.domain.model.ServicoNfeModel;
import br.com.epicaweb.ws.request.NfeRequest;

import java.util.List;

public interface NfeService {

    List<NfeListarModel> getNfeEmitir(String datade, String dataAte, String codigo);
    List<ServicoNfeModel> getNfeServico();
    String sendNfeWs(NfeRequest nfeRequest);

}
