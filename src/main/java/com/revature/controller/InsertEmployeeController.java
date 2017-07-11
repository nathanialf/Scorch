package com.revature.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.bean.Role;
import com.revature.bean.User;
import com.revature.service.UserService;

@Controller
@RequestMapping(value = "/add")
public class InsertEmployeeController {
	User sessionUser;

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String getEmployeesPost(User user, @RequestParam("role") int role, BindingResult bindingResult, ModelMap modelMap, HttpSession session) {
		user.setActive(1);
		user.setPassword("generic");
		//System.out.println("\n\n\n" + role + "\n\n\n");
		Role r = userService.getRole(role);
		userService.addEmployee(user);
		return "home";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getEmployeesGet(User user,@RequestParam("role") int role, BindingResult bindingResult, ModelMap modelMap, HttpSession session) {
		user.setActive(1);
		user.setPassword("generic");
		Role r = userService.getRole(role);
		userService.addEmployee(user);
		return "home";
	}
}
