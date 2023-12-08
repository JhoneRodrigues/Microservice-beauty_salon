package br.com.jhonerodrigues.core.usecases;

import java.util.List;

import br.com.jhonerodrigues.core.domain.Professional;

public interface ProfessionalService {
	
	List <Professional> findAll();
}
