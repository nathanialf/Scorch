
package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.User;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.service.UserService;

@Controller
@RequestMapping(value = "/employeedrop")
public class EmployeeDropController {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public String getEmployeesPost(ModelMap modelMap, HttpSession session, HttpServletRequest request) {
		session.setAttribute("note", null);
		
		return "home";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getEmployeesGet(ModelMap modelMap, HttpSession session, HttpServletRequest request) {
		session.setAttribute("note", null);
		
		int id = Integer.parseInt(request.getParameter("id"));
		UserDAO uDAO = new UserDAOImpl();
		User u  = uDAO.getUserById(id);
		u.setActive(0);
		userService.updateUser(u);
		session.setAttribute("note", "User " + u.getFirstname() + " " + u.getLastname() + " has been removed from the program");
		
		
		return "home";
	}
}