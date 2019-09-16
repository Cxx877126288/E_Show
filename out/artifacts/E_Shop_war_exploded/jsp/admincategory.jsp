
<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/8/6
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        function trClick(btnTD) {
            var btn = btnTD.parentNode;
            var img = btn.getElementsByTagName("td")[0].firstChild;
            var trs = document.getElementsByTagName("tr");
            if(img.src.substring(29) == "-.gif"){
                for(var i = 0; i < trs.length;i++){
                    if(trs[i].id.substring(1) == btn.id.substring(1)){
                        if(trs[i].id.indexOf("m") == -1)
                            trs[i].style.display = "none";
                    }
                }
                img.src = "../images/+.gif";
            }else {
                for(var i = 0; i < trs.length;i++){
                    if(trs[i].id.substring(1) == btn.id.substring(1)){
                        if(trs[i].id.indexOf("m") == -1)
                            trs[i].style.display = "inline";
                    }
                }
                img.src = "../images/-.gif";
            }
        }

        function btnClick(btn) {
            var _id = btn.parentNode.id;
            //flag 1代表修改  2代表删除小类  3代表删除大类
            var data = "flag=";
            var tr = btn.parentNode.parentNode;
            var input = tr.getElementsByTagName("input")[0];
            if(btn.value == "修改"){
                data += "1&name=";

                if(input.value == ""){
                    alert("内容不能为空");
                }
                else{
                    if(confirm("是否修改为 " + input.value + " 类")){
                        data += input.value;
                        if(tr.id.indexOf("m") != -1){
                            data += "&id=c" + _id;
                        }
                        else{
                            data += "&id=s" + _id;
                        }

                    }
                }
            }else if (btn.value == "删除"){
                if(btn.parentNode.parentNode.id.indexOf("m") != -1){
                    alert("不能删除一个大类！！！");
                }else{
                    var tr = btn.parentNode.parentNode;
                    var input = tr.getElementsByTagName("input")[0];
                    if(confirm("是否删除 " + input.value + " 类，删除将清空所有货物以及购物车有该类商品的人，请确认...")){
                        data += "2" + "&id=s" + _id;

                    }
                }
            }
            $.ajax({
                "url": "../updsort",
                "data": data,
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

        function submitForm() {
            var insname = document.getElementById("insname").value;
            var option = $("#sel").val();  //选无是空串

            $.ajax({
                "url": "../addcategory",
                "data": "insname=" + insname + "&selid=" + option,
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

    </script>
</head>
<body>
    <p style="margin-left: 40%;margin-top: 3%">商品类别</p>
    <div style="margin-left: 20%;margin-top: 2%;">
        <table border="1px" style="border-collapse: collapse;">
            <% int index = 1;%>
            <tr align="center" style="width: 30px;">
                <td style="width: 70px;">编号</td>
                <td style="width: 250px;">类别名称</td>
                <td style="width: 100px;">父类编号</td>
                <td style="width: 120px;">操作</td>
            </tr>
            <c:forEach items="${category}" var="c">
                <tr style="display: inline;height: 30px;" id="m${c.commodityname}" >
                    <td onclick="trClick(this);"><img id="image" src="../images/-.gif"><%=index%></td>
                    <td><input style="width: 50%;border: 0px;border-bottom: 1px solid black;" type="text" value="${c.commodityname}"></td>
                    <td>0</td>
                    <td id="${c._id}" align="center">
                        <input type="button" value="修改" onclick="btnClick(this);">
                        <input type="button" value="删除" style="margin-left: 5%;" onclick="btnClick(this);">
                    </td>
                </tr>
                <%index++;%>
                <c:forEach items="${secondca}" var="s">
                    <c:if test="${c.commodityname.equals(s.follow_name)}">
                        <tr style="display: inline;" id="f${c.commodityname}">
                            <td align="right"><%=index%></td>
                            <td align="right">
                                <input style="width: 50%;border: 0px;border-bottom: 1px solid black;" type="text" value="${s.secondname}">
                            </td>
                            <td align="right">${c._id}</td>
                            <td id="${s._id}" align="center">
                                <input type="button" value="修改" onclick="btnClick(this);">
                                <input type="button" value="删除" style="margin-left: 5%;" onclick="btnClick(this);">
                            </td>
                        </tr>
                        <%index++;%>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </table>
    </div>
<form>
    <div style="margin-left: 18%;margin-top: 3%;">
        <p>添加分类:</p>
        <div style="margin-left:3%;">
            类别名称：<input id="insname"maxlength="12" type="text"><span style="color: red;">&nbsp;&nbsp;*&nbsp;新增商品类别不超过12个汉字</span>
        </div>
        <div style="margin-top: 3%;margin-left: 3%;">
            父类名称：
            <select id="sel">
                <option value="">无</option>
                <c:forEach items="${category}" var="g">
                    <option value="${g._id}">${g.commodityname}</option>
                </c:forEach>
            </select>
            <span style="color: red;">&nbsp;&nbsp;*&nbsp;选择无是添加大类，选择其他是添加到相应的大类</span>
        </div>
        <div style="margin-left: 15%;margin-top: 3%;">
            <input type="button" value="添加" onclick="submitForm();">
            <input type="reset" value="重置" style="margin-left: 8%;">

        </div>
    </div>

</form>

</body>
</html>
