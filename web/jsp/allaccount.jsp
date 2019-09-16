<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/8/4
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function detailAccount(btn) {
            var tr = btn.parentNode.parentNode;
            window.location.href = "/accountdetail?account_id=" + tr.id;
        }
    </script>
    <style>
        a{
            cursor: pointer;
            text-decoration: none;
            color: black;
        }
        a:hover{
            color: red;
        }
    </style>
</head>
<body>
<div>
    <table border="1px" style="border-collapse: collapse;" align="center">
        <tr align="center">
            <td>订单编号</td>
            <td>下单时间</td>
            <td>总类数</td>
            <td>总件数</td>
            <td style="width: 100px;">总金额</td>
            <td>审核状态</td>
            <td>操作</td>
        </tr>
        <% int index = 0;%>
        <c:forEach items="${account}" var="a" >
            <tr align="center" id="${a._id}" style="height: 30px;">
                <td>${a._id}</td>
                <td><c:forEach items="${time}" var="t" begin="<%=index%>" end="<%=index%>">${t}</c:forEach></td>
                <td><c:forEach items="${category}" var="c" begin="<%=index%>" end="<%=index%>">${c}</c:forEach></td>
                <td><c:forEach items="${number}" var="n" begin="<%=index%>" end="<%=index%>">${n}</c:forEach></td>
                <td><c:forEach items="${price}" var="p" begin="<%=index%>" end="<%=index%>">${p}</c:forEach></td>
                <td><c:forEach items="${flag}" var="f" begin="<%=index%>" end="<%=index%>">${f}</c:forEach></td>
                <td><a id="accountdetail" style="cursor: pointer;color: red;" onclick="detailAccount(this);">查看详情</a></td>
            </tr>
            <% index++; %>
        </c:forEach>
    </table>
</div>
<div style="width: 100%;margin-top: 4%;" align="center">
    <div style="float: left;margin-left: 20%;width: 20%;">第${pagenumber}页 | 共${count}页</div>
    <div style="margin-left: 5%;width:50%;">
        <a href="/allaccount?userid=${userid}&pagenumber=1">首页</a>
        <a <c:if test="${pagenumber > 1}">href="/allaccount?userid=${userid}&pagenumber=${pagenumber - 1}"</c:if>
           <c:if test="${pagenumber <= 1}">href="/allaccount?userid=${userid}&pagenumber=${pagenumber}"</c:if>
        >上一页</a>
        <c:forEach var="i" begin="1" end="${count}">
            <a href="/allaccount?userid=${userid}&pagenumber=${i}">${i}</a>
        </c:forEach>
        <a
                <c:if test="${pagenumber < count}">href="/allaccount?userid=${userid}&pagenumber=${pagenumber + 1}"</c:if>
                <c:if test="${pagenumber >= count}">href="/allaccount?userid=${userid}&pagenumber=${pagenumber}"</c:if>
        >下一页</a>
        <a href="/allaccount?userid=${userid}&pagenumber=${count}">尾页</a>
    </div>
</div>

</body>
</html>
