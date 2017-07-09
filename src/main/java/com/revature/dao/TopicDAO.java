package com.revature.dao;

import java.util.List;

import com.revature.bean.Topic;

public interface TopicDAO {
	public int insertTopic(Topic t);
	public Topic getTopicById(int id);
	public List<Topic> getAllTopics();
	public void updateTopic(Topic t);
	public void deleteUser(Topic t);
}
