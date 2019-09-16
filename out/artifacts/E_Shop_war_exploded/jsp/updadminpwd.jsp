<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/8/15
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <% String adminid = request.getParameter("adminid");%>
    <script type="text/javascript" src="../js/utils.js"></script>
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        function admsure() {
            var newpwd = document.getElementById("newpwd").value;
            var surepwd = document.getElementById("surepwd").value;
            if (isEmpty(newpwd) || isEmpty(surepwd)) {
                alert("请完整填写");
            }else {
                if (newpwd != surepwd) {
                    alert("两次密码不相同")
                } else {
                    $.ajax({
                        "url": "../updadminpwd",
                        "data": "newpwd=" + newpwd + "&adminid=" + <%=adminid%>,
                        "type": "post",
                        "dataType": "text",
                        "success": function (data) {
                            if (data != null) {
                                alert(data);
                                parent.document.location.href = "../jsp/adminlogin.jsp";
                            }
                        }
                    });
                }
            }
        }
    </script>
</head>
<body>
<form action="/updpwd" method="post">
    <table style="margin-left: 20%;width: 60%;border-collapse: collapse">

        <tr style="height: 40px;">
            <td align="center">新密码：</td>
            <td><input id="newpwd" name="newpwd" type="password" style="width: 80%;"></td>
        </tr>

        <tr style="height: 40px;">
            <td align="center">确认密码：</td>
            <td><input id="surepwd" name="surepwd" type="password" style="width: 80%;"></td>
        </tr>
        <tr style="height: 40px;">
            <td align="center" colspan="2">
                <input type="button" value="确认" onclick="admsure();">
                <input type="reset" value="重置" style="margin-left: 50px">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
