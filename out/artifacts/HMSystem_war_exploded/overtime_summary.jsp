<%--
  Created by IntelliJ IDEA.
  User: STARS
  Date: 2023/6/22
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>加班信息汇总</title>
</head>
<body>
<h1>加班信息汇总</h1>
<table>
    <thead>
    <tr>
        <th>员工姓名</th>
        <th>加班总时长</th>
        <th>加班平均时长</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="overtimeSummary" items="${overtimeSummarys}">
        <tr>
            <td>${overtimeSummary.employeeName}</td>
            <td>${overtimeSummary.totalOvertime}</td>
            <td>${overtimeSummary.meanOvertime}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="overtime">返回</a>
</body>
</html>
