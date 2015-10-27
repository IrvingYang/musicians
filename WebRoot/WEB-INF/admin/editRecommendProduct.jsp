<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->

<div class="pageContent">

	<form method="post" action="manage/recommendProduct/${action eq 'update'?'updateRecomendProduct.do':'addRecomendProduct.do'}" class="pageForm required-validate" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<input type="hidden" value="${action}" name="action"/>
		<div class="pageFormContent nowrap" layoutH="56">

			<fieldset>
			<legend id="addtype">推荐产品操作</legend>
			
			<dl>
				<dt>选择商品</dt>
				<dd>
					<input type="hidden" name="shop.productId" value="${recommend.productId}"/>
					<input type="text" class="required" name="shop.productName" value="${recommend.product_ext_shop.product.productName}" readonly="readonly" lookupGroup="shop" /> 
					<c:if test="${action eq 'add'}">
						<a class="btnLook" href="manage/productshop/queryAllProductShop.do?action=dialog&type=recommend&typeId=7" lookupGroup="shop">查找选择商城商品</a>
					</c:if>
				</dd>
			</dl>
			<dl>
				<dt>推荐开始时间：</dt>
				<dd>
				<fmt:formatDate value="${recommend.recommBeginTime}" var="beginTime" pattern="yyyy-MM-dd HH:mm:ss"/>
				<input type="text" readonly="true" value="${beginTime}" datefmt="yyyy-MM-dd HH:mm:ss" class="date textInput readonly valid" name="beginTime">
				<a href="javascript:;" class="inputDateButton">选择</a></dd>
			</dl>
			<dl>
				<dt>推荐结束时间：</dt>
				<dd>
				<fmt:formatDate value="${recommend.recommEndTime}" var="endTime" pattern="yyyy-MM-dd HH:mm:ss"/>
				<input type="text" readonly="true" datefmt="yyyy-MM-dd HH:mm:ss" value="${endTime}" class="date textInput readonly valid" name="endTime">
				<a href="javascript:;" class="inputDateButton">选择</a></dd>
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
