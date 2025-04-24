package com.example.spca.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spca.entities.Order;
import com.example.spca.service.ShoppingCartService;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {
	
	private final ShoppingCartService scs;
	
	public ShoppingCartController(ShoppingCartService scs) {
		this.scs = scs;
	}
	
	@PostMapping("/{customerId}/add")
	public ResponseEntity<Void> addItem(@PathVariable int customerId, @RequestParam int bookId, @RequestParam int quantity) {
		scs.addItem(customerId, bookId, quantity);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{customerId}/remove")
	public ResponseEntity<Void> removeItem(@PathVariable int customerId, @RequestParam int bookId) {
		scs.removeItem(customerId, bookId);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/undo")
	public ResponseEntity<Void> undoLastAction() {
		scs.undoLastAction();
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{customerId}")
    public ResponseEntity<Order> getCart(@PathVariable int customerId) {
        return ResponseEntity.ok(scs.getCartForCustomer(customerId));
    }
	
	@GetMapping("/{customerId}/subtotal")
    public ResponseEntity<Double> calculateSubtotal(@PathVariable int customerId) {
        return ResponseEntity.ok(scs.calculateSubTotal(customerId));
    }
	
	@PostMapping("/{customerId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable int customerId) {
        scs.clearCart(customerId);
        return ResponseEntity.ok().build();
	}
}