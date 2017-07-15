<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.revature.service.UserService"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<body>
	<div class="container">
		<h2>Batches</h2>
		<table class="table table-hover">
			<tr>
				<th>Batch
				<th>Trainer
				<th>Week
				<th>
			<c:forEach var="batch" items="${batches}">
				<tr>
					<td>${batch.getName()}
					<td>${userService.getTrainer(batch).getFirstname()} ${userService.getTrainer(batch).getLastname()}
					<td>${batch.getWeeks().size()}
					<td><form:form method="post" action="batchindividual">
										<button class="btn btn-warning" type="submit">View</button>
										<input type="hidden" name="id" value="${batch.getId()}">
									</form:form>
			</c:forEach>
		</table>
	</div>
</body>