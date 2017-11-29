<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="updateproduct" method="post">
	<input type="hidden" name="id" value="${productName }">
	
	Product Description <input type="text" name="description" placeholder="Enter the product info"> <br>
	Quantity <input type="number" name="quantity" placeholder="Enter quantity"> <br>
	Price <input type="number" name="price" min="1" step="any" placeholder="Enter price"> <br>
	<input type="submit" value="Add this item">
	
</form>


</body>
</html>