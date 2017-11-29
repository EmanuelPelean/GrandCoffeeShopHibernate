<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Grand Coffee Shop</title>
<link href="resources/test.css" rel="stylesheet" />
</head>
<body>
	<br>
	<div style="text-align: center">
		<h2>
			Grand Coffee Shop<br> <br>
		</h2>
		<h3>
			<a href="adminconsole">Admin Console</a><br> <a
				href="userregistration">Click here to register!</a>
		</h3>
		${ showUserInfo }
	</div>
	<form action="searchbyproductnameindex" method="get">
		<input type="text" name="productName"> <input type="submit"
			value="Search Products">
	</form>
	<table border="1">
		<c:forEach var="myVar" items="${message}">
			<tr>
				<td>Product: ${myVar.productName}</td>
				<td>Description: ${myVar.description}</td>
				<td>Quantity: ${myVar.quantity}</td>
				<td>Price: ${myVar.price}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>