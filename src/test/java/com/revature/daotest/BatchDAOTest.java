package com.revature.daotest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.BatchDAO;
import com.revature.dao.BatchDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.dao.WeekDAO;

public class BatchDAOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//From DB tables
		//Batches has-a User
		//				Week
		UserDAO bs1 = new UserDAOImpl();
		//WeekDAO bs2 = new WeekDAOImpl();
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
		//ok..
//		User t = new User("Bobbert", "java", "Bob", "Tester", 1, null);
//		//Create first batch
//		test.createBatch(t, "Java 404", null, null, null);
//		
//		//get batch 1
//		assertNotNull(test.getBatchById(1));
//		Batch toupd = test.getBatchById(1);
//				
//		//create 2nd batch
//		test.createBatch(t, "Java 404", null, null, null);
//
//		//get batch 2
//		assertNotNull(test.getBatchById(2));
//		
//		//update an existing batch
//		toupd.setName("Java 300");
//		test.setBatch(toupd);
//		
//		//get batch with update
//		System.out.println(test.getBatchById(1));
//		
//		//get all batches
//		assertNotNull(test.getAllBatches());
		
		
	}

}
