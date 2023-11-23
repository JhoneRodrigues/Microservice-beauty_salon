package br.com.jhonerodrigues.adapters.gateways;

import java.util.List;

import br.com.jhonerodrigues.core.DTO.JobDTO;

public interface JobRepository {
	
	List<JobDTO> findAll();
	JobDTO findById(Long id);
}
