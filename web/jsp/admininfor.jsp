<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/8/13
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>

<table style="margin-left: 25%;border-collapse: collapse" border="1px">
    <tr align="center">
        <td>用户名：</td>
        <td>${admin.id}</td>
    </tr>
    <tr align="center">
        <td>性别：</td>
        <td>${admin.sex}</td>
    </tr>
    <tr align="center">
        <td>姓名：</td>
        <td>${admin.name}</td>
    </tr>
    <tr align="center">
        <td>出生日期：</td>
        <td>${admin.borntime}</td>
    </tr>
    <tr align="center">
        <td>电子邮箱：</td>
        <td>${admin.email}</td>
    </tr>
    <tr align="center">
        <td>电话号码：</td>
        <td>${admin.phone}</td>
    </tr>
    <tr align="center">
        <td>地址：</td>
        <td>${admin.address}</td>
    </tr>
    <tr align="center">
        <td>邮编：</td>
        <td>${admin.postalcode}</td>
    </tr>
    <tr align="center">
        <td>注册时间：</td>
        <td>${admin.registertime}</td>
    </tr>
</table>
</body>
</html>
