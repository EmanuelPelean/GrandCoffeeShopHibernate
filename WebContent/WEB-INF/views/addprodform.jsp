<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add to inventory</title>
</head>
<body>

<form action="addnewproduct" method="post">
	Product Name <input type="text" name="productName" placeholder="Enter the product name"> <br>
	Product Description <input type="text" name="description" placeholder="Enter the product info"> <br>
	Quantity <input type="number" name="quantity" placeholder="Enter quantity"> <br>
	Quantity <input type="number" name="price" min="1" step="any" placeholder="Enter price"> <br>
	<input type="submit" value="Add this item">

</form>

</body>
</html>