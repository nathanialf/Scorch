<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<body>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Revature</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="login">Home</a></li>
			<li><a href="profile">Profile</a></li>
		</ul>
		
		<form:form action="logout" method="GET">
		<button type="submit" class="btn btn-default btn-sm" style="float:right;margin-top:10px">
			<span class="glyphicon glyphicon-log-out"></span>&nbsp;Log-Out
		</button>
		</form:form>
	</div>
	</nav>
	<div class="container">
		<div class="jumbotron">
			<h1>The Review Page</h1>
			<p>You are a(n) ${user.getRole().getName()}</p>
			<p>Let's do some reviewing...</p>
		</div>
		<p>This is some text.</p>
		<p>This is another text.</p>
	</div>
</body>