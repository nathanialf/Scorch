package com.revature.service;

import org.springframework.stereotype.Component;

import com.revature.bean.User;

@Component
public class UserService {

	public User auth(User user){
		User authUser = null;
		
		if(user.getUsername().equals("Bobbert") &&
				user.getPassword().equals("stuff")){
			authUser = user;
		}
		
		return authUser;
		/*
		 * This authentication will return null in all situations where
		 * both the password and username are not correct
		 */
	}
}
