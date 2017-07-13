package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.Role;
import com.revature.bean.User;
import com.revature.service.UserService;

@Controller
@RequestMapping(value = "/employeeprofile")
public class EmployeeProfileController {
	User sessionUser;

	UserService userService = new UserService();

	@RequestMapping(method = RequestMethod.GET)
	public String getEmployees(ModelMap modelMap, HttpSession session) {

		List<Role> roles = userService.allRoles();
		modelMap.addAttribute("roles", roles);
		
		
		return "profile";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doLogin(@Valid User user, BindingResult bindingResult, ModelMap modelMap, HttpSession session) {

		List<Role> roles = userService.allRoles();
		modelMap.addAttribute("roles", roles);
		return "profile";
	}
}
