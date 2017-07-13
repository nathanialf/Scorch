package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.bean.Batch;
import com.revature.bean.Review;
import com.revature.bean.User;
import com.revature.dao.BatchDAO;
import com.revature.dao.BatchDAOImpl;
import com.revature.dao.ReviewDAO;
import com.revature.dao.ReviewDAOImpl;
import com.revature.dao.WeekDAO;
import com.revature.dao.WeekDAOImpl;
import com.revature.service.BatchService;

@Controller
@RequestMapping(value = "/week")
public class WeekController {

	@RequestMapping(method = RequestMethod.GET)
	public String getPage(HttpSession session, ModelMap modelMap) {
		ReviewDAO rDao = new ReviewDAOImpl();

		List<Review> reviews = rDao.getAllReviews();

		System.out.println(reviews);
		
		
		User current = (User) session.getAttribute("user");
		BatchDAO bDao = new BatchDAOImpl();
		//Verifying...
		BatchService bs = new BatchService();
		int myBatchId = bs.getBatchIdOfCurrentAssociate(current);
		Batch myBatch = bDao.getBatchById(myBatchId);
		
		System.out.println(bDao.selectBatchByUser(current));
		System.out.println(myBatch);
	

		return "week";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String getPostPage(@Valid Review review, HttpSession session) {
//		WeekDAO wDao = new WeekDAOImpl();
		ReviewDAO rDao = new ReviewDAOImpl();
//		BatchService bs = new BatchService();
//		BatchDAO bDao = new BatchDAOImpl();
		User current = (User) session.getAttribute("user");
//		int myBatchId = bs.getBatchIdOfCurrentAssociate(current);
//		Batch myBatch = bDao.getBatchById(myBatchId);
//		System.out.println(myBatch.getWeeks());
//		
//		List<Review> reviews = rDao.getAllReviews();
//		for(Review r : reviews){
//			r.getUser();
//		}
		

		review.setUser(current);
		// review.setWeek(week);
		
		
		rDao.insertReview(review);
		

		return "week";
	}
}
