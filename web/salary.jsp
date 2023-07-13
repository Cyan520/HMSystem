<%--
  Created by IntelliJ IDEA.
  User: STARS
  Date: 2023/6/14
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>工资管理</title>
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
<h1>工资管理</h1>
<form method="post" action="${pageContext.request.contextPath}/salary">
  <label style="display: inline-block;">输入员工姓名：<input type="text" name="employee_name"></label>
  <input type="hidden" name="action" value="search">
<%--  <label>开始日期：<input type="date" name="start_date"></label>--%>
<%--  <label>结束日期：<input type="date" name="end_date"></label>--%>
  <button type="submit">查询员工薪酬</button>
</form>
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>员工ID</th>
    <th>员工姓名</th>
    <th>发薪日期</th>
    <th>工资金额</th>
    <th>操作</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="salary" items="${salaries}">
    <tr>
      <td>${salary.id}</td>
      <td>${salary.employeeId}</td>
      <td>${salary.employeeName}</td>
      <td>${salary.salaryDate}</td>
      <td>${salary.salaryAmount}</td>
      <td>
        <form method="post" action="${pageContext.request.contextPath}/salary">
          <input type="hidden" name="id" value="${salary.id}">
          <input type="hidden" name="action" value="delete">
          <button type="submit">删除</button>
        </form>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<h2>添加工资记录</h2>
<form method="post" action="${pageContext.request.contextPath}/salary">
  <label style="display: inline-block;">员工姓名：<input type="text" name="employee_name"></label>
  <label style="display: inline-block;">工资金额：<input type="text" name="salary_amount"></label>
  <input type="hidden" name="action" value="add">
  <button type="submit">添加</button>
</form>
<br>
<a href="homePage.jsp">返回主页</a>
</body>
</html>
