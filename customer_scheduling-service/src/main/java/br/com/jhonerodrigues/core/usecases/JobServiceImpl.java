package br.com.jhonerodrigues.core.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhonerodrigues.adapters.gateways.JobRepository;
import br.com.jhonerodrigues.core.DTO.JobDTO;

@Service
public class JobServiceImpl implements JobService{
	
	@Autowired
	private JobRepository repository;

	@Override
	public List<JobDTO> findAll() {
		var result = repository.findAll();
		return result.stream().map(x -> new JobDTO(x)).toList();
	}

	@Override
	public JobDTO findById(Long id) {
		return new JobDTO (repository.findById(id));
	}
}
