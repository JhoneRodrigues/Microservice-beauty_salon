package br.com.jhonerodrigues.adapters.gateways;

import java.util.List;

import br.com.jhonerodrigues.core.domain.Scheduling;
import br.com.jhonerodrigues.core.requests.SchedulingRequest;

public interface SchedulingRepository {
	
	List<Scheduling> findAll();
	Scheduling findById(Long id);
	Scheduling InsertSchedulingByUserId(Long id, SchedulingRequest request);
}
