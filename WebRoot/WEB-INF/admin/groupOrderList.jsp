<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<script type="text/javascript">
$(function(){
	$("#groupordermine").children("label").children("input[type='radio']").click(function(){
		if($(this).val()==0){
			$(".groupordermine").hide();
		}else if($(this).val()==1){
			$(".groupordermine").show();
		}
	});
});
</script>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="manage/order/getAllOrder_List.do" method="post">
	<input type="hidden" name="typeId" value="2"/>
	<div class="searchBar">
		<table class="searchContent"  style="float:left;">
			<tr>
				<td>
					订单状态：
				</td>
				<td>
					<select name="status">
						<option value="">所有状态</option>
						<option value="01" ${status eq '01' ? 'selected':'' }>已下单</option>
						<option value="02" ${status eq '02' ? 'selected':'' }>已撤单</option>
						<option value="03" ${status eq '03' ? 'selected':'' }>已付款</option>
						<option value="04" ${status eq '04' ? 'selected':'' }>等待开团</option>
						<option value="05" ${status eq '05' ? 'selected':'' }>开团成功</option>
						<option value="06" ${status eq '06' ? 'selected':'' }>等待配送中</option>
						<option value="07" ${status eq '07' ? 'selected':'' }>配送中</option>
						<option value="08" ${status eq '08' ? 'selected':'' }>已完成</option>
						<option value="09" ${status eq '09' ? 'selected':'' }>退货处理中</option>
						<option value="10" ${status eq '10' ? 'selected':'' }>同意退货(退款处理中)</option>
						<option value="11" ${status eq '11' ? 'selected':'' }>不同意退货</option>
						<option value="12" ${status eq '12' ? 'selected':'' }>等待退款</option>
						<option value="12" ${status eq '13' ? 'selected':'' }>退款完成</option>
					</select>
				</td>
				<td>
					下单日期：<input type="text" class="date" datefmt="yyyy-MM-dd" name="beginTime" readonly /> 到  <input type="text" datefmt="yyyy-MM-dd" class="date" name="endTime" readonly />
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
			<li><a class="add" href="manage/order/getOrderDetail.do?typeId=2&group=true&orderId={null}" target="dialog" mask="true" width="900" height="500" title="查看订单详细" id="groupOrderDetail"><span>查看详细</span></a></li>
			<li><a class="delete" href="manage/order/deleteProduct.do?typeId=2&orderId={null}" target="ajaxTodo" title="确定要删除吗?" id="groupOrderdelete" ><span>删除</span></a></li>
<!-- 			<li><a class="edit" href="manage/order/orderBusiness.do?action=confirmpay&typeId=2&orderId={null}" target="ajaxTodo"  mask="true" title="确认第一条订单已经收到付款?" id="groupOrderconfirmpay"><span>确认付款</span></a></li> -->
			<li><a class="edit" href="manage/order/todelivery.do?orderId={null}" mask="true" target="dialog" id="groupOrderdelivery" width="600" height="400" title="确认已经发货?" rel="groupOrderdelivery"><span>发货</span></a></li>
<!-- 			<li><a class="edit" href="manage/product/orderBusiness.do?endOrder&typeId=2&orderId={null}" target="ajaxTodo" id="groupOrderok" title="确认完成交易?" ><span>已完成</span></a></li> -->
			<c:if test="${sessionScope.admin.partnerflag==0}">
				<li style="margin-top:5px;">显示非自营订单信息 </li>
				<li id="groupordermine"  class="edit">
					<label><input type="radio" name="groupordermine" value="1" checked="checked"/>是</label>
					<label><input type="radio" name="groupordermine" value="0" />否</label>
				</li>
			</c:if>
		</ul>	
	</div>
	<table  width="100%" id="groupbuyOrderDatables" class="display" >	
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				<th width="">订单号</th>
				<th width="">用户名</th>
				<th width="">状态</th>
				<th width="50">价格</th>
				<th width="70">创建时间</th>
				<th width="70">更新时间</th>
				<th width="">付款方式</th>
				<th width="">付款账号</th>
				<th width="">发票</th>
				<th width="">发票类型</th>
				<th width="">发票抬头</th>
				<th width="">发票明细</th>
				<th width="">打印发票</th>
				<th width="">收货地址</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ordersList}" var="order" varStatus="state">
				<c:set value="" var="classgroupby"/>
				<c:if test="${sessionScope.admin.partnerflag==0}">
					<c:if test="${sessionScope.admin.providerId!=order.providerid}">
						<c:set value="groupordermine" var="classgroupby"/>
					</c:if>
				</c:if>
				<tr rel="${order.orderId}" class="${classgroupby}" >
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
							<c:when test="${order.status eq '13'}">退款完成</c:when>
						</c:choose>
					</td>
					<td>${order.totalamt}</td>
					<td>${order.createTime}</td>
					<td>${order.updateTime}</td>
					<td>
						${order.payment_Method.instname }
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
							<c:when test="${order.invoicetype==0}">个人</c:when>
							<c:when test="${order.invoicetype==1}">企业</c:when>
						</c:choose>
					</td>
					<td>${order.invoicetitle}</td>
					<td>${order.invoicecontent}</td>
					<td>
						<c:choose>
							<c:when test="${order.invoiceprinted==0}">未打印</c:when>
							<c:when test="${order.invoiceprinted==1}">已打印</c:when>
						</c:choose>
					</td>
					<td>${order.userAddress.district.districtName}${order.userAddress.street}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width="">订单号</th>
				<th width="">用户名</th>
				<th width="">状态</th>
				<th width="">价格</th>
				<th width="">创建时间</th>
				<th width="">更新时间</th>
				<th width="">付款方式</th>
				<th width="">付款账号</th>
				<th width="">发票</th>
				<th width="">发票类型</th>
				<th width="">发票抬头</th>
				<th width="">发票明细</th>
				<th width="">打印发票</th>
				<th width="">收货地址</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#groupbuyOrderDatables').dataTable({
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
			 $('#groupbuyOrderDatables tbody').on( 'click', 'tr', function () {
			       	$(this).toggleClass('selected');
			        prod_selected = $("#groupbuyOrderDatables").children("tbody").children(".selected");
				 	var productIds="";
					$.each(prod_selected, function(index, data){
						if(index==0){
	 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
						}else{
							productIds+="&orderId="+$(data).attr("rel");
						}
					});
					$("#groupOrderdelivery").attr("href","manage/order/todelivery.do?orderId="+(productIds==""?"{null}":productIds));
					$("#groupOrderconfirmpay").attr("href","manage/order/orderBusiness.do?action=confirmpay&typeId=2&orderId="+(productIds==""?"{null}":productIds)); 
					$("#groupOrderok").attr("href","manage/order/orderBusiness.do?action=endOrder&typeId=2&orderId="+(productIds==""?"{null}":productIds)); 
					
					$("#groupOrderdelete").attr("href","manage/order/deleteOrder.do?typeId=2&orderId="+(productIds==""?"{null}":productIds));
					$("#groupOrderDetail").attr("href","manage/order/getOrderDetail.do?group=true&orderId="+(productIds==""?"{null}":productIds)); 
			  } );
			 
		</script>
</div>
