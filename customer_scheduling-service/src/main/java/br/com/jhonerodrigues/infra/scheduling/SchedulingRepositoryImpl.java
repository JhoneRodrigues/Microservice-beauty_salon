package br.com.jhonerodrigues.infra.scheduling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jhonerodrigues.adapters.gateways.SchedulingRepository;
import br.com.jhonerodrigues.core.DTO.SchedulingDTO;
import br.com.jhonerodrigues.core.domain.Scheduling;
import br.com.jhonerodrigues.core.domain.exceptions.ResourceNotFoundException;

@Repository
public class SchedulingRepositoryImpl implements SchedulingRepository{
	
	@Autowired
	private jpaSchedulingRepository repository;
	
	@Override
	public List<SchedulingDTO> findAll() {
		List <Scheduling> result= repository.findAll();
		return result.stream().map(x -> new SchedulingDTO(x)).toList();
	}
	
	public SchedulingDTO findById(Long id) {
		SchedulingDTO dto = repository.findById(id)
				.map(SchedulingDTO::new)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		return dto;
	}
}
