<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        <title>商品详细页-${shop.product.productName}</title>

        <link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/idangerous.swiper.css" />
        <link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/public.css" />
        <link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/details.css" />
        <link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/style.css" />
        <script src="<%=basePath %>resources/js/jquery-1.11.1.min.js"></script>
        <script src="<%=basePath %>resources/js/idangerous.swiper.min.js"></script>
        <script src="<%=basePath %>resources/js/jquery.jcarousellite.min.js"></script>
        <script src="<%=basePath %>resources/js/details-show.js"></script>
        <script src="<%=basePath %>resources/js/FancyZoom.js"></script>
        <script src="<%=basePath %>resources/js/FancyZoomHTML.js"></script>
        <script src="<%=basePath %>resources/js/easySlider1.7.js"></script>
        <script src="<%=basePath %>resources/js/js.js"></script>
    </head>
    <body onload="setupZoom()">
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
                
                <!-- 商品详情 -->
                <div class="details">
                    <!-- 图片展示 -->
                    <div class="details_img">
                        <div class="MainBg">
                            <div class="OriginalPicBorder">
                                <div id="OriginalPic">
                                    <!-- <DIV id="aPrev" class="CursorL"></DIV>
                                    <DIV id="aNext" class="CursorR"></DIV> -->
	                				<c:forEach begin="0" end="${fn:length(shop.product.productimglist)/3}" varStatus="status">
	                                    <P class="Hidden">
	                                        <span class="SliderPicBorder FlRight">
	                                            <a href="${shop.product.productimglist[status.index*3+1].path }">
	                                                <img src="${shop.product.productimglist[status.index*3].path }">
	                                            </a>
	                                        </span>
	                                        <span class="Clearer"></span>
	                                    </P>
                                    </c:forEach>
                                    
                                </div> 
                            </div>
                            <div class="roll_imgWidth">
                                <div class="ThumbPicBorder">
                                    <img style="cursor: pointer;" id="btnPrev" class="FlLeft" src="resources/images/details_img/details_prev.png">
                                    <div class="roll_imgWidth">
                                        <div class="jCarouselLite FlLeft">
                                            <ul id="ThumbPic">
			                					<c:forEach begin="0" end="${fn:length(shop.product.productimglist)/4}" varStatus="status">
		                                        	<li rel="${status.index+1 }"><img src="${shop.product.productimglist[status.index*4+1].path }"></li>
			                					</c:forEach>
                                            </ul>
                                            <div class="Clearer"></div>
                                        </div>
                                    </div>
                                    <img style="cursor: pointer;" id="btnNext" class="FlLeft" src="resources/images/details_img/details_next.png">
                                    <div class="Clearer"></div>
                                </div>
                            </div>
                            <div class="HS15"></div>
                        </div>
                    </div> 
                    
                    <!-- 商品信息、购买和价钱 -->
                    <div class="details_infor">
                        <div class="brands_box">
                            <a href="javascript:;">
                                <img src="resources/images/brand01.jpg" />
                            </a>
                            <p>
                                 ${shop.productId}
                            </p>
                        </div>
                        <div class="details_pro">
                            <h3>${shop.product.productName}</h3>

                            <div class="simple_infor">
                                <p>
                                    ${shop.product.description}
                                </p>    
                            </div>

                            <div class="details_settle">
                                <span class="detail_settleStar"></span>
                                <a href="javascript:;">30条评价</a>
                            </div>
                            <div class="details_price">
                            
                            	<c:choose>
                            		<c:when test="${shop.promoteflag==1}">
	                                    <p class="new_price">
	                                       	 	促销价：<span class="catch">￥${shop.promotePrice}</span>
	                                    </p>
	                                    <p class="old_price">
	                                        	原价：<span>￥${shop.originalPrice}（购买节省￥${shop.originalPrice-shop.promotePrice}）</span>
	                                    </p>
                                    </c:when>
                                    <c:when test="${shop.promoteflag!=1}">
	                                    <p class="new_price">
	                                       	 	现价：<span class="catch">￥${shop.originalPrice}</span>
	                                    </p>
	                                    <p class="old_price">
	                                        	商品定价：<span>￥${shop.product.shopPrice}（购买节省￥${shop.product.shopPrice-shop.originalPrice}）</span>
	                                    </p>
                                    </c:when>
                            	</c:choose>
                                <p>运费：<span>免运费</span></p>
                            </div>
	                            <div class="details_buy">
		                                <span class="buy_count">
		                                    <input type="text" maxlength="2" value="1" name="addcount"/>
		                                    <div class="al_control">
		                                        <span class="buy_add">+</span>
		                                        <span class="buy_less">-</span>
		                                    </div>
		                                </span>
		                                <span class="buy_cart">
		                                    <input type="button" id="addCart" value="加入购物车" onclick="updateCart()" />
		                                </span>
		                                <span class="buy_collect">
		                                    <input type="button" />
		                                </span>
		                                <span class="buy_back">
		                                    <a href="web/repo/repoStart.shtml">回购</a>
		                                </span>
	                            </div>
	                            <script type="text/javascript">
	                            	function updateCart(){
	                            		var pid= '${shop.productId}';
	                            		var count = $("input[name='addcount']").val();
	                           			$.post("eshop/product/operationCart.action", {
	                    					'action' : 'add',
	                    					'cartType' : '0',
	                    					'productId' : pid,
	                    					'addcount' : count
	                    				},function(){
	                    					window.location.reload()
	                    				});
	                            	}
	                            </script>
                            </form>
                        </div>

                    </div>
                </div>

                

                <!-- 商品描述 -->
                <div class="pro_detailed">
                    <div class="overview">
                        <h3>商品详细</h3>
                        <p class="ov_subhead"></p>
                        <p class="ov_text">
                            ${shop.product.remarkHtml}
                        </p>
                    </div>

