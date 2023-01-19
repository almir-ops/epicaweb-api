package br.com.epicaweb.security.repository;

import br.com.epicaweb.domain.model.EnvioEmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvioEmailRepository extends JpaRepository<EnvioEmailModel,Long> {

}
