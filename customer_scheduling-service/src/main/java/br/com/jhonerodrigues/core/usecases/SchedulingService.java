package br.com.jhonerodrigues.core.usecases;

import java.util.List;

import br.com.jhonerodrigues.core.DTO.SchedulingDTO;

public interface SchedulingService {
	
	List<SchedulingDTO> findAll();
	
}
