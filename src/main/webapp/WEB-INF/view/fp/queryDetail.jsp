<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String basePath = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询发票</title>
<script type="text/javascript" src="<%=basePath %>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>/jquery/jquery.js"></script>

<script type="text/javascript">


</script>


</head>
<body>
<h4>查询条件</h4>
<form action="<%=basePath %>/report/query" method="post">
	开始时间：<input id="beginDate" name="beginDate" class="Wdate" 
	onfocus="var endDate=$dp.$('endDate');WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})" 
	value="${param.beginDate }" style="width: 100px;"/>-结束时间
	<input id="endDate" name="endDate" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'beginDate\')}'})" 
	value="${param.endDate }" style="width: 100px;"/>
	<input type="submit" value="查询">  <a href="javascript:void(0)" onclick="exportData()">导出</a>
</form><br/>
<form action="<%=basePath%>/report/download" method="post">
<table border="1" cellspacing="0" >
	<tr>
		<td><input type="checkbox" id=""></td>
		<td>序号</td>
		<td>订单号</td>
		<td>申请人</td>
		<td>会员名</td>
		<td>会员ID</td>
		<td>订单时间</td>
		<td>申请时间</td>
		<td>发票抬头</td>
		<td>发票类型</td>
		<td>发票种类</td>
		<td>申请入口</td>
		<td>金额</td>
		<td>收货人</td>
		<td>收货人电话</td>
		<td>寄送地址</td>
		<td>邮寄时间</td>
		<td>发票号码</td>
		<td>发货人</td>
		<td>物流公司 </td>
		<td>物流单号</td>
		<td>退款状态</td>
		<td>发票状态</td>
	</tr>
	<c:forEach items="${fpxxList}" var="fp">
		<tr><td><input type="checkbox" id ="${fp.ddh}" name="ddh4ept" value="${fp.ddh}"></td>
			<td><c:out value="${fp.number}"/></td>
			<td><c:out value="${fp.ddh}"/></td>
			<td><c:out value="${fp.sqr}"/></td>
			<td><c:out value="${fp.hym}"/></td>
			<td><c:out value="${fp.hyid}"/></td>
			<td><c:out value="${fp.ddsj}"/></td>
			<td><c:out value="${fp.sqsj}"/></td>
			<td><c:out value="${fp.fptt}"/></td>
			<td><c:out value="${fp.fplx}"/></td>
			<td><c:out value="${fp.fpzl}"/></td>
			<td><c:out value="${fp.sqrk}"/></td>
			<td><c:out value="${fp.je}"/></td>
			<td><c:out value="${fp.shr}"/></td>
			<td><c:out value="${fp.shrdh}"/></td>
			<td><c:out value="${fp.jsdz}"/></td>
			<td><c:out value="${fp.yjsj}"/></td>
			<td><c:out value="${fp.fphm}"/></td>
			<td><c:out value="${fp.fhr}"/></td>
			<td><c:out value="${fp.wlgs}"/></td>
			<td><c:out value="${fp.wldh}"/></td>
			<td><c:out value="${fp.tkzt}"/></td>
			<td><c:out value="${fp.fpzt}"/></td>
		</tr>
	</c:forEach>
</table>
</form>
</body>
</html>