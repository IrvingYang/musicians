<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->


<script  type="text/javascript">

	var finder1;
	
	function uploadAdImage()
	{
	   finder1 = new CKFinder();
	   finder1.selectActionFunction = setProviderField1;
	   finder1.popup();       
	}
	function setProviderField1( fileUrl ){
		$("#image1").val(fileUrl);
		$("#image1image").attr("src",fileUrl);
	}

</script> 

<div class="pageContent">

	<form method="post" action="trip/modulerecommend/${action eq 'add' ?'addModuleRecommendline.do':'updateModuleRecommendline.do' }" class="pageForm required-validate" name="myproducForm" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			<fieldset>
			<legend id="addtype">广告编辑</legend>
			<c:if test="${action eq 'add' }">
			<dl>
				<dt>广告类型：</dt>
				<dd id="chadType">
					<label><input type="radio" value="01" class="required" checked="checked" name="entitytype"  ${moduleRecommend.entitytype==1?'checked':''}/>趣采摘</label>
					<label><input type="radio" value="02" class="required" name="entitytype"  ${moduleRecommend.entitytype==3?'checked':''}/>趣旅游</label>
					<label><input type="radio" value="03" class="required" name="entitytype"  ${moduleRecommend.entitytype==4?'checked':''}/>游分享</label>
				</dd>
			</dl>
			<dl>
				<dt>选择展示信息</dt>
				<dd>
					<input type="hidden" class="required" name="entity.entityid" value="${moduleRecommend.entityId }"  lookupGroup="entity" /> 
					<input type="text" class="required" name="entity.title" value="" readonly="readonly"  lookupGroup="entity" /> 
					<a class="btnLook" href="manage/activity/getActivityList.do?action=dialog" lookupGroup="entity" id="chentityurl">查找推荐</a>
				</dd>
			</dl>
			</c:if>
			
			<c:if test="${action eq 'update' }">
				<input type="hidden" class="required" name="entity.entityid" value="${moduleRecommend.entityid }" /> 
				<input type="hidden" class="required" name="entitytype" value="${moduleRecommend.entitytype }" /> 
			</c:if>
			<dl>
				<dt>广告标题：</dt>
				<dd>
					<input type="text"  name="title" value="${moduleRecommend.title}"/>
				</dd>
			</dl>
			<dl>
				<dt>广告图片：</dt>
				<dd>
					<input type="hidden" name="recommendImage" value="${imagepath}" id="image1"/>
					<img src="${imagepath}" alt="242x242" id="image1image"/>
					<a class="button" href="javascript:;"  rel="dlg_page7" onClick="uploadAdImage()" ><span>选择图片</span></a>
				</dd>
			</dl>
			
			<dl>
				<dt>开始时间：</dt>
				<dd>
					<fmt:formatDate value="${moduleRecommend.recommBeginTime}" pattern="yyyy-MM-dd" var="startTime"/>
					<input name="startTime" type="text" size="20" value="${startTime}" datefmt="yyyy-MM-dd" class="required date textInput readonly valid" >
					<a href="javascript:;" class="inputDateButton">选择</a>
				</dd>
			</dl>
			<dl>
				<dt>结束时间：</dt>
				<dd>
					<fmt:formatDate value="${moduleRecommend.recommEndTime}" pattern="yyyy-MM-dd" var="endTime"/>
					<input name="endTime" type="text" size="20" value="${endTime}" datefmt="yyyy-MM-dd" class="required date textInput readonly valid" >
					<a href="javascript:;" class="inputDateButton">选择</a>
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
	<script type="text/javascript">
		$(function(){
			$("#chadType").children().children("input").click(function(){
				var value = $(this).val();
				var url="";
				if(value == "01"){
					url="manage/activity/getActivityList.do?action=dialog";
				}
				else if(value == "02"){
					url = "manage/recommendline/getRecommendlineList.do?action=dialog";
				}
				else if(value == "03"){
					url = "manage/blogs/getBlogsList.do?action=dialog";
				}
				$("#chentityurl").attr("href",url);
			});
		});
	</script>
	
</div>
