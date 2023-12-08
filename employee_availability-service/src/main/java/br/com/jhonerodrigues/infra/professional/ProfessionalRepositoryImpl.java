package br.com.jhonerodrigues.infra.professional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jhonerodrigues.adapters.gateways.ProfessionalRepository;
import br.com.jhonerodrigues.core.domain.Professional;

@Repository
public class ProfessionalRepositoryImpl implements ProfessionalRepository{
	
	@Autowired
	private jpaProfessionalRepository repository;
	
	@Override
	public List<Professional> findAll() {
		return repository.findAll();
	}

}
