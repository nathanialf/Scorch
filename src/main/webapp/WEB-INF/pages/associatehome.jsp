<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.revature.bean.*, com.revature.dao.*"%>
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
			Batch b = bDAO.selectBatchByUser((User)session.getAttribute("user"));
		%>
		<h2><%= b.getName() %></h2><br>
		Trainer:<br>
		
		<%
			for(Week w : b.getWeeks()){
				out.println(w);
			}
		%>
		
	</div>
</body>