<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
  <TITLE>青鸟租房 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
  <LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
</HEAD>
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
         $("#street>option").remove();
           for(var i=0;i<data.length;i++){
              //添加一个dom节点
              var option=$("<option value="+data[i].id+">"+data[i].name+"</option>");
              $("#street").append(option);
           }
           //街道回显
          //$("#street>option[value='<%--${house.streetId}--%>']").prop("selected","true");
          $("#street").val(${house.streetId});
    },"json")
 }
</script>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新房屋信息发布</DT>
  <DD class=past>填写房屋信息</DD></DL>
<DIV class=box>
<FORM id=add_action method=post name=up.action
action="upHouse" enctype="multipart/form-data">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>${fabuInfo}
    <TD class=field>标　　题：</TD>
    <input type="hidden" name="id" value="${house.id}">
    <TD><INPUT id=add_action_title class=text
               type=text id="t" name=title value="${house.title}"> </TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD><SELECT class=text name=typeId id="type">
      <c:forEach items="${types}" var="t">
        <OPTION <c:if test="${t.id==house.typeId}">selected</c:if> value=${t.id}>${t.name}</OPTION>
      </c:forEach>
    </SELECT>
    </TD></TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><INPUT id=add_action_floorage class=text
               type=text name=floorage value="${house.floorage}"> </TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD><INPUT id=add_action_price class=text
               type=text name=price value="${house.price}"> </TD></TR>
  <TR>
    <TD class=field>发布日期：</TD>
    <TD><INPUT class=text type=date name=pubdate
               value="<f:formatDate value="${house.pubdate}" pattern="yyyy-MM-dd"/>"></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>区：<SELECT class=text name=districtId id="district">
      <c:forEach items="${districts}" var="d">
        <OPTION <c:if test="${d.name==house.dname}">selected</c:if> value=${d.id}>${d.name}</OPTION>
      </c:forEach>
    </SELECT>
      街：<SELECT class=text name=streetId id="street">
      </SELECT>
    </TD></TR>
  <TR>
    <TD class=field>联系方式：</TD>
    <TD><INPUT id=add_action_contact class=text
               type=text name=contact value="${house.contact}"> </TD></TR>
  <TR>
  <TR>
    <TD class=field>图片：</TD>
    <TD><INPUT type=file id="pic" name="picFile" class=text><br/>
        <input type="hidden" name="oldFile" value="${house.path}">
         <img src="http://localhost:80/${house.path}?a=<%=Math.random()%>" width="100"> </TD></TR>
  <TR>
    <TD class=field>详细信息：</TD>
    <TD><TEXTAREA name=description>${house.description}</TEXTAREA></TD></TR>
  </TBODY></TABLE>
<DIV class=buttons>
  <INPUT type=submit value=立即更新>
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
