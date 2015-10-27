<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->


<div class="pageContent">

	<form method="post" action="manage/useraddress/updateUserAddress.do" class="pageForm required-validate" novalidate="novalidate" name="myproducForm" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="56">
			<input type="hidden" value="${userAddress.userId}" name="userId"/>
			<input type="hidden" value="${userAddress.userAddressId}" name="userAddressId"/>
			<input name="validflag" type="hidden"  value="1"/>
			<fieldset>
			<legend>个人用户收货地址编辑</legend>
			<dl>
				<dt>用户名：</dt>
				<dd>
					<input type="text" name="userName" readonly value="${userAddress.user_Ext_Personal.user.userName}" size="30"/>
				</dd>
			</dl>
			<dl>
				<dt>省份：</dt>
				<dd>
					<input name="useraddress.stateId" type="hidden" class="required" size="20" value="${userAddress.district.city.state.stateId}"/>
					<input name="useraddress.stateName" type="text" class="required" size="20" value="${userAddress.district.city.state.stateName}" readonly/>
				</dd>
			</dl>
			<dl>
				<dt>城市：</dt>
				<dd>
					<input name="useraddress.cityId" type="hidden" id="cityid" class="required" size="20" value="${userAddress.district.city.cityId}" oldValue="${userAddress.district.city.cityId}" />
					<input name="useraddress.cityName" type="text" id="cityname" class="required" size="20" value="${userAddress.district.city.cityName}" readonly/>
					<a class="btnLook" href="manage/state/getStateConnectCityList.do" lookupGroup="useraddress">查找城市</a>
				</dd>
			</dl>
			<dl>
				<dt>区（县）：</dt>
				<dd>
					<input name="useraddress.districtId" type="hidden" class="required" size="20" value="${userAddress.districtId}" id="districtId"/>
					<input name="useraddress.districtName" type="text" class="required" size="20" value="${userAddress.district.districtName}" readonly/>
					<a class="btnLook" href="manage/district/lookDistrict.do?cityId=${userAddress.district.city.cityId}" id="districtIdhref" lookupGroup="useraddress">查找城市</a>
				</dd>
			</dl>
			<script type="text/javascript">
			
			setInterval(function(){
				var oldValue = $("#cityid").attr("oldValue");
				var value = $("#cityid").val();
				if(value!=oldValue){
					$("#cityid").attr("oldValue",value);
					$("#districtIdhref").attr("href","manage/district/lookDistrict.do?cityId="+value);
				}
				
			}, 500);
				
			</script>
			<dl>
				<dt>街道地址：</dt>
				<dd>
					<input type="text" size="50" class="required" name="street" value="${userAddress.street}"/>
				</dd>
			</dl>
			<dl>
				<dt>邮编：</dt>
				<dd>
					<input type="text" name="postCode" value="${userAddress.postCode}" size="30"/>
				</dd>
			</dl>
			<dl>
				<dt>联系电话：</dt>
				<dd>
					<input name="telephone" type="text" class="required number" size="20" value="${userAddress.telephone}"/>
				</dd>
			</dl>
			<dl>
				<dt>联系人姓名：</dt>
				<dd>
					<input name="name" type="text" class="required" size="20" value="${userAddress.name}" />
				</dd>
			</dl>
			<dl>
				<dt>是否默认：</dt>
				<dd>
					<label><input name="defaultflag" type="radio" class="required" size="20" value="0" ${userAddress.defaultflag eq 0?'checked':''} />不是</label>
					<label><input name="defaultflag" type="radio" class="required" size="20" value="1" ${userAddress.defaultflag eq 1?'checked':''} />是</label>
				</dd>
			</dl>
			<dl>
				<dt>送货时间</dt>
				<dd>
					<select name="deliveryschedule" size="6">
						<option value="01" ${userAddress.deliveryschedule eq'01'?'selected':'' }>周一至周五 09:00-18:00</option>
						<option value="02" ${userAddress.deliveryschedule eq'02'?'selected':'' }>周一至周五 18:00-21:00</option>
						<option value="03" ${userAddress.deliveryschedule eq'03'?'selected':'' }>周六至周日 09:00-21:00</option>
					</select>
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
	
</div>
