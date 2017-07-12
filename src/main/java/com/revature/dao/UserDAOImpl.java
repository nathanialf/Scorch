package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.User;
import com.revature.util.HibernateUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public int insertUser(User user) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		int userId = 0;

		try {
			tx = session.beginTransaction();
			userId = (Integer) session.save(user);
			tx.commit();
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
			tx = session.beginTransaction();/*
			List<User> users = session.createQuery("FROM User").list();			
			
			for (User u : users) {
				if (u.getUsername().equals(username) && u.getPassword().equals(password) && u.getActive() == 1) {
					user = u;
				}
			}*/
			List<User> users = session.createQuery("FROM User WHERE username = :user and password = :pass").setString("user", username).setString("pass", password).list();
			for(User u : users){
				user = u;
			}
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

}
