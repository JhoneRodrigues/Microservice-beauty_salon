package br.com.jhonerodrigues.infra.professional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jhonerodrigues.core.domain.Professional;

public interface jpaProfessionalRepository extends JpaRepository<Professional, Long>{

}
