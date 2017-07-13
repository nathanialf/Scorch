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
</head>


<body>
	<jsp:include page="../../static/pages/navbar.jsp"/>
	
	<%-- Topics with combined rating go here --%>
	<div class="container">
		<div class="jumbotron">
			<h1>The Week Page</h1>
			<p>Let's do some reviewing...</p>
		</div>
		<p>This is some text.</p>
		<p>This is another text.</p>
	</div>

	<%-- Create Review from Associate --%>
	<div class="container">
		<div class="jumbotron">
			<h1>Comments for the week</h1>

			<c:if test="${param.submitted}">
				<c:if test="${empty param.review}" var="noReview" />

				<c:if
					test="${not (noReview)}">
					<c:set value="${param.review}" var="review" scope="request" />
				</c:if>
			</c:if>

			<form method="post">
				<input type="hidden" name="submitted" value="true" />

				<P>
					Enter your review: 
					<textarea name="review"></textarea><br />
					<c:if test="${noReview}">
						<small><font color="red"> Note: Write out a review first </font></small>
					</c:if>
				</p>

				<input type="submit" value="Submit" />
			</form>
		</div>
	</div>
	
	<%-- All reviews --%>

</body>

</html>