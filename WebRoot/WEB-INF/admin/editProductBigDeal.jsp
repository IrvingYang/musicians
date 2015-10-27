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
    function deleteImg(obj,imgId){
    	if(imgId==0){
    		$(obj).parent().parent().remove();
    		return;
    	}
    }
    function SetFileField( fileUrl )
    {  	
    	$("#bigdealimg").val(fileUrl);
    }
    function  aaa(){
    	alert();
    }
</script> 

<div class="pageContent">

	<form method="post" action="manage/productbigdeal/${action eq 'add'?'addBigDeal.do':'updateBigDeal.do'}" class="pageForm required-validate" name="myproducForm" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			<input type="hidden" value="${bigdeal.bigdealId}" name="bigdealId"/>
			<dl>
				<dt>商品:</dt>
				<dd>
					<input type="hidden" name="bigdeal.productId" value="${bigdeal.productId}" />
					<input type="text" class="required" name="bigdeal.productName" readonly="readonly" value="${bigdeal.product.productName}"  lookupGroup="bigdeal" /> 
					<c:if test="${action eq 'add'}">
						<a class="btnLook" href="manage/product/productList.do?action=dialog&typeId=2" lookupGroup="bigdeal">查找选择商品</a>
					</c:if>
				</dd>
			</dl>
			<dl>
				<dt>参考价格：</dt>
				<dd><input name="referencePrice" class="required number" type="text" value="${bigdeal.referencePrice}" size="20" ></dd>
			</dl>
			<dl>
				<dt>单位：</dt>
				<dd>
					<input name="unit" class="required" type="text" size="10" value="${bigdeal.unit }" maxlength="5"/>
				</dd>
			</dl>
			<dl>
				<dt>销售范围：</dt>
				<dd>
					<input name="salesarea" class="required" type="text" size="30" value="${bigdeal.salesarea }"/>
				</dd>
			</dl>
			<dl>
				<dt>联系人：</dt>
				<dd>
					<input name="contactMan" class="required" type="text" size="20" value="${bigdeal.contactMan }" placeholder="请输入联系人"/>
				</dd>
			</dl>
			<dl>
				<dt>联系人电话：</dt>
				<dd>
					<input name="contactTelephone" class="required phone" type="text" size="20" value="${bigdeal.contactTelephone }" maxlength="15" placeholder="请输入联系电话"/>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" id="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>
