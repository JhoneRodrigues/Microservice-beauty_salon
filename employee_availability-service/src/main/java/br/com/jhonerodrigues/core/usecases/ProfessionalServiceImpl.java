package br.com.jhonerodrigues.core.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhonerodrigues.adapters.gateways.ProfessionalRepository;
import br.com.jhonerodrigues.core.DTO.ProfessionalDTO;
import br.com.jhonerodrigues.core.requests.ProfessionalRequest;

@Service
public class ProfessionalServiceImpl implements ProfessionalService{

	@Autowired
	private ProfessionalRepository repository;
	
	@Override
	public List<ProfessionalDTO> findAll() {
		return repository.findAll();
	}

	@Override
	public ProfessionalDTO findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public ProfessionalDTO insert(ProfessionalRequest request) {
		return repository.insert(request);
	}

	@Override
	public ProfessionalDTO update(Long id, ProfessionalRequest request) {
		return repository.update(id, request);
	}
}
