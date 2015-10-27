<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="demo_page1.html" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					选择团购信息：
					<input type="hidden" name="groupbuyers.groupbuyid" value="" id="orderId" oldValue=""/>
				</td>
				<td> 
				<a class="btnLook" href="manage/groupbuy/getAllProductGroupBuy.do?action=dialog" lookupGroup="groupbuyers">查找选择商品</a></td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent" layoutH="20">
<!-- 	<div class="panelBar"> -->
<!-- 		<ul class="toolBar">			 -->
<!-- 			<li class="line">line</li> -->
<!-- 		</ul>	 -->
<!-- 	</div> -->
	<table  width="100%" id="groupBuyersDatatables" class="display" >	
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				
				<th width=""></th>
				<th width="">订单编号</th>
				<th width="">用户名</th>
				<th width="">用户邮件</th>
				<th width="">联系电话</th>
				<th width="">订单状态</th>
				<th width="140">下单时间</th>
				<th width="140">最后修改时间</th>
			</tr>
		</thead>
		<tbody id="groupBuyerstbody">
			<c:forEach items="${groupBuysList}" var="group" varStatus="state">
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width=""></th>
				<th width="">订单编号</th>
				<th width="">用户名</th>
				<th width="">用户邮件</th>
				<th width="">联系电话</th>
				<th width="">订单状态</th>
				<th width="">下单时间</th>
				<th width="">最后修改时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#groupBuyersDatatables').dataTable({
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
			 
			function work(){
				var oldValue = $("#orderId").attr("oldValue");
				var value = $("#orderId").val();
				if(oldValue!=value){
					$("#orderId").attr("oldValue",value);
					$.ajax({
						url:"manage/order/getOrderUserByGroupId.do",
						data:{groupbuyid:value},
						type:"get",
						dataType:"json",
						success:function(data){
							$("#groupBuyerstbody").children().remove();
							$.each(data,function(index,orderList){
								var statusStr = "";
								switch(orderList.status){
								
								case '01':statusStr='已下单'; 
								break;
								case '02':statusStr='已撤单'; 
								break;
								case '03':statusStr='已付款'; 
								break;
								case '04':statusStr='等待开团'; 
								break;
								case '05':statusStr='开团成功'; 
								break;
								case '06':statusStr='等待配送'; 
								break;
								case '07':statusStr='配送中'; 
								break;
								case '08':statusStr='已完成'; 
								break;
								case '09':statusStr='退货处理中'; 
								break;
								case '10':statusStr='同意退货'; 
								break;
								case '11':statusStr='不同意退货'; 
								break;
								case '12':statusStr='等待退款'; 
								break;
								case '13':statusStr='退款完成'; 
								break;
							
							}
								var lastUpdateTime = "";
								if(orderList.lastUpdateTime!=null && orderList.lastUpdateTime!=""){
									var date = new Date(orderList.lastUpdateTime);
									var year = date.getFullYear();
									var month = date.getMonth()+1;
									var day = date.getDate();
									var hours = date.getHours();
									var minutes = date.getMinutes();
									var seconds = date.getSeconds();
									lastUpdateTime= year+'年'+month+'月'+day+'日  '+hours+':'+minutes+':'+seconds;
								}
								var createTime = "";
								if(orderList.createTime!=null && orderList.createTime!=""){
									var date = new Date(orderList.createTime);
									var year = date.getFullYear();
									var month = date.getMonth()+1;
									var day = date.getDate();
									var hours = date.getHours();
									var minutes = date.getMinutes();
									var seconds = date.getSeconds();
									createTime = year+'年'+month+'月'+day+'日  '+hours+':'+minutes+':'+seconds;
									
								}
								var html = 
									'<tr rel="'+orderList.orderId+'">'+
										'<td>'+(index+1)+'</td>'+
										'<td>'+orderList.orderId+'</td>'+
										'<td>'+orderList.user.userName+'</td>'+
										'<td>'+orderList.user.email+'</td>'+
										'<td>'+orderList.user.personal.telephone+'</td>'+
										'<td>'+statusStr+'</td>'+
										'<td>'+createTime+'</td>'+
										'<td>'+lastUpdateTime+'</td>'+
									'</tr>';
								$("#groupBuyerstbody").append(html);
							});
						}
					});
				}
			}
			setInterval(work, 100);
		</script>
</div>
