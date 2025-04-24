package com.example.spca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spca.entities.Customer;
import com.example.spca.service.CustomerService;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	private final CustomerService cs;
	
	public CustomerController(CustomerService cs) {
		this.cs = cs;
	}
	
	@GetMapping("/hello")
	public String test() {
	    return "It works";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
		return ResponseEntity.of(Optional.ofNullable(cs.findById(id)));
	}
	
	@GetMapping("/listAll")
	public List<Customer> listAll() {
		return cs.findAllCustomers();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable int id) {
		Customer c = cs.findById(id);
		if(c != null) {
			cs.updateCustomer(c);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
		Customer c = cs.findById(id);
		if(c != null) {
			cs.deleteCustomer(c);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}