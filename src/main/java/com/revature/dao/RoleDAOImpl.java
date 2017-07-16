package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.Role;
import com.revature.util.HibernateUtil;

public class RoleDAOImpl implements RoleDAO {
	final static Logger logger = Logger.getLogger(RoleDAOImpl.class);

	@Override
	public int insertRole(Role r) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		int id = 0;

		try {
			tx = session.beginTransaction();
			id = (Integer) session.save(r);
			tx.commit();
			logger.info("Created Role "+r);
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
	public Role getRoleById(int id) {
		Session session = HibernateUtil.getSession();
		Role role = null;
		try {
			role = (Role) session.get(Role.class, id);
			logger.info("Retrieved Role By Id "+id);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return role;
	}

	@Override
	public List<Role> getRoles() {
		Session session = HibernateUtil.getSession();
		List<Role> roles = null;
		
		try {
			roles = session.createCriteria(Role.class).list();
			logger.info("Retrieved All Roles");
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return roles;
	}

	@Override
	public void updateRole(Role r) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			
			session.update(r);
			
			tx.commit();
			logger.info("Updated Role "+r);
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
	public void deleteRole(Role r) {
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.delete(r);
            tx.commit();
            logger.info("Deleted Role "+r);
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
