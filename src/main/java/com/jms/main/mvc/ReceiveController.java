package com.jms.main.mvc;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jms.main.model.User;


@RequestMapping
@Controller
public class ReceiveController {
	@Autowired
	private JmsTemplate jmsTemplate;

	@RequestMapping(value = "/receive", method = RequestMethod.POST, produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public Object receive() throws JMSException {
		Message receive = jmsTemplate.receive();
		if (receive instanceof TextMessage) {
			System.out.println(((TextMessage) receive).getText());
			return ((TextMessage) receive).getText();
		}
		if(receive instanceof ObjectMessage){
			User user=(User) ((ObjectMessage) receive).getObject();
			return user;
		}
		System.out.println(receive.getClass());
		return null;
	}
}
