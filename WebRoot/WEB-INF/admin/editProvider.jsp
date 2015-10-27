<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->

<script type="text/javascript">

	var finder1;
	var finder2;
	var finder3;
	var finder4;
	var finder5;

	function uploadProvider1()
	{
	
	   finder1 = new CKFinder();
	   finder1.selectActionFunction = setProviderField1;
	   finder1.popup();       
	}
	function uploadProvider2()
	{
	
	   finder2 = new CKFinder();
	   finder2.selectActionFunction = setProviderField2;
	   finder2.popup();       
	}
	function uploadProvider3()
	{
	
	   finder3 = new CKFinder();
	   finder3.selectActionFunction = setProviderField3;
	   finder3.popup();       
	}
	function uploadProvider4()
	{
	
	   finder4 = new CKFinder();
	   finder4.selectActionFunction = setProviderField4;
	   finder4.popup();       
	}
	function uploadProvider5()
	{
	
	   finder5 = new CKFinder();
	   finder5.selectActionFunction = setProviderField5;
	   finder5.popup();       
	}
	function setProviderField1( fileUrl ){
		$("#image1").val(fileUrl);
		$("#image1image").attr("src",fileUrl);
	}
	function setProviderField2( fileUrl ){  	
		$("#image2").val(fileUrl);
		$("#image2image").attr("src",fileUrl);
	}
	function setProviderField3( fileUrl ){ 
		$("#image3").val(fileUrl); 	
		$("#image3image").attr("src",fileUrl);
	}
	function setProviderField4( fileUrl ){  
		$("#image4").val(fileUrl);	
		$("#image4image").attr("src",fileUrl);
	}
	function setProviderField5( fileUrl ){ 
		$("#image5").val(fileUrl); 	
		$("#image5image").attr("src",fileUrl);
	}
</script>

