package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.Role;
import com.revature.bean.User;
import com.revature.service.UserService;

@Controller
@RequestMapping(value = "/employeenew")
public class NewEmployeeController {
	User sessionUser;

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String getEmployees(ModelMap modelMap, HttpSession session) {
		session.setAttribute("note", null);

		List<Role> roles = userService.allRoles();
		modelMap.addAttribute("roles", roles);
		
		return "newemployee";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String getEmployeesPost(@Valid User user, BindingResult bindingResult, ModelMap modelMap, HttpSession session) {
		session.setAttribute("note", null);

		List<Role> roles = userService.allRoles();
		modelMap.addAttribute("roles", roles);
		return "newemployee";
	}
}
