package br.com.jhonerodrigues.adapters.gateways;

import br.com.jhonerodrigues.core.requests.SchedulingRequest;

public interface SchedulingRepository {
	
	void insert (SchedulingRequest request);
}
