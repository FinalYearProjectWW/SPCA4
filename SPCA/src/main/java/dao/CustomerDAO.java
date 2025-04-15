package dao;

import java.util.List;

import entities.Customer;

public interface CustomerDAO extends CommonDAO<Customer, Integer>{
	List<Customer> findByUsername(String username);
	
	Customer findByEmailAndPassword(String email, String password);
}