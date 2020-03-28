package org.ait.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "book")
public class Book implements Serializable {
	@Id
	private Long isbn;
	private String title;
	private String author;
	private String publisher;
	private Date releaseyear;
	private String format;

	public Book() {
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long long1) {
		this.isbn = long1;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getReleaseyear() {
		return releaseyear;
	}

	public void setReleaseyear(Date releaseyear) {
		this.releaseyear = releaseyear;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", releaseyeare=" + releaseyear + ", format=" + format + "]";
	}
}