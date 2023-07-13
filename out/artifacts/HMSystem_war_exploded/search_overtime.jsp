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
    <th>员工姓名</th>
    <th>加班日期</th>
    <th>加班时间</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="overtime" items="${overtimes}">
    <tr>
      <td>${overtime.employeeName}</td>
      <td>${overtime.overtimeDate}</td>
      <td>${overtime.overtimeHours}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<a href="overtime">返回</a>
</body>
</html>
