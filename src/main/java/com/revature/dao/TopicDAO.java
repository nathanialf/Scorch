package com.revature.dao;

import java.util.List;

import com.revature.bean.Topic;
import com.revature.bean.Week;

public interface TopicDAO {
	public int insertTopic(Topic t);
	public Topic getTopicById(int id);
	public List<Topic> getAllTopics();
	public List<Topic> getAllTopicsByWeek(Week w);
	public void updateTopic(Topic t);
	public void deleteTopic(Topic t);
}
