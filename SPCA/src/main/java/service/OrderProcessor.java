package service;

import entities.Book;
import entities.Order;
import entities.OrderItem;

public class OrderProcessor extends AbstractOrderProcessor{
	
	BookService bookService;
	
	public OrderProcessor() {
		this.bookService = BookService.getInstance();
	}

	@Override
	protected void validateOrder(Order order) {
		// TODO Auto-generated method stub
		if(order == null || order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
			throw new IllegalArgumentException("Order cannot be empty");
		}
		
	}

	@Override
	protected boolean processPayment(Order order) {
		// TODO Auto-generated method stub
		if(order.getCustomer() != null && order.getCustomer().getPaymentMethod() != null && !order.getCustomer().getPaymentMethod().isEmpty()) {
			System.out.print("Payment processed for order " + order.getId() + " with method " + order.getCustomer().getPaymentMethod());
		}
		return true;
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
