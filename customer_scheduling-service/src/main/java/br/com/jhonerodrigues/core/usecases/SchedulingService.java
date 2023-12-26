package br.com.jhonerodrigues.core.usecases;

import java.util.List;

import br.com.jhonerodrigues.core.DTO.SchedulingDTO;
import br.com.jhonerodrigues.core.requests.SchedulingRequest;

public interface SchedulingService {
	
	List<SchedulingDTO> findAll();
	SchedulingDTO findById(Long id);
	SchedulingDTO InsertSchedulingByUserId(Long id, SchedulingRequest request);
}
