<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <title>乐星网</title>

        <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/css/idangerous.swiper.css">
        <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/css/public.css">
        <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/css/style.css">
        <script src="<%=basePath%>resources/js/jquery-1.11.1.min.js"></script>
        <script src="<%=basePath%>resources/js/idangerous.swiper.min.js"></script>
        <script src="<%=basePath%>resources/js/easySlider1.7.js"></script>
        <script src="<%=basePath%>resources/js/js.js"></script>
    </head>
    <body>
        <!-- header -->
        <div class="header">
            <!-- 导航 -->
            <div class="nav">
                <!-- 第一部分导航 -->
                <jsp:include page="/WEB-INF/web/common/top.jsp"></jsp:include>
                <div class="clear"></div>

                <!-- 第二部分导航 -->
                <jsp:include page="/WEB-INF/web/common/navigation.jsp"></jsp:include>
                
            </div>
        </div>

        <!-- content -->
        <div class="content">
            <div class="content_maxwidth">
                <div class="main_img" id="main_img"> 
                    <div class="mainImg_btn main_prev" id="mainImg_prev">
                    </div>
                    <div class="mainImg_btn main_next" id="mainImg_next">
                    </div>
                    <div class="swiper-wrapper">
                    	<c:forEach items="${ad_parasList}" var="ad">
                    		 <div class="swiper-slide">
                            	<img src="${ad.ad_imagesList[0].imagepath }">
                       		</div>
                    	</c:forEach>
                       
