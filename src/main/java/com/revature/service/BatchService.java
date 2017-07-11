package com.revature.service;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.revature.bean.Batch;
import com.revature.dao.BatchDAO;
import com.revature.dao.BatchDAOImpl;

@Component
public class BatchService {

	public void createEmptyBatch(String name){
		Batch batch = new Batch(name, new Date(System.currentTimeMillis()));
		
		BatchDAO bDao = new BatchDAOImpl();
		
		
	}
	

}
