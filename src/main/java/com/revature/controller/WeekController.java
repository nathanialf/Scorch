package com.revature.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

@Controller
@RequestMapping(value = "/week")
public class WeekController {

	@RequestMapping(method = RequestMethod.GET)
	public String getPage(HttpSession session, ModelMap modelMap, HttpServletRequest request) {
		ReviewDAO rDao = new ReviewDAOImpl();
		// Get My batch -> Which has a the set of Associates in my Batch
		BatchDAO bDao = new BatchDAOImpl();
		BatchService bs = new BatchService();
		User current = (User) session.getAttribute("user");
		int myBatchId = bs.getBatchIdOfCurrentAssociate(current);
		Batch myBatch = bDao.getBatchById(myBatchId);

		// Get current week from a parameter
		WeekDAO wDao = new WeekDAOImpl();
		int weekId = Integer.parseInt(request.getParameter("wid"));
		Week week = wDao.getWeekById(weekId);
		int currentWeek = week.getNum();
		TopicDAO tDAO = new TopicDAOImpl();
		List<Topic> t = tDAO.getAllTopicsByWeek(week);
		week.setTopics(t);
		session.setAttribute("weekId", currentWeek);
		modelMap.addAttribute("week", week);
		
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

	@RequestMapping(method = RequestMethod.POST)
	public String getPostPage(@Valid Review review, HttpSession session, ModelMap modelMap) {
		WeekDAO wDao = new WeekDAOImpl();
		ReviewDAO rDao = new ReviewDAOImpl();
		BatchService bs = new BatchService();
		BatchDAO bDao = new BatchDAOImpl();
		User current = (User) session.getAttribute("user");
		int myBatchId = bs.getBatchIdOfCurrentAssociate(current);
		Batch myBatch = bDao.getBatchById(myBatchId);

		// Get current week from a parameter
		int currentWeek = (int)session.getAttribute("weekId");
		review.setUser(current);
		review.setWeek(wDao.getWeekById(currentWeek));
		Week week = wDao.getWeekById(currentWeek);
		TopicDAO tDAO = new TopicDAOImpl();
		List<Topic> t = tDAO.getAllTopicsByWeek(week);
		week.setTopics(t);
		session.setAttribute("weekId", currentWeek);
		modelMap.addAttribute("week", week);

		List<Review> reviews = rDao.getAllReviews();
		int updated = 0;
		postUpdateExit: for (Review r : reviews) {
			if (r.getUser() != null) {
				if (r.getUser().getId() == current.getId() && r.getWeek().getId() == currentWeek) {
					r.setReview(review.getReview());
					rDao.updateReview(r);
					updated = 1;
					break postUpdateExit;
				}
			}
		}
		if (updated == 0) {
			rDao.insertReview(review);
		}

		// Update to include the newly added review!
		reviews = rDao.getAllReviews();

		// Get the associates in the batch
		Set<User> setOfAssociates = myBatch.getAssociates();

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

}
