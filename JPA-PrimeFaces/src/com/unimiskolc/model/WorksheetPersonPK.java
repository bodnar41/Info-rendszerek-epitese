package com.unimiskolc.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the worksheet_person database table.
 * 
 */
@Embeddable
public class WorksheetPersonPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="WORKSHEET_ID", insertable=false, updatable=false)
	private int worksheetId;

	@Column(name="PERSON_ID", insertable=false, updatable=false)
	private int personId;

	public WorksheetPersonPK() {
	}
	public int getWorksheetId() {
		return this.worksheetId;
	}
	public void setWorksheetId(int worksheetId) {
		this.worksheetId = worksheetId;
	}
	public int getPersonId() {
		return this.personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof WorksheetPersonPK)) {
			return false;
		}
		WorksheetPersonPK castOther = (WorksheetPersonPK)other;
		return 
			(this.worksheetId == castOther.worksheetId)
			&& (this.personId == castOther.personId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.worksheetId;
		hash = hash * prime + this.personId;
		
		return hash;
	}
}