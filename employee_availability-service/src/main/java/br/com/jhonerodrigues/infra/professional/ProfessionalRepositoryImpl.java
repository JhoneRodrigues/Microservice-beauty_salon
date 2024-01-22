package br.com.jhonerodrigues.infra.professional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jhonerodrigues.adapters.gateways.ProfessionalRepository;
import br.com.jhonerodrigues.core.domain.Professional;
import br.com.jhonerodrigues.core.exceptions.ResourceNotFoundException;

@Repository
public class ProfessionalRepositoryImpl implements ProfessionalRepository{
	
	@Autowired
	private jpaProfessionalRepository repository;
	
	@Override
	public List<Professional> findAll() {
		return repository.findAll();
	}

	@Override
	public Professional findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Override
	public Professional insert(Professional request) {
		return repository.save(request);
	}

	@Override
	public Professional update(Long id, Professional request) {
		Professional entity = findById(id);
		repository.save(updateData(entity, request));
		return entity;
	}
	
	public Professional updateData(Professional obj, Professional entityByRequest) {
		obj.setDescription(entityByRequest.getDescription());
		obj.setName(entityByRequest.getName());
		obj.setUrlImage(entityByRequest.getUrlImage());
		return obj;
	}
}
