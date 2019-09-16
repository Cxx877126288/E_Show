<%@ page import="com.gcxy.pojo.Buyuser" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gcxy.pojo.Buycar" %><%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/7/2
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        int goodsnumber = 0;
        if( request.getAttribute( "goodsnumber" ) != null){
            goodsnumber = Integer.parseInt( request.getAttribute( "goodsnumber" ).toString() );
        }
        double total_price = 0.0;
        if(request.getAttribute( "tprice" ) != null){
            total_price =  Double.parseDouble( request.getAttribute( "tprice" ).toString() );
        }

        String name = (String)request.getAttribute( "name" );
    %>

    <title>鑫星电子商城</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/test.css">
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">

        var itemHeight = 30;
        var dividerHeight = 1;
        function openMenu(obj) {
            menuTitleId = obj.id;
            menuId = "menu" + menuTitleId.substring(10);
            indicatorId = "indicator" + menuTitleId.substring(10);

            menu = document.getElementById(menuId);
            indicator = document.getElementById(indicatorId);
            height = menu.style.height;

            if (height == "0px" || height == "") {
                childAmount = menu.getElementsByTagName('div').length;
                dividerAmount = menu.getElementsByTagName('li').length;
                height = childAmount * itemHeight + dividerAmount * dividerHeight;
                menu.style.height = height + "px";
                indicator.style.transform = "rotate(180deg)";
            } else {
                menu.style.height = "0px";
                indicator.style.transform = "rotate(0deg)";
            }
        }
        function loginOut() {
            var login = document.getElementById("loginname");
            login.innerText = '请先登录';
            $('#loginout').css("display","none");
            location = location;
        }


        window.onload = function(){
            var login = document.getElementById("loginname");
            if(login.innerText == '请先登录'){
                login.href = "../jsp/login.jsp";
            }else {
                var loginid = $('#userid').val();
                login.href = "../jsp/ownercenter.jsp?userid=" + loginid + "&username=" +login.innerText;
                var login_out = document.getElementById("loginout");
                $('#loginout').css("display","block");
            }
        }
        function pay(b) {
            var login = document.getElementById("loginname");

            if(login.innerText == '请先登录'){
                if(confirm("请先登录")){
                    b.href = "../jsp/login.jsp";
                }
            }else {
                if(b.innerText == "个人中心"){
                    var loginid = $('#userid').val();
                    window.location.href = "../jsp/ownercenter.jsp?userid=" + loginid + "&username=" +login.innerText;
                }
                else if(b.innerText == "进入购物车"){
                    var car = document.getElementById('car');
                    if(car.childNodes[5].childNodes[0].nodeValue == '0'){
                        alert("购物车暂无商品")
                    }else{
                        var loginid = $('#userid').val();
                        window.location.href =  "/pay?userid=" + loginid;
                    }
                }
            }
        }

        function opclick(op) {
            $.ajax({
                "url": "../initSelect",
                "data": "fname=" + op.value,
                "type": "post",
                "dataType":"json",
                "success": function (data, textStatus) {
                    if (data != null) {
                        var json=eval(data);
                        $("#icate").find("option").remove();
                        $("#icate").append("<option value=null>请选择</option>");
                        $.each(json,function (index, item) {
                            var s1=json[index].secondname;
                            $("#icate").append("<option value=" + s1+">" +s1 + "</option>");
                        })
                    }
                }
            });
        }

        function search() {
            var category = $("#scate").val();   //获得的是null字符串  大类
            var second = $("#icate").val();   //小类
            var keyword = $("#keyword").val();   //什么都不输  是空字符串  不是null   关键字
            if(category == "null")
                category = null;
            if(second == "null")
                second = null;
            if (keyword == "")
                keyword = null;
            var iframe = document.getElementById("show");
            iframe.src = "/search?category=" + category + "&second=" + second + "&keyword=" + keyword + "&pagenumber=1";
        }

    </script>

