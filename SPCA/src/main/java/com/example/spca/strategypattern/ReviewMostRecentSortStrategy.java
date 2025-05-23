package com.example.spca.strategypattern;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.spca.entities.Review;

@Component("recent")
public class ReviewMostRecentSortStrategy implements ReviewSortStrategy{

	@Override
	public List<Review> sort(List<Review> reviews) {
		// TODO Auto-generated method stub
		return reviews.stream().sorted(Comparator.comparing(Review::getReviewDate)
				.reversed()).collect(Collectors.toList());
	}
}
