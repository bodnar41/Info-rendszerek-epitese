package org.ait.test;

import javax.naming.InitialContext;

import org.ait.QueueTestRemote;
import org.jboss.ejb3.mdb.ProducerManager;
import org.jboss.ejb3.mdb.ProducerObject;

public class TestMDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InitialContext ctx;

		System.setProperty(InitialContext.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		System.setProperty(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		System.setProperty(InitialContext.URL_PKG_PREFIXES,
				"org.jboss.naming:org.jnp.interfaces");

		try {
			ctx = new InitialContext();
			QueueTestRemote tester = (QueueTestRemote) ctx
					.lookup(QueueTestRemote.class.getName());

			ProducerObject po = (ProducerObject) tester;
			ProducerManager manager = po.getProducerManager();

			manager.connect(); // internally create a JMS connection
			try {
				tester.queueFunction("hello world", 55);
			} finally {
				manager.close(); // clean up the JMS connection
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
