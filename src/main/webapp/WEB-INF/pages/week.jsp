<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.revature.bean.*, com.revature.dao.*, java.util.*"%>

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
<link rel="stylesheet" href="static/css/circle.css">

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

	<div id="week-id" style="display: none;">${week.getId()}</div>
	<!-- 
	<div class="container" id="topic_container">
		<c:forEach var="topic" items="${week.getTopics()}">
			<c:choose>
				<c:when test="${user.getId() == trainer.getId()}">
					<div class="topic">
						<c:forEach var="tr" items="${ratings}">
							<c:set var="avg" value="1"/>
							<c:set var="num" value="0"/>
							<c:if test="${topic.getId() == tr.getTopic().getId()}">
								<c:set var="avg" value="${avg + tr.getRating()}" />
								<c:set var="num" value="${num + 1}" />
							</c:if>
						</c:forEach>
						${avg / num}
				</c:when>
				<c:otherwise>
					<div class="topic" id="${topic.getId()}">
						<c:forEach var="tr" items="${ratings}">
							<c:if test="${topic.getId() == tr.getTopic().getId()}">
								<c:if test="${tr.getUser().getId() == user.getId()}">
							${tr.getRating()}
						</c:if>
							</c:if>
						</c:forEach>
				</c:otherwise>
			</c:choose>
			<br>${topic.getTopic()}</div>
	</c:forEach>

	<c:if test="${user.getId() == trainer.getId()}">
		<div class="topic" id="add">
			<br> <span class="glyphicon glyphicon-plus"></span>
		</div>
	</c:if>
	</div>
-->
	<div class="container" id="topic_container">
		<c:forEach var="topic" items="${week.getTopics()}">
			<c:set var="hit" value="0" />
			
			<c:choose>
				<c:when
					test="${user.getId() == trainer.getId() || user.getRole().getId() == 2 || user.getRole().getId() == 1}">
					<c:set var="avg" value="1" />
					<c:set var="num" value="0" />
					<c:forEach var="tr" items="${ratings}">
						<c:if test="${topic.getId() == tr.getTopic().getId()}">
							<c:set var="avg" value="${avg + tr.getRating()}" />
							<c:set var="num" value="${num + 1}" />
						</c:if>
					</c:forEach>
					<div
						class="c100 p<fmt:formatNumber value="${avg/num}" maxFractionDigits="0"/> orange topic">
						<span>${topic.getTopic()}</span>
						<div class="slice">
							<div class="bar"></div>
							<div class="fill"></div>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="tr" items="${ratings}">
						<c:if
							test="${topic.getId() == tr.getTopic().getId() && tr.getUser().getId() == user.getId()}">
							<c:set var="foo" value="${(tr.getRating() / 5) * 100}" />
							<div
								class="c100 p<fmt:formatNumber value="${foo}" maxFractionDigits="0"/> orange topic"
								id="${topic.getId()}">
								<span>${topic.getTopic()}</span>
								<div class="slice">
									<div class="bar"></div>
									<div class="fill"></div>
								</div>
								<c:set var="hit" value="1" />
							</div>
						</c:if>
					</c:forEach>

					<c:forEach var="tr" items="${ratings}">
						<c:if test="${hit == 0}">
							<div class="c100 p0 orange topic" id="${topic.getId()}">
								<span>${topic.getTopic()}</span>
								<div class="slice">
									<div class="bar"></div>
									<div class="fill"></div>
								</div>
							</div>
							<c:set var="hit" value="1" />
						</c:if>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:if test="${user.getId() == trainer.getId()}">
			<div class="c100 p100 orange topic" id="add">
				<span class="glyphicon glyphicon-plus"></span>
				<div class="slice">
					<div class="bar"></div>
					<div class="fill"></div>
				</div>
			</div>
		</c:if>
	</div>

	<div id="hidden-form"></div>

	<%-- Create Review from Associate --%>
	<div class="container well">
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
				<br>
				<button type="submit" class="btn btn-warning">Submit</button>
			</form>

		</c:if>
		<c:if test="${sessionScope.user.getRole().getName() == \"Evaluator\"}">
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
				<br> <input type="hidden" name="batchid"
					value="${weekBatch.getId()}">
				<button type="submit" class="btn btn-warning">Submit</button>
			</form>

		</c:if>
		<br>
	</div>

	<c:if
		test="${user.getRole().getId() == 1 || user.getRole().getId() == 2}"></c:if>
	<%-- All batch reviews --%>
	<c:forEach items="${ requestScope.qcReviews }" var="rev">
		<div class="container">
			<div class="well">
				<h4>QC</h4>
				<c:out value="${ rev.getReview() }"></c:out>
			</div>
		</div>
	</c:forEach>

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