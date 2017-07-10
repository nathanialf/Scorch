package com.revature.daotest;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.bean.Role;
import com.revature.bean.User;
import com.revature.dao.RoleDAO;
import com.revature.dao.RoleDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;

public class UserDAOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Role setup
		RoleDAO x = new RoleDAOImpl();
		Role a = new Role("Associate");
		Role b = new Role("Trainer");
		x.insertRole(a);
		x.insertRole(b);
		
		//Setup for having at least one user
		UserDAO y = new UserDAOImpl();
		User u = new User("mandatoryu","pass","Adam","Smith",1,b,null);
		y.insertUser(u);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("---BEFORE THE TEST---");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("---AFTER THE TEST---");
	}
	
	@Ignore
	@Test
	public void insertUser() {
		System.out.println("-STARTING INSERTUSER-");
		UserDAO x = new UserDAOImpl();
		
		//Two roles
		Role y1 = new Role("Associate");
		Role y2 = new Role("Trainer");
		
		//Two users of each role
		User u1 = new User("username1", "pass", "Bob", "Bobson", 1, y1,null);
		User u2 = new User("username2", "pass", "Bob2", "Bobson2", 1, y2,null);

		//Check id of the inserted role
		System.out.println("id: " + x.insertUser(u1));
		System.out.println("id: " + x.insertUser(u2));
		
		//Extra get all users test
		System.out.println(x.getAllUsers());
	}
	
//	@Test
//	public void getUserById() {
//		System.out.println("-STARTING GETUSERBYID-");
//
//		UserDAO x = new UserDAOImpl();
//		// 51 is a known id
//		assertNotNull(x.getUserById(50));
//		System.out.println(x.getUserById(50));
//	}
	
	@Ignore
	@Test
	public void getAllUsers() {
		System.out.println("-STARTING GET_ALL_USERS-");

		UserDAO x = new UserDAOImpl();
		assertNotNull(x.getAllUsers());
		System.out.println(x.getAllUsers());
	}
	
	@Ignore
	@Test
	public void updateUser() {
		System.out.println("-STARTING UPDATE_USER-");

		UserDAO x = new UserDAOImpl();
		User t = x.getUserById(50);
		System.out.println(t);
		t.setFirstname("UPDATEDTEST");
		x.updateUser(t);
		
		//Check to see if update worked
		System.out.println(x.getUserById(50));
		
		assert(x.getUserById(50).getFirstname().equals("UPDATEDTEST"));
	}
	
	@Ignore
	@Test
	public void deleteUser() {
		System.out.println("-STARTING DELETE_USER-");

		UserDAO x = new UserDAOImpl();
		// 52 is a known id
		x.deleteUser(x.getUserById(50));
		
		//Check after deleting
		System.out.println(x.getUserById(50));
	}
}
