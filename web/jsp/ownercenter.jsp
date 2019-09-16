<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="com.gcxy.service.BuyuserService" %>
<%@ page import="com.gcxy.service.impl.BuyuserServiceImpl" %>
<%@ page import="com.gcxy.pojo.Buyuser" %><%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/8/2
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>个人中心</title>
    <%
        response.setCharacterEncoding( "utf-8" );
        request.setCharacterEncoding( "utf-8" );
        String userid = request.getParameter( "userid" );
        String username = request.getParameter( "username" );
        if(username == null){
        	username = (String)request.getAttribute( "username" );
        }
        ApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        BuyuserService bs = ac.getBean( "buyuserService",BuyuserServiceImpl.class );
        Buyuser buyuser = bs.selByID( userid );
        String userpwd = buyuser.getUserpwd();
    %>
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/test.css">
    <script type="text/javascript">
        function loginout() {
            var f=document.createElement('form');
            f.style.display='none';
            f.action='../main';
            f.method='post';
            document.body.appendChild(f);
            f.submit();
        }

        function jumpToMainPage() {
            var f=document.createElement('form');
            f.style.display='none';
            f.action='../main';
            f.method='post';
            f.innerHTML = '<input type="hidden" name="userid" value="'+ <%=userid%> +'"/>';
            f.innerHTML += '<input type="hidden" name="userpwd" value="'+ <%=userpwd%> +'"/>';
            f.innerHTML += '<input type="hidden" name="flag" value="'+ 2 +'"/>';
            document.body.appendChild(f);
            f.submit();
        }

    </script>
</head>
<body>
<div class="mainpage clearfix">
    <div class="mainTitle">
        <div class="logo left">
            <img src="../images/myimages/logo.png">
        </div>
        <h1>欢&nbsp;迎&nbsp;来&nbsp;到&nbsp;会&nbsp;员&nbsp;中&nbsp;心&nbsp;！</h1>
    </div>
    <div style="width:75%;margin-left: 10%;height: 1%;">
        <img src="../images/myimages/line_head.jpg" height="100%" width="100%">
    </div>
    <div class="right" style="margin-right: 20%;margin-top: 2%;">
        您好，尊敬的会员：<b style="color: #ff691a"><%=username%></b>
        &nbsp;&nbsp;&nbsp;<a onclick="loginout()" style="color: red;cursor: pointer;">注销</a>
    </div>
    <div class="left" style="width: 15%;height:30%;margin-left:10%;margin-top: 6%;">
        <div class="left"  style="width: 100%;border-spacing: inherit;background-image: url(../images/top_tt_bg.gif);
                background-repeat: no-repeat;background-size: 100% 100%;-moz-background-size: 100% 100%;height: 13%;" align="center">
            会员中心
        </div>
        <div class="left"  style="width: 100%;">
            <div class="left" style="width: 95.5%;height:12%;border-left: 1px solid red;border-right: 1px solid red;
                border-bottom: 1px dashed red;margin-left: 1.5%;padding-top: 2%;"
                 align="center">
                <img src="../images/q_images/agree_item.gif">
                <a target="owner" href="/userinfo?userid=<%=userid%>&flag=1" style="text-decoration: none;color: black;cursor: pointer;">基本资料显示</a>
            </div>

            <div class="left" style="width: 95.5%;height:12%;border-left: 1px solid red;border-right: 1px solid red;
                border-bottom: 1px dashed red;margin-left: 1.5%;padding-top: 2%;"
                 align="center">
                <img src="../images/q_images/agree_item.gif">
                <a target="owner" style="text-decoration: none;color: black;cursor: pointer;"  href="/userinfo?userid=<%=userid%>&flag=2">用户资料修改</a>
            </div>

            <div class="left" style="width: 95.5%;height:12%;border-left: 1px solid red;border-right: 1px solid red;
                border-bottom: 1px dashed red;margin-left: 1.5%;padding-top: 2%;"
                 align="center">
                <img src="../images/q_images/agree_item.gif">
                <a target="owner" style="text-decoration: none;color: black;cursor: pointer;" href="/userinfo?userid=<%=userid%>&flag=3">用户密码修改</a>
            </div>

            <div class="left" style="width: 95.5%;height:12%;border-left: 1px solid red;border-right: 1px solid red;
                border-bottom: 1px solid red;margin-left: 1.5%;padding-top: 2%;"
                 align="center">
                <img src="../images/q_images/agree_item.gif">
                <a href="/allaccount?userid=<%=userid%>&pagenumber=1" style="text-decoration: none;color: black;cursor: pointer;" target="owner">查询全部订单</a>
            </div>


            <div class="left" style="width: 95.5%;height:12%;border-left: 1px solid red;border-right: 1px solid red;
                border-bottom: 1px solid red;margin-left: 1.5%;padding-top: 2%;"
                 align="center">
                <img src="../images/q_images/agree_item.gif">
                <a style="text-decoration: none;color: black;cursor: pointer;" onclick="jumpToMainPage()">返回商城首页</a>
            </div>
        </div>

    </div>
    <div class="left" style="width: 58%;height: 70%;margin-top: 2%;margin-left: 2%;">
        <iframe id="owner" src="/userinfo?userid=<%=userid%>&flag=1" name="owner" height="100%" width="100%" frameborder="0" scrolling="no"></iframe>
    </div>
</div>
</body>
</html>
