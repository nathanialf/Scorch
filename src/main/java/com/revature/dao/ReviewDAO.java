package com.revature.dao;

import com.revature.bean.Review;

public interface ReviewDAO {
	
	public int insertReview(Review r);
	public Review getReviewById(int id);
}
