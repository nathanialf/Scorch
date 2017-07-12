package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.Batch;
import com.revature.bean.User;
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
			
			System.out.println(batch.getId());
			System.out.println(batch.getName());
			System.out.println(batch.getAssociates());
			System.out.println(batch.getWeeks());
			
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
		List<Batch> batches = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			batches = session.createQuery("FROM Batch").list();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return batches;
	}

	@Override
	public void deleteBatch(Batch batch) {

		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.delete(batch);
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
	public Batch selectBatchByUser(User u) {
		Batch batch = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();/*
			Query query = session.createQuery("select batch_id from batch_assoc where associate_id = :id");
			query.setInteger("id", u.getId());
			List<Object[]> rows = query.list();
			for(Object o : rows){
				batch = (Batch) session.get(Batch.class, (int)o);
			}
			*/
			batch = (Batch) session.createQuery("FROM Batch WHERE id in (select batch_id from batch_assoc where associate_id = :id)").setInteger("id", u.getId());

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return batch;
	}

}