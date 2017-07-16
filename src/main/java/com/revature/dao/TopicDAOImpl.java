package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.Topic;
import com.revature.bean.Week;
import com.revature.util.HibernateUtil;

public class TopicDAOImpl implements TopicDAO {
	final static Logger logger = Logger.getLogger(TopicDAOImpl.class);

	@Override
	public int insertTopic(Topic t) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		int id = 0;

		try {
			tx = session.beginTransaction();
			id = (Integer) session.save(t);
			tx.commit();
			logger.info("Created Topic "+t);
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
	public Topic getTopicById(int id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		Topic topic = null;

		try {
			tx = session.beginTransaction();
			topic = (Topic) session.get(Topic.class, id);
			logger.info("Retrieved Topic By Id "+id);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return topic;
	}

	@Override
	public List<Topic> getAllTopics() {
		List<Topic> topics = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			topics = session.createQuery("FROM Topic").list();
			logger.info("Retrieved All Topics");
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return topics;
	}
	
	@Override
	public List<Topic> getAllTopicsByWeek(Week w) {
		List<Topic> topics = null;
		List<Topic> weekTops = new ArrayList<Topic>();
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			topics = session.createQuery("FROM Topic").list();
			
			for(Topic t : topics) {
				if(t.getWeek().getId() == w.getId()) {
					weekTops.add(t);
				}
			}
			logger.info("Retrieved Topics By Week "+w);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return weekTops;
	}

	@Override
	public void updateTopic(Topic t) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.update(t);
			tx.commit();
			logger.info("Updated Topic "+t);
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
	public void deleteTopic(Topic t) {

		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.delete(t);
			tx.commit();
			logger.info("Deleted Topic "+t);
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
