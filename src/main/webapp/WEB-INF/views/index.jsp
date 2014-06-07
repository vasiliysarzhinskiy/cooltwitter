<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Twitter</title>
<style>
.layer_left_part {
	width: 80%;
}

.layer_login_fixed_length {
	width: 250px;
}

.layer_same_line {
	padding: 0px;
	margin: 30px;
	display: inline;
}
</style>
</head>
<body>
	<img src='images/main.jpg' width="100%" height="80" />
	<table>
		<tr>
			<td width="80%" bgcolor="#fdeef4">NEWS! 
				
				<br>
				<img src='images/twitImage.jpg' width="400px" height="400px" />
			</td>

			<td width="20%" bgcolor="#6CC417" valign="top">
				<table>
					<tr>
						<td><a href="<c:url value="/registration"/>"> Register </a>
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

				<form method="post" action="login">
					<table>
						<tr>
							<td><label> <strong><spring:message
											code="label.email" /></strong></label></td>
							<td><input name="email" class="layer_login_fixed_length"
								required /></td>
						</tr>
						<tr>
							<td><label> <strong><spring:message
											code="label.password" /></strong></label></td>
							<td><input type="password" name="password"
								class="layer_login_fixed_length" required /></td>
						</tr>

						<tr>
							<td><input type="submit" value="login" /></td>
							<td><input type="checkbox" name="remember" checked="checked">Remember
								me?</td>
						</tr>
						<tr>
							<td colspan="2"><c:if test="${not empty err_login}">
									<img src='images/error.png' width="17" height="17" />
									<label class="layer_error"> <spring:message
											code="label.err_login" /></label>
								</c:if> <c:if test="${not empty err_register_empty}">
									<img src='images/error.png' width="17" height="17" />
									<label class="layer_error"> <spring:message
											code="label.err_register_empty" /></label>
								</c:if></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>


</body>
</html>
