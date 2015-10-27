var order={
		
	pageCartList:function(){
		var type = common.getParameter("checkouttype");
		var nowDate = new Date();
		if(type == '01'){//普通商城交易
			var url=common.getRootPath()+"/eshop/product/operationCart.action";
			$.ajax({
				url:url,
				type:"POST",
				async: false,
				data:{productId:null,action:'getCart',addcount:null},
				dataType:'json',
				success:function(data){
					$("#prices").prevAll().remove();
					$.each(data.product,function(index,object){
						html = '<tr>'+
								'		<!--Product name and image-->'+
								'	<td data-title="Product Image &amp; name" class="t_md_align_c">'+
								'		<img src="'+object.product.productimglist[0].path+'" alt=""'+
								'		class="m_md_bottom_5 d_xs_block d_xs_centered"> <a'+
								'		href="#" class="d_inline_b m_left_5 color_dark">'+object.product.productName+'</a>'+
								'	</td>'+
								'	<!--product key-->'+
								'	<td data-title="SKU">'+object.product.productId+'</td>'+
								'	<!--product price-->';
								if(object.cartType==0)
								{
							        if(object.promoteflag == 1){
							        	html+='	<td data-title="Price"><s>￥'+object.originalPrice+'</s>'+
										'		<p class="f_size_large color_dark">￥'+object.promotePrice+'</p></td>';
							        }else{
							        	html+='<td data-title="Price"><p class="f_size_large color_dark">￥'+object.originalPrice+'</p></td>';
							        }
								}
								else if(object.cartType==1)
								{
						        	html+='<td data-title="Price"><p class="f_size_large color_dark">￥'+object.shopPrice+'</p></td>';
								}

								html+='	<td data-title="Quantity">'+
								'		<div'+
								'			class="clearfix quantity r_corners d_inline_middle f_size_medium color_dark m_bottom_10">'+
								'			<button type="button" class="bg_tr d_block f_left add" data-direction="down"  onclick="order.changeProductCount(\'cut\', this, \''+object.product.productId+'\')" >-</button>'+
								'			<input type="text" name="" readonly value="'+object.productCount+'" class="f_left" oninput="javascript:alert();" />'+
								'			<button type="button" class="bg_tr d_block f_left cut" data-direction="up" onclick="order.changeProductCount(\'add\', this,\''+object.product.productId+'\')">+</button>'+
								'		</div>'+
								'		<div>'+
								'			<a href="javascript:order.deleteCart(this,\''+object.product.productId+'\');" class="color_dark"><i'+
								'				class="fa fa-times f_size_medium m_right_5"></i>删除</a><br>'+
								'		</div>'+
								'	</td>'+
								'	<!--subtotal-->'+
								'	<td data-title="Subtotal">';
								if(object.cartType==0)
								{
									 if(object.promoteflag == 1)
									 {
								        	html+='<p class="f_size_large fw_medium scheme_color">￥'+(object.productCount * object.promotePrice).toFixed(2)+'</p>';
								     }else
								     {
								        	html+='<p class="f_size_large fw_medium scheme_color">￥'+(object.productCount * object.originalPrice).toFixed(2)+'</p>';
								     }
								}
								else if(object.cartType==1)
								{
						        	html+='<p class="f_size_large fw_medium scheme_color">￥'+(object.productCount * object.shopPrice).toFixed(2)+'</p>';
								}
								html+='</td></tr>';
						$("#cartTable").prepend(html);
					});
					$(".totalPrice").html('￥'+data.totalPrice);
					
					$(".add").click(function(){
			   			var productId = $(this).parent("div").children("input").attr("key");
			   		});
			   		$(".cut").click("click",function(){
			   			var productId = $(this).parent("div").children("input").attr("key");
			   		});
					
				},
				error:function(){
//					products.operationCart('getCart',null,null);
				}
			});
		}else if(type == '02'){//团购交易
			var count = common.getParameter('count');
			var url = common.getRootPath()+"/eshop/groupBuyProduct/getgroupBuyProductByType.action";
			$.ajax({				
				url:url,
				type:"POST",
				async: false,
				data:{type:3, typeId:common.getParameter('groupbuyid'),maxcount:1},
				dataType:'json',
				success:function(srcdata){
					var object = srcdata[0];
					$("#prices").prevAll().remove();
						html = '<tr>'+
								'		<!--Product name and image-->'+
								'	<td data-title="Product Image &amp; name" class="t_md_align_c">'+
								'		<img src="'+object.product.productimglist[2].path+'" alt=""'+
								'		class="m_md_bottom_5 d_xs_block d_xs_centered"> <a'+
								'		href="#" class="d_inline_b m_left_5 color_dark">'+object.product.productName+'</a>'+
								'	</td>'+
								'	<!--product key-->'+
								'	<td data-title="SKU">'+object.productId+'</td>'+
								'	<!--product price-->'+
								'	<td data-title="Price"><s>￥'+object.product.shopPrice+'</s>'+
								'		<p class="f_size_large color_dark">￥'+object.groupBuyPrice+'</p></td>'+
								'	<!--quanity-->'+
								'	<td data-title="Quantity">'+
								'		<div'+
								'			class="clearfix quantity r_corners d_inline_middle f_size_medium color_dark m_bottom_10">'+
								'			<button type="button" class="bg_tr d_block f_left add" data-direction="down"   >-</button>'+
								'			<input type="text" name="" readonly value="'+count+'" class="f_left" oninput="javascript:alert();" />'+
								'			<button type="button" class="bg_tr d_block f_left cut" data-direction="up" >+</button>'+
								'		</div>'+
								'		<div>'+
								'			<a href="javascript:order.deleteCart(this,\''+object.productId+'\');" class="color_dark"><i'+
								'				class="fa fa-times f_size_medium m_right_5"></i>删除</a><br>'+
								'		</div>'+
								'	</td>'+
								'	<!--subtotal-->'+
								'	<td data-title="Subtotal">'+
								'		<p class="f_size_large fw_medium scheme_color">￥'+(count * object.groupBuyPrice)+'</p>'+
								'	</td>'+
								'</tr>';
						$("#cartTable").prepend(html);


					$(".totalPrice").html('￥'+count * object.groupBuyPrice);
					
					$(".add").click(function(){
			   			var productId = $(this).parent("div").children("input").attr("key");
			   		});
			   		$(".cut").click("click",function(){
			   			var productId = $(this).parent("div").children("input").attr("key");
			   		});
			   		
			   		
				},
				error:function(){
					 alert("获取团购数据失败！");
				}
			});
			
		}else if(type == '03'){//大宗交易交易
			var count = common.getParameter('count');
			var url = common.getRootPath()+"/eshop/bigDealProduct/getbigDealProductByType.action";
			$.ajax({
				url:url,
				type:"POST",
				async: false,
				data:{type:3, typeId:common.getParameter('bigdealid'),maxcount:1},
				dataType:'json',
				success:function(srcdata){
					var object = srcdata[0];
					$("#prices").prevAll().remove();
						html = '<tr>'+
								'		<!--Product name and image-->'+
								'	<td data-title="Product Image &amp; name" class="t_md_align_c">'+
								'		<img src="'+object.product.productimglist[2].path+'" alt=""'+
								'		class="m_md_bottom_5 d_xs_block d_xs_centered"> <a'+
								'		href="#" class="d_inline_b m_left_5 color_dark">'+object.product.productName+'</a>'+
								'	</td>'+
								'	<!--product key-->'+
								'	<td data-title="SKU">'+object.productId+'</td>'+
								'	<!--product price-->'+
								'	<td data-title="Price"><s>￥'+object.product.shopPrice+'</s>'+
								'		<p class="f_size_large color_dark">￥'+object.referencePrice+'</p></td>'+
								'	<!--quanity-->'+
								'	<td data-title="Quantity">'+
								'		<div'+
								'			class="clearfix quantity r_corners d_inline_middle f_size_medium color_dark m_bottom_10">'+
								'			<button type="button" class="bg_tr d_block f_left add" data-direction="down"  >-</button>'+ // onclick="order.changeProductCount(\'cut\', this, \''+object.productId+'\')"
								'			<input type="text" name="" readonly value="'+count+'" class="f_left" oninput="javascript:alert();" />'+
								'			<button type="button" class="bg_tr d_block f_left cut" data-direction="up" >+</button>'+ //onclick="order.changeProductCount(\'add\', this,\''+object.productId+'\')"
								'		</div>'+
								'		<div>'+
								'			<a href="javascript:order.deleteCart(this,\''+object.productId+'\');" class="color_dark"><i'+
								'				class="fa fa-times f_size_medium m_right_5"></i>删除</a><br>'+
								'		</div>'+
								'	</td>'+
								'	<!--subtotal-->'+
								'	<td data-title="Subtotal">'+
								'		<p class="f_size_large fw_medium scheme_color">￥'+(count * object.referencePrice)+'</p>'+
								'	</td>'+
								'</tr>';
						$("#cartTable").prepend(html);


					$(".totalPrice").html('￥'+count * object.referencePrice);
					
					$(".add").click(function(){
			   			var productId = $(this).parent("div").children("input").attr("key");
			   		});
			   		$(".cut").click("click",function(){
			   			var productId = $(this).parent("div").children("input").attr("key");
			   		});
				},
				error:function(){
                    alert("获取大宗交易数据失败！");
				}
			});
		}
		else if(type == '04'){//商城商品立即购买
			var count = common.getParameter('count');
			var url = common.getRootPath()+"/eshop/shopProduct/getShopProductDetail.action";

			$.ajax({
				url:url,
				type:"POST",
				async: false,
				data:{productId:common.getParameter('productId')},
				dataType:'json',
				success:function(object){
					$("#prices").prevAll().remove();
						html = '<tr>'+
								'		<!--Product name and image-->'+
								'	<td data-title="Product Image &amp; name" class="t_md_align_c">'+
								'		<img src="'+object.product.productimglist[2].path+'" alt=""'+
								'		class="m_md_bottom_5 d_xs_block d_xs_centered"> <a'+
								'		href="#" class="d_inline_b m_left_5 color_dark">'+object.product.productName+'</a>'+
								'	</td>'+
								'	<!--product key-->'+
								'	<td data-title="SKU">'+object.productId+'</td>'+
								'	<!--product price-->';
								if(object.promoteflag==1){
									html+='	<td data-title="Price"><s>￥'+object.originalPrice+'</s>'+
									'	<p class="f_size_large color_dark">￥'+object.promotePrice+'</p></td>';
								}
								else{
									html+='<td data-title="Price">'+
									'	<p class="f_size_large color_dark">￥'+object.originalPrice+'</p></td>';
								}
								html+='	<!--quanity-->'+
								'	<td data-title="Quantity">'+
								'		<div'+
								'			class="clearfix quantity r_corners d_inline_middle f_size_medium color_dark m_bottom_10">'+
								'			<button type="button" class="bg_tr d_block f_left add" data-direction="down"  >-</button>'+ // onclick="order.changeProductCount(\'cut\', this, \''+object.productId+'\')"
								'			<input type="text" name="" readonly value="'+count+'" class="f_left" oninput="javascript:alert();" />'+
								'			<button type="button" class="bg_tr d_block f_left cut" data-direction="up" >+</button>'+ //onclick="order.changeProductCount(\'add\', this,\''+object.productId+'\')"
								'		</div>'+
								'		<div>'+
								'			<a href="javascript:order.deleteCart(this,\''+object.productId+'\');" class="color_dark"><i'+
								'				class="fa fa-times f_size_medium m_right_5"></i>删除</a><br>'+
								'		</div>'+
								'	</td>'+
								'	<!--subtotal-->'+
								'	<td data-title="Subtotal">';
								if(object.promoteflag==1){
									html+='	<p class="f_size_large fw_medium scheme_color">￥'+(count * object.promotePrice)+'</p>';
								}
								else{
									html+='	<p class="f_size_large fw_medium scheme_color">￥'+(count * object.originalPrice)+'</p>';
								}
								
								html+='	</td>'+
								'</tr>';
						$("#cartTable").prepend(html);
						
					if(object.promoteflag==1){
						$(".totalPrice").html('￥'+count * object.promotePrice);
					}
					else{
						$(".totalPrice").html('￥'+count * object.originalPrice);
					}
					
					$(".add").click(function(){
			   			var productId = $(this).parent("div").children("input").attr("key");
			   		});
			   		$(".cut").click("click",function(){
			   			var productId = $(this).parent("div").children("input").attr("key");
			   		});
				},
				error:function(){
				}
			});
		}
		else if(type == '05'){//商城商品立即购买
			var count = common.getParameter('count');
			var url = common.getRootPath()+"/eshop/product/getProductDetailedById.action";

			$.ajax({
				url:url,
				type:"POST",
				async: false,
				data:{productId:common.getParameter('productId')},
				dataType:'json',
				success:function(object){
					$("#prices").prevAll().remove();
						html = '<tr>'+
								'		<!--Product name and image-->'+
								'	<td data-title="Product Image &amp; name" class="t_md_align_c">'+
								'		<img src="'+object.productimglist[2].path+'" alt=""'+
								'		class="m_md_bottom_5 d_xs_block d_xs_centered"> <a'+
								'		href="#" class="d_inline_b m_left_5 color_dark">'+object.productName+'</a>'+
								'	</td>'+
								'	<!--product key-->'+
								'	<td data-title="SKU">'+object.productId+'</td>'+
								'	<!--product price-->';
								html+='<td data-title="Price">'+
									'	<p class="f_size_large color_dark">￥'+object.shopPrice+'</p></td>';
								html+='	<!--quanity-->'+
								'	<td data-title="Quantity">'+
								'		<div'+
								'			class="clearfix quantity r_corners d_inline_middle f_size_medium color_dark m_bottom_10">'+
								'			<button type="button" class="bg_tr d_block f_left add" data-direction="down"  >-</button>'+ // onclick="order.changeProductCount(\'cut\', this, \''+object.productId+'\')"
								'			<input type="text" name="" readonly value="'+count+'" class="f_left" oninput="javascript:alert();" />'+
								'			<button type="button" class="bg_tr d_block f_left cut" data-direction="up" >+</button>'+ //onclick="order.changeProductCount(\'add\', this,\''+object.productId+'\')"
								'		</div>'+
								'		<div>'+
								'			<a href="javascript:order.deleteCart(this,\''+object.productId+'\');" class="color_dark"><i'+
								'				class="fa fa-times f_size_medium m_right_5"></i>删除</a><br>'+
								'		</div>'+
								'	</td>'+
								'	<!--subtotal-->'+
								'	<td data-title="Subtotal">';
								html+='	<p class="f_size_large fw_medium scheme_color">￥'+(count * object.shopPrice)+'</p>';
								html+='	</td>'+
								'</tr>';
						$("#cartTable").prepend(html);
						
					if(object.promoteflag==1){
						$(".totalPrice").html('￥'+count * object.promotePrice);
					}
					else{
						$(".totalPrice").html('￥'+count * object.originalPrice);
					}
					
					$(".add").click(function(){
			   			var productId = $(this).parent("div").children("input").attr("key");
			   		});
			   		$(".cut").click("click",function(){
			   			var productId = $(this).parent("div").children("input").attr("key");
			   		});
				},
				error:function(){
					alert(123);
				}
			});
		}
	},

	newProduct : function(){
		
	},
	specialRecommend : function(){
		
	},
	operOrder : function(action,orderId,orderType){
		if(action=='cancel'){
			$.post("order/orderList/cancelOrder.action",{orderId:orderId,orderType:orderType},function(data){
				if(data=="success"){
					alert("操作成功");
					window.location.reload(true);
				}
			});
		}
		else if(action=='payment'){
			$.post("alipay/pay.action",{orderId:orderId,orderType:orderType},function(data){
				$("body").append(data);
				$(".alipayForm").remove();
				if(data=="success"){
					alert("操作成功");
					window.location.reload(true);
				}
			});
		}
		else if(action=='applyRefund'){
			$.post("order/orderList/applyRefund.action",{orderId:orderId,orderType:orderType},function(data){
				if(data=="success"){
					alert("操作成功");
					window.location.reload(true);
				}
			});
		}
		
	},
	payMethod : function(){
		var url = common.getRootPath()+"/order/payment_Method/paymentMethods.action";
		$.get(url,function(data){
			$.each(data,function(index,pay){
				$("#payMethod").append(
						'<figure class="block_select clearfix relative m_bottom_15">'+
						'	<input type="radio" name="paymentway" class="d_none" value="'+pay.paymentway+'" checked="checked">'+
						'	<img src="'+pay.imgpath+'" alt=""'+
						'		class="f_left m_right_20 f_mxs_none m_mxs_bottom_10">'+
						'	<figcaption>'+
						'		<h5 class="color_dark fw_medium m_bottom_15 m_sm_bottom_5">'+pay.instname+'</h5>'+
						'		<p>'+pay.instname+'</p>'+
						'	</figcaption>'+
						'</figure><hr class="m_bottom_20">');
			});
		});
	},
	changeProductCount : function(type,objectDom, productId){
		var value = $(objectDom).parent("div").children("input").val();
		if(type=="cut"){
			value = (value==1?1:(value-1));
		}else{
			value++;
		}
		var url=common.getRootPath()+"/eshop/product/operationCart.action";
		$.post(url,
		{productId:productId,action:'update',addcount:value},function(data){
			order.pageCartList();
		});
	},
	
	deleteCart: function(object,productId){

		$.ajax({
			url:common.getRootPath()+"/eshop/product/operationCart.action",
			type:"POST",
			async: false,
			data:{productId:productId,action:'remove',addcount:''},
			dataType:'text',
			success:function(data){
				order.pageCartList();
			}
		});
	},
	getOrderList : function(orderType){
		
		if(orderType==null || orderType==""){
			window.location.href="index.html";
		}
		var type = parseInt(orderType);
		$.ajax({
			url:common.getRootPath()+"/order/orderList/getOrderListByUserId.action",
			type:"POST",
			async: false,
			data:{orderType:type,type:0},
			dataType:'text',
			success:function(data){
				if(type==1 || type==2)
				{
					if(data=='needLogin'){
						alert("您需要登录个人账户");
						window.location.href="/user_login.html?returnURI="+window.location.href;
						return;
					}
				}
				else if(type==3)
				{
					if(data=='needLoginEnt'){
						alert("您需要登陆企业用户");
						window.location.href="/user_login.html?returnURI="+window.location.href;
						return;
					}
				}
				eval("var jData = "+data);
				$("#orderList").children().remove();
				$.each(jData,function(index,order){
					var status = order.status;
					var statusStr = '';
					var oper="";
					switch(status){
					
						case '01':statusStr='已下单'; 
						if(order.payofflineflag==0)
						{
							oper='<a href="javascript:order.operOrder(\'payment\',\''+order.orderId+'\','+order.orderType+');">付款</a> &nbsp;&nbsp;&nbsp; <a href="javascript:order.operOrder(\'cancel\',\''+order.orderId+'\','+order.orderType+');">取消订单</a>';
						}
						break;
						case '02':statusStr='已撤单'; 
						break;
						case '03':statusStr='已付款'; 
						break;
						case '04':statusStr='等待开团'; 
						break;
						case '05':statusStr='开团成功'; 
						break;
						case '06':statusStr='等待配送'; 
						break;
						case '07':statusStr='配送中'; 
						break;
						case '08':statusStr='已完成'; 
						oper='<a href="javascript:order.operOrder(\'applyRefund\',\''+order.orderId+'\','+order.orderType+');">申请退货</a>';
						break;
						case '09':statusStr='退货处理中'; 
						break;
						case '10':statusStr='同意退货'; 
						break;
						case '11':statusStr='不同意退货'; 
						break;
						case '12':statusStr='等待退款'; 
						break;
						case '13':statusStr='退款完成'; 
						break;
					
					}

					var date = new Date(order.createTime);
					var year = date.getFullYear();
					
					var month = date.getMonth()+1;
					var day = date.getDate();
					var hours = date.getHours();
					var minutes = date.getMinutes();
					var seconds = date.getSeconds();
					if(orderType==1){
						
						
						$("#orderList").append(
							'<tr>'+
							'<td data-title="Order Number">'+
							'<a href="order_detail.html?orderId='+order.orderId+
							'"	class="color_dark">'+order.orderId+'</a></td>'+
							'<td data-title="Order Date">'+(year+'年'+month+'月'+day+'日  '+hours+':'+minutes+':'+seconds)+'</td>'+
							'<td data-title="Order Status">'+statusStr+'</td>'+
							'<td data-title="Total"><span'+
							'	class="f_size_large fw_medium scheme_color">￥'+order.totalamt+'</span></td>'+
							'	<td data-title="Order Status" width="280">'+oper+'</td>'+
							'</tr>'
						);
					}
					else if(orderType==2 || orderType==3){
						var type = (orderType==2?"团购":"大宗交易");
						var orderProName = "";
						if(orderType==2){
							orderProName=order.order_detail[0].groupBuy.product.productName;
						}else{
							orderProName=order.order_detail[0].bigDeal.product.productName;
						}
						//，当订单类型为大宗交易的时候，不允许采取任何操作
						if(orderType==3){
							oper="";
						}
						$("#group").append(
							'<tr>'+
							'	<td data-title="Order Number"><a href="javascript:;"'+
							'		class="color_dark">'+order.orderId+'</a></td>'+
							'	<td data-title="Order Type">'+type+'</td>'+
							'	<td data-title="Product Name">'+orderProName+'</td>'+
							'	<td data-title="Order Date">'+(year+'年'+month+'月'+day+'日  '+hours+':'+minutes+':'+seconds)+'</td>'+
							'	<td data-title="Order Status">'+statusStr+'</td>'+
							'	<td data-title="Total"><span'+
							'		class="f_size_large fw_medium scheme_color">￥'+order.totalamt+'</span></td>'+
							'	<td data-title="Order Status" width="280">'+oper+'</td>'+
							'</tr>'
						);
					}
				});
			}
		});
	},
	getOrderDetail : function(){
		
		var orderId = common.getParameter("orderId");
		if(orderId==''){
			window.location.href=common.getRootPath();
			return ;
		}
		$.ajax({
			url:common.getRootPath()+"/order/orderList/getOrderDetail.action",
			type:"POST",
			async: false,
			data:{orderId:orderId},
			dataType:'text',
			success:function(data){
				if(data=='needLogin'){
					alert("您还没登陆");
					return;
				}
				eval("var rData = "+data);
				var jData = rData[0];
				var orderType = '';
				switch(jData.orderType){
				case 1:orderType='商城';break;
				case 2:orderType='团购';break;
				case 3:orderType='大宗交易';break;
				}
				var statusStr = "";
				$("#orderType").text(orderType);
				$("#orderNo").text(jData.orderId);
				$("#createTime").text(jData.createTime);
				switch(jData.status){
					
					case '01':statusStr='已下单'; 
					break;
					case '02':statusStr='已撤单'; 
					break;
					case '03':statusStr='已付款'; 
					break;
					case '04':statusStr='等待开团'; 
					break;
					case '05':statusStr='开团成功'; 
					break;
					case '06':statusStr='等待配送'; 
					break;
					case '07':statusStr='配送中'; 
					break;
					case '08':statusStr='已完成'; 
					break;
					case '09':statusStr='退货处理中'; 
					break;
					case '10':statusStr='同意退货'; 
					break;
					case '11':statusStr='不同意退货'; 
					break;
					case '12':statusStr='等待退款'; 
					break;
					case '13':statusStr='退款完成'; 
					break;
				
				}
				$("#orderStatus").text(statusStr);
				
				var date = new Date(jData.lastUpdateTime);
				var year = date.getFullYear();
				
				var month = date.getMonth()+1;
				var day = date.getDate();
				var hours = date.getHours();
				var minutes = date.getMinutes();
				var seconds = date.getSeconds();
				
				$("#updateTime").text(year+'年'+month+'月'+day+'日  '+hours+':'+minutes+':'+seconds);
				if(jData.express!=null)
				{
					$("#express").text(jData.express.vendorname);
					$("#expressnumber").text(jData.expressnumber==''?"暂无信息":jData.expressnumber);
				}
				$("#remark").text(jData.remark);
				$("#totalPrices").text('￥'+jData.totalamt);
				$("#totalPrice1").text('￥'+jData.totalamt);
				
				var userAddress = jData.userAddress;
				
				$("#deliveryname").text(userAddress.name);
				$("#street").text(userAddress.street);
				$("#postCode").text(userAddress.postCode);
				$("#stateName").text(userAddress.district.city.state.stateName);
				$("#cityName").text(userAddress.district.city.cityName);
				$("#telephone").text(userAddress.telephone);

				if(jData.payofflineflag==0){
					$.ajax({
						url:common.getRootPath()+"/order/payment_Method/getPaymentDetail.action",
						type:"POST",
						async: false,
						data:{payment:jData.paymentway},
						dataType:'json',
						success:function(data){
							$("#payments").text(data.instname);
						}
					});
				}
				else{
					$("#payments").text("货到付款");
				}
				
				
				var order_details = jData.order_detail;
				
				$.each(order_details,function(index,orderDetail){
					
					$("#orderProduct").prepend(
						'<tr>'+
						'	<td data-title="SKU">'+orderDetail.product.productId+'</td>'+
						'	<td data-title="Product Name"><a href="#"'+
						'		class="color_dark d_inline_b m_bottom_5">'+orderDetail.product.productName+'</a><br>'+
						'	</td>'+
						'	<td data-title="Product Status">销售中</td>'+
						'	<td data-title="Price">￥'+orderDetail.price+'</td>'+
						'	<td data-title="Qty">'+orderDetail.quantity+'</td>'+
						'	<td data-title="Total"><p'+
						'			class="color_dark f_size_large">￥'+orderDetail.totalamt+'</p></td>'+
						'</tr>');
					
				});
				//得到历史订单
				$.ajax({
					url:common.getRootPath()+"/order/orderList/getOrderListByUserId.action",
					type:"POST",
					async: false,
					data:{orderId:orderId,type:1,orderType:jData.orderType},
					dataType:'json',
					success:function(data){
						$("#orderLists").children().remove();
						$.each(data,function(index,order){
							
							var date = new Date(order.createTime);
							var year = date.getFullYear();
							
							var month = date.getMonth()+1;
							var day = date.getDate();
							var hours = date.getHours();
							var minutes = date.getMinutes();
							var seconds = date.getSeconds();
							
							var orderType1 = '';
							switch(order.orderType){
							case 1:orderType1='商城';break;
							case 2:orderType1='团购';break;
							case 3:orderType1='大宗交易';break;
							}
							
							$("#orderLists").append(
								'<tr>'+
								'<td data-title="Order Number">'+
								'<a href="order_detail.html?orderId='+order.orderId+
								'"	class="color_dark">'+order.orderId+'</a></td>'+
								'<td data-title="Order Status">'+orderType1+'</td>'+
								'<td data-title="Order Date">'+(year+'年'+month+'月'+day+'日  '+hours+':'+minutes+':'+seconds)+'</td>'+
								'<td data-title="Order Status" ><span'+
								'	class="f_size_large fw_medium scheme_color">￥'+statusStr+'</span></td>'+
								'<td data-title="Total">'+order.totalamt+'</td>'+
								'</tr>'
							);
						});
					}
				});
				
//				$("#dtotalPrice")
			}
		});
		
	}
};
$(function(){
	
	$("#orderForm").submit(function(){
		var bool = true;
		$.ajax({
			url:'user/user/getLoginUser.action',
			type:'POST',
			async: false,
			dataType:'text',
			success:function(tdata){
				if(tdata=='nologin'){
					bool = false;
					alert("您还未登录！");
					window.location.href="/user_login.html?returnURI="+window.location.href;
					return false;
				}
			}
		
		});
		var userAddressId = document.getElementsByName("userAddressId");
		var i=0;
		var checkouttype = common.getParameter("checkouttype");
		if(checkouttype=='01' || checkouttype == '02' || checkouttype == '04' || checkouttype == '05')
		{
			for(var j=0;j<userAddressId.length;j++){
				if(userAddressId[j].checked==false){
					i++;
				}
			}
			if(i==userAddressId.length){
				alert("请选择地址");
				return false;
			}
		}
		var url=common.getRootPath()+"/eshop/product/operationCart.action";
		if($("#orderTypeInput").val()=='01'){
			$.ajax({
				url:url,
				type:"POST",
				async: false,
				data:{action:"getCart"},
				dataType:'json',
				success:function(data){
					if(data.totalPrice==0){
						bool = false;
						alert("您的购物车无任何商品");
					}
				}
			});
		}
		return bool;
	});
});
