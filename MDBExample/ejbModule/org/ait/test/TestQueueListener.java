package org.ait.test;
import java.util.Properties; import javax.annotation.Resource; import javax.jms.*; import javax.naming.*
import org.ait.mdb.Note; import org.ait.mdb.QueueListener; import org.hornetq.api.core.*; import org.hornetq.api.core.client.*; import org.hornetq.api.jms.HornetQJMSClient; import org.hornetq.api.jms.JMSFactoryType; import org.hornetq.core.remoting.impl.invm.InVMConnectorFactory; import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
public class TestQueueListener {

	public static void main(String[] args) { final String QUEUE_LOOKUP = "queue/MyQueue"; final String CONNECTION_FACTORY = "ConnectionFactory"; try { TransportConfiguration transportConfiguration = new TransportConfiguration(NettyConnectorFactory.class.getName()); ConnectionFactory connectionFactory = (ConnectionFactory) HornetQJMSClient.createConnectionFactoryWithoutHA(JMSFactoryType.CF,transportConfiguration); //The queue name should match the jms-queue name in standalone.xml Queue queue = HornetQJMSClient.createQueue("testQueue"); Connection connection = connectionFactory.createConnection(); Session session = connection.createSession(false, QueueSession.AUTO_ACKNOWLEDGE); MessageProducer messageProducer = session.createProducer(queue); TextMessage message = session.createTextMessage(); message.setText("Hello EJB3 MDB Queue!!!"); messageProducer.send(message); System.out.println("Sent TextMessage to the Queue"); ObjectMessage objMsg = session.createObjectMessage(); Note note = new Note(); note.setId(2163); note.setText("One note to the Queue."); objMsg.setObject(note); messageProducer.send(objMsg); System.out.println("Sent ObjectMessage to the Queue"); session.close(); } catch (JMSException e) {
	e.printStackTrace(); } catch (Exception e) { e.printStackTrace(); } }
}
