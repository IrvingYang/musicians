<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			<li><a class="add" href="manage/productbigdeal/toAddBigDeal.do" target="navTab" title="添加大宗交易"><span>添加</span></a></li>
			<li><a class="delete" href="manage/productbigdeal/deleteBigDeal.do?bigDealId={null}" target="ajaxTodo" title="确定要删除吗?" id="gourpBuydelete"><span>删除</span></a></li>
			<li><a class="edit" href="manage/productbigdeal/toEditBigDeal.do?bigDealId=={null}" target="navTab" id="gourpBuyedit"  title="修改大宗交易"><span>修改</span></a></li>
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
				<th width="">参考价格</th>
				<c:if test="${sessionScope.admin.partnerflag==0}">
					<th width="80"  class="salesbyself">产品销售商</th>
				</c:if>
				<th width="">计量单位</th>
				<th width="">销售范围</th>
				<th width="">联系人</th>
				<th width="75">联系人电话</th>
				<th width="75">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${bigDealsList}" var="bigdeal" varStatus="state">
			    <c:if test="${bigdeal.product.providerId!=sessionScope.admin.providerId}">
					<c:set var="classes" value="salesbyself"/>
				</c:if>
				<c:if test="${bigdeal.product.providerId==sessionScope.admin.providerId}">
					<c:set var="classes" value=""/>
				</c:if>
				<tr rel="${bigdeal.bigdealId}"  class="${classes}">
					<td>${state.index+1}</td>
					<td>${bigdeal.product.productName}</td>
					<td>${bigdeal.referencePrice}</td>
					<c:if test="${sessionScope.admin.partnerflag==0}">
						<td  class="salesbyself">${bigdeal.product.provider.name}</td>
					</c:if>
					<td>${bigdeal.unit}</td>
					<td>${bigdeal.salesarea}</td>
					<td>${bigdeal.contactMan}</td>
					<td>${bigdeal.contactTelephone}</td>
					<td>${bigdeal.lastUpdateTime}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width=""></th>
				<th width="">商品名</th>
				<th width="">参考价格</th>
				<c:if test="${sessionScope.admin.partnerflag==0}">
					<th width="80"  class="salesbyself">产品销售商</th>
				</c:if>
				<th width="">计量单位</th>
				<th width="">销售范围</th>
				<th width="">联系人</th>
				<th width="75">联系人电话</th>
				<th width="75">最后修改时间</th>
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
 							productIds+="&bigDealId="+$(data).attr("rel");
 						}
 					});
 					$("#gourpBuydelete").attr("href","manage/productbigdeal/deleteBigDeal.do?bigDealId="+(productIds==""?"{null}":productIds));
					$("#gourpBuyedit").attr("href","manage/productbigdeal/toEditBigDeal.do?bigDealId="+(productIds==""?"{null}":productIds)); 
			  } );
			 
		</script>
</div>
