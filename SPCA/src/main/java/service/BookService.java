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
	
	public Book findBookById(int id) {
		return bookDAO.findByID(id);
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
	
	public void sortBooks(List<Book> books, String sort, boolean ascending) {
		SortingStrategy ss;
		
		switch(sort.toLowerCase()) {
		case "title":
			ss = new SortByTitleStrategy();
			break;
		case "price":
			ss = new SortByPriceStrategy();
			break;
		default:
			ss = new SortByTitleStrategy();
			break;
		}
		ss.sort(books, ascending);
	}
}