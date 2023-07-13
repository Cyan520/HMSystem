<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: STARS--%>
<%--  Date: 2023/6/14--%>
<%--  Time: 16:06--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
<%--         pageEncoding="UTF-8"%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--  <meta charset="UTF-8">--%>
<%--  <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--  <title>人事管理系统</title>--%>
<%--  <link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css">--%>
<%--  <style>--%>
<%--    body {--%>
<%--      background-color: #f8f9fa;--%>
<%--    }--%>
<%--    .card {--%>
<%--      margin-top: 20px;--%>
<%--    }--%>
<%--    .card-header {--%>
<%--      background-color: #007bff;--%>
<%--      color: #fff;--%>
<%--    }--%>
<%--    .card-header a {--%>
<%--      color: #fff;--%>
<%--    }--%>
<%--    .card-header a:hover {--%>
<%--      color: #fff;--%>
<%--      text-decoration: none;--%>
<%--    }--%>
<%--  </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="container">--%>
<%--  <div class="row">--%>
<%--    <div class="col-md-4">--%>
<%--      <div class="card">--%>
<%--        <div class="card-header">--%>
<%--          <a href="employee">员工管理</a>--%>
<%--        </div>--%>
<%--        <div class="card-body">--%>
<%--          这里是员工管理模块的简介--%>
<%--        </div>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--    <div class="col-md-4">--%>
<%--      <div class="card">--%>
<%--        <div class="card-header">--%>
<%--          <a href="department">部门管理</a>--%>
<%--        </div>--%>
<%--        <div class="card-body">--%>
<%--          这里是部门管理模块的简介--%>
<%--        </div>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--    <div class="col-md-4">--%>
<%--      <div class="card">--%>
<%--        <div class="card-header">--%>
<%--          <a href="vacation">休假管理</a>--%>
<%--        </div>--%>
<%--        <div class="card-body">--%>
<%--          这里是休假管理模块的简介--%>
<%--        </div>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--  </div>--%>
<%--  <div class="row">--%>
<%--    <div class="col-md-4">--%>
<%--      <div class="card">--%>
<%--        <div class="card-header">--%>
<%--          <a href="event_log">事件日志管理</a>--%>
<%--        </div>--%>
<%--        <div class="card-body">--%>
<%--          这里是事件日志管理模块的简介--%>
<%--        </div>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--    <div class="col-md-4">--%>
<%--      <div class="card">--%>
<%--        <div class="card-header">--%>
<%--          <a href="attendance">人事考勤</a>--%>
<%--        </div>--%>
<%--        <div class="card-body">--%>
<%--          这里是人事考勤模块的简介--%>
<%--        </div>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--    <div class="col-md-4">--%>
<%--      <div class="card">--%>
<%--        <div class="card-header">--%>
<%--          <a href="overtime">加班管理</a>--%>
<%--        </div>--%>
<%--        <div class="card-body">--%>
<%--          这里是加班管理模块的简介--%>
<%--        </div>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--  </div>--%>
<%--  <div class="row">--%>
<%--    <div class="col-md-4">--%>
<%--      <div class="card">--%>
<%--        <div class="card-header">--%>
<%--          <a href="salary">工资管理</a>--%>
<%--        </div>--%>
<%--        <div class="card-body">--%>
<%--          这里是工资管理模块的简介--%>
<%--        </div>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--    <div class="col-md-4">--%>
<%--      <div class="card">--%>
<%--        <div class="card-header">--%>
<%--          <a href="report">报表打印</a>--%>
<%--        </div>--%>
<%--        <div class="card-body">--%>
<%--          这里是报表打印模块的简介--%>
<%--        </div>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--  </div>--%>
<%--</div>--%>
<%--<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.min.js"></script>--%>
<%--<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.5.0/js/bootstrap.min.js"></script>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>人事管理系统</title>
  <!-- 引入 Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <h1 class="text-center mt-5">人事管理系统</h1>
  <div class="row mt-5">
    <div class="col-md-3">
      <a href="employee" class="btn btn-primary btn-block">员工管理</a>
    </div>
    <div class="col-md-3">
      <a href="department" class="btn btn-primary btn-block">部门管理</a>
    </div>
    <div class="col-md-3">
      <a href="vacation" class="btn btn-primary btn-block">休假管理</a>
    </div>
    <div class="col-md-3">
      <a href="event_log" class="btn btn-primary btn-block">事件日志管理</a>
    </div>
  </div>
  <div class="row mt-5">
    <div class="col-md-3">
      <a href="attendance" class="btn btn-primary btn-block">人事考勤</a>
    </div>
    <div class="col-md-3">
      <a href="overtime" class="btn btn-primary btn-block">加班管理</a>
    </div>
    <div class="col-md-3">
      <a href="salary" class="btn btn-primary btn-block">工资管理</a>
    </div>
    <div class="col-md-3">
      <a href="report" class="btn btn-primary btn-block">报表打印</a>
    </div>
  </div>
</div>
<!-- 引入 Bootstrap JavaScript -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
