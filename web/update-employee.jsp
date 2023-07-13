<%--
  Created by IntelliJ IDEA.
  User: STARS
  Date: 2023/6/14
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>修改员工</title>
</head>
<body>
<h1>修改员工</h1>
<form action="employee?action=update" method="post">
  <table>
    <tr>
      <td>编号：</td>
      <td>${employee.id}</td>
      <td><label>
      <input type="hidden" name="id" value="${employee.id}">
      </label></td>
    </tr>
    <tr>
      <td>姓名：</td>
      <td><label>
        <input type="text" name="name" value="${employee.name}">
      </label></td>
    </tr>
    <tr>
      <td>性别：</td>
      <td>
        <label>
          <input type="radio" name="gender" value="男" ${employee.gender== '男' ? 'checked' : ''}>
          男
        </label>
        <label>
          <input type="radio" name="gender" value="女" ${employee.gender== '女' ? 'checked' : ''}>
          女
        </label>
      </td>
    </tr>
    <tr>
      <td>年龄：</td>
      <td><label>
        <input type="text" name="age" value="${employee.age}">
      </label></td>
    </tr>
    <tr>
      <td>职位：</td>
      <td><label>
        <input type="text" name="position" value="${employee.position}">
      </label></td>
    </tr>
    <tr>
      <td>薪资：</td>
      <td><label>
        <input type="text" name="salary" value="${employee.salary}">
      </label></td>
    </tr>
    <tr>
      <td>部门号：</td>
      <td><label>
          <input type="text" name="departmentid" value="${employee.departmentId}">
      </label></td>
    </tr>
    <tr>
      <td colspan="2"><input type="submit" value="修改"></td>
    </tr>
  </table>
</form>
<br>
<a href="employee?action=list">返回</a>
</body>
</html>
