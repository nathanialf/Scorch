<%@page import="com.revature.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.revature.bean.*, com.revature.dao.*,java.util.*"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<body>
	<div class="container">
		<div class="jumbotron">
			<h1>Welcome, ${user.getFirstname()} ${user.getLastname()}</h1>
			<p>You are an Associate</p>
			<p>Here you can view your current and previous weeks of training.</p>
		</div>
		<%
			BatchDAO bDAO = new BatchDAOImpl();
			TopicDAO tDAO = new TopicDAOImpl();
			Batch b = bDAO.selectBatchByUser((User) session.getAttribute("user"));
			UserService userService = new UserService();
			if (b != null) {
				out.println("<h2>" + b.getName() + "</h2><br><h3>Trainer: " + userService.getTrainer(b).getFirstname() + " " + userService.getTrainer(b).getLastname() + "</h3><br>");
				//out.println("<div class='well container'>");
				
				for (Week w : b.getWeeks()) {
					List<Topic> t = tDAO.getAllTopicsByWeek(w);
					out.println("<div class='well container'>"+
					"<div class = 'col-sm-5' style='padding:0px;'>" + "<h2>Week: " + w.getNum() +"</h2></div>");
					out.println("<div class='col-sm-5' style='padding:0px;'><strong>Topics:</strong><br>");
					for (Topic top : t) {
						out.println(top.getTopic() + "<br>");
					}
					out.println("</div>");
					out.println("<div class='col-sm-2' style='padding:0px;'>");
					out.println("<br><button class='btn btn-primary' onclick=\"window.open('week?wid=" + w.getId() +"', '_self');\">View</button>");
					out.println("</div></div>");
				}
			}
		%>

	</div>
</body>