package com.example.spca.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spca.dao.OrderDAO;
import com.example.spca.entities.Customer;
import com.example.spca.entities.Order;
import com.example.spca.entities.OrderItem;
import com.example.spca.templatemethod.AbstractOrderProcessor;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {
	
	private final OrderDAO orderDAO;
	private final AbstractOrderProcessor aop;
	private final CustomerService cs;
	
	public OrderService(OrderDAO oDAO, AbstractOrderProcessor aop, CustomerService cs) {
		this.orderDAO = oDAO;
		this.aop = aop;
		this.cs = cs;
	}
	
	public Order createOrder(Order order) {
		int customerId = order.getCustomer().getId();
		Customer customer = cs.findById(customerId);
		order.setCustomer(customer);
		order.setOrderDate(new Date());
		
		double total = 0.0;
		for(OrderItem oi: order.getOrderItems()) {
			total += oi.getPrice() * oi.getQuantity();
			oi.setOrder(order);
		}
		order.setTotalAmount(total);
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
	
	public Order findOrderById(int id) {
	    return orderDAO.findByID(id);
	}
	
	public void deleteOrder(Order order) {
		orderDAO.delete(order);
	}
	
	public void finaliseOrder(Order order) {
		aop.processOrder(order);
		orderDAO.saveOrUpdate(order);
	}
	
	public List<Order> findAllOrdersByCustomerId(int customerId) {
		return orderDAO.findAllOrdersByCustomerId(customerId);
	}
}