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
		<link rel="stylesheet" href="/css/newTask.css">
		<title>Edit Task</title>
	</head>
	<body>
		<main>
			<h1>Edit <c:out value="${task_name}"/></h1>
			<form:form action="/tasks/${model_task.getId()}/edit" method="post" modelAttribute="model_task">
				<input type="hidden" name="_method" value="put">
				
				<form:input type="hidden" path="user" value="${user.getId()}"/>
				<form:input type="hidden" path="id" value="${model_task.getId()}"/>
				
				<form:errors class="text-danger" path="task"></form:errors>
				<div>
					<form:label path="task">Task</form:label>
					<form:input class="form-control" path="task"/>
				</div>
				
				<form:errors class="text-danger" path="assigned_user"></form:errors>
				<div>
					<form:label path="assigned_user">Assignee</form:label>
					<form:select class="form-select"  path="assigned_user">
						<c:forEach var="user" items="${users}">
							<form:option value="${user.getId()}">${user.getName()}</form:option>
						</c:forEach>
					</form:select>
				</div>
				
				<form:errors class="text-danger" path="priority"></form:errors>
				<div>
					<form:label path="priority">Priority</form:label>
					<form:select class="form-select"  path="priority">
						<form:option value="high">High</form:option>
						<form:option value="medium">Medium</form:option>
						<form:option value="low">Low</form:option>
					</form:select>
				</div>
				
				<input class="btn btn-primary" type="submit" value="Edit">
				
			</form:form>
		</main>
	</body>
</html>