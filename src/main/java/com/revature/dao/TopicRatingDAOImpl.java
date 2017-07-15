package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.Topic;
import com.revature.bean.TopicRating;
import com.revature.bean.User;
import com.revature.util.HibernateUtil;

public class TopicRatingDAOImpl implements TopicRatingDAO{

	@Override
	public int insertTopicRating(TopicRating t) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		int id = 0;

		try {
			tx = session.beginTransaction();
			id = (Integer) session.save(t);
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
	public TopicRating getTopicRatingById(int id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		TopicRating rating = null;

		try {
			tx = session.beginTransaction();
			rating = (TopicRating) session.get(TopicRating.class, id);

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return rating;
	}

	@Override
	public List<TopicRating> getAllTopicRatings() {
		List<TopicRating> ratings = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			ratings = session.createQuery("FROM TopicRating").list();
			System.out.println(ratings.size());
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return ratings;
	}

	@Override
	public void updateTopicRating(TopicRating t) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.update(t);
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
	public void deleteTopicRating(TopicRating t) {

		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.delete(t);
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
	public TopicRating getTopicRatingByUserandTopic(User user, Topic topic) {
		TopicRating tr = null;
		List<TopicRating> ratings = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			ratings = session.createQuery("FROM TopicRating").list();
			
			for(TopicRating rate : ratings){
				if(rate.getUser().getId() == user.getId() && rate.getTopic().getId() == topic.getId()){
					tr = rate;
					break;
				}
			}
			
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return tr;
		
	}

}
