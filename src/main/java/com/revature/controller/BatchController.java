package com.revature.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String getBatchPage(HttpSession session){
		System.out.println("GET: /batch");
		
		
		List<User> aUsers = batchService.getAssociates();
		System.out.println(aUsers);
		
		session.setAttribute("associates", aUsers);
		
		return "batch";
	}
		
	
	@RequestMapping(method = RequestMethod.POST)
	public String doNewBatch(@ModelAttribute Batch batch){
		System.out.println("POST: /batch");
		
		//List<User> aUsers = batchService.getAssociates();
		//System.out.println(aUsers);
		
		Set<User> associates = batch.getAssociates();

		System.out.println(associates);
		
		batchService.createBatch(batch);
		
		return "batch";
	}
}
