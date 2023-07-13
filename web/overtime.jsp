<%--
  Created by IntelliJ IDEA.
  User: STARS
  Date: 2023/6/14
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>加班管理</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h1 {
            color: #333;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        form {
            display: inline-block;
            margin-right: 5px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 5px 10px;
            border: none;
            cursor: pointer;
            border-radius: 3px;
        }

        button:hover {
            background-color: #45a049;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"] {
            padding: 5px;
            border-radius: 3px;
            border: 1px solid #ccc;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>
<body>
<h1>加班管理</h1>
    <form method="post" action="${pageContext.request.contextPath}/overtime">
        <label style="display: inline-block;">员工姓名：<input type="text" name="employee_name"></label>
        <label style="display: inline-block;">开始日期：<input type="date" name="start_time"></label>
        <label style="display: inline-block;">结束日期：<input type="date" name="end_time"></label>
        <input type="hidden" name="action" value="search">
        <button type="submit">查询</button>
    </form>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>员工ID</th>
            <th>员工姓名</th>
            <th>加班日期</th>
            <th>加班时长</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="overtime" items="${overtimes}">
            <tr>
                <td>${overtime.id}</td>
                <td>${overtime.employeeId}</td>
                <td>${overtime.employeeName}</td>
                <td>${overtime.overtimeDate}</td>
                <td>${overtime.overtimeHours}</td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/overtime">
                        <input type="hidden" name="id" value="${overtime.id}">
                        <input type="hidden" name="action" value="delete">
                        <button type="submit">删除</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h2>添加加班记录</h2>
    <form method="post" action="${pageContext.request.contextPath}/overtime">
        <label style="display: inline-block;">员工姓名：<input type="text" name="employee_name"></label>
        <label style="display: inline-block;">加班时长：<input type="text" name="overtime_hours"></label>
        <input type="hidden" name="action" value="add">
        <button type="submit">添加</button>
    </form>
<br>
<form method="post" action="${pageContext.request.contextPath}/overtime">
    <input type="hidden" name="action" value="summary">
    <button type="submit">加班信息汇总</button>
</form>
<br>
<a href="homePage.jsp">返回主页</a>
</body>
</html>
