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

	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Revature</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="../login">Home</a></li>
			<li class="active"><a href="#">View Employees</a></li>
			<li><a href="../employee/profile">Profile</a></li>
		</ul>
		<form:form method="GET" action="logout">
		<button type="submit" class="btn btn-default btn-sm" style="float:right;margin-top:10px">
			<span class="glyphicon glyphicon-log-out"></span>&nbsp;Log-Out
		</button>
		</form:form>
	</div>
	</nav>
	<div class="container">
		<div class="jumbotron">
			<h2>${batch.getName()}</h2><br>
			<h3>Trainer Name</h3>
		</div>
		<div class="col-sm-6">
		<div style="text-align:center;"><h4>Associates</h4></div><br>
		<table class="table table-hover">
			<tr><th>Name
			<th>Username
			<c:forEach var="assoc" items="${batch.getAssociates()}">
				<tr><td>${assoc.getFirstname()} ${assoc.getLastname()}
				<td>${assoc.getUsername()}
			</c:forEach>
		</table>
		</div>

		<div class="col-sm-6">
		<div style="text-align:center;"><h4>Week</h4></div><br>
		<table class="table table-hover">
			<tr><th>Week
			<th>sdjfk
			<c:forEach var="week" items="${batch.getWeeks()}">
				<tr><td>${week}
				<td>
			</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>