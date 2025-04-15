package service;

import java.util.List;
import dao.BookDAO;
import dao.BookDAOImpl;
import entities.Book;

public class BookService {
	
	private static BookService instance; 
	
	private final BookDAO bookDAO;
	
	private BookService() {
		this.bookDAO = new BookDAOImpl();
	}
	
	public static synchronized BookService getInstance() {
		if(instance == null) {
			instance = new BookService();
		}
		return instance;
	}
	
	public List<Book> searchBooksByTitle(String title) {
		return bookDAO.searchByTitle(title);
	}
	
	public List<Book> findAllBooks() {
		return bookDAO.findAll();
	}
	
	public void addBook(Book book) {
	    bookDAO.save(book);
	}
	
	public Book updateBook(Book book) {
		return bookDAO.update(book);
	}
	
	public void deleteBook(Book book) {
		bookDAO.delete(book);
	}
}