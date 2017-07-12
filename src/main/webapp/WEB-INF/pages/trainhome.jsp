<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Revature</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="login">Home</a></li>
			<li><a href="batch">Batches</a></li>
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
			<h1>Welcome, ${user.getFirstname()} ${user.getLastname()}</h1>
			<p>You are a(n) ${user.getRole().getName()}</p>
			<p>Here you can view the current and previous weeks of training</p>
		</div>
	</div>
</body>