package br.com.epicaweb.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.epicaweb.domain.model.ContratoModel;

@Repository
public interface ContratoRepository extends JpaRepository<ContratoModel, String>{

}
