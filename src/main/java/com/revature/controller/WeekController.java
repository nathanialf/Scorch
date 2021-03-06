package com.revature.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.Batch;
import com.revature.bean.Review;
import com.revature.bean.Topic;
import com.revature.bean.TopicRating;
import com.revature.bean.User;
import com.revature.bean.Week;
import com.revature.dao.BatchDAO;
import com.revature.dao.BatchDAOImpl;
import com.revature.dao.ReviewDAO;
import com.revature.dao.ReviewDAOImpl;
import com.revature.dao.TopicDAO;
import com.revature.dao.TopicDAOImpl;
import com.revature.dao.TopicRatingDAO;
import com.revature.dao.TopicRatingDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.dao.WeekDAO;
import com.revature.dao.WeekDAOImpl;
import com.revature.service.BatchService;
import com.revature.service.UserService;

@Controller
@RequestMapping(value = "/week")
public class WeekController {
	
	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPage(HttpSession session, ModelMap modelMap, HttpServletRequest request) {
		session.setAttribute("note", null);
		ReviewDAO rDao = new ReviewDAOImpl();
		// Get My batch -> Which has a the set of Associates in my Batch
		BatchDAO bDao = new BatchDAOImpl();
		BatchService bs = new BatchService();
		User current = (User) session.getAttribute("user");
		
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

		Batch myBatch = null;
		if (current.getRole().getName().equals("Associate")) {
			int myBatchId = bs.getBatchIdOfCurrentAssociate(current);
			myBatch = bDao.getBatchById(myBatchId);
		}
		else{
			//get batch by week
			myBatch = week.getBatch();
		}
		System.out.println(myBatch);

		TopicRatingDAO trDAO = new TopicRatingDAOImpl();
		List<TopicRating> trs = trDAO.getAllTopicRatings();
		System.out.println("RATINGS: " + trs.size());
		modelMap.addAttribute("ratings", trs);
System.out.println(userService.getTrainer(myBatch));
		modelMap.addAttribute("trainer", userService.getTrainer(myBatch));
		modelMap.addAttribute("weekBatch", myBatch);

		// Get the associates in the batch
		Set<User> setOfAssociates = myBatch.getAssociates();

		UserDAO uDAO = new UserDAOImpl();
		
		// Get only the batch reviews from all reviews
		List<Review> reviews = rDao.getAllReviews();
		List<Review> myBatchReviews = new ArrayList<Review>();
		List<User> management = uDAO.getManagement();
		List<Review> qcReviews = new ArrayList<Review>();
		
		for (User a : setOfAssociates) {
			for (Review r : reviews) {
				if (r.getUser().getId() == a.getId() && r.getWeek().getId() == week.getId()) {
					myBatchReviews.add(r);
				}
			}
		}
		
		for(User m : management){
			for(Review r : reviews){
				if(r.getUser().getId() == m.getId() && r.getWeek().getId() == week.getId()){
					qcReviews.add(r);
				}
			}
		}

		System.out.println(myBatchReviews);
		modelMap.addAttribute("myBatchReviews", myBatchReviews);
		modelMap.addAttribute("qcReviews", qcReviews);

		return "week";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String getPostPage(@Valid Review review, HttpSession session, ModelMap modelMap, HttpServletRequest request) {
		session.setAttribute("note", null);
		WeekDAO wDao = new WeekDAOImpl();
		ReviewDAO rDao = new ReviewDAOImpl();
		BatchService bs = new BatchService();
		BatchDAO bDao = new BatchDAOImpl();
		User current = (User) session.getAttribute("user");
		int myBatchId = 0;
		if(current.getRole().getName().equals("Associate"))
			myBatchId = bs.getBatchIdOfCurrentAssociate(current);
		else{
			myBatchId = Integer.parseInt(request.getParameter("batchid"));
		}
		Batch myBatch = bDao.getBatchById(myBatchId);

		// Get current week from a parameter
		int currentWeek = (int) session.getAttribute("weekId");
		review.setUser(current);
		review.setWeek(wDao.getWeekById(currentWeek));
		Week week = wDao.getWeekById(currentWeek);
		TopicDAO tDAO = new TopicDAOImpl();
		List<Topic> t = tDAO.getAllTopicsByWeek(week);
		week.setTopics(t);
		session.setAttribute("weekId", currentWeek);
		modelMap.addAttribute("week", week);

		List<Review> reviews = rDao.getAllReviews();

		// Do not do HQL unless there is content in the review
		if (review.getReview().length() != 0) {
			int updated = 0;
			postUpdateExit: for (Review r : reviews) {
				if (r.getUser() != null) {
					if (r.getUser().getId() == current.getId() && r.getWeek().getId() == currentWeek) {
						r.setReview(review.getReview());
						rDao.updateReview(r);
						updated = 1;
						session.setAttribute("note", "You have updated your review for this week");
						break postUpdateExit;
					}
				}
			}
			if (updated == 0) {
				rDao.insertReview(review);
				session.setAttribute("note", "You have added a review for this week");
			}
		}
		// Update to include the newly added review!
		reviews = rDao.getAllReviews();

		TopicRatingDAO trDAO = new TopicRatingDAOImpl();
		List<TopicRating> trs = trDAO.getAllTopicRatings();
		System.out.println("RATINGS: " + trs.size());
		modelMap.addAttribute("ratings", trs);

		modelMap.addAttribute("weekBatch", myBatch);
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
