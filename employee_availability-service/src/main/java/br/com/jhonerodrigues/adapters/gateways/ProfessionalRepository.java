package br.com.jhonerodrigues.adapters.gateways;

import java.util.List;

import br.com.jhonerodrigues.core.DTO.ProfessionalDTO;

public interface ProfessionalRepository {
	
	List <ProfessionalDTO> findAll();
}
