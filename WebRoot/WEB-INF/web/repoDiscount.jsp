<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
        <title>回购折现</title>

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
            <div class="getmoney_way">
                <h3>进行折现</h3>
                <div class="my_chose_oods">
                    <ul style="text-align: left;">
                        <li>商品名：<span>${shop.product.productName}</span></li>
                        <li>折扣率：<span class="oods_color">${percent}%</span></li>
                        <li>折扣价格：<span class="oods_color">￥${price}</span></li>
                      	<li class="send_address">
                            	请发货到以下地址：<span>${sendaddr } 
                            	收货人：${senduser } 
                            	手机号码：${senduserphone } 
                            	邮政编码：${postcode }</span>
                        </li>
                    </ul>
                </div>
                <form action="web/repo/addRepo.action" method="POST" name="repoform">
                	<input type="hidden" name="repoType" value="1"/>
                	<input type="hidden" name="productId" value="${productId }"/>
                	<input type="hidden" name="price" value="${price}"/>
                    <div class="input_infor presonal_check">
                        <label for="">银行卡号：<span>*</span></label>
                        <input id="bank-card" type="text" name="bankcard"/>
                        <span class="address_error"></span>
                    </div>

                    <div class="input_infor presonal_check">
                        <label>持卡人姓名：<span>*</span></label> 
                        <input id="card-name" type="text" name="cardname"/>
                        <span class="address_error"></span>
                    </div>

                    <div class="input_infor presonal_check">
                        <label>手机号码：<span>*</span></label> 
                        <input id="phone" type="text" name="phone"/>
                        <span class="address_error"></span>
                    </div>

                    <input id="get_moneypage" class="next_step" type="button" value="下一步" onclick="document.repoform.submit();">
                </form>
            </div>
        </div>

        <!-- footer -->
        <jsp:include page="/WEB-INF/web/common/footer.jsp" />
    </body>
</html>