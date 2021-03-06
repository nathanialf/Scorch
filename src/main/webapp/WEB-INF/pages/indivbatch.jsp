<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.revature.bean.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Revature TAP | Employees</title>
<!--  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<c:if test="${sessionScope.user == null}">
		<jsp:forward page="Login" />
	</c:if>
	<jsp:include page="../../static/pages/navbar.jsp" />
	<div class="container">
		<div class="jumbotron">
			<h1>${batch.getName()}</h1>
			<br>
			<h3>Trainer: ${trainerForBatchPage.getFirstname()} ${trainerForBatchPage.getLastname()}</h3>
		</div>
		<div class="col-sm-6">
			<div style="text-align: center;">
				<h4>Associates</h4>
			</div>
			<br>
			<table class="table table-hover">
				<tr>
					<th>Name
					<th>Username
					<th> 
					<c:forEach var="assoc"
							items="${batch.getAssociates()}">
							<c:if test="${assoc.getActive() == 1}">
							
							<tr>
								<td>${assoc.getFirstname()} ${assoc.getLastname()}
								<td>${assoc.getUsername()}
								<td><c:if test="${user.getId() == trainerForBatchPage.getId()}">
									<button class="btn btn-warning" onclick="window.open('employeedrop?id=${assoc.getId()}', '_self');">Drop</button>
								</c:if>
							</c:if>
						</c:forEach>
			</table>
		</div>

		<div class="col-sm-6">
			<div style="text-align: center;">
				<h4>Week</h4>
			</div>
			<br>
			<table class="table table-hover">
				<tr>
					<th>Week
					<th>Topics
					<th><c:forEach var="week" items="${batch.getWeeks()}">
							<tr>
								<td>Week ${week.getNum()}
								<td>${not empty week.getTopics().size()?week.getTopics().size():'0'} topics
								<td><button class="btn btn-warning"
										onclick="window.open('week?wid=${week.getId()}', '_self');">View</button>
						</c:forEach>
			</table>
			<c:if test="${user.getId() == trainerForBatchPage.getId()}">
				<form method="post" action="weekadd">
					<button type="submit" class="btn btn-warning btn-block">
						<span class="glyphicon glyphicon-plus"></span> Add Week
					</button>
				</form>
			</c:if>
		</div>
	</div>

</body>
</html>