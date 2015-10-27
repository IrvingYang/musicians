/**
 * 
 * user
 * 
 */
var user = {
			
	getUserAddressByUserId:function(){
		var bool = false;
		$.ajax({
			url:common.getRootPath()+"/user/userAddress/getUserAddressByUserId.action",
			type:"POST",
			async: false,
			dataType:'text',
			success:function(data){
				if((data.toString())=="needlogin"){
					alert("您还未登录");
					window.location.href="/user_login.html?returnURI="+window.location.href;
					return;
				}
				else if(data.toString()=="entUser")
				{
					//企业用户无需处理地址信息
				}
				else{
					eval("var jData = "+data);
					$("#userAddress").children("figure").remove();
					$.each(jData,function(index,userAddress){
						var html = 
							'<figure class="block_select clearfix relative m_bottom_15" >'+
							'	<input type="radio" name="userAddressId" class="d_none" value="'+userAddress.userAddressId+'">'+
							'	<figcaption>'+
							'		<h5 class="color_dark fw_medium m_bottom_15 m_sm_bottom_5">'+userAddress.name+'</h5>'+
							'		<p>'+userAddress.district.city.state.stateName+userAddress.district.city.cityName+userAddress.street+'</p>'+
							'	</figcaption>'+
							'</figure>';
						$("#userAddress").prepend(html);
					});
				}
				bool = true;
			},
			error:function(data){
				
			}
		});
		return bool;
	},
	getUserAddressByUserId2:function(){
		$.ajax({
			url:common.getRootPath()+"/user/userAddress/getUserAddressByUserId.action",
			type:"POST",
			async: false,
			dataType:'text',
			success:function(data){
				if((data.toString())=="needlogin"){
					alert("您需要登录个人用户");
					window.location.href="/user_login.html?returnURI="+window.location.href;
					return;
				}
				else if(data.toString()=="entUser")
				{
					//企业用户无需处理地址信息
				}
				else{
					eval("var jData = "+data);
					$("#userAddress").children("figure").remove();
					$.each(jData,function(index,userAddress){
						var html = 
//							'<figure class="block_select clearfix relative m_bottom_15" >'+
//							'	<input type="radio" name="userAddressId" class="d_none" value="'+userAddress.userAddressId+'">'+
							'	<figcaption>'+
							'		<h5 class="color_dark fw_medium m_bottom_15 m_sm_bottom_5">'+userAddress.name+'</h5>'+
							'		<p>'+userAddress.district.city.state.stateName+userAddress.district.city.cityName+userAddress.street+
//							'		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
							'		<a class="m_left_100" href="javascript:user.deleteAddress(\''+userAddress.userAddressId+'\',\''+userAddress.userId+'\');">删除此地址</a></p>'+
							'	</figcaption>';
//							'</figure>';
						$("#userAddress").prepend(html);
					});
				}
			},
			error:function(data){
				
			}
		});
	},
	deleteAddress : function(userAddressId,userId){
		$.ajax({
			url:common.getRootPath()+"/user/userAddress/deleteUserAddress.action",
			type:"POST",
			async: false,
			data:{userAddressId:userAddressId,userId:userId},
			dataType:'text',
			success:function(data){
				if(data=="success"){
					alert("操作成功");
					window.location.reload(true);
				}
				else if(data=="failed"){
					alert("操作失败");
				}
				else{
					window.location.href="/index.html";
				}
			}
		});
	},
	addToWishList:function(obj,partnerflag){
		productId = $(obj).attr("key");
		$.ajax({
			url:common.getRootPath()+"/user/wishlist/addToWishList.action",
			type:"POST",
			async: true,
			dataType:'text',
			data:{productId:productId,partnerflag:partnerflag},
			success:function(data){
				if(data=="needLogin"){
					alert("您需要登录个人用户");
					window.location.href="/user_login.html?returnURI="+window.location.href;
					return false;
				}
				if(data=="already"){
					alert("您已经收藏该商品");
				}
				if(data=='true'){
					alert("收藏成功");
				}
			},
			error:function(){
				alert("收藏失败");
			}
		});
	},
	deleteWishList : function(obj,productId){
		
		$.ajax({
			url:common.getRootPath()+"/user/wishlist/deleteWishList.action",
			type:"POST",
			async: true,
			data:{productId:productId},
			dataType:'text',
			success:function(data){
				if(data=="true" || data==true){
					alert("操作成功！");
					$(obj).parent().parent().fadeOut();
					setTimeout(1000,function(){
						$(obj).parent().parent().remove();
					})
				}
			}
		});
	},
	getWishList : function(){
		
		$.ajax({
			url:common.getRootPath()+"/user/wishlist/getWishList.action",
			type:"POST",
			async: true,
			dataType:'text',
			success:function(data){
				if(data=="needLogin"){
					alert("您需要登录个人用户");
					window.location.href="/user_login.html?returnURI="+window.location.href;
					return false;
				}
				if(data=="entUser"){
					alert("当前为企业用户，操作被拒绝!");
					return false;
				}
				eval("var wishList = "+ data);
				$("#wishList").children().remove();
				var nowDate = new Date().getTime();
				$.each(wishList,function(index,wish){
					
					var urlHref="";
					var html = "";
					if(wish.partnerflag==0)
					{
						urlHref='product_page.html?productId='+wish.product_ext_shop.productId+'&productTypeId='+wish.product_ext_shop.product.productTypeId+'&requestType=page';
						
						html =
							'<tr>'+
							'	<td data-title="Product Image"><img'+
							'		src="'+wish.product_ext_shop.product.productimglist[2].path+'" alt=""></td>'+
							'	<td data-title="Product Name"><a href="'+urlHref+'"'+
							'		class="fw_medium d_inline_b f_size_ex_large color_dark m_bottom_5">'+wish.product_ext_shop.product.productName+'</a><br>'+
							'		<a href="'+urlHref+'" class="default_t_color">'+wish.product_ext_shop.product.product_type.typeName+'</a></td>'+
							'	<td data-title="Price">';
							
							if(wish.product_ext_shop.promoteflag==1 && nowDate-wish.product_ext_shop.promoteStartTime>0 && wish.product_ext_shop.promoteEndTime-nowDate>0){
								html += '<span class="scheme_color fw_medium f_size_large"><s>￥'+wish.product_ext_shop.originalPrice+'</s></span><span class="scheme_color fw_medium f_size_large">￥'
									+wish.product_ext_shop.promotePrice+'</span>';
							}else{
								html += '<span class="scheme_color fw_medium f_size_large">￥'+wish.product_ext_shop.originalPrice+'</span>';
							}
							
							html+=
							'</td> <td data-title="Quantity">'+
							'		<div'+
							'			class="clearfix quantity r_corners d_inline_middle f_size_medium color_dark">'+
							'			<button class="bg_tr d_block f_left" data-direction="down">-</button>'+
							'			<input type="text" name="" readonly value="1" class="f_left" key="'+wish.product_ext_shop.productId+'">'+
							'			<button class="bg_tr d_block f_left" data-direction="up">+</button>'+
							'		</div>'+
							'	</td>'+
							'	<td data-title="Action">'+
							'		<button type="button" onclick="user.wishListCart(this);" '+
							'			class="button_type_4 bg_scheme_color r_corners tr_all_hover color_light mw_0 m_bottom_10">添加到购物车'+
							'		</button> <br> <a href="javascript:;" class="color_dark" onclick="user.deleteWishList(this,\''+wish.product_ext_shop.productId+'\');"><i'+
							'			class="fa fa-times m_right_5" ></i>删除</a>'+
							'	</td>'+
							'</tr>';
						
					}
					else
					{
						urlHref='provider_product_page.html?productId='+wish.product.productId+'&productTypeId='+wish.product.productTypeId+'&requestType=page';
						
						html =
							'<tr>'+
							'	<td data-title="Product Image"><img'+
							'		src="'+wish.product.productimglist[0].path+'" alt=""></td>'+
							'	<td data-title="Product Name"><a href="'+urlHref+'"'+
							'		class="fw_medium d_inline_b f_size_ex_large color_dark m_bottom_5">'+wish.product.productName+'</a><br>'+
							'		<a href="'+urlHref+'" class="default_t_color">'+wish.product.product_type.typeName+'</a></td>'+
							'	<td data-title="Price">';
							
							html += 
							'<span class="scheme_color fw_medium f_size_large">￥'+wish.product.shopPrice+'</span>'+
							'</td> <td data-title="Quantity">'+
							'		<div'+
							'			class="clearfix quantity r_corners d_inline_middle f_size_medium color_dark">'+
							'			<button class="bg_tr d_block f_left" data-direction="down">-</button>'+
							'			<input type="text" name="" readonly value="1" class="f_left" key="'+wish.product.productId+'">'+
							'			<button class="bg_tr d_block f_left" data-direction="up">+</button>'+
							'		</div>'+
							'	</td>'+
							'	<td data-title="Action">'+
							'		<button type="button" onclick="user.wishListCart(this);" '+
							'			class="button_type_4 bg_scheme_color r_corners tr_all_hover color_light mw_0 m_bottom_10">添加到购物车'+
							'		</button> <br> <a href="javascript:;" class="color_dark" onclick="user.deleteWishList(this,\''+wish.product.productId+'\');"><i'+
							'			class="fa fa-times m_right_5" ></i>删除</a>'+
							'	</td>'+
							'</tr>';
						
					}
					$("#wishList").append(html);
				});
			}
		});
	},
	getCheckOutSideWish : function(maxCount){
		$.ajax({
			url:common.getRootPath()+"/user/wishlist/getWishList.action",
			type:"POST",
			async: true,
			dataType:'text',
			data:{maxCount:maxCount},
			success:function(data){
				if(data=="needLogin"){
					return false;
				}
				if(data=="entUser"){
					return false;
				}
				eval("var wishList = "+ data);
				
				$("#wish").children("div").remove();
				$.each(wishList,function(inndex,wish){
					
					$("#wish").prepend(
						'<div class="clearfix m_bottom_15 relative cw_product">'+
						'	<img src="'+wish.product_ext_shop.product.productimglist[0].path+'" alt=""'+
						'		class="f_left m_right_15 m_sm_bottom_10 f_sm_none f_xs_left m_xs_bottom_0">'+
						'	<a href="#" class="color_dark d_block bt_link">'+wish.product_ext_shop.product.productName+'</a>'+
//						'	<button type="button" onclick="alert(\'delete\');"'+
//						'		class="f_size_medium f_right color_dark bg_tr tr_all_hover close_fieldset">'+
//						'		<i class="fa fa-times lh_inherit"></i>'+
//						'	</button>'+
						'</div>');
					
				});
			}
		});
	},
	getwishListCount : function (){
		$.ajax({
			url:common.getRootPath()+"/user/wishlist/getWishListCount.action",
			data:{},
			dataType:'text',
			success:function(data){
				if(data!="needLogin" && data!="entUser"){
					$("#wishCount").text(data);
				}
			}
		});
	},
	wishListCart : function(obj){
		var countObject = $(obj).parent().siblings("td:eq(3)").children().children("input");
		var cartCount = countObject.val();
		var productId = countObject.attr("key");
		var url=common.getRootPath()+"/eshop/product/operationCart.action";
		$.ajax({
			url:url,
			type:"POST",
			async: false,
			data:{productId:productId,action:"add",addcount:cartCount},
			dataType:'text',
			success:function(data){
				if(data=="operasuccess"){
					common.operationCart('getCart','');
					alert("加入成功");
				}
			}
		});
	},
	logout:function(){
		$.ajax({
			url:"user/user/logout.action",
			type:"POST",
			async: false,
			dataType:'text',
			success:function(data){
				window.location.reload(true);
			}
		});
	}
};

