package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.User;
import com.revature.bean.Week;
import com.revature.util.HibernateUtil;

public class WeekDAOImpl implements WeekDAO {

	@Override
	public int insertWeek(Week week) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		int id = 0;

		try {
			tx = session.beginTransaction();
			id = (Integer) session.save(week);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return id;
	}

	@Override
	public Week getWeekById(int id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Week week = null;

		try {
			tx = session.beginTransaction();
			week = (Week) session.get(Week.class, id);

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return week;
	}

	@Override
	public void updateWeek(Week week) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.update(week);
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
	public List<Week> getAllWeeks() {
		List<Week> weeks = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			weeks = session.createQuery("FROM Week").list();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return weeks;
	}

}
