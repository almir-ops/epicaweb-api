package br.com.epicaweb.security.repository;

import br.com.epicaweb.domain.model.ScannerDocumentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ScannerDocumentoRepository extends JpaRepository<ScannerDocumentoModel,Long> {

    List<ScannerDocumentoModel> findByClienteContaining(String nome);
    List<ScannerDocumentoModel> findByProposta(String proposta);

}
