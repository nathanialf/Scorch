<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.revature.service.UserService"%>
<%@ page import="com.revature.bean.*, com.revature.dao.*, java.util.*"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<body>
	<div class="container">
		<%
			TopicDAO tDAO = new TopicDAOImpl();
			User user = (User)session.getAttribute("user");
			Batch b = null;
			
			for(Batch bat : user.getBatches()){
				b = bat;
				break;
			}
			

			BatchDAO bDAO = new BatchDAOImpl();
			b = bDAO.getBatchById(b.getId());
			if (b != null) {
				out.println("<h2>" + b.getName() + "<button type='button' class='btn btn-default' onclick='week' style='display:inline-block;'><span class='glyphicon glyphicon-chevron-right'></span> Current Week</button></h2><br>");
				
				for (Week w : b.getWeeks()) {
					List<Topic> t = tDAO.getAllTopicsByWeek(w);
					out.println("<div class='well container'>"+
					"<div class = 'col-sm-2' style='padding:0px;'>" + "<h2>Week: " + w.getNum() +"</h2></div>");
					
					out.println("<div class='col-sm-8' id='topic_container' style='padding:0px;'>");
					for (Topic top : t) {
						out.println("<div class='topic-sm'><br>" + top.getTopic() + "</div>");
					}
					out.println("</div>");
					out.println("<div class='col-sm-2' style='padding:0px;'>");
					out.println("<br><button class='btn btn-warning' onclick=\"window.open('week?wid=" + w.getId() +"', '_self');\">View</button>");
					out.println("</div></div>");
				}
			}
		%>
	</div>
</body>