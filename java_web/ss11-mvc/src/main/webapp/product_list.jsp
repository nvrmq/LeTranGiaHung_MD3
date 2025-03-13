<%--
  Created by IntelliJ IDEA.
  User: macbook
  Date: 12/3/25
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<h3>Searche product</h3>
<form action="/products" method="get">
  <input type="hidden" name="action" value="search">
  <input type="text" name="keyword" placeholder="Enter product name">
  <input type="hidden" name="action" value="search">
  <button type="submit">Search</button>
</form>
<a href="/products?action=new">Add product</a>
<h2>Products List</h2>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Price</th>
    <th>Description</th>
    <th>Manufacturer</th>
    <th>Action</th>
  </tr>
  <c:forEach var="product" items="${products}">
    <tr>
      <td>${product.id}</td>
      <td>${product.name}</td>
      <td>${product.price}</td>
      <td>${product.description}</td>
      <td>${product.manufacturer}</td>
      <td>
        <a href="/products?action=edit&id=${product.getId()}">Edit</a>
        <a href="/products?action=delete&id=${product.getId()}" onclick="confirm('Do you want to delete${product.getName()}')">Delete</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
