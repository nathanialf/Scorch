package com.revature.dao;

import java.util.List;

import com.revature.bean.Role;

public interface RoleDAO {
	
	public int insertRole(Role r);
	public Role getRoleById(int id);
	public List<Role> getRoles();
	public void updateRole(Role r);
	public void deleteRole(Role r);
}
