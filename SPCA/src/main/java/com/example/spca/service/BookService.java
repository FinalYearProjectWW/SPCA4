package com.example.spca.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.spca.dao.BookDAO;
import com.example.spca.entities.Book;
import com.example.spca.strategypattern.SortByAuthorStrategy;
import com.example.spca.strategypattern.SortByPriceStrategy;
import com.example.spca.strategypattern.SortByTitleStrategy;
import com.example.spca.strategypattern.SortingStrategy;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookService {
		
	private final BookDAO bookDAO;
	private final Map<String, SortingStrategy> strategies;
	
	public BookService(BookDAO bDAO, Map<String, SortingStrategy> strategies) {
		this.strategies = strategies;
		this.bookDAO = bDAO;
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
		if(book.getStockLevel() == null) {
			book.setStockLevel(100);
		}
	    bookDAO.save(book);
	}
	
	public Book updateBook(Book book) {
		return bookDAO.update(book);
	}
	
	public void deleteBook(Book book) {
		bookDAO.delete(book);
	}
	
	public void sortBooks(List<Book> books, String sort, boolean ascending) {
		SortingStrategy strat = strategies.getOrDefault(sort.toLowerCase(), strategies.get("title"));
		strat.sort(books, ascending);
	}
}