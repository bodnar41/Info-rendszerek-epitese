package org.ait.mdb;

import java.util.Date;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/** * Message-Driven Bean implementation class for: QueueListener */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/MyQueue") })
public class QueueListener implements MessageListener {
	/** * Default constructor. */
	public QueueListener() {
	}

	/** * @see MessageListener#onMessage(Message) */
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				System.out.println("Queue: I received a TextMessage at " + new Date());
				TextMessage msg = (TextMessage) message;
				System.out.println("Az üzenet: " + msg.getText());
			} else if (message instanceof ObjectMessage) {
				System.out.println("Queue: I received a TextMessage at " + new Date());
				ObjectMessage msg = (ObjectMessage) message;
				Note note = (Note) msg.getObject();
				System.out.println("Note details:");
				System.out.println(note.getId());
				System.out.println(note.getText());
			} else {
				System.out.println("Not valid message");
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}