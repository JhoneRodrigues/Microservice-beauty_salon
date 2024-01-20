package br.com.jhonerodrigues.core.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhonerodrigues.adapters.gateways.SchedulingRepository;
import br.com.jhonerodrigues.core.DTO.SchedulingDTO;
import br.com.jhonerodrigues.core.requests.SchedulingRequest;

@Service
public class SchedulingServiceImpl implements SchedulingService{
	
	@Autowired
	private SchedulingRepository repository;

	@Override
	public List<SchedulingDTO> findAll() {
		var result = repository.findAll();
		return result.stream().map(x -> new SchedulingDTO(x)).toList();
	}

	@Override
	public SchedulingDTO findById(Long id) {
		return new SchedulingDTO (repository.findById(id));
	}

	@Override
	public SchedulingDTO InsertSchedulingByUserId(Long id, SchedulingRequest request) {
		return new SchedulingDTO (repository.InsertSchedulingByUserId(id, request));
	}
}
