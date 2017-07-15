package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping(value = "/employeeupdate")
public class EmployeeUpdateController {

	User sessionUser;

	@Autowired
	UserService userService;

	/*
	 * @RequestMapping(method = RequestMethod.GET) public String
	 * getEmployees(ModelMap modelMap, HttpSession session) {
	 * 
	 * 
	 * return "profile"; }
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String doLogin(@Valid User user, BindingResult bindingResult, HttpServletRequest request,
			HttpSession session) {
		sessionUser = (User) session.getAttribute("user");
		session.setAttribute("note", null);

		sessionUser.setFirstname(user.getFirstname());
		sessionUser.setLastname(user.getLastname());
		sessionUser.setUsername(user.getUsername());

		if (request.getParameter("oldpass").equals(sessionUser.getPassword()))
			sessionUser.setPassword(user.getPassword());
		else
			sessionUser.setPassword(sessionUser.getPassword());

		session.setAttribute("user", userService.updateUser(sessionUser));
		session.setAttribute("note", "Your account has been updated");

		return "profile";
	}
}
