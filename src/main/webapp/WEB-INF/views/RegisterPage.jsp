<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="label.title" /></title>
<style>
	.layer_margin_left {
		margin-left: 3%;
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
</head>
<body>
	<img src='images/main.jpg' width="100%" height="120" />
	<br><h1> Register </h1>
    <form method="post" action="register">
        <table  class="layer_margin_left" >
            <tr>
            	<td>
            		<label> <strong><spring:message code="label.email" /></strong></label>
            	</td>
            	<td>
            		<input name="email"  class="layer_fixed_length" required />
            	</td>		  
            </tr>
             <tr>
            	<td>
            		<label> <strong><spring:message code="label.password" /></strong></label>
            	</td>
            	<td>
            		<input type="password" name="password" class="layer_fixed_length" required />
            	</td>		  
            </tr>
             <tr>
            	<td>
            		<label> <strong><spring:message code="label.confirmpassword"  /></strong></label>
            	</td>
            	<td>
            		<input type="password" name="confirmpassword" class="layer_fixed_length" required />
            	</td>		  
            </tr>
            <tr >
                <td>
                	<label> <strong><spring:message code="label.firstName" /></strong></label>
                </td>
                <td>
                	<input name="firstName" class="layer_fixed_length" />
                </td>
            </tr>
            <tr>
                <td>
                	<label> <strong><spring:message code="label.lastName" /></strong> </label>
                </td>
                <td>
                	<input name="lastName" class="layer_fixed_length" />
                </td>
            </tr>
            <tr>
                <td>
                	<input type="submit" value="register" />
                </td>
                <td>
                	<c:if test="${not empty err_register_dif_password}">    
                		<img src='images/error.png' width="17" height="17" />
						<label class="layer_error"> <spring:message code="label.err_register_dif_password"  /></label>
					</c:if>

					<c:if test="${not empty err_register_exist_email}">
						<label class="layer_error"> <spring:message code="label.err_register_exist_email"  /></label>
					</c:if>
					
					<c:if test="${not empty err_register_empty}">
						<label class="layer_error"> <spring:message code="label.err_register_empty"  /></label>
					</c:if>
                </td>
            </tr>
        </table>
    </form>

	<br>
	

	

</body>
</html>
