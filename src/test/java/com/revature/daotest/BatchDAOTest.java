package com.revature.daotest;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.bean.Batch;
import com.revature.bean.Role;
import com.revature.bean.User;
import com.revature.dao.BatchDAO;
import com.revature.dao.BatchDAOImpl;
import com.revature.dao.RoleDAO;
import com.revature.dao.RoleDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;

public class BatchDAOTest {

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void test() {
		BatchDAO test = new BatchDAOImpl();
		
		//After ensuring UserDAOTest worked perfectly...
		UserDAO udao = new UserDAOImpl();
	
		Batch x = new Batch();
		
		//Assign to handle referential integrity
		x.getAssociates().add(udao.getUserById(udao.insertUser(
				new User("bdaotest","bdaopass","Batcher","Batchmen",1,new Role("Associate"),null))));
		
		x.setWeeks(null);
		x.setName("Java 404");
		x.setStartDate(new Date(System.currentTimeMillis()));
		
		int xn = test.insertBatch(x);
		//int y = test.insertBatch(new Batch("Java 405", new Date(System.currentTimeMillis())));
		//int z = test.insertBatch(new Batch("Java 406", new Date(System.currentTimeMillis())));
		
		assertNotNull(test.getBatchById(xn));
		//assertNotNull(test.getBatchById(y));
		//assertNotNull(test.getBatchById(z));
		
		System.out.println(test.getAllBatches());
		assertNotNull(test.getAllBatches());
		
		x.setName("New Name For Upd");
		test.updateBatch(x);
		assert(test.getBatchById(xn).getName().equals("New Name For Upd"));
		
		System.out.println(test.getAllBatches());
		assertNotNull(test.getAllBatches());
	}

}
