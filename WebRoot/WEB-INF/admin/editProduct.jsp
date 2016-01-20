<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->


<script  type="text/javascript">

    var finder;
	
    function doCKFinderUpload()
    {
 
       finder = new CKFinder();
       finder.selectActionFunction = SetFileField;
       finder.popup();       
    }
    function doCKFinderUploadbigdeal()
    {
 
       finder = new CKFinder();
       finder.selectActionFunction = SetFileFieldGroupbuyBigdeal;
       finder.popup();       
    }
    function deleteImg(obj,imgId){
    	if(imgId==0){
    		$(obj).parent().parent().remove();
    		return;
    	}
    }
    
   /*  function getFileName(fileUrl){
    	var index = fileUrl.lastIndexOf("/") + 1;
    	var filename = fileUrl.substr(index);
		alert(filename);
    	return filename;
    } */
    
    function SetFileFieldGroupbuyBigdeal( fileUrl )
    {  	
    	$("#groupbigdeal").val(fileUrl);
    	$("#groupbigdealimg").attr("src",fileUrl);
    }
    function SetFileField( fileUrl )
    {  	
    	$("#productImg").after(
    		'<dl class="pimg">'+
			'	<dt>图片：&nbsp;</dt>'+
			'	<dd id="attributes">'+
			'		<img src='+fileUrl+' width="200" height="200"  align="middle" /> <a href="javascript:;" onClick="deleteImg(this,0);" style="color:red;">删除</a>'+
			'       <input type="hidden" name="image" value="'+fileUrl+'"/> '+
			'	</dd>'+
			'</dl>');  
    }
    function productcall(){
    	alert();
    }
</script> 

