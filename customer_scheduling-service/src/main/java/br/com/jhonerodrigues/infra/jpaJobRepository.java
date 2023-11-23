package br.com.jhonerodrigues.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jhonerodrigues.core.domain.Job;

public interface jpaJobRepository extends JpaRepository<Job, Long>{

}
