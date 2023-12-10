package br.com.jhonerodrigues.core.usecases;

import java.util.List;

import br.com.jhonerodrigues.core.DTO.ProfessionalDTO;

public interface ProfessionalService {
	
	List <ProfessionalDTO> findAll();
	ProfessionalDTO findById(Long id);
}
