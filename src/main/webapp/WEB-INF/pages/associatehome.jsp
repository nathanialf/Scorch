<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<body>
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
		<form:form action="logout" method="GET">
		<button type="submit" class="btn btn-default btn-sm" style="float:right;margin-top:10px">
			<span class="glyphicon glyphicon-log-out"></span>Log-Out
		</button>
		</form:form>
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
</body>