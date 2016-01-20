<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="modal fade rent" id="rentModal" tabindex="-1" role="dialog" aria-labelledby="myRentModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h3 class="modal-title" id="myRentModalLabel">租赁</h3>
			</div>
			<div class="modal-body">
				<div class="row name">
					<label class="col-md-2 control-label" id="yajin">租赁物品:</label> <label class="col-md-2" id="pname">${shop.product.productName}</label>
				</div>
				<div class="row table">
					<table class="table table-bordered" id="records_table" border='1'>

					</table>
				</div>

				<div class="row">

					<label class="col-md-2 control-label" id="yajin">租赁时间:</label>
					<div class="btn-group" data-toggle="buttons" id="period_selection"></div>
				</div>

				<!-- <div class="row">
							<table id="table-pagination" data-toggle="table" data-url="user/userAddress/getUserAddressByUserId.action" data-page-size="5"
								data-page-list="[5, 10]" data-pagination="true" data-search="false" data-click-to-select="true" data-select-item-name="radioName" data-id-field="userAddressId">
								<thead>
									<tr>
										<th data-field="state" data-radio="true"></th>
										
										<th data-field="stateId" data-align="center" data-sortable="true">省/直辖市</th>
										<th data-field="cityId" data-align="center" data-sortable="false">市/直辖市区</th>
										<th data-field="districtId" data-align="center" data-sortable="false">区/县</th>
										<th data-field="street" data-sortable="false">街道地址</th>
										<th data-field="name" data-sortable="false" data-formatter="nameFormatter">收件人姓名</th>
										<th data-field="telephone" data-sortable="false">收件人电话</th>
									</tr>
								</thead>
							</table>
						</div> -->
				<div class="row">
					<label class="col-md-1 control-label" id="yajin">押金:</label>
					<label class="col-md-2 price" id="priceTag"></label>
					<label class="col-md-1 control-label">租金:</label>
					<label class="col-md-2" id="zujin"></label>
					<label class="col-md-2 control-label heji">合计:</label>
					<label class="col-md-2" id="sum"></label>
				</div>
				<div class="row"></div>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button id="activate-step-2" class="btn btn-primary pull-right">去租赁</button>
			</div>

		</div>
	</div>
</div>