<!--                     <div class="spcific"> -->
<!--                         <h3>特点介绍</h3> -->
<!--                         <ul class="sf_list"> -->
<!--                             <li>特点介绍1</li> -->
<!--                             <li>特点介绍2</li> -->
<!--                             <li>特点介绍3</li> -->
<!--                             <li>特点介绍4</li> -->
<!--                         </ul> -->
<!--                         <p class="sf_subhead">需要一个黑色（当然也可以其他）背景</p> -->
<!--                     </div> -->

<!--                     <div class="parameter"> -->
<!--                         <h3>特点介绍</h3> -->
<!--                         <p class="pt_subhead">需要一个黑色（当然也可以其他）背景</p> -->
<!--                         <ul class="pt_list"> -->
<!--                             <li>特点介绍1</li> -->
<!--                             <li>特点介绍2</li> -->
<!--                             <li>特点介绍3</li> -->
<!--                             <li>特点介绍4</li> -->
<!--                             <li>特点介绍1</li> -->
<!--                             <li>特点介绍2</li> -->
<!--                             <li>特点介绍3</li> -->
<!--                             <li>特点介绍4</li> -->
<!--                         </ul> -->
<!--                     </div> -->
                </div>

                <!-- 好评率 -->
                <div class="pro_impress">
                    <div class="good_probability">
                        <div class="main_probability">
                            <strong>95%</strong>
                            <span>好评度</span>
                        </div>
                        <div class="probability">
                            <div><label>好评（95%）</label><div><span></span></div></div>
                            <div><label>中评（4%）</label><div><span></span></div></div>
                            <div><label>差评（1%）</label><div><span></span></div></div>
                        </div>
                    </div>
                    <div class="user_tag">
                        <p>用户印象</p>
                        <div class="tag_box">
                            <span class="good">就是喜欢（777）</span>
                            <span class="good">手感不错（1221）</span>
                            <span class="good">质量很好（33）</span>
                            <span class="good">性价比高（1）</span>
                            <span class="good">音质不错（55）</span>
                            <span class="good">定位准确（79）</span>
                            <span class="good">大小合适（44）</span>
                            <span class="bad">不怎么喜欢（33）</span>
                        </div>
                    </div>
                </div>

                <!-- 商品评价 -->
                <div class="pro_reviews">
                    <div id="reviews_chose" class="pro_reviewsType">
                        <ul>
                            <li class="active">全部评价</li>
                            <li>好评</li>
                            <li>中评</li>
                            <li>差评</li>
                        </ul>
                    </div>
                    <div class="reviews_words">
                        <div class="reviews_sort">
                            <span class="res_item1">评价</span>
                            <span class="res_item2">顾客满意度</span>
                            <span class="res_item3">购买信息</span>
                            <span class="res_item4">评论者</span>
                        </div>
                        <div class="reviews_list" id="reviews_alllist">
                            <ul>
                                <li>
                                    <table>
                                        <tbody>     
                                            <tr>
                                                <td class="td_item1">
                                                    散热出风口也不会说烫手，之前就听朋友说笔记本对散热要求高，不然硬件寿命会缩短，网上看到说联想笔记本散热性好，现在亲身体验下来，确实如此。 商品不错，吉他
                                                    <div class="img-scroll" id="scroll1">
                                                        <span class="prev" id="prev1"></span>
                                                        <span class="next" id="next1"></span>
                                                        <div class="img-list" id="list1">
                                                            <ul>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg01.png">
                                                                        <img src="resources/images/pro_bimg01.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg02.png">
                                                                        <img src="resources/images/pro_bimg02.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg03.png">
                                                                        <img src="resources/images/pro_bimg03.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg04.png">
                                                                        <img src="resources/images/pro_bimg04.png"/>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <script>
                                                        DY_scroll('#scroll1','#prev1','#next1','#list1',5,false);
                                                    </script>
                                                </td>
                                                <td class="td_item2">
                                                    <span></span>
                                                </td>
                                                <td class="td_item3">sdfasdvaafsd</td>
                                                <td class="td_item4">fwefwefs</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </li>
                                <li>
                                    <table>
                                        <tbody>     
                                            <tr>
                                                <td class="td_item1">
                                                    散热出风口也不会说烫手，之前就听朋友说笔记本对散热要求高，不然硬件寿命会缩短，网上看到说联想笔记本散热性好，现在亲身体验下来，确实如此。 商品不错，吉他
                                                    <div class="img-scroll" id="scroll2">
                                                        <span class="prev" id="prev2"></span>
                                                        <span class="next" id="next2"></span>
                                                        <div class="img-list" id="list2">
                                                            <ul>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg01.png">
                                                                        <img src="resources/images/pro_bimg01.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg02.png">
                                                                        <img src="resources/images/pro_bimg02.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg03.png">
                                                                        <img src="resources/images/pro_bimg03.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg04.png">
                                                                        <img src="resources/images/pro_bimg04.png"/>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <script>
                                                        DY_scroll('#scroll2','#prev2','#next2','#list2',5,false);
                                                    </script>
                                                </td>
                                                <td class="td_item2">
                                                    <span></span>
                                                </td>
                                                <td class="td_item3">sdfasdvaafsd</td>
                                                <td class="td_item4">fwefwefs</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </li>
                                <li>
                                    <table>
                                        <tbody>     
                                            <tr>
                                                <td class="td_item1">
                                                    散热出风口也不会说烫手，之前就听朋友说笔记本对散热要求高，不然硬件寿命会缩短，网上看到说联想笔记本散热性好，现在亲身体验下来，确实如此。 商品不错，吉他
                                                    <div class="img-scroll" id="scroll3">
                                                        <span class="prev" id="prev3"></span>
                                                        <span class="next" id="next3"></span>
                                                        <div class="img-list" id="list3">
                                                            <ul>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg01.png">
                                                                        <img src="resources/images/pro_bimg01.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg02.png">
                                                                        <img src="resources/images/pro_bimg02.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg03.png">
                                                                        <img src="resources/images/pro_bimg03.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg04.png">
                                                                        <img src="resources/images/pro_bimg04.png"/>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <script>
                                                        DY_scroll('#scroll3','#prev3','#next3','#list3',5,false);
                                                    </script>
                                                </td>
                                                <td class="td_item2">
                                                    <span></span>
                                                </td>
                                                <td class="td_item3">sdfasdvaafsd</td>
                                                <td class="td_item4">fwefwefs</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </li>
                            </ul>
                        </div>
                        <div class="reviews_list" id="reviews_goodlist">
                            <ul>
                                <li>
                                    <table>
                                        <tbody>     
                                            <tr>
                                                <td class="td_item1">
                                                    散热出风口也不会说烫手，之前就听朋友说笔记本对散热要求高，不然硬件寿命会缩短，网上看到说联想笔记本散热性好，现在亲身体验下来，确实如此。 商品不错，吉他
                                                    <div class="img-scroll" id="scroll4">
                                                        <span class="prev" id="prev4"></span>
                                                        <span class="next" id="next4"></span>
                                                        <div class="img-list" id="list4">
                                                            <ul>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg01.png">
                                                                        <img src="resources/images/pro_bimg01.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg02.png">
                                                                        <img src="resources/images/pro_bimg02.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg03.png">
                                                                        <img src="resources/images/pro_bimg03.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg04.png">
                                                                        <img src="resources/images/pro_bimg04.png"/>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <script>
                                                        DY_scroll('#scroll4','#prev4','#next4','#list4',5,false);
                                                    </script>
                                                </td>
                                                <td class="td_item2">
                                                    <span></span>
                                                </td>
                                                <td class="td_item3">sdfasdvaafsd</td>
                                                <td class="td_item4">fwefwefs</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </li>
                                <li>
                                    <table>
                                        <tbody>     
                                            <tr>
                                                <td class="td_item1">
                                                    散热出风口也不会说烫手，之前就听朋友说笔记本对散热要求高，不然硬件寿命会缩短，网上看到说联想笔记本散热性好，现在亲身体验下来，确实如此。 商品不错，吉他
                                                    <div class="img-scroll" id="scroll5">
                                                        <span class="prev" id="prev5"></span>
                                                        <span class="next" id="next5"></span>
                                                        <div class="img-list" id="list5">
                                                            <ul>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg01.png">
                                                                        <img src="resources/images/pro_bimg01.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg02.png">
                                                                        <img src="resources/images/pro_bimg02.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg03.png">
                                                                        <img src="resources/images/pro_bimg03.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg04.png">
                                                                        <img src="resources/images/pro_bimg04.png"/>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <script>
                                                        DY_scroll('#scroll5','#prev5','#next5','#list5',5,false);
                                                    </script>
                                                </td>
                                                <td class="td_item2">
                                                    <span></span>
                                                </td>
                                                <td class="td_item3">sdfasdvaafsd</td>
                                                <td class="td_item4">fwefwefs</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </li>
                                <li>
                                    <table>
                                        <tbody>     
                                            <tr>
                                                <td class="td_item1">
                                                    散热出风口也不会说烫手，之前就听朋友说笔记本对散热要求高，不然硬件寿命会缩短，网上看到说联想笔记本散热性好，现在亲身体验下来，确实如此。 商品不错，吉他
                                                    <div class="img-scroll" id="scroll6">
                                                        <span class="prev" id="prev6"></span>
                                                        <span class="next" id="next6"></span>
                                                        <div class="img-list" id="list6">
                                                            <ul>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg01.png">
                                                                        <img src="resources/images/pro_bimg01.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg02.png">
                                                                        <img src="resources/images/pro_bimg02.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg03.png">
                                                                        <img src="resources/images/pro_bimg03.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg04.png">
                                                                        <img src="resources/images/pro_bimg04.png"/>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <script>
                                                        DY_scroll('#scroll6','#prev6','#next6','#list6',5,false);
                                                    </script>
                                                </td>
                                                <td class="td_item2">
                                                    <span></span>
                                                </td>
                                                <td class="td_item3">sdfasdvaafsd</td>
                                                <td class="td_item4">fwefwefs</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </li>
                            </ul>
                        </div>
                        <div class="reviews_list" id="reviews_normallist">
                            <ul>
                                <li>
                                    <table>
                                        <tbody>     
                                            <tr>
                                                <td class="td_item1">
                                                    散热出风口也不会说烫手，之前就听朋友说笔记本对散热要求高，不然硬件寿命会缩短，网上看到说联想笔记本散热性好，现在亲身体验下来，确实如此。 商品不错，吉他
                                                    <div class="img-scroll" id="scroll7">
                                                        <span class="prev" id="prev7"></span>
                                                        <span class="next" id="next7"></span>
                                                        <div class="img-list" id="list7">
                                                            <ul>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg01.png">
                                                                        <img src="resources/images/pro_bimg01.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg02.png">
                                                                        <img src="resources/images/pro_bimg02.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg03.png">
                                                                        <img src="resources/images/pro_bimg03.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg04.png">
                                                                        <img src="resources/images/pro_bimg04.png"/>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <script>
                                                        DY_scroll('#scroll7','#prev7','#next7','#list7',5,false);
                                                    </script>
                                                </td>
                                                <td class="td_item2">
                                                    <span></span>
                                                </td>
                                                <td class="td_item3">sdfasdvaafsd</td>
                                                <td class="td_item4">fwefwefs</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </li>
                            </ul>
                        </div>
                        <div class="reviews_list" id="reviews_badlist">
                            <ul>
                                <li>
                                    <table>
                                        <tbody>     
                                            <tr>
                                                <td class="td_item1">
                                                    散热出风口也不会说烫手，之前就听朋友说笔记本对散热要求高，不然硬件寿命会缩短，网上看到说联想笔记本散热性好，现在亲身体验下来，确实如此。 商品不错，吉他
                                                    <div class="img-scroll" id="scroll8">
                                                        <span class="prev" id="prev8"></span>
                                                        <span class="next" id="next8"></span>
                                                        <div class="img-list" id="list8">
                                                            <ul>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg01.png">
                                                                        <img src="resources/images/pro_bimg01.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg02.png">
                                                                        <img src="resources/images/pro_bimg02.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg03.png">
                                                                        <img src="resources/images/pro_bimg03.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg04.png">
                                                                        <img src="resources/images/pro_bimg04.png"/>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <script>
                                                        DY_scroll('#scroll8','#prev8','#next8','#list8',5,false);
                                                    </script>
                                                </td>
                                                <td class="td_item2">
                                                    <span></span>
                                                </td>
                                                <td class="td_item3">sdfasdvaafsd</td>
                                                <td class="td_item4">fwefwefs</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </li>
                                <li>
                                    <table>
                                        <tbody>     
                                            <tr>
                                                <td class="td_item1">
                                                    散热出风口也不会说烫手，之前就听朋友说笔记本对散热要求高，不然硬件寿命会缩短，网上看到说联想笔记本散热性好，现在亲身体验下来，确实如此。 商品不错，吉他
                                                    <div class="img-scroll" id="scroll9">
                                                        <span class="prev" id="prev9"></span>
                                                        <span class="next" id="next9"></span>
                                                        <div class="img-list" id="list9">
                                                            <ul>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg01.png">
                                                                        <img src="resources/images/pro_bimg01.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg02.png">
                                                                        <img src="resources/images/pro_bimg02.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg03.png">
                                                                        <img src="resources/images/pro_bimg03.png"/>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="resources/images/pro_bimg04.png">
                                                                        <img src="resources/images/pro_bimg04.png"/>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <script>
                                                        DY_scroll('#scroll9','#prev9','#next9','#list9',5,false);
                                                    </script>
                                                </td>
                                                <td class="td_item2">
                                                    <span></span>
                                                </td>
                                                <td class="td_item3">sdfasdvaafsd</td>
                                                <td class="td_item4">fwefwefs</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </li>
                            </ul>
                        </div>
                        <div class="pages">
                            <a href="javascript">上一页</a>
                            <a class="active" href="javascript">1</a>
                            <a href="javascript">2</a>
                            <a href="javascript">3</a>
                            <a href="javascript">4</a>
                            <a href="javascript">5</a>
                            <a href="javascript">30</a>
                            <a href="javascript">下一页</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
	
	
	<!-- 猜你喜欢 -->
                <div class="guess_youlike top20">
                    <div class="guess_youlike_title">
                        <span>猜你喜欢</span>
                    </div>
                    <div class="guess_youlike_rolBox">  
                        <div id="guess_youlike">  
                            <div class="guseeLike_btn guseeLike_prev" id="guseeLike_prev">
                                
                            </div>
                            <div class="guseeLike_btn guseeLike_next" id="guseeLike_next">

                            </div>
                            <div class="swiper-wrapper">
                                <div class="swiper-slide">
                                    <div class="pro_box">
                                        <div class="pro_imgbox">
                                            <img src="resources/images/prd01.jpg">
                                            <span class="detailed_btn"></span>
                                            <div class="detailed_message">
                                                查看详情
                                            </div>
                                        </div>
                                        <div class="pro_message">
                                            <a href="javascript:;">
                                                商品名称商品名称越长，排版会稍微好看一点
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
                                            <img src="resources/images/prd02.jpg"> 
                                            <span class="detailed_btn"></span>
                                            <div class="detailed_message">
                                                查看详情
                                            </div>
                                        </div>
                                        <div class="pro_message">
                                            <a href="javascript:;">
                                                商品名称商品名称越长，排版会稍微好看一点
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
                                            <img src="resources/images/prd03.jpg">  
                                            <span class="detailed_btn"></span>
                                            <div class="detailed_message">
                                                查看详情
                                            </div>
                                        </div>
                                        <div class="pro_message">
                                            <a href="javascript:;">
                                                商品名称商品名称越长，排版会稍微好看一点
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
                                                商品名称商品名称越长，排版会稍微好看一点
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
                                                商品名称商品名称越长，排版会稍微好看一点
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
                                    <div class="pro_box">
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
                                    <div class="pro_box">
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
                                    <div class="pro_box">
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
                    </div>
                </div>

                <!-- 类似商品 -->
                <div class="similar top20">
                    <div class="similar_title">
                        <span>类似商品</span>
                    </div>
                    <div class="similar_rolBox">  
                        <div id="similar">  
                            <div class="similar_btn similar_prev" id="similar_prev">
                                
                            </div>
                            <div class="similar_btn similar_next" id="similar_next">

                            </div>
                            <div class="swiper-wrapper">
                                <div class="swiper-slide">
                                    <div class="pro_box right_line">
                                        <div class="pro_imgbox">
                                            <img src="resources/images/prd01.jpg">
                                            <span class="detailed_btn"></span>
                                            <div class="detailed_message">
                                                查看详情
                                            </div>
                                        </div>
                                        <div class="pro_message">
                                            <a href="javascript:;">
                                                商品名称商品名称越长，排版会稍微好看一点
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
                                            <img src="resources/images/prd02.jpg"> 
                                            <span class="detailed_btn"></span>
                                            <div class="detailed_message">
                                                查看详情
                                            </div>
                                        </div>
                                        <div class="pro_message">
                                            <a href="javascript:;">
                                                商品名称商品名称越长，排版会稍微好看一点
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
                                                商品名称商品名称越长，排版会稍微好看一点
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
                                            <img src="resources/images/prd04.jpg">
                                            <span class="detailed_btn"></span>
                                            <div class="detailed_message">
                                                查看详情
                                            </div>
                                        </div>
                                        <div class="pro_message">
                                            <a href="javascript:;">
                                                商品名称商品名称越长，排版会稍微好看一点
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
                                                商品名称商品名称越长，排版会稍微好看一点
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
                                    <div class="pro_box right_line">
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

        <script>
            var mySwiper = new Swiper('#guess_youlike',{
                loop: true,
                touchRatio : 0,
                //其他设置
            });

            $('#guseeLike_prev').click(function(){
                mySwiper.swipePrev(); 
            })
            $('#guseeLike_next').click(function(){
                mySwiper.swipeNext(); 
            })



            var mySwiper1 = new Swiper('#similar',{
                loop: true,
                touchRatio : 0,
                //其他设置
            });

            $('#similar_prev').click(function(){
                mySwiper1.swipePrev(); 
            })
            $('#similar_next').click(function(){
                mySwiper1.swipeNext(); 
            })

            //缩略图滚动事件
            $(".jCarouselLite").jCarouselLite({
                btnNext: "#btnNext",
                btnPrev: "#btnPrev",
                scroll: 1,
                speed: 240,
                circular: false,
                visible: 3,
            });
        </script>
    </body>
</html>