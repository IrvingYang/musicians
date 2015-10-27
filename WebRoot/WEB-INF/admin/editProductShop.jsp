<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->


<div class="pageContent">

	<form method="post" action="manage/productshop/${action eq 'add'?'saveProductShop.do':'updateProductShop.do'}" class="pageForm required-validate" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			<input type="hidden" value="${shop.onTime}" name="ontimes"/>
			<input type="hidden" value="${(empty shop.promotePrice)?0:shop.promotePrice}" name="promotePrice"/>
			<input type="hidden" value="${(empty shop.promoteflag)?0:shop.promoteflag}" name="promoteflag"/>
			<input type="hidden" value="${shop.promoteStartTime}" name="promoteStartTimes"/>
			<input type="hidden" value="${shop.promoteEndTime}" name="promoteEndTimes"/>
			<fieldset>
			<legend id="addtype">商城产品操作</legend>
			<dl>
				<dt>商品：</dt>
				<dd>
					<!-- suggestFields="productName" suggestUrl="demo/database/db_lookupSuggest.html"-->
					<input type="hidden" name="orgLookup.productId" value="${shop.productId}"/>
					<input type="text" class="required" name="orgLookup.productName" value="${shop.product.productName}" readonly="readonly"  lookupGroup="orgLookup" /> 
					<c:if test="${action eq 'add'}">
						<a class="btnLook" href="manage/product/productList.do?action=dialog&typeId=0" lookupGroup="orgLookup">查找选择未上架商品</a>
					</c:if>
				</dd>
			</dl>
			<dl>
				<dt>计量单位：</dt>
				<dd>
					<input type="text" class="required" name="orgLookup.unit" value="${shop.unit}" readonly="readonly"  lookupGroup="orgLookup" />
				</dd>
			</dl>
			<dl>
				<dt>售价：</dt>
				<dd>
					<input name="originalPrice" type="text" class="required" size="20" value="${shop.originalPrice}"/>
				</dd>
			</dl>
			<dl>
				<dt>销售范围：</dt>
				<dd>
					<input name="salesarea" type="text" class="required" size="30" value="${shop.salesarea}"/>
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
