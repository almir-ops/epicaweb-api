package br.com.epicaweb.domain.Service;

import br.com.epicaweb.domain.model.EnvioSMSModel;
import br.com.epicaweb.ws.request.EnvioSMSRequest;

import java.util.List;

public interface EnvioSMSService {

     List<EnvioSMSModel> getAllEnvioSms(EnvioSMSRequest filtro);
     EnvioSMSModel getOneEnviosms(EnvioSMSRequest filtro);
     boolean geraLogEnvio(String proposta, String vencimento);
     boolean sendSMS(EnvioSMSRequest envioSMSRequest);

}
