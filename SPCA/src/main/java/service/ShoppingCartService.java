package service;

import java.util.Date;

import dao.OrderDAO;
import dao.OrderDAOImpl;
import entities.Book;
import entities.Customer;
import entities.Order;
import entities.OrderItem;

public class ShoppingCartService {
	
	private static ShoppingCartService instance;
	private final OrderDAO orderDAO;
	private final BookService bookService;
	private final CartInvoker ci;
	
	private ShoppingCartService() {
		this.orderDAO = new OrderDAOImpl();
		this.bookService = BookService.getInstance();
		this.ci = new CartInvoker();
	}
	
	public static synchronized ShoppingCartService getInstance() {
		if(instance == null) {
			instance = new ShoppingCartService();
		}
		return instance;
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
			Customer c = CustomerService.getInstance().findById(customerId);
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
		o.getOrderItems().add(oi);
		orderDAO.saveOrUpdate(o);
	}
	
	public void internalRemoveItem(int customerId, OrderItem oi) {
		Order o = getCartForCustomer(customerId);
        o.getOrderItems().removeIf(i -> i.getId() == oi.getId());
        orderDAO.saveOrUpdate(o);
	}

}
