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
		<link rel="stylesheet" href="/css/showTask.css">
		<title><c:out value="${task.getTask()}"/></title>
	</head>
	<body>
		<main>
			<h1>Task: <c:out value="${task.getTask()}"/></h1>
			<ul>
				<li><span>Creator:</span> <c:out value="${task.getUser().getName()}"/></li>
				<li><span>Assignee:</span> <c:out value="${task.getAssigned_user().getName()}"/></li>
				<li><span>Priority:</span> <c:out value="${task.getPriority()}"/></li>
			</ul>
			<c:if test="${task.getUser().equals(user)}">
				<div class="option_container" >
					<a class="btn btn-primary" href="/tasks/<c:out value="${task.getId()}"/>/edit">Edit</a>
					<form:form action="/tasks/${task.getId()}/delete" method="post">
						<input type="hidden" name="_method" value="delete">
						<input class="btn btn-primary" type="submit" value="Delete" >
					</form:form>
				</div>
			</c:if>
			<c:if test="${task.getAssigned_user().equals(user)}">
				<form:form action="/tasks/${task.getId()}/complete" method="post">
					<input type="hidden" name="_method" value="delete">
					<input class="btn btn-success" type="submit" value="Complete" >
				</form:form>
			</c:if>
		</main>
	</body>
</html>