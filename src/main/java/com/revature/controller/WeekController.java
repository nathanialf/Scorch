package com.revature.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
		// Get My batch -> Which has a the set of Associates in my Batch
		BatchDAO bDao = new BatchDAOImpl();
		BatchService bs = new BatchService();
		User current = (User) session.getAttribute("user");
		int myBatchId = bs.getBatchIdOfCurrentAssociate(current);
		Batch myBatch = bDao.getBatchById(myBatchId);
		
		// Calculate the current week
		int currentWeek = ((int) getDateDiff(myBatch.getStartDate(), new Date(System.currentTimeMillis()),
				TimeUnit.DAYS) / 7) + 1;
		
		// Get the associates in the batch
		Set<User> setOfAssociates = myBatch.getAssociates();
		
		
		// Get only the batch reviews from all reviews
		List<Review> reviews = rDao.getAllReviews();
		
		List<Review> myBatchReviews = new ArrayList<Review>();
		for (User a : setOfAssociates) {
			for (Review r : reviews) {
				if(r.getUser().getId() == a.getId() 
						&& r.getWeek().getId() == currentWeek ){
					myBatchReviews.add(r);
				}
			}
		}

		session.setAttribute("myBatchReviews", myBatchReviews);


		return "week";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String getPostPage(@Valid Review review, HttpSession session) {
		WeekDAO wDao = new WeekDAOImpl();
		ReviewDAO rDao = new ReviewDAOImpl();
		BatchService bs = new BatchService();
		BatchDAO bDao = new BatchDAOImpl();
		User current = (User) session.getAttribute("user");
		int myBatchId = bs.getBatchIdOfCurrentAssociate(current);
		Batch myBatch = bDao.getBatchById(myBatchId);

		// Calculate the current week
		int currentWeek = ((int) getDateDiff(myBatch.getStartDate(), new Date(System.currentTimeMillis()),
				TimeUnit.DAYS) / 7) + 1;

		review.setUser(current);
		review.setWeek(wDao.getWeekById(currentWeek));

		
		
		List<Review> reviews = rDao.getAllReviews();
		int updated = 0;
		postUpdateExit: for (Review r : reviews) {
			if (r.getUser() != null) {
				if (r.getUser().getId() == current.getId()) {
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
		
		//After updating/inserting the review, must update the session attribute as well
		List<Review> myBatchReviews = (List<Review>)session.getAttribute("myBatchReviews");
		for(Review r : myBatchReviews){
			if(r.getId() == current.getId() ){
				r = rDao.getReviewById(current.getId());
			}
		}
		session.setAttribute("myBatchReviews", myBatchReviews);

		return "week";
	}

	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
}
