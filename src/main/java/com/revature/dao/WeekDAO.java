package com.revature.dao;

import java.util.List;

import com.revature.bean.Week;

public interface WeekDAO {
	
	public Week getWeekById(int batchId);
	public void createBatch();
	public void setBatch(Week batch);
	public List<Week> getAllWeeks();
}
