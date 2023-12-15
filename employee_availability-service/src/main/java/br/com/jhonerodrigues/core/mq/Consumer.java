package br.com.jhonerodrigues.core.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	
	@RabbitListener(queues = "${broker.queue.email.name}")
	public void listenSchedulingQueue(@Payload String str) {
		System.out.println(str);
	}
}
