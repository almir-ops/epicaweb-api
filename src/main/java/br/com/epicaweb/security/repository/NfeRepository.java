package br.com.epicaweb.security.repository;

import br.com.epicaweb.domain.model.NfeEmitirModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.epicaweb.domain.model.NfeListarModel;


@Repository
public interface NfeRepository extends JpaRepository<NfeEmitirModel, String> {

}
