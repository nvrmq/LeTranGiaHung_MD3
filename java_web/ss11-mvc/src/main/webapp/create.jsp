<%--
  Created by IntelliJ IDEA.
  User: macbook
  Date: 12/3/25
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
    <style>
        form {
            display: flex;
            flex-direction: column;
            width: 100%;
        }
        .contents{
            display: flex;
            align-content: center;
            justify-content: center;
        }
        .main{
            margin-top: 17%;
            width: 50%;
            box-shadow: black 0px 0px 5px;
            padding: 10px;
            border-radius: 10px;
        }
        #submit{
            width: 15%;
        }

    </style>
</head>
<body>
<div class="contents">
    <div class="main">
        <h2>Add new product</h2>
        <form method="post" action="/products">
            <input type="hidden" name="action" value="new">
            <label for="name">Product's name</label>
            <input type="text" id="name" name="name">
            <label for="name">Price</label>
            <input type="number" id="price" name="price">
            <label for="description">Description</label>
            <textarea id="description" name="description"></textarea>
            <label for="manufacturer">Manufacturer</label>
            <input type="text" id="manufacturer" name="manufacturer">
            <br>

            <input type="submit" value="Add product" id="submit">
        </form>
        <br>
        <a href="/products">Return</a>
    </div>
</div>
</body>
</html>
