package org.ait;

import org.jboss.annotation.ejb.Producer;

@Producer
public interface QueueTestRemote {
	public void queueFunction(String msg, int num);
}
