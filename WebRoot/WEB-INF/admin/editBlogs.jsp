<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->


<script  type="text/javascript">

    var activityImage;
	
    function doUploadActivityImage()
    {
 
   		activityImage = new CKFinder();
    	activityImage.selectActionFunction = SetActivityFileField;
    	activityImage.popup();       
    }
    function SetActivityFileField( fileUrl )
    {  	
    	$("#imagepath").val(fileUrl);
    	$("#imagetarget").attr("src",fileUrl);
    }
</script> 

<div class="pageContent">

	<form method="post" action="manage/activity/${action eq 'add'?'addActivity.do':'updateActivity.do'}" class="pageForm required-validate" name="myactivityForm" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			<c:if test="${action eq 'update'}">
				<input type="hidden" value="${activity.activityid}" name="activityid"/>
			</c:if>
			<dl>
				<dt>活动标题：</dt>
				<dd>
					<input name="title" type="text" class="required" size="30" value="${activity.title}"/>
				</dd>
			</dl>
			
			<dl id="productImg">
				<dt>活动图片：</dt>
				<dd id="attributes">
					<img src="${activity.entityimage[0].imagepath}" alt="活动图片（730x360）" id="imagetarget"/>
					<input type="hidden" id="imagepath" value="${activity.entityimage[0].imagepath}" name="imagepath"/>
					<a class="button" href="javascript:;"  rel="dlg_page7" onClick="doUploadActivityImage()" ><span>选择图片</span></a>
				</dd>
			</dl>
			<dl>
				<dt>活动费用：</dt>
				<dd>
					<input type="text"  value="${activity.price}" name="price" class="required">
				</dd>
			</dl>
			<dl>
				<dt>申请开始时间：</dt>
				<dd>
					<fmt:formatDate value="${activity.beginapplytime}" var="beginapplytime" pattern="yyyy-MM-dd"/>
					<input type="text" readonly="true" value="${beginapplytime}" datefmt="yyyy-MM-dd" class="date textInput readonly valid required" name="cbeginapplytime">
				</dd>
			</dl>
			<dl>
				<dt>申请结束日期：</dt>
				<dd>
					<fmt:formatDate value="${activity.endapplytime}" var="endapplytime" pattern="yyyy-MM-dd"/>
					<input type="text" readonly="true" value="${endapplytime}" datefmt="yyyy-MM-dd" class="date textInput readonly valid required" name="cendapplytime">
				</dd>
			</dl>
			<dl>
				<dt>活动开始时间：</dt>
				<dd>
					<fmt:formatDate value="${activity.begintime}" var="begintime" pattern="yyyy-MM-dd"/>
					<input type="text" readonly="true" value="${begintime}" datefmt="yyyy-MM-dd" class="date textInput readonly valid required" name="cbegintime">
				</dd>
			</dl>
			<dl>
				<dt>预定数量：</dt>
				<dd>
					<input name="bookcount" class="required" type="text" size="30" value="${activity.bookcount }"/>
				</dd>
			</dl>
<!-- 			<dl> -->
<!-- 				<dt>是否vip专属：</dt> -->
<!-- 				<dd> -->
<!-- 					<label><input name="isvip" class="required" type="radio" size="30" value="1" ${activity.isvip==1?'checked':''} />是</label> -->
<!-- 					<label><input name="isvip" class="required" type="radio" size="30" value="0" ${activity.isvip==0?'checked':''}/>否</label> -->
<!-- 				</dd> -->
<!-- 			</dl> -->
				<input name="isvip" class="required" type="hidden" size="30" value="0"/>
			<dl>
				<dt>活动介绍：</dt>
				<dd>
					 <textarea name="introduction" id="introduction"  style="width:800px;height:100px;">${activity.introduction}</textarea>
				</dd>
			</dl>
			<dl>
				<dt>活动安排：</dt>
				<dd>
					 <textarea name="schedule" id="schedule" class="ckeditor" style="width:800px;height:500px;">${activity.schedule}</textarea>
				</dd>
			</dl>
			<dl>
				<dt>行程亮点：</dt>
				<dd>
					 <textarea name="highlight" id="highlight" class="ckeditor" style="width:800px;height:500px;">${activity.highlight}</textarea>
				</dd>
			</dl>
			<dl>
				<dt>行程线路：</dt>
				<dd>
					 <textarea name="route" id="route" class="ckeditor" style="width:800px;height:500px;">${activity.route}</textarea>
				</dd>
			</dl>
			<dl>
				<dt>价格说明：</dt>
				<dd>
					 <textarea name="pricedesc" id="pricedesc" class="ckeditor" style="width:800px;height:500px;">${activity.pricedesc}</textarea>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
	<script type="text/javascript">
	     var schedule = CKEDITOR.replace( 'schedule' );
	     var highlight = CKEDITOR.replace( 'highlight' );
	     var route = CKEDITOR.replace( 'route' );
	     var pricedesc = CKEDITOR.replace( 'pricedesc' );
	     CKFinder.setupCKEditor( schedule, '/ckfinder/' );
	     CKFinder.setupCKEditor( highlight, '/ckfinder/' );
	     CKFinder.setupCKEditor( route, '/ckfinder/' );
	     CKFinder.setupCKEditor( pricedesc, '/ckfinder/' );
    	 $("#submit").click(function(){
    		 $("#schedule").text(schedule.getData());
    		 $("#highlight").text(highlight.getData());
    		 $("#route").text(route.getData());
    		 $("#pricedesc").text(pricedesc.getData());
    		 document.myactivityForm.onsubmit();
    	 });
	</script>
	
</div>













