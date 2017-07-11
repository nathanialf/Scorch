<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.revature.bean.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Revature TAP</title>
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
		<jsp:forward page="Login"/>
	</c:if>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Revature</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="#">Home</a></li>
			<li><a href="#">Page 1</a></li>
			<li><a href="#">Page 2</a></li>
			<li><a href="#">Page 3</a></li>
		</ul>
		<button type="button" class="btn btn-default btn-sm" style="float:right;margin-top:10px" onclick="window.open(\"Logout\", \"_self\");">
			<span class="glyphicon glyphicon-log-out"></span>Log-Out
		</button>
	</div>
	</nav>
	<div class="container">
		<div class="jumbotron">
			<h1>Welcome, ${user.getFirstname()} ${user.getLastname()}</h1>
			<p>You are a(n) ${user.getRole().getName()}</p>
			<p>This page is ready to be improved by other views.</p>
		</div>
		<p>This is some text.</p>
		<p>This is another text.</p>
	</div>
	<c:if test="${user.getRole().getName() == \"Associate\"}">
		<jsp:include page="associatehome.jsp"/>
	</c:if>
	<c:if test="${user.getRole().getName() == \"Trainer\"}">
		<jsp:include page="trainhome.jsp"/>
	</c:if>
	<c:if test="${user.getRole().getName() == \"Evaluator\"}">
		<jsp:include page="evalhome.jsp"/>
	</c:if>
	<c:if test="${user.getRole().getName() == \"Manager\"}">
		<jsp:include page="managerhome.jsp"/>
	</c:if>
</body>
</html>