package com.example.spca.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.multipart.MultipartFile;

import com.example.spca.entities.Book;
import com.example.spca.observer.AverageRatingService;
import com.example.spca.service.BookService;


@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private final BookService bs;
	private final AverageRatingService ars;
	
	public BookController(BookService bs, AverageRatingService ars){
		this.bs = bs;
		this.ars = ars;
	}
	
	@GetMapping("/hello")
	public String helloWorld() {
	    return "Hello world!";
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Book>> getAllBooks(@RequestParam(defaultValue = "") String category, @RequestParam(defaultValue = "title")  String sort, @RequestParam(defaultValue = "true")  boolean ascending) {
	    List<Book> books = bs.findAllBooks();
	    if (!category.isEmpty()) {
	        books = books.stream().filter(b -> b.getCategory().equals(category)).collect(Collectors.toList());
	    }
	    bs.sortBooks(books, sort, ascending);

	    return ResponseEntity.ok(books);
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bs.findBookById(id);
        double avg = ars.getAverageRating(id);
        book.setAverageRating(avg);
        return ResponseEntity.ok(book);
    }
	
	@GetMapping("/search")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bs.searchBooksByTitle(title));
    }
	
	@PostMapping("/addBook")
    public ResponseEntity<Void> addBook(@RequestParam String title, @RequestParam String author, @RequestParam String publisher, @RequestParam double price, @RequestParam String category, @RequestParam("imageFile") MultipartFile file) throws IOException {
		Book book = new Book();
	    book.setTitle(title);
	    book.setAuthor(author);
	    book.setPrice(price);
	    book.setCategory(category);
	    book.setImage(file.getBytes());
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