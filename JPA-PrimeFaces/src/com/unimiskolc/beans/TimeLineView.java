package com.unimiskolc.beans;

import java.io.Serializable;
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

import org.primefaces.event.timeline.TimelineModificationEvent;
import org.primefaces.event.timeline.TimelineSelectEvent;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;

import com.unimiskolc.model.Worksheet;

@ManagedBean
public class TimeLineView implements Serializable {
	@PersistenceContext(unitName = "worksheetApp")
	private EntityManager entityManager;
	@Resource
	private UserTransaction userTransaction;
	
	private TimelineModel model;

	private boolean selectable = true;
	private boolean zoomable = true;
	private boolean moveable = true;
	private boolean stackEvents = true;
	private String eventStyle = "box";
	private boolean axisOnTop;
	private boolean showCurrentTime = true;
	private boolean showNavigation = true;

	@PostConstruct
	public void initialize() {
		model = new TimelineModel();

		String q = "SELECT b from Worksheet b";
		Query query = entityManager.createQuery(q);
		List<Worksheet> workSheets = query.getResultList();
		for (Worksheet workSheet : workSheets) {
			model.add(new TimelineEvent(workSheet.getDescription(), workSheet.getStartDate()));
		}
	}

	public void onSelect(TimelineSelectEvent e) {
		TimelineEvent timelineEvent = e.getTimelineEvent();

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected event:",
				timelineEvent.getData().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onChange(TimelineModificationEvent e) throws Exception {
		TimelineEvent timelineEvent = e.getTimelineEvent();
		Query query = entityManager.createQuery("SELECT e FROM Worksheet e WHERE e.description = :desc");
		
		query.setParameter("desc", timelineEvent.getData());
		Worksheet worksheet = (Worksheet)query.getSingleResult();
		
		worksheet.setStartDate(timelineEvent.getStartDate());
		userTransaction.begin();
		entityManager.merge(worksheet);
		userTransaction.commit();
	}

	public TimelineModel getModel() {
		return model;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public boolean isZoomable() {
		return zoomable;
	}

	public void setZoomable(boolean zoomable) {
		this.zoomable = zoomable;
	}

	public boolean isMoveable() {
		return moveable;
	}

	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}

	public boolean isStackEvents() {
		return stackEvents;
	}

	public void setStackEvents(boolean stackEvents) {
		this.stackEvents = stackEvents;
	}

	public String getEventStyle() {
		return eventStyle;
	}

	public void setEventStyle(String eventStyle) {
		this.eventStyle = eventStyle;
	}

	public boolean isAxisOnTop() {
		return axisOnTop;
	}

	public void setAxisOnTop(boolean axisOnTop) {
		this.axisOnTop = axisOnTop;
	}

	public boolean isShowCurrentTime() {
		return showCurrentTime;
	}

	public void setShowCurrentTime(boolean showCurrentTime) {
		this.showCurrentTime = showCurrentTime;
	}

	public boolean isShowNavigation() {
		return showNavigation;
	}

	public void setShowNavigation(boolean showNavigation) {
		this.showNavigation = showNavigation;
	}
}