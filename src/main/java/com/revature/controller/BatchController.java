package com.revature.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.Batch;
import com.revature.bean.User;

@Controller
@RequestMapping(value="/batch")
public class BatchController {
	
	@Autowired
	Batch emptyBatch;
	
	
	@ModelAttribute("Some info")
	public String addInfotoRequestScope(){
		System.out.println("Clicked on Create User from JSP Page...");
		return "This is added information";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getLoginPage(ModelMap modelMap){
		
		return "batch";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doLogin(@Valid User user, BindingResult bindingResult, ModelMap modelMap, HttpSession session){
		
		return "batch";
	}
}
