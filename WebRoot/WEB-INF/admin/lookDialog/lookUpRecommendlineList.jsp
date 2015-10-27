<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script>
	$(function(){
		$("#recommendlinePartner").children("label").children("input[type='radio']").click(function(){
			if($(this).val()==0){
				$(".recommendlinePartner").hide();
			}else if($(this).val()==1){
				$(".recommendlinePartner").show();
			}
		});
	});
</script>

<div class="pageContent"  layoutH="0">
	<div class="panelBar">
		<ul class="toolBar">
			<c:if test="${sessionScope.admin.partnerflag==0}">
				<li style="margin-top:5px;">显示非自营产品 </li>
				<li id="recommendlinePartner"  class="edit">
					<label><input type="radio" name="salesbyself" value="1" checked="checked"/>是</label>
					<label><input type="radio" name="salesbyself" value="0" />否</label>
				</li>
			</c:if>
		</ul>
		
	</div>
	<div id="w_list_print" layoutH="35" >
		<table  width="100%" id="lookuprecommendlineDatatables" class="display" >
			<thead>
				<tr>
					<th width="10"></th>
					<c:if test="${sessionScope.admin.partnerflag==0}">
						<th width="80" class="recommendlinePartner">合作社名称</th>
					</c:if>
					<th width="100">线路标题</th>
					<th width="80">线路类型</th>
					<th width="80">申请开始日期</th>
					<th width="80">申请结束日期</th>
					<th width="70">开始时间</th>
					<th width="50">状态</th>
					<th width="50">价格</th>
					<th width="50">出游天数</th>
					<th width="40">选择</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${recommendlinesList}" var="recommendline" varStatus="state">
				
					<c:if test="${recommendline.providerId!=sessionScope.admin.providerId}">
						<c:set var="classes" value="recommendlinePartner"/>
					</c:if>
					<c:if test="${recommendline.providerId==sessionScope.admin.providerId}">
						<c:set var="classes" value=""/>
					</c:if>
				
					<tr target="activity" rel="${recommendline.recommendlineid}" class="${classes}">
						<td class="odd">${state.index+1}</td>
						<c:if test="${sessionScope.admin.partnerflag==0}">
							<td class="recommendlinePartner">${recommendline.provider.name}</td>
						</c:if>
						<td><p style="text-overflow:ellipsis; white-space: nowrap; overflow: hidden; width: 100px;">${recommendline.title}</p></td>
						<td>${recommendline.recommendlinetype.title}元</td>
						<fmt:formatDate value="${recommendline.beginapplytime}" pattern="yyyy-MM-dd" var="beginapplytime"/>
						<td>${beginapplytime}</td>
						<fmt:formatDate value="${recommendline.endapplytime}" pattern="yyyy-MM-dd" var="endapplytime"/>
						<td>${endapplytime}</td>
						<fmt:formatDate value="${recommendline.departuretime}" pattern="yyyy-MM-dd" var="departuretime"/>
						<td>${departuretime}</td>
						<td>
							<c:choose>
								<c:when test="${recommendline.status==0}">未开始</c:when>
								<c:when test="${recommendline.status==1}">报名中</c:when>
								<c:when test="${recommendline.status==2}">报名已结束</c:when>
								<c:when test="${recommendline.status==3}">活动已开始</c:when>
								<c:when test="${recommendline.status==4}">活动已结束</c:when>
							</c:choose>
						</td>
						<td>${recommendline.price}元</td>
						<td>${recommendline.days}天</td>
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({entityid:'${recommendline.recommendlineid}', title:'${recommendline.title}'})">选择</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th width="10"></th>
					<c:if test="${sessionScope.admin.partnerflag==0}">
						<th width="" class="recommendlinePartner">合作社名称</th>
					</c:if>
					<th width="">线路标题</th>
					<th width="">线路类型</th>
					<th width="">申请开始日期</th>
					<th width="">申请结束日期</th>
					<th width="">开始时间</th>
					<th width="">状态</th>
					<th width="">价格</th>
					<th width="">出游天数</th>
					<th width="">选择</th>
				</tr>
			</tfoot>
		</table>
	</div>
	
		<script type="text/javascript">
		prod_oTable = $('#lookuprecommendlineDatatables').dataTable({
		         "sPaginationType" : "full_numbers",
		         "oLanguage": {
		 			"sLengthMenu": "每页显示 _MENU_ 条记录",
		 			"sZeroRecords": "抱歉， 没有找到",
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
			 
		</script>
</div>
