<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
	font-size: 30px;
	font-weight: bold;
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

			
			<td width="60%" bgcolor="#fdeef4" valign="top">
				<div>
					<div style="display: inline;  padding-left: 30px;" ><a href="<c:url value="/search_users" />">search users</a></div>
					<div style="display: inline;  padding-left: 30px;" ><a href="<c:url value="/full_search_users" />">full search users</a></div>
				</div>
				
				<c:if test="${not empty searchOption}">		
					<c:choose>
						<c:when test="${searchOption == 'search_users'}">
							<div style="font-style: italic; font-weight: bold; color: red;">Search users</div>
							<form method="post" action="search_user" style="float: right;">
								<c:choose>
									<c:when test="${not empty searchUserInfo}">
										<input  name="searchUserInfo" value="${searchUserInfo}"/>
									</c:when>
									<c:otherwise>
										<input  name="searchUserInfo" value=""/>
									</c:otherwise>
								</c:choose>
								<input type="submit" value="search" />
							</form>
							
							<br>
							<table bgcolor="#CCFFFF" cellspacing="10">
								<thead >
									<tr>
										<th>Surname Name</th>
										<th>Email</th>
										<th></th>
									</tr>
								</thead>
								<c:forEach items="${users}" var="currentUser">
									<tr>
										<td colspan="3">
									 		<hr color="green" width="550px" size="2" align="left">
										</td>
									</tr>
									<tr>
										<td>${currentUser.surname} ${currentUser.name} </td>
										<td>${currentUser.email} </td>
										<td>
											<form method="post" action="show_user" style="float: left;">
												<input type="hidden" name="userId" value="${currentUser.id}"/>
												<input type="submit" value="show"/> 
											</form>
											<c:set var="isFriend" value="false"/>
											
											<c:forEach items="${myFriends}" var="friend">
													<c:if test="${currentUser.id == friend.id}">
														<c:set var="isFriend" value="true"/>
													</c:if>	
											</c:forEach>	
											
											<c:choose>
												<c:when test="${isFriend == 'true'}">
													<img src="images/success.png" style="float: left; margin-left: 80px;"> 
												</c:when>
												<c:when test="${currentUser.id == user.id}">
													<img src="images/owner.jpg" style="float:left; margin-left: 80px;" >
												</c:when>
												<c:otherwise>
													<form method="post" action="addToFriend" style="margin-left: 20px; float: right;">
															<input type="hidden" name="userId" value="${currentUser.id}"/>
															<input type="submit" value="add to friend"/> 
													</form>
												</c:otherwise>									
											</c:choose>
											
																					
										</td>
									</tr>
								</c:forEach>
							
							</table>
							
						</c:when>
						
						<c:otherwise>
							<div style="font-style: italic; font-weight: bold; color: red;">full search users</div>
							<form method="post" action="full_search_user" style="float: right;">
								<c:choose>
									<c:when test="${not empty searchUserInfo}">
										<input  name="searchUserInfo" value="${searchUserInfo}"/>
									</c:when>
									<c:otherwise>
										<input  name="searchUserInfo" value=""/>
									</c:otherwise>
								</c:choose>
								<input type="submit" value="search" />
							</form>
							
							<br>
							<table bgcolor="#CCFFFF" cellspacing="10">
								<thead >
									<tr>
										<th>Surname Name</th>
										<th>Email</th>
										<th></th>
									</tr>
								</thead>
								<c:forEach items="${users}" var="currentUser">
									<tr>
										<td colspan="3">
									 		<hr color="green" width="550px" size="2" align="left">
										</td>
									</tr>
									<tr>
										<td>${currentUser.surname} ${currentUser.name} </td>
										<td>${currentUser.email} </td>
										<td>
											<form method="post" action="show_user" style="float: left;">
												<input type="hidden" name="userId" value="${currentUser.id}"/>
												<input type="submit" value="show"/> 
											</form>
											<c:set var="isFriend" value="false"/>
											
											<c:forEach items="${myFriends}" var="friend">
													<c:if test="${currentUser.id == friend.id}">
														<c:set var="isFriend" value="true"/>
													</c:if>	
											</c:forEach>	
											
											<c:choose>
												<c:when test="${isFriend == 'true'}">
													<img src="images/success.png" style="float: left; margin-left: 80px;"> 
												</c:when>
												<c:when test="${currentUser.id == user.id}">
													<img src="images/owner.jpg" style="float:left; margin-left: 80px;" >
												</c:when>
												<c:otherwise>
													<form method="post" action="addToFriendFullSearch" style="margin-left: 20px; float: right;">
															<input type="hidden" name="userId" value="${currentUser.id}"/>
															<input type="submit" value="add to friend"/> 
													</form>
												</c:otherwise>									
											</c:choose>
											
																					
										</td>
									</tr>
								</c:forEach>
							
							</table>
							
						</c:otherwise>
					</c:choose>
				</c:if>
			</td>

			<td width="20%" bgcolor="#6CC417" align="right" valign="top">
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
