<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/8/19
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        request.setCharacterEncoding( "utf-8" );
        String adminid = request.getParameter( "adminid" );
        String accountid = request.getParameter( "accountid" );
    %>
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        function submit() {
            var flag = document.getElementById("flag").value;
            var infor = document.getElementById("infor").value;
            var accountid = document.getElementById("accid").innerText;;
            var adminid = document.getElementById("adid").innerText;
            var data = "flag=" + flag + "&infor=" + infor + "&accountid=" + accountid + "&adminid=" + adminid;
            $.ajax({
                "url": "../examine",
                "data": data,
                "type": "post",
                "dataType":"text",
                "success": function (data, textStatus) {
                    if (data != null) {
                        alert(data)
                        if(data.trim(data) == "审核成功"){
                            window.history.back(-1);
                        }
                    }
                }
            });
        }

        function ba() {
            window.history.back(-1);
        }

    </script>
</head>
<body>
<table border="1px" style="border-collapse: collapse;">
    <tr>
        <td>订单编号</td>
        <td id="accid"><%=accountid%></td>
    </tr>
    <tr>
        <td>审核状态</td>
        <td><select id="flag">
            <option value="1">通过</option>
            <option value="-1">不通过</option>
        </select></td>
    </tr>
    <tr>
        <td>审核反馈</td>
        <td><textarea name="infor" id="infor" cols="30" rows="10"></textarea></td>
    </tr>
    <tr>
        <td>审核人</td>
        <td id="adid"><%=adminid%></td>
    </tr>
    <tr>
        <td></td>
        <td><input type="button" value="提交审核" onclick="submit()" >
            <input type="reset" value="重置" >
            <input type="button" value="返回" onclick="ba()" ></td>
    </tr>
</table>
</body>
</html>
