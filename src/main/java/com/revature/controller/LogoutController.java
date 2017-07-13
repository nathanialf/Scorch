package com.revature.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.User;

@Controller
@RequestMapping(value="**/logout")
public class LogoutController {
	
	@Autowired
	User emptyUser;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getLogoutPage(HttpSession session, ModelMap modelMap) {
		
		modelMap.addAttribute("user", emptyUser);
		
		session.invalidate();
		
		return "login";
	}
}
