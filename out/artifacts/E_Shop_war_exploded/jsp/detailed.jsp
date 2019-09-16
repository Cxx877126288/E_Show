<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/7/9
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>详细信息</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/detailed.css">
    <%
        String userid = request.getParameter( "userid" );
        String userpwd = request.getParameter( "userpwd" );
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

    %>
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        function addClick(btn) {
            var id = <%= userid %>;
            if(id == -1 ){
                //为空就要跳转登陆界面
                window.location.href = "../jsp/login.jsp";
            }
            else {
                //不为空直接添加到购物车
                var getuserid = <%= userid %>;
                var divs = $('div');
                var getgoodsid = divs[0].id;
                var userpwd = document.getElementById("userpwd").value;
                $.ajax({
                    "url": "../insBuycar",
                    "data": "userid=" + getuserid + "&goodsid=" + getgoodsid,
                    "type": "post",
                    "dataType": "text",
                    "success": function (data, textStatus) {
                        if (data != null) {
                            alert(data);
                            if(data.trim(data) == "添加购物车成功"){
                                var f=document.createElement('form');
                                f.style.display='none';
                                f.action='main';
                                f.method='post';
                                f.innerHTML = '<input type="hidden" name="userid" value="'+ getuserid +'"/>';
                                f.innerHTML += '<input type="hidden" name="userpwd" value="'+ userpwd +'"/>';
                                f.innerHTML += '<input type="hidden" name="flag" value="2"/>';
                                document.body.appendChild(f);
                                f.submit();
                            }
                        }
                    }
                });
            }
        }

    </script>
</head>
<body>
<form method="post" action="<%=basePath%>main">
    <div id="${goods.goodsid}" class="detailed clearfix">

        <div class="photo left">
            <!-- 插入一张图片 -->
            <img src="../images/sy.gif" style="width: 100%;height: 100%">
        </div>
        <div class="goodsname left">
            【商品名称】：${goods.goodsname}
        </div>
        <div class="goodsname left" >
            【商品价格】：${goods.goodsprice}
        </div>
        <div class="goodsname left"  >
            【商品数量】：${goods.goodsnumber}
        </div>

        <div class="buybtn">
            <input type="button" value="返回" class="left" style="width: 15%;background: #ffe4d0;color: #e5511d;" onClick="javascript :history.back();">
            <input type="button" class="left" value="加入购物车"style="width: 25%;background: #ff4400;color: #ffffff;" onclick="addClick(this)">
        </div>
        <div class="infor left">
            ${goods.goodsinfor}
        </div>
        <%--<input type="text" value="<%= userid %>" style="display: none">--%>
        <input id="userid" name="userid" value="<%= userid %>" style="display: none;">
        <input id="userpwd" name="userpwd" value="${userpwd}" style="display:none;">
    </div>
</form>
</body>
</html>
