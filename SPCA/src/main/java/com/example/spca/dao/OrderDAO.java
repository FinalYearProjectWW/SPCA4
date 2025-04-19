package com.example.spca.dao;

import com.example.spca.entities.Order;

public interface OrderDAO extends CommonDAO<Order, Integer>{
	Order findOrderByCustomerId(int customerId);
	void saveOrUpdate(Order order);
	Order findCartByCustomerId(int customerId);
}
