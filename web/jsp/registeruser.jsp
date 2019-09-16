<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/8/20
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新用户注册</title>
</head>
<style>
    .td_one{
        width: 25%;
    }
    input[type="text"],input[type="tel"],input[type="number"],input[type="password"]{
        border: none;
        border-bottom: 1px solid black;
    }
</style>
<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../js/myCheck.js"></script>
<script type="text/javascript">
    function backLogin() {
        window.location.href = "/jsp/login.jsp";
    }

    function check() {
        var sex = $("#sex option:selected").val();
        var userid = document.getElementById("userid").value;
        var username = document.getElementById("name").value;
        var userpwd = document.getElementById("userpwd").value;
        var surepwd = document.getElementById("surepwd").value;
        var borntime = document.getElementById("borntime").value;
        var email = document.getElementById("email").value;
        var address = document.getElementById("address").value;
        var phone = document.getElementById("phone").value;
        var postalcode = document.getElementById("postalcode").value;
        if(checkHaveChinese(userid)){
            if(checkPwdLength(userpwd) && checkSurePwd(userpwd,surepwd)){
                if(checkBorntime(borntime)){
                    if(checkEmail(email)){
                        var data = "userid=" + userid + "&username=" + username + "&userpwd=" + userpwd + "&borntime=" + borntime
                            + "&email=" + email + "&address=" + address + "&phone=" + phone + "&postalcode=" + postalcode + "&sex=" + sex;
                        $.ajax({
                            "url": "../insuser",
                            "data": data,
                            "type": "post",
                            "dataType":"text",
                            "success": function (data, textStatus) {
                                if (data != null) {
                                    if(data.trim(data) == "注册成功"){
                                        alert(data);
                                        window.location.href = "/jsp/login.jsp";
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }
    }

</script>
<body>
<table border="1px" style="margin-left: 30%;width: 40%;border-collapse: collapse;margin-top: 10%;">

<tr>
    <td class="td_one" align="center">用户名</td>
    <td><input type="text" id="userid" name="userid" value="" maxlength="16"  style="width: 50%;" ><span style="color: red;font-size: 15px;">*最大输入16位</span></td>
</tr>
    <tr>
        <td class="td_one" align="center">密码</td>
        <td><input type="password" id="userpwd" name="userpwd" value="" maxlength="16"  style="width: 50%;" ><span style="color: red;font-size: 15px;">*密码为6-16位数字，字母，符号</span></td>
    </tr>
    <tr>
        <td class="td_one" align="center">确认密码</td>
        <td><input type="password" id="surepwd" name="surepwd" value="" maxlength="16"  style="width: 50%;" ><span style="color: red;font-size: 15px;">*请确认密码</span></td>
    </tr>
<tr>
    <td class="td_one"  align="center">性别</td>
    <td><select style="width:200px" id="sex"><option value="1">男</option><option value="2">女</option> </select></td>
</tr>
<tr>
    <td class="td_one"  align="center">真实姓名</td>
    <td><input type="text" style="width: 50%;" maxlength="6" id="name" name="name" value=""><span style="color: red;font-size: 15px;">*不超过6位汉字</span></td>
</tr>
<tr>
    <td class="td_one"  align="center">出生日期</td>
    <td><input type="text" style="width: 50%;" id="borntime" name="borntime" value="" placeholder="格式：xxxx-xx-xx"><span style="color: red;font-size: 15px;">*格式要正确</span></td>
</tr>
<tr>
    <td class="td_one"  align="center">电子邮箱</td>
    <td><input type="text" style="width: 50%;" id="email" name="email" value="" placeholder="格式：xx..xx@xx.com"><span style="color: red;font-size: 15px;">*格式要正确</span></td>
</tr>
<tr>
    <td class="td_one"  align="center">电话号码</td>
    <td><input type="tel" style="width: 50%;" maxlength="11" id="phone" name="phone" value=""><span style="color: red;font-size: 15px;">*不超过11位数字</span></td>
</tr>
<tr>
    <td class="td_one"  align="center">地址</td>
    <td><input type="text" style="width: 50%;" value="" id="address" name="address"><span style="color: red;font-size: 15px;">*不超过30位汉字</span></td>
</tr>
<tr>
    <td class="td_one"  align="center">邮编</td>
    <td><input type="number" style="width: 50%;" maxlength="6" value="" id="postalcode" name="postalcode"><span style="color: red;font-size: 15px;">*不超过6位数字</span></td>
</tr>

<tr>
    <td colspan="2" align="center">
        <input type="button" value="确认" onclick="check()">
        <input   type="button" value="返回" style="margin-left: 10%;" onclick="backLogin()">
    </td>
</tr>
</table>
</body>
</html>
