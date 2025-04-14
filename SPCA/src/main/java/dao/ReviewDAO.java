package dao;

import java.util.List;

import SPCA.SPCA.Book;
import SPCA.SPCA.Review;

public interface ReviewDAO extends CommonDAO<Review, Integer> {
	List<Review> findByBook(Book book);
}