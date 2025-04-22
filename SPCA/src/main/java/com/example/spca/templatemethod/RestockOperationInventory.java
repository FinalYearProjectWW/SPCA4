package com.example.spca.templatemethod;

import org.springframework.stereotype.Component;

import com.example.spca.dao.BookDAO;
import com.example.spca.entities.Book;

@Component("restockOp")
public class RestockOperationInventory extends AbstractInventoryOperation{

	protected RestockOperationInventory(BookDAO bDAO) {
		super(bDAO);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void validate(int bookId, int qty) {
		// TODO Auto-generated method stub
		if(qty<=0) throw new IllegalArgumentException("Qty must be >0");
		fetch(bookId); 
	}

	@Override
	protected void adjustStock(int bookId, int qty) {
		// TODO Auto-generated method stub
		Book b = fetch(bookId);
		b.setStockLevel(b.getStockLevel() + qty);
		bDAO.save(b);
	}
}