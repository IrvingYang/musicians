<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->


<div class="pageContent">

	<form method="post" action="manage/promote/${action eq 'add'?'savePromoteProduct.do':'updatePromoteProduct.do'}" class="pageForm required-validate" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			<fieldset>
			<legend id="addtype">销售产品操作</legend>
			<dl>
				<dt>商品：</dt>
				<dd>
					<!-- suggestFields="productName" suggestUrl="demo/database/db_lookupSuggest.html"-->
					<input type="hidden" name="productPromote.productId" value="${shop.productId}"/>
					<input type="text" class="required" name="productPromote.productName" value="${shop.product.productName}" readonly="readonly"  lookupGroup="productPromote" /> 
					<c:if test="${action eq 'add'}">
						<a class="btnLook" href="manage/productshop/queryAllProductShop.do?action=dialog&typeId=3" lookupGroup="productPromote">查找选择未促销商品</a>
					</c:if>
				</dd>
			</dl>
			<dl>
				<dt>促销价：</dt>
				<dd>
					<input name="promotePrice" type="text" class="required" size="20" value="${shop.promotePrice}"/>
				</dd>
			</dl>
			<dl>
				<dt>促销开始时间：</dt>
				<dd>
					<fmt:formatDate value="${shop.promoteStartTime}" pattern="yyyy-MM-dd HH:mm:ss" var="beginTime"/>
					<input type="text" readonly="true" value="${beginTime}" datefmt="yyyy-MM-dd HH:mm:ss" class="date textInput readonly valid required" name="beginTime">
					<a href="javascript:;" class="inputDateButton">选择</a>
				</dd>
			</dl>
			<dl>
				<dt>促销结束时间：</dt>
				<dd>
					<fmt:formatDate value="${shop.promoteEndTime}" pattern="yyyy-MM-dd HH:mm:ss" var="endTime"/>
					<input type="text" readonly="true" value="${endTime}" datefmt="yyyy-MM-dd HH:mm:ss" class="date textInput readonly valid required" name="endTime">
					<a href="javascript:;" class="inputDateButton">选择</a>
				</dd>
			</dl>
			</fieldset>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>
