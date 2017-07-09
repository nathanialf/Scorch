package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createWeek() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateWeek(Week week) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Week> getAllWeeks() {
		// TODO Auto-generated method stub
		return null;
	}

}
