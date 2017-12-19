package com.jms.main.service;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Component;

@Component("spitterHandler")
public class SpitterAlertHandler implements MessageListener {
	
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		System.out.println(message.toString());
	}
}