<script type="application/javascript">
		function nameFormatter(value, row) {
			return value ? '<strong>' + value + '</strong> 收 ' : '';
		}
		
		$.ajax({
		    url: 'web/lease/getLeasePrice.action',
		    type: 'POST',
		    data: {
		    	productTypeId: '${shop.product.productTypeId}',
		    	productId: '${shop.product.productId}',
		    	promoteFlag: ${shop.promoteflag}
		    },
		    success: function (response) {
		    	var trHTML = '';
		    	var firstline='<thead><tr><th>时间</th>';
		        var secondline = '<tr><td>价格</td>';
		        var radioHTML= '';
		        
		        var firstZujin='';
		        $.each(response, function (i, item) {
		        	azujin=item.money*0.01*${shop.product.shopPrice};
		        	firstline+='<th>' + item.day + ' 天</th>'
		        	secondline += '<td>' + azujin.toFixed(2) + '</td>';
		        	//$('#option'+(i+1)).append(item.day);
		        	var j =i +1;
		        	var checked = '';
		        	var active = '';
		        	
		        	if(i==0){
		        		checked = 'checked';
		        		active = 'active'
		        		firstZujin= azujin.toFixed(2);
		        		firstYajin= ${shop.product.shopPrice}*(item.depositPercent).toFixed(2)
		        	}
		        	
		        	radioHTML +='<label id="label'+j+'" class="btn btn-success '+active+'"> <input type="radio" name="options" id="option'+j+'" value="'+azujin.toFixed(2)+'" data-period="'+item.day+'" data-percent="'+ item.depositPercent+'" autocomplete="off"'+checked+ '>'+item.day+'<span> 天 </span>'+'</label>'
		        });
		        
		        firstline +='</tr></thead>'
		        
		        secondline +='</tr>'
		       
		        trHTML = firstline + secondline;
		        
		        $('#period_selection').append(radioHTML);
		        
		        $('#records_table').append(trHTML);
		        
		        $('#zujin').html(firstZujin);
		        
		        $('#priceTag').html(firstYajin);
		        
		        $('#sum').html(firstZujin*1+$('#priceTag').text()*1);
		    }
		});
		
        $(document).ready(function() {
            var navListItems = $('ul.setup-panel li a'),
                    allWells = $('.setup-content');
            allWells.hide();
            navListItems.click(function(e)
            {
                e.preventDefault();
                var $target = $($(this).attr('href')),
                        $item = $(this).closest('li');

                if (!$item.hasClass('disabled')) {
                    navListItems.closest('li').removeClass('active');
                    $item.addClass('active');
                    allWells.hide();
                    $target.show();
                }
            });

           $('ul.setup-panel li.active a').trigger('click');
          var leaseData;
            // DEMO ONLY //
            $('#activate-step-2').on('click', function(e) {
               
               var leaseCycle=$("input:radio[name='options']:checked","#period_selection").attr('data-period');
               
             // var addressId=$("input:radio[name='radioName']:checked","#table-pagination").val();
               
				var count = $("input[name='addcount']").val();
					$.post("eshop/product/operationCart.action", {
						'action' : 'add',
						'cartType' : '2',
						'productId' : '${shop.product.productId}',
						'addcount' : 1,
						'leaseCycle':leaseCycle
					}, function() {
						window.location.reload()
					});
               
                /* $.post("web/lease/makeALease.do", {
					'productId' : '${shop.product.productId}',
					'productTypeId' : '${shop.product.productTypeId}',
					'count' : count,
					'leaseType' : '1',
					'leaseCycle' : leaseCycle,
					'addressId' : addressId
				}, function(data, status){
					leaseData=data;
					//alert(JSON.stringify(data));
			        //alert("Data: " + data.leaseOrderList.orderId + "\nStatus: " + status);
			        $('input#leaseId').val(data.leaseId);
			        $('input#orderId').val(data.leaseOrderList.orderId);
			        $('input#price').val(data.leaseOrderList.order_detail[0].totalamt);
			        $('input#quantity').val(data.leaseOrderList.order_detail[0].quantity);
			        //==========
			        $('ul.setup-panel li:eq(1)').removeClass('disabled');
	               $('ul.setup-panel li:eq(0)').addClass('disabled');
	               // alert($("input:radio[name='options']:checked").val());
	               // alert($("input:radio[name='radioName']:checked").attr('data-index'));
	               $('ul.setup-panel li a[href="#step-2"]').trigger('click');
	               $(this).remove();
			    }); */
            })
            
            // DEMO ONLY //
            $('#activate-step-3').on('click', function(e) {
               
                
                $.post("alipay/dummy/pay.do", {
					'orderId' : $('input#orderId').val(),
					'orderType': leaseData.leaseOrderList.orderType,
					'trade_no' : '123456789'
				}, function(data, status){
					 $('ul.setup-panel li:eq(2)').removeClass('disabled');
		                $('ul.setup-panel li:eq(1)').addClass('disabled');
		                $('ul.setup-panel li a[href="#step-3"]').trigger('click');
					
					//alert(JSON.stringify(data));
			        //alert("Data: " + data.leaseOrderList.orderId + "\nStatus: " + status);
					$(this).remove();
			    });
                
                
            })

            $('#activate-step-4').on('click', function(e) {
            	$.post("order/orderList/confirmDelivery.do", {
					'orderId' : $('input#orderId').val(),
					'productId': ${shop.product.productId},
					'leaseId': leaseData.leaseId
				}, function(data, status){
					$('ul.setup-panel li:eq(3)').removeClass('disabled');
	                $('ul.setup-panel li:eq(2)').addClass('disabled');
	                $('ul.setup-panel li a[href="#step-4"]').trigger('click');
	                $(this).remove();
					
					//alert(JSON.stringify(data));
			        //alert("Data: " + data.leaseOrderList.orderId + "\nStatus: " + status);
				//	$(this).remove();
			    });
            })
            
            $("#period_selection").on("change", function () {
               var myZujin = $("#period_selection").find(":radio:checked").first().val();
               var myYajin = $("#period_selection").find(":radio:checked").first().attr('data-percent');;
               $('#zujin').text(myZujin);
               $('#priceTag').text((myYajin*${shop.product.shopPrice}).toFixed(2));
               $('#sum').html(myZujin*1+$('#priceTag').text()*1);
            })
            
        });



</script>