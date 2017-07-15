package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.Batch;
import com.revature.bean.Topic;
import com.revature.bean.User;
import com.revature.bean.Week;
import com.revature.dao.TopicDAO;
import com.revature.dao.TopicDAOImpl;
import com.revature.service.BatchService;
import com.revature.service.UserService;

@Controller
@RequestMapping(value = "/batchindividual")
public class IndivBatchController {

	User sessionUser;

	@Autowired
	BatchService batchService;

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
	public String doBatch(ModelMap modelMap, HttpSession session, HttpServletRequest request) {
		session.setAttribute("note", null);
		// System.out.println(id);
		int i = Integer.parseInt(request.getParameter("id"));
		Batch b = batchService.getBatch(i);

		for (Week w : b.getWeeks()) {
			TopicDAO tDAO = new TopicDAOImpl();
			w.setTopics(tDAO.getAllTopicsByWeek(w));
		}

		session.setAttribute("batch", b);
		User t = userService.getTrainer(b);
		System.out.println(t);
		session.setAttribute("trainerForBatchPage", t);
		return "indivbatch";
	}
}