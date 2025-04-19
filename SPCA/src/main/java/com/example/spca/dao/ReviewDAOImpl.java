package com.example.spca.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.spca.entities.Book;
import com.example.spca.entities.Review;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ReviewDAOImpl extends CommonDAOImpl<Review, Integer> implements ReviewDAO {

	public ReviewDAOImpl() {
        super(Review.class);
    }

	@Override
	public List<Review> findByBook(Book book) {
		// TODO Auto-generated method stub
		TypedQuery<Review> query = em.createQuery("SELECT r FROM Review r WHERE r.book = :book", Review.class);
		query.setParameter("book", book);
		return query.getResultList();
	}
}