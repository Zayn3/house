<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  //判断当前用户有没有登入,防盗链
  Object o=session.getAttribute("user");
  if(o==null){
    out.print("<script>alert('你还没有登入或超时');location.href='login.jsp';</script>");
  }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0050)http://localhost:8080/HouseRent/manage!list.action -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>青鸟租房 - 用户管理</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
  <LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR >
</HEAD>
<BODY>
<DIV id=header class=wrap>
  <DIV id=logo><IMG src="../images/logo.gif"></DIV>
<DIV class=search><div>欢迎:${user.name}</div><LABEL class="ui-green searchs"><a href="/page/goFabu">发布房屋信息</a></LABEL>
  <LABEL class="ui-green searchs"><a href="/page/selectPageHouseByUsersId">管理房屋信息</a></LABEL>
<LABEL class=ui-green>
  <INPUT onclick='document.location="index.jsp"' value="退       出" type=button name=search></LABEL>
</DIV></DIV>
<DIV class="main wrap">
<DIV id=houseArea>
<TABLE class=house-list>
  <TBODY>
  <c:forEach items="${pageInfo.list}" var="h">
    <TR>
      <TD class=house-thumb><SPAN><A href="/page/selectOneHouse?id=${h.id}" target="_blank"><img src="http://localhost:80/${h.path}?a=<%=Math.random()%>" width="100" height="75"></A></SPAN></TD>
      <TD>
        <DL>
          <DT><A href="/page/selectOneHouse?id=${h.id}" target="_blank" name="title">${h.title}</A> ${h.tname}</DT>
          <DD>${h.dname}${h.sname},${h.floorage}平米<BR>联系方式：${h.contact} </DD></DL>
      </TD>
      <td><c:if test="${h.ispass==1}">已核审</c:if>
          <c:if test="${h.ispass==0}">未核审</c:if></td>
      <TD class=house-type><LABEL class="ui-green searchs">
        <a href="/page/selectOneHouseAndDistrict?id=${h.id}">修改</a></LABEL></TD>
      <TD class=house-type><LABEL class="ui-green searchs">
        <a href="/page/delHouse?id=${h.id}">删除</a></LABEL></TD>
    </TR>
  </c:forEach>
  </TBODY></TABLE></DIV>
<DIV class=pager>
<UL>
  <c:forEach begin="1" end="${pageInfo.pages}" var="p" step="1">
    <LI class=current>
      <A id=widget_338868862 href="/page/selectPageHouseByUsersId?page=${p}"
         parseContent="true" showError="true" targets="houseArea" ajaxAfterValidation="false" validate="false"
         dojoType="struts:BindAnchor">${p}</A>
    </LI>
  </c:forEach>
   </LI></UL><SPAN class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
