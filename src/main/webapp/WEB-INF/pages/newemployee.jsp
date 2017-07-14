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
		<jsp:forward page="../Login" />
	</c:if>
	<jsp:include page="../../static/pages/navbar.jsp"/>
	<div class="container">
		<div class="jumbotron">
			<h2>New Employee</h2>
		</div>

		<form:form method="POST" action="employeeadd" commandName="user">
			<div class="input-group col-md-6">
				<span class="input-group-addon"><i
					class="glyphicon glyphicon-user"></i></span> <input type="text"
					name="firstname" class="form-control" placeholder="First Name"
					required>
			</div>
			<br>
			<div class="input-group col-md-6">
				<span class="input-group-addon"><i
					class="glyphicon glyphicon-user"></i></span> <input type="text"
					name="lastname" class="form-control" placeholder="Last Name"
					required>
			</div>
			<br>
			<div class="input-group col-md-6">
				<span class="input-group-addon"><i
					class="glyphicon glyphicon-user"></i></span> <input type="text"
					name="username" class="form-control" placeholder="Username"
					required>
			</div><br>
			<div class="form-group col-md-6">
			<select name="role" class="form-control"
					id="sel1">
					<c:forEach var="role" items="${roles}">
						<option value="${role.getId()}">${role.getName()}</option>
					</c:forEach>
				</select>
			</div><br><br><br><br>
			<input type="submit" class="btn btn-warning" value="Submit">
		</form:form>

	</div>
</body>
</html>