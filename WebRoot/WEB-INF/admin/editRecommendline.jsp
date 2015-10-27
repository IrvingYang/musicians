<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>


<script  type="text/javascript">

    var recommendlineImage;
	
    function doUploadRecommendImage()
    {
 
    	recommendlineImage = new CKFinder();
    	recommendlineImage.selectActionFunction = SetRecommendFileField;
    	recommendlineImage.popup();       
    }
    function SetRecommendFileField( fileUrl )
    {  	
    	$("#imagepath").val(fileUrl);
    	$("#imagetarget").attr("src",fileUrl);
    }
</script> 

<div class="pageContent">

	<form method="post" action="manage/recommendline/${action eq 'add'?'addRecommendline.do':'updateRecommendline.do'}" class="pageForm required-validate" name="myrecommendlineForm" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			<c:if test="${action eq 'update'}">
				<input type="hidden" value="${recommendline.recommendlineid}" name="recommendlineid"/>
				<input type="hidden" value="${recommendline.coverimageid}" name="coverimageid"/>
				<input type="hidden" value="${recommendline.providerId}" name="providerId"/>
				<input type="hidden" value="${recommendline.status}" name="status"/>
			</c:if>
			
			<dl>
				<dt>线路类型：</dt>
				<dd>
					<input type="hidden" name="recommendlinetype.linetypeid" value="${recommendline.recommendlinetype.linetypeid}" id="thirdType" oldValue="${recommendline.recommendlinetype.linetypeid}"   lookupGroup="recommendlinetype" />
					<input type="text" class="required" name="recommendlinetype.title" readonly="readonly" value="${recommendline.recommendlinetype.title}"  lookupGroup="recommendlinetype" /> 
					<c:if test="${action eq 'add'}">
						<a class="btnLook" href="manage/recommendlinetype/getRecommendlinetype.do" lookupGroup="recommendlinetype" >查找选择商品类型</a>
					</c:if>
				</dd>
			</dl>
			
			<dl>
				<dt>线路标题：</dt>
				<dd>
					<input name="title" type="text" class="required" size="30" value="${recommendline.title}"/>
				</dd>
			</dl>
			
			<dl id="productImg">
				<dt>线路封面图片：</dt>
				<dd id="attributes">
					<img src="${recommendline.entityimage[0].imagepath}" alt="线路封面图片、（730x360）" id="imagetarget"/>
					<input type="hidden" id="imagepath" value="${recommendline.entityimage[0].imagepath}" name="imagepath"/>
					<a class="button" href="javascript:;"  rel="dlg_page7" onClick="doUploadRecommendImage()" ><span>选择图片</span></a>
				</dd>
			</dl>
			<dl>
				<dt>线路费用：</dt>
				<dd> 
					<input type="text"  value="${recommendline.price}" name="price" class="required number">
				</dd>
			</dl>
			<dl>
				<dt>申请开始时间：</dt>
				<dd>
					<fmt:formatDate value="${recommendline.beginapplytime}" var="beginapplytime" pattern="yyyy-MM-dd"/>
					<input type="text" readonly="true" value="${beginapplytime}" datefmt="yyyy-MM-dd" class="date textInput readonly valid required" name="cbeginapplytimes">
				</dd>
			</dl>
			<dl>
				<dt>申请结束日期：</dt>
				<dd>
					<fmt:formatDate value="${recommendline.endapplytime}" var="endapplytime" pattern="yyyy-MM-dd"/>
					<input type="text" readonly="true" value="${endapplytime}" datefmt="yyyy-MM-dd" class="date textInput readonly valid required" name="cendapplytimes">
				</dd>
			</dl>
			<dl>
				<dt>出行时间：</dt>
				<dd>
					<fmt:formatDate value="${recommendline.departuretime}" var="departuretime" pattern="yyyy-MM-dd"/>
					<input type="text" readonly="true" value="${departuretime}" datefmt="yyyy-MM-dd" class="date textInput readonly valid required" name="departuretimes">
				</dd>
			</dl>
			<dl>
				<dt>出游天数：</dt>
				<dd>
					<input name="days" class="required number" type="text" size="10" value="${recommendline.days}"/>
				</dd>
			</dl>

			<dl>
				<dt>简介：</dt>
				<dd>
					 <textarea name="introduction" id="introduction"  style="width:800px;height:100px;">${recommendline.introduction}</textarea>
				</dd>
			</dl>
			<dl>
				<dt>特色：</dt>
				<dd>
					 <textarea name="feature" id="feature" class="ckeditor" style="width:800px;height:500px;">${recommendline.feature}</textarea>
				</dd>
			</dl>
			<dl>
				<dt>价格说明：</dt>
				<dd>
					 <textarea name="priceexplain" id="priceexplain" class="ckeditor" style="width:800px;height:500px;">${recommendline.priceexplain}</textarea>
				</dd>
			</dl>
			<dl>
				<dt>自购费用：</dt>
				<dd>
					 <textarea name="oneselfexpense" id="oneselfexpense" class="ckeditor" style="width:800px;height:500px;">${recommendline.oneselfexpense}</textarea>
				</dd>
			</dl>
			<dl>
				<dt>旅行提示：</dt>
				<dd>
					 <textarea name="travelprompt" id="travelprompt" class="ckeditor" style="width:800px;height:500px;">${recommendline.travelprompt}</textarea>
				</dd>
			</dl>
			<dl>
				<dt>旅行分享：</dt>
				<dd>
					 <textarea name="share" id="share" class="ckeditor" style="width:800px;height:500px;">${recommendline.share}</textarea>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="recommendsubmit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
	<script type="text/javascript">
	
	     var feature = CKEDITOR.replace( 'feature' );
	     var priceexplain = CKEDITOR.replace( 'priceexplain' );
	     var oneselfexpense = CKEDITOR.replace( 'oneselfexpense' );
	     var travelprompt = CKEDITOR.replace( 'travelprompt' );
	     var share = CKEDITOR.replace( 'share' );
	     
	     CKFinder.setupCKEditor( feature, '/ckfinder/' );
	     CKFinder.setupCKEditor( priceexplain, '/ckfinder/' );
	     CKFinder.setupCKEditor( oneselfexpense, '/ckfinder/' );
	     CKFinder.setupCKEditor( travelprompt, '/ckfinder/' );
	     CKFinder.setupCKEditor( share, '/ckfinder/' );
	     
    	 $("#recommendsubmit").click(function(){
    		 $("#feature").text(feature.getData());
    		 $("#priceexplain").text(priceexplain.getData());
    		 $("#oneselfexpense").text(oneselfexpense.getData());
    		 $("#travelprompt").text(travelprompt.getData());
    		 $("#share").text(share.getData());
    		 document.myrecommendlineForm.onsubmit();
    	 });
    	 
	</script>
	
</div>













