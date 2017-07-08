package com.revature.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.Batch;
import com.revature.bean.User;
import com.revature.bean.Week;
import com.revature.util.HibernateUtil;

public class BatchDAOImpl implements BatchDAO {

	@Override
	public Batch getBatchById(int batchId) {
		Session session = HibernateUtil.getSession();
		
		Batch batch = (Batch)session.get(Batch.class, batchId);
		
		session.close();
		return batch;
	}

	@Override
	public void createBatch(User trainer, 
							String batchName,
							Date batchStartDate,
							List<Week> weeks,
							List<User> associates) {
		//id, User trainer, string batchName, Date batchStartDate, List<Week> weeks, List<User> associates
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
				
		Batch batch = new Batch(trainer, batchName, batchStartDate, weeks, associates);
		
		session.save(batch);
		
		tx.commit();
		
		session.close();
	}

	@Override
	public void setBatch(Batch batch) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		//Check if batch already exists
		if(session.contains(batch)){
			//Update batch with new changes...
			session.save(batch);
		}
		
		tx.commit();
		
		session.close();
	}

	@Override
	public List<Batch> getAllBatches() {
		Session session = HibernateUtil.getSession();
		
		List<Batch> batches = session.createCriteria(Batch.class).list();
		
		session.close();
		return batches;
	}

}
