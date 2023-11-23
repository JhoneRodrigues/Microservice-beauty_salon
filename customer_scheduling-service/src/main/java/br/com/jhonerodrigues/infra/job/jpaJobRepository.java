package br.com.jhonerodrigues.infra.job;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jhonerodrigues.core.domain.Job;

public interface jpaJobRepository extends JpaRepository<Job, Long>{

}
