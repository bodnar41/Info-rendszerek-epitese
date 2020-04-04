package com.unimiskolc.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the workhours database table.
 * 
 */
@Embeddable
public class WorkhourPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int id;

	@Column(name="WORKSHEET_PERSON_WORKSHEET_ID", insertable=false, updatable=false)
	private int worksheetPersonWorksheetId;

	@Column(name="WORKSHEET_PERSON_PERSON_ID", insertable=false, updatable=false)
	private int worksheetPersonPersonId;

	public WorkhourPK() {
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWorksheetPersonWorksheetId() {
		return this.worksheetPersonWorksheetId;
	}
	public void setWorksheetPersonWorksheetId(int worksheetPersonWorksheetId) {
		this.worksheetPersonWorksheetId = worksheetPersonWorksheetId;
	}
	public int getWorksheetPersonPersonId() {
		return this.worksheetPersonPersonId;
	}
	public void setWorksheetPersonPersonId(int worksheetPersonPersonId) {
		this.worksheetPersonPersonId = worksheetPersonPersonId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof WorkhourPK)) {
			return false;
		}
		WorkhourPK castOther = (WorkhourPK)other;
		return 
			(this.id == castOther.id)
			&& (this.worksheetPersonWorksheetId == castOther.worksheetPersonWorksheetId)
			&& (this.worksheetPersonPersonId == castOther.worksheetPersonPersonId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.worksheetPersonWorksheetId;
		hash = hash * prime + this.worksheetPersonPersonId;
		
		return hash;
	}
}