</head>
<body>
<div class="mainpage clearfix">
    <div class="mainTitle">
        <div class="logo left">
            <img src="../images/myimages/logo.png">
        </div>
        <h1>欢&nbsp;迎&nbsp;来&nbsp;到&nbsp;购&nbsp;物&nbsp;广&nbsp;场&nbsp;！</h1>
    </div>
    <div style="width:75%;margin-left: 10%;height: 1%;">
        <img src="../images/myimages/line_head.jpg" height="100%" width="100%">
    </div>
    <div class="main_Login">
        <div class="welcome left">
            <b class="left" style="cursor: default">欢迎！</b>
            <a id="loginname"  class="left" style="color: red;text-decoration: none;"><%= name == null ? "请先登录" : name  %></a>
            <a id="loginout" class="right"  style=";text-decoration: none;margin-right: 15%;display: none;color: red" onclick="loginOut();">注销</a>
        </div>
    </div>

    <div style="width: 15%;  margin-left: 12%;   margin-top:1.2%;" class="left">
        <div class="goodscar" id="car">
           <br/>&nbsp;&nbsp;&nbsp;&nbsp;您的购物车:<br/>
                &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;共有 <strong><%= goodsnumber %></strong>&nbsp;件商品<br/>
                &nbsp;&nbsp;&nbsp;&nbsp;总价值:<br/>
                &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<strong><%= total_price %></strong>元<br/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
            <a onclick="pay(this);">个人中心</a>
            <a onclick="pay(this);">进入购物车</a>
        </div>
        <div class="mainHead left">
            <div class="category left">
                <div class="category-head">
                    全&nbsp;部&nbsp;分&nbsp;类
                </div>
                <%
                    int menu_titleID = 1;
                    int indicatorID=1;
                    int menu_ID=1;
                %>
                <div class="menus left">
                    <c:forEach items="${category}" var="c">
                        <div id="menu_title<%= menu_titleID %>" class="menu_title" onclick="openMenu(this)">
                                ${c.commodityname}
                            <div id="indicator<%=indicatorID%>" class="indicator">^</div>
                        </div>
                        <div class="menu" id="menu<%=menu_ID%>" >
                            <c:forEach items="${secondca}" var="s">
                                <c:if test="${c.commodityname.equals(s.follow_name)}">
                                    <div class="item">
                                        <a style="display: block;width: 100%;height: auto" href="<%= basePath %>show?secondname=${s.secondname}&pagenumber=1" target="show">
                                                ${s.secondname}
                                        </a>
                                    </div>
                                    <li class="item_divider"></li>
                                </c:if>
                            </c:forEach>
                        </div>
                        <%
                            menu_titleID++;
                            indicatorID++;
                            menu_ID++;
                        %>
                    </c:forEach>
                </div>
            </div >
        </div>
    </div>
    <div class="left" style="width: 58%;height: 120%;margin-left: 1%">
        <div style="width: 100%;height: 3%;margin-left: 3%">
            <select id="scate" style="width: 25%;height: 100%;">
                <option value="null" onclick="opclick(this)">请选择</option>
                <c:forEach items="${category}" var="c">
                    <option value="${c.commodityname}" onclick="opclick(this)">${c.commodityname}</option>
                </c:forEach>
            </select>

            <select id="icate" style="width: 25%;height: 100%;margin-left: 5%;">
                <option value="null">请选择</option>
            </select>
            关键字：
            <input type="text" id="keyword" placeholder="关键字" style="width: 20%;height: 100%;margin-left: 2%">
            <input type="button" value="查询" style="height: 100%;margin-left: 2%;" onclick="search()">
        </div>
        <div class="selectGoods ">
            <iframe id="show" src="/show?pagenumber=1" name="show" height="100%" width="100%" frameborder="0" scrolling="no"></iframe>
        </div>
    </div>
    <input type="text" id="userid" value="${userid}" style="display:none;">
</div>
</body>
</html>
