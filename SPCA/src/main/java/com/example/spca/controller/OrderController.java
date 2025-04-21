package com.example.spca.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spca.entities.Order;
import com.example.spca.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	private final OrderService os;
	
	public OrderController(OrderService os) {
		this.os = os;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Order> createOrder(@RequestBody Order o) {
		return ResponseEntity.ok(os.createOrder(o));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody Order o) {
		o.setId(id);
		return ResponseEntity.ok(os.updateOrder(o));
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Order> getOrderByCustomerId(@PathVariable int customerId) {
			Order o = os.findOrderByCustomerId(customerId);
			return o != null ? ResponseEntity.ok(o) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/customer/{customerId}/all")
	public ResponseEntity<List<Order>> getAllOrdersByCustomerId(@PathVariable int customerId) {
		List<Order> orders = os.findAllOrdersByCustomerId(customerId);
		return orders.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(orders);
	}
	
	@PostMapping("/{id}/finalise")
	public ResponseEntity<Void> finaliseOrder(@PathVariable int id) {
		Order o = os.findOrderById(id);
		if(o != null) {
			os.finaliseOrder(o);
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
		Order o = os.findOrderById(id);
		if(o != null) {
			os.deleteOrder(o);
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}

}
