package com.jms.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class TraditionJMSClient {

	public static void main(String[] args) throws JMSException {
		ConnectionFactory cf = new ActiveMQConnectionFactory(
				"tcp://127.0.0.1:61616");
		Connection con = null;
		Session session = null;
		con = cf.createConnection();
		con.start();
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination destination = new ActiveMQQueue("spitter.queue");
		MessageConsumer consumer =session.createConsumer(destination);
		Message message =consumer.receive();
		TextMessage textMessage=(TextMessage)message;
		System.out.println("get a message "+textMessage.getText());
		con.start();
		con.close();
		session.close();
	}

}
