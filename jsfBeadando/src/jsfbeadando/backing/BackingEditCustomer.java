package jsfbeadando.backing;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import jsfbeadando.bo.CompaniesBO;
import jsfbeadando.bo.CustomersBO;
import jsfbeadando.model.Company;
import jsfbeadando.model.Customer;
@ManagedBean(name="backingEditCustomer", eager=true)
@SessionScoped
public class BackingEditCustomer {
	private Customer customer = new Customer();	
	private CustomersBO customersBO = new CustomersBO();	
	private CompaniesBO companiesBO = new CompaniesBO();
	private List<Company> companiesList = new ArrayList<Company>();
	

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	

	public List<Company> getCompaniesList() {
		return companiesList;
	}


	public void init() {
		this.companiesList = companiesBO.findAllCompanies();
		
		// Add empty string as first company name (default)
		Company company = new Company();
		company.setName("");
		companiesList.add(0,company);
		
	}
	
	public String updateCustomer() {
		customersBO.updateCustomer(customer);	
		return "customers";		
	}
}
