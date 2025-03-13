<%--
  Created by IntelliJ IDEA.
  User: macbook
  Date: 13/3/25
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit product</title>

</head>
<body>
<form method="post" action="/products?action=edit">
  <input type="hidden" name="id" value="${product.id}">
  <label for="name">Product's name</label>
  <input type="text" id="name" name="name" value="${product.getName()}">
    <label for="price">Price</label>
    <input type="number" id="price" name="price" value="${product.getPrice()}">
    <label for="description">Description</label>
    <input type="text" id="description" name="description" value="${product.getDescription()}">
    <label for="manufacturer">Manufacturer</label>
    <input type="text" id="manufacturer" name="manufacturer" value="${product.getManufacturer()}">

    <input type="submit" value="Update">
</form>
</body>
</html>
