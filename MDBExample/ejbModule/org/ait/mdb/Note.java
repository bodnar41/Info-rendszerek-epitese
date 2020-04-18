package org.ait.mdb;

import java.io.Serializable;

public class Note implements Serializable {
	private int id;
	private String text;
	public Note() {
}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}