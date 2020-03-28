package org.ait.businesslogic;

import java.util.List;
import javax.ejb.Remote;
import org.ait.entities.Book;

@Remote
public interface BookBeanRemote {
	public void saveBook(Book book);

	public Book findBook(Book book);

	public List<Book> retrieveAllBooks();
}