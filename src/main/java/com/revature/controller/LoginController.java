package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.revature.bean.Week;
import com.revature.dao.BatchDAO;
import com.revature.dao.BatchDAOImpl;
import com.revature.dao.WeekDAO;
import com.revature.dao.WeekDAOImpl;
import com.revature.service.BatchService;
import com.revature.service.UserService;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	
	@Autowired
	User emptyUser;
	
	@Autowired
	UserService userService;
	
	@ModelAttribute("Some info")
	public String addInfotoRequestScope(){
		System.out.println("Adding info the the modelMap");
		return "This is added information";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getLoginPage(ModelMap modelMap, HttpSession session){
		
		if(session.getAttribute("user") != null){
			
			if(((User)session.getAttribute("user")).getRole().getName().equals("Evaluator") || ((User)session.getAttribute("user")).getRole().getName().equals("Manager")) {
				BatchDAO bDao = new BatchDAOImpl();
				BatchService bServ = new BatchService();
				List<Batch> aBatches = bDao.getAllBatches();
				for(Batch b : aBatches) {
					WeekDAO wdao = new WeekDAOImpl();
					List<Week> weeks = wdao.getAllWeeks();
					List<Week> batchWeeks = new ArrayList<Week>();
					for (Week w : weeks) {
						if (w.getBatch().getId() == b.getId())
							batchWeeks.add(w);
					}
					b.setWeeks(batchWeeks);
				}
				modelMap.addAttribute("batches", aBatches);
				modelMap.addAttribute("userService", userService);
			}
			
			
			return "home";
		}

		
		System.out.println(modelMap.get("Some info"));
		
		System.out.println("GET REQUEST");
		
		modelMap.addAttribute("user", emptyUser);
		
		return "login";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doLogin(@Valid User user, BindingResult bindingResult, ModelMap modelMap, HttpSession session){
		
		/*
		 * @Valid
		 * - Apply a validation check on user object.
		 * - its at this point it will validate against the validator annotations
		 *   that we used in our user bean
		 * 
		 * BindingResult
		 * - This object houses error messages should any of the @Valid checks
		 *   come back as a failure
		 *   
		 * ModelMap
		 * - This object represents the data being passed around the back-end and 
		 *   will ultimately be passed back to the client at the end of the 
		 *   life-cycle displayed as a view
		 * 
		 * HttpSession
		 * - This is the session object. 
		 */
		
		if(bindingResult.hasErrors())
			return "login";
		
		User authUser = userService.auth(user);
		
		if(authUser != null){
			System.out.println(user.getUsername());
			modelMap.addAttribute("user", authUser);
			session.setAttribute("user", authUser);
			if(authUser.getRole().getName().equals("Evaluator") || authUser.getRole().getName().equals("Manager")) {
				BatchDAO bDao = new BatchDAOImpl();
				BatchService bServ = new BatchService();
				List<Batch> aBatches = bDao.getAllBatches();
				for(Batch b : aBatches) {
					WeekDAO wdao = new WeekDAOImpl();
					List<Week> weeks = wdao.getAllWeeks();
					List<Week> batchWeeks = new ArrayList<Week>();
					for (Week w : weeks) {
						if (w.getBatch().getId() == b.getId())
							batchWeeks.add(w);
					}
					b.setWeeks(batchWeeks);
				}
				modelMap.addAttribute("batches", aBatches);
				modelMap.addAttribute("userService", userService);
			}
			return "home";
		}else{
			modelMap.addAttribute("errorMessage", "username/password incorrect");
			return "login";
		}
	}
}
