<%--
  Created by IntelliJ IDEA.
  User: macbook
  Date: 9/3/25
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: 'Times New Roman', Times, serif;
            text-align: center;
        }

        table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 12px;
        }

        th {
            font-weight: bold;
        }

        tr {
            border-bottom: 1px solid #ddd;
        }

        tr:last-child {
            border-bottom: none;
        }
    </style>
</head>
<body>
<h1>Danh sách khách hàng</h1>
<table>
    <tr>
        <th>Tên</th>
        <th>Ngày sinh</th>
        <th>Địa chỉ</th>
    </tr>
    <tr>
        <c:forEach items="${customers}" var="student" varStatus="status">
            <tr>
                <td>${student.getName()}</td>
                <td>${student.getDob()}</td>
                <td>${student.getAddress()}</td>
            </tr>
        </c:forEach>
    </tr>
</table>
</body>
</html>
