<%--
  Created by IntelliJ IDEA.
  User: STARS
  Date: 2023/6/14
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>部门树状视图</title>

    <style>
        ul {
            list-style-type: none;
            padding-left: 20px;
        }
        .department {
            font-weight: bold;
        }
    </style>
</head>
<body>
<h1>部门树状视图</h1>
<ul>
    <c:forEach var="department" items="${departments}" varStatus="status">
        <c:if test="${department.parentId == 0}">
            <li>
                <span class="department">${department.name}</span>
                <ul>
                    <c:forEach var="subDepartment" items="${departments}" varStatus="subStatus">
                        <c:if test="${subDepartment.parentId == department.id}">
                            <li>${subDepartment.name}</li>
                        </c:if>
                    </c:forEach>
                </ul>
            </li>
        </c:if>
    </c:forEach>
</ul>
<br>
<a href="department">返回</a>
</body>
</html>

<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Department Tree</title>--%>
<%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div id="departmentTree"></div>--%>

<%--<script>--%>
<%--    var departments = ${departments};--%>

<%--    var treeData = [];--%>

<%--    departments.forEach(function(department) {--%>
<%--        var node = {--%>
<%--            id: department.id,--%>
<%--            parent: department.parentId === 0 ? "#" : department.parentId,--%>
<%--            text: department.name--%>
<%--        };--%>
<%--        treeData.push(node);--%>
<%--    });--%>

<%--    $('#departmentTree').jstree({--%>
<%--        'core': {--%>
<%--            'data': treeData--%>
<%--        }--%>
<%--    });--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>
