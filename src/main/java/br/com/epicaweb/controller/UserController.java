package br.com.epicaweb.controller;

import java.security.Principal;
import java.util.AbstractList;
import java.util.List;

import br.com.epicaweb.assembler.UsuarioAssembler;
import br.com.epicaweb.domain.Service.PermissaoService;
import br.com.epicaweb.ws.response.ContratoResponse;
import br.com.epicaweb.ws.response.PermissaoResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private PermissaoService permissaoService;

    UsuarioAssembler usuarioAssembler;

    @GetMapping
    public String getMethod(Principal user) {
        return user.getName();
    }

    @GetMapping("/permissao/{login}")
    public List<PermissaoResponse> getPermissaoAcesso(@PathVariable("login") String login){
     List<PermissaoResponse> permissao = usuarioAssembler.toCollectionModel(permissaoService.getPermissaoAcesso(login));
     return permissao;
    }




}
