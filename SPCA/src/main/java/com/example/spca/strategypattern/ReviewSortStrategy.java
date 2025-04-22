package com.example.spca.strategypattern;

import java.util.List;

import com.example.spca.entities.Review;

public interface ReviewSortStrategy {
	List<Review> sort(List<Review> reviews);
}