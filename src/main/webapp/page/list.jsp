<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<script type="text/javascript" src="/admin/js/jquery-1.8.3.js"></script>
<script>
    $(function () {
        //加载页面显示街道
        showStreet($("#district").val());

        $("#district").change(function (){
            showStreet($("#district").val())});
    })
    //显示街道
    function showStreet(did) {
        $.post("selectStreetBydid",{"did":did},
            function (data) {
                //清空option
                $("#street>option:gt(0)").remove();
                for(var i=0;i<data.length;i++){
                    //添加一个dom节点
                    var option=$("<option value="+data[i].id+">"+data[i].name+"</option>");
                    $("#street").append(option);
                }
                //街道回显
                //$("#street>option[value='<%--${house.streetId}--%>']").prop("selected","true");
                $("#street").val(${houseCondition.streetId});
            },"json")
    }
    //查询分页
  function goPage(pageNum) {
      $("#page").val(pageNum);
      $("#sform").submit();
  }
</script>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
<DL class="search clearfix">
  <FORM id=sform action="/page/goList" method=post>
  <%--标题,起止价格,区域,街道,房型,面积--%>
  标题:<input type="text" name="title" <c:if test="${houseCondition.title!=null}">value="${houseCondition.title}"</c:if>/>
  起始价格:<input type="text" name="startPrice" <c:if test="${houseCondition.startPrice!=null}">value="${houseCondition.startPrice}"</c:if>/>
  截止价格:<input type="text" name="endPrice" <c:if test="${houseCondition.endPrice!=null}">value="${houseCondition.endPrice}"</c:if>/><br/>
  区域:<select name="districtId" id="district">
    <option value="0">请选择</option>
    <c:forEach items="${districts}" var="d">
    <OPTION value=${d.id} <c:if test="${houseCondition.districtId==d.id}">selected</c:if>>${d.name}</OPTION>
    </c:forEach>
  </select>
  街道:<select name="streetId" id="street">
    <option value="0">请选择</option>
  </select>
  户型:<select name="typeId" id="type">
    <option value="0">请选择</option>
    <c:forEach items="${types}" var="t">
      <OPTION value=${t.id} <c:if test="${houseCondition.typeId==t.id}">selected</c:if>>${t.name}</OPTION>
    </c:forEach>
  </select>
  面积:<SELECT id="area" name=area> <OPTION selected  value="">不限</OPTION>
    <OPTION value="0-40">40以下</OPTION> <OPTION value="41-80">41-80</OPTION>
    <OPTION value="81-500">81-500</OPTION>
  </SELECT>
  <script type="text/javascript">
      //面积回显
    $("#area").val("${houseCondition.area}")
  </script>
<%--页码--%>
    <input type="hidden" name="page" id="page">
<%--提交按钮--%>
   <LABEL class=ui-blue>
   <INPUT type="submit" onclick=doSearch() value=搜索房屋  ></LABEL>
  </FORM></DL></DIV>
<DIV class="main wrap">
<TABLE class=house-list>
  <TBODY>
<c:forEach items="${pageInfo.list}" var="h">
  <TR>
    <TD class=house-thumb><span><A href="/page/selectOneHouse?id=${h.id}" target="_blank"><img src="http://localhost:80/${h.path}?a=<%=Math.random()%>" width="100" height="75"></a></span></TD>
    <TD>
      <DL>
        <DT><A href="/page/selectOneHouse?id=${h.id}" target="_blank">${h.title}</A></DT>
        <DD>${h.dname}${h.sname},${h.floorage}平米<BR>联系方式：${h.contact} </DD></DL></TD>
    <TD class=house-type>${h.tname}</TD>
    <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD></TR>
</c:forEach>
  </TBODY>
</TABLE>
<DIV class=pager>
<UL>
  <LI class=current><A href="javascript:goPage(1)">首页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.pageNum-1==0?1:pageInfo.pageNum-1})">上一页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.pageNum+1>pageInfo.pages?pageInfo.pages:pageInfo.pageNum+1})">下一页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.pages})">末页</A></LI></UL>
  <SPAN class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY>
</HTML>
