package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.Batch;
import com.revature.util.HibernateUtil;

public class BatchDAOImpl implements BatchDAO {

	@Override
	public Batch getBatchById(int batchId) {
		Session session = HibernateUtil.getSession();
		Batch batch = null;
		try {
			batch = (Batch) session.get(Batch.class, batchId);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return batch;
	}

	@Override
	public int insertBatch(Batch batch) {
		// id, User trainer, string batchName, Date batchStartDate, List<Week>
		// weeks, List<User> associates

		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		int batchId = 0;

		try {
			tx = session.beginTransaction();
			batchId = (Integer) session.save(batch);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return batchId;
	}

	@Override
	public void updateBatch(Batch batch) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			// Update batch with new changes...
			session.update(batch);
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public List<Batch> getAllBatches() {
		Session session = HibernateUtil.getSession();
		List<Batch> batches = null;
		
		try {
			batches = session.createCriteria(Batch.class).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return batches;
	}

}