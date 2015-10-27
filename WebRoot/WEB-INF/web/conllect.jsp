<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
    <base href="<%=basePath%>">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>登录及注册</title>

        <link rel="stylesheet" type="text/css" href="resources/css/public.css">
        <link rel="stylesheet" type="text/css" href="resources/css/style.css">
        <script src="resources/js/jquery-1.11.1.min.js"></script>
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
        <div class="content maxwidth">
            <div class="collect">
                <h3>我的收藏</h3>
                <div class="all_to_cart">
                    <a>全部添加到购物车</a>
                </div>

                <div class="my_collect">
                    <ul>
                        <li>
                            <div class="collect_item1">
                                <a href="javascript:;">
                                    <img src="images/pro03.jpg" />
                                </a>
                            </div>
                            <div class="collect_item2">
                                <a href="product-details-page.html">
                                    官方授权正品 YAMAHA雅马哈 F310民谣吉他 41寸 初学民谣木吉他官方授权正品 YAMAHA雅马哈 F310民谣吉他 41寸 初学民谣木吉他
                                </a>
                                <p>价格：<strong>￥335元</strong></p>
                                <p>添加时间：<strong>2015-07-14</strong></p>
                            </div>
                            <div class="collect_item3">
                                <a>加入购物车</a>
                            </div>
                            <div class="collect_item4">
                                <a>从收藏夹中删除</a>
                            </div>
                        </li>
                        <li>
                            <div class="collect_item1">
                                <a href="javascript:;">
                                    <img src="images/pro03.jpg" />
                                </a>
                            </div>
                            <div class="collect_item2">
                                <a href="product-details-page.html">
                                    官方授权正品 YAMAHA雅马哈 F310民谣吉他 41寸 初学民谣木吉他官方授权正品 YAMAHA雅马哈 F310民谣吉他 41寸 初学民谣木吉他
                                </a>
                                <p>价格：<strong>￥335元</strong></p>
                                <p>添加时间：<strong>2015-07-14</strong></p>
                            </div>
                            <div class="collect_item3">
                                <a>加入购物车</a>
                            </div>
                            <div class="collect_item4">
                                <a>从收藏夹中删除</a>
                            </div>
                        </li>
                        <li>
                            <div class="collect_item1">
                                <a href="javascript:;">
                                    <img src="images/pro03.jpg" />
                                </a>
                            </div>
                            <div class="collect_item2">
                                <a href="product-details-page.html">
                                    官方授权正品 YAMAHA雅马哈 F310民谣吉他 41寸 初学民谣木吉他官方授权正品 YAMAHA雅马哈 F310民谣吉他 41寸 初学民谣木吉他
                                </a>
                                <p>价格：<strong>￥335元</strong></p>
                                <p>添加时间：<strong>2015-07-14</strong></p>
                            </div>
                            <div class="collect_item3">
                                <a>加入购物车</a>
                            </div>
                            <div class="collect_item4">
                                <a>从收藏夹中删除</a>
                            </div>
                        </li>
                    </ul>
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
                                        <img src="images/img1.jpg" />
                                        <br/>
                                        <p>商品说明文商品说明文商品说明文商品说明文商品说
                                        </p>
                                    </a>
                                </li>
                                <li>
                                    <h3>商品展示1</h3>
                                    <a href="javascript:;">
                                        <img src="images/img2.jpg" />
                                        <br/>
                                        <p>商品说明文商品说明文商品说明文商品说明文商品说
                                        </p>
                                    </a>
                                </li>
                                <li>
                                    <h3>商品展示1</h3>
                                    <a href="javascript:;">
                                        <img src="images/img3.jpg" />
                                        <br/>
                                        <p>商品说明文商品说明文商品说明文商品说明文商品说
                                        </p>
                                    </a>
                                </li>
                                <li>
                                    <h3>商品展示1</h3>
                                    <a href="javascript:;">
                                        <img src="images/img4.jpg" />
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
                            <li><a href="javascript:;">链接3</a></li>
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
    </body>
</html>