package com.revature.dao;

import java.util.List;

import com.revature.bean.Batch;

public interface BatchDAO {
	
	public Batch getBatchById(int batchId);
	
	public int insertBatch(Batch batch);
	
	public void updateBatch(Batch batch);
	
	public List<Batch> getAllBatches();
	
	public void deleteBatch(Batch batch);
}