<div class="pageContent">

	<form method="post" action="manage/product/${action eq 'add'?'saveProduct.do':'updateProduct.do'}" class="pageForm required-validate" name="myproducForm" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			<c:if test="${action eq 'update'}">
				<input type="hidden" value="${product.productId}" name="productId"/>
			</c:if>
			<c:if test="${sessionScope.admin.partnerflag==0}">
				<input type="hidden" value="1" name="salesbyself"/>
			</c:if>
			<input type="hidden" value="${product.providerId}" name="providerId"/>
			<input type="hidden" name="ontimes" value="${product.onTime}"/>
			<input type="hidden" name="grade" value="${product.grade}"/>
			<input type="hidden" name="reviewcount" value="${product.reviewcount}"/>
			<input type="hidden" name="salesvolume" value="${product.salesvolume}"/>
			
			
			<input name="validflag" type="hidden"  value="1"/>
			<dl>
				<dt>商品名称：</dt>
				<dd>
					<input name="productName" type="text" class="required" size="30" value="${product.productName}"/>
				</dd>
			</dl>
			
			<dl>
				<dt>商品类型</dt>
				<dd>
					<input type="hidden" name="productType.productTypeId" value="${product.product_type.productTypeId}" id="thirdType" oldValue="${product.product_type.productTypeId}" />
					<input type="text" class="required" name="productType.typeName" readonly="readonly" value="${product.product_type.typeName}" callback="productcall();"  lookupGroup="productType" /> 
					<c:if test="${action eq 'add'}">
						<a class="btnLook" href="manage/productType/getAllProductType.do?action=dialog&level=3" lookupGroup="productType" >查找选择商品类型</a>
					</c:if>
				</dd>
			</dl>
			<dl>
				<dt>描述：</dt>
				<dd><textarea name="description" class="required" type="text" size="50" alt="" >${product.description}</textarea></dd>
			</dl>
			<dl>
				<dt>商品定价：</dt>
				<dd>
					<input name="shopPrice" class="required" type="text" size="10" value="${product.shopPrice }"/>
				</dd>
			</dl>
			<dl>
				<dt>单位：</dt>
				<dd>
					<input name="unit" class="required" type="text" size="30" value="${product.unit }"/>
				</dd>
			</dl>
			<dl>
				<dt>库存：</dt>
				<dd>
					<input name="stockNumber" class="required" type="text" size="30" value="${product.stockNumber }"/>
				</dd>
			</dl>
			<dl>
				<dt>产品品牌：</dt>
				<dd>
					<input type="hidden" name="vendor.brandid" value="${product.brand_vendor.brandid}"/>
					<input type="text" class="required" name="vendor.brandname" value="${product.brand_vendor.brandname}" readonly="readonly"  lookupGroup="vendor" /> 
						<a class="btnLook" href="manage/brand_vendor/getAllbrand_vendor.do?action=dialog" lookupGroup="vendor">查找选择商品品牌</a>
				</dd>
			</dl>
			<%-- <dl>
				<dt>商品产地：</dt>
				<dd>
					<input type="hidden" name="city.cityId" value="${product.city.cityId}"/>
					<input type="text" class="required" name="city.cityName" value="${product.city.cityName}" readonly="readonly"  lookupGroup="city" /> 
						<a class="btnLook" href="manage/city/getAllCity.do?action=dialog" lookupGroup="city">查找选择商品产地</a>
				</dd>
			</dl> --%>
			<dl>
				<dt>商品产地：</dt>
				<dd>
					<input type="hidden" name="country.countryId" value="${product.country.countryId}"/>
					<input type="text" class="required" name="country.countryName" value="${product.country.countryName}" readonly="readonly"  lookupGroup="country" /> 
						<a class="btnLook" href="manage/country/getCountryList.do?action=dialog" lookupGroup="country">查找选择商品产地</a>
				</dd>
			</dl>
			<dl>
				<dt>销售范围</dt>
				<dd>
					<%-- <c:if test="${sessionScope.admin.partnerflag==0}"> --%>
						<label><input type="checkbox" name="shop" value="1" ${fn:substring(product.salesrange,0,1)==1?'checked':''}  ${action eq 'add'?'checked':''}/>商城</label>
					<%-- </c:if> --%>
					<label><input type="checkbox" name="group" value="1" ${fn:substring(product.salesrange,1,2)==1?'checked':''}/>团购</label>
					<label><input type="checkbox" name="bigdeal" value="1" ${fn:substring(product.salesrange,2,3)==1?'checked':''}/>大宗交易</label>
				</dd>
			</dl>
			<dl>
				<dt>商品属性：</dt>
				<dd id="attributes">
				</dd>
			</dl>
			<dl id="productImg">
				<dt>商品图片：</dt>
				<dd id="attributes">
					<a class="button" href="javascript:;"  rel="dlg_page7" onClick="doCKFinderUpload()" ><span>添加图片</span></a>（建议720x720）
				</dd>
			</dl>
			<c:if test="${'update' eq action }">
				<c:forEach items="${product.productimglist}" var="productImg">
					<dl class="pimg">
						<dt>图片：&nbsp;</dt>
						<dd id="attributes">
							<img src="${productImg.path}" width="200" height="200"  align="middle" /> <a href="javascript:;" onClick="deleteImg(this,0);" style="color:red;">删除</a>
					       <input type="hidden" name="image" value="${productImg.path}"/>
						</dd>
					</dl>
				</c:forEach>
			</c:if>
			<dl id="productImg">
				<dt>团购(大宗交易图片)：</dt>
				<dd id="attributes">
					<input type="hidden" name="groupbigdealimg" value="${productimg.path}" id="groupbigdeal" class="required"/>
					<img src="${productimg.path}" id="groupbigdealimg" alt="团购和大宗交易图片（建议900x500）" width="400" />
					<a class="button" href="javascript:;"  rel="dlg_page7" onClick="doCKFinderUploadbigdeal()" ><span>选择图片</span></a>
				</dd>
			</dl>
			<dl>
				<dt>产品溯源：</dt>
				<dd>
					 <textarea name="trackInfo" rows="10" cols="100">${product.producttrack.trackInfo}</textarea>
				</dd>
			</dl>
			<dl>
				<dt>商品详情：</dt>
				<dd>
					 <textarea name="remarkHtml" id="remarkHtml" class="ckeditor" style="width:800px;height:500px;" >${product.remarkHtml}</textarea>
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
	     var editor = CKEDITOR.replace( 'remarkHtml' );
	     CKFinder.setupCKEditor( editor, '/ckfinder/' );
    	 $("#submit").click(function(){
    		 $("#remarkHtml").text(editor.getData());
    		 document.myproducForm.onsubmit();
    	 });
			function loadProperty(){
				var value = $("#thirdType").val();
				var oldValue = $("#thirdType").attr("oldValue");
				if(oldValue!=value){
					$("#thirdType").attr("oldValue",value);
					$.get("manage/productType/getProductTypeDetail.do",{productTypeId:value},function(data){
						var attributeArray = data.properties.split(",");
						$("#attributes").children().remove();
						for(var i=0;i<attributeArray.length;i++){
							$("#attributes").append('<label style="width:80px;">'+attributeArray[i]+'：</label><input type="text" name="property"/><br /><br />');
						}
						$("#attributes").append("<input type='hidden' value='"+data.properties+"' name='propertyName'/>");
					});
				}
			}
			setInterval(loadProperty, 1000);
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
