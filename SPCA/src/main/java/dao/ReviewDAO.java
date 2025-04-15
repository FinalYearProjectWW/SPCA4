package dao;

import java.util.List;

import entities.Book;
import entities.Review;

public interface ReviewDAO extends CommonDAO<Review, Integer> {
	List<Review> findByBook(Book book);
}