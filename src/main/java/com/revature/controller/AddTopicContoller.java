package com.revature.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.Batch;
import com.revature.bean.Review;
import com.revature.bean.Topic;
import com.revature.bean.User;
import com.revature.bean.Week;
import com.revature.dao.BatchDAO;
import com.revature.dao.BatchDAOImpl;
import com.revature.dao.ReviewDAO;
import com.revature.dao.ReviewDAOImpl;
import com.revature.dao.TopicDAO;
import com.revature.dao.TopicDAOImpl;
import com.revature.dao.WeekDAO;
import com.revature.dao.WeekDAOImpl;
import com.revature.service.BatchService;
import com.revature.service.UserService;

@Controller
@RequestMapping(value = "/addtopic")
public class AddTopicContoller {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public String getEmployeesPost(ModelMap modelMap, HttpSession session, HttpServletRequest request) {
		session.setAttribute("note", null);
		String topic = request.getParameter("topic");
		int id = Integer.parseInt(request.getParameter("id"));
		WeekDAO wDAO = new WeekDAOImpl();
		Week w = wDAO.getWeekById(id);
		TopicDAO tDAO = new TopicDAOImpl();
		tDAO.insertTopic(new Topic(w, topic));
		session.setAttribute("note", "You have added " + topic + " as a new topic in Week " + w.getNum());
		
		
		
		

		ReviewDAO rDao = new ReviewDAOImpl();
		// Get My batch -> Which has a the set of Associates in my Batch
		BatchDAO bDao = new BatchDAOImpl();
		BatchService bs = new BatchService();
		User current = (User) session.getAttribute("user");
		
		// Get current week from a parameter
		//int weekId = Integer.parseInt(request.getParameter("wid"));
		Week week = wDAO.getWeekById(w.getId());
		int currentWeek = week.getNum();
		List<Topic> t = tDAO.getAllTopicsByWeek(week);
		week.setTopics(t);
		session.setAttribute("weekId", currentWeek);
		modelMap.addAttribute("week", week);

		Batch myBatch = null;
		if (current.getRole().getName().equals("Associate")) {
			int myBatchId = bs.getBatchIdOfCurrentAssociate(current);
			myBatch = bDao.getBatchById(myBatchId);
		}
		else{
			//get batch by week
			myBatch = week.getBatch();
		}
		
		modelMap.addAttribute("trainer", userService.getTrainer(myBatch));

		// Get the associates in the batch
		Set<User> setOfAssociates = myBatch.getAssociates();

		// Get only the batch reviews from all reviews
		List<Review> reviews = rDao.getAllReviews();

		List<Review> myBatchReviews = new ArrayList<Review>();
		for (User a : setOfAssociates) {
			for (Review r : reviews) {
				if (r.getUser().getId() == a.getId() && r.getWeek().getId() == currentWeek) {
					myBatchReviews.add(r);
				}
			}
		}

		System.out.println(myBatchReviews);
		modelMap.addAttribute("myBatchReviews", myBatchReviews);
		
		
		
		
		return "week";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getEmployeesGet(ModelMap modelMap, HttpSession session) {
		session.setAttribute("note", null);
		return "week";
	}
}