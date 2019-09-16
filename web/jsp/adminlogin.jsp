<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/8/5
  Time: 16:19
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
        String aid = request.getParameter("adminid");
        String flag = request.getParameter( "flag" );
        if(flag != null && flag .equals( "1" )){
        	msg = "* 验 证 码 错 误";
        }else if(flag != null && flag .equals( "2" )){
        	msg = "* 用 户 名 或 密 码 错 误";
        }else{
        	msg = null;
        }
    %>
    <title>登陆</title>
    <link rel="stylesheet" type="text/css" href="../css/login.css">
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <script type="text/javascript" src="../js/utils.js"></script>
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        function aClick(){
            $("img").attr("src","/valid?data="+ new Date());
        }
    </script>

</head>
<body  style="    border-spacing: inherit;
    background-image: url('../images/myimages/adminloginbackground.png');
background-repeat: no-repeat;
background-size: 100% 100%;
-moz-background-size: 100% 100%;">
<div class="total">
    <form class="from" id="mainFrom"  action="<%=basePath%>admin" method="post">
        <div>
            <input id="adminid" name="adminid" type="text" value="<%= aid != null ? aid : "" %>" placeholder="用户名">
        </div>

        <div>
            <input id="adminpwd" name="adminpwd" type="password" placeholder="密码">
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
            <span class="myspan left"><%=  msg != null ? msg : "" %></span>
        </div>
        <div class="mybtn">
            <input type="submit" value="登录">
        </div>
        <div style="margin-top: 7%;">
            <a href="/jsp/login.jsp">返回用户登陆</a>
        </div>
    </form>
</div>

</body>
</html>

