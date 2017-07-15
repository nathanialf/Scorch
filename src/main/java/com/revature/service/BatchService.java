package com.revature.service;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.revature.bean.Batch;
import com.revature.bean.User;
import com.revature.bean.Week;
import com.revature.dao.BatchDAO;
import com.revature.dao.BatchDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;

@Component
public class BatchService {

	public void createBatch(Batch batch) {
		batch.setStartDate(new Date(System.currentTimeMillis()));

		BatchDAO bDao = new BatchDAOImpl();

		bDao.insertBatch(batch);
	}

	public List<User> getAssociates() {
		UserDAO uDao = new UserDAOImpl();
		List<User> users = uDao.getAllUsers();

		// Selects users that are not associates
		Predicate<User> userPredicate = p -> !p.getRole().getName().equals("Associate");

		// Removes any user that is part of the predicate
		users.removeIf(userPredicate);

		return users;
	}
	
	
	public List<User> getTrainers() {
		UserDAO uDao = new UserDAOImpl();
		List<User> users = uDao.getAllUsers();

		// Selects users that are not associates
		Predicate<User> userPredicate = p -> !p.getRole().getName().equals("Trainer");

		// Removes any user that is part of the predicate
		users.removeIf(userPredicate);

		return users;
	}

	public Batch getBatch(int id) {
		BatchDAO bDao = new BatchDAOImpl();
		return bDao.getBatchById(id);
	}

	/*
	 * Gets the FIRST batch that the associate is in. An associate should only
	 * belong to one batch BUT since the Tables are not completely setup, then
	 * currently retrieves the first match
	 */
	public int getBatchIdOfCurrentAssociate(User currentAssociate) {
		int batchIdImIn = 0;
		BatchDAO bDao = new BatchDAOImpl();
		System.out.println(bDao.getAllBatches());
		List<Batch> batches = bDao.getAllBatches();

		firstBatch: for (Batch b : batches) {
			Set<User> userPile = b.getAssociates();
			for (User u : userPile) {
				if (u != null) {
					if (u.getId() == currentAssociate.getId()) {
						batchIdImIn = b.getId();
						break firstBatch;
					}
				}
			}
		}
		return batchIdImIn;
	}
	
	public Batch getBatchByWeek(Week w){
		Batch batch = null;
		
		
		
		return batch;
	}
}
