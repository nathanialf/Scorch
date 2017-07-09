package com.revature.dao;

import java.util.List;

import com.revature.bean.User;

public interface UserDAO {
	
	public int insertUser(User u);
	public User getUserById(Integer id);
	public List<User> getAllUsers();
	public void setUser(User u);
	public void deleteUser(Integer id);
}
