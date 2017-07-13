package com.revature.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.springframework.stereotype.Component;

import com.revature.bean.Batch;
import com.revature.bean.Role;
import com.revature.bean.User;
import com.revature.dao.RoleDAO;
import com.revature.dao.RoleDAOImpl;
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
	
	public int addEmployee(User u){
		UserDAO uDAO = new UserDAOImpl();
		return uDAO.insertUser(u);
	}
	
	public List<Role> allRoles(){
		RoleDAO rDAO = new RoleDAOImpl();
		return rDAO.getRoles();
	}
	
	public Role getRole(int id){
		RoleDAO rDAO = new RoleDAOImpl();
		return rDAO.getRoleById(id);
	}
	
	public User updateUser(User user){
		UserDAO uDAO = new UserDAOImpl();
		uDAO.updateUser(user);
		return uDAO.getUserById(user.getId());
	}
	
	public User getTrainer(Batch b){
		UserDAO uDAO = new UserDAOImpl();
		User trainer = null;
		
		List<User> users = uDAO.getAllUsers();
		
		user:
		for(User u : users){
			for(Batch bat : u.getBatches()){
				if(bat.getId() == b.getId()){
					trainer = u;
					break user;
				}
			}
		}
		
		return trainer;
	}
}
