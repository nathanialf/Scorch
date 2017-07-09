package com.revature.dao;

import java.util.List;

import com.revature.bean.Review;

public interface ReviewDAO {
	
	public int insertReview(Review r);
	public Review getReviewById(int id);
	public List<Review> getAllReviews();
	public void updateReview(Review r);
	public void deleteReview(Review r);
}
