<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/7/2
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>

    <%
        String msg = "";
        String uid = request.getParameter("userid");
        if("false".equals( request.getParameter( "msg" ) )){
        	msg = "* 用 户 名 或 密 码 错 误，请 重 新 再 试。";
        }else if("show".equals( request.getParameter( "msg" ) )){
        	msg = "* 您 的 账 号 已 经 被 冻 结，请 联 系 管 理 员 解 冻！";
        }else if("yan".equals( request.getParameter( "msg" ) )){
            msg = "* 验 证 码 错 误！";
        }
    %>
    <title>登陆</title>
    <link rel="stylesheet" type="text/css" href="../css/login.css">
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <script type="text/javascript" src="../js/utils.js"></script>
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        window.onload = function () {
            if("<%=msg%>" != null && "<%=msg%>" != "" && "<%=msg%>" != "null")
                alert("<%=msg%>")
        }
        function aClick(){
            $("img").attr("src","/valid?data="+ new Date());
        }

        var   committed=false;

        function dosubmit(){
            if(committed)
                return false;
            committed=true;
            return true;
        }

    </script>

</head>
<body style="border-spacing: inherit;
    background-image: url('../images/myimages/loginbackground.png');
background-repeat: no-repeat;
background-size: 100% 100%;
-moz-background-size: 100% 100%;">
<div class="total" onload="loadForm()">
    <form class="from" id="mainFrom" onsubmit="return dosubmit()"  action="<%=basePath%>main" method="post">
        <div>
            <input id="userid" name="userid" type="text" value="<%= uid != null ? uid : "" %>" placeholder="用户名">
        </div>

        <div>
            <input id="userpwd" name="userpwd" type="password" placeholder="密码">
        </div>
        <div class="left" style="width: 100%;">
            <div style="width: 43%;" class="left">
                验证码：<input id="code" name="code" type="text" maxlength="4" style="width: 30%;">
            </div>
            <div class="left" style="width: 30%;height: 40px;margin-top: 4%;">
                <img style="width: 100%;height: 100%;" src="/valid">
            </div>
            <div style="margin-top: 12%;">
                <a style="font-size: 20px;padding-left: 2%;" onclick="aClick();">看不清</a>
            </div>
        </div>
        <div style="height: 20px;margin-top: 10px;">
            <span class="myspan left"></span>
        </div>

        <div class="mybtn">
            <input type="submit" value="登录">
        </div>

        <div class="register">
            <a href="/jsp/adminlogin.jsp" class="left">管理员登陆</a>
            <a href="/jsp/registeruser.jsp" class="right">新用户注册</a>
        </div>
        <div>
            <input id="flag" name="flag" type="text" value="1" style="display:none;">
        </div>
    </form>
</div>


</body>
</html>
