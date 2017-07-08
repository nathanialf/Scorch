package com.revature.dao;

import java.sql.Date;
import java.util.List;

import com.revature.bean.Batch;
import com.revature.bean.User;
import com.revature.bean.Week;

public interface BatchDAO {
	
	public Batch getBatchById(int batchId);
	public void createBatch(User trainer, 
			String batchName,
			Date batchStartDate,
			List<Week> weeks,
			List<User> associates);
	
	public void setBatch(Batch batch);
	
	public List<Batch> getAllBatches();
	
}
