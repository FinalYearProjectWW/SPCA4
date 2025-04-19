package com.example.spca.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.spca.dao.CustomerDAO;
import com.example.spca.entities.Customer;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {
		
	private final CustomerDAO customerDAO;
	
	public CustomerService(CustomerDAO cDAO) {
		this.customerDAO = cDAO;
	}
	
	public void registerCustomer(String username, String password, String shippingAddress, String paymentMethod) {
		Customer customer = new Customer.Builder(username, password)
				.shippingAddress(shippingAddress)
				.paymentMethod(paymentMethod)
				.build();
		customerDAO.save(customer);
	}
	
	public Customer authenticate(String email, String password) {
		return customerDAO.findByEmailAndPassword(email, password);
	}
	
	public Customer updateCustomer(Customer customer) {
		return customerDAO.update(customer);
	}
	
	public void deleteCustomer(Customer customer) {
		customerDAO.delete(customer);
	}
	
	public List<Customer> findAllCustomers() {
		return customerDAO.findAll();
	}
	
	public Customer findById(int id) {
		return customerDAO.findByID(id);
	}
}