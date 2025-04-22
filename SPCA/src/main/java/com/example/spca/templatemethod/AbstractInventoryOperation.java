package com.example.spca.templatemethod;

import org.springframework.transaction.annotation.Transactional;

import com.example.spca.dao.BookDAO;
import com.example.spca.entities.Book;

public abstract class AbstractInventoryOperation {
	
	protected final BookDAO bDAO;
	
	protected AbstractInventoryOperation(BookDAO bDAO) {
		this.bDAO = bDAO;
	}
	
	@Transactional
	public final void perform(int bookId, int qty) {
		validate(bookId, qty);
		adjustStock(bookId, qty);
	}
	
	protected abstract void validate(int bookId, int qty);
	
	protected abstract void adjustStock(int bookId, int qty);
	
	protected Book fetch(int bookId) {
		return bDAO.findByID(bookId);
	}

}
