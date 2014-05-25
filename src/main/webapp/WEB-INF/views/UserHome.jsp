<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="label.title" /></title>
<style>
.layer_same_line {
	padding: 0px;
	margin:30px;
	display:inline; 
}
.layer_user_info {
	color: red;
	font-size: larger;
	font-weight: bold;
}
</style>


</head>
<body>
	
	
	<img src='images/main.jpg' width="1330" height="80" />
	<table>
		<tr>
			<td width="20%" bgcolor="#fdeaa4">
				<div class="layer_user_info">
					${user.name}
					${user.surname}  
				</div>
				
				NEWS!
				<spring:message code="label.title_news" />
				<br>
				<a href="<c:url value="/userhome"/>">My Home Page</a>
				<br>
			    <a href="<c:url value="/user/viewall"/>"> View All Users</a>
				<br>
				<a href="<c:url value="/usereditinfo"/>">Edit info</a>
				<br>
				<a href="<c:url value="/twitspage"/>">Twits</a>	
				<br>
				<a href="<c:url value="/friends"/>">Friends</a>	
			</td>
			
			<td width="60%" bgcolor="#fdeef4">
				TWIT!
			</td>

			<td width="20%" bgcolor="#6CC417" align="right">
				<table>
					<tr>
						<td>
							<a href="<c:url value="/logout"/>">logout</a>			
							<form class="layer_same_line" method="post" action="language">
									<select size="1" name="language">
										<c:forEach items="${language}" var="language">
										<c:choose>
											<c:when test="${cookie.localeCookie.value == language.key}">
												<option selected="true" value="${language.key}">${language.value}</option>
											</c:when>
											<c:otherwise>
												<option value="${language.key}">${language.value}</option>
											</c:otherwise>
										</c:choose>

									</c:forEach>
										
									</select> <input type="submit" value="upply">
							</form>
						</td>
					</tr>
				</table>

				
			</td>
		</tr>
	</table>

</body>
</html>
