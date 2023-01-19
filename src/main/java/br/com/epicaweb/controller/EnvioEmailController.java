package br.com.epicaweb.controller;


import br.com.epicaweb.assembler.EnvioEmailAssembler;
import br.com.epicaweb.domain.Service.EnvioEmailService;
import br.com.epicaweb.ws.request.EnvioEmailRequest;
import br.com.epicaweb.ws.response.EnvioEmailResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/envioEmail")

public class EnvioEmailController {


    private EnvioEmailService envioEmailService;
    private EnvioEmailAssembler envioEmailAssembler;

    @PostMapping("/search")
    public List<EnvioEmailResponse> getListEmail(@RequestBody EnvioEmailRequest envioEmailRequest){
        List<EnvioEmailResponse> retorno =  envioEmailAssembler.toCollectionModel(envioEmailService.getEnvioEmail(envioEmailRequest));
        return retorno;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendEmailCobranca(@RequestBody EnvioEmailRequest envioEmailRequest) throws Exception {

        try {
            envioEmailService.sendEmail(envioEmailRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            envioEmailService.sendEmailError(envioEmailRequest.getProposta(), e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
