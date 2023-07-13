<%--
  Created by IntelliJ IDEA.
  User: STARS
  Date: 2023/6/22
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
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
<%--        <th>ID</th>--%>
        <th>员工姓名</th>
        <th>考勤日期</th>
        <th>考勤时间</th>
        <th>考勤类型</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="attendance" items="${attendances}">
        <tr>
            <td>${attendance.employeeName}</td>
            <td>${attendance.attendanceDate}</td>
            <td>${attendance.attendanceTime}</td>
            <td>${attendance.attendanceType}</td>
<%--            <td>${eventLog.eventDetail}</td>--%>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="attendance">返回</a>
</body>
</html>