<!--                         <div class="swiper-slide"> -->
<!--                             <img src="resources/images/main_img.jpg"> -->
<!--                         </div> -->
<!--                         <div class="swiper-slide"> -->
<!--                             <img src="resources/images/main_img.jpg"> -->
<!--                         </div> -->
                    </div>                 
                </div>

                <div class="get_email">
                    <div class="get_email_title">
                        <h3>获取email</h3>
                    </div>
                    <div class="get_email_box">
                        <p>
                            注册成为会员，首先需要验证您的E-mail地址,请在输入框中输入您的E-mail地址进行验证
                        </p>
                        <form action="http://www.baidu.com">
                            <input class="check_email" id="check_email" placeholder="请输入您的E-mail地址" />
                            <span class="error_message"></span>
                            <input class="sub_email" id="sub_email" disabled="disabled" type="submit" value="现在加入" />
                        </form>
                    </div>
                </div>

                <div class="spotlights">
                    <h3>推荐商品</h3>
                    <a href="javascript:;">
                        <img src="resources/images/spotlights01.jpg">
                    </a>
                    <a href="javascript:;">
                        <img src="resources/images/spotlights02.jpg">
                    </a>
                </div>

                <div class="roll_img_show" id="product_show">
                    <div class="roll_title">
                        流行商品
                    </div>
                    <div class="control_btn prev_btn" id="roll_prev">
                        
                    </div>
                    <div class="control_btn next_btn" id="roll_next">

                    </div>
                    <div class="swiper-wrapper">
                        <div class="swiper-slide">
                        	
                        	<c:forEach items="${recommendProdsList}" var="recommend">
                        	
                        		 <div class="pro_box right_line">
	                                <div class="pro_imgbox">
	                                    <img src="${recommend.product_ext_shop.product.productimglist[0].path}"  width="120" height="120">
	                                    <span class="detailed_btn"></span>
	                                   	<a href="eshop/shopProduct/productShopDetail.html?productId=${recommend.productId}"> <div class="detailed_message">
	                                        查看详情
	                                    </div></a>
	                                </div>
	                                <div class="pro_message">
	                                    <a href="javascript:;">
	                                       	${recommend.product_ext_shop.product.productName }
	                                    </a>
	                                    <div class="comment">
	                                        <span class="stars"></span>
	                                        <span class="comment_count"><a href="javascript:;">7评论</a></span>
	                                    </div>
	                                   <c:choose>
                                    	<c:when test="${recommend.product_ext_shop.promoteflag==1}">
		                                    <p class="new_price">
		                                       	 	促销价：￥${recommend.product_ext_shop.promotePrice}
		                                    </p>
		                                    <p class="old_price">
		                                        	原价：￥${recommend.product_ext_shop.originalPrice}（购买节省￥${recommend.product_ext_shop.originalPrice-recommend.product_ext_shop.promotePrice}）
		                                    </p>
	                                    </c:when>
	                                    <c:when test="${recommend.product_ext_shop.promoteflag!=1}">
		                                    <p class="new_price">
		                                       	 	现价：￥${recommend.product_ext_shop.originalPrice}
		                                    </p>
		                                    <p class="old_price">
		                                        	商品定价：￥${recommend.product_ext_shop.product.shopPrice}（购买节省￥${recommend.product_ext_shop.product.shopPrice-recommend.product_ext_shop.originalPrice}）
		                                    </p>
	                                    </c:when>
                                    </c:choose>
	                                </div>
	                            </div>
                        	
                        	</c:forEach>
                           
                            <div class="pro_box right_line">
                                <div class="pro_imgbox">
                                    <img src="resources/images/prd02.jpg"> 
                                    <span class="detailed_btn"></span>
                                    <div class="detailed_message">
                                        查看详情
                                    </div>
                                </div>
                                <div class="pro_message">
                                    <a href="javascript:;">
                                        商品名称
                                    </a>
                                    <div class="comment">
                                        <span class="stars"></span>
                                        <span class="comment_count"><a href="javascript:;">7评论</a></span>
                                    </div>
                                    <p class="new_price">
                                        折扣价：￥230.00
                                    </p>
                                    <p class="old_price">
                                        原价：￥330.00（购买节省￥100）
                                    </p>
                                </div>
                            </div>
                            <div class="pro_box right_line">
                                <div class="pro_imgbox">
                                    <img src="resources/images/prd03.jpg">  
                                    <span class="detailed_btn"></span>
                                    <div class="detailed_message">
                                        查看详情
                                    </div>
                                </div>
                                <div class="pro_message">
                                    <a href="javascript:;">
                                        商品名称
                                    </a>
                                    <div class="comment">
                                        <span class="stars"></span>
                                        <span class="comment_count"><a href="javascript:;">7评论</a></span>
                                    </div>
                                    <p class="new_price">
                                        折扣价：￥230.00
                                    </p>
                                    <p class="old_price">
                                        原价：￥330.00（购买节省￥100）
                                    </p>
                                </div>
                            </div>
                            <div class="pro_box">
                                <div class="pro_imgbox">
                                    <img src="resources/images/prd04.jpg">
                                    <span class="detailed_btn"></span>
                                    <div class="detailed_message">
                                        查看详情
                                    </div>
                                </div>
                                <div class="pro_message">
                                    <a href="javascript:;">
                                        商品名称
                                    </a>
                                    <div class="comment">
                                        <span class="stars"></span>
                                        <span class="comment_count"><a href="javascript:;">7评论</a></span>
                                    </div>
                                    <p class="new_price">
                                        折扣价：￥230.00
                                    </p>
                                    <p class="old_price">
                                        原价：￥330.00（购买节省￥100）
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="pro_box right_line">
                                <div class="pro_imgbox">
                                    <img src="resources/images/prd05.jpg">  
                                    <span class="detailed_btn"></span>
                                    <div class="detailed_message">
                                        查看详情
                                    </div>
                                </div>
                                <div class="pro_message">
                                    <a href="javascript:;">
                                        商品名称
                                    </a>
                                    <div class="comment">
                                        <span class="stars"></span>
                                        <span class="comment_count"><a href="javascript:;">7评论</a></span>
                                    </div>
                                    <p class="new_price">
                                        折扣价：￥230.00
                                    </p>
                                    <p class="old_price">
                                        原价：￥330.00（购买节省￥100）
                                    </p>
                                </div>
                            </div>
                            <div class="pro_box right_line">
                                <div class="pro_imgbox">
                                    <img src="resources/images/prd06.jpg">  
                                    <span class="detailed_btn"></span>
                                    <div class="detailed_message">
                                        查看详情
                                    </div>
                                </div>
                                <div class="pro_message">
                                    <a href="javascript:;">
                                        商品名称
                                    </a>
                                    <div class="comment">
                                        <span class="stars"></span>
                                        <span class="comment_count"><a href="javascript:;">7评论</a></span>
                                    </div>
                                    <p class="new_price">
                                        折扣价：￥230.00
                                    </p>
                                    <p class="old_price">
                                        原价：￥330.00（购买节省￥100）
                                    </p>
                                </div>
                            </div>
                            <div class="pro_box right_line">
                                <div class="pro_imgbox">
                                    <img src="resources/images/prd07.jpg">  
                                    <span class="detailed_btn"></span>
                                    <div class="detailed_message">
                                        查看详情
                                    </div>
                                </div>
                                <div class="pro_message">
                                    <a href="javascript:;">
                                        商品名称
                                    </a>
                                    <div class="comment">
                                        <span class="stars"></span>
                                        <span class="comment_count"><a href="javascript:;">7评论</a></span>
                                    </div>
                                    <p class="new_price">
                                        折扣价：￥230.00
                                    </p>
                                    <p class="old_price">
                                        原价：￥330.00（购买节省￥100）
                                    </p>
                                </div>
                            </div>
                            <div class="pro_box">
                                <div class="pro_imgbox">
                                    <img src="resources/images/prd08.jpg">  
                                    <span class="detailed_btn"></span>
                                    <div class="detailed_message">
                                        查看详情
                                    </div>
                                </div>
                                <div class="pro_message">
                                    <a href="javascript:;">
                                        商品名称
                                    </a>
                                    <div class="comment">
                                        <span class="stars"></span>
                                        <span class="comment_count"><a href="javascript:;">7评论</a></span>
                                    </div>
                                    <p class="new_price">
                                        折扣价：￥230.00
                                    </p>
                                    <p class="old_price">
                                        原价：￥330.00（购买节省￥100）
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>                   
                </div>



                <div class="content_words">
                    <div>
                        <h3>无法保证明天就</h3>
                    </div>
                    <p>
                        享受今天的每分每秒，因为你无法保证明天就一定会到来不要拖延自己的梦想，别等“将来某一天”再去做，去尝试，去享受。如果真的很重要，那就只争朝夕今天就做！关注正在发生的事情，身边的人，手上的工作，关注今天做出的选择，无论大小。3. Let it go. 学会放手。moving forward in your life. 昨日种种譬如昨日死，那些没有实现的期待，那些艰难困苦、失败和冲突都已成为过去，你无力改变，所以干脆放手。别把自己的精力浪费在纠结着生气、愤恨和失望这些负面情绪上，这些只会让你沉湎过去，让你停滞不前。It’s called work for a reason.付出总会有回报。success story, don’t forget about all of the work that came before. You may have to do work you do not enjoy and trudge through the trenches of planning, building, refining, moving up, out, over and redefining before you get to the place where success clicks. Keep going. 任何成功都需要付出劳动。你所听到的那些一夜成名的故事，不要忘记背后付出的所有劳动和汗水。也许你得做无爱的工作，需要在计划、构建、修改、实施、再修改这一系列琐碎的过程中艰难前行，直到突然有一天成功破天而降。继续前进不要停。5. Believe in yourself.
                    </p>
                </div>
            </div>
        </div>

        <!-- footer -->
        <div class="footer">
            <div class="content_maxwidth">
                <div class="footer_floor1">
                    <div class="show_goods">
                        <div id="slider">
                            <span class="left_shadow"></span> 
                            <ul>
                                               
                                <li>
                                    <h3>商品展示1</h3>
                                    <a href="javascript:;">
                                        <img src="resources/images/img1.jpg" />
                                        <br/>
                                        <p>商品说明文商品说明文商品说明文商品说明文商品说
                                        </p>
                                    </a>
                                </li>
                                <li>
                                    <h3>商品展示1</h3>
                                    <a href="javascript:;">
                                        <img src="resources/images/img2.jpg" />
                                        <br/>
                                        <p>商品说明文商品说明文商品说明文商品说明文商品说
                                        </p>
                                    </a>
                                </li>
                                <li>
                                    <h3>商品展示1</h3>
                                    <a href="javascript:;">
                                        <img src="resources/images/img3.jpg" />
                                        <br/>
                                        <p>商品说明文商品说明文商品说明文商品说明文商品说
                                        </p>
                                    </a>
                                </li>
                                <li>
                                    <h3>商品展示1</h3>
                                    <a href="javascript:;">
                                        <img src="resources/images/img4.jpg" />
                                        <br/>
                                        <p>商品说明文商品说明文商品说明文商品说明文商品说
                                        </p>
                                    </a>
                                </li>
                            </ul>
                            <span class="right_shadow"></span> 
                        </div>
                    </div>    
                    <div class="service">
                        <h3>服务</h3>
                        <a href="javascript:;">服务项目1</a>
                        <a href="javascript:;">服务项目2</a>
                    </div>
                    <div class="support">
                        <h3>支持</h3>
                        <a href="javascript:;">项目支持1</a>
                        <a href="javascript:;">项目支持2</a>
                        <a href="javascript:;">项目支持3</a>
                        <a href="javascript:;">项目支持4</a>
                        <a href="javascript:;">项目支持5</a>
                    </div>
                    <div class="about">
                        <h3>关于我们</h3>
                        <a href="javascript:;">关于我们1</a>
                        <a href="javascript:;">关于我们2</a>
                        <a href="javascript:;">关于我们3</a>
                        <div class="contact">
                            <div class="contact_words">
                                <a href="javascript:;">联系我们</a>  
                                <a href="javascript:;">FAQ</a>
                                <p>+183444455</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="footer_floor2">
                    <div class="share">
                        <h3>分享到：</h3>
                        <ul id="share_link">
                            <li><a href="javascript:;"></a></li>
                            <li><a href="javascript:;"></a></li>
                            <li><a href="javascript:;"></a></li>
                            <li><a href="javascript:;"></a></li>
                            <li><a href="javascript:;"></a></li>
                            <li><a href="javascript:;"></a></li>
                            <li><a href="javascript:;"></a></li>
                        </ul>
                    </div>

                    <div class="floor2_right">
                        <div class="floor2_rtitle top10">
                            <h3>未知功能1</h3>
                        </div>
                        <ul class="top10">
                            <li><a href="javascript:;">链接1</a></li>
                            <li><a href="javascript:;">链接2</a></li>
                            <li><a href="javascript:;">链接366666</a></li>
                            <li><a href="javascript:;">链接4</a></li>
                            <li><a href="javascript:;">链接5</a></li>
                            <li><a href="javascript:;">链接6</a></li>
                            <li><a href="javascript:;">链接7</a></li>
                            <li><a href="javascript:;">链接8</a></li>
                            <li><a href="javascript:;">链接9</a></li>
                            <li><a href="javascript:;">链接6</a></li>
                            <li><a href="javascript:;">链接7</a></li>
                            <li><a href="javascript:;">链接8</a></li>
                            <li><a href="javascript:;">链接9</a></li>
                        </ul> 

                        <span></span>

                        <div class="floor2_rtitle">
                            <h3>未知功能1</h3>
                        </div>
                        <ul>
                            <li><a href="javascript:;">链接1</a></li>
                            <li><a href="javascript:;">链接2</a></li>
                            <li><a href="javascript:;">链接3</a></li>
                            <li><a href="javascript:;">链接4</a></li>
                            <li><a href="javascript:;">链接5</a></li>
                            <li><a href="javascript:;">链接6</a></li>
                            <li><a href="javascript:;">链接7</a></li>
                        </ul>
                    </div>
                </div>
                <div class="footer_floor3">
                    <div class="friends" id="friend">
                        <ul>
                            <li><a href="javascript:;"></a></li>
                            <li><a href="javascript:;"></a></li>
                            <li><a href="javascript:;"></a></li>
                        </ul>
                    </div>

                    <div class="floor3_right">
                        <div class="floor3_rlink">
                            <a href="javascript:;">链接测试1</a><span>|</span>
                            <a href="javascript:;">链接测试1</a><span>|</span>
                            <a href="javascript:;">链接测试1</a><span>|</span>
                            <a href="javascript:;">链接测试1</a><span>|</span>
                            <a href="javascript:;">链接测试1</a>    
                        </div> 
                        <div class="floor3_rtxt">
                            <p>
                                jQuery EasyUI 是一个基于 jQuery 的框架，集成了各种用户界面插件。jQuery EasyUI 框架提供了创建网页所需的一切，帮助您轻松建立站点。本教程将告诉您如何使用 jQuery EasyUI 框架创建应用。正如前面所说，你在使用EasyUI之前必须先声明UI控件，有两个方法声明它。1. 直接在 HTML 声明组件。
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            var mainImg = new Swiper('#main_img',{
                loop: true,
                touchRatio : 0,
                //其他设置
            });

            $('#mainImg_prev').click(function(){
                mainImg.swipePrev(); 
            })
            $('#mainImg_next').click(function(){
                mainImg.swipeNext(); 
            })


            var mySwiper = new Swiper('#product_show',{
                loop: true,
                touchRatio : 0,
                //其他设置
            });

            $('#roll_prev').click(function(){
                mySwiper.swipePrev(); 
            })
            $('#roll_next').click(function(){
                mySwiper.swipeNext(); 
            })
         </script>
    </body>
</html>