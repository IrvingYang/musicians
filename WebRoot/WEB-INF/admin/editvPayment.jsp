<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->
<script type="text/javascript">
var finder;
function uploadpayment()
{
   finder = new CKFinder();
   finder.selectActionFunction = setpaymentimagepath;
   finder.popup();
}
function setpaymentimagepath( fileUrl )
{  	
	$("#imagepath").val(fileUrl);
	$("#imagepathimg").attr("src",fileUrl)
}
</script>
<div class="pageContent">

	<form method="POST" action="manage/payment/saveOrUpdate.do" class="pageForm required-validate" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<input type="hidden" value="${action}" name="action"/>
		<div class="pageFormContent nowrap" layoutH="56">

			<fieldset>
			<legend id="addtype">支付方式编辑</legend>
			
			<input type="hidden" name="paymentway" value="${payment.paymentway }"/>
			<dl>
				<dt>机构名称</dt>
				<dd>
					<input type="text" name="instname" class="required" class="" value="${payment.instname}"/>
				</dd>
			</dl>
			<dl>
				<dt>商户编号</dt>
				<dd>
					<input type="hidden" name="umerchantId" value="${payment.merchantId}"/>
					<input type="text" name="merchantId" class="" value="${payment.merchantId}"/>
				</dd>
			</dl>
			<dl>
				<dt>收款机构名称</dt>
				<dd>
					<input type="text" name="recvbranchname" class="" value="${payment.recvbranchname}"/>
				</dd>
			</dl>
			<dl>
				<dt>收款机构编号</dt>
				<dd>
					<input type="text" name="recvbranchnumber" class="" value="${payment.recvbranchnumber}"/>
				</dd>
			</dl>
			<dl>
				<dt>SWIFT机构名称</dt>
				<dd>
					<input type="text" name="swiftname" class="" value="${payment.swiftname}"/>
				</dd>
			</dl>
			<dl>
				<dt>SWIFT号</dt>
				<dd>
					<input type="text" name="swiftnumber" class="" value="${payment.swiftnumber}"/>
				</dd>
			</dl>
			<dl>
				<dt>收款账户名</dt>
				<dd>
					<input type="text" name="recvaccountname" class="" value="${payment.recvaccountname}"/>
				</dd>
			</dl>
			<dl>
				<dt>收款账户帐号</dt>
				<dd>
					<input type="text" name="recvaccountnumber" class="" value="${payment.recvaccountnumber}"/>
				</dd>
			</dl>
			<dl>
				<dt>KEY</dt>
				<dd>
					<input type="text" name="communicationkey" class="" value="${payment.communicationkey}"/>
				</dd>
			</dl>
			<dl>
				<dt>
					图片
				</dt>
				<dd>
					<input type="hidden" value="${payment.imgpath}" name="imgpath" class="required" id="imagepath" />
					<img src="${payment.imgpath}" id="imagepathimg" alt="支付类型图片(160*80)"/>
					<a class="button" href="javascript:;"  rel="dlg_page7" onClick="uploadpayment()" ><span>选择图片</span></a>
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
