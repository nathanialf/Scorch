package com.revature.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.Review;
import com.revature.bean.User;
import com.revature.util.HibernateUtil;

public class ReviewDAOImpl implements ReviewDAO {

	@Override
	public int insertReview(Review r) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		int reviewId = 0;

		try {
			tx = session.beginTransaction();
			reviewId = (Integer) session.save(r);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return reviewId;
	}

	@Override
	public Review getReviewById(int id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Review review = null;

		try {
			tx = session.beginTransaction();
			review = (Review) session.get(Review.class, id);

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return review;
	}

}
