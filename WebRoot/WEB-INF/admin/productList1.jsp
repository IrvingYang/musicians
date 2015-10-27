<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="pagerForm" method="post" action="demo_page1.html">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<script type="text/javascript">
	$(function(){
		$("#salesbyself").children("label").children("input[type='radio']").click(function(){
			if($(this).val()==0){
				$(".salesbyself").hide();
			}else if($(this).val()==1){
				$(".salesbyself").show();
			}
		});
	})
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="demo_page1.html" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					我的客户：<input type="text" name="keyword" />
				</td>
				<td>
					<select class="combox" name="province">
						<option value="">所有省市</option>
						<option value="北京">北京</option>
						<option value="上海">上海</option>
						<option value="天津">天津</option>
						<option value="重庆">重庆</option>
						<option value="广东">广东</option>
					</select>
				</td>
				<td>
					建档日期：<input type="text" class="date" readonly="true" />
				</td>
				<c:if test="${empty sessionScope.salesbyself}">
					<td>
						<label>是否显示非自营产品：</label>
					</td>
					<td>
					</td>
					<td width="180" id="salesbyself">
						<label><input type="radio" name="salesbyself" value="1" />是</label>
						<label><input type="radio" name="salesbyself" value="0" />否</label>
					</td>
				</c:if>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="demo_page4.html" target="navTab"><span>添加</span></a></li>
			<li><a class="delete" href="demo/common/ajaxDone.html?uid={product}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="demo_page4.html?uid={product}" target="navTab"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="200">
		<thead>
			<tr>
				<th width="80"></th>
				<th width="60">商品名</th>
				<th width="80">商品类型</th>
				<th width="80">库存</th>
				<th width="80">发布时间</th>
				<th width="80">撤销时间</th>
				<th width="80">商品定价</th>
				<c:if test="${empty sessionScope.salesbyself}">
					<th width="80" class="salesbyself">自营产品</th>
				</c:if>
				<th width="80">品牌名称</th>
				<th width="80">产品供应商</th>
				<th width="80">商品评级</th>
				<th width="80">热度</th>
				<th width="80">销售范围</th>
				<th width="80">产地</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${productsList}" var="product" varStatus="state">
				<tr target="product" rel="${product.productId}">
					<td>${state.index+1}</td>
					<td>${product.productName}</td>
					<td>${product.product_type.typeName}</td>
					<td>${product.stockNumber}</td>
					<td>${product.onTime}</td>
					<td>${product.offTime}</td>
					<td>￥${product.shopPrice}</td>
					<c:if test="${empty sessionScope.salesbyself}">
						<td  class="salesbyself">${product.salesbyself}</td>
					</c:if>
					<td>${product.brand_vendor.brandname}</td>
					<td>${product.provider.name}</td>
					<td>${product.productGrade}</td>
					<td>${product.attentionGrade}</td>
					<td>${product.salesrange}</td>
					<td>${product.city.cityName}</td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10" currentPage="1"></div>

	</div>
</div>
