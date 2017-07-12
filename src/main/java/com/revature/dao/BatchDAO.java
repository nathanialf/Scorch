package com.revature.dao;

import java.util.List;

import com.revature.bean.Batch;
import com.revature.bean.User;

public interface BatchDAO {
	
	public Batch getBatchById(int batchId);
	
	public int insertBatch(Batch batch);
	
	public void updateBatch(Batch batch);
	
	public List<Batch> getAllBatches();
	
	public void deleteBatch(Batch batch);
	
	public Batch selectBatchByUser(User u);
	
	//public Batch selectBatchByTrainer(User u);
}