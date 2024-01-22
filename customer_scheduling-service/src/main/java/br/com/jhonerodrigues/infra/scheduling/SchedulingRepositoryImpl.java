package br.com.jhonerodrigues.infra.scheduling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jhonerodrigues.adapters.gateways.SchedulingRepository;
import br.com.jhonerodrigues.core.domain.Scheduling;
import br.com.jhonerodrigues.core.exceptions.ResourceNotFoundException;

@Repository
public class SchedulingRepositoryImpl implements SchedulingRepository {

	@Autowired
	private jpaSchedulingRepository repository;

	@Override
	public List<Scheduling> findAll() {
		return repository.findAll();
	}

	@Override
	public Scheduling findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Override
	public Scheduling insert(Scheduling obj) {
		return repository.save(obj);
	}
}
