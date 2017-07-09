package com.revature.dao;

import java.util.List;

import com.revature.bean.Week;

public interface WeekDAO {
	public int insertWeek(Week week);
	public Week getWeekById(int id);
	public void updateWeek(Week week);
	public List<Week> getAllWeeks();
}
