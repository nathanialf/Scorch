<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TAP Login</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="static/css/style.css" />
</head>
<body>
	<div class="container-fluid">
		<div class="col-sm-4 col-sm-offset-4 login-card container-fluid" style="">
			<img src="static/img/revlogo.png" class="col-sm-12"
				style="background-color: #EFEFEF; border-bottom: 1px solid lightgray;">
			<br> <br>
			<c:if test="${errorMessage != null}">
				<div class="alert alert-danger custom-alert">${errorMessage}</div>
			</c:if>
			<div class="container-fluid">
				<form:form action="login" method="POST" commandName="user">
		Username:<br>
					<div class="input-group col-md-12">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-user"></i></span>
						<form:input path="username" />
					</div>
					<form:errors path="username" cssClass="alert alert-danger"
						element="div" />
					<br>
					<br>
		Password:<br>
					<div class="input-group col-md-12">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-user"></i></span>
						<form:password path="password" />
					</div>
					<form:errors path="username" cssClass="alert alert-danger"
						element="div" />
					<br>
					<input type="submit" class="login-button" value="Login">
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>