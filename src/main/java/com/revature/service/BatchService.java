package com.revature.service;

import java.sql.Date;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.revature.bean.Batch;
import com.revature.bean.User;
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
		
		//Selects users that are not associates
        Predicate<User> userPredicate = p -> !p.getRole().getName().equals("Associate");
		
        //Removes any user that is part of the predicate
		users.removeIf(userPredicate);
		
		return users;
	}
}
