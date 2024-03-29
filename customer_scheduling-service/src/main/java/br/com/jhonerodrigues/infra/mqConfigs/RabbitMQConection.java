package br.com.jhonerodrigues.infra.mqConfigs;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import br.com.jhonerodrigues.core.mq.constQueue;
import jakarta.annotation.PostConstruct;

@Component
public class RabbitMQConection {
	private static final String NOME_EXCHANGE = "amq.direct";

	  private AmqpAdmin amqpAdmin;

	  public RabbitMQConection(AmqpAdmin amqpAdmin){
	    this.amqpAdmin = amqpAdmin;
	  }

	  private Queue queue(String queueName){
	    return new Queue(queueName, true, false, false);
	  }

	  private DirectExchange directExchange() {
	    return new DirectExchange(NOME_EXCHANGE);
	  }

	  private Binding relationship(Queue queue, DirectExchange exchange){
	    return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
	  }

	  //está função é executada assim que nossa classe é instanciada pelo Spring, devido a anotação @Component
	  @PostConstruct
	  private void add(){
	    Queue queueScheduling = this.queue(constQueue.QUEUE_NAME);

	    DirectExchange replace = this.directExchange();

	    Binding call = this.relationship(queueScheduling, replace);

	    //Criando as filas no RabbitMQ
	    this.amqpAdmin.declareQueue(queueScheduling);

	    this.amqpAdmin.declareExchange(replace);

	    this.amqpAdmin.declareBinding(call);
	  }
}
