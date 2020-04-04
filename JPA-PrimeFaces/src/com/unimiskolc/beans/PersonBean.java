package com.unimiskolc.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.unimiskolc.model.Person;

@ManagedBean
public class PersonBean {
	@PersistenceContext(unitName = "worksheetApp")
	private EntityManager entityManager;
	@Resource
	private UserTransaction userTransaction;

	private Person person = new Person();

	private List<Person> persons;
	private List<Person> availablePersons;

	@PostConstruct
	public void init() {
		if (availablePersons == null) {
			String q = "SELECT o from Person o";
			Query query = entityManager.createQuery(q);
			availablePersons = query.getResultList();
		}
	}


	public void addNew() throws Exception {
		Person p = new Person();
		p.setName(person.getName());
		p.setDisplayColor(person.getDisplayColor());
		
		userTransaction.begin();
		entityManager.persist(p);
		userTransaction.commit();
	}

	// entityManager.merge() lehet updatelni az adatot
	public void onRowEdit(RowEditEvent event) throws Exception {
		Person workSheet = (Person) event.getObject();
		
		userTransaction.begin();
		entityManager.merge(workSheet);
		userTransaction.commit();
		FacesMessage msg = new FacesMessage("Elmentve!", ((Person) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Megszakítás", ((Person) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}


	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
	}


	public List<Person> getPersons() {
		return persons;
	}


	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}


	public List<Person> getAvailablePersons() {
		return availablePersons;
	}


	public void setAvailablePersons(List<Person> availablePersons) {
		this.availablePersons = availablePersons;
	}
	
}
