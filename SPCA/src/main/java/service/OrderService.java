package service;

import java.util.Date;

import dao.OrderDAO;
import dao.OrderDAOImpl;
import entities.Order;

public class OrderService {
	
	private static OrderService instance;
	private final OrderDAO orderDAO;
	private final AbstractOrderProcessor aop = new OrderProcessor();
	
	private OrderService() {
		this.orderDAO = new OrderDAOImpl();
	}
	
	public static synchronized OrderService getInstance() {
		if(instance == null) {
			instance = new OrderService();
		}
		return instance;
	}
	
	public Order createOrder(Order order) {
		order.setOrderDate(new Date());
		orderDAO.saveOrUpdate(order);
		return order;
	}
	
	public Order updateOrder(Order order) {
		orderDAO.saveOrUpdate(order);
		return order;
	}
	
	public Order findOrderByCustomerId(int customerId) {
		return orderDAO.findOrderByCustomerId(customerId);
	}
	
	public void deleteOrder(Order order) {
		orderDAO.delete(order);
	}
	
	public void finaliseOrder(Order order) {
		aop.processOrder(order);
		orderDAO.saveOrUpdate(order);
	}
}