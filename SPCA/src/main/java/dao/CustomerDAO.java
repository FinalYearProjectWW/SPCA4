package dao;

import java.util.List;

import SPCA.SPCA.Customer;

public interface CustomerDAO extends CommonDAO<Customer, Integer>{
	List<Customer> findByUsername(String username);
}