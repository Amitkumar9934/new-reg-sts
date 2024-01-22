<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> List of All Product here</title>
</head>
<body>
	<table>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Mobile</th>
			<th>Delete</th>
			<th>Update</th>
		</tr>
		
		<c:forEach var ="res" items="${table}">
			<tr>
				<td>${res.firstName}</td>
				<td>${res.lastName}</td>
				<td>${res.email}</td>
				<td>${res.mobile}</td>
				<td><a href = "delete?uid=${res.id}">delete</a></td>
				<td><a href="getRegistrationById?uid=${res.id}">update</a></td>
			</tr>
				
		</c:forEach>
	</table>

</body>
</html>