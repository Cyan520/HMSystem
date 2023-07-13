<%--
  Created by IntelliJ IDEA.
  User: STARS
  Date: 2023/6/14
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>报表详情</title>
</head>
<body>
<h1>${report.reportName}</h1>
<pre>${report.reportData}</pre>
<button onclick="printReport()">打印</button>
<script>
  function printReport() {
    window.print();
  }
</script>
<br>
<a href="report">返回</a>
</body>
</html>
