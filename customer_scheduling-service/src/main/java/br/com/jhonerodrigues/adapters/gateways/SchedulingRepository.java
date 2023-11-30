package br.com.jhonerodrigues.adapters.gateways;

import java.util.List;

import br.com.jhonerodrigues.core.DTO.SchedulingDTO;

public interface SchedulingRepository {
	
	List<SchedulingDTO> findAll();
}
