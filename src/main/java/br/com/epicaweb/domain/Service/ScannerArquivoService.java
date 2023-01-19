package br.com.epicaweb.domain.Service;

import br.com.epicaweb.domain.model.ScannerArquivoModel;
import br.com.epicaweb.security.repository.ScannerArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScannerArquivoService {

    @Autowired
    private ScannerArquivoRepository scannerArquivoRepository;

    public List<ScannerArquivoModel> buscaArquivoPorDocumento(Long documento){
        return scannerArquivoRepository.findAll();
    }

    @Transactional
    public void salvarArquivoPorDocumento(ScannerArquivoModel scannerArquivoModel){
        scannerArquivoRepository.save(scannerArquivoModel);
    }
}
