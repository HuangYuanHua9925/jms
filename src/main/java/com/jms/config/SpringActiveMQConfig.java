package com.jms.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

@Configuration
@ComponentScan("com.jms")
public class SpringActiveMQConfig {
	@Bean("connectionFactory")
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL("tcp://127.0.0.1:61616");
		return connectionFactory;
	}

	@Bean("queue")
	public ActiveMQQueue queue() {
		ActiveMQQueue queue = new ActiveMQQueue("spitter.queue");
		return queue;
	}

	@Bean("topic")
	public ActiveMQTopic topic() {
		ActiveMQTopic topic = new ActiveMQTopic("spitter.ropic");
		return topic;
	}

	@Bean
	public MessageConverter messageConverter() {
		// return new SimpleMessageConverter();
		return new MappingJackson2MessageConverter();
	}

	@Bean
	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory,
			MessageConverter messageConverter, ActiveMQQueue queue) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setMessageConverter(messageConverter);
		jmsTemplate.setDefaultDestination(queue);
		return jmsTemplate;
	}

}
