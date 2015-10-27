<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<script type="text/javascript">
$(function(){
	$("#leasemine").children("label").children("input[type='radio']").click(function(){
		if($(this).val()==0){
			$(".productordermine").hide();
		}else if($(this).val()==1){
			$(".productordermine").show();
		}
	});
});
</script>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="manage/order/getAllOrder_List.do" method="post">
	<input type="hidden" name="typeId" value="100"/>
	<div class="searchBar">
		<table class="searchContent" style="float:left;">
			<tr>
				<td>
					订单状态：
				</td>
				<td>
					<select name="status">
						<option value="">所有状态</option>
						<option value="01" ${status eq '01' ? 'selected':'' }>已下单</option>
						<option value="02" ${status eq '02' ? 'selected':'' }>已付款</option>
						<option value="03" ${status eq '03' ? 'selected':'' }>已发货</option>
						<option value="04" ${status eq '04' ? 'selected':'' }>已收货</option>
						<option value="05" ${status eq '05' ? 'selected':'' }>已完成</option>
					</select>
				</td>
				<td>
					下单日期：<input type="text" class="date" datefmt="yyyy-MM-dd" name="beginTime" value="${beginTime}" readonly /> 到  <input type="text" value="${endTime}" datefmt="yyyy-MM-dd" class="date" name="endTime" readonly />
				</td>
			</tr>
		</table>
		<div class="subBar" style="float:right;">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent" layoutH="40">
	<div class="panelBar">
		<ul class="toolBar">
<!-- 			<li><a class="add" href="manage/order/getOrderDetail.do?typeId=1&group=false&orderId={null}" target="dialog" mask="true" max="true" width="960" height="500" title="订单商品详细" id="productOrderDetail"><span>查看详细</span></a></li> -->
			<li><a class="delete" href="manage/lease/deleteProduct.do?typeId=1&orderId={null}" target="ajaxTodo" title="确定要删除吗?" id="leaseDelete" ><span>删除</span></a></li>
			<li><a class="edit" href="manage/lease/orderBusiness.do?action=confirmpay&typeId=1&orderId={null}" target="ajaxTodo"  title="确认第一条订单已经收到付款?" id="leaseConfirmpay"><span>确认付款</span></a></li>
			<li><a class="edit" href="manage/order/todelivery.do?orderId={null}" mask="true" target="dialog" id="leaseDelivery"  title="确认已经发货?" width="600" height="300" rel="productOrderdelivery"><span>发货</span></a></li>
<!-- 			<li><a class="edit" href="manage/product/orderBusiness.do?endOrder&typeId=1&orderId={null}" target="ajaxTodo" id="productOrderok" title="确认交易完成?"><span>已完成</span></a></li> -->
			<c:if test="${sessionScope.admin.partnerflag==0}">
				<li id="leasemine"  class="edit">显示非自营订单信息
					<label><input type="radio" name="leasemine" value="1" checked="checked"/>是</label>
					<label><input type="radio" name="leasemine" value="0" />否</label>
				</li>
			</c:if>
		</ul>	
	</div>
	<table id="leaseDatabases" class="display">	
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				<th>租赁编号</th>
				<th>用户ID</th>
				<th>租赁产品名称</th>
				<th>租赁类型</th>
				<th>总价格</th>
				<th>租赁状态</th>
				<th>租金</th>
				<th>创建时间</th>
				<th>租赁周期</th>
				<th>续租租赁订单</th>
				<th>结束时间</th>
				<th>实际结束时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${leaseList}" var="lease" varStatus="status">
				<c:set value="" var="class"/>
				<c:if test="${sessionScope.admin.partnerflag==0}">
					<c:if test="${sessionScope.admin.providerId!=lease.providerId}">
						<c:set value="productordermine" var="class"/>
					</c:if>
				</c:if>
				<tr rel="${lease.leaseId}" class="${classes}">
					<td>${lease.leaseId}</td>
					<td>${lease.userId}</td>
					<td>${lease.ext_shop.product.productName}</td>
					<td>${lease.leaseType}</td>
					<td>￥${lease.leaseOrderList.totalamt}</td>
					<td>
						<c:choose>
							<c:when test="${lease.status eq '01'}">已下单</c:when>
							<c:when test="${lease.status eq '02'}">已付款</c:when>
							<c:when test="${lease.status eq '03'}">已发货</c:when>
							<c:when test="${lease.status eq '04'}">已收货</c:when>
							<c:when test="${lease.status eq '05'}">已完成</c:when>
						</c:choose>
					</td>
					<td>${lease.ext_shop.originalPrice}</td>
					<td>${lease.createTime}</td>
					<td>${lease.leaseCycle} 天</td>
					<td>${lease.continueId}</td>
					<td>${lease.shouldEndTime}</td>
					<td>${lease.realEndTime }</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th>租赁编号</th>
				<th>用户ID</th>
				<th>租赁产品名称</th>
				<th>租赁类型</th>
				<th>总价格</th>
				<th>租赁状态</th>
				<th>租金</th>
				<th>创建时间</th>
				<th>租赁周期</th>
				<th>续租租赁订单</th>
				<th>结束时间</th>
				<th>实际结束时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#leaseDatabases').dataTable({
		         "sPaginationType" : "full_numbers",
		         "iDisplayLength" : "20",
		         "oLanguage": {
		 			"sLengthMenu": "每页显示 _MENU_ 条记录",
		 			"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
		 			"sInfoEmpty": "没有数据",
		 			"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
		 			"oPaginate": {  
		                     "sFirst": "首页",  
		                     "sPrevious": "上一页",
		                     "sNext": "下一页",  
		                     "sLast": "尾页"  
		                 }, 
				    "sSearch": "搜索:",
		 			"sZeroRecords": "没有检索到数据",  
		 			"sProcessing": "<img src='./loading.gif' />"
		         }
		     });
			 $('#leaseDatabases tbody').on( 'click', 'tr', function () {
			       $(this).toggleClass('selected');
			       prod_selected = $("#leaseDatabases").children("tbody").children(".selected");
			        var productIds = '';
						$.each(prod_selected, function(index, data){
							if(index==0){
		 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
							}else{
								productIds+="&leaseId="+$(data).attr("rel");
							}
						});
					$("#leaseDelivery").attr("href","manage/lease/todelivery.do?leaseId="+(productIds==""?"{null}":productIds));
					$("#leaseConfirmpay").attr("href","manage/lease/business.do?action=confirmpay&leaseId="+(productIds==""?"{null}":productIds)); 
// 					$("#productOrderok").attr("href","manage/order/orderBusiness.do?action=endOrder&typeId=1&orderId="+(productIds==""?"{null}":productIds)); 
					
					$("#leaseDelete").attr("href","manage/lease/deleteLease.do?typeId=1&leaseId="+(productIds==""?"{null}":productIds));
// 					$("#productOrderDetail").attr("href","manage/order/getOrderDetail.do?group=false&orderId="+(productIds==""?"{null}":productIds)); 
			  } );
		</script>
</div>
