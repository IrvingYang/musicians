<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->

<div class="pageContent">

	<form method="post" action="manage/newProduct/${action eq 'add'?'addNewProduct.do':'updateNewProduct.do' }" class="pageForm required-validate" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			
			<fieldset>
			<legend id="addtype">新到商品操作</legend>
			<dl>
				<dt>选择商品</dt>
				<dd>
					<input type="hidden" name="product.productId" value="${newProduct.productId}"/>
					<input type="text" class="required" readonly="readonly" name="product.productName" value="${newProduct.product_ext_shop.product.productName}"  lookupGroup="product" /> 
					<a class="btnLook" href="manage/productshop/queryAllProductShop.do?action=dialog&type=new&typeId=7" lookupGroup="product">查找选择商品类型</a>
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
