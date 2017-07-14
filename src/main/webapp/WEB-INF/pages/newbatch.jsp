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
	
		<jsp:include page="../../static/pages/navbar.jsp" />
	<div class="container">

		<div class="jumbotron">
			<h1>Batches</h1>
		</div>
		<c:if
			test="${	sessionScope.user.getRole().getName() == \"Trainer\" || 
					sessionScope.user.getRole().getName() == \"Evaluator\" ||
					sessionScope.user.getRole().getName() == \"Manager\" }">
			<form method="POST">
				<div class="container-fluid">
					<button type="submit" class="btn btn-warning">Create Batch</button>

					<div class="form-group col-md-3">
						<input type="text" class="form-control" name="name">
					</div>
				</div>
				<table border="0" cellpadding="10" cellspacing="10">
					<tr>
						<th align="left" valign="top">Select:</th>
						<th>&nbsp;</th>
						<th align="left" valign="top">Selected:</th>
					</tr>
					<tr>
						<td align="left" valign="top"><select id="allAssoc"
							name="allAssoc" multiple class="form-control"
							style="width: 200px;" size="10">
								<c:forEach items="${ sessionScope.associates }" var="u">
									<option value="${ u.getId() }"><c:out
											value="${ u.getFirstname() }" />
										<c:out value="${ u.getLastname() }" /></option>
								</c:forEach>
						</select></td>

						<td align="center" valign="middle">
							<button id="btnMoveRight" name="btnMoveRight"
								class="btn btn-default">
								<span class="glyphicon glyphicon-chevron-right"></span>
							</button>
							<button id="btnMoveLeft" name="btnMoveLeft"
								class="btn btn-default">
								<span class="glyphicon glyphicon-chevron-left"></span>
							</button>
						</td>

						<td align="left" valign="top"><select id="selectedAssoc"
							name="selectedAssoc" multiple class="form-control"
							style="width: 200px;" size="10" required></select></td>
					</tr>
				</table>

			</form>
		</c:if>
	</div>
</body>