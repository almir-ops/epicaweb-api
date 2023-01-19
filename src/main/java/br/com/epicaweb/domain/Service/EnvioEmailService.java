package br.com.epicaweb.domain.Service;


import br.com.epicaweb.domain.model.EnvioEmailModel;
import br.com.epicaweb.ws.request.EnvioEmailRequest;

import java.util.List;


public interface EnvioEmailService {
    List<EnvioEmailModel> getEnvioEmail(EnvioEmailRequest filtro);
    void sendEmail(EnvioEmailRequest envioEmailRequest);
    void sendEmailError(String proposta, String erro) throws Exception;
}
