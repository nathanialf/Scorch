package com.revature.dao;

import java.util.List;

import com.revature.bean.User;

public interface UserDAO {
	
	public int insertUser(User u);
	public User getUserById(int id);
	public List<User> getAllUsers();
	public List<User> getAllUsersExcept(int id);
	public void updateUser(User u);
	public void deleteUser(User u);
	public User getUserByLogin(String username, String password);
}
