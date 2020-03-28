package org.ait.beans;

import java.util.HashMap;
import javax.ejb.Local;
import javax.ejb.Remove;

@Local
public interface ShoppingCartBeanLocal {
	public void buy(String product, int quality);

	public HashMap<String, Integer> getCartContent();

	@Remove
	public void checkout();
}
