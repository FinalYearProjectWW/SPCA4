package com.example.spca.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spca.commandpatternandfactorypattern.InventoryCommand;
import com.example.spca.commandpatternandfactorypattern.InventoryCommandFactory;
import com.example.spca.dao.BookDAO;
import com.example.spca.entities.Book;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	private final BookDAO bDAO;
	private final InventoryCommandFactory icf;
	
	public InventoryController(BookDAO bDAO, InventoryCommandFactory icf) {
		this.bDAO = bDAO;
		this.icf = icf;
	}
	
	@GetMapping
	public List<Book> listAll() {
	    return bDAO.findAll();
	}
	
	@GetMapping("/{bookId}/stock")
	public ResponseEntity<Integer> stockLevel(@PathVariable int bookId) {
	    Book book = bDAO.findByID(bookId);
	    if (book == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok(book.getStockLevel());
	}

	
	@PostMapping("/{bookId}/restock")
	public ResponseEntity<Void> action(@PathVariable int bookId, @RequestParam int qty) {
		InventoryCommand cmd = icf.get("restockCmd");
		cmd.execute(bookId, qty);
		return ResponseEntity.noContent().build();
	}
}