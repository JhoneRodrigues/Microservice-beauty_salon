package br.com.jhonerodrigues.infra.professional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jhonerodrigues.adapters.gateways.ProfessionalRepository;
import br.com.jhonerodrigues.core.DTO.ProfessionalDTO;
import br.com.jhonerodrigues.core.domain.Professional;

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
		Professional entity = repository.findById(id).get();
		return new ProfessionalDTO(entity);
	}
}
