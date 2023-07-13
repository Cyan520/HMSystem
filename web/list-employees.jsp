<%--
  Created by IntelliJ IDEA.
  User: STARS
  Date: 2023/6/14
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>员工管理系统</title>
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
<h1>员工列表</h1>
<table>
  <tr>
    <th>编号</th>
    <th>姓名</th>
    <th>性别</th>
    <th>年龄</th>
    <th>职位</th>
    <th>薪资</th>
    <th>部门号</th>
    <th>操作</th>
  </tr>
  <c:forEach var="employee" items="${employees}">
    <tr>
      <td>${employee.id}</td>
      <td>${employee.name}</td>
      <td>${employee.gender}</td>
      <td>${employee.age}</td>
      <td>${employee.position}</td>
      <td>${employee.salary}</td>
      <td>${employee.departmentId}</td>
      <td>
        <a href="employee?action=showUpdateForm&id=${employee.id}">修改</a>
        <a href="employee?action=delete&id=${employee.id}">删除</a>
      </td>
    </tr>
  </c:forEach>
</table>
<br>
<a href="employee?action=showAddForm" class="add-button">添加员工</a>
<br>
<a href="homePage.jsp" class="back-button">返回主页</a>
</body>
</html>
