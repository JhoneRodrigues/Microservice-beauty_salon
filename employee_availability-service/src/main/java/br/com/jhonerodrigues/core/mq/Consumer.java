package br.com.jhonerodrigues.core.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.jhonerodrigues.adapters.gateways.SchedulingRepository;
import br.com.jhonerodrigues.core.requests.SchedulingRequest;

@Component
public class Consumer {
	
	@Autowired
	private SchedulingRepository repository;
	
	@RabbitListener(queues = "${broker.queue.scheduling.name}")
	public void listenSchedulingQueue(@Payload SchedulingRequest request) {
		repository.insert(request);
	}
}
