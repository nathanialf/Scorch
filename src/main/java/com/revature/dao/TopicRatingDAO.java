package com.revature.dao;

import java.util.List;

import com.revature.bean.TopicRating;

public interface TopicRatingDAO {
	
	public int insertTopicRating(TopicRating t);
	public TopicRating getTopicRatingById(int id);
	public List<TopicRating> getAllTopicRatings();
	public void updateTopicRating(TopicRating t);
	public void deleteTopicRating(TopicRating t);
}
