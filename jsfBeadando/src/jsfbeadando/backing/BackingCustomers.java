package jsfbeadando.backing;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import jsfbeadando.bo.CustomersBO;
import jsfbeadando.model.Customer;

@ManagedBean(name = "backingCustomers")
@SessionScoped
public class BackingCustomers {
	CustomersBO customersBO = new CustomersBO();
	@ManagedProperty(value = "#{backingEditCustomer}")
	private BackingEditCustomer backingEditCustomer;

	public BackingEditCustomer getBackingEditCustomer() {
		return backingEditCustomer;
	}

	public void setBackingEditCustomer(BackingEditCustomer backingEditCustomer) {
		this.backingEditCustomer = backingEditCustomer;
	}
	
	public List<Customer> findAllCustomers() {
		return customersBO.findAllCustomers();
	}

	public void delete(Customer customer) {
		customersBO.deleteCustomer(customer);
	}

	public String edit(Customer customer) {
		backingEditCustomer.setCustomer(customer);
		return "update-customer";
	}
}
