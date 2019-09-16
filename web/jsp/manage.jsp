<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/8/15
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        a{
            cursor: pointer;
        }
        a:hover{
            color: red;
        }
    </style>
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        function aClick(aValue) {
            var data="&pagenumber=";
            var va = aValue.innerText;
            if( va == "上一页"){
                if(${pagenumber > 1}){
                    data += ${pagenumber - 1};
                }else{
                    data += ${pagenumber};
                    alert("已经是第一页")
                }
            }else if(va == "下一页"){
                if(${pagenumber < count}){
                    data += ${pagenumber + 1};
                }else{
                    data += ${pagenumber};
                    alert("已经是最后一页")
                }
            }else if( va == "首页"){
                data += 1;
            }else if(va =="尾页"){
                data += ${count}
            }else {
                data += va;
            }
            var userid = document.getElementById("userid").value;
            var sex = $("#se").val();
            var flag = $("#flag").val();
            if(userid == ""){
                userid = null;
            }
            if(sex == 'boy'){
                sex = 1;
            }else if(sex == 'girl'){
                sex = 2;
            }else{
                sex = "";
            }
            if(flag == 'normal'){
                flag = 1;
            }else if(flag == 'unnormal'){
                flag = -1;
            }else {
                flag = ""
            }
            window.location.href = "/selalluser?" + "userid=" + userid + "&sex=" + sex + "&flag=" + flag + data;
        }


        function searchClick() {
            var userid = document.getElementById("userid").value;
            var sex = $("#se").val();
            var flag = $("#flag").val();
            if(userid == ""){
                userid = null;
            }
            if(sex == 'boy'){
                sex = 1;
            }else if(sex == 'girl'){
                sex = 2;
            }else{
                sex = "";
            }
            if(flag == 'normal'){
                flag = 1;
            }else if(flag == 'unnormal'){
                flag = -1;
            }else {
                flag = ""
            }
            window.location.href = "/selalluser?" + "userid=" + userid + "&sex=" + sex + "&flag=" + flag + "&pagenumber=1";
        }

        function freeze(btn) {
            var tr = btn.parentNode.parentNode;
            var tds = tr.getElementsByTagName("td");
            var userid = tds[0].innerText;
            var flag = tds[4].innerText;
            if(flag == "正常")
                flag = 1;
            else if(flag == "冻结"){
                flag = -1;
            }else{
                flag = "";
            }

            $.ajax({
                "url": "../upduserflag",
                "data": "userid=" + userid + "&flag=" + flag,
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
<table border="1px" style="border-collapse: collapse;margin-left: 15%;margin-top: 2%;">
    <tr align="center">
        <td  width="270px">用户名：<input type="text" name="userid" id="userid" value=""></td>
        <td width="150px">性别：<select id="se">
            <option value="">请选择</option>
            <option value="boy">男</option>
            <option value="girl">女</option>
        </select></td>
        <td width="150px">状态：<select id="flag">
            <option value="">请选择</option>
            <option value="normal">正常</option>
            <option value="unnormal">冻结</option>
        </select></td>
        <td width="150px">
            <input type="button" value="查询" onclick="searchClick()">
            <input type="reset" value="重置" style="margin-left: 10%;">
        </td>
    </tr>
</table>
<table style="margin-top: 52px; border-collapse: collapse;margin-left: 17%;" border="1px">
    <tr align="center">
        <td style="width: 150px">用户名</td>
        <td style="width: 100px">姓名</td>
        <td style="width: 70px">性别</td>
        <td style="width: 200px">注册时间</td>
        <td style="width: 70px">状态</td>
        <td style="width: 70px">操作</td>
    </tr>
    <c:forEach items="${users}" var="u">
        <tr align="center">
            <td id="id">${u.userid}</td>
            <td>${u.username}</td>
            <td><c:if test="${u.sex == 1}">男</c:if><c:if test="${u.sex == 2}">女</c:if></td>
            <td>${u.register}</td>
            <td id="f"><c:if test="${u.flag == 1}">正常</c:if><c:if test="${u.flag == -1}">冻结</c:if></td>
            <td><input type="button" value="<c:if test="${u.flag == 1}">冻结</c:if><c:if test="${u.flag == -1}">解冻</c:if>" onclick="freeze(this)"></td>
        </tr>
    </c:forEach>
    <tr style="width: 100%">
        <td colspan="6" align="center">
            <div style="width: 100%;" align="center">
                第${pagenumber}页/共${count}页|
                <a onclick="aClick(this)">首页</a>|
                <a onclick="aClick(this)">上一页</a>|
                <c:forEach var="i" begin="1" end="${count}">
                    <a onclick="aClick(this)">${i}</a>|
                </c:forEach>
                <a onclick="aClick(this)">下一页</a>|
                <a onclick="aClick(this)">尾页</a>
            </div>
        </td>
    </tr>
</table>
</body>
</html>
