package br.com.jhonerodrigues.infra.scheduling;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jhonerodrigues.core.domain.Scheduling;

public interface jpaSchedulingRepository extends JpaRepository<Scheduling, Long>{

}
