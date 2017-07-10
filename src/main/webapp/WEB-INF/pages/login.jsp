<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TAP Login</title>
<!--  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />-->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<c:if test="${errorMessage != null}">
		<div class="alert alert-danger">${errorMessage}</div>
	</c:if>
	<h1>Login</h1>
	<form:form action="login" method="POST" commandName="user">
		Username: <form:input path="username"/>
		<form:errors path="username" cssClass="alert alert-danger" element="div"/>
		<br>
		Password: <form:password path="password"/>
		<form:errors path="username" cssClass="alert alert-danger" element="div"/>
		<br>
		<input type="submit" value="Login">
	</form:form>
</body>
</html>