package com.jms.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;

import com.jms.main.service.SpitterAlertHandler;

//@Configuration
public class SpringMDPConfig {
	@Bean
	public MessageListenerContainer messageListnerContainer(
			ConnectionFactory connectionFactory,
			SpitterAlertHandler spitterHandler, ActiveMQQueue queue) {
		DefaultMessageListenerContainer listenerContainer = new DefaultMessageListenerContainer();
		listenerContainer.setConnectionFactory(connectionFactory);

		listenerContainer.setDestination(queue);

		listenerContainer.setMessageListener(spitterHandler);

		return listenerContainer;

	}
}
