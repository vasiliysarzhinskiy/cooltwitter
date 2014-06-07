<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="label.title" /></title>
<style>
.layer_same_line {
	padding: 0px;
	margin: 30px;
	display: inline;
}

.layer_user_info {
	color: red;
	font-size: 30px;
	font-weight: bold;
}
.layer_fixed_length {
		width: 270px;
}
.layer_error {
		margin-left: 3%;
		color: red;
		font-style: italic;
		font-size: 15px;
}
</style>

<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script type="text/javascript" language="javascript">
 $(function()
  {
  $("#datepicker").datepicker(
  {
	dateFormat:"yy-mm-dd",
	changeYear: true
  });
  });
 </script>

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
					<div class="layer_user_info" style="display: inline;">${user.name} ${user.surname}</div>
				</div>
			<hr color="green" width="400px" size="5" align="left">



				<form method="post" action="saveuserinfo">
					<table>
						<tr >
							<td><label> <strong><spring:message
											code="label.firstName" /></strong></label></td>
							<td><input name="firstName" class="layer_fixed_length" value="${user.name}" /></td>
						</tr>
						<tr>
							<td><label> <strong><spring:message
											code="label.lastName" /></strong>
							</label></td>
							<td><input name="lastName" class="layer_fixed_length" value="${user.surname}" /></td>
						</tr>
						<tr>
							<td colspan="2">
							 	<hr color="green" width="400px" size="5" align="left">
							</td>
						<tr>
						<tr>
							<td><label> <strong><spring:message
											code="label.old_password" /></strong></label></td>
							<td><input type="password" name="oldPassword"
								class="layer_fixed_length" /></td>
						</tr>
						<tr>
							<td><label> <strong><spring:message
											code="label.new_password" /></strong></label></td>
							<td><input type="password" name="newPassword"
								class="layer_fixed_length" /></td>
						</tr>
						<tr>
							<td><label> <strong><spring:message
											code="label.confirm_new_password" /></strong></label></td>
							<td><input type="password" name="confirmNewPassword"
								class="layer_fixed_length" /></td>
						</tr>
						<tr>
							<td colspan="2">
							 	<hr color="green" width="400px" size="5" align="left">
							</td>
						<tr>
						
						<tr>
							<td><label> <strong><spring:message
											code="label.gender" /></strong></label></td>
							<td>
							
							<c:choose>
									<c:when test="${gender == 'male'}">
												<input type="radio" name="gender" checked="true" value="male"/>
									</c:when>
									<c:otherwise>
											<input type="radio" name="gender" value="male"/>
									</c:otherwise>
							</c:choose>
							Male
							<c:choose>
									<c:when test="${gender == 'female'}">
												<input type="radio" name="gender" checked="true" value="female"/>
									</c:when>
									<c:otherwise>
											<input type="radio" name="gender" value="female"/>
									</c:otherwise>
							</c:choose>
							Female
							
							
						 </td>
						</tr>
						<tr>
							<td><label> <strong><spring:message
											code="label.birthday" /></strong></label></td>
							<td><!--  <input type="text" name="birthday"
								class="layer_fixed_length" value="${birthday}" /> -->
								<input type="text" name="birthday" id="datepicker"
									class="layer_fixed_length" value="${birthday}"> 
								
								</td>
						</tr>
						<tr>
							<td><label> <strong><spring:message
											code="label.country" /></strong></label></td>
							<td><input type="text" name="country"
								class="layer_fixed_length" value="${country}" /></td>
						</tr>
						<tr>
							<td><label> <strong><spring:message
											code="label.city" /></strong></label></td>
							<td><input type="text" name="city"
								class="layer_fixed_length" value="${city}" /></td>
						</tr>
						<tr>
							<td><label> <strong><spring:message
											code="label.address" /></strong></label></td>
							<td><input type="text" name="address"
								class="layer_fixed_length" value="${address}" /></td>
						</tr>
						<tr>
							<td><label> <strong><spring:message
											code="label.phone" /></strong></label></td>
							<td><input type="text" name="phone"
								class="layer_fixed_length" value="${phone}" /></td>
						</tr>
						<tr>
							<td><label> <strong><spring:message
											code="label.status" /></strong></label></td>
							<td><input type="text" name="status"
								class="layer_fixed_length" value="${status}" /></td>
						</tr>
						<tr>
							<td><label> <strong><spring:message
											code="label.aboutYourself" /></strong></label></td>
							<td>
								<textarea  name="aboutYourself" rows="4"  class="layer_fixed_length" 
								 	style="text-align: 'left';">${aboutYourself}</textarea>
							</td>
						</tr>
						
						
						<tr>
							<td><input type="submit" value="save" /></td>
							<td><c:if test="${not empty err_register_dif_password}">
									<img src='images/error.png' width="17" height="17" />
									<label class="layer_error"> <spring:message
											code="label.err_register_dif_password" /></label>
								</c:if> <c:if test="${not empty err_register_exist_email}">
									<label class="layer_error"> <spring:message
											code="label.err_register_exist_email" /></label>
								</c:if> <c:if test="${not empty err_register_empty}">
									<label class="layer_error"> <spring:message
											code="label.err_register_empty" /></label>
								</c:if></td>
						</tr>
					</table>
				</form>
				
				<hr color="green" width="400px" size="5" align="left">
				 Photo
				<br>
				<form method="post" action="loadUserPhoto" enctype="multipart/form-data">
					<label for="file"></label>
					<input name="file" type="file" value="load">
					<input type="submit" value="save">
				</form>
				<c:if test="${not empty photo}">
					Photo:<img src="/photo/${user.id}"></img>
				</c:if>
				


			</td>

			<td width="20%" bgcolor="#6CC417" align="right" valign="top">
				<table>
					<tr>
						<td><a href="<c:url value="/logout"/>">logout</a>
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
							</form></td>
					</tr>
				</table>


			</td>
		</tr>
	</table>


</body>
</html>
