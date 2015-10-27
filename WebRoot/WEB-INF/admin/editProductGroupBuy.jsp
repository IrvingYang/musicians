<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->



<div class="pageContent">

	<form method="post" action="manage/groupbuy/${action eq 'update'?'updateGroupBuy.do':'addGroupBuy.do'}" class="pageForm required-validate" name="myproducForm" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			<input type="hidden" value="${groupbuy.groupbuyid}" name="groupbuyid"/>
			<input name="validflag" type="hidden"  value="1"/>
			<fieldset>
			<legend id="addtype">团购商品操作</legend>
			<dl>
				<dt>商品：</dt>
				<dd>
					<!-- suggestFields="productName" suggestUrl="demo/database/db_lookupSuggest.html"-->
					<input type="hidden" name="groupbuy.productId" value="${groupbuy.productId}"/>
					<input type="text" class="required" name="groupbuy.productName" value="${groupbuy.product.productName}" readonly="readonly"  lookupGroup="groupbuy" /> 
					<c:if test="${action eq 'add'}">
						<a class="btnLook" href="manage/product/productList.do?action=dialog&typeId=1" lookupGroup="groupbuy">查找选择商品</a>
					</c:if>
				</dd>
			</dl>
			<dl>
				<dt>团购模式：</dt>
				<dd>
					<label><input name="groupBuyType" type="radio" class="required" size="20" value="0" ${groupbuy.groupBuyType==0?'checked':''} />预开团模式</label>
					<label><input name="groupBuyType" type="radio" class="required" size="20" value="1" ${groupbuy.groupBuyType==1?'checked':''} />立即开团模式</label>
				</dd>
			</dl>
			<dl>
				<dt>团购价格：</dt>
				<dd>
					<input name="groupBuyPrice" type="text" class="required number" size="20" value="${groupbuy.groupBuyPrice}"/>
				</dd>
			</dl>
			<dl>
				<dt>单位：</dt>
				<dd>
					<input name="unit" type="text" class="required" size="10" value="${groupbuy.unit}"/>
				</dd>
			</dl>
			<dl>
				<dt>销售范围：</dt>
				<dd>
					<input name="salesarea" type="text" class="required" size="20" value="${groupbuy.salesarea}"/>
				</dd>
			</dl>
			<dl>
				<dt>库存：</dt>
				<dd>
					<input name="stockNumber" type="text" class="required digits" size="20" value="${groupbuy.stockNumber}"/>
				</dd>
			</dl>
			<dl>
				<dt>团购开始时间：</dt>
				<dd>
					<fmt:formatDate value="${groupbuy.groupBuyStartTime}" pattern="yyyy-MM-dd" var="startTime"/>
					<input name="startTime" type="text" size="10" value="${startTime}" datefmt="yyyy-MM-dd" class="required date textInput readonly valid" >
					<a href="javascript:;" class="inputDateButton">选择</a>
				</dd>
			</dl>
			<dl>
				<dt>团购结束时间：</dt>
				<dd>
					<fmt:formatDate value="${groupbuy.groupBuyEndTime}" pattern="yyyy-MM-dd" var="endTime"/>
					<input name="endTime" type="text" size="10" value="${endTime}" datefmt="yyyy-MM-dd" class="date textInput readonly valid required">
					<a href="javascript:;" class="inputDateButton">选择</a>
				</dd>
			</dl>
			<dl>
				<dt>最小购买数量：</dt>
				<dd>
					<input name="minBuyCount" type="text" class="required digits" size="10" value="${groupbuy.minBuyCount}"/>
				</dd>
			</dl>
			<dl>
				<dt>开团数量或者金额：</dt>
				<dd>
					<input name="targetCount" type="text" class="required digits" size="10" value="${groupbuy.targetCount}"/>
				</dd>
			</dl>
			<dl>
				<dt>开团标准：</dt>
				<dd>
					<label><input name="targetType" type="radio" class="required" value="0" ${groupbuy.targetType==0?'checked':''}/>金额达到标准</label>
					<label><input name="targetType" type="radio" class="required" value="1" ${groupbuy.targetType==1?'checked':''}/>数量达到标准</label>
				</dd>
			</dl>
			<dl>
				<dt>团购说明：</dt>
				<dd>
					 <textarea name="groupbuyhint"  style="width:300px;height:50px;" >${groupbuy.groupbuyhint}</textarea>
