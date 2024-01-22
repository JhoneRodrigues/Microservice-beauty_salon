package br.com.jhonerodrigues.infra.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jhonerodrigues.adapters.gateways.SchedulingRepository;
import br.com.jhonerodrigues.core.domain.Scheduling;

@Repository
public class SchedulingRepositoryImpl implements SchedulingRepository{

	@Autowired
	private jpaSchedulingRepository repository;

	@Override
	public void insert(Scheduling request) {
		repository.save(request);
	}
}
