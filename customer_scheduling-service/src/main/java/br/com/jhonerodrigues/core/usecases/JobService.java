package br.com.jhonerodrigues.core.usecases;

import java.util.List;

import br.com.jhonerodrigues.core.DTO.JobDTO;

public interface JobService {
	
	List<JobDTO> findAll();
	JobDTO findById(Long id);
}
