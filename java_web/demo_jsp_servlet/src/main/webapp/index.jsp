<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<div class="content">
    <h2>Product discount calculator</h2>
    <br/>
    <form action="/abc" method="post">
        <label>Product Description:</label>
        <input type="text" name="description" required><br><br>

        <label>List Price:</label>
        <input type="number" name="listPrice" required><br><br>

        <label>Discount Percent:</label>
        <input type="number" name="discountPercent" required><br><br>

        <button type="submit">Calculate Discount</button>
    </form>
</div>
</body>
</html>