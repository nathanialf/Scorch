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
	<jsp:include page="../../static/pages/navbar.jsp"/>
	<div class="container">
		<div class="jumbotron">
			<h2>Employees</h2>
			<br>
			<br>
		<form:form method="post" action="employee/new">
		<button type="submit" class="btn btn-default btn-sm">
			<span class="glyphicon glyphicon-plus"></span>&nbsp;New Employee
		</button>
		</form:form>
		</div>

		<table class="table table-hover">
			<thead>
				<tr>
					<th>Name
					<th>Username
					<th>Role
					<th>Active
				</tr>
			</thead>
			<tbody>
				<c:forEach var="emp" items="${employees}">
					<tr>
						<td>${emp.getFirstname()}&nbsp;${emp.getLastname()}
						<td>${emp.getUsername()}
						<td>${emp.getRole().getName()}
						<td>${emp.getActive()}
				</c:forEach>
			</tbody>
		</table>

	</div>

</body>
</html>