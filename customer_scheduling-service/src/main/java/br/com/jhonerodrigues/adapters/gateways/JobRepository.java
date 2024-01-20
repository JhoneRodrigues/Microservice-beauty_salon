package br.com.jhonerodrigues.adapters.gateways;

import java.util.List;

import br.com.jhonerodrigues.core.domain.Job;

public interface JobRepository {
	
	List<Job> findAll();
	Job findById(Long id);
}
