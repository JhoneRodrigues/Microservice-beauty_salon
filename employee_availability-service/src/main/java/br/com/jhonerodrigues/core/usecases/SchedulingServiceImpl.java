package br.com.jhonerodrigues.core.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhonerodrigues.adapters.gateways.SchedulingRepository;
import br.com.jhonerodrigues.core.requests.SchedulingRequest;

@Service
public class SchedulingServiceImpl implements SchedulingService{
	
	@Autowired
	private SchedulingRepository repository;
	
	@Override
	public void insert(SchedulingRequest request) {
		repository.insert(request);
	}
}
