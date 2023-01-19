package br.com.epicaweb.controller;

import br.com.epicaweb.assembler.EnvioSMSAssembler;
import br.com.epicaweb.domain.Service.EnvioEmailService;
import br.com.epicaweb.domain.Service.EnvioSMSService;
import br.com.epicaweb.ws.request.EnvioSMSRequest;
import br.com.epicaweb.ws.response.EnvioSMSResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/envioSms")
public class EnvioSMSController {

    private EnvioSMSAssembler envioSMSAssembler;
    private EnvioSMSService envioSMSService;
    private EnvioEmailService envioEmailService;


    @PostMapping("/search")
    public List<EnvioSMSResponse> getListSMS(@RequestBody EnvioSMSRequest envioSMSRequest){
        List<EnvioSMSResponse> retorno =  envioSMSAssembler.toCollectionModel(envioSMSService.getAllEnvioSms(envioSMSRequest));
        return retorno;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendSMS(@RequestBody EnvioSMSRequest envioSMSRequest) throws Exception {
        try {
            envioSMSService.sendSMS(envioSMSRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            envioEmailService.sendEmailError(envioSMSRequest.getProposta(), "Erro no envio de SMS da proposta" + envioSMSRequest.getProposta() );
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


}
