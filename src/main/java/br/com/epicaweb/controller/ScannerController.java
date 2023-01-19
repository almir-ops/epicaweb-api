package br.com.epicaweb.controller;

import br.com.epicaweb.domain.Service.ScannerDocumentoService;
import br.com.epicaweb.domain.model.ScannerDocumentoModel;
import br.com.epicaweb.ws.request.ScannerSearchRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/scanner")
public class ScannerController {


    ScannerDocumentoService scannerDocumentoService;

    @PostMapping("/search")
    public ResponseEntity<?> listar( @RequestBody ScannerSearchRequest scannerSearchRequest){
        List<ScannerDocumentoModel> documento = null;
        documento = scannerDocumentoService.searchDocumento(scannerSearchRequest);
        return ResponseEntity.ok(documento);
    }


    @PostMapping("/save")
    public ResponseEntity<?> saveDocument (
           @RequestParam String proposta , @RequestParam(required = false) Long codigo,
           @RequestParam String nome , @RequestParam(required = false) String cpf,
           @RequestParam String email , @RequestParam(required = false) String setor,
           @RequestParam String quadra , @RequestParam(required = false) String lote,
           @RequestParam String falecimento , @RequestParam String scanner,
           @RequestPart(value = "file", required = false) MultipartFile[] files) throws IOException, InterruptedException {

           LocalDateTime hoje = LocalDateTime.now();
           DateTimeFormatter formatDT = DateTimeFormatter.ofPattern("d/MM/yyyy hh:MM:ss");
           hoje.format(formatDT);

           ScannerDocumentoModel scannerDocumentoModel = new ScannerDocumentoModel();
           scannerDocumentoModel.setCodigo(codigo);
           scannerDocumentoModel.setProposta(proposta);
           scannerDocumentoModel.setCliente(nome.toUpperCase());
           scannerDocumentoModel.setCpf(cpf);
           scannerDocumentoModel.setEmail(email);
           scannerDocumentoModel.setSetor(setor);
           scannerDocumentoModel.setQuadra(quadra);
           scannerDocumentoModel.setLote(lote);
           scannerDocumentoModel.setData_reg(hoje);
           scannerDocumentoModel.setFalecimento(falecimento);

           scannerDocumentoService.salvar(scannerDocumentoModel, files, scanner);

           return ResponseEntity.ok("Documento salvo com sucesso!!!");
    }


}
