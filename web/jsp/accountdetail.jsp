<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/8/4
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .td_one{
            width: 10%;
            height: 100%;
        }
        .td_two{
            width: 20%;
            height: 100%;
        }
    </style>
    <script>
        function ba() {
            window.history.back(-1  );
        }
    </script>
    <style>
        a:hover{
            color: red;
        }
    </style>
</head>
<body>
    <table border="1px" style="width:100%;border-collapse: collapse;">
        <tr  style="width: 100%;height: 50px;" align="center">
            <td class="td_one">订单编号：</td>
            <td class="td_two">${account._id}</td>
            <td class="td_one">下单时间：</td>
            <td class="td_two">${time}</td>
            <td class="td_one">付款方式：</td>
            <td class="td_two">${account.payway}</td>
        </tr>

        <tr style="width: 100%;" align="center">
            <td class="td_one">发货方式：</td>
            <td class="td_two">${account.getway}</td>
            <td class="td_one">收货人：</td>
            <td class="td_two">${account.name}</td>
            <td class="td_one">收货地址：</td>
            <td class="td_two">${account.address}</td>
        </tr>

        <tr style="width: 100%;" align="center">
            <td class="td_one">收货人邮编：</td>
            <td class="td_two">${account.postalcode}</td>
            <td class="td_one">收货人电话：</td>
            <td class="td_two">${account.phone}</td>
            <td class="td_one">收货人邮箱：</td>
            <td class="td_two">${account.email}</td>
        </tr>
    </table>
    <table style="width: 100%;border-collapse: collapse;margin-top: 3%;" border="1px">
        <tr align="center" style="width: 100%;">
            <td>商品编号</td>
            <td>商品图片</td>
            <td>商品名称</td>
            <td>商品数量</td>
            <td>商品单价</td>
            <td>商品小计</td>
        </tr>
        <% int index = 0;%>
        <c:forEach items="${goods}" var="g">
            <tr style="width: 100%;" align="center">
                <td>${g.goodsid}</td>
                <td style="width: 15%;height: 50px;" align="center"><img style="width: 80%;height: 100%;" src="../${g.goodsimage}"></td>
                <td>${g.goodsname}</td>
                <c:forEach items="${number}" var="n" begin="<%=index%>" end="<%=index%>">
                    <td>${n}</td>
                </c:forEach>
                <td>${g.goodsprice}</td>
                <c:forEach items="${price}" var="p" begin="<%=index%>" end="<%=index%>">
                    <td>${p}</td>
                </c:forEach>
            </tr>
            <%index++;%>
        </c:forEach>
        <tr>
            <td colspan="6" align="right">
                总共有<%=index%>类商品&nbsp;/&nbsp;共有${totalnumber}个商品&nbsp;|&nbsp;共计：${totalprice}元
            </td>
        </tr>
    </table>

    <table style="width: 100%;border-collapse: collapse;margin-top: 3%;" border="1px">
        <tr style="width: 100%;">
            <td style="width: 13%;">审核状态：</td>
            <td style="width: 15%">${flag}</td>
            <td style="width: 10%;">审核人：</td>
            <td style="width: 15%;">${account.adminid}</td>
            <td style="width: 13%;">审核时间：</td>
            <td>${extime}</td>
        </tr>
        <tr>
            <td>反馈：</td>
            <td colspan="5">${account.examineinfor}</td>
        </tr>
        <tr id="back">
            <td colspan="6" align="center">
                <a style="cursor: pointer;" onclick="ba()">返回</a>
            </td>
        </tr>
    </table>
</body>
</html>
