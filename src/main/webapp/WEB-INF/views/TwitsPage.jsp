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
					<div style="display: inline; "><a href="<c:url value="/my_twits"/>">my twits</a></div>
					<div style="display: inline;  padding-left: 30px;" ><a href="<c:url value="/new_twit" />">new twit</a></div>
					<div style="display: inline; padding-left: 30px;" ><a href="<c:url value="/friends_twits" />">friends twits</a></div> 
				</div>
				
				<c:if test="${not empty twitOption}">		
					<c:choose>
						<c:when test="${twitOption == 'new_twit'}">
							<div style="font-style: italic; font-weight: bold; color: red;">Creating Twit</div>
							<br>
							<form method="post" action="createTwit">
								<textarea rows="7" cols="50" name="createdTwit" style="text-align: left">
								</textarea>
								<br><input type="submit" value="save"/>
							</form>
							
						</c:when>
						<c:when test="${twitOption == 'friends_twits'}">
							<div style="font-style: italic; font-weight: bold; color: red;">Friends Twits</div>
							
							<form method="post" action="selectFriendForTwits">
								<select size="1" name="selectedFriendId">
									<c:forEach items="${friends}" var="friend">
										<option name="friend" name="friendId" value="${friend.id}">${friend.surname} ${friend.name}</option>
									</c:forEach>
									<input type="submit" style="margin-left: 20px" value="ok" />
								</select>
							</form>
							
							<c:if test="${not empty friend}">
								${friend.name} ${friend.surname}
								<c:forEach items="${friendTwits}" var="twit">
									
									<form method="post" action="editFriendTwit" style="margin-top: 5px">
									<table>
										<tr>
											<td valign="top">
												<textarea readonly="readonly" text-align: left" rows="3" cols="40" >${twit.text}</textarea>
											</td>
											<td valign="top">
												<br style="line-height: 3px;" >
												<a   href="<c:url value="/twitAddLike"/>">
													<img src="images/like_icon.png" width="15" height="15">
												</a>
												
											</td>
										</tr>
									</table>
									
								</form>
									
									
									
									
								</c:forEach>
							</c:if>
							
						</c:when>
						<c:otherwise>
							<div style="font-style: italic; font-weight: bold; color: red;">My Twits</div>
							<c:forEach items="${myTwits}" var="twit">
								<div style="color:orange; margin-top: 15px;" >
									creation date:  <joda:format value="${twit.dateTime}" pattern="dd-mm-yyyy"/>
								</div>  
								
								
									<table>
										<tr>
											<td valign="top">
												<textarea readonly="readonly" text-align: left" rows="3" cols="40" >${twit.text}</textarea>
											</td>
											<td valign="top">
												<a   href="<c:url value="/twitAddLike"/>">
													<img src="images/like_icon.png" width="15" height="15">
												</a>
												
											</td>
										</tr>
										<tr>
											<td>
												<div style="display: inline;">
												<form method="post" action="editTwit"  style="display: inline; width: 50px;">
													<input type="submit" value="edit" style="width: 70px;"/>
												</form>
												
												<form method="post" action="commentTwit" style="display: inline; width: 50px;">
													<input type="submit" value="comment" style="width: 70px;"/>
												</form>
												
												<form method="post" action="deleteTwit" style="display: inline;" >
													<input type="submit" value="delete" style="width: 70px;"/>
												</form>
												</div>
											</td>
											<td>
												
											</td>	
										</tr>
									</table>
									
								
								
							</c:forEach>
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
