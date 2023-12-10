package br.com.jhonerodrigues.adapters.gateways;

import java.util.List;

import br.com.jhonerodrigues.core.DTO.ProfessionalDTO;
import br.com.jhonerodrigues.core.requests.ProfessionalRequest;

public interface ProfessionalRepository {
	
	List <ProfessionalDTO> findAll();
	ProfessionalDTO findById(Long id);
	ProfessionalDTO insert (ProfessionalRequest request);
	ProfessionalDTO update (Long id, ProfessionalRequest request);
}
