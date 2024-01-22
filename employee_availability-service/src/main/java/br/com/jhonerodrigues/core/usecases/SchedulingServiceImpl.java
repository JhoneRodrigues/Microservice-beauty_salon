package br.com.jhonerodrigues.core.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhonerodrigues.adapters.gateways.ProfessionalRepository;
import br.com.jhonerodrigues.adapters.gateways.SchedulingRepository;
import br.com.jhonerodrigues.core.domain.Professional;
import br.com.jhonerodrigues.core.domain.Scheduling;
import br.com.jhonerodrigues.core.requests.SchedulingRequest;

@Service
public class SchedulingServiceImpl implements SchedulingService{
	
	@Autowired
	private SchedulingRepository repository;
	
	@Autowired
	private ProfessionalRepository professionalRepository;
	
	@Override
	public void insert(SchedulingRequest request) {
		
		Professional professional = professionalRepository.findById(request.getProfessional_id());
		Scheduling scheduling = new Scheduling (request);
		
		repository.insert(scheduling);
		professional.getSchedulings().add(scheduling);
		professionalRepository.update(professional.getId(), professional);
	}
}
