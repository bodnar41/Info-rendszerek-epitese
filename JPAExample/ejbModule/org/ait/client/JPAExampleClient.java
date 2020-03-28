package org.ait.client;

import java.sql.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingException;
import org.ait.businesslogic.BookBean;
import org.ait.businesslogic.BookBeanRemote;
import org.ait.cientutility.JNDILookupClass;
import org.ait.entities.Book;

public class JPAExampleClient {
	public static void main(String[] args) {
		BookBeanRemote bean = doLookup();
		Book b1 = new Book();
		b1.setIsbn(new java.lang.Long("9789631424607"));
		b1.setTitle("A Pendragon legenda");
		b1.setAuthor("Szerb Antal");
		b1.setPublisher("Magvetõ Könyvkiadó");
		b1.setReleaseyear(new Date(2007, 1, 1));
		b1.setFormat("papír/puha kötés");
		Book b2 = new Book();
		b2.setIsbn(new java.lang.Long("9789631194074"));
		b2.setTitle("A kis herceg");
		b2.setAuthor("Antoinde de Saint-Exupéry");
		b2.setPublisher("Móra Kiadó");
		b2.setReleaseyear(new Date(2013, 1, 1));
		b2.setFormat("kemény kötés");
		bean.saveBook(b1);
		bean.saveBook(b2);
		System.out.println("List of books:");
		List<Book> books = bean.retrieveAllBooks();
		for (Book book : books) {
			System.out.println(book);
		}
	}

	private static BookBeanRemote doLookup() { Context context = null; BookBeanRemote bean = null; try { context = JNDILookupClass.getInitialContext(); String lookupName = getLookupName(); bean = (BookBeanRemote) context.lookup(lookupName); } catch (NamingException e) { e.printStackTrace(); } return bean; }

private static String getLookupName() { String appName = ""; String moduleName = "JPAExample"; String distinctName = ""; String beanName = BookBean.class.getName(); final String interfaceName = BookBeanRemote.class.getName();
String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + interfaceName; return name; } }
