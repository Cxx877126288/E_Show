<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/8/3
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <title>Title</title>
    <%
        request.setCharacterEncoding( "utf-8" );
    String userid = request.getParameter( "userid" );
    %>
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        function submitClick(){
            var trs = document.getElementsByTagName("tr");
            var sex = trs[1].getElementsByTagName("td")[1].childNodes[0].value;
            var name = trs[2].getElementsByTagName("td")[1].childNodes[0].value;
            var borntime = trs[3].getElementsByTagName("td")[1].childNodes[0].value;
            var email = trs[4].getElementsByTagName("td")[1].childNodes[0].value;
            var phone = trs[5].getElementsByTagName("td")[1].childNodes[0].value;
            var address = trs[6].getElementsByTagName("td")[1].childNodes[0].value;
            var postalcode = trs[7].getElementsByTagName("td")[1].childNodes[0].value;

            $.ajax({
                "url": "../upduserinfor",
                "data": "userid=" + <%=userid%> + "&sex=" + sex + "&name=" + name + "&borntime=" + borntime+ "&email=" + email+ "&phone=" + phone+ "&address="
                + address+ "&postalcode=" + postalcode,
                "type": "post",
                "dataType":"text",
                "success": function (data, textStatus) {
                    if (data != null) {
                        alert(data);
                        var par_doc = parent.document;
                        par_doc.location.href = "../jsp/ownercenter.jsp?userid=" + <%=userid%> + "&username=" + name + "&flag";

                    }
                }
            });

        }
    </script>
    <style>
        .td_one{
            width: 25%;
        }
        input[type="text"],input[type="tel"],input[type="number"]{
            border: none;
            border-bottom: 1px solid black;
        }
    </style>
</head>
<body>
<table border="1px" style="margin-left: 20%;width: 60%;border-collapse: collapse">

    <tr>
        <td class="td_one" align="center">用户名</td>
        <td>${user.userid}</td>
    </tr>
    <tr>
        <td class="td_one"  align="center">性别</td>
        <td><input type="text" style="width: 50%;" maxlength="1" value="${sex}"><span style="color: red;font-size: 15px;">*仅输入男或女</span></td>
    </tr>
    <tr>
        <td class="td_one"  align="center">真实姓名</td>
        <td><input type="text" style="width: 50%;" maxlength="6" value="${user.username}"><span style="color: red;font-size: 15px;">*不超过6位汉字</span></td>
    </tr>
    <tr>
        <td class="td_one"  align="center">出生日期</td>
        <td><input type="text" style="width: 50%;" value="${borntime}"><span style="color: red;font-size: 15px;">*格式要正确</span></td>
    </tr>
    <tr>
        <td class="td_one"  align="center">电子邮箱</td>
        <td><input type="text" style="width: 50%;" value="${user.email}"><span style="color: red;font-size: 15px;">*格式要正确</span></td>
    </tr>
    <tr>
        <td class="td_one"  align="center">电话号码</td>
        <td><input type="tel" style="width: 50%;" maxlength="11" value="${user.phone}"><span style="color: red;font-size: 15px;">*不超过11位数字</span></td>
    </tr>
    <tr>
        <td class="td_one"  align="center">地址</td>
        <td><input type="text" style="width: 50%;" value="${user.address}"><span style="color: red;font-size: 15px;">*不超过30位汉字</span></td>
    </tr>
    <tr>
        <td class="td_one"  align="center">邮编</td>
        <td><input type="number" style="width: 50%;" maxlength="6" value="${user.postalcode}"><span style="color: red;font-size: 15px;">*不超过6位数字</span></td>
    </tr>
    <tr>
        <td class="td_one"  align="center">注册时间</td>
        <td>${register}</td>
    </tr>
    <tr>
        <td class="td_one"  align="center">最后登陆时间</td>
        <td>${last}</td>
    </tr>
    <tr>
        <td class="td_one"  align="center">登陆次数</td>
        <td>${login.logincount}</td>
    </tr>

    <tr>
        <td colspan="2" align="center">
            <input type="button" value="确认" onclick="submitClick()">
            <input style="margin-left: 15%;" type="reset" value="重置">
        </td>
    </tr>
</table>
</body>
</html>