<div class="pageContent">

	<form method="post" action="manage/provider/${action eq 'add'?'saveProvider.do':'updateProvider.do'}" class="pageForm required-validate" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			<input type="hidden" value="1" name="validflag"/>
			<input type="hidden" value="0" name="superprovider"/>
			<input type="hidden" value="086" name="countryId"/>
			<input type="hidden" value="${provider.providerId }" name="providerId"/>
			<fieldset>
			<legend id="addtype">供应商编辑</legend>
			<dl>
				<dt>供应商名称：</dt>
				<dd>
					<input name="name" type="text" class="required" size="20" value="${provider.name}"/>
				</dd>
			</dl>
			<dl>
				<dt>方向：</dt>
				<dd>
					<label><input name="direction" type="radio" class="required" size="20" value="01" ${provider.direction eq '01'?'checked':''}/>凉山东</label>
					<label><input name="direction" type="radio" class="required" size="20" value="02" ${provider.direction eq '02'?'checked':''}/>凉山西</label>
					<label><input name="direction" type="radio" class="required" size="20" value="03" ${provider.direction eq '03'?'checked':''}/>凉山南</label>
					<label><input name="direction" type="radio" class="required" size="20" value="04" ${provider.direction eq '04'?'checked':''}/>凉山北</label>
				</dd>
			</dl>
			<dl>
				<dt>省份：</dt>
				<dd>
					<input name="cityLookUp.stateId" type="hidden" class="required" size="20" value="${provider.stateId}"/>
					<input name="cityLookUp.stateName" type="text" class="required" size="20" value="${provider.city.state.stateName}" readonly/>
				</dd>
			</dl>
			<dl>
				<dt>城市：</dt>
				<dd>
					<input name="cityLookUp.cityId" type="hidden" class="required" size="20" value="${provider.cityId}"/>
					<input name="cityLookUp.cityName" type="text" class="required" size="20" value="${provider.city.cityName}" readonly/>
					<a class="btnLook" href="manage/state/getStateConnectCityList.do" lookupGroup="cityLookUp">查找带回</a>
				</dd>
			</dl>
			<dl>
				<dt>经度：</dt>
				<dd>
					<input name="longitude" type="text" class="required number" size="20" value="${provider.location.longitude}"/>
				</dd>
			</dl>
			<dl>
				<dt>纬度：</dt>
				<dd>
					<input name="latitude" type="text" class="required number" size="20" value="${provider.location.latitude}"/>
				</dd>
			</dl>
			<dl>
				<dt>街道：</dt>
				<dd>
					<input name="street" type="text" class="required" size="30" value="${provider.street}"/>
				</dd>
			</dl>
			<dl>
				<dt>邮编：</dt>
				<dd>
					<input name="postCode" type="text" class="required number" size="30" maxlength="6" value="${provider.postCode}"/>
				</dd>
			</dl>
			<dl>
				<dt>封面图：</dt>
				<dd>
					<input type="hidden" name="image1" value="${imageURl2}" id="image1"/>
					<img src="${imageURl2}" alt="封面图(830X365)" id="image1image"/>
					<a class="button" href="javascript:;"  rel="dlg_page7" onClick="uploadProvider1()" ><span>选择图片</span></a>
				</dd>
			</dl>
			<dl>
				<dt>联系电话：</dt>
				<dd>
					<input name="telephone" type="text" class="required" size="30" value="${provider.telephone}"/>
				</dd>
			</dl>
			<dl>
				<dt>缩略图：</dt>
				<dd>
					<input type="hidden" name="image2" value="${imageURl1}" id="image2"/>
					<img src="${imageURl1}" alt="缩略图(245*245)" id="image2image"/>
					<a class="button" href="javascript:;"  rel="dlg_page7" onClick="uploadProvider2()" ><span>选择图片</span></a>
				</dd>
			</dl>
			<dl>
				<dt>营业执照编号：</dt>
				<dd>
					<input name="certid1" type="text" class="required" size="50" value="${provider.certid1}"/>
				</dd>
			</dl>
			<dl>
				<dt>营业执照影像：</dt>
				<dd>
					<input type="hidden" name="image3" value="${imageURl3}" id="image3"/>
					<img src="${imageURl3}" alt="营业执照影像" id="image3image"/>
					<a class="button" href="javascript:;"  rel="dlg_page7" onClick="uploadProvider3()" ><span>选择图片</span></a>
				</dd>
			</dl>
			<dl>
				<dt>税务登记证编号：</dt>
				<dd>
					<input name="certid2" type="text" class="required" size="50" value="${provider.certid2}"/>
				</dd>
			</dl>
			<dl>
				<dt>税务登记证影像：</dt>
				<dd>
					<input type="hidden" name="image4" value="${imageURl4}" id="image4"/>
					<img src="${imageURl4}" alt="税务登记证影像" id="image4image"/>
					<a class="button" href="javascript:;"  rel="dlg_page7" onClick="uploadProvider4()" ><span>选择图片</span></a>
				</dd>
			</dl>
			<dl>
				<dt>组织机构代码证编号：</dt>
				<dd>
					<input name="certid3" type="text" class="required" size="50" value="${provider.certid3}"/>
				</dd>
			</dl>
			<dl>
				<dt>组织机构代码证影像：</dt>
				<dd>
					<input type="hidden" name="image5" value="${imageURl5}" id="image5"/>
					<img src="${imageURl5}" alt="组织机构代码证影像" id="image5image"/>
					<a class="button" href="javascript:;"  rel="dlg_page7" onClick="uploadProvider5()" ><span>选择图片</span></a>
				</dd>
			</dl>
			<dl>
				<dt>联系人：</dt>
				<dd>
					<input name="contactman" type="text" class="required" size="30" value="${provider.contactman}"/>
				</dd>
			</dl>
			<dl>
				<dt>联系人电话：</dt>
				<dd>
					<input name="phone" type="text" class="required" size="30" value="${provider.phone}"/>
				</dd>
			</dl>
			<dl>
				<dt>供应商描述：</dt>
				<dd>
					<textarea name="description" type="text" class="required" rows="4" cols="100" >${provider.description}</textarea>
				</dd>
			</dl>
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
