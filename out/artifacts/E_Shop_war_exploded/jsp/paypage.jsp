<%@ page import="com.gcxy.pojo.Buycar" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.gcxy.pojo.Goods" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/7/19
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>结算中心</title>
    <% String userid = request.getParameter("userid");%>
    <link type="text/css" href="../css/common.css">
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        function jump() {
            var form = document.forms[0];
            var cate = 0,number = 0,price = 0;
            var tests = $("input[type='checkbox']");
            var checkGoodsid = new Array();
            for(var i = 0;i < tests.length;i++){
                if(tests[i].checked){
                    var test = tests[i].parentNode.parentNode;
                    var sda = test.getElementsByTagName("td")[3].firstChild;
                    var goodsnumber = sda.value;
                    var sd = test.getElementsByTagName("td")[2];
                    var goodsprice = sd.innerHTML;
                    cate++;
                    number += parseInt(goodsnumber);
                    price += goodsnumber * goodsprice;
                    checkGoodsid.push(test.id);
                }
            }
            document.getElementById("userid").value = <%=userid%>;
            document.getElementById("goodsid").value = checkGoodsid;

            var msg = "您一共选择了" + number + "件商品，总价值" + price + "元\n" + "请确认是否购买";
            if(confirm(msg)){
                form.submit();
            }
        }
        function btnClick(btn) {
            var test = btn.parentNode.parentNode;
            var sda = test.getElementsByTagName("td")[3].firstChild;
            var carid = sda.id;
            var goodsnumber = sda.value;
            if( (goodsnumber == "")){
                alert("请输入修改的数量")
            }else{
                var data = "carid=" + carid + "&flag=";
                var flag = -1;
                if(btn.id == 'delete'){  //删除点击
                    flag = 1;
                    data += flag;
                }else if(btn.id == 'update'){   //修改点击
                    flag = 2;
                    data += flag + "&goodsnumber=" + goodsnumber ;
                }

                $.ajax({
                    "url": "../updatecar",
                    "data": data,
                    "type": "post",
                    "dataType":"text",
                    "async":false,
                    "success":function (msg) {
                        if(msg.trim(msg) == "删除成功")
                            location = location;
                        if(msg.trim(msg) == "修改成功")
                            location = location;
                    }
                });
            }
        }
        function back() {
            window.history.back(-1);
        }
    </script>
</head>
<body class="clearfix">
<form style="margin-left:20%; margin-top: 2%;width: 60%;height: 100%;" action="/order" method="post">
    <table border="1px">
        <tr style="text-align: center;">
            <td style="width: 10%;">缩略图</td>
            <td style="width: 10%;">商品名称</td>
            <td style="width: 8%;">商品单价</td>
            <td style="width: 10%;">商品数量</td>
            <td style="width: 10%;">商品小计</td>
            <td style="width: 10%;">操作</td>
            <td style="width: 3%;">选择</td>
        </tr>
        <%
            int index = 0;
            int GoodsTotalNumber = 0;
            double TotalPrice = 0.0;
        %>
        <c:forEach items="${goods}" var="g" begin="<%= index %>" >
            <tr align="center" style="text-align: center;" id="${g.goodsid}">
                <td align="center">
                    <div style="height: 70%;width: 70%;">
                        <img style="width: 100%;height: 100%;" src="../${g.goodsimage}">
                    </div>
                </td>
                <td>${g.goodsname}</td>
                <td>${g.goodsprice}</td>

                <c:forEach items="${cars}" var="c" begin="<%= index %>" end="<%= index %>" >
                    <td style="text-align: center;font-size: 20px"><input id="${c._id}" style="border:0px;width: 30%;" type="text"  autocomplete="off"
                               value="<fmt:formatNumber value=" ${c.goodsnumber}"  pattern="#" type="number"/>"></td>
                    <td><fmt:formatNumber value="${g.goodsprice * c.goodsnumber}"  pattern="#.00" type="number"/></td>
                    <%
                        List<Buycar> cars = (ArrayList)request.getAttribute( "cars" );
                        List<Goods> goods = (ArrayList)request.getAttribute( "goods" );
                        GoodsTotalNumber += (int)cars.get( index ).getGoodsnumber();
                        TotalPrice += (int)cars.get( index ).getGoodsnumber() * goods.get( index ).getGoodsprice();
                    %>
                </c:forEach>
                <td>
                    <input id="delete" type="button" value="删除" onclick="btnClick(this)">
                    <input id="update" type="button" value="修改" onclick="btnClick(this)" style="margin-left: 5%;">
                </td>
                <td>
                    <input type="checkbox" id="sure" checked="true">
                </td>
            </tr>
            <% index++; %>
        </c:forEach>
    </table>
    <div style="width: 100%;text-align: center;margin-top: 3%;">
        <div id="category" style="width: 15%;float: left;margin-left: 12%;">商品种类：<% out.print( index ); %>种</div>
        <div id="totalnumber" style="width: 20%; float: left">商品总数：<% out.print( GoodsTotalNumber ); %>个</div>
        <div id="totalprice" style="width: 23%; float: left">商品总价：<%
            DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
            String p=decimalFormat.format(TotalPrice);//format 返回的是字符串
            out.print( p); %>元</div>
        <div style="width: 20%;float: left;">
            <a style="text-decoration: none;color: red;cursor: pointer;" onclick="jump();">结算中心</a>
            <a style="text-decoration: none;color: red;cursor: pointer;margin-left: 3%;" onclick="back();">返&nbsp;&nbsp;回</a>


        </div>
    </div>
    <input style="display: none;" type="text" name="userid" id="userid">
    <input style="display: none;" type="text" name="goodsid" id="goodsid">
</form>

</body>
</html>
