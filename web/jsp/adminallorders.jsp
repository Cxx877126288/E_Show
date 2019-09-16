<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/8/17
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        a{
            cursor: pointer;
        }
        a:hover{
            color: red;
        }
    </style>
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script>
        function search() {
            var userid = document.getElementById("userid").value;
            var starttime = document.getElementById("starttime").value;
            var endtime = document.getElementById("endtime").value;
            var flag = document.getElementById("se").value;
            var data = "userid=" + userid + "&starttime=" + starttime + "&endtime=" + endtime + "&flag=" + flag + "&pagenumber=1";
            window.location.href = "/selallaccount?" + data;
        }

        function aClick(aValue) {
            var data="&pagenumber=";
            var va = aValue.innerText;
            if( va == "上一页"){
                if(${pagenumber > 1}){
                    data += ${pagenumber - 1};
                }else{
                    data += ${pagenumber};
                    alert("已经是第一页")
                }
            }else if(va == "下一页"){
                if(${pagenumber < count}){
                    data += ${pagenumber + 1};
                }else{
                    data += ${pagenumber};
                    alert("已经是最后一页")
                }
            }else if( va == "首页"){
                data += 1;
            }else if(va =="尾页"){
                data += ${count}
            }else {
                data += va;
            }
            var userid = document.getElementById("userid").value;
            var starttime = document.getElementById("starttime").value;
            var endtime = document.getElementById("endtime").value;
            var flag = document.getElementById("se").value;
            var url = "userid=" + userid + "&starttime=" + starttime + "&endtime=" + endtime + "&flag=" + flag + data;
            window.location.href = "/selallaccount?" + url;
        }

        function btnClick(btn) {
            var tr = btn.parentNode.parentNode;
            var adid = parent.document.getElementById("adminid").innerText;
            if(btn.value == "查看"){
                window.location.href = "/accountdetail?account_id=" + tr.id;
            }else if(btn.value == "审核"){
                if(tr.getElementsByTagName("td")[3].innerHTML.trim(tr.getElementsByTagName("td")[3].innerHTML) == "未审核")
                    window.location.href = "/jsp/examine.jsp?accountid=" + tr.id + "&adminid=" + adid;
                else{
                    alert("该订单已审核")
                }
            }else{
                window.location.href = "/showupdaccount?account_id=" + tr.id;
            }
           // alert(tr.id + btn.value);
        }
    </script>
</head>
<body>
<table border="1px" style="border-collapse: collapse;margin-left: 8%;margin-top:3%" >
    <tr>
        <td width="250px">
            用户名&nbsp;：<input id="userid" type="text" value="<c:if test="${userid != null}">${userid}</c:if>">
        </td>
        <td width="400px">
            下单时间:<input type="text" id="starttime" value="<c:if test="${starttime != null}">${starttime}</c:if>" placeholder="例:2000-02-15">
            至:<input type="text" id="endtime" value="<c:if test="${endtime != null}">${endtime}</c:if>" placeholder="例:2000-02-16">
        </td>
        <td width="250px">
            审核状态：<select id="se" >
            <option value="2" <c:if test="${serflag != -1 && serflag != 0 && serflag != 1}">selected="true"</c:if>>请选择</option>
            <option value="-1" <c:if test="${serflag == -1}">selected="true"</c:if> >未通过</option>
            <option value="0" <c:if test="${serflag == 0}">selected="true"</c:if>>未审核</option>
            <option value="1" <c:if test="${serflag == 1}">selected="true"</c:if>>已通过</option>
        </select>
            <input type="button" value="查询" style="margin-left: 5%;" onclick="search()">
        </td>
    </tr>


</table>
<table border="1px" style="border-collapse: collapse;margin-top:3%;margin-left: 10%;" >
    <%int index = 0;%>
    <tr>
        <td style="width: 250px;" align="center">订单编号</td>
        <td style="width: 150px;" align="center">用户名</td>
        <td style="width: 200px;" align="center">下单时间</td>
        <td style="width: 100px;" align="center">审核状态</td>
        <td style="width: 150px;" align="center">操作</td>
    </tr>
    <c:forEach items="${accounts}" var="a">
        <tr id="${a._id}">
            <td align="center">${a._id}</td>
            <td align="center">${a.userid}</td>
            <td align="center"><c:forEach items="${time}" var="t" begin="<%=index%>" end="<%=index%>">
                ${t}
            </c:forEach></td>
            <td align="center">
            <c:forEach items="${flag}" var="f" begin="<%=index%>" end="<%=index%>">
                ${f}
            </c:forEach></td>
            <% index++; %>
            <td align="center"><input type="button" value="查看" onclick="btnClick(this)">
                <input type="button" value="审核" onclick="btnClick(this)">
                <input type="button" value="修改" onclick="btnClick(this)"></td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="5" align="center">
            第${pagenumber}页/共${count}页|
            <a onclick="aClick(this)">首页</a>|
            <a onclick="aClick(this)">上一页</a>|
            <c:forEach var="i" begin="1" end="${count}">
                <a onclick="aClick(this)">${i}</a>|
            </c:forEach>
            <a onclick="aClick(this)">下一页</a>|
            <a onclick="aClick(this)">尾页</a>

        </td>
    </tr>
</table>

</body>
</html>
