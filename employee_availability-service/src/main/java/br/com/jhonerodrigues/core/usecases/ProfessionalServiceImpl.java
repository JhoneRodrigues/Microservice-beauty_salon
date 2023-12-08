package br.com.jhonerodrigues.core.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhonerodrigues.adapters.gateways.ProfessionalRepository;
import br.com.jhonerodrigues.core.domain.Professional;

@Service
public class ProfessionalServiceImpl implements ProfessionalService{

	@Autowired
	private ProfessionalRepository repository;
	
	@Override
	public List<Professional> findAll() {
		return repository.findAll();
	}

}
