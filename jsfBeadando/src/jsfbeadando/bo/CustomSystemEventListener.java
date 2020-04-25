package jsfbeadando.bo;

import javax.faces.application.Application;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PostConstructApplicationEvent;
import javax.faces.event.PreDestroyApplicationEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

import jsfbeadando.model.Company;

public class CustomSystemEventListener implements SystemEventListener {
	
	@Override
	public boolean isListenerForSource(Object value) {
		//only for Application
		return (value instanceof Application);
	}
	
	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {
        if(event instanceof PostConstructApplicationEvent){
            System.out.println("Application Started. PostConstructApplicationEvent occurred!");
            CustomersBO customersBO = new CustomersBO();
            customersBO.createCustomerTable();
            CompaniesBO companiesBO = new CompaniesBO();
            String result = companiesBO.createCompaniesTable();
            if (result.equals("Created")) {
            	Company company = new Company();
            	company.setName("Mc Neil Corp.");
            	companiesBO.insertCompany(company);
            	
            	company.setName("Sanders");
            	companiesBO.insertCompany(company);
            	
            	company.setName("MicroWave");
            	companiesBO.insertCompany(company);
            	
            	company.setName("Holdmart CO");
            	companiesBO.insertCompany(company);
            	
            	company.setName("Genesys");
            	companiesBO.insertCompany(company);
            	
            	company.setName("Mosquitos");
            	companiesBO.insertCompany(company);
             }
            
        }
        if(event instanceof PreDestroyApplicationEvent){
            System.out.println("PreDestroyApplicationEvent occurred. Application is stopping.");
        }
    }
}