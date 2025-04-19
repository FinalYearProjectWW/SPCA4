package com.example.spca.dao;

import java.util.List;

import com.example.spca.entities.Book;
import com.example.spca.entities.Review;


public interface ReviewDAO extends CommonDAO<Review, Integer> {
	List<Review> findByBook(Book book);
}