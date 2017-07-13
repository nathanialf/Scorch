package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
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
		Batch batch = null;
		try {
			batch = (Batch) session.get(Batch.class, batchId);
			WeekDAO wdao = new WeekDAOImpl();
			List<Week> weeks = wdao.getAllWeeks();
			List<Week> batchWeeks = new ArrayList<Week>();
			for (Week w : weeks) {
				if (w.getBatch().getId() == batch.getId())
					batchWeeks.add(w);
			}
			batch.setWeeks(batchWeeks);
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
			tx = session.beginTransaction();

			List<Batch> batches = session.createQuery("FROM Batch").list();

			batch: for (Batch b : batches) {
				for (User user : b.getAssociates()) {
					if (user.getId() == u.getId()) {
						batch = b;
						break batch;
					}
				}
			}
			if (batch != null) {
				WeekDAO wdao = new WeekDAOImpl();
				List<Week> weeks = wdao.getAllWeeks();
				List<Week> batchWeeks = new ArrayList<Week>();
				for (Week w : weeks) {
					if (w.getBatch().getId() == batch.getId())
						batchWeeks.add(w);
					TopicDAO tDAO = new TopicDAOImpl();
					w.setTopics(tDAO.getAllTopicsByWeek(w));
				}
				batch.setWeeks(batchWeeks);
			}

			// batch = (Batch) session.createQuery("FROM Batch WHERE id in
			// (select Batch.id from BATCH_ASSOC where User.id =
			// :id)").setInteger("id", u.getId());

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

	// @Override
	// public Batch selectBatchByTrainer(User u) {
	// Batch batch = null;
	// Session session = HibernateUtil.getSession();
	// Transaction tx = null;
	//
	// try {
	// tx = session.beginTransaction();
	//
	// List<Batch> batches = session.createQuery("FROM Batch").list();
	//
	// batch:
	// for(Batch b : batches){
	// if(b.getTrainer().getId() == u.getId()){
	// batch = b;
	// break batch;
	// }
	// }
	//
	// } catch (HibernateException e) {
	// if (tx != null) {
	// tx.rollback();
	// }
	// e.printStackTrace();
	// } finally {
	// session.close();
	// }
	//
	// return batch;
	// }

}