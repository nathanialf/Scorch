package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.User;
import com.revature.service.UserService;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
	User sessionUser;

	UserService userService = new UserService();

	@RequestMapping(method = RequestMethod.GET)
	public String getEmployees(ModelMap modelMap, HttpSession session) {

		session.setAttribute("note", null);
		sessionUser = (User) session.getAttribute("user");
		if (sessionUser == null)
			System.out.println("null");
		else {
			List<User> users = userService.allEmployees(sessionUser.getId());
			modelMap.addAttribute("employees", users);
		}
		return "employee";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doLogin(@Valid User user, BindingResult bindingResult, ModelMap modelMap, HttpSession session) {
		session.setAttribute("note", null);
		return "employee";
	}
}
