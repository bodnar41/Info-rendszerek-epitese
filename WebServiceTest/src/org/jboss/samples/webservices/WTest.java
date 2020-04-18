package org.jboss.samples.webservices;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;
public class WTest {
	public static void main(String[] args) throws RemoteException, ServiceException {
		HelloWorldService hws = new HelloWorldServiceLocator();
		HelloWorld hw = hws.getHelloWorldPort();

		System.out.println(hw.sayHello("Zoli"));
	}
}
