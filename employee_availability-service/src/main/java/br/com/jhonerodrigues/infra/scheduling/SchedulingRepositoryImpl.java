package br.com.jhonerodrigues.infra.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jhonerodrigues.adapters.gateways.SchedulingRepository;
import br.com.jhonerodrigues.core.domain.Scheduling;
import br.com.jhonerodrigues.core.exceptions.ResourceNotFoundException;
import br.com.jhonerodrigues.core.requests.SchedulingRequest;
import br.com.jhonerodrigues.infra.professional.jpaProfessionalRepository;

@Repository
public class SchedulingRepositoryImpl implements SchedulingRepository{

	@Autowired
	private jpaSchedulingRepository schedulingRepository;
	
	@Autowired
	private jpaProfessionalRepository professionalRepository;
	
	@Override
	public void insert(SchedulingRequest request) {
		
		var professional = professionalRepository.findById(request.getProfessional_id())
				.orElseThrow(() -> new ResourceNotFoundException(request.getProfessional_id()));
		Scheduling scheduling = new Scheduling(request);
		
		schedulingRepository.save(scheduling);
		professional.getSchedulings().add(scheduling);
		professionalRepository.save(professional);
	}
}
