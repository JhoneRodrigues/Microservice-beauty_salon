package br.com.jhonerodrigues.core.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhonerodrigues.adapters.gateways.SchedulingRepository;
import br.com.jhonerodrigues.core.DTO.SchedulingDTO;
import br.com.jhonerodrigues.core.requests.SchedulingRequest;
import br.com.jhonerodrigues.core.response.SchedulingResponse;

@Service
public class SchedulingServiceImpl implements SchedulingService{
	
	@Autowired
	private SchedulingRepository repository;

	@Override
	public List<SchedulingDTO> findAll() {
		return repository.findAll();
	}

	@Override
	public SchedulingDTO findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public SchedulingResponse InsertSchedulingByUserId(Long id, SchedulingRequest request) {
		return repository.InsertSchedulingByUserId(id, request);
	}
}
