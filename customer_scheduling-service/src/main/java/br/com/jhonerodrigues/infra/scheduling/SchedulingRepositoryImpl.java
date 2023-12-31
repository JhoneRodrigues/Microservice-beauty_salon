package br.com.jhonerodrigues.infra.scheduling;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jhonerodrigues.adapters.gateways.SchedulingRepository;
import br.com.jhonerodrigues.core.DTO.SchedulingDTO;
import br.com.jhonerodrigues.core.domain.Job;
import br.com.jhonerodrigues.core.domain.Scheduling;
import br.com.jhonerodrigues.core.domain.User;
import br.com.jhonerodrigues.core.exceptions.ResourceNotFoundException;
import br.com.jhonerodrigues.core.mq.SchedulingProducer;
import br.com.jhonerodrigues.core.requests.JobId;
import br.com.jhonerodrigues.core.requests.SchedulingRequest;
import br.com.jhonerodrigues.infra.job.jpaJobRepository;
import br.com.jhonerodrigues.infra.user.jpaUserRepository;

@Repository
public class SchedulingRepositoryImpl implements SchedulingRepository{
	
	@Autowired
	private jpaSchedulingRepository schedulingRepository;
	
	@Autowired
	private jpaUserRepository userRepository;
	
	@Autowired
	private jpaJobRepository jobRepository;
	
	@Autowired
	private SchedulingProducer schedulingProducer;
	
	@Override
	public List<SchedulingDTO> findAll() {
		List <Scheduling> result= schedulingRepository.findAll();
		return result.stream().map(x -> new SchedulingDTO(x)).toList();
	}
	
	@Override
	public SchedulingDTO findById(Long id) {
		SchedulingDTO dto = schedulingRepository.findById(id)
				.map(SchedulingDTO::new)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		return dto;
	}

	@Override
	public SchedulingDTO InsertSchedulingByUserId(Long id, SchedulingRequest request) {
		
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		Scheduling scheduling = new Scheduling(request);
		
		Set<Job> list = new HashSet<>(jobRepository.findAllById(request.getJobs().stream()
                .map(JobId::getId)
                .collect(Collectors.toList())));
		
		schedulingProducer.sendScheduling(user, scheduling);
	    updateSchedulingAndUser(user, scheduling, list);
	    userRepository.save(user);
		return new SchedulingDTO(scheduling);
	}
	
	private void updateSchedulingAndUser(User user, Scheduling scheduling, Set<Job> jobs) {
	    scheduling.setJobs(jobs);
	    schedulingRepository.save(scheduling);
	    user.getSchedulings().add(scheduling);
	}
}
