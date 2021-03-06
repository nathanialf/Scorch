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
<title>Batch</title>
<!--  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		$('#allAssoc').dblclick(function() {
			return !$('#allAssoc option:selected').appendTo('#selectedAssoc');
		});
		$('#btnMoveRight').click(function() {
			return !$('#allAssoc option:selected').appendTo('#selectedAssoc');
		});

		$('#selectedAssoc').dblclick(function() {
			return !$('#selectedAssoc option:selected').appendTo('#allAssoc');
		});
		$('#btnMoveLeft').click(function() {
			return !$('#selectedAssoc option:selected').appendTo('#allAssoc');
		});
	});
</script>
</head>
<body>


	<c:if test="${sessionScope.user == null}">
		<jsp:forward page="Login" />
	</c:if>

	<c:if
		test="${	sessionScope.user.getRole().getName() == \"Trainer\" || 
					sessionScope.user.getRole().getName() == \"Evaluator\" ||
					sessionScope.user.getRole().getName() == \"Manager\" }">

		<jsp:include page="../../static/pages/navbar.jsp" />

		<div class="container">
			<div class="jumbotron">
				<h1>Batches</h1>

				<br> <br>
				<c:if
					test="${ sessionScope.user.getRole().getName() == \"Trainer\" }">

					<form:form method="get" action="batchnew">
						<button type="submit" class="btn btn-default btn-sm">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;New Batch
						</button>
					</form:form>
				</c:if>
			</div>
			<table class="table table-hover">
				<tr>
					<th>Batch Name
					<th>Trainer
					<th>Size
					<th>Start Date
					<th><c:forEach items="${ sessionScope.batches }" var="b">
							<tr>
								<td>${ b.getName() }
								<td>
								<c:forEach items="${ sessionScope.stringyTrainers }" var="t">
								<c:if test="${ b.getId() == t[0] }">
								${ t[1] }
								</c:if>
								</c:forEach>
								<td>${ b.getAssociates().size() }
								<td>${ b.getStartDate() }
								<td><form:form method="post" action="batchindividual">
										<button class="btn btn-warning" type="submit">View</button>
										<input type="hidden" name="id" value="${b.getId()}">
									</form:form>
						</c:forEach>
			</table>
		</div>


	</c:if>

</body>
</html>