<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
    <head>
    <base href="<%=basePath%>" />
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>热卖商品</title>

        <link rel="stylesheet" href="resources/css/public.css" />
        <link rel="stylesheet" type="text/css" href="resources/css/style.css" />
        <link href="resources/css/city.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="resources/css/presonal.css" />

        <script src="resources/js/jquery-1.11.1.min.js"></script>
        <script src="resources/js/area.js"></script>
        <script src="resources/js/easySlider1.7.js"></script>
        <script src="resources/js/js.js"></script>
    </head>
    <body>
        <!-- header -->
        <div class="header">
            <!-- 导航 -->
            <div class="nav">
                <!-- 第一部分导航 -->
                <jsp:include page="/WEB-INF/web/common/top.jsp" />
                <div class="clear"></div>
                <!-- 第二部分导航 -->
                <jsp:include page="/WEB-INF/web/common/navigation.jsp" />
            </div>
        </div>

        <!-- content -->
        <div class="maxwidth content">
            <div class="buyback_st">
                <form action="web/repo/repoType.action" method="post" name="repoType">
                    <div class="select_buyback">
                        <h3>请选择你所需要的回购方式</h3>
                        <div class="radio_box">
                            <input id="get_older" type="radio" name="repotype" checked="checked" value="depreciation"/><label for="get_older">折旧</label>
                        </div>
                        <div class="radio_box">
                            <input id="get_money" type="radio" name="repotype" value="discount"/><label for="get_money">折现</label>
                        </div>
                        
                        <div class="pro_Numbox">
                            <input id="proNum" type="text" placeholder="请输入商品编号" name="productId" oninput="validproductId(this);"/>
                            <span id='proNum_error' style="display: block; margin-top: 20px; color: red;"></span>
                        </div>

                        <span class="other_buyback"><a href="web/repo/otherRepo.shtml">非本网站购买商品折换渠道>></a></span>
                        <div id="discount" class="discount">
                            <input type="button" value="折现率查询" id="queryDiscount"/>
                        </div>

                        <div class="go_next">
                            <input type="button" id="next" value="下一步" />
                        </div>
                    </div>

	                <div class="discount_box">
	                    <h3>该商品折现率</h3>
	                    <table>
	                        <thead>
	                            <tr>
	                            	<td>折现率选择</td>
	                                <td>年份</td>
	                                <td>折现率</td>
	                            </tr>
	                        </thead>
	                        <tbody id="discountboty">
	                        	<c:forEach items="${discountList}" var="discount">
		                            <tr>
		                            	<td class="pre"><input type="radio" name="percent"></td>
		                                <td>${discount.month/12}年</td>
		                                <td>${discount.discountRate}%</td>
		                            </tr>
	                            </c:forEach>
	                        </tbody>
	                    </table>
	                </div>
                </form>
            </div>
        </div>

        <!-- footer -->
        <jsp:include page="/WEB-INF/web/common/footer.jsp" />
        <script type="text/javascript">
        $(function(){
        	$("#queryDiscount").click(function(){
        		var productId = $("#proNum").val();
        		if(productId==""){
        			return;
        		}
        		$.ajax({
        			url:"/web/repo/getDisCount.action",
        			data:{productId:productId},
        			dataType:"JSON",//JSON
        			success:function(data){
        				$("#discountboty").children().remove();
        				$.each(data,function(index,discount){
            				$("#discountboty").append("<tr><td class='pre'><input type='radio' checked name='percent' value="+discount.discountRate+" ></td>"+
                                    "<td>"+discount.month/12+"年"+
                                    "<td>"+discount.discountRate+"%</td></tr>");
        				});
        			}
        		});
        	});
        });
        var boolean = false;
        function validproductId(obj){
        	if(obj.value.length>=13){
	        	$.ajax({
	    			url:"/web/repo/validateProduct.shtml",
	    			data:{productId:obj.value},
	    			dataType:"text",//JSON
	    			success:function(data){
	    				if(data=="true"){
	    					boolean = true;
	    					$("#proNum_error").text("");
	    				}else{
	    					$("#proNum_error").text("您好，您并未购买此商品");
	    				}
	    			}
	    		});
        	}
        }
        $(function(){
        	$("#next").click(function(){
        		if(boolean){
        			var percentList = document.getElementsByName("percent");
        			if(percentList==null || percentList.length<=0){
    					$("#proNum_error").text("请查看并选择折现率");
    					return;
        			}
        			document.repoType.submit();
        		}
        	});
        });
        </script>
    </body>
</html>