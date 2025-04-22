package com.example.spca.templatemethod;

import org.springframework.stereotype.Component;

import com.example.spca.dao.BookDAO;
import com.example.spca.entities.Book;

@Component("purchaseOp")
public class PurchaseOperationInventory extends AbstractInventoryOperation{

	protected PurchaseOperationInventory(BookDAO bDAO) {
		super(bDAO);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void validate(int bookId, int qty) {
		if(qty <= 0) throw new IllegalArgumentException("Quantity must be greater than 0");
		Book b = fetch(bookId);
		if(b.getStockLevel() < qty) throw new OutOfStockException(bookId, qty);
	}
	
	@Override
	protected void adjustStock(int bookId, int qty) {
		Book b = fetch(bookId);
		b.setStockLevel(b.getStockLevel() - qty);
		bDAO.save(b);
	}
}