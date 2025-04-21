package com.example.spca.observer;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Service;
import com.example.spca.entities.Review;
import com.example.spca.service.ReviewService;

@Service
public class AverageRatingService implements ReviewObserver{
	
	private final ReviewService rs;
	private final ConcurrentMap<Integer, Double> avgCache = new ConcurrentHashMap<>();
	
	public AverageRatingService(ReviewService rs) {
        this.rs = rs;
        rs.registerObserver(this);
    }

	@Override
	public void onReviewAdded(Review newReview) {
		// TODO Auto-generated method stub
		int bookId = newReview.getBook().getId();
		List<Review> all = rs.findByBook(bookId);
		double avg = all.stream().mapToInt(Review::getRating).average().orElse(0.0);
		avgCache.put(bookId, avg);
	}
	
	public double getAverageRating(int bookId) {
		return avgCache.getOrDefault(bookId, 0.0);
	}
}