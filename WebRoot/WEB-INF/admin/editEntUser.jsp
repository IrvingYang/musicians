<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->


<script  type="text/javascript">

	var finder1;
	var finder2;
	var finder3;
	
	function uploadProvider1()
	{
	   finder1 = new CKFinder();
	   finder1.selectActionFunction = setProviderField1;
	   finder1.popup();       
	}
	function uploadProvider2()
	{
	   finder2 = new CKFinder();
	   finder2.selectActionFunction = setProviderField2;
	   finder2.popup();       
	}
	function uploadProvider3()
	{
	   finder3 = new CKFinder();
	   finder3.selectActionFunction = setProviderField3;
	   finder3.popup();       
	}
	function setProviderField1( fileUrl ){
		$("#image1").val(fileUrl);
		$("#image1image").attr("src",fileUrl);
	}
	function setProviderField2( fileUrl ){  	
		$("#image2").val(fileUrl);
		$("#image2image").attr("src",fileUrl);
	}
	function setProviderField3( fileUrl ){ 
		$("#image3").val(fileUrl); 	
		$("#image3image").attr("src",fileUrl);
	}
</script> 

<div class="pageContent">

	<form method="post" action="manage/user/editUser.do?userType=2" class="pageForm required-validate" name="myproducForm" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			<input name="validflag" type="hidden"  value="1"/>
<!-- 			<input name="userType" type="hidden"  value="2"/> -->
			<input name="userId" type="hidden"  value="${user.userId}"/>
			<fieldset>
			<legend id="addtype">企业用户编辑</legend>
			<dl>
				<dt>用户名：</dt>
				<dd>
					<!-- suggestFields="productName" suggestUrl="demo/database/db_lookupSuggest.html"-->
					<input type="text" readonly="readonly" name="groupbuy.productId" value="${user.user.userName}"/>
				</dd>
			</dl>
			<dl>
				<dt>邮箱：</dt>
				<dd>
					<!-- suggestFields="productName" suggestUrl="demo/database/db_lookupSuggest.html"-->
					<input type="text" name="email" value="${user.user.email}"/>
				</dd>
			</dl>
			<dl>
				<dt>联系人：</dt>
				<dd>
					<input name="contactman" type="text" class="required" size="20" value="${user.contactman}"/>
				</dd>
			</dl>
			<dl>
				<dt>联系电话：</dt>
				<dd>
					<input name="telephone" type="text" class="required number" size="20" value="${user.telephone}"/>
				</dd>
			</dl>
			<dl>
				<dt>营业执照编号：</dt>
				<dd>
					<input type="text" name="certid1" value="${user.certid1}" size="50"/>
				</dd>
			</dl>
			<dl>
				<dt>营业执照影像：</dt>
				<dd>
					<input type="hidden" name="image1" value="${imageURl1}" id="image1"/>
					<img src="${imageURl1}" alt="营业执照影像" id="image1image"/>
					<a class="button" href="javascript:;"  rel="dlg_page7" onClick="uploadProvider1()" ><span>选择图片</span></a>
				</dd>
			</dl>
			<dl>
				<dt>税务登记证编号：</dt>
				<dd>
					<input type="text" name="certid2" value="${user.certid2}" size="50"/>
				</dd>
			</dl>
			<dl>
				<dt>税务登记证影像：</dt>
				<dd>
					<input type="hidden" name="image2" value="${imageURl2}" id="image2"/>
					<img src="${imageURl2}" alt="税务登记证影像" id="image2image"/>
					<a class="button" href="javascript:;"  rel="dlg_page7" onClick="uploadProvider2()" ><span>选择图片</span></a>
				</dd>
			</dl>
			<dl>
				<dt>组织机构代码证编号：</dt>
				<dd>
					<input type="text" name="certid3" value="${user.certid3}" size="50"/>
				</dd>
			</dl>
			<dl>
				<dt>组织机构代码证影像：</dt>
				<dd>
					<input type="hidden" name="image3" value="${imageURl3}" id="image3"/>
					<img src="${imageURl3}" alt="组织机构代码证影像" id="image3image"/>
					<a class="button" href="javascript:;"  rel="dlg_page7" onClick="uploadProvider3()" ><span>选择图片</span></a>
				</dd>
			</dl>
			<dl>
				<dt>企业名称：</dt>
				<dd>
					<input name="enterprisename" type="text" size="20" value="${user.enterprisename}"  class="textInput valid required">
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
