<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script  type="text/javascript">

    var productTypefinder;
	
    function productTypedoCKFinderUpload()
    {
   		productTypefinder = new CKFinder();
   	 	productTypefinder.selectActionFunction = productTypeSetFileField;
    	productTypefinder.popup();       
    }
    function productTypeSetFileField( fileUrl )
    {  	
    	$(".LogImage").attr("src",fileUrl);  
    	$(".logImagePath").val(fileUrl);
    }
    $(function(){
    	$("#chooseLevel").children("label").children("input").click(function(){
    		var i = $(this).val();
    		var dom = $("#typeform"+i);
    		dom.siblings("form").hide();
    		dom.show();
    		$(".parentName").empty();
        	$(".LogImage").attr("src","");  
    	});
    })
</script> 

<div class="pageContent">
	<c:if test="${action eq 'add'}">
		<fieldset>
			<legend id="addtype">选择添加类型级别：</legend>
			<dl>
				<dd id="chooseLevel">
					<label><input name="level" type="radio" class="required" size="30" value="1"/>一级</label>
					<label><input name="level" type="radio" class="required" size="30" value="2"/>二级</label>
					<label><input name="level" type="radio" class="required" size="30" value="3" checked="checked"/>三级</label>
				</dd>
			</dl>
		</fieldset>
	</c:if>
	<form method="post" action="manage/productType/${action eq 'update'?'updateProductType.do':'saveProductType.do'}" id="typeform3"  class="pageForm required-validate" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<c:if test="${action eq 'update'}">
			<input type="hidden" value="${productType.productTypeId}" name="productTypeId"/>
		</c:if>
		<input name="validflag" type="hidden"  value="1"/>
		<div class="pageFormContent nowrap" layoutH="${action eq 'update'?56:'100'}">
			<fieldset>
			<legend id="addtype">产品类型操作</legend>
				<div>
					<dl>
						<dt>类型名称：</dt>
						<dd>
							<input name="typeName" type="text" class="required" size="30" value="${productType.typeName}"/>
						</dd>
					</dl>
					<dl>
						<dt>父类型：</dt>
						<dd>
							<input type="hidden" name="productType.productTypeId" value="${productType.parentProductType.productTypeId}" />
							<input type="text" class="required" name="productType.typeName" class="parentName" readonly="readonly" value="${productType.parentProductType.typeName}"  lookupGroup="productType" /> 
							<c:if test="${action eq 'add'}">
								<a class="btnLook" href="manage/productType/getAllProductType.do?action=dialog&level=2" lookupGroup="productType">查找选择二级商品类型</a>
							</c:if>
						</dd>
					</dl>
					
					
					<dl>
						<dt>&nbsp;</dt>
						<dt>Log图片：</dt>
						<dd>
							<img src="${productType.logImagePath}" alt="&nbsp;log图片(建议大小(130*130))" class="LogImage"/>
							<input type="hidden" value="${productType.logImagePath}" name="logImagePath" class="logImagePath"/>
						</dd>
					
					</dl>
					<dl>
						<dt>&nbsp;</dt>
						<dd>
						<a class="button" href="javascript:;"  rel="dlg_page7" onClick="productTypedoCKFinderUpload();"><span>选择图片</span></a></dd>
					</dl>
					<dl>
						<dt>包含属性(多属性请换行)：</dt>
						<dd><textarea name="properties" class="required" cols="60" rows="5" alt="" >${productType.properties}</textarea></dd>
					</dl>
					<dl>
						<dt>类型描述：</dt>
						<dd><textarea name="description" class="required" cols="60" rows="5" alt="" >${productType.description}</textarea></dd>
					</dl>
				</div>
			</fieldset>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
	<form method="post" action="manage/productType/${action eq 'update'?'updateProductType.do':'saveProductType.do'}" style="display:none;" id="typeform2" class="pageForm required-validate" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<c:if test="${action eq 'update'}">
			<input type="hidden" value="${productType.productTypeId}" name="productTypeId"/>
		</c:if>
		<input name="validflag" type="hidden"  value="1"/>
		<div class="pageFormContent nowrap" layoutH="${action eq 'update'?56:'100'}">
		<fieldset>
			<legend id="addtype">产品类型操作</legend>
				<div>
					<dl>
						<dt>类型名称：</dt>
						<dd>
							<input name="typeName" type="text"  class="required" size="30" value="${productType.typeName}"/>
						</dd>
					</dl>
					<dl>
						<dt>父类型：</dt>
						<dd>
							<input type="hidden" name="productType.productTypeId" value="${productType.parentProductType.productTypeId}" />
							<input type="text" class="required" name="productType.typeName" class="parentName" readonly="readonly" value="${productType.parentProductType.typeName}"  lookupGroup="productType" /> 
							<c:if test="${action eq 'add'}">
								<a class="btnLook" href="manage/productType/getAllProductType.do?action=dialog&level=1" lookupGroup="productType">查找选择一级商品类型</a>
							</c:if>
						</dd>
					</dl>
					
					<dl>
						<dt>类型描述：</dt>
						<dd><textarea name="description" class="required" cols="60" rows="5" alt="" >${productType.description}</textarea></dd>
					</dl>
					<dl>
						<dt>&nbsp;</dt>
						<dt>Log图片：</dt>
						<dd>
							<img src="${productType.logImagePath}" alt="&nbsp;log图片(建议大小(130*130))" class="LogImage"/>
							<input type="hidden" value="${productType.logImagePath}" name="logImagePath" class="logImagePath"/>
						</dd>
					</dl>
					<dl>
						<dt>&nbsp;</dt>
						<dd>
						<a class="button" href="javascript:;"  rel="dlg_page7" onClick="productTypedoCKFinderUpload();"><span>选择图片</span></a></dd>
					</dl>
				</div>
			</fieldset>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
	<form method="post" action="manage/productType/${action eq 'update'?'updateProductType.do':'saveProductType.do'}" style="display:none;" id="typeform1" class="pageForm required-validate" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<c:if test="${action eq 'update'}">
			<input type="hidden" value="${productType.productTypeId}" name="productTypeId"/>
		</c:if>
		<input name="validflag" type="hidden"  value="1"/>
		<input type="hidden" name="parentId" value="0000000000" />
		<div class="pageFormContent nowrap" layoutH="${action eq 'update'?56:'100'}">
			<fieldset>
			<legend id="addtype">产品类型操作</legend>
				<div>
					<dl>
						<dt>类型名称：</dt>
						<dd>
							<input name="typeName" type="text" class="required" size="30" value="${productType.typeName}"/>
						</dd>
					</dl>
					<dl>
						<dt>类型描述：</dt>
						<dd><textarea name="description" class="required" cols="60" rows="5" alt="" >${productType.description}</textarea></dd>
					</dl>
					<dl>
						<dt>&nbsp;</dt>
						<dt>Log图片：</dt>
						<dd>
							<img src="${productType.logImagePath}" alt="&nbsp;log图片(建议大小(130*130))" class="LogImage"/>
							<input type="hidden" value="${productType.logImagePath}" name="logImagePath" class="logImagePath"/>
						</dd>
					</dl>
					<dl>
						<dt>&nbsp;</dt>
						<dd>
						<a class="button" href="javascript:;"  rel="dlg_page7" onClick="productTypedoCKFinderUpload();"><span>选择图片</span></a></dd>
					</dl>
				</div>
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

<script type="text/javascript">
	$(function(){
		if("${productType.productTypeId}"!=""){
			var dom = $("#typeform"+(("${productType.productTypeId}".length)/2));
			dom.siblings("form").hide();
			dom.show();
		}
	});
</script>



