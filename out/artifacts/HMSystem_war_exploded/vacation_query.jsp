<%--
  Created by IntelliJ IDEA.
  User: STARS
  Date: 2023/6/20
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>休假信息查询</title>
</head>
<body>
<h1>休假信息查询</h1>
<%--<form action="queryServlet" method="post">--%>
<%--  <label for="employeeId">员工ID：</label>--%>
<%--  <input type="text" id="employeeId" name="employeeId">--%>
<%--  <br>--%>
<%--  <input type="submit" value="查询">--%>
<%--</form>--%>
<hr>
<h2>统计结果：</h2>
<p>员工姓名：<%= request.getAttribute("employeeName") %></p>
<p>总休假天数：<%= request.getAttribute("totalDays") %></p>
<p>平均休假天数：<%= request.getAttribute("averageDays") %></p>

<a href="vacation">返回</a>
</body>
</html>

