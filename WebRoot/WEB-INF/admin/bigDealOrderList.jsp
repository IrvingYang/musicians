<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<script type="text/javascript">
$(function(){
	$("#bigdealorder").children("label").children("input[type='radio']").click(function(){
		if($(this).val()==0){
			$(".bigdealorder").hide();
		}else if($(this).val()==1){
			$(".bigdealorder").show();
		}
	});
});
</script>

<div class="pageContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">			
			<li><a class="delete" href="manage/order/deleteOrder.do?typeId=3&orderId={null}" target="ajaxTodo" title="确定要删除吗?" id="bigDealOrderdelete" ><span>删除</span></a></li>
<!-- 			<li><a class="edit" href="manage/product/toEditPage.do?productId={product}" target="navTab" id="bigDealOrderedit"><span>修改</span></a></li> -->
			<c:if test="${sessionScope.admin.partnerflag==0}">
				<li id="bigdealorder"  class="edit">显示非自营数据
					<label><input type="radio" name="bigdeal" value="1" checked="checked"/>是</label>
					<label><input type="radio" name="bigdeal" value="0" />否</label>
				</li>
			</c:if>
		</ul>	
	</div>
	<table  width="100%" id="bigDealOrderDatables" class="display" >	
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				<th width="">订单号</th>
				<th width="">用户名</th>
				<th width="">状态</th>
				<th width="">商品名</th>
				<th width="">价格</th>
				<th width="">创建时间</th>
				<th width="">更新时间</th>
				<th width="">留言</th>
				<th width="">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ordersList}" var="order" varStatus="state">
			
				<c:set value="" var="classbigdeal"/>
				<c:if test="${sessionScope.admin.partnerflag==0}">
					<c:if test="${sessionScope.admin.providerId!=order.providerid}">
						<c:set value="bigdealorder" var="classbigdeal"/>
					</c:if>
				</c:if>
				<tr rel="${order.orderId}" class="${classbigdeal}">
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
					<td>${order.order_detail[0].product.productName}</td>
					<td>${order.totalamt}</td>
					<td>${order.createTime}</td>
					<td>${order.updateTime}</td>
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
				<th width="">商品名</th>
				<th width="">价格</th>
				<th width="">创建时间</th>
				<th width="">更新时间</th>
				<th width="">留言</th>
				<th width="">最后修改时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#bigDealOrderDatables').dataTable({
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
			 
			 $('#bigDealOrderDatables tbody').on( 'click', 'tr', function () {
			       	$(this).toggleClass('selected');
			        prod_selected = $("#bigDealOrderDatables").children("tbody").children(".selected");
				 	var productIds="";
					$.each(prod_selected, function(index, data){
						if(index==0){
	 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
						}else{
							productIds+="&orderId="+$(data).attr("rel");
						}
					});
// 					$("#groupOrderdelivery").attr("href","manage/order/todelivery.do?orderId="+(productIds==""?"{null}":productIds));
// 					$("#groupOrderconfirmpay").attr("href","manage/order/orderBusiness.do?action=confirmpay&typeId=2&orderId="+(productIds==""?"{null}":productIds)); 
// 					$("#groupOrderok").attr("href","manage/order/orderBusiness.do?action=endOrder&typeId=2&orderId="+(productIds==""?"{null}":productIds)); 
					
					$("#bigDealOrderdelete").attr("href","manage/order/deleteOrder.do?typeId=3&orderId="+(productIds==""?"{null}":productIds));
// 					$("#groupOrderDetail").attr("href","manage/order/getOrderDetail.do?orderId="+(productIds==""?"{null}":productIds)); 
			  } );
			 
		</script>
</div>
