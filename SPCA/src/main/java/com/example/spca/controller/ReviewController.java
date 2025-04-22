package com.example.spca.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spca.entities.Book;
import com.example.spca.entities.Review;
import com.example.spca.service.ReviewService;
import com.example.spca.strategypattern.ReviewSortStrategy;

@RestController
@RequestMapping("/api/books/{bookId}/reviews")
public class ReviewController {
	
	private final ReviewService rs;
	private final Map<String, ReviewSortStrategy> strategies;
	
	public ReviewController(ReviewService rs, Map<String, ReviewSortStrategy> strategies) {
		this.rs = rs;
		this.strategies = strategies;
	}
	
	@PostMapping
	public ResponseEntity<Void> addReview(@PathVariable int bookId, @RequestBody Review reviewPayload) {
		
		reviewPayload.setBook(new Book());
		reviewPayload.getBook().setId(bookId);
		rs.addReview(reviewPayload);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping
	public ResponseEntity<List<Review>> getReviews(@PathVariable int bookId, @RequestParam(defaultValue="recent") String sortBy) {
		List<Review> reviews = rs.findByBook(bookId);
		ReviewSortStrategy strat = strategies.getOrDefault(sortBy.toLowerCase(), strategies.get("recent"));
		return ResponseEntity.ok(strat.sort(reviews));
	}
	
	@DeleteMapping("/{reviewId}")
	public ResponseEntity<Void> delete(@PathVariable int reviewId) {
		rs.deleteReview(reviewId);
		return ResponseEntity.noContent().build();
	}
}