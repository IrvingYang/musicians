<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->



<div class="pageContent">

	<form method="post" action="manage/programa/updatePrograma.do" class="pageForm required-validate" id="myproducForm${programa.programaId}" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			<input type="hidden" value="${programa.programaId}" name="programaId"/>
			<input type="hidden" name="programaType" value="${programa.programaType}"/>
			<input name="validflag" type="hidden"  value="1"/>
			<dl>
				<dt>栏目名称：</dt>
				<dd>
					<input name="programaTitle" type="text" readonly class="required" size="30" value="${programa.programaTitle}"/>
				</dd>
			</dl>
			
			<dl>
				<dt>栏目内容：</dt>
				<dd>
					 <textarea name="programaContent" id="remarkHtml${programa.programaId}" class="ckeditor" style="width:800px;height:500px;" >${programa.programaContent}</textarea>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="submit${programa.programaId}">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
	<script type="text/javascript">
	     var editor = CKEDITOR.replace( "remarkHtml${programa.programaId}" );
	     CKFinder.setupCKEditor( editor, '/ckfinder/' );
    	 $("#submit${programa.programaId}").click(function(){
    		 $("#remarkHtml${programa.programaId}").text(editor.getData());
    		 $("#myproducForm${programa.programaId}").submit();
    	 });
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
