package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.User;
import com.revature.util.HibernateUtil;

public class UserDAOImpl implements UserDAO {
	final static Logger logger = Logger.getLogger(UserDAOImpl.class);

	@Override
	public int insertUser(User user) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		int userId = 0;
		try {
			tx = session.beginTransaction();
			userId = (Integer) session.save(user);
			tx.commit();
			logger.info("Created User "+user);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return userId;
	}

	@Override
	public User getUserById(int id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = session.beginTransaction();
			user = (User) session.get(User.class, id);
			logger.info("Retrieved User By Id "+id);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			users = session.createQuery("FROM User").list();
			logger.info("Retrieved All Users");
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return users;
	}

	@Override
	public void updateUser(User user) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
			logger.info("Updated User "+user);
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
	public void deleteUser(User u) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(u);
			tx.commit();
			logger.info("Deleted User "+u);
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
	public User getUserByLogin(String username, String password) {
		User user = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<User> users = session.createQuery("FROM User WHERE username = :user and password = :pass and active=1")
					.setString("user", username).setString("pass", password).list();

			for (User u : users) {
				user = u;
			}
			logger.info("Retrieved User By Login");
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();

		} finally {
			session.close();
		}

		return user;
	}

	@Override
	public List<User> getAllUsersExcept(int id) {
		List<User> user = null;
		List<User> users = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			users = session.createQuery("FROM User").list();
			for (User u : users) {
				if (u.getId() == id) {
					users.remove(u);
				}
			}
			logger.info("Retrieved All Users Except "+id);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return users;

	}
	
	public List<User> getManagement() {
		List<User> users = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			users = session.createQuery("FROM User WHERE ROLE_ID = 1 OR ROLE_ID = 2").list();		
			logger.info("Retrieved Management Users");
		} catch (HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return users;
	}

}