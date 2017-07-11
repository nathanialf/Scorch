package com.revature.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.revature.bean.User;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;

@Component
public class UserService {

	public User auth(User user){
		User authUser = null;
		
		UserDAO uDao = new UserDAOImpl();
		
		authUser = uDao.getUserByLogin(user.getUsername(), user.getPassword());
		
		System.out.println(authUser);
//		if(user.getUsername().equals("bobbert") &&
//				user.getPassword().equals("stuff")){
//			authUser = user;
//		}
		
		return authUser;
		/*
		 * This authentication will return null in all situations where
		 * both the password and username are not correct
		 */
	}
	
	public List<User> allEmployees(int id){
		UserDAO uDAO = new UserDAOImpl();
		//return uDAO.getAllUsersExcept(id);
		return uDAO.getAllUsers();
	}
}
