package br.com.jhonerodrigues.adapters.gateways;

import java.util.List;

import br.com.jhonerodrigues.core.domain.Professional;

public interface ProfessionalRepository {
	
	List <Professional> findAll();
}
