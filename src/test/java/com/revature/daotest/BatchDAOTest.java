package com.revature.daotest;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.bean.Batch;
import com.revature.bean.User;
import com.revature.dao.BatchDAO;
import com.revature.dao.BatchDAOImpl;

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
		
		Batch x = new Batch();
		
		List<User> us = new ArrayList<User>();
		
		
//		x.setAssociates(us);
//		x.setWeeks(null);
//		x.setName("Java 404");
//		x.setStartDate(new Date(System.currentTimeMillis()));
//	
//		int xn = test.insertBatch(x);
//		//int y = test.insertBatch(new Batch("Java 405", new Date(System.currentTimeMillis())));
//		//int z = test.insertBatch(new Batch("Java 406", new Date(System.currentTimeMillis())));
//		
//		assertNotNull(test.getBatchById(xn));
//		//assertNotNull(test.getBatchById(y));
//		//assertNotNull(test.getBatchById(z));
//		
//		System.out.println(test.getAllBatches());
//		assertNotNull(test.getAllBatches());
//		
//		x.setName("New Name For Upd");
//		test.updateBatch(x);
//		assert(test.getBatchById(xn).getName().equals("New Name For Upd"));
//		
//		System.out.println(test.getAllBatches());
//		assertNotNull(test.getAllBatches());
	}

}
