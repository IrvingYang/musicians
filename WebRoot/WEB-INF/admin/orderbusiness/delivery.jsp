<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<div class="pageContent">

	<form method="post" action="alipay/sendGoods.action" class="pageForm required-validate" name="myproducForm" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<input type="hidden" name="action" value="delivery"/>
		<input type="hidden" name="typeId" value="1" />
		<div class="pageFormContent nowrap" layoutH="56">
		<fieldset>
			<legend id="addtype">编辑发货信息</legend>
			<dl>
				<dt>订单编号：</dt>
				<dd>
					<input type="text" name="orderId" readonly value="${orderList.orderId}" size="50"/>
				</dd>
			</dl>
			<dl>
				<dt>快递公司：</dt>
				<dd>
					<input type="hidden" name="orderexpress.expressid" value="${product.brand_vendor.brandid}"/>
					<input type="text" class="required" name="orderexpress.expressname" value="${product.brand_vendor.brandname}" readonly="readonly"  lookupGroup="orderexpress" /> 
					<a class="btnLook" href="manage/express/getAllExpress.do?action=dialog" lookupGroup="orderexpress">查找快递公司</a>
				</dd>
			</dl>
			<dl>
				<dt>快递单号：</dt>
				<dd>
					<input name="waybill" type="text" class="required" size="50" value="1" ${user.sex==1?'checked':''} />
				</dd>
			</dl>
			</fieldset>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" id="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>