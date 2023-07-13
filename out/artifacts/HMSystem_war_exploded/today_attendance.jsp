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
    <title>今日考勤统计</title>
</head>
<body>
<h1>今日考勤统计</h1>
<table>
    <thead>
    <tr>
        <th>员工姓名</th>
        <th>考勤状态</th>
        <th>考勤时间</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="todayAttendance" items="${TodayAttendances}">
        <tr>
            <td>${todayAttendance.employeeName}</td>
            <td>${todayAttendance.attendance}</td>
            <td>${todayAttendance.attendanceTime}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="attendance">返回</a>
</body>
</html>
