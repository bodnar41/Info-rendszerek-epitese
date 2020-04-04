package com.unimiskolc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the worksheet_person database table.
 * 
 */
@Entity
@Table(name="worksheet_person")
@NamedQuery(name="WorksheetPerson.findAll", query="SELECT w FROM WorksheetPerson w")
public class WorksheetPerson implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WorksheetPersonPK id;

	//bi-directional many-to-one association to Workhour
	@OneToMany(mappedBy="worksheetPerson")
	private List<Workhour> workhours;

	//bi-directional many-to-one association to Worksheet
	@ManyToOne
	private Worksheet worksheet;

	//bi-directional many-to-one association to Person
	@ManyToOne
	private Person person;

	public WorksheetPerson() {
	}

	public WorksheetPersonPK getId() {
		return this.id;
	}

	public void setId(WorksheetPersonPK id) {
		this.id = id;
	}

	public List<Workhour> getWorkhours() {
		return this.workhours;
	}

	public void setWorkhours(List<Workhour> workhours) {
		this.workhours = workhours;
	}

	public Workhour addWorkhour(Workhour workhour) {
		getWorkhours().add(workhour);
		workhour.setWorksheetPerson(this);

		return workhour;
	}

	public Workhour removeWorkhour(Workhour workhour) {
		getWorkhours().remove(workhour);
		workhour.setWorksheetPerson(null);

		return workhour;
	}

	public Worksheet getWorksheet() {
		return this.worksheet;
	}

	public void setWorksheet(Worksheet worksheet) {
		this.worksheet = worksheet;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}