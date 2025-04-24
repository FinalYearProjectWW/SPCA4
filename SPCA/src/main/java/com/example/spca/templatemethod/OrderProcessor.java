package com.example.spca.templatemethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.spca.entities.Book;
import com.example.spca.entities.Order;
import com.example.spca.entities.OrderItem;
import com.example.spca.service.BookService;

@Component
public class OrderProcessor extends AbstractOrderProcessor{
	
	BookService bookService;
	
	@Autowired
	public OrderProcessor(BookService bs) {
		this.bookService = bs;
	}

	@Override
	protected void validateOrder(Order order) {
		// TODO Auto-generated method stub
		if(order == null || order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
			throw new IllegalArgumentException("Order cannot be empty");
		}
		
	}

	@Override
	protected void updateStock(Order order) {
		// TODO Auto-generated method stub
		for(OrderItem items: order.getOrderItems()) {
			Book book = items.getBook();
			book.setStockLevel(book.getStockLevel() - items.getQuantity());
			bookService.updateBook(book);
		}
	}
}
