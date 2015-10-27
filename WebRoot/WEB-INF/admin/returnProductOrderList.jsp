<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(function(){
	$("#returnProduct").children("label").children("input[type='radio']").click(function(){
		if($(this).val()==0){
			$(".returnProduct").hide();
		}else if($(this).val()==1){
			$(".returnProduct").show();
		}
	});
});
</script>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="manage/order/getAllOrder_List.do" method="post">
	<input type="hidden" name="typeId" value="10"/>
	<div class="searchBar">
		<table class="searchContent" style="float:left;">
			<tr>
				<td>
					订单状态：
				</td>
				<td>
					<select name="status">
						<option value="">所有状态</option>
						<option value="09" ${status eq '09' ? 'selected':'' }>退货处理中</option>
						<option value="10" ${status eq '10' ? 'selected':'' }>同意退货</option>
						<option value="11" ${status eq '11' ? 'selected':'' }>不同意退货</option>
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
			<li><a class="edit" href="manage/order/orderBusiness.do?action=acceptReturnProduct&typeId=10&orderId={null}" target="ajaxTodo" width="900" height="500" title="确定同意退货?" id="acceptReturn"><span>同意退货</span></a></li>
			<li><a class="edit" href="manage/order/orderBusiness.do?action=notAcceptReturnProduct&typeId=10&orderId={null}" target="ajaxTodo" title="确定不同意退货?" id="notAcceptReturn" ><span>不同意退货</span></a></li>
			<li><a class="edit" href="manage/order/orderBusiness.do?action=receivedReProduct&typeId=10&orderId={null}" target="ajaxTodo"  mask="true" title="确认第已经收到退货?" id="receivedReProduct"><span>收到退货</span></a></li>
			<c:if test="${sessionScope.admin.partnerflag==0}">
				<li id="returnProduct"  class="edit">显示非自营数据
					<label><input type="radio" name="salesbyself" value="1" checked="checked"/>是</label>
					<label><input type="radio" name="salesbyself" value="0" />否</label>
				</li>
			</c:if>
		</ul>	
	</div>
	<table id="returnProductOrderDatables" class="display">	
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
				<th width="">发票</th>
				<th width="">打印发票</th>
				<th width="">留言</th>
				<th width="">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ordersList}" var="order" varStatus="state">
				<c:set value="" var="classreturnproduct"/>
				<c:if test="${sessionScope.admin.partnerflag==0}">
					<c:if test="${sessionScope.admin.providerId!=order.providerid}">
						<c:set value="returnProduct" var="classreturnproduct"/>
					</c:if>
				</c:if>
				<tr rel="${order.orderId}" class="${classreturnproduct}">
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
							<c:when test="${order.status eq '10'}">同意退货</c:when>
							<c:when test="${order.status eq '11'}">不同意退货</c:when>
							<c:when test="${order.status eq '12'}">等待退款</c:when>
							<c:when test="${order.status eq '13'}">退款完成成</c:when>
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
					<td>
						<c:choose>
							<c:when test="${order.requireinvoice==0}">不需要</c:when>
							<c:when test="${order.requireinvoice==1}">需要</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${order.invoiceprinted==0}">未打印</c:when>
							<c:when test="${order.invoiceprinted==1}">已打印</c:when>
						</c:choose>
					</td>
					<td>${order.remark}</td>
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
				<th width="">发票</th>
				<th width="">打印发票</th>
				<th width="">留言</th>
				<th width="">最后修改时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#returnProductOrderDatables').dataTable({
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
			 $('#returnProductOrderDatables tbody').on( 'click', 'tr', function () {
			       $(this).toggleClass('selected');
			       prod_selected = $("#returnProductOrderDatables").children("tbody").children(".selected");
			        var productIds = '';
						$.each(prod_selected, function(index, data){
							if(index==0){
		 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
							}else{
								productIds+="&orderId="+$(data).attr("rel");
							}
						});
					$("#acceptReturn").attr("href","manage/order/orderBusiness.do?action=acceptReturnProduct&typeId=10&orderId="+(productIds==""?"{null}":productIds));
					$("#notAcceptReturn").attr("href","manage/order/orderBusiness.do?action=notAcceptReturnProduct&typeId=10&orderId="+(productIds==""?"{null}":productIds)); 
					$("#receivedReProduct").attr("href","manage/order/orderBusiness.do?action=receivedReProduct&typeId=10&orderId="+(productIds==""?"{null}":productIds)); 
			  } );
		</script>
</div>
