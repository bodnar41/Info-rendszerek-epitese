package org.ait.beans;

import javax.ejb.Local;

@Local
public interface CalculatorBeanLocal {
	public int add(int x, int y); public int sub(int x, int y);
}
