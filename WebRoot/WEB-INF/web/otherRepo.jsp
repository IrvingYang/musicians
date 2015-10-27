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
            <div class="other_way">
                <h3>非本站商品回购申请</h3>

                <form action="web/site/addSite.shtml" method="POST">
                	<input type="hidden" name="emailType" value="1"/>
                    <div class="input_infor">
                        <label for="">商品名称：<span>*</span></label>
                        <input id="otherway-name" type="text" name="productName"/>
                        <span class="address_error"></span>
                    </div>

                    <div class="input_infor">
                        <label class="textearestyle" for="">详细介绍：<span>*</span></label>
                        <textarea id="otherway-infor" name="description"></textarea>
                        <span class="address_error"></span>
                    </div>                

                    <div class="input_infor">
                        <label for="">联系电话：<span>*</span></label>
                        <input id="otherway-phone" type="text" name="telephone"/>
                        <span class="address_error"></span>
                    </div>

                    <div class="input_infor">
                        <label for="">邮箱：<span>*</span></label>
                        <input id="otherway-email" type="text" name="email"/>
                        <span class="address_error"></span>
                    </div>

                    <input id="application" class="application" type="submit" value="申请回购" />
                </form>
            </div>
        </div>
        <!-- footer -->
        <jsp:include page="/WEB-INF/web/common/footer.jsp" />
    </body>
</html>