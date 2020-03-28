package org.ait.businesslogic;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.ait.entities.Book;

@Stateless
public class BookBean implements BookBeanRemote {
	@PersistenceContext(unitName = "jpaexampledb")
	private EntityManager entityManager;

	public BookBean() {
	}

	@Override
	public void saveBook(Book book) {
		entityManager.persist(book);
	}

	@Override
	public Book findBook(Book book) {
		Book b = entityManager.find(Book.class, book.getIsbn());
		return b;
	}

	@Override
	public List<Book> retrieveAllBooks() {
		String q = "SELECT b from" + Book.class.getName() + " p";
		Query query = entityManager.createQuery(q);
		List<Book> books = query.getResultList();
		return books;
	}
}