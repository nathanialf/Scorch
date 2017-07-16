package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.Review;
import com.revature.util.HibernateUtil;

public class ReviewDAOImpl implements ReviewDAO {
	
	final static Logger logger = Logger.getLogger(ReviewDAOImpl.class);

	@Override
	public int insertReview(Review r) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		int reviewId = 0;

		try {
			tx = session.beginTransaction();
			reviewId = (Integer) session.save(r);
			tx.commit();
			logger.info("Created Review "+r);
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
			logger.info("Retrieved Review By Id "+id);

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

	@Override
	public List<Review> getAllReviews() {
		List<Review> reviews = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			reviews = session.createQuery("FROM Review").list();
			logger.info("Retrieved All Reviews");
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return reviews;
	}

	@Override
	public void updateReview(Review r) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.update(r);
			tx.commit();
			logger.info("Updated Review "+r);
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
	public void deleteReview(Review r) {

		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.delete(r);
			tx.commit();
			logger.info("Deleted Review "+r);

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
