<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: STARS--%>
<%--  Date: 2023/6/14--%>
<%--  Time: 17:06--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
<%--         pageEncoding="UTF-8"%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>员工休假管理</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>员工休假管理</h1>--%>
<%--    <table>--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th>ID</th>--%>
<%--            <th>员工ID</th>--%>
<%--            <th>员工姓名</th>--%>
<%--            <th>开始日期</th>--%>
<%--            <th>结束日期</th>--%>
<%--            <th>类型</th>--%>
<%--            <th>原因</th>--%>
<%--            <th>操作</th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <c:forEach var="vacation" items="${vacations}">--%>
<%--            <tr>--%>
<%--                <td>${vacation.id}</td>--%>
<%--                <td>${vacation.employeeId}</td>--%>
<%--                <td>${vacation.employee_name}</td>--%>
<%--                <td>${vacation.startDate}</td>--%>
<%--                <td>${vacation.endDate}</td>--%>
<%--                <td>${vacation.type}</td>--%>
<%--                <td>${vacation.reason}</td>--%>
<%--                <td>--%>
<%--                    <form method="post" action="${pageContext.request.contextPath}/vacation">--%>
<%--                        <input type="hidden" name="id" value="${vacation.id}">--%>
<%--                        <input type="hidden" name="action" value="delete">--%>
<%--                        <button type="submit">删除</button>--%>
<%--                    </form>--%>
<%--&lt;%&ndash;                    <form method="get" action="${pageContext.request.contextPath}/vacation">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <input type="hidden" name="id" value="${vacation.id}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <input type="hidden" name="action" value="search">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <button type="submit">查询员工休假信息</button>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </form>&ndash;%&gt;--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--        </tbody>--%>
<%--    </table>--%>
<%--    <h2>添加员工休假信息</h2>--%>
<%--    <form method="post" action="${pageContext.request.contextPath}/vacation">--%>
<%--        <label>员工ID：<input type="text" name="employee_id"></label>--%>
<%--        <label>开始日期：<input type="date" name="start_date"></label>--%>
<%--        <label>日期：<input type="date" name="end_date"></label>--%>
<%--        <label>类型：<input type="text" name="type"></label>--%>
<%--        <label>原因：<input type="text" name="reason"></label>--%>
<%--        <input type="hidden" name="action" value="add">--%>
<%--        <button type="submit">添加</button>--%>
<%--    </form>--%>

<%--<form method="post" action="${pageContext.request.contextPath}/vacation">--%>
<%--    <label>输入员工姓名：<input type="text" name="employee_name"></label>--%>
<%--&lt;%&ndash;    <input type="text" name="name" value="${.}">&ndash;%&gt;--%>
<%--    <input type="hidden" name="action" value="search">--%>
<%--    <button type="submit">查询员工休假信息</button>--%>
<%--    <c:if test="${not empty errorMessage}">--%>
<%--        <div style="color: red;">${errorMessage}</div>--%>
<%--    </c:if>--%>


<%--</form>--%>
<%--<a href="homePage.jsp">返回主页</a>--%>
<%--</body>--%>
<%--</html>--%>

<%--
  Created by IntelliJ IDEA.
  User: STARS
  Date: 2023/6/14
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>员工休假管理</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h1 {
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        form {
            margin-bottom: 10px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="date"] {
            padding: 5px;
            width: 200px;
        }

        button[type="submit"] {
            padding: 5px 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        button[type="submit"]:hover {
            background-color: #45a049;
        }

        .error-message {
            color: red;
            margin-top: 10px;
        }

        .back-link {
            display: block;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<h1>员工休假管理</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>员工ID</th>
        <th>员工姓名</th>
        <th>开始日期</th>
        <th>结束日期</th>
        <th>类型</th>
        <th>原因</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="vacation" items="${vacations}">
        <tr>
            <td>${vacation.id}</td>
            <td>${vacation.employeeId}</td>
            <td>${vacation.employee_name}</td>
            <td>${vacation.startDate}</td>
            <td>${vacation.endDate}</td>
            <td>${vacation.type}</td>
            <td>${vacation.reason}</td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/vacation">
                    <input type="hidden" name="id" value="${vacation.id}">
                    <input type="hidden" name="action" value="delete">
                    <button type="submit">删除</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h2>添加员工休假信息</h2>
<form method="post" action="${pageContext.request.contextPath}/vacation">
    <label style="display: inline-block;">员工姓名：<input type="text" name="employee_name"></label>
    <label style="display: inline-block;">开始日期：<input type="date" name="start_date"></label>
    <label style="display: inline-block;">日期：<input type="date" name="end_date"></label>
    <label style="display: inline-block;">类型：<input type="text" name="type"></label>
    <label style="display: inline-block;">原因：<input type="text" name="reason"></label>
    <input type="hidden" name="action" value="add">
    <button type="submit">添加</button>
</form>

<h2>查询员工休假信息</h2>
<form method="post" action="${pageContext.request.contextPath}/vacation">
    <label style="display: inline-block;">输入员工姓名：<input type="text" name="employee_name"></label>
    <input type="hidden" name="action" value="search">
    <button type="submit">查询</button>
    <c:if test="${not empty errorMessage}">
        <div class="error-message">${errorMessage}</div>
    </c:if>
</form>
<a href="homePage.jsp" class="back-link">返回主页</a>
</body>
</html>
