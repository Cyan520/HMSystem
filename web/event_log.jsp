<%--
  Created by IntelliJ IDEA.
  User: STARS
  Date: 2023/6/14
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>事件日志管理</title>
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
<h1>事件日志管理</h1>
<form method="post" action="${pageContext.request.contextPath}/event_log">
  <label style="display: inline-block;">用户名：<input type="text" name="username"></label>
  <label style="display: inline-block;">开始时间：<input type="date" name="start_time" ></label>
  <label style="display: inline-block;">结束时间：<input type="date" name="end_time" ></label>
  <label style="display: inline-block;">事件名：<input type="text" name="event_name"></label>
  <input type="hidden" name="action" value="search">
  <button type="submit">查询</button>
</form>

<table>
    <thead>
    <tr>
      <th>ID</th>
      <th>用户名</th>
      <th>事件时间</th>
      <th>事件名称</th>
      <th>事件详情</th>
      <th>操作</th>
    </tr>    </thead>
    <tbody>
    <c:forEach var="eventLog" items="${eventLogs}">
      <tr>
        <td>${eventLog.id}</td>
        <td>${eventLog.username}</td>
        <td>${eventLog.eventTime}</td>
        <td>${eventLog.eventName}</td>
        <td>${eventLog.eventDetail}</td>
        <td>
          <form method="post" action="${pageContext.request.contextPath}/event_log">
            <input type="hidden" name="id" value="${eventLog.id}">
            <input type="hidden" name="action" value="delete">
            <button type="submit">删除</button>
          </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <h2>添加事件日志信息</h2>
  <form method="post" action="${pageContext.request.contextPath}/event_log">
    <label style="display: inline-block;">用户名：<input type="text" name="username"></label>
    <label style="display: inline-block;">事件名称：<input type="text" name="event_name"></label>
    <label style="display: inline-block;">事件详情：<input type="text" name="event_detail"></label>
    <input type="hidden" name="action" value="add">
    <button type="submit">添加</button>
  </form>
<br>
<a href="homePage.jsp">返回主页</a>
</body>
</html>
