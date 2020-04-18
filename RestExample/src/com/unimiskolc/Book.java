package com.unimiskolc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Book {
    @XmlID
    private String id;
    
    @XmlAttribute
    private String title;

    @XmlAttribute
    private String author; 
    
    public Book()  {
    	
    }
    
	public Book(String id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}