package br.com.epicaweb.controller;


import br.com.epicaweb.assembler.ClienteAssembler;
import br.com.epicaweb.domain.Service.ClienteService;
import br.com.epicaweb.ws.response.ClienteResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/cliente")
public class ClienteController {

    private ClienteService   clienteService;
    private ClienteAssembler clienteAssembler;

    @GetMapping("/{cpf_cnpj}")
    public ClienteResponse getCliente(@PathVariable("cpf_cnpj") String cpfCnpj){
        return clienteAssembler.toModel(clienteService.getCliente(cpfCnpj));
    }

}
