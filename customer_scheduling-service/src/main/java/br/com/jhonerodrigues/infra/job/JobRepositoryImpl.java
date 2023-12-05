package br.com.jhonerodrigues.infra.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jhonerodrigues.adapters.gateways.JobRepository;
import br.com.jhonerodrigues.core.DTO.JobDTO;
import br.com.jhonerodrigues.core.domain.Job;
import br.com.jhonerodrigues.core.exceptions.ResourceNotFoundException;

@Repository
public class JobRepositoryImpl implements JobRepository{
	
	@Autowired
	private jpaJobRepository repository;

	@Override
	public List<JobDTO> findAll() {
		List <Job> result= repository.findAll();
		return result.stream().map(x -> new JobDTO(x)).toList();
	}

	@Override
	public JobDTO findById(Long id) {
		JobDTO dto = repository.findById(id)
		        .map(JobDTO::new)
		        .orElseThrow(() -> new ResourceNotFoundException(id));
		return dto;
	}
}
