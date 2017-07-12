package com.revature.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.Batch;
import com.revature.bean.User;
import com.revature.service.BatchService;

@Controller
@RequestMapping(value="/batch")
public class BatchController {
	
	@Autowired 
	BatchService batchService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getBatchPage(){
		System.out.println("GET: /batch");
		
		
		List<User> associates = batchService.getAssociates();
		System.out.println(associates);
		
		
		return "batch";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doNewBatch(@Valid Batch batch){
		System.out.println("POST: /batch");
		
		List<User> associates = batchService.getAssociates();
		System.out.println(associates);
		
		batchService.createEmptyBatch(batch);
		
		return "batch";
	}
}
