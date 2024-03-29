package br.com.jhonerodrigues.adapters.gateways;

import java.util.List;

import br.com.jhonerodrigues.core.domain.Scheduling;

public interface SchedulingRepository {
	
	List<Scheduling> findAll();
	Scheduling findById(Long id);
	Scheduling insert(Scheduling obj);
}
