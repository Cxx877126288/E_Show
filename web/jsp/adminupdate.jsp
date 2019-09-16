<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/8/13
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        input[type="input"]{
            width: 100px;
        }
        .ad_td1{
            width: 100px;

        }
        .ad_td2{
            width: 300px;
        }
    </style>
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        function submit() {
            var data = "";
            var id = document.getElementById("id").value;
            var sex = document.getElementById("sex").value;
            var name = document.getElementById("name").value;
            var borntime = document.getElementById("borntime").value;
            var email = document.getElementById("email").value;
            var phone = document.getElementById("phone").value;
            var address = document.getElementById("address").value;
            var postalcode = document.getElementById("postalcode").value;
            data = "id=" + id + "&sex=" + sex + "&name=" + name + "&borntime=" + borntime + "&email="+email + "&phone="  + phone + "&address=" + address + "&postalcode="+ postalcode;
            $.ajax({
                "url": "../updadmininfor",
                "data": data,
                "type": "post",
                "dataType":"text",
                "success": function (data, textStatus) {
                    if (data != null) {
                        alert(data)
                        location = location;
                    }
                }
            });
        }
    </script>
</head>
<body>
<table border="1px" style="border-collapse: collapse;margin-left: 25%;">
    <tr>
        <td class="ad_td1" align="center">用户名：</td>
        <td class="ad_td2"><input id="id" value="${admin.id}" disabled="true"></td>
    </tr>
    <tr>
        <td class="ad_td1" align="center">性别：</td>
        <td class="ad_td2"><input id="sex" value="${admin.sex}"></td>
    </tr>
    <tr>
        <td class="ad_td1" align="center">姓名：</td>
        <td class="ad_td2"><input id="name" value="${admin.name}"></td>
    </tr>
    <tr>
        <td class="ad_td1" align="center">出生日期：</td>
        <td class="ad_td2"><input id="borntime" value="${admin.borntime}"></td>
    </tr>
    <tr>
        <td class="ad_td1" align="center">电子邮箱：</td>
        <td class="ad_td2"><input id="email" value="${admin.email}"></td>
    </tr>
    <tr>
        <td class="ad_td1" align="center">电话号码：</td>
        <td class="ad_td2"><input id="phone" value="${admin.phone}"></td>
    </tr>
    <tr>
        <td class="ad_td1" align="center">地址：</td>
        <td class="ad_td2"><input id="address" value="${admin.address}"></td>
    </tr>
    <tr>
        <td class="ad_td1" align="center">邮编：</td>
        <td class="ad_td2"><input id="postalcode" value="${admin.postalcode}"></td>
    </tr>
    <tr>
        <td class="ad_td1" align="center">注册时间：</td>
        <td class="ad_td2"><input id="registertiome" value="${admin.registertime}" disabled="true"></td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <input type="button" value="确认" onclick="submit()">
            <input type="reset"  value="重置">
        </td>
    </tr>
</table>
</body>
</html>
