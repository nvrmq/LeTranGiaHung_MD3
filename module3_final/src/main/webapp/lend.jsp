<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: macbook
  Date: 2/4/25
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lend Book - Library Management System</title>
    <style>
        /* Reset and base styles */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Arial', sans-serif;
        }

        body {
            background-color: #f5f5f5;
            color: #333;
            line-height: 1.6;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        /* Navigation */
        .nav {
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            padding: 1rem;
            margin-bottom: 2rem;
        }

        .nav-links {
            display: flex;
            gap: 20px;
            list-style: none;
        }

        .nav-links a {
            text-decoration: none;
            color: #333;
            font-weight: 500;
        }

        /* Search bars */
        .search-container {
            display: flex;
            gap: 20px;
            margin-bottom: 2rem;
        }

        .search-group {
            flex: 1;
        }

        .search-group label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: 500;
        }

        .search-input {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
        }

        /* Table styles */
        .table-container {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            overflow: hidden;
        }

        .table {
            width: 100%;
            border-collapse: collapse;
        }

        .table th,
        .table td {
            padding: 1rem;
            text-align: left;
            border-bottom: 1px solid #eee;
        }

        .table th {
            background-color: #f8f9fa;
            font-weight: 600;
        }

        /* Buttons */
        .btn {
            padding: 0.75rem 1.5rem;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: 500;
            transition: background-color 0.2s;
        }

        .btn-primary {
            background-color: #007bff;
            color: white;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .btn-danger {
            background-color: #dc3545;
            color: white;
        }

        .btn-danger:hover {
            background-color: #c82333;
        }

        /* Form styles */
        .form-container {
            max-width: 600px;
            margin: 0 auto;
            background: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: 500;
        }

        .form-control {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
        }

        /* Toast notification */
        .toast {
            position: fixed;
            top: 20px;
            right: 20px;
            padding: 1rem 2rem;
            border-radius: 4px;
            color: white;
            animation: slideIn 0.3s ease-in-out;
            z-index: 1000;
        }

        .toast-success {
            background-color: #28a745;
        }

        .toast-error {
            background-color: #dc3545;
        }

        @keyframes slideIn {
            from {
                transform: translateX(100%);
                opacity: 0;
            }
            to {
                transform: translateX(0);
                opacity: 1;
            }
        }
    </style>
</head>
<body>
<nav class="nav">
    <ul class="nav-links">
        <li><a href="index.html">Books</a></li>
        <li><a href="history.html">Lending History</a></li>
    </ul>
</nav>

<div class="container">
    <div class="form-container">
        <h1 style="margin-bottom: 2rem;">Lend Book</h1>

        <form id="lendForm" method="post" action="/book?action=add">
            <div class="form-group">
                <label for="bookId">Book ID</label>
                <input type="text" name="bookId" value="${book.getId()}" id="bookId" class="form-control" readonly placeholder="${book.getId()}">
            </div>

            <div class="form-group">
                <label for="bookName">Book Name</label>
                <input type="text" name="bookName" id="bookName" class="form-control" readonly placeholder="${book.getName()}">
            </div>

            <div class="form-group">
                <label for="studentSelect">Select Student</label>
                <select name="studentSelect" id="studentSelect" class="form-control" required>
                    <option value="">Choose a student...</option>
                    <c:forEach var="student" items="${students}">
                        <option value="${student.getId()}">${student.getName()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label>Lending Date</label>
                <p id="lendDate" class="form-control" value="<%= new SimpleDateFormat("yyyy/MM/dd").format(new Date()) %>">${localTime}</p>
            </div>

            <div class="form-group">
                <label for="returnDate">Return Date</label>
                <input type="text" class="form-control" id="returnDate" name="returnDate" required placeholder="yyyy/MM/dd">
            </div>

            <div style="display: flex; gap: 1rem;">
                <button type="submit" class="btn btn-primary" style="flex: 1;">Lend Book</button>
                <button type="button" class="btn btn-danger" style="flex: 1;" onclick="window.location.href='/book'">Cancel</button>
            </div>
        </form>
    </div>
</div>

<script src="lend.js"></script>
</body>
<script>
    const returnDateInput = document.getElementById('returnDate');
    const today = new Date();
    let dd = today.getDate();
    let mm = today.getMonth() + 1; // January is 0!
    let yyyy = today.getFullYear();

    if (dd < 10) {
        dd = '0' + dd;
    }

    if (mm < 10) {
        mm = '0' + mm;
    }

    const todayFormatted = yyyy + '-' + mm + '-' + dd;
    returnDateInput.setAttribute('min', todayFormatted);
</script>
</html>
