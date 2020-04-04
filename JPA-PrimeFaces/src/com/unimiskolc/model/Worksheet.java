package com.unimiskolc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the worksheet database table.
 * 
 */
@Entity
@NamedQuery(name="Worksheet.findAll", query="SELECT w FROM Worksheet w")
public class Worksheet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DUE_DATE")
	private Date dueDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="END_DATE")
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="START_DATE")
	private Date startDate;

	//bi-directional many-to-many association to Person
	@ManyToMany
	@JoinTable(
		name="worksheet_person"
		, joinColumns={
			@JoinColumn(name="WORKSHEET_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="PERSON_ID")
			}
		)
	private List<Person> persons;

	//bi-directional many-to-one association to WorksheetPerson
	@OneToMany(mappedBy="worksheet")
	private List<WorksheetPerson> worksheetPersons;

	public Worksheet() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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

	public List<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<WorksheetPerson> getWorksheetPersons() {
		return this.worksheetPersons;
	}

	public void setWorksheetPersons(List<WorksheetPerson> worksheetPersons) {
		this.worksheetPersons = worksheetPersons;
	}

	public WorksheetPerson addWorksheetPerson(WorksheetPerson worksheetPerson) {
		getWorksheetPersons().add(worksheetPerson);
		worksheetPerson.setWorksheet(this);

		return worksheetPerson;
	}

	public WorksheetPerson removeWorksheetPerson(WorksheetPerson worksheetPerson) {
		getWorksheetPersons().remove(worksheetPerson);
		worksheetPerson.setWorksheet(null);

		return worksheetPerson;
	}

}