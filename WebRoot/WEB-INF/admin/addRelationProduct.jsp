<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->

<div class="pageContent">

	<form method="post" action="manage/relation/addRelationProduct.do" class="pageForm required-validate" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			<fieldset>
				<legend>关联商品操作</legend>
				<dl>
					<dt>选择被关联商品</dt>
					<dd>
						<input type="hidden" name="relation1.productId" oldValue="" value="" id="relationorder1"/>
						<input type="text" class="required" readonly="readonly" name="relation1.productName" oninput="alert(this.value);"  lookupGroup="relation1" /> 
						<a class="btnLook" href="manage/productshop/getAllCanAddRelactionProduct.do" order="0" lookupGroup="relation1">查找选择商品类型</a>
					</dd>
				</dl>
				<dl>
					<dt>选择关联商品预览(请先选择上一项)</dt>
					<dd>
						<input type="hidden" name="relation2.productId" id="relationorder2"  oldValue="" value=""/>
						<input type="text" class="required" readonly="readonly" id="choose" placeholder="尚未选择"/> 
						<a class="btnLook" id="relationbtn" href="manage/productshop/getAllCanAddRelactionProduct.do" order="1" lookupGroup="relation2" style="display: none;" >查找选择商品类型</a>
					</dd>
				</dl>
			</fieldset>
			<fieldset>
			<legend>关联商品(请先选择被关联商品)</legend>
			<table class="table" width="100%">	
				<thead>
					<tr>
						<td width="">商品名</td>
						<td width="">上架时间</td>
						<td width="">下架时间</td>
						<td width="">原价</td>
						<td width="">促销价</td>
						<td width="">促销</td>
						<td width="">单位</td>
						<td width="">销售范围</td>
						<td width="">促销开始时间</td>
						<td width="">促销结束时间</td>
					</tr>
				</thead>
				<tbody id="relationbody">
					
				</tbody>
			</table>
		</fieldset>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
	<script type="text/javascript">

		function quartzWork(){
			var oldValue1 = $("#relationorder1").attr("oldValue");
			var value1 = $("#relationorder1").val();
			
			var oldValue2 = $("#relationorder2").attr("oldValue");
			var value2 = $("#relationorder2").val();
			if(oldValue2!=value2){
				$("#relationorder2").attr("oldValue",value2);
				$("#choose").val("已选择");
			}
			
			if(oldValue1!=value1){
				$("#relationorder1").attr("oldValue",value1);
				$("#relationorder2").next("input").next("a").attr("href","manage/productshop/getAllCanAddRelactionProduct.do?action=relation&productId="+value1);
				$("#relationbtn").css("display","block");
				
				$.ajax({
					url:"manage/productshop/getProductShopInId.do",
					data:{productId:value1},
					dataType:"json",
					success:function(data){
						$("#relationbody").children().remove();
						$.each(data,function(index,shop){
							var date = new Date(shop.onTime);
							var year = date.getFullYear();
							var month = date.getMonth()+1;
							var day = date.getDate();
							var hours = date.getHours();
							var minutes = date.getMinutes();
							var seconds = date.getSeconds();
							var onTime = (year+'年'+month+'月'+day+'日  '+hours+':'+minutes+':'+seconds);
							
							var offTime="";
							if(shop.offTime!='' && shop.offTime!=null){
								date = new Date(shop.offTime);
								year = date.getFullYear();
								month = date.getMonth()+1;
								day = date.getDate();
								hours = date.getHours();
								minutes = date.getMinutes();
								seconds = date.getSeconds();
								offTime = (year+'年'+month+'月'+day+'日  '+hours+':'+minutes+':'+seconds);
							}
							
							date = new Date(shop.promoteStartTime);
							year = date.getFullYear();
							month = date.getMonth()+1;
							day = date.getDate();
							hours = date.getHours();
							minutes = date.getMinutes();
							seconds = date.getSeconds();
							var promoteStartTime = (year+'年'+month+'月'+day+'日  '+hours+':'+minutes+':'+seconds);
							
							date = new Date(shop.promoteEndTime);
							year = date.getFullYear();
							month = date.getMonth()+1;
							day = date.getDate();
							hours = date.getHours();
							minutes = date.getMinutes();
							seconds = date.getSeconds();
							var promoteEndTime = (year+'年'+month+'月'+day+'日  '+hours+':'+minutes+':'+seconds);
							
							date = new Date(shop.lastUpdateTime);
							year = date.getFullYear();
							month = date.getMonth()+1;
							day = date.getDate();
							hours = date.getHours();
							minutes = date.getMinutes();
							seconds = date.getSeconds();
							var lastUpdateTime = (year+'年'+month+'月'+day+'日  '+hours+':'+minutes+':'+seconds);
							
							var html =
								'<tr>'+
								'<td>'+shop.product.productName+'</td>'+
								'<td>'+onTime+'</td>'+
								'<td>'+offTime+'</td>'+
								'<td>'+shop.originalPrice+'</td>'+
								'<td>'+(shop.promoteflag==0?shop.promotePrice:("<s>"+shop.promotePrice+"</s>"))+'</td>'+
								'<td>'+(shop.promoteflag==0?"否":"是")+'</td>'+
								'<td>'+shop.unit+'</td>'+
								'<td>'+shop.salesarea+'</td>'+
								'<td>'+promoteStartTime+'</td>'+
								'<td>'+promoteEndTime+'</td>'+
								'</tr>';
							$("#relationbody").append(html);
							
						});
							
					},
					error:function(){
						
					}
				});
			}
		}
		setInterval(quartzWork,100);
		
	</script>
</div>
