package br.com.jhonerodrigues.infra.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jhonerodrigues.adapters.gateways.JobRepository;
import br.com.jhonerodrigues.core.domain.Job;
import br.com.jhonerodrigues.core.exceptions.ResourceNotFoundException;

@Repository
public class JobRepositoryImpl implements JobRepository{
	
	@Autowired
	private jpaJobRepository repository;

	@Override
	public List<Job> findAll() {
		return repository.findAll();
	}

	@Override
	public Job findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}
}
