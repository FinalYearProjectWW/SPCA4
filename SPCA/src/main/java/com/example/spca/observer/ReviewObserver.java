package com.example.spca.observer;

import com.example.spca.entities.Review;

public interface ReviewObserver {
	void onReviewAdded(Review newReview);
}
