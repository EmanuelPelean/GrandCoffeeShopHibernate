<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Grand Coffee Shop Admin Console</title>
<link href="resources/test.css" rel="stylesheet" />

</head>
<body>


	<a href="getnewprod">Add Product</a>
	
	
	<form action="searchbyproductname" method="get">
		<input type="text" name="productName">
		<input type="submit" value="Search">
	</form>
	
	<table border="1">
		<c:forEach var="myVar" items="${message}">
			<tr>
				<td>${myVar.productName}</td>
				<td>${myVar.description}</td>
				<td>${myVar.quantity}</td>
				<td>${myVar.price}</td>
				<td><a href="delete?id=${myVar.productName}"> Delete </a></td>
				<td><a href="update?id=${myVar.productName}"> Update </a></td>

			</tr>

		</c:forEach>

	</table>


</body>
</html>