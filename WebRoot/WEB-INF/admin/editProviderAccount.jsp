<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->


<div class="pageContent">

	<form method="post" action="manage/provideraccount/${action eq 'update'?'updateProviderAccount.do':'addProviderAccount.do'}" class="pageForm required-validate" name="myproducForm" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			<fieldset>
			<legend id="addtype">合作社账户编辑</legend>
			<dl>
				<dt>选择合作社：</dt>
				<dd>
					<!-- suggestFields="productName" suggestUrl="demo/database/db_lookupSuggest.html"-->
					<input type="hidden" name="providerAccount.providerId" value="${providerAccount.providerId}"/>
					<input type="text" class="required" name="providerAccount.name" value="${providerAccount.provider.name}" readonly="readonly"  lookupGroup="providerAccount" /> 
					<c:if test="${action eq 'add'}">
						<a class="btnLook" href="manage/provider/getProviderList.do?action=dialog" lookupGroup="providerAccount">查找选择合作社</a>
					</c:if>
				</dd>
			</dl>
			<dl>
				<dt>开户行账户名称：</dt>
				<dd>
					<input name="branchName" type="text" class="required" size="20" value="${providerAccount.branchName}"  />
				</dd>
			</dl>
			<dl>
				<dt>账户名称：</dt>
				<dd>
					<input name="accountName" type="text" class="required" size="20" value="${providerAccount.accountName}"  />
				</dd>
			</dl>
			<dl>
				<dt>账户号：</dt>
				<dd>
					<input name="accountNumber" type="text" class="required" size="20" value="${providerAccount.accountNumber}"  />
					<input name="uaccountNumber" type="hidden" size="20" value="${providerAccount.accountNumber}"  />
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
