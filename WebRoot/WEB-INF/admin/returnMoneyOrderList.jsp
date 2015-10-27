<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<script type="text/javascript">
$(function(){
	$("#returnMoney").children("label").children("input[type='radio']").click(function(){
		if($(this).val()==0){
			$(".returnMoney").hide();
		}else if($(this).val()==1){
			$(".returnMoney").show();
		}
	});
});
</script>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="manage/order/getAllOrder_List.do" method="post">
	<input type="hidden" name="typeId" value="11"/>
	<div class="searchBar">
		<table class="searchContent" style="float:left;">
			<tr>
				<td>
					订单状态：
				</td>
				<td>
					<select name="status">
						<option value="">所有状态</option>
						<option value="12" ${status eq '12' ? 'selected':'' }>等待退款</option>
						<option value="13" ${status eq '13' ? 'selected':'' }>退款完成</option>
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
<div class="pageContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
<!-- 			<li><a class="add" href="manage/order/getOrderDetail.do?typeId=1&orderId={null}" target="dialog" width="900" height="500" title="查看详细" id="returnMoneyOrderDetail"><span>查看详细</span></a></li> -->
				<li><a class="edit" href="manage/order/orderBusiness.do?aciton=returnMoney&typeId=99999&orderId={null}" target="ajaxTodo" title="确定要给第一条订单执行退款吗?" id="returnMoneyRe" ><span>退款</span></a></li>
<!-- 			<li><a class="edit" href="manage/order/orderBusiness.do?action=confirmpay&typeId=1&orderId={null}" target="ajaxTodo"  mask="true" title="确认第一条订单已经收到付款?" id="productOrderconfirmpay"><span>确认付款</span></a></li> -->
<!-- 			<li><a class="edit" href="manage/order/todelivery.do?orderId={null}" mask="true" target="dialog" id="productOrderdelivery" width="600" height="300" rel="productOrderdelivery"><span>发货</span></a></li> -->
<!-- 			<li><a class="edit" href="manage/product/orderBusiness.do?endOrder&typeId=1&orderId={null}" target="ajaxTodo" id="productOrderok"><span>已完成</span></a></li> -->
			<c:if test="${sessionScope.admin.partnerflag==0}">
				<li id="returnMoney"  class="edit">显示非自营数据
					<label><input type="radio" name="returnMoney" value="1" checked="checked"/>是</label>
					<label><input type="radio" name="returnMoney" value="0" />否</label>
				</li>
			</c:if>
		</ul>	
	</div>
	<table id="returnMoneyOrderDatables" class="display">	
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				<th width="">订单号</th>
				<th width="">用户名</th>
				<th width="">状态</th>
				<th width="">订单类型</th>
				<th width="">价格</th>
				<th width="">创建时间</th>
				<th width="">更新时间</th>
				<th width="">付款方式</th>
				<th width="">付款账号</th>
				<th width="">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ordersList}" var="order" varStatus="state">
				<c:set value="" var="classproductMoney"/>
				<c:if test="${sessionScope.admin.partnerflag==0}">
					<c:if test="${sessionScope.admin.providerId!=order.providerid}">
						<c:set value="returnMoney" var="classproductMoney"/>
					</c:if>
				</c:if>
				<tr rel="${order.orderId}" class="${classproductMoney}">
					<td>${order.orderId}</td>
					<td>${order.user.userName}</td>
					<td>
						<c:choose>
							<c:when test="${order.status eq '01'}">已下单</c:when>
							<c:when test="${order.status eq '02'}">已撤单</c:when>
							<c:when test="${order.status eq '03'}">已付款</c:when>
							<c:when test="${order.status eq '04'}">等待开团</c:when>
							<c:when test="${order.status eq '05'}">开团成功</c:when>
							<c:when test="${order.status eq '06'}">等待配送</c:when>
							<c:when test="${order.status eq '07'}">配送中</c:when>
							<c:when test="${order.status eq '08'}">已完成</c:when>
							<c:when test="${order.status eq '09'}">退货处理中</c:when>
							<c:when test="${order.status eq '10'}">退款处理中</c:when>
							<c:when test="${order.status eq '11'}">不同意退货</c:when>
							<c:when test="${order.status eq '12'}">等待退款</c:when>
							<c:when test="${order.status eq '13'}">退款完成</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${order.orderType==1}">商城订单</c:when>
							<c:when test="${order.orderType==2}">团购订单</c:when>
							<c:when test="${order.orderType==3}">大宗交易订单</c:when>
							<c:when test="${order.orderType==4}">合作社订单</c:when>
						</c:choose>
					</td>
					<td>${order.totalamt}</td>
					<td>${order.createTime}</td>
					<td>${order.updateTime}</td>
					<td>
						<c:choose>
							<c:when test="${order.paymentway eq '01'}">支付宝</c:when>
							<c:when test="${order.paymentway eq '02'}">银联</c:when>
						</c:choose>
					</td>
					<td>${order.accountno}</td>
					<td>${order.lastUpdateTime}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width="">订单号</th>
				<th width="">用户名</th>
				<th width="">状态</th>
				<th width="">订单类型</th>
				<th width="">价格</th>
				<th width="">创建时间</th>
				<th width="">更新时间</th>
				<th width="">付款方式</th>
				<th width="">付款账号</th>
				<th width="">最后修改时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#returnMoneyOrderDatables').dataTable({
		         "sPaginationType" : "full_numbers",
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
			 $('#returnMoneyOrderDatables tbody').on( 'click', 'tr', function () {
			       $(this).toggleClass('selected');
			       prod_selected = $("#returnMoneyOrderDatables").children("tbody").children(".selected");
			        var productIds = '';
						$.each(prod_selected, function(index, data){
							if(index==0){
		 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
							}else{
								productIds+="&orderId="+$(data).attr("rel");
							}
						});
// 					$("#productOrderdelivery").attr("href","manage/order/todelivery.do?orderId="+(productIds==""?"{null}":productIds));
// 					$("#productOrderconfirmpay").attr("href","manage/order/orderBusiness.do?action=confirmpay&typeId=1&orderId="+(productIds==""?"{null}":productIds)); 
// 					$("#productOrderok").attr("href","manage/order/orderBusiness.do?action=endOrder&typeId=1&orderId="+(productIds==""?"{null}":productIds)); 
// 					$("#productOrderdelete").attr("href","manage/order/deleteOrder.do?typeId=1&orderId="+(productIds==""?"{null}":productIds));
					$("#returnMoneyRe").attr("href","manage/order/orderBusiness.do?action=returnMoney&typeId=99999&orderId="+(productIds==""?"{null}":productIds)); 
			  } );
		</script>
</div>
