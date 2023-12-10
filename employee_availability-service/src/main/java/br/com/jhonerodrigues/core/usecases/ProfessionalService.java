package br.com.jhonerodrigues.core.usecases;

import java.util.List;

import br.com.jhonerodrigues.core.DTO.ProfessionalDTO;
import br.com.jhonerodrigues.core.requests.ProfessionalRequest;

public interface ProfessionalService {
	
	List <ProfessionalDTO> findAll();
	ProfessionalDTO findById(Long id);
	ProfessionalDTO insert (ProfessionalRequest request);
	ProfessionalDTO update (Long id, ProfessionalRequest request);
}
