package com.jms.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class TraditionJMSService {
	public static void main(String[] args) throws JMSException {
		ConnectionFactory cf = new ActiveMQConnectionFactory(
				"tcp://127.0.0.1:61616");
		Connection con = null;
		Session session = null;
		con = cf.createConnection();
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = new ActiveMQQueue("spitter.queue");
		MessageProducer producer = session.createProducer(destination);
		TextMessage message = session.createTextMessage();
		message.setText("hello world");
		producer.send(message);
		con.close();
		session.close();
	}

}
