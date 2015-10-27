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

	<form method="post" action="manage/ad/${action eq 'add' ?'addAdvertising.do':'updateAdvertisting.do' }" class="pageForm required-validate" name="myproducForm" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			<input name="validflag" type="hidden"  value="1"/>
			<input name="adid" type="hidden"  value="${advertising.adid}"/>
			<c:if test="${action eq 'update' }">
				<input name="adarea" type="hidden"  value="${advertising.adarea}"/>
				<input name="adlink" type="hidden"  value="${advertising.adlink}"/>
				<input name="adserial" type="hidden"  value="${advertising.adserial}"/>
				<input name="adtype" type="hidden"  value="${advertising.adtype}"/>
			</c:if>
			<fieldset>
			<legend id="addtype">广告编辑</legend>
			<c:if test="${action eq 'add' }">
			<dl>
				<dt>广告类型：</dt>
				<dd id="chadType">
					<label><input type="radio" value="1" class="required" checked="checked" name="adtype"  ${advertising.adtype==1?'checked':''}/>单个产品广告</label>
<!-- 					<label><input type="radio" value="2" class="required" checked="checked" name="adtype"  ${advertising.adtype==2?'checked':''}/>批量产品广告</label> -->
					<label><input type="radio" value="3" class="required" name="adtype"  ${advertising.adtype==3?'checked':''}/>团购产品广告</label>
					<label><input type="radio" value="4" class="required" name="adtype"  ${advertising.adtype==4?'checked':''}/>趣旅游</label>
					<label><input type="radio" value="5" class="required" name="adtype"  ${advertising.adtype==5?'checked':''}/>趣采摘</label>
				</dd>
			</dl>
			<dl>
				<dt>选择广告产品</dt>
				<dd>
					<input type="hidden" class="required" name="entity.productId" value=""  lookupGroup="entity" /> 
					<input type="text" class="required" name="entity.productName" value="" readonly="readonly"  lookupGroup="entity" /> 
					<a class="btnLook" id="chAdPro" href="manage/productshop/queryAllProductShop.do?action=dialog&typeId=0" lookupGroup="entity">查找选择商品</a>
				</dd>
			</dl>
			<dl>
				<dt>选择广告页面：</dt>
				<dd>
					<select name="code1" id="adcode1">
						<option value="01">首页</option>
						<option value="05">收藏夹</option>
						<option value="06">产品列表页</option>
						<option value="07">产品详细页</option>
						<option value="08">结算页面</option>
						<option value="10">订单详情</option>
						<option value="12">团购详细</option>
						<option value="15">大宗交易详细</option>
						<option value="18">趣采摘</option>
						<option value="20">趣旅游列表页</option>
						<option value="24">合作社产品详细页</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>选择广告位置：</dt>
				<dd>
					<select name="code2" id="adcode2">
						<option value="01">第01位广告位</option>
						<option value="02">第02位广告位</option>
						<option value="03">第03位广告位</option>
						<option value="04">第04位广告位</option>
						<option value="05">第05位广告位</option>
					</select>
				</dd>
			</dl>
			</c:if>
			<dl>
				<dt>广告名称：</dt>
				<dd>
					<input type="text"  name="adname" value="${advertising.adname}"/>
				</dd>
			</dl>
			
			<dl>
				<dt>开始时间：</dt>
				<dd>
					<fmt:formatDate value="${advertising.adbegintime}" pattern="yyyy-MM-dd" var="startTime"/>
					<input name="startTime" type="text" size="20" value="${startTime}" datefmt="yyyy-MM-dd" class="required date textInput readonly valid" >
					<a href="javascript:;" class="inputDateButton">选择</a>
				</dd>
			</dl>
			<dl>
				<dt>结束时间：</dt>
				<dd>
					<fmt:formatDate value="${advertising.adendtime}" pattern="yyyy-MM-dd" var="endTime"/>
					<input name="endTime" type="text" size="20" value="${endTime}" datefmt="yyyy-MM-dd" class="required date textInput readonly valid" >
					<a href="javascript:;" class="inputDateButton">选择</a>
				</dd>
			</dl>
			<dl>
				<dt>是否付费：</dt>
				<dd id="bischarge">
					&nbsp;<label><input type="radio" value="1" name="ischarge" class="required" checked="checked"  ${advertising.ischarge==1?'checked':''}/>是</label>
					<label><input type="radio" value="0" name="ischarge" class="required" ${advertising.ischarge==0?'checked':''}/>否</label>
				</dd>
			</dl>
			<dl>
				<dt>付费标准：</dt>
				<dd id="cmode">
					&nbsp;<label><input type="radio" value="1" name="chargemode" checked="checked" ${advertising.chargemode==1?'checked':''}/>按日计费</label>
					<label><input type="radio" value="2" name="chargemode"  ${advertising.chargemode==2?'checked':''}/>按月计费</label>
					<label><input type="radio" value="3" name="chargemode"  ${advertising.chargemode==3?'checked':''}/>一次性费用</label>
					<label><input type="radio" value="3" name="chargemode"  ${advertising.chargemode==4?'checked':''}/>千人印象</label>
					<label><input type="radio" value="3" name="chargemode"  ${advertising.chargemode==5?'checked':''}/>点击次数</label>
				</dd>
			</dl>
			<dl>
				<dt>费用：</dt>
				<dd>
					<input name="price" id="adprice" type="text" class="number" size="20" value="${advertising.price}"/>
				</dd>
			</dl>
			<dl>
				<dt>广告图片：</dt>
				<dd>
					<input type="hidden" name="image1" value="${advertising.ad_imagesList[0].imagepath}" id="image1"/>
					<img src="${advertising.ad_imagesList[0].imagepath}" alt="900*400" id="image1image"/>
					<a class="button" href="javascript:;"  rel="dlg_page7" onClick="uploadAdImage()" ><span>选择图片</span></a>
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
			$("#adcode1").change(function(){
				$("#adcode2").children().remove();
				var value = $(this).val();
				if(value=="01"){
					for(var i=1;i<=5;i++){
						$("#adcode2").append('<option value="0'+i+'">第0'+i+'位广告</option>');
					}
					$("#image1image").attr("alt","900*400");
				}else if(value=="05" || value=="07" || value=="10" || value=="24"){
					for(var i=1;i<=2;i++){
						$("#adcode2").append('<option value="0'+i+'">第0'+i+'位广告</option>');
					}
					$("#image1image").attr("alt","262*220");
				}else if(value=="06"){
					for(var i=1;i<=3;i++){
						$("#adcode2").append('<option value="0'+i+'">第0'+i+'位广告</option>');
					}
					$("#image1image").attr("alt","900*400");
				}else if(value=="08" || value=="12" || value=="15"){
					$("#adcode2").append('<option value="01">第01位广告</option>');
					$("#image1image").attr("alt","262*220");
				}
				else if(value=="20" || value=="18"){
					$("#adcode2").append('<option value="01">第01位广告</option>');
					$("#image1image").attr("alt","900*400");
				}
				
			});
			$("#adcode2").change(function(){
				var adcode1 = $("#adcode1").val();
				var adcode2 = $(this).val();
				if(adcode1=="01" || adcode1=="20" || adcode1=="18"){
					if(adcode2=="01"){
						$("#image1image").attr("alt","900*400");
					}
					else if(adcode2=="02" || adcode2=="03"){
						$("#image1image").attr("alt","550*220");
					}else{
						$("#image1image").attr("alt","262*220");
					}
				}else if(adcode1=="06"){
					if(adcode2=="01"){
						$("#image1image").attr("alt","900*400");
					}else{
						$("#image1image").attr("alt","262*220");
					}
				}else{
					$("#image1image").attr("alt","262*220");
				}
			})
			$("#chadType").children("label").children("input").click(function(){
				var value = $(this).val();
				if(value==1){
					$("#chAdPro").attr("href","manage/productshop/queryAllProductShop.do?action=dialog&typeId=0");
				}else if(value==3){
					$("#chAdPro").attr("href","manage/groupbuy/getAllProductGroupBuy.do?action=dialog");
				}
			});
			$("#bischarge").children("label").children("input").click(function(){
				var value = $(this).val();
				if(value==1){
					$("#cmode").find("input:eq(0)").attr("checked","true")
					$("#cmode").find("input").removeAttr("disabled");
					$("#adprice").removeAttr("disabled")
				}else{
					$("#cmode").find("input").removeAttr("checked")
					$("#cmode").find("input").attr("disabled","disabled");
					$("#adprice").attr("disabled","disabled")
					$("#adprice").val("");
				}
			})
		})
	</script>
	
</div>
