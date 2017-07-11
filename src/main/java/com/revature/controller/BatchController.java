package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.Batch;
import com.revature.service.BatchService;

@Controller
@RequestMapping(value="/batch")
public class BatchController {
	
	@Autowired
	Batch emptyBatch;
	
	@Autowired 
	BatchService batchService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getLoginPage(){
		System.out.println("GET: /batch");
		
		return "batch";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doLogin(){
		System.out.println("POST: /batch");
		
		return "batch";
	}
}
