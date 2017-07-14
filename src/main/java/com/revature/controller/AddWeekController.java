package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.revature.dao.WeekDAO;
import com.revature.dao.WeekDAOImpl;

@Controller
@RequestMapping(value = "/weekadd")
public class AddWeekController {


	@RequestMapping(method = RequestMethod.POST)
	public String addWeek(ModelMap modelMap, HttpSession session) {
		WeekDAO wDao = new WeekDAOImpl();
		ReviewDAO rDao = new ReviewDAOImpl();
		BatchDAO bDao = new BatchDAOImpl();
		//Get batch from the session
		Batch b = (Batch)session.getAttribute("batch");
		//Add a new week to the weeks
		List<Week> wks = b.getWeeks();
		Week wk = new Week(b, wks.size()+1, null);
		
		wks.add(wk);
		b.setWeeks(wks);

		wDao.insertWeek(wk);

		//Update the batch
		session.setAttribute("batch", b);
		return "indivbatch";
	}
}