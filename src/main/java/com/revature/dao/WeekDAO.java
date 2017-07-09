package com.revature.dao;

import java.util.List;

import com.revature.bean.Week;

public interface WeekDAO {
	
	public Week getWeekById(int id);
	public void createWeek();
	public void updateWeek(Week week);
	public List<Week> getAllWeeks();
}
