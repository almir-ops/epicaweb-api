package br.com.epicaweb.security.repository;

import br.com.epicaweb.domain.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel,Long> {

}
