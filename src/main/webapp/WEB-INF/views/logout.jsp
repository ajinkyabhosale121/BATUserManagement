<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true" %>

<!DOCTYPE html>

<html lang="en">
<head>

	<title>logout</title>

</head>
<body>

	<%
	session.invalidate();
	String url = request.getContextPath() + "/";
	response.sendRedirect(url);
	%>
	
			
			<br><h2>${pageContext.request.contextPath}</h2><br>
			

</body>
</html>
