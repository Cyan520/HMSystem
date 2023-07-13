<%--
  Created by IntelliJ IDEA.
  User: STARS
  Date: 2023/6/14
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>添加员工</title>
</head>
<body>
<h1>添加员工</h1>
<form action="employee?action=add" method="post">
<table>
  <tr>
    <td>姓名：</td>
    <td><label>
      <input type="text" name="name">
    </label></td>
  </tr>
  <tr>
    <td>性别：</td>
    <td>
      <label>
        <input type="radio" name="gender" value="男">
        男
      </label>
      <label>
        <input type="radio" name="gender" value="女">
        女
      </label>
    </td>
  </tr>
  <tr>
    <td>年龄：</td>
    <td><label>
      <input type="text" name="age">
    </label></td>
  </tr>
  <tr>
    <td>职位：</td>
    <td><label>
      <input type="text" name="position">
    </label></td>
  </tr>
  <tr>
    <td>薪资：</td>
    <td><label>
      <input type="text" name="salary">
    </label></td>
  </tr>
  <tr>
    <td>部门号：</td>
    <td><label>
      <input type="text" name="departmentid" value="${employee.departmentId}">
    </label></td>
  </tr>
  <tr>
    <td colspan="2"><input type="submit" value="添加"></td>
  </tr>
</table>
</form>
<br>
<a href="employee?action=list">返回</a>
</body>
</html>
