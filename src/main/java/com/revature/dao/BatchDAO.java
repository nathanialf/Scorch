package com.revature.dao;

import java.util.List;

import com.revature.bean.Batch;

public interface BatchDAO {
	
	public Batch getBatchById(int batchId);
	public void createBatch();
	public void setBatch(Batch batch);
	public List<Batch> getAllBatches();
	
}
