package br.com.epicaweb.security.repository;

import br.com.epicaweb.domain.model.ScannerArquivoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScannerArquivoRepository extends JpaRepository<ScannerArquivoModel,Long> {
    List<ScannerArquivoModel> findAll();
}
