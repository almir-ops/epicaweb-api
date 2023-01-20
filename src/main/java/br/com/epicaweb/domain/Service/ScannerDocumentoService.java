package br.com.epicaweb.domain.Service;

import br.com.epicaweb.domain.model.ScannerArquivoModel;
import br.com.epicaweb.domain.model.ScannerDocumentoModel;
import br.com.epicaweb.security.repository.ScannerDocumentoRepository;
import br.com.epicaweb.thread.RemoverArquivo;
import br.com.epicaweb.ws.request.ScannerSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScannerDocumentoService {


    @Autowired
    ScannerArquivoService scannerArquivoService;

    @Autowired
    private ScannerDocumentoRepository scannerDocumentoRepository;

    public List<ScannerDocumentoModel> buscarPorCliente(String nome) {
        return scannerDocumentoRepository.findByClienteContaining(nome);
    }

    public  List<ScannerDocumentoModel> buscaPorProposta(String proposta){
        return scannerDocumentoRepository.findByProposta(proposta);
    }

    public List<ScannerDocumentoModel> searchDocumento (ScannerSearchRequest scannerListaRequest){

        return scannerDocumentoRepository.findAll().stream()
               .filter( documento -> scannerListaRequest.getProposta().equals("")      || documento.getProposta().equals(scannerListaRequest.getProposta()))
               .filter( documento -> scannerListaRequest.getCliente().equals("")       || documento.getCliente().startsWith(scannerListaRequest.getCliente().toUpperCase()))
               .filter( documento -> scannerListaRequest.getQuadra().equals("")        || documento.getQuadra().equals(scannerListaRequest.getQuadra()))
               .filter( documento -> scannerListaRequest.getLote().equals("")          || documento.getLote().equals(scannerListaRequest.getLote()))
               .filter( documento -> scannerListaRequest.getSetor().equals("")         || documento.getSetor().equals(scannerListaRequest.getSetor()))
               .filter( documento -> scannerListaRequest.getDtFalecimento().equals("") || documento.getFalecimento().equals(scannerListaRequest.getDtFalecimento()))
               .collect(Collectors.toList());
    }

    @Transactional
    public ScannerDocumentoModel salvar(ScannerDocumentoModel scannerDocumento , MultipartFile[] files, String scanner){

           scannerDocumento = salvarDoc(scannerDocumento);

           if(files != null) {
               upLoadFile(scannerDocumento, files, scanner);
           }

           return scannerDocumento;
    }



//    public  List<ScannerArquivoModel>  upLoadFile(ScannerDocumentoModel scannerDocumentoModel, MultipartFile[] files, String scanner){
     public void upLoadFile(ScannerDocumentoModel scannerDocumentoModel, MultipartFile[] files, String scanner){

        List<ScannerArquivoModel> scannerArquivo =  new ArrayList<ScannerArquivoModel>();

        for (int i  = 0 ; i < files.length; i++){

            ScannerArquivoModel arquivos = new ScannerArquivoModel();
            String fileName = StringUtils.cleanPath(files[i].getOriginalFilename());
            String UPLOAD_LOCAL = "//192.168.0.5/Documentos Digitalizados/OBITOS/";
            String pathDestinoLocal = UPLOAD_LOCAL + scannerDocumentoModel.getProposta() + "/";
            String pathDestinoSystemImage = "obito/" + scannerDocumentoModel.getProposta() + "/";

            if (!fileName.isBlank()){
                String[] extensao = fileName.split("\\.");
                Date now = new Date();
                String nowString = now.toString();
                String removendoEspaco = nowString.replaceAll("\\s+", "");
                String removendoCartacter = removendoEspaco.replaceAll(":", "");
                fileName = scannerDocumentoModel.getProposta() + "_" + removendoCartacter + "_" + i + "." + extensao[1];

                arquivos.setArquivo_destino(fileName);
                arquivos.setPath_destino(pathDestinoLocal + fileName);
                arquivos.setDocumentos(scannerDocumentoModel);
                arquivos.setPath_sys(pathDestinoSystemImage + fileName);
            }

            scannerArquivoService.salvarArquivoPorDocumento(arquivos);
            scannerArquivo.add(arquivos);

            try{
                Path path = Paths.get(pathDestinoLocal);
                boolean diretorioExistenteLocal = Files.exists(path);

                // Diretório no Local/Servidor
                if (diretorioExistenteLocal) {
                    path = Paths.get(pathDestinoLocal + fileName);
                    Files.copy(files[i].getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    Files.createDirectory(path);
                    path = Paths.get(pathDestinoLocal + fileName);
                    Files.copy(files[i].getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                }
                new RemoverArquivo(scanner);

            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
//      return scannerArquivo;
    }

    @Transactional
    public ScannerDocumentoModel salvarDoc(ScannerDocumentoModel salvardoc){

        return scannerDocumentoRepository.save(salvardoc);

    }


}
