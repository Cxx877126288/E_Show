<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/8/18
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script>
        function ba() {
            window.history.back(-1);
        }
        function Sumbit() {

            var payway =  $("#payway option:selected").text();
            var getway = $("#getway option:selected").text();
            var name = document.getElementById("username").value;
            var address = document.getElementById("address").value;
            var postalcode = document.getElementById("postalcode").value;
            var phone = document.getElementById("phone").value;
            var email = document.getElementById("email").value;
            var accountid = document.getElementById("id").innerHTML;

            var data = "accountid=" + accountid + "&payway=" + payway + "&getway=" + getway + "&name=" + name +
                        "&address=" + address + "&postalcode=" + postalcode + "&phone=" + phone + "&email=" + email;
            $.ajax({
                "url": "../updaccinfor",
                "data":data,
                "type": "post",
                "dataType":"text",
                "success": function (data, textStatus) {
                    if (data != null) {
                        alert(data);
                        window.history.back( -1 );
                    }
                }
            });
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
        <td class="td_two" id="id">${account._id}</td>
        <td class="td_one">下单时间：</td>
        <td class="td_two">${time}</td>
        <td class="td_one">付款方式：</td>
        <td class="td_two">
            <select id="payway"  style="width: 50%;">
                <option value="online" <c:if test="${account.payway =='在线'}">selected="true"</c:if> >在线</option>
                <option value="third" <c:if test="${account.payway =='第三方支付'}">selected="true"</c:if> >第三方支付</option>
            </select>
            </td>
    </tr>

    <tr style="width: 100%;" align="center">
        <td class="td_one">发货方式：</td>
        <td class="td_two">
            <select id="getway" style="width: 50%">
                <option value="email" <c:if test="${account.getway =='平邮'}">selected="true"</c:if>>平邮</option>
                <option value="express"  <c:if test="${account.getway =='快递'}">selected="true"</c:if>>快递</option>
            </select></td>
        <td class="td_one">收货人：</td>
        <td class="td_two"><input id="username" value="${account.name}"></td>
        <td class="td_one">收货地址：</td>
        <td class="td_two"><input id="address" value="${account.address}"></td>
    </tr>

    <tr style="width: 100%;" align="center">
        <td class="td_one">收货人邮编：</td>
        <td class="td_two"><input id="postalcode" value="${account.postalcode}"></td>
        <td class="td_one">收货人电话：</td>
        <td class="td_two"><input id="phone" value="${account.phone}"></td>
        <td class="td_one">收货人邮箱：</td>
        <td class="td_two"><input id="email" value="${account.email}"></td>
    </tr>
    <tr align="center">
        <td colspan="6" align="center">
            <input type="button" value="提交" onclick="Sumbit();">
            <input type="reset" value="重置" style="margin-left: 15%;">
        </td>
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
            总共有<%=index%>类商品&nbsp;/&nbsp;共有${totalnumber}种商品&nbsp;|&nbsp;共计：${totalprice}元
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