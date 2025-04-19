package com.example.spca.dao;

import java.util.List;

import com.example.spca.entities.Customer;


public interface CustomerDAO extends CommonDAO<Customer, Integer>{
	List<Customer> findByUsername(String username);
	
	Customer findByEmailAndPassword(String email, String password);
}