$(document).ready(function(){
	$("#clogin").click(function(){
		var username = $("#username").val();
		if(username==""){
			$("#username").parent("li").children("span").remove();
			$("#username").parent("li").append("<span style='color:red'>请输入用户名</span>");
			return false;
		}
		var password = $("#password").val();
		if(password==""){
			$("#password").parent("li").children("span").remove();
			$("#password").parent("li").append("<span style='color:red'>请输入密码</span>");
			return false;
		}
		$("#userLogin").ajaxSubmit(function(data){
			if(data==null || data=="null" || data =="uperror"){
				$("#userLogin").attr("action","");
				alert("用户名或者密码错误");
				return false;
			}else{
				window.location.reload(true); 
				return false;
			}
		});
		return false;
	});
	$("#userAddress").children("figure").click(function(){
		$(this).children("input").attr("checked","true");
	});
	$("#transport").children("figure").click(function(){
		$(this).children("input").attr("checked","true");
	});
	$("#addaddrbtn").click(function(){
		if($("#name").val()=='00'){
			alert("请输入收货人姓名");
			return false;
		}
		if($("#province").val()=='00'){
			alert("请选择省份");
			return false;
		}
		if($("#city").val()=='00'){
			alert("请选择城市");
			return false;
		}
		if($("#district").val()=='00'){
			alert("请选择区(县)");
			return false;
		}
		if($("#street").val()==''){
			alert("请输入收货地址详细");
			return false;
		}
		var postCode = $("#postCode").val();
		if(isNaN(postCode) || postCode.length!=6){
			alert("请输入正确的邮政编码");
			return false;
		}
		var phone = $("#telephone").val();
		if(phone=="" || phone.length<8 || isNaN(phone)){
			alert("请输入正确的电话号码");
			return false;
		}
		$("#addAddressForm").ajaxSubmit(function(data){
			if(data=="needLogin"){
				alert("您需要登录个人用户");
				window.location.href="/user_login.html?returnURI="+window.location.href;
				return false;
			}
			else if(data=="entUser"){
				alert("当前为企业用户，操作被拒绝!");
				return false;
			}else{
				alert("操作成功");
				window.location.reload(true);
			}
		});
	});
});