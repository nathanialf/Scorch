package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
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
			userId = (Integer)session.save(user);
			tx.commit();
		}
		catch(HibernateException e) {
			if(tx != null) {
            	tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
        	session.close();
        }
        
        return userId;
	}

	@Override
	public User getUserById(Integer id) {
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
        User user = null;
        
        try {
            tx = session.beginTransaction();
            user = (User)session.get(User.class, id);
            
        }
        catch(HibernateException e) {
            if(tx != null) {
            	tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
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
	            
	        }
	        catch(HibernateException e) {
	            if(tx != null) {
	            	tx.rollback();
	            }
	            e.printStackTrace();
	        }
	        finally {
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
			if (session.contains(user)) {
				session.save(user);
			}
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
            
        }
        catch(HibernateException e) {
            if(tx != null) {
            	tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
        	session.close();
        }
	}

}
