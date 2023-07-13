<%--
  Created by IntelliJ IDEA.
  User: STARS
  Date: 2023/6/14
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>报表管理</title>
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
<h1>报表管理</h1>
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>报表名称</th>
    <th>操作</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="report" items="${reports}">
    <tr>
      <td>${report.id}</td>
      <td>${report.reportName}</td>
      <td>
        <form method="get" action="${pageContext.request.contextPath}/report">
          <input type="hidden" name="id" value="${report.id}">
          <button type="submit">查看</button>
        </form>
        <form method="post" action="${pageContext.request.contextPath}/report">
          <input type="hidden" name="id" value="${report.id}">
          <input type="hidden" name="action" value="delete">
          <button type="submit">删除</button>
        </form>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<h2>添加报表</h2>
<form method="post" action="${pageContext.request.contextPath}/report">
  <label style="display: inline-block;">报表名称：<input type="text" name="report_name"></label>
  <label style="display: inline-block;">报表数据：<textarea name="report_data"></textarea></label>
  <input type="hidden" name="action" value="add">
  <button type="submit">添加</button>
</form>
<br>
<a href="homePage.jsp">返回主页</a>
</body>
</html>
