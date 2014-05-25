<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View All Users!</title>
</head>
<body>
	<div style = "clear: right; float: right; text-align: right;">
		<a href="<c:url value="/logout"/>">logout</a>
	 </div>
	<h2 align="left">User: ${user} </h2>
	<h3>${labelRegister}</h3> 
	<table bgcolor="#CCFFFF">
		<thead>
			<tr>
				<th>ID</th>
				<th>SURNAME</th>
				<th>NAME</th>
				<th>EMAIL</th> 
			</tr>
		</thead>
		<c:forEach var="user" items="${users}" >
			<tr>
				<td>${user.id}</td>
				<td>${user.surname}</td>
				<td>${user.name}</td>
				<td>${user.email}</td>
				<td>
					<form method="post" action="viewuser">
						<input type="hidden" name="userid" value="${user.id}" />
						<input type="submit" value="Show" />
					</form>
				</td>	
			</tr>
		</c:forEach>
	</table>

</body>
</html>