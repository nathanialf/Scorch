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
<title>The Weekly View</title>
<!--  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="static/css/style.css" />
<script src="static/js/scorch.js"></script>
</head>


<body>
	<jsp:include page="../../static/pages/navbar.jsp" />

	<%-- Topics with combined rating go here --%>
	<div class="container">
		<div class="jumbotron">
			<h1>${weekBatch.getName()}</h1>
			<p>Week ${week.getNum()}</p>
		</div>
	</div>
	
	<div id="week-id" style="display:none;">${week.getId()}</div>

	<div class="container" id="topic_container">
		<c:forEach var="topic" items="${week.getTopics()}">
			<c:choose>
			<c:when test="${user.getId() == trainer.getId()}">
				<div class="topic">
				
			</c:when>
			<c:otherwise>
				<div class="topic" id="${topic.getId()}">
			</c:otherwise>
			</c:choose>
			<br>${topic.getTopic()}
	</div>
	
	</c:forEach>
	<c:if test="${user.getId() == trainer.getId()}">
		<div class="topic" id="add">
			<br>
			<span class="glyphicon glyphicon-plus"></span>
		</div>
	</c:if>
	</div>
	
	<div id="hidden-form">
	</div>

	<%-- Create Review from Associate --%>
	<div class="container">
		<c:if test="${param.submitted}">
			<c:if test="${empty param.review}" var="noReview" />

			<c:if test="${not (noReview)}">
				<c:set value="${param.review}" var="review" scope="request" />
			</c:if>
		</c:if>

		<c:if test="${sessionScope.user.getRole().getName() == \"Associate\"}">
			<form method="post">
				<input type="hidden" name="submitted" value="true" />

				<P>
					Enter your review:<br>
					<textarea name="review" class="col-sm-12"></textarea>
					<br />
					<c:if test="${noReview}">
						<br>
						<br>
						<div class="alert alert-danger">Write out a review before
							submitting</div>
					</c:if>
				</p>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>

		</c:if>
		<br>
	</div>

	<%-- All batch reviews --%>
	<c:forEach items="${ requestScope.myBatchReviews }" var="rev">
		<div class="container">
			<div class="well">
				<c:out value="${ rev.getReview() }"></c:out>
			</div>
		</div>
	</c:forEach>


</body>

</html>