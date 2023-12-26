package br.com.jhonerodrigues.adapters.gateways;

import java.util.List;

import br.com.jhonerodrigues.core.DTO.SchedulingDTO;
import br.com.jhonerodrigues.core.requests.SchedulingRequest;

public interface SchedulingRepository {
	
	List<SchedulingDTO> findAll();
	SchedulingDTO findById(Long id);
	SchedulingDTO InsertSchedulingByUserId(Long id, SchedulingRequest request);
}
