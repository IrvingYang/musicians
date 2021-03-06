<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<script type="text/javascript">
$(function(){
	$("#repomine").children("label").children("input[type='radio']").click(function(){
		if($(this).val()==0){
			$(".productordermine").hide();
		}else if($(this).val()==1){
			$(".productordermine").show();
		}
	});
});
</script>

<!-- <div class="pageHeader"> -->
<!-- 	<form onsubmit="return navTabSearch(this);" action="manage/order/getAllOrder_List.do" method="post"> -->
<!-- 	<input type="hidden" name="typeId" value="1"/> -->
<!-- 	<div class="searchBar"> -->
<!-- 		<table class="searchContent" style="float:left;"> -->
<!-- 			<tr> -->
<!-- 				<td> -->
<!-- 					订单状态： -->
<!-- 				</td> -->
<!-- 				<td> -->
<!-- 					<select name="status"> -->
<!-- 						<option value="">所有状态</option> -->
<!-- 						<option value="01" ${status eq '01' ? 'selected':'' }>已下单</option> -->
<!-- 						<option value="02" ${status eq '02' ? 'selected':'' }>已撤单</option> -->
<!-- 						<option value="03" ${status eq '03' ? 'selected':'' }>已付款</option> -->
<!-- 						<option value="06" ${status eq '04' ? 'selected':'' }>等待配送中</option> -->
<!-- 						<option value="07" ${status eq '05' ? 'selected':'' }>配送中</option> -->
<!-- 					</select> -->
<!-- 				</td> -->
<!-- 				<td> -->
<!-- 					下单日期：<input type="text" class="date" datefmt="yyyy-MM-dd" name="beginTime" value="${beginTime}" readonly /> 到  <input type="text" value="${endTime}" datefmt="yyyy-MM-dd" class="date" name="endTime" readonly /> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
<!-- 		<div class="subBar" style="float:right;"> -->
<!-- 			<ul> -->
<!-- 				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交查询</button></div></div></li> -->
<!-- 			</ul> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	</form> -->
<!-- </div> -->
<div class="pageContent" layoutH="40">
	<div class="panelBar">
		<ul class="toolBar">
<!-- 			<li><a class="add" href="manage/order/getOrderDetail.do?typeId=1&group=false&orderId={null}" target="dialog" mask="true" max="true" width="960" height="500" title="订单商品详细" id="productOrderDetail"><span>查看详细</span></a></li> -->
			<li><a class="delete" href="manage/siteEmail/deleteSiteEmail.do?emailId={null}" target="ajaxTodo" title="确定要删除吗?" id="siteDelete" ><span>删除</span></a></li>
<!-- 			<li><a class="edit" href="manage/order/orderBusiness.do?action=cancel&orderId={null}" target="ajaxTodo"  title="确认取消第一条订单?" id="productRepoconfirmpay"><span>超期取消订单</span></a></li> -->
<!-- 			<li><a class="edit" href="manage/order/orderBusiness.do?action=receiveGoods&orderId={null}" target="ajaxTodo"  title="确认第一条订单已经收到货物?" id="productReporecive"><span>确认收货</span></a></li> -->
<!-- 			<li><a class="edit" href="manage/order/orderBusiness.do?action=remittance&orderId={null}" target="ajaxTodo"  title="确认第一条订单已经汇款成功?" id="productReporemittance"><span>已汇款</span></a></li> -->
<!-- 			<li><a class="edit" href="manage/order/todelivery.do?orderId={null}" mask="true" target="dialog" id="productOrderdelivery"  title="确认已经发货?" width="600" height="300" rel="productOrderdelivery"><span>发货</span></a></li> -->
<!-- 			<li><a class="edit" href="manage/product/orderBusiness.do?action=finishBusiness&orderId={null}" target="ajaxTodo" id="productRepook" title="确认交易完成?"><span>已完成</span></a></li> -->
<!-- 			<c:if test="${sessionScope.admin.partnerflag==0}"> -->
<!-- 				<li id="repomine"  class="edit">显示非自营订单信息  -->
<!-- 					<label><input type="radio" name="productordermine" value="1" checked="checked"/>是</label> -->
<!-- 					<label><input type="radio" name="productordermine" value="0" />否</label> -->
<!-- 				</li> -->
<!-- 			</c:if> -->
		</ul>	
	</div>
	<table id="siteDatables" class="display">	
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				<th >编号</th>
				<th >用户</th>
				<th>类型</th>
				<th>产品名称</th>
				<th >产品说明</th>
				<th >邮箱</th>
				<th >联系电话</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${siteEmails}" var="site" varStatus="state">
				<tr rel="${site.emailId}" >
					<td>${site.emailId}</td>
					<td>${site.userId}</td>
					<td>
						<c:choose>
							<c:when test="${site.emailType==1 }">
								回购申请
							</c:when>
							<c:when test="${site.emailType==2 }">
								托管申请
							</c:when>
						</c:choose>
					</td>
					<td>${site.productName}</td>
					<td>${site.description}</td>
					<td>${site.email}</td>
					<td>${site.telephone}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th >编号</th>
				<th >用户</th>
				<th>类型</th>
				<th>产品名称</th>
				<th >产品说明</th>
				<th >邮箱</th>
				<th >联系电话</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#siteDatables').dataTable({
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
			 $('#siteDatables tbody').on( 'click', 'tr', function () {
			       $(this).toggleClass('selected');
			       prod_selected = $("#siteDatables").children("tbody").children(".selected");
			        var productIds = '';
						$.each(prod_selected, function(index, data){
							if(index==0){
		 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
							}else{
								productIds+="&emailId="+$(data).attr("rel");
							}
						});
// 						productRepoconfirmpay productReporecive productReporemittance
// 					$("#productRepodelivery").attr("href","manage/order/todelivery.do?orderId="+(productIds==""?"{null}":productIds));
// 					$("#productReporemittance").attr("href","manage/repo/business.do?action=remittance&repoId="+(productIds==""?"{null}":productIds));
// 					$("#productReporecive").attr("href","manage/repo/business.do?action=receiveGoods&repoId="+(productIds==""?"{null}":productIds));
// 					$("#productRepoconfirmpay").attr("href","manage/repo/business.do?action=cancel&repoId="+(productIds==""?"{null}":productIds));
// 					$("#productRepook").attr("href","manage/repo/business.do?action=finishBusiness&typeId=1&repoId="+(productIds==""?"{null}":productIds)); 
					
					$("#siteDelete").attr("href","manage/siteEmail/deleteSiteEmail.do?emailId="+(productIds==""?"{null}":productIds));
// 					$("#productRepoDetail").attr("href","manage/order/getOrderDetail.do?group=false&orderId="+(productIds==""?"{null}":productIds)); 
			  } );
		</script>
</div>
