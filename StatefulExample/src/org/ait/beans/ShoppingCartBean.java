package org.ait.beans;

import javax.ejb.LocalBean;

import javax.ejb.Stateless;
import java.util.HashMap;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class ShoppingCartBean
 */
@Stateless
@LocalBean
public class ShoppingCartBean implements ShoppingCartBeanLocal {

	private HashMap<String, Integer> cart = new HashMap<String, Integer>();

	public ShoppingCartBean() {
	}

	@Override
	public void buy(String product, int quality) {
		if (cart.containsKey(product)) {
			int currqt = cart.get(product);
			currqt += quality;
			cart.put(product, currqt);
		} else {
			cart.put(product, quality);
		}
	}

	@Override public HashMap<String, Integer> getCartContent() { return cart;

}

	@Override
	public void checkout() {
		System.out.println("To be implemented");
		
	}
}
