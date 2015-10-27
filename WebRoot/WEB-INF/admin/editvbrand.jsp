<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->


<script  type="text/javascript">

	var finder1;
	
	function uploadAdImageb()
	{
	   finder1 = new CKFinder();
	   finder1.selectActionFunction = setProviderFieldb;
	   finder1.popup();       
	}
	function setProviderFieldb( fileUrl ){
		$("#image1b").val(fileUrl);
		$("#image1bimage").attr("src",fileUrl);
	}
	
</script> 

<div class="pageContent">

	<form method="post" action="manage/brand_vendor/saveOrUpdate.do" class="pageForm required-validate" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<input type="hidden" value="${action}" name="action"/>
		<div class="pageFormContent nowrap" layoutH="56">

			<fieldset>
			<legend id="addtype">品牌编辑</legend>
			
			<dl>
				<dt>品牌名称</dt>
				<dd>
					<input type="hidden" name="brandid" value="${brand.brandid}"/>
					<input type="text" name="brandname" class="required" value="${brand.brandname}"/>
				</dd>
			</dl>
			<dl>
				<dt>品牌链接</dt>
				<dd>
					<input type="text" name="url" class="required" value="${brand.url}"/> (请去掉http://)
				</dd>
			</dl>
			<dl>
				<dt>品牌图片</dt>
				<dd>
					<img src="${brand.imagepath}" id="image1bimage" alt="品牌图片(100*80)" />
					<input type="hidden" id="image1b" name="image1b" value="${brand.imagepath}"/>
					<a class="button" href="javascript:;"  rel="dlg_page7" onClick="uploadAdImageb()" ><span>选择图片</span></a>
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
