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
import com.revature.dao.RoleDAO;
import com.revature.dao.RoleDAOImpl;
import com.revature.service.UserService;

@Controller
@RequestMapping(value = "/employeeadd")
public class AddEmployeeController {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public String getEmployeesPost(@Valid User user, BindingResult bindingResult, ModelMap modelMap, HttpSession session) {
		session.setAttribute("note", null);
		user.setActive(1);
		user.setPassword("generic");
		int roleid = Integer.parseInt(user.getRole().getName());
		user.setRole(userService.getRole(roleid));
		//System.out.println(user);
		/*
		Role r = userService.getRole(role);
		user.setRole(r);
		*/
		userService.addEmployee(user);
		session.setAttribute("note", "You have add a new employee");
		List<Role> roles = userService.allRoles();
		modelMap.addAttribute("roles", roles);
		return "newemployee";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getEmployeesGet(@Valid User user, BindingResult bindingResult, ModelMap modelMap, HttpSession session) {
		session.setAttribute("note", null);
		user.setActive(1);
		user.setPassword("generic");
		/*
		Role r = userService.getRole(role);
		user.setRole(r);
		*/
		userService.addEmployee(user);
		session.setAttribute("note", "You have added a new employee");
		List<Role> roles = userService.allRoles();
		modelMap.addAttribute("roles", roles);
		return "newemployee";
	}
}