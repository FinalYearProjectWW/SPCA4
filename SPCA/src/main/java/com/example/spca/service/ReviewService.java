package com.example.spca.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

import com.example.spca.dao.ReviewDAO;
import com.example.spca.entities.Book;
import com.example.spca.entities.Customer;
import com.example.spca.entities.Review;
import com.example.spca.observer.ReviewObserver;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReviewService {
	
	private final ReviewDAO rDAO;
	private final BookService bs;
	private final CustomerService cs;
	
	private final List<ReviewObserver> observers = new CopyOnWriteArrayList<>();
	
	public ReviewService(ReviewDAO rDAO, BookService bs, CustomerService cs) {
		this.rDAO = rDAO;
		this.bs = bs;
		this.cs = cs;
	}
	
	public void registerObserver(ReviewObserver ro) {
		observers.add(ro);
	}
	
	public void unregisterObsever(ReviewObserver ro) {
		observers.remove(ro);
	}
	
	private void notifyReviewAdded(Review r) {
		for(ReviewObserver ro: observers) {
			ro.onReviewAdded(r);
		}
	}
	
	public void addReview(Review review) {
		Book book = bs.findBookById(review.getBook().getId());
		Customer customer = cs.findById(review.getCustomer().getId());
		review.setBook(book);
		review.setCustomer(customer);
		
		if(review.getReviewDate() == null) {
			review.setReviewDate(new Date());
		}
		rDAO.save(review);
		notifyReviewAdded(review);
	}
	
	public List<Review> findByBook(int bookId) {
		Book book = bs.findBookById(bookId);
		return rDAO.findByBook(book);
	}
	
	public Review updateReview(Review review) {
		return rDAO.update(review);
	}
	
	public void deleteReview(int reviewId) {
		Review r = rDAO.findByID(reviewId);
		if(r != null) {
			rDAO.delete(r);
		}
	}
	
	public Review findById(int id) {
		return rDAO.findByID(id);
	}
}