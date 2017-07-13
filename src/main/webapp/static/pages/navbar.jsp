<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Revature</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="login">Home</a></li>
			<c:if test="${sessionScope.user.getRole().getName() == \"Trainer\"}">
				<li><a href="batch">Batches</a></li>
			</c:if>
			<c:if test="${sessionScope.user.getRole().getName() == \"Evaluator\"}">
				<li><a href="batch">Batches</a></li>
			</c:if>
			<c:if test="${sessionScope.user.getRole().getName() == \"Manager\"}">
				<li><a href="employee">View Employees</a></li>
			</c:if>
			<li><a href="employeeprofile">Profile</a></li>
		</ul>
		<form:form method="GET" action="logout">
			<button type="submit" class="btn btn-default btn-sm" style="float:right;margin-top:10px">
				<span class="glyphicon glyphicon-log-out"></span>&nbsp;Log-Out
			</button>
		</form:form>
	</div>
</nav>