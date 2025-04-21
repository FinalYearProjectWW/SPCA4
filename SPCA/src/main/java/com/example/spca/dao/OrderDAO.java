package com.example.spca.dao;

import java.util.List;

import com.example.spca.entities.Order;

public interface OrderDAO extends CommonDAO<Order, Integer>{
	Order findOrderByCustomerId(int customerId);
	void saveOrUpdate(Order order);
	Order findCartByCustomerId(int customerId);
	List<Order> findAllOrdersByCustomerId(int customerId);
}
