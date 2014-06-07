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
	margin: 0px;
	display:inline; 
}
.layer_user_info {
	color: red;
	font-weight: bold;
	font-size: 30px;
}
</style>


</head>
<body>

	<spring:message code="label.home_page" var="labelHomePage" />
	<spring:message code="label.edit_info" var="labelEditInfo" />										
	<spring:message code="label.twits" var="labelTwits" />
	<spring:message code="label.friends" var="labelFriends" />
	<spring:message code="label.search_users" var="labelSearchUsers" />
	<spring:message code="label.news" var="labelNews" />
	
	<img src='images/main.jpg' width="100%" height="80" />
	<table>
		<tr>
			<td width="20%" bgcolor="#fdeaa4" valign="top">
				<div class="layer_user_info">
					${user.name}
					${user.surname}  
				</div>
				
				<br>
				<a href="<c:url value="/userhome"/>" style="color: blue;">${labelHomePage}</a>
				<br>
				<a href="<c:url value="/usereditinfo"/>" style="color: fuchsia;">${labelEditInfo}</a>
				<br>
				<a href="<c:url value="/twitspage"/>" style="color: blue;">${labelTwits}</a>	
				<br>
				<a href="<c:url value="/friends"/>" style="color: fuchsia;">${labelFriends}</a>	
				<br>
			    <a href="<c:url value="/search_users"/>" style="color: blue;">${labelSearchUsers}</a>
				<br>
				<a href="<c:url value="/news"/>" style="color: fuchsia;">${labelNews}</a>
			</td>
			
			<td width="60%" bgcolor="#fdeef4">
				<br>
				
					<c:choose>
						<c:when test="${empty showUserHome}">
							<div class="layer_user_info">	
								${user.name}
								${user.surname}  
							</div>
							<br><br>
							<div style="font-style: italic; font-weight: bold; color: green;">My Twits</div>
					
						</c:when>	
						<c:otherwise>
								<div class="layer_user_info">	
								${showUserHome.name}
								${showUserHome.surname}  
							</div>
							<br><br>
							<div style="font-style: italic; font-weight: bold; color: green;">${showUserHome.name} Twits</div>
						</c:otherwise>				
					</c:choose>
					
							
							
							<c:forEach items="${myTwits}" var="twit">
								<div style="color:orange; margin-top: 10px;">
									creation date:  <joda:format value="${twit.dateTime}" pattern="dd-mm-yyyy"/>
								</div>  
								
								<form method="post" action="editTwit" style="margin-top: 5px">
									<table>
										<tr>
											<td valign="top">
												<textarea readonly="readonly" text-align: left" rows="3" cols="40" >${twit.text}</textarea>
											</td>
											<td valign="top">
											
												<input type="submit" value="edit"/>
												<br style="line-height: 3px;" >
												<a   href="<c:url value="/twitAddLike"/>">
													<img src="images/like_icon.png" width="15" height="15">
												</a>
												
											</td>
										</tr>
									</table>
									
								</form>
								
							</c:forEach>
				
				
			</td>

			<td width="20%" bgcolor="#6CC417"  align="right" valign="top">
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
