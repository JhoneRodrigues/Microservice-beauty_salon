package br.com.jhonerodrigues.infra.professional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jhonerodrigues.adapters.gateways.ProfessionalRepository;
import br.com.jhonerodrigues.core.DTO.ProfessionalDTO;
import br.com.jhonerodrigues.core.domain.Professional;
import br.com.jhonerodrigues.core.exceptions.ResourceNotFoundException;
import br.com.jhonerodrigues.core.requests.ProfessionalRequest;

@Repository
public class ProfessionalRepositoryImpl implements ProfessionalRepository{
	
	@Autowired
	private jpaProfessionalRepository repository;
	
	@Override
	public List<ProfessionalDTO> findAll() {
		List <Professional> list = repository.findAll();
		return list.stream().map(x -> new ProfessionalDTO(x)).toList();
	}

	@Override
	public ProfessionalDTO findById(Long id) {
		ProfessionalDTO dto = repository.findById(id)
				.map(ProfessionalDTO::new)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		return dto;
	}

	@Override
	public ProfessionalDTO insert(ProfessionalRequest request) {
		Professional entity = repository.save(new Professional(request));
		return new ProfessionalDTO(entity);
	}

	@Override
	public ProfessionalDTO update(Long id, ProfessionalRequest request) {
		Professional entity = new Professional(findById(id));
		repository.save(updateData(entity, request));
		return new ProfessionalDTO(entity);
	}
	
	public Professional updateData(Professional obj, ProfessionalRequest entityByRequest) {
		obj.setDescription(entityByRequest.getDescription());
		obj.setName(entityByRequest.getName());
		obj.setUrlImage(entityByRequest.getUrlImage());
		return obj;
	}
}
