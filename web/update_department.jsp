<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: STARS--%>
<%--  Date: 2023/6/14--%>
<%--  Time: 21:59--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
<%--         pageEncoding="UTF-8"%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>修改部门信息</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>修改部门信息</h1>--%>
<%--<form method="post" action="${pageContext.request.contextPath}/department">--%>
<%--    <label>ID：<input type="text" name="id" value="${department.id}" readonly></label>--%>
<%--    <label>名称：<input type="text" name="name" value="${department.name}"></label>--%>
<%--    <label>父部门ID：<input type="text" name="parent_id" value="${department.parentId}"></label>--%>
<%--    <input type="hidden" name="action" value="update">--%>
<%--    <button type="submit">更新</button>--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>


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
    <title>修改部门</title>
</head>
<body>
<h1>修改部门</h1>
<form action="department?action=update_dep" method="post">
    <table>
        <tr>
            <td>ID：</td>
            <td>${department.id}</td>
            <td><label>
                <input type="hidden" name="id" value="${department.id}">
            </label></td>
        </tr>
        <tr>
            <td>部门：</td>
            <td><label>
                <input type="text" name="name" value="${department.name}">
            </label></td>
        </tr>
        <tr>
            <td>主管：</td>
            <td><label>
                <input type="text" name="manger" value="${department.manger}">
            </label></td>
        </tr>
        <tr>
            <td>父部门ID：</td>
            <td><label>
                <input type="text" name="parentId" value="${department.parentId}">
            </label></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="修改"></td>
        </tr>
    </table>
</form>
<br>
<a href="department">返回</a>
</body>
</html>
