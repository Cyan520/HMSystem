
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>部门管理</title>
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
<h1>部门管理</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>部门</th>
        <th>主管</th>
        <th>父部门ID</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="department" items="${departments}">
        <tr>
            <td>${department.id}</td>
            <td>${department.name}</td>
            <td>${department.manger}</td>
            <td>${department.parentId}</td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/department">
                    <input type="hidden" name="id" value="${department.id}">
                    <input type="hidden" name="action" value="delete">
                    <button type="submit">删除</button>
                </form>
                <form method="post" action="${pageContext.request.contextPath}/department">
                    <input type="hidden" name="id" value="${department.id}">
                    <input type="hidden" name="action" value="update">
                    <button type="submit">编辑</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form method="post" action="${pageContext.request.contextPath}/department">
    <input type="hidden" name="id" value="${department.id}">
    <input type="hidden" name="action" value="tree">
    <button type="submit">查看部门树状图</button>
</form>
<h2>添加部门</h2>
<form method="post" action="${pageContext.request.contextPath}/department">
    <label style="display: inline-block;">部门：<input type="text" name="name"></label>
    <label style="display: inline-block;">主管：<input type="text" name="manger"></label>
    <label style="display: inline-block;">父部门ID：<input type="text" name="parent_id"></label>
    <input type="hidden" name="action" value="add">
    <button type="submit">添加</button>
</form>
<br>
<a href="homePage.jsp">返回主页</a>
</body>
</html>
