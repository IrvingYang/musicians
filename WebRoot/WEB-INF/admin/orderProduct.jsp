<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript" src="<%=basePath %>js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript">
	function print()
	{
		$("#orderDetail").jqprint({ operaSupport:true,debug: true });
	}
</script>
<style type="text/css">
	td{
		border-collapse: collapse;
		border-color: blue;
	}
</style>
<div class="pageContent" id="orderDetail" style="width:1060px; height:auto;" layoutH="33">
	<h3>彝家优品-购物清单</h3>
	<div style="float: left; margin:10px 40px 20px 0px; width:30%;">订单编号：${orderList[0].orderId}</div>
	<fmt:formatDate value="${orderList[0].createTime}" pattern="yyyy-MM-dd HH:mm:ss" var="createtime"/>
	<div style="float: left; margin:10px 40px 20px 0px; width:30%;">下单时间：${createtime}</div>
	<div style="float: left; margin:10px 40px 20px 0px; width:30%;">客户姓名：${orderList[0].userAddress.name} </div>
	<table class="table" width="99%">
		<thead>
			<tr>
				<th width="120">
					<c:choose>
						<c:when test="${orderType==2}">团购编号</c:when>
						<c:when test="${orderType==1}">商品编号</c:when>
					</c:choose>
				</th>
				<th width="">商品名称</th>
				<th width="80">商品价格</th>
				<th width="80">单位</th>
				<th width="40">数量</th>
				<th width="80">小计</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${orderList[0].order_detail}" var="detail">
				<tr target="sid_user" rel="1">
					<td height="50">${detail.productId }</td>
					<td>
						<c:choose>
							<c:when test="${orderType==2}">${detail.groupBuy.product.productName}</c:when>
							<c:when test="${orderType==1}">${detail.product.productName}</c:when>
						</c:choose>
					</td>
					<td>${detail.price}
					</td>
					<td>
						<c:choose>
							<c:when test="${orderType==2}">${detail.groupBuy.unit}</c:when>
							<c:when test="${orderType==1}">${detail.product.unit}</c:when>
						</c:choose>
					</td>
					<td>${detail.quantity }</td>
					<td>${detail.totalamt }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="float: left; margin:20px 10px 10px 0px;">客户收货地址：</div>
	<div style="float: left; margin:10px 40px 10px 0px; width:100%;">
		${orderList[0].userAddress.district.city.state.stateName} 
		${orderList[0].userAddress.district.city.cityName} 
		${orderList[0].userAddress.district.districtName}
		${orderList[0].userAddress.street}
		邮政编码:
		${orderList[0].userAddress.postCode}
	</div>
	<br/>
	<div style="float: left; margin:10px 10px 10px 0px;">联系电话:</div>
	<div style="float: left; margin:10px 10px 10px 0px;">
		${orderList[0].userAddress.telephone}
	</div>
	
</div>
<div class="formBar">
	<ul>
		<li><div class="buttonActive"><div class="buttonContent" id="print"><button type="button" id="button" onclick="print();	">打印订单</button></div></div></li>
		<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
	</ul>
</div>
