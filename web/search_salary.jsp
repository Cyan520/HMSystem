<%--
  Created by IntelliJ IDEA.
  User: STARS
  Date: 2023/6/23
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>员工薪酬</title>
</head>
<body>
<h1>员工薪酬</h1>
<table>
    <thead>
    <tr>
        <th>员工姓名</th>
        <th>总获得薪酬</th>
        <th>平均薪酬</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="employeeSalary" items="${employeeSalaries}">
        <tr>
            <td>${employeeSalary.employeeName}</td>
            <td>${employeeSalary.totalSalary}</td>
            <td>${employeeSalary.meanSalary}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="salary">返回</a>
</body>
</html>
