<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->

<div class="pageContent">

	<form method="post" action="manage/leaseConfig/${action eq 'update'?'update.do':'add.do'}" class="pageForm required-validate" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<input type="hidden" value="${action}" name="action"/>
		<div class="pageFormContent nowrap" layoutH="56">
			<fieldset>
			<legend id="addtype">推荐产品操作</legend>
			
			<dl>
						<dt>产品类型：</dt>
						<dd>
							<input type="hidden" value="${leaseConfig.lcId}" name="lcId"/>
							<input type="hidden" name="productType.productTypeId" value="${leaseConfig.productTypeId}" />
							<input type="text" class="required" name="productType.typeName" class="parentName" readonly="readonly" value="${leaseConfig.productType.typeName}"  lookupGroup="productType" /> 
							<c:if test="${action eq 'add'}">
								<a class="btnLook" href="manage/leaseConfig/getAllProductType.do?action=dialog" lookupGroup="productType">选择商品类型</a>
							</c:if>
						</dd>
					</dl>
			<dl>
						<dt>租赁天数：</dt>
						<dd><input name="day" class="required" type="number" value="${leaseConfig.day}" > 天</dd>
					</dl>
					<dl>
						<dt>租金：</dt>
						<dd><input name="money" class="required" type="number" value="${leaseConfig.money}" > 元</dd>
					</dl>
					<dl>
						<dt>押金比例：</dt>
						<dd><input name="depositPercent" class="required" type="number" value="${leaseConfig.depositPercent*100}" > %</dd>
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
