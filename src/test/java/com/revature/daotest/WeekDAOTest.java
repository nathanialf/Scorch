package com.revature.daotest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.WeekDAO;
import com.revature.dao.WeekDAOImpl;


//Doing this test case after User and Batch tests...
public class WeekDAOTest {

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
		WeekDAO wdao = new WeekDAOImpl();
				
//		wdao.getWeekById(id);
//		
//		wdao.insertWeek(week);
//		
//		wdao.updateWeek(week);
//		
//		wdao.getAllWeeks();

	}

}