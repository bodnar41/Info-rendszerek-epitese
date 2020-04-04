package com.unimiskolc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the workhours database table.
 * 
 */
@Entity
@Table(name="workhours")
@NamedQuery(name="Workhour.findAll", query="SELECT w FROM Workhour w")
public class Workhour implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WorkhourPK id;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="END_DATE")
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="START_DATE")
	private Date startDate;

	//bi-directional many-to-one association to WorksheetPerson
	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="WORKSHEET_PERSON_PERSON_ID", referencedColumnName="PERSON_ID", insertable=false, updatable=false),
		@JoinColumn(name="WORKSHEET_PERSON_WORKSHEET_ID", referencedColumnName="WORKSHEET_ID", insertable=false, updatable=false)
		})
	private WorksheetPerson worksheetPerson;

	public Workhour() {
	}

	public WorkhourPK getId() {
		return this.id;
	}

	public void setId(WorkhourPK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public WorksheetPerson getWorksheetPerson() {
		return this.worksheetPerson;
	}

	public void setWorksheetPerson(WorksheetPerson worksheetPerson) {
		this.worksheetPerson = worksheetPerson;
	}

}