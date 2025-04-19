package com.example.spca.service;

import java.util.Date;

import com.example.spca.dao.OrderDAO;
import com.example.spca.entities.Order;
import com.example.spca.templatemethod.AbstractOrderProcessor;


public class OrderService {
	
	private final OrderDAO orderDAO;
	private final AbstractOrderProcessor aop;
	
	private OrderService(OrderDAO oDAO, AbstractOrderProcessor aop) {
		this.orderDAO = oDAO;
		this.aop = aop;
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