<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/8/3
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .td_one{
            width: 25%;
        }
    </style>
</head>
<body>
    <table border="1px" style="margin-left: 20%;width: 60%;border-collapse: collapse">
        <tr>
            <td class="td_one" align="center">用户名</td>
            <td>${user.userid}</td>
        </tr>
        <tr>
            <td class="td_one"  align="center">性别</td>
            <td>${sex}</td>
        </tr>
        <tr>
            <td class="td_one"  align="center">真实姓名</td>
            <td>${user.username}</td>
        </tr>
        <tr>
            <td class="td_one"  align="center">出生日期</td>
            <td>${borntime}</td>
        </tr>
        <tr>
            <td class="td_one"  align="center">电子邮箱</td>
            <td>${user.email}</td>
        </tr>
        <tr>
            <td class="td_one"  align="center">电话号码</td>
            <td>${user.phone}</td>
        </tr>
        <tr>
            <td class="td_one"  align="center">地址</td>
            <td>${user.address}</td>
        </tr>
        <tr>
            <td class="td_one"  align="center">邮编</td>
            <td>${user.postalcode}</td>
        </tr>
        <tr>
            <td class="td_one"  align="center">注册时间</td>
            <td>${register}</td>
        </tr>
        <tr>
            <td class="td_one"  align="center">最后登陆时间</td>
            <td>${last}</td>
        </tr>
        <tr>
            <td class="td_one"  align="center">登陆次数</td>
            <td>${login.logincount}</td>
        </tr>
    </table>
</body>
</html>
