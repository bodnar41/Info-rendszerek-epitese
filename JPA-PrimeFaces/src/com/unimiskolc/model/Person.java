package com.unimiskolc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="DISPLAY_COLOR")
	private String displayColor;

	private String name;

	//bi-directional many-to-many association to Worksheet
	@ManyToMany(mappedBy="persons")
	private List<Worksheet> worksheets;

	//bi-directional many-to-one association to WorksheetPerson
	@OneToMany(mappedBy="person")
	private List<WorksheetPerson> worksheetPersons;

	public Person() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDisplayColor() {
		return this.displayColor;
	}

	public void setDisplayColor(String displayColor) {
		this.displayColor = displayColor;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Worksheet> getWorksheets() {
		return this.worksheets;
	}

	public void setWorksheets(List<Worksheet> worksheets) {
		this.worksheets = worksheets;
	}

	public List<WorksheetPerson> getWorksheetPersons() {
		return this.worksheetPersons;
	}

	public void setWorksheetPersons(List<WorksheetPerson> worksheetPersons) {
		this.worksheetPersons = worksheetPersons;
	}

	public WorksheetPerson addWorksheetPerson(WorksheetPerson worksheetPerson) {
		getWorksheetPersons().add(worksheetPerson);
		worksheetPerson.setPerson(this);

		return worksheetPerson;
	}

	public WorksheetPerson removeWorksheetPerson(WorksheetPerson worksheetPerson) {
		getWorksheetPersons().remove(worksheetPerson);
		worksheetPerson.setPerson(null);

		return worksheetPerson;
	}

}