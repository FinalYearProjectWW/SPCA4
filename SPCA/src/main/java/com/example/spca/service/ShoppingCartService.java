package com.example.spca.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spca.commandpatternandfactorypattern.AddItemCommand;
import com.example.spca.commandpatternandfactorypattern.CartInvoker;
import com.example.spca.commandpatternandfactorypattern.RemoveItemCommand;
import com.example.spca.dao.OrderDAO;
import com.example.spca.entities.Book;
import com.example.spca.entities.Customer;
import com.example.spca.entities.Order;
import com.example.spca.entities.OrderItem;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShoppingCartService {
	
	private final OrderDAO orderDAO;
	private final BookService bookService;
	private final CustomerService cs;
	private final CartInvoker ci;
	
	@Autowired
	public ShoppingCartService(OrderDAO oDAO, BookService bs, CustomerService cs, CartInvoker ci) {
		this.orderDAO = oDAO;
		this.bookService = bs;
		this.cs = cs;
		this.ci = ci;
	}
	
	public void addItem(int customerId, int bookId, int quantity) {
		Book book = bookService.findBookById(bookId);
		OrderItem oi = new OrderItem();
		oi.setBook(book);
		oi.setQuantity(quantity);
		oi.setPrice(book.getPrice());
		
		ci.execute(new AddItemCommand(this, customerId, oi));
	}
	
	public void removeItem(int customerId, int bookId) {
		Order o = getCartForCustomer(customerId);
		o.getOrderItems().stream()
		.filter(i -> i.getId() == bookId)
		.findFirst()
		.ifPresent(item -> {
			ci.execute(new RemoveItemCommand(this, customerId, item));
		});
	}
	
	public void undoLastAction() {
		ci.undoLast();
	}
	
	public Order getCartForCustomer(int customerId) {
		Order o = orderDAO.findCartByCustomerId(customerId);
		if(o == null) {
			o = new Order();
			Customer c = cs.findById(customerId);
			o.setCustomer(c);
			o.setOrderDate(new Date());
			o.setStatus("IN_CART");
			orderDAO.saveOrUpdate(o);
		}
		return o;
	}
	
	public double calculateSubTotal(int customerId) {
		Order o = getCartForCustomer(customerId);
		return o.getOrderItems().stream()
				.mapToDouble(i -> i.getPrice() * i.getQuantity())
				.sum();
	}
	
	public void clearCart(int customerId) {
		Order o = getCartForCustomer(customerId);
		o.getOrderItems().clear();
		orderDAO.saveOrUpdate(o);
	}
	
	public void internalAddItem(int customerId, OrderItem oi) {
		Order o = getCartForCustomer(customerId);
		oi.setOrder(o);
		o.getOrderItems().add(oi);
		orderDAO.saveOrUpdate(o);
	}
	
	public void internalRemoveItem(int customerId, OrderItem oi) {
		Order o = getCartForCustomer(customerId);
        o.getOrderItems().removeIf(i -> i.getId() == oi.getId());
        orderDAO.saveOrUpdate(o);
	}
}