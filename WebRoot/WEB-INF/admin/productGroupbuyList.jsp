<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript">
	$(function(){
		$("#salesbyself").children("label").children("input[type='radio']").click(function(){
			if($(this).val()==0){
				$(".salesbyself").hide();
			}else if($(this).val()==1){
				$(".salesbyself").show();
			}
		});
	});
</script>

<div class="pageContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/groupbuy/toAddGroupBuy.do" target="navTab" title="添加团购商品"><span>添加</span></a></li>
			<li><a class="delete" href="manage/groupbuy/deleteGroupBuy.do?groupbuyId={null}" target="ajaxTodo" title="确定要删除吗?" id="gourpBuydelete"><span>删除</span></a></li>
			<li><a class="edit" href="manage/groupbuy/toEditGroupBuy.do?groupbuyId={null}" target="navTab" id="gourpBuyedit" title="修改团购信息"><span>修改</span></a></li>
			<li><a class="edit" href="manage/groupbuy/openGroupBuy.do?statusId=1&groupbuyId={null}" target="ajaxTodo" mask="true" id="opengroup1"><span>开团</span></a></li>
			<li><a class="edit" href="manage/groupbuy/openGroupBuy.do?statusId=0&groupbuyId={null}" target="ajaxTodo" mask="true" id="opengroup0"><span>取消开团</span></a></li>
			<li class="line">line</li>
			<c:if test="${sessionScope.admin.partnerflag==0}">
				<li style="margin-top:5px;">显示非自营产品 </li>
				<li id="salesbyself"  class="edit">
					<label><input type="radio" name="salesbyself" value="1" checked="checked"/>是</label>
					<label><input type="radio" name="salesbyself" value="0" />否</label>
				</li>
			</c:if>
		</ul>
		
	</div>
	<table  width="100%" id="groupBuyDatatables" class="display" >	
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				<th width=""></th>
				<th width="">商品名</th>
				<th width="">团购模式</th>
				<th width="">团购价格</th>
				<c:if test="${sessionScope.admin.partnerflag==0}">
					<th width="80"  class="salesbyself">产品销售商</th>
				</c:if>
				<th width="">计量单位</th>
				<th width="">销售范围</th>
				<th width="">库存</th>
				<th width="">开始时间</th>
				<th width="">结束时间</th>
				<th width="">最小购买数量</th>
				<th width="">开团金额或数量</th>
				<th width="">开团条件</th>
				<th width="">开团结果</th>
				<th width="130">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${groupBuysList}" var="group" varStatus="state">
			    <c:if test="${group.product.providerId!=sessionScope.admin.providerId}">
					<c:set var="classes" value="salesbyself"/>
				</c:if>
				<c:if test="${group.product.providerId==sessionScope.admin.providerId}">
					<c:set var="classes" value=""/>
				</c:if>
				<tr rel="${group.groupbuyid}"  class="${classes}">
					<td>${state.index+1}</td>
					<td>${group.product.productName}</td>
					<td>
					<c:choose>
						<c:when test="${group.groupBuyType==0}">预开团</c:when>
						<c:when test="${group.groupBuyType==1}">立即开团</c:when>
					</c:choose>
					</td>
					<td>${group.groupBuyPrice}</td>
					<c:if test="${sessionScope.admin.partnerflag==0}">
						<td  class="salesbyself">${group.product.provider.name}</td>
					</c:if>
					<td>${group.unit}</td>
					<td>${group.salesarea}</td>
					<td>${group.stockNumber}</td>
					<fmt:formatDate value="${group.groupBuyStartTime}" pattern="yyyy-MM-dd" var="startTime"/>
					<td>${startTime}</td>
					<fmt:formatDate value="${group.groupBuyEndTime}" pattern="yyyy-MM-dd" var="endTime"/>
					<td>${endTime}</td>
					<td>${group.minBuyCount}</td>
					<td>${group.targetCount}</td>
					<td>
					<c:choose>
						<c:when test="${group.targetType==0}">以金额为标准</c:when>
						<c:when test="${group.targetType==1}">以数量为标准</c:when>
					</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${empty group.openresult}">等待开团</c:when>
							<c:when test="${group.openresult==0}">失败</c:when>
							<c:when test="${group.openresult==1}">成功</c:when>
						</c:choose>
					</td>
					<td>${group.lastUpdateTime}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width=""></th>
				<th width="">商品名</th>
				<th width="">团购模式</th>
				<th width="">团购价格</th>
				<c:if test="${sessionScope.admin.partnerflag==0}">
					<th width="80"  class="salesbyself">产品销售商</th>
				</c:if>
				<th width="">计量单位</th>
				<th width="">销售范围</th>
				<th width="">库存</th>
				<th width="">开始时间</th>
				<th width="">结束时间</th>
				<th width="">最小购买数量</th>
				<th width="">开团金额或数量</th>
				<th width="">开团条件</th>
				<th width="">开团结果</th>
				<th width="130">最后修改时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#groupBuyDatatables').dataTable({
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
			 
			 $('#groupBuyDatatables tbody').on( 'click', 'tr', function () {
			        $(this).toggleClass('selected');
			        var selected = $("#groupBuyDatatables").children("tbody").children(".selected");
			        var productIds = "";
 					$.each(selected, function(index, data){
 						if(index==0){
 	 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
 						}else{
 							productIds+="&groupbuyId="+$(data).attr("rel");
 						}
 					});
 					$("#gourpBuydelete").attr("href","manage/groupbuy/deleteGroupBuy.do?groupbuyId="+(productIds==""?"{null}":productIds));
					$("#gourpBuyedit").attr("href","manage/groupbuy/toEditGroupBuy.do?groupbuyId="+(productIds==""?"{null}":productIds)); 
					$("#opengroup0").attr("href","manage/groupbuy/openGroupBuy.do?statusId=0&groupbuyId="+(productIds==""?"{null}":productIds)); 
					$("#opengroup1").attr("href","manage/groupbuy/openGroupBuy.do?statusId=1&groupbuyId="+(productIds==""?"{null}":productIds)); 
			  } );
			 
		</script>
</div>
