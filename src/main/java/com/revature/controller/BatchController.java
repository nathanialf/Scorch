package com.revature.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.Batch;
import com.revature.bean.User;
import com.revature.dao.BatchDAO;
import com.revature.dao.BatchDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
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
		
		List<User> tUsers = batchService.getTrainers();
		
		BatchDAO bDao = new BatchDAOImpl();
		List<Batch> aBatches = bDao.getAllBatches();
		

		String[][] bidAndItsTrainer = new String[aBatches.size()][2];
		int i = 0;
		for(User t : tUsers){
			Set<Batch> tempB = t.getBatches();
			for(Batch b : aBatches) {
				for(Batch tb : tempB) {
					if(b.getId() == tb.getId()){
						bidAndItsTrainer[i][0]= String.valueOf(b.getId());
						bidAndItsTrainer[i][1]= t.getFirstname() + " " + t.getLastname();
						i++;
						
					}
				}
			}
		}
		
		
		session.setAttribute("stringyTrainers", bidAndItsTrainer);
		session.setAttribute("associates", aUsers);
		session.setAttribute("batches", aBatches);
		
		return "batch";
	}
		
	
	@RequestMapping(method = RequestMethod.POST)
	public String doNewBatch(@Valid Batch batch, HttpSession session, HttpServletRequest request){
		System.out.println("POST: /batch");
		
		
		//List<User> associates = (List<User>)session.getAttribute("associates");
		//		System.out.println("The full list: " + associates);
		
			
		Set<User> a = new HashSet<>();
		UserDAO uDao = new UserDAOImpl();
		
		String[] options = request.getParameterValues("selectedAssoc");
		for(String option : options){
			a.add(uDao.getUserById(Integer.parseInt(option)));
		}
		
		System.out.println("The Associates in a set:");
		System.out.println(a);
		
		User trainer = (User)session.getAttribute("user");
		System.out.println("The current trainer is:");
		System.out.println(trainer.getUsername());
		
		Set<Batch> b = new HashSet<>();
		b.add(batch);
		
			
		trainer.setBatches(b);
				
		batch.setAssociates(a);
		
		//First MUST create the batch 
		batchService.createBatch(batch);
		
		//THEN Update the user
		uDao.updateUser(trainer);
		
		
		//Update the batches attribute
		BatchDAO bDao = new BatchDAOImpl();
		List<Batch> aBatches = bDao.getAllBatches();
		session.setAttribute("batches", aBatches);
		
		return "batch";
	}
}
