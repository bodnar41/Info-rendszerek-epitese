package com.unimiskolc.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.unimiskolc.model.Person;
import com.unimiskolc.model.Worksheet;

@ManagedBean
public class WorkSheetBean {
	@PersistenceContext(unitName = "worksheetApp")
	private EntityManager entityManager;
	@Resource
	private UserTransaction userTransaction;

	@ManagedProperty(value = "#{timeLineView}")
	private TimeLineView timeline;

	private Worksheet workSheet = new Worksheet();

	private List<Worksheet> workSheets;
	private List<Person> availablePersons;
	private List<String> selectedPersonIDs;

	public WorkSheetBean() {

	}

	@PostConstruct
	public void init() {
		if (availablePersons == null) {
			String q = "SELECT b from Person b";
			Query query = entityManager.createQuery(q);
			availablePersons = query.getResultList();
		}
	}

	public void saveWorkSheet(Worksheet workSheet) {
		entityManager.persist(workSheet);
	}

	public List<Worksheet> getAllWorkSheets() {
		// mindig egy mentett listát kell visszadni, mert egyébként nem lehet
		// editálni a táblában
		if (workSheets == null) {
			// a WorkSheet case sensitive!!! ugyanaz legyen a neve mint a JPA
			// bean nek!
			String q = "SELECT b from Worksheet b";
			Query query = entityManager.createQuery(q);
			workSheets = query.getResultList();
		}
		return workSheets;
	}

	public Worksheet getWorkSheet() {
		return workSheet;
	}

	public void setWorkSheet(Worksheet book) {
		this.workSheet = book;
	}

	public List<Person> getAvailablePersons() {
		return availablePersons;
	}

	public TimeLineView getTimeline() {
		return timeline;
	}

	public void setTimeline(TimeLineView timeline) {
		this.timeline = timeline;
	}

	public void setAvailablePersons(List<Person> persons) {
		this.availablePersons = persons;
	}

	public List<String> getSelectedPersonIDs() {
		return selectedPersonIDs;
	}

	public void setSelectedPersonIDs(List<String> selectedPersonIDs) {
		this.selectedPersonIDs = selectedPersonIDs;
	}

	public void addNew() throws Exception {
		Worksheet b = new Worksheet();
		b.setDescription(workSheet.getDescription());
		Calendar calendar = new GregorianCalendar();
		Date initTime = new Date();
		calendar.setTime(initTime);
		b.setStartDate(new Date());
		b.setEndDate(new Date());
		b.setDueDate(new Date());
		userTransaction.begin();
		entityManager.persist(b);
		userTransaction.commit();
		// reload timeline
		timeline.initialize();
		RequestContext.getCurrentInstance().update("workSheetForm:timeline");
	}

	// entityManager.merge() lehet updatelni az adatot
	public void onRowEdit(RowEditEvent event) throws Exception {
		userTransaction.begin();
		Worksheet workSheet = (Worksheet) event.getObject();
		if (!selectedPersonIDs.isEmpty()) {
			Query query = entityManager.createQuery("SELECT e FROM Person e WHERE e.id in :ids");
			List idList = new ArrayList<Integer>();
			for (String id : selectedPersonIDs) {
				idList.add(Integer.parseInt(id));
			}
			query.setParameter("ids", idList);
			List resultList = query.getResultList();
			workSheet.setPersons(resultList);
		}

		entityManager.merge(workSheet);
		userTransaction.commit();
		FacesMessage msg = new FacesMessage("Elmentve!", ((Worksheet) event.getObject()).getDescription());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		// reload timeline
		timeline.initialize();
		RequestContext.getCurrentInstance().update("workSheetForm:timeline");
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Megszakítás", ((Worksheet) event.getObject()).getDescription());
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
}
