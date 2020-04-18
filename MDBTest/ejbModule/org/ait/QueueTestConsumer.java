package org.ait;

import javax.ejb.ActivationConfigProperty;

import org.jboss.annotation.ejb.Consumer;

@Consumer(activationConfig =
{
@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
@ActivationConfigProperty(propertyName="destination", propertyValue="queue/tutorial/example")
})
public class QueueTestConsumer implements QueueTestRemote {
	public void queueFunction(String msg, int num) {
		System.out.println("method1(" + msg + ", " + num + ")");
	}
}
