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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spca.entities.Book;
import com.example.spca.service.BookService;


@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private final BookService bs;
	
	public BookController(BookService bs) {
		this.bs = bs;
	}
	
	@GetMapping("/hello")
	public String helloWorld() {
	    return "Hello world!";
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Book>> getAllBooks(@RequestParam(defaultValue = "title") String sort, @RequestParam(defaultValue = "true") boolean ascending) {
		List<Book> books = bs.findAllBooks();
		bs.sortBooks(books, sort, ascending);
		return ResponseEntity.ok(books);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        return ResponseEntity.ok(bs.findBookById(id));
    }
	
	@GetMapping("/search")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bs.searchBooksByTitle(title));
    }
	
	@PostMapping("/addBook")
    public ResponseEntity<Void> addBook(@RequestBody Book book) {
        bs.addBook(book);
        return ResponseEntity.ok().build();
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
	    book.setId(id);
	    return ResponseEntity.ok(bs.updateBook(book));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable int id) {
		Book book = bs.findBookById(id);
		if(book != null) {
			bs.deleteBook(book);
			return ResponseEntity.ok().build();
		}else {
	        return ResponseEntity.notFound().build();
		}
	}
}