package br.com.jhonerodrigues.adapters.gateways;

import java.util.List;

import br.com.jhonerodrigues.core.domain.Professional;

public interface ProfessionalRepository {
	
	List <Professional> findAll();
	Professional findById(Long id);
	Professional insert (Professional request);
	Professional update (Long id, Professional request);
}