<!-- 					 id="remarkHtml" class="ckeditor" -->
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
// 	     var editor = CKEDITOR.replace( 'remarkHtml' );
// 	     CKFinder.setupCKEditor( editor, '/ckfinder/' );
//     	 $("#submit").click(function(){
//     		 $("#remarkHtml").text(editor.getData());
//     		 document.myproducForm.onsubmit();
//     	 });
// 		$(function(){
// 			$("#rootType").change(function(){
// 				var value = $(this).val();
// 				if(value=="00"){
// 					$("#secondType").attr("disabled","disabled");
// 					$("#thirdType").attr("disabled","disabled");
// 				}else{
// 					$.get("manage/productType/getProductTypeByType.do",{type:1,typeId:value},function(data){
// 						$("#secondType").removeAttr("disabled");
// 						$("#secondType").children("option:gt(0)").remove();
// 						$.each(data,function(index,productType){
// 							$("#secondType").append("<option value='"+productType.productTypeId+"' >"+productType.typeName+"</option>");
// 						});
// 					});
// 				}
// 			});
			
// 			$("#secondType").change(function(){
// 				var value = $(this).val();
// 				if(value=="00"){
// 					$("#thirdType").attr("disabled","disabled");
// 				}else{

// 					$.get("manage/productType/getProductTypeByType.do",{type:1,typeId:value},function(data){
// 						$("#thirdType").removeAttr("disabled");
// 						$("#thirdType").children("option:gt(0)").remove();
// 						$.each(data,function(index,productType){
// 							$("#thirdType").append("<option value='"+productType.productTypeId+"'  >"+productType.typeName+"</option>");
// 						});
// 					});
					
// 				}
// 			});
			$("#loadProperty").click(function(){
				var value = $("#thirdType").val();
				if(value==""){
					alert("请先选择产品类型");
					return;
				}else{
					$.get("manage/productType/getProductTypeDetail.do",{productTypeId:value},function(data){
						var attributeArray = data.properties.split(",");
						$("#attributes").children().remove();
						for(var i=0;i<attributeArray.length;i++){
							$("#attributes").append('<label style="width:80px;">'+attributeArray[i]+'：</label><input type="text" name="property"/><br /><br />');
						}
						$("#attributes").append("<input type='hidden' value='"+data.properties+"' name='propertyName'/>");
					});
				}
			});
			$(function(){
				
				if('${product.attribute}'!=""){
					var value = $("#thirdType").val();
					eval("var property="+'${product.attribute}');
					$.get("manage/productType/getProductTypeDetail.do",{productTypeId:value},function(data){
						var attributeArray = data.properties.split(",");
						$("#attributes").children().remove();
						for(var i=0;i<attributeArray.length;i++){
							$("#attributes").append('<label style="width:80px;">'+attributeArray[i]+'：</label><input type="text" value="'+property[i].propertyValue+'" name="property"/><br /><br />');
						}
						$("#attributes").append("<input type='hidden' value='"+data.properties+"' name='propertyName'/>");
					});
					
				}
				
			})
// 			$("#state").change(function(){
// 				var value = $(this).val();
// 				if(value=='00'){
// 					$("#city").attr("disabled","disabled");
// 					return;
// 				}
// 				$("#city").removeAttr("disabled");
// 				$.get("manage/city/getCityByTypeId.do",{type:1,typeId:value},function(data){
// 					$("#city").children("option:gt(0)").remove();
// 					$.each(data,function(index,city){
// 						$("#city").append('<option value="'+city.cityId+'">'+city.cityName+'</option>');
// 					});
// 				});
// 			});
// 		});
	</script>
	
</div>
