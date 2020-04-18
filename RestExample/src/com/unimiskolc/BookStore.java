package com.unimiskolc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/")
@Consumes({ "application/xml", "application/json" })
@Produces({ "application/xml", "application/json" })
public class BookStore {
	List<Book> books;
	
	public BookStore() {
		books = new ArrayList<Book>();
		books.add(new Book("1", "Gárdonyi Géza", "Egri Csillagok"));
		books.add(new Book("2", "Kiss Elek", "Az én könyvem"));		
	}
	
	@GET
	@Path("books")
	public Collection<Book> getBooks(){
		return books;
		
	}
	@POST
	@Path("books")
	public void addBook(Book book){
		books.add(book);
	}

	@GET
	@Path("book/{id}")
	public Book getBook(@PathParam("id") String id){
		for (Book book : books) {
			if(book.getId().equalsIgnoreCase(id)) {
				return book;
			}
		}
		return null;
	}

	@PUT
	@Path("book/{id}")
	public void updateBook(@PathParam("id") String id, Book book){
		
	}

	@DELETE
	@Path("book/{id}")
	public void deleteBook(@PathParam("id") String id){
		
	}

}