<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/8/6
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
       String adminid = request.getAttribute( "adminID" ).toString();
       // String adminName = request.getAttribute( "adminName" ).toString();
       //  String adminid = request.getParameter( "adminID" );
    %>
    <title>管理界面</title>
    <style>
        a{
            text-decoration: none;
            color: red;
        }
        input[type="button"]:hover{
            cursor: pointer;
        }
    </style>
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        function updPwd() {
            $("#ashow").attr("src","/jsp/updadminpwd.jsp?adminid=<%=adminid%>")
        }
        function loginOut() {
            window.location.href = "/jsp/adminlogin.jsp";
        }
    </script>
</head>
<body>
    <div style="width: 80%;height: 100%;margin-left: 10%;margin-top: 3%;">
        <div style="width: 100%;height: 12%;float: left;border-spacing: inherit;background-image: url('../images/top_bg.jpg');
            background-repeat: no-repeat;background-size: 100% 100%;-moz-background-size: 100% 100%;">

            <div style="width: 15%;height:100%;float: left;border-spacing: inherit;background-image: url('../images/top_logo.jpg');
                    background-repeat: no-repeat;background-size: 100% 100%;-moz-background-size: 100% 100%;">
            </div>
            <div style="width: 80%;height:70%;float: left;margin-left: 3%;margin-top: 7px;">
                <div style="float: left">欢迎您，<c:if test="${adminflag == 1}">普通管理员</c:if><c:if test="${adminflag == 2}">超级管理员</c:if>：</div>
                <div id="adminid" style="float: left"><%=adminid%></div>
            </div>
            <div style="width: 60%;height: 20%;float: left;margin-left: 3%;">
                <div ><input style="height: 100%;border-spacing: inherit;background-image: url('../images/top_tt_bg.gif');
            background-repeat: no-repeat;background-size: 100% 100%;-moz-background-size: 100% 100%;width: 10%;float: left;border: 0px;" type="button" value="修改密码" onclick="updPwd()"></div>
                <div><input style="height: 100%;border-spacing: inherit;background-image: url('../images/top_tt_bg.gif');
            background-repeat: no-repeat;background-size: 100% 100%;-moz-background-size: 100% 100%;width: 10%;float: left;margin-left:2%;border: 0px" type="button" value="退出登陆" onclick="loginOut()"></div>
            </div>
        </div>

        <div style="width: 15%;height:550px; background: #08a2ff;float: left;margin-top: 2%;">
            <div align="center" style="width: 98%;margin-left: 2px;background: white">
                <div align="center" style="width: 100%;height: 20px;border-spacing: inherit;background-image: url('../images/top_tt_bg.gif');
            background-repeat: no-repeat;background-size: 100% 100%;-moz-background-size: 100% 100%;" >
                    商品管理
                </div>
                <div style=""><img src="../images/closed.gif" style="margin-top: 2px"><a target="ashow" href="/adcategory">商品列表管理</a></div>
                <div style=""><img src="../images/closed.gif" style="margin-top: 2px"><a target="ashow" href="/goods">商品信息管理</a></div>
            </div>

            <div align="center" style="width: 98%;margin-left: 2px;margin-top: 5px;background: white">
                <div align="center" style="width: 100%;height: 20px;border-spacing: inherit;background-image: url('../images/top_tt_bg.gif');
            background-repeat: no-repeat;background-size: 100% 100%;-moz-background-size: 100% 100%;" >
                    用户管理
                </div>
                <div style=""><img src="../images/closed.gif" style="margin-top: 2px"><a target="ashow" href="/selalluser">注册用户管理</a></div>
            </div>

            <div align="center" style="width: 98%;margin-left: 2px;margin-top: 5px;background: white">
                <div align="center" style="width: 100%;height: 20px;border-spacing: inherit;background-image: url('../images/top_tt_bg.gif');
            background-repeat: no-repeat;background-size: 100% 100%;-moz-background-size: 100% 100%;" >
                    个人资料管理
                </div>
                <div style=""><img src="../images/closed.gif" style="margin-top: 2px"><a target="ashow" href="/seloneadmin?adminid=<%=adminid%>">显示个人资料</a></div>
                <div style=""><img src="../images/closed.gif" style="margin-top: 2px"><a target="ashow" href="/updadmin?adminid=<%=adminid%>">个人信息修改</a></div>
                <div style=""><img src="../images/closed.gif" style="margin-top: 2px"><a target="ashow" href="/jsp/updadminpwd.jsp?adminid=<%=adminid%>">个人密码修改</a></div>
            </div>

            <div align="center" style="width: 98%;margin-left: 2px;margin-top: 5px;background: white">
                <div align="center" style="width: 100%;height: 20px;border-spacing: inherit;background-image: url('../images/top_tt_bg.gif');
            background-repeat: no-repeat;background-size: 100% 100%;-moz-background-size: 100% 100%;" >
                    订单管理
                </div>
                <div style=""><img src="../images/closed.gif" style="margin-top: 2px"><a target="ashow" href="/selallaccount">用户订单管理</a></div>
            </div>
        </div>
        <!-- 接受的管理员ID要改下接受方式 -->
        <div style="width: 85%;height: 85%;float: left;">
            <iframe id="ashow" name="ashow" height="100%" width="100%" frameborder="0" scrolling="no" src="/adcategory"></iframe>
        </div>

    </div>
</body>
</html>
