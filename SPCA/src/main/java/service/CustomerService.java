package service;

import java.util.List;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import entities.Customer;

public class CustomerService {
	
	private static CustomerService instance;
	
	private final CustomerDAO customerDAO;
	
	private CustomerService() {
		this.customerDAO = new CustomerDAOImpl();
	}
	
	public static synchronized CustomerService getInstance() {
		if(instance == null) {
			instance = new CustomerService();
		}
		return instance;
	}
	
	public void registerCustomer(Customer customer) {
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
}