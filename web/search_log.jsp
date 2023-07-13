<%--
  Created by IntelliJ IDEA.
  User: STARS
  Date: 2023/6/21
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>查询结果</title>
</head>
<body>
<h1>查询结果</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>用户名</th>
        <th>事件时间</th>
        <th>事件名称</th>
        <th>事件详情</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="eventLog" items="${eventLogs}">
        <tr>
            <td>${eventLog.id}</td>
            <td>${eventLog.username}</td>
            <td>${eventLog.eventTime}</td>
            <td>${eventLog.eventName}</td>
            <td>${eventLog.eventDetail}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="event_log">返回</a>
</body>
</html>
