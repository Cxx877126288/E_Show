<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 安之若命丶
  Date: 2019/8/8

  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        function imageChange() {
            var reads = new FileReader();
            f = document.getElementById('fileClick').files[0];
            reads.readAsDataURL(f);
            reads.onload = function(e) {
                document.getElementById("image").src = this.result;
            };
        }

        function btnClick(btn) {
            var filesrc = document.getElementById("fileClick");
            var title = document.getElementById("title").innerText;
            var image;

            var flag = -1;
            if(title == "新增商品"){
                flag = 1;
                image = filesrc.value;
            }else{
                flag = 2;

                if(filesrc.value == null || filesrc.value=="") {
                    image = document.getElementById("image").src;
                }else{
                    image = filesrc.value;
                }
            }

            var goodsid = btn.id;
            var goodsname = document.getElementById("name").value;
            var secondname = document.getElementById("seco").value;
            var goodsprice = document.getElementById("price").value;

            var goodsinfor = document.getElementById("goodsinfor").value;
            var goodsnumber = document.getElementById("number").value;

            var inputdata = "flag=" + flag + "&goodsname=" + goodsname + "&secondname=" + secondname +"&goodsprice=" + goodsprice +
                "&image=" + image +"&goodsinfor=" + goodsinfor +"&goodsnumber=" + goodsnumber+"&goodsid=" + goodsid ;
            $.ajax({
                "url": "../addgoods",
                "data": inputdata,
                "type": "post",
                "dataType":"text",
                "success": function (data, textStatus) {
                    if (data != null) {
                        alert(data);
                        parent.history.back(-1);
                    }
                }
            });
        }

        function seclick(op) {
            $.ajax({
                "url": "../initSelect",
                "data": "fname=" + op.value,
                "type": "post",
                "dataType":"json",
                "success": function (data, textStatus) {
                    if (data != null) {
                        var json=eval(data);
                        $("#seco").find("option").remove();
                        $("#seco").append("<option value=null>请选择</option>");
                        $.each(json,function (index, item) {
                            var s1=json[index].secondname;
                            $("#seco").append("<option value=" + s1+">" +s1 + "</option>");
                        })
                    }
                }
            });
        }

        function searchImg() {
            document.getElementById("fileClick").click();
        }

    </script>
</head>
<body>
<form>
    <div  style="width: 100%;height: 100%;float: left;">
        <div id="title" style="width: 100%;font-size: 30px;" align="center">${title}</div>
        <div style="width: 100%;height: 80%;" >
            <table>
                <tr>
                    <td>商品名称：</td>
                    <td><input id="name" type="text" value="<c:if test="${goods != null}">${goods.goodsname}</c:if>"></td>
                </tr>

                <tr>
                    <td>商品大类：</td>
                    <td><select id="cate" style="width: 100px;">
                        <option value="" onclick="seclick(this)">请选择</option>
                        <c:forEach items="${cate}" var="c">
                            <option value="${c.commodityname}" onclick="seclick(this)" <c:if test="${goods != null}">
                                <c:if test="${c.commodityname.equals(cname)}">
                                    selected="true"
                                </c:if>
                            </c:if> >${c.commodityname}</option>
                        </c:forEach>
                    </select></td>
                </tr>

                <tr>
                    <td>商品小类：</td>
                    <td><select id="seco" style="width: 100px;">
                        <option value="">请选择</option>
                        <c:forEach items="${sname}" var="s">
                            <option value="${s.secondname}" <c:if test="${goods != null}">
                                <c:if test="${s.secondname.equals(goods.second_name)}">
                                    selected="true"
                                </c:if>
                            </c:if>>${s.secondname}</option>
                        </c:forEach>
                    </select></td>
                </tr>

                <tr>
                    <td>商品价格：</td>
                    <td>
                        <input id="price" type="text" <c:if test="${goods != null}">value="${goods.goodsprice}" </c:if>>
                    </td>
                </tr>

                <tr>
                    <td align="center">商品图片：</td>

                    <td>
                        <img id="image" style="width: 100px;height: 100px;" src="<c:if test="${goods != null}">../${goods.goodsimage}</c:if>
                        <c:if test="${goods == null}">../images/addpicture.png</c:if>"
                             <c:if test="${goods != null}">onerror="this.src='../images/kong.gif'" </c:if>
                        onclick="searchImg()"
                    </td>
                </tr>

                <tr>
                    <td>
                        <c:if test="${goods == null}">上传图片：</c:if>
                        <c:if test="${goods != null}">重新上传：</c:if>
                    </td>
                    <td>
                        <input type="button" value="浏览" onclick="searchImg()">
                    </td>
                </tr>

                <tr>
                    <td>商品描述：</td>
                    <td>
                        <textarea id="goodsinfor" rows="5" cols="35"><c:if test="${goods != null}">${goods.goodsinfor}</c:if></textarea>
                    </td>
                </tr>

                <tr>
                    <td>商品数量：</td>
                    <td><input  type="text" id="number" value="<c:if test="${goods != null}">${goods.goodsnumber} </c:if>"> </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input id="<c:if test="${goods != null}">${goods.goodsid}</c:if> " type="button" value="提交" onclick="btnClick(this);">
                        <input type="reset" value="重置" style="margin-left:20%;">
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <input type="file" id="fileClick" style="display: none;" accept="image/*" onchange="imageChange()">
</form>

</body>
</html>
