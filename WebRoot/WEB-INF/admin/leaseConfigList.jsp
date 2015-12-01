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

<%-- <div class="pageHeader">
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
</div> --%>
<div class="pageContent" layoutH="40">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/leaseConfig/toAdd.do" target="navTab" title="添加租赁设置" id="leaseAdd"><span>添加</span></a></li>
			<li><a class="edit" href="manage/leaseConfig/toUpdate.do?lcId={null}" target="navTab" title="修改租赁设置" id="leaseEdit"><span>修改</span></a></li>
			<li><a class="delete" href="manage/leaseConfig/deleteProduct.do?typeId=1&orderId={null}" target="ajaxTodo" title="确定要删除吗?" id="leaseDelete" ><span>删除</span></a></li>
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
				<th>天数</th>
				<th>租金(原价百分比)</th>
				<th>产品类型(ID)</th>
				<th>押金比例</th>
				<th>更新时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${leaseConfigList}" var="lease" varStatus="status">
				<c:set value="" var="class"/>
				<tr rel="${lease.lcId}" class="${classes}">
					<td>${lease.lcId} </td>
					<td>${lease.day} 天 </td>
					<td>${lease.money} %</td>
					<td>${lease.productType.typeName} (${lease.productTypeId})</td>
					<td>${lease.depositPercent*100} %</td>
					<td>${lease.lastUpdateTime}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th>租赁编号</th>
				<th>天数</th>
				<th>租金</th>
				<th>产品类型(ID)</th>
				<th>押金比例</th>
				<th>更新时间</th>
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
								productIds+="&lcId="+$(data).attr("rel");
							}
						});
												
					$("#leaseAdd").attr("href","manage/leaseConfig/toAdd.do");
					$("#leaseEdit").attr("href","manage/leaseConfig/toUpdate.do?lcId="+(productIds==""?"{null}":productIds));
					$("#leaseDelete").attr("href","manage/leaseConfig/toDelete?lcId="+(productIds==""?"{null}":productIds));
			  } );
		</script>
</div>
