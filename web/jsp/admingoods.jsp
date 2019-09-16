<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/8/7
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        input[type="text"]{
            border: 0px;
            border-bottom: 1px solid black;
        }
        select{
            width: 100px;
        }
    </style>
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        function addgoods() {
            window.location.href = "/choicegoods";
        }

        function adSearch() {
            var cate = $("#cate").val();
            var seco = $("#seco").val();
            var keyword = $("#keyword").val();
            var lowprice = $("#lowprice").val();
            var highprice = $("#highprice").val();
            var price1 = 0.0,price2 = 0.0;
            if(cate == "")
                cate = null;
            if(seco == "")
                seco = null;
            if(keyword == "")
                keyword = null;
            if(lowprice == ""){
                lowprice = null;
            }else{
                price1 = parseFloat(lowprice)

            }
            if(highprice == ""){
                highprice = null;
            }else{
                price2 = parseFloat(highprice);
            }
            if(price2 - price1 >= 0)
                window.location.href = "/goods?secondname=" + seco + "&lowpirce=" + lowprice + "&highprice=" + highprice + "&keyword=" + keyword + "&pagenumer=1" + "&catename=" + cate;
            else{
                alert("后面的价格应比前面的大")
            }
        }

        function btnop(btn) {
            var tr = btn.parentNode.parentNode;
            if(btn.value == "修改"){
                window.location.href = "/choicegoods?goodsid=" + tr.id;
            }else if (btn.value == "删除"){
                $.ajax({
                    "url": "../delgoods",
                    "data": "goodsid=" + tr.id,
                    "type": "post",
                    "dataType":"text",
                    "success": function (data, textStatus) {
                        if (data != null) {
                           alert(data);
                           location = location;
                        }
                    }
                });
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
                        $("#seco").find("option").remove();
                        $("#seco").append("<option value=null>请选择</option>");
                        $.each(json,function (index, item) {
                            var s1=json[index].secondname;
                            $("#seco").append("<option value=" + s1+">" +s1 + "</option>");
                        })
                    }
                }
            });
        }
    </script>
</head>
<body>
<form>
    <table border="1px" style="border-collapse: collapse;width: 750px;margin-left: 15%;">
        <tr><td colspan="3" align="right"><input type="button" value="添加新商品" onclick="addgoods()"></td></tr>
        <tr>
            <td>所属大类：<select id="cate" >
                <option onclick="opclick(this);" value="">请选择</option>
                <c:forEach items="${cates}" var="ca">
                <option onclick="opclick(this);" value="${ca.commodityname}"
                        <c:if test="${ca.commodityname.equals(cate)}">selected="true"</c:if>
                >${ca.commodityname}</option>
                </c:forEach>
            </select></td>
            <td>所属小类：<select id="seco">
                <option value="">请选择</option>
                <c:if test="${secolist != null}">
                    <c:forEach items="${secolist}" var="ss">
                        <option <c:if test="${seco.equals(ss.secondname)}">
                            selected="true"
                        </c:if>>${ss.secondname}
                        </option>
                    </c:forEach>

                </c:if>
            </select></td>
            <td>关键字：<input id="keyword" type="text" value="<c:if test="${keyword != null && !keyword.equals('null')}">${keyword}</c:if>"></td>
        </tr>

        <tr>
            <td colspan="2">
                价格：<input type="text" id="lowprice" value="<c:if test="${lowprice != null && !lowprice.equals('null')}">${lowprice}</c:if>">
                至<input type="text" id="highprice" value="<c:if test="${highprice != null && !highprice.equals('null')}">${highprice}</c:if>"><span>&nbsp;&nbsp;价格最多两位小数</span>
            </td>
            <td align="center">
                <input type="button" value="查询" onclick="adSearch();">
            </td>
        </tr>
    </table>

    <table border="1px" style="border-collapse: collapse;width: 750px;margin-top: 3%;margin-left: 15%;">
        <c:if test="${goods != null}">
            <tr>
                <td align="center">商品图片</td>
                <td align="center">商品名称</td>
                <td align="center">商品单价</td>
                <td align="center">是否缺货</td>
                <td align="center">操  作</td>
            </tr>
            <c:forEach items="${goods}" var="g">
                <tr id="${g.goodsid}">
                    <td align="center" width="150px" height="50px"><img width="80%" height="100%" style="margin-left: 5%;" src="../${g.goodsimage}" onerror="this.src='../images/kong.gif'"></td>
                    <td align="center">${g.goodsname}</td>
                    <td align="center">${g.goodsprice}</td>
                    <td align="center"><c:if test="${g.goodsnumber > 0}">否</c:if> <c:if test="${g.goodsnumber <= 0}">是</c:if></td>
                    <td align="center">
                        <input type="button" value="修改" onclick="btnop(this)">
                        <input type="button" value="删除" onclick="btnop(this)" style="margin-left: 5%;">
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>

</form>

</body>
</html>
