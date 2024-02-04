package br.com.jhonerodrigues.core.usecases;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhonerodrigues.adapters.gateways.SchedulingRepository;
import br.com.jhonerodrigues.core.DTO.SchedulingDTO;
import br.com.jhonerodrigues.core.domain.Job;
import br.com.jhonerodrigues.core.domain.Scheduling;
import br.com.jhonerodrigues.core.domain.User;
import br.com.jhonerodrigues.core.mq.SchedulingProducer;
import br.com.jhonerodrigues.core.requests.JobId;
import br.com.jhonerodrigues.core.requests.SchedulingRequest;

@Service
public class SchedulingServiceImpl implements SchedulingService{
	
	@Autowired
	private SchedulingRepository repository;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SchedulingProducer producer;
	
	@Override
	public List<SchedulingDTO> findAll() {
		var result = repository.findAll();
		return result.stream().map(x -> new SchedulingDTO(x)).toList();
	}

	@Override
	public SchedulingDTO findById(Long id) {
		return new SchedulingDTO (repository.findById(id));
	}

	@Override
	public SchedulingDTO InsertSchedulingByUserId(Long id, SchedulingRequest request) {
		
//		Salvando o agendamento
		Set <Job> jobs = new HashSet<>();
		
		for (JobId jobId: request.getJobs()) {
			var dto = jobService.findById(jobId.getId());
			Job job = new Job();
			BeanUtils.copyProperties(dto, job);
			jobs.add(job);
		}
		
		var scheduling = new Scheduling(request);
		scheduling.setJobs(jobs);
		scheduling = repository.insert(scheduling);
		
//		Atualizando a lista de agendamentos no usuario
		User user = new User(userService.findById(id));
		user.getSchedulings().add(scheduling);
		userService.update(id, user);
		
//		Envio da mensagem para o outro serviço
		producer.sendScheduling(user, scheduling);
		
		return new SchedulingDTO(scheduling);
	}
}
