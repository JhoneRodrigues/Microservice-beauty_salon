package br.com.jhonerodrigues.core.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jhonerodrigues.core.DTO.SchedulingDTOForMessage;
import br.com.jhonerodrigues.core.domain.Scheduling;
import br.com.jhonerodrigues.core.domain.User;

@Component
public class SchedulingProducer {
	
	@Autowired
	private RabbitTemplate template;
	
	private String rountingKey = constQueue.QUEUE_NAME;
	
	public void sendScheduling(User user, Scheduling scheduling) {
		var schedulingDTO = new SchedulingDTOForMessage();
		schedulingDTO.setClient_name(user.getName());
		schedulingDTO.setClient_email(user.getEmail());
		schedulingDTO.setCol_day(scheduling.getCol_day());
		schedulingDTO.setCol_time(scheduling.getCol_time());
		schedulingDTO.setProfessional_id(scheduling.getProfessional_id());
		
		template.convertAndSend("", rountingKey, schedulingDTO);
	}
}
