<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/7/31
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        request.setCharacterEncoding( "utf-8" );
        String userid = request.getParameter( "userid" );
        String goodsids = request.getParameter( "goodsid" );
        String category = (String)request.getAttribute( "category" );
        String number = (String)request.getAttribute( "number" );
        String price = (String)request.getAttribute( "price" );
        DecimalFormat decimalFormat=new DecimalFormat(".00");
        String p = decimalFormat.format(Double.parseDouble( price ));
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        String time = sdf.format( new Date(  ) );
    %>
    <title>结算中心</title>
    <style type="text/css">
        .myBorder{
            border-collapse: collapse
        }
        .myTd_one{
            height: 25px;
            background: cyan;
            width: 150px;
            text-align: center;
        }
        .myTd_two{
            height: 30px;
            width: 350px;
        }
        input[type="text"]{
            width: 50%;;
            border-left:none;
            border-right:none;
            border-top:none;
            border-bottom:1px solid #0F2543;
        }

    </style>
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        function takeorder(){
            var trs = document.getElementsByTagName("tr");

            if(confirm("请确认订单")){
                var payway = document.getElementById("payway");
                var payway_text = payway.options[payway.selectedIndex].text;
                var getway = document.getElementById("getway");
                var getway_text = getway.options[getway.selectedIndex].text;

                var username = trs[7].getElementsByTagName("td")[1].childNodes[0].value;
                var address = trs[8].getElementsByTagName("td")[1].childNodes[0].value;
                var postalcode = trs[9].getElementsByTagName("td")[1].childNodes[0].value;
                var phone = trs[10].getElementsByTagName("td")[1].childNodes[0].value;
                var email = trs[11].getElementsByTagName("td")[1].childNodes[0].value;
                var time = trs[3].getElementsByTagName("td")[1].innerHTML;

                var goodsid = document.getElementById("goodsid").value;
                var number = document.getElementById("pernumber").value;

                var f=document.createElement('form');
                f.style.display='none';
                f.action='takeorder';
                f.method='post';
                f.innerHTML = '<input type="hidden" name="payway" value="'+ payway_text +'"/>';
                f.innerHTML += '<input type="hidden" name="getway" value="'+ getway_text +'"/>';
                f.innerHTML += '<input type="hidden" name="userid" value="'+ <%=userid%> +'"/>';
                f.innerHTML += '<input type="hidden" name="name" value="'+ username +'"/>';
                f.innerHTML += '<input type="hidden" name="goodsid" value="'+ goodsid +'"/>';
                f.innerHTML += '<input type="hidden" name="number" value="'+ number +'"/>';
                f.innerHTML += '<input type="hidden" name="address" value="'+ address +'"/>';
                f.innerHTML += '<input type="hidden" name="postalcode" value="'+ postalcode +'"/>';
                f.innerHTML += '<input type="hidden" name="phone" value="'+ phone +'"/>';
                f.innerHTML += '<input type="hidden" name="email" value="'+ email +'"/>';
                f.innerHTML += '<input type="hidden" name="time" value="'+ time +'"/>';
                document.body.appendChild(f);
                f.submit();
           }
        }
    </script>
</head>
<body>
<form>
    <div  class="tableStyle" style="margin-left: 30%;">
        <table id="account" border="1px" class="myBorder">
            <tr>
                <td class="myTd_one">所购买商品种类数</td>
                <td class="myTd_two"><%=category%></td>
            </tr>
            <tr>
                <td  class="myTd_one">所购买商品总数目</td>
                <td class="myTd_two"><%=number%></td>
            </tr>
            <tr>
                <td class="myTd_one">所购买商品总价格</td>
                <td class="myTd_two"><%=price%></td>
            </tr>
            <tr>
                <td class="myTd_one">订单购买时间</td>
                <td class="myTd_two"><%=time%></td>
            </tr>
            <tr>
                <td class="myTd_one">付款方式</td>
                <td class="myTd_two">
                    <select id="payway"  style="width: 50%;">
                        <option value="online">在线</option>
                        <option value="third">第三方支付</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="myTd_one">收货方式</td>
                <td class="myTd_two">
                    <select id="getway" style="width: 50%">
                        <option value="email">平邮</option>
                        <option value="express">快递</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2">------------------------------------------------------------------------------------------------</td>
            </tr>
            <tr>
                <td class="myTd_one">收货人姓名</td>
                <td class="myTd_two"><input type="text" value="${user.username}">
                    <span style="color: red;font-size: 15px;">*必填，不超过6位汉字</span>
                </td>
            </tr>
            <tr>
                <td class="myTd_one">收货人地址</td>
                <td class="myTd_two"><input type="text" value="${user.address}">
                    <span style="color: red;font-size: 15px;">*必填，不超过30位汉字</span>
                </td>
            </tr>
            <tr>
                <td class="myTd_one">收货人邮编</td>
                <td class="myTd_two"><input type="text" value="${user.postalcode}">
                    <span style="color: red;font-size: 15px;">*必填，不超过6位数字</span>
                </td>
            </tr>
            <tr>
                <td class="myTd_one">收货人电话</td>
                <td class="myTd_two"><input type="text" value="${user.phone}">
                    <span style="color: red;font-size: 15px;">*必填，不超过11位数字</span>
                </td>
            </tr>
            <tr>
                <td class="myTd_one">收货人邮箱</td>
                <td class="myTd_two"><input type="text" value="${user.email}">
                    <span style="color: red;font-size: 15px;">*必填，格式要正确</span>
                </td>
            </tr>
            <tr style="height: 40px">
                <td align="center" colspan="2">
                    <input style="width: 10%;" type="button" value="下单" onclick="takeorder()">
                    <input style="width: 10%;margin-left: 15%;" type="reset" value="重置">
                </td>
            </tr>
        </table>
        <input style="display: none" type="text" value="${goodsid}" id="goodsid">
        <input style="display: none" type="text" value="${pernumber}" id="pernumber">
    </div>

</form>
</body>
</html>
