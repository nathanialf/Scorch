package com.revature.dao;

import java.util.List;

import com.revature.bean.ReviewType;

public interface ReviewTypeDAO {

	public int insertReviewType(ReviewType t);
	public ReviewType getReviewTypeById(int id);
	public List<ReviewType> getAllReviewTypes();
	public void updateReviewType(ReviewType t);
	public void deleteReviewType(ReviewType t);
}
