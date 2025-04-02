<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: macbook
  Date: 2/4/25
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lending History - Library Management System</title>
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
    <h1 style="margin-bottom: 2rem;">Lending History</h1>

    <div class="search-container">
        <div class="search-group">
            <label for="studentSearch">Search by Student Name</label>
            <input type="text" id="studentSearch" class="search-input" placeholder="Enter student name...">
        </div>
        <div class="search-group">
            <label for="bookSearch">Search by Book Name</label>
            <input type="text" id="bookSearch" class="search-input" placeholder="Enter book name...">
        </div>
    </div>

    <div class="table-container">
        <table class="table" id="historyTable">
            <thead>
            <tr>
                <th>Book ID</th>
                <th>Book Name</th>
                <th>Author</th>
                <th>Student Name</th>
                <th>Student Class</th>
                <th>Lent Date</th>
                <th>Return Date</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody id="historyTableBody">
            <c:forEach var="item" items="${tickets}">
                <a>
                    <tr>
                        <td><c:out value="${item.getTicketId()}"/></td>
                        <td><c:out value="${item.getBookName()}"/></td>
                        <td><c:out value="${item.getAuthor()}"/></td>
                        <td><c:out value="${item.getStudentName()}"/></td>
                        <td><c:out value="${item.getStudentClass()}"/></td>
                        <td><c:out value="${item.getLentDate()}"/></td>
                        <td><c:out value="${item.getReturnDate()}"/></td>
                        <td><a href="/book?action=lend&id=${item.getTicketId()}">Return/a></td>
                    </tr>
                </a>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="history.js"></script>
</body>
</html>
