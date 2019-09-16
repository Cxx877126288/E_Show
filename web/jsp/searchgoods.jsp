<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/7/23
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/showgoods.css">
<script type="text/javascript" src="../js/ajax.js"></script>
<script type="text/javascript" src="../js/utils.js"></script>
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">

    function carClick(btn) {
        var maindiv = btn.parentNode.parentNode;
        var par_doc = parent.document;
        if(btn.value == "加入购物车"){
            if(maindiv != null) {
                if (par_doc.getElementById("loginname").innerText == "请先登录") {
                    //未登陆就跳到登陆界面
                    if (confirm("请先登录"))
                        parent.window.location.href = '../jsp/login.jsp';
                } else {
                    var userid = par_doc.getElementById("userid").value;//获取到了用户id
                    var goodsid = maindiv.id;
                    $.ajax({
                        "url": "../insBuycar",
                        "data": "userid=" + userid + "&goodsid=" + goodsid,
                        "type": "post",
                        "dataType": "text",
                        "success": function (data, textStatus) {
                            if (data != null) {
                                alert(data);
                            }
                        }
                    });
                    var par_doc = parent.document;
                    var car = par_doc.getElementById('car');
                    car.childNodes[5].childNodes[0].nodeValue = parseInt(car.childNodes[5].childNodes[0].nodeValue) + 1;
                    car.childNodes[11].childNodes[0].nodeValue = (parseFloat(car.childNodes[11].childNodes[0].nodeValue) + parseFloat(maindiv.childNodes[5].id)).toFixed(2);
                }
            }
        }else if(btn.value == "查看详情"){
            var goodsid = maindiv.id;
            var userid = par_doc.getElementById("userid").value;
            var h = "/detailed?goodsid=" + goodsid;
            if(userid != null && userid != "")
                h += ("&userid=" + userid);
            else
                h += ("&userid=-1");
            par_doc.location.href = h;
        }
    }
</script>
<body>
<div class="goodsmainpage clearfix">
    <c:if test="${goods  == null}">
        没有商品
    </c:if>
    <c:if test="${goods != null}">
        <c:forEach items="${goods}" var="g">
            <div class="goods left" id="${g.goodsid}">
                <div class="photo">
                    <img src="../${g.goodsimage     }" title="${g.goodsname}">
                </div>
                <div class="info" title="${g.goodsinfor}">
                        ${g.goodsinfor}
                </div>
                <div class="price left" id="${g.goodsprice}" >
                    ¥${g.goodsprice}
                </div>
                <div class="mybutton left">
                    <input id="car" type="button" value="加入购物车" style="font-size: 6%;font-weight: bold;" onclick="carClick(this)" title="加入购物车">
                    <input id="information" type="button" value="查看详情" style="font-size: 6%;font-weight: bold;" onclick="carClick(this)" title="查看详情">
                </div>
            </div>
        </c:forEach>
        <div class="left clpage">
            第${nowpage}页/共${count}页
            <a href="/search?category=${category}&second=${second}&keyword=${keyword}&pagenumber=1">首页</a>
                <%--href="/show?secondname=${sename}&pagenumber=${nowpage - 1}"--%>
            <a
                    <c:if test="${nowpage > 1}"> href="/search?category=${category}&second=${second}&keyword=${keyword}&pagenumber=${nowpage - 1}"</c:if>
                    <c:if test="${nowpage <= 1}"> href="/search?category=${category}&second=${second}&keyword=${keyword}&pagenumber=${nowpage}"</c:if>
            >
            上一页</a>
            <c:forEach var="i" begin="1" end="${count}">
                <a href="/search?category=${category}&second=${second}&keyword=${keyword}&pagenumber=1">${i}</a>
            </c:forEach>
            <a
                    <c:if test="${nowpage < count}">href="/search?category=${category}&second=${second}&keyword=${keyword}&pagenumber=${nowpage + 1}"</c:if>
                    <c:if test="${nowpage >= count}">href="/search?category=${category}&second=${second}&keyword=${keyword}&pagenumber=${nowpage}"</c:if>
            >下一页</a>
            <a href="/search?category=${category}&second=${second}&keyword=${keyword}&pagenumber=1">尾页</a>
        </div>
    </c:if>
</div>
</body>
</html>
