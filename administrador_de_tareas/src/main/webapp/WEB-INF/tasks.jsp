<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- para errores de renderizado en rutas PUT -->
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="/css/task.css">
		
		<title>Task Manager</title>
	</head>
	<body>
		<header>
			<h1>Welcome, <c:out value="${user.getName()}"/></h1>
			<div class=filters_container>
				<form:form action="/order/1" method="post">
					<input class="a" type="submit" value="Priority High - Low" >
				</form:form>
				<form:form action="/order/2" method="post">
					<input class="a" type="submit" value="Priority Low - High" >
				</form:form>
			</div>
			<form action="/logout" method="post">
				<input class="a" type="submit" value="Logout">
			</form>
		</header>
		<main>
			<table class="table table-dark table-striped">
				<thead>
					<tr>
						<th scope="col">Task</th>
						<th scope="col">Creator</th>
						<th scope="col">Assignee</th>
						<th scope="col">Priority</th>
					</tr>
				</thead>
				<tbody id="tBody">
					<c:forEach var="task" items="${tasks}">
						<tr>
						    <td><a href="/tasks/<c:out value="${task.getId()}"/>"><c:out value="${task.getTask()}"/></a></td>
						    <td><c:out value="${task.getUser().getName()}"/></td>
						    <td><c:out value="${task.getAssigned_user().getName()}"/></td>
							<td><c:out value="${task.getPriority()}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</main>
		<footer>
			<a class="btn btn-primary" href="/tasks/new">Create Task</a>
		</footer>
	</body>
</html>