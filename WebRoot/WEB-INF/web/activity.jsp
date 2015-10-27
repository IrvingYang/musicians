<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
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

        <link rel="stylesheet" href="css/public.css" />
        <link rel="stylesheet" type="text/css" href="css/style.css" />

        <script src="js/jquery-1.11.1.min.js"></script>
        <script src="js/area.js"></script>
        <script src="js/easySlider1.7.js"></script>
        <script src="js/js.js"></script>
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
            <div class="group_buy">
                <div class="group_searchBox">
                    <div class="searchBox_title">
                        <h3>音乐活动</h3>
                    </div>
                    <!-- <div class="searchBox_search">
                        <select name="">
                            <option value="">全部</option>
                            <option value="">小提琴</option>
                            <option value="">大提琴</option>
                            <option value="">钢琴</option>
                        </select>
                    </div> -->
                </div>
                
                <div class="group_list">
                    <div class="group_listItem">
                        <a href="active_details.html">
                            <div class="group_imgBox">
                                <img src="images/group01.jpg" />
                                <div class="group_shade"></div>
                            </div>
                                
                            <div class="music_wordBox">
                                <h4>各种各样的乐器产品，长度测试零零落落老地方各种各样的乐器产品，长度测试零零落落老地方</h4>
                            </div>
                        </a>
                    </div>
                    <div class="group_listItem">
                        <a href="active_details.html">
                            <div class="group_imgBox">
                                <img src="images/group01.jpg" />
                                <div class="group_shade"></div>
                            </div>
                                
                            <div class="music_wordBox">
                                <h4>各种各样的乐器产品，长度测试零零落落老地方各种各样的乐器产品，长度测试零零落落老地方</h4>
                            </div>
                        </a>
                    </div>
                    <div class="group_listItem">
                        <a href="active_details.html">
                            <div class="group_imgBox">
                                <img src="images/group01.jpg" />
                                <div class="group_shade"></div>
                            </div>
                                
                            <div class="music_wordBox">
                                <h4>各种各样的乐器产品，长度测试零零落落老地方各种各样的乐器产品，长度测试零零落落老地方</h4>
                            </div>
                        </a>
                    </div>
                    <div class="group_listItem">
                        <a href="active_details.html">
                            <div class="group_imgBox">
                                <img src="images/group01.jpg" />
                                <div class="group_shade"></div>
                            </div>
                                
                            <div class="music_wordBox">
                                <h4>各种各样的乐器产品，长度测试零零落落老地方各种各样的乐器产品，长度测试零零落落老地方</h4>
                            </div>
                        </a>
                    </div>
                    <div class="group_listItem">
                        <a href="active_details.html">
                            <div class="group_imgBox">
                                <img src="images/group01.jpg" />
                                <div class="group_shade"></div>
                            </div>
                                
                            <div class="music_wordBox">
                                <h4>各种各样的乐器产品，长度测试零零落落老地方各种各样的乐器产品，长度测试零零落落老地方</h4>
                            </div>
                        </a>
                    </div>
                    <div class="group_listItem">
                        <a href="active_details.html">
                            <div class="group_imgBox">
                                <img src="images/group01.jpg" />
                                <div class="group_shade"></div>
                            </div>
                                
                            <div class="music_wordBox">
                                <h4>各种各样的乐器产品，长度测试零零落落老地方各种各样的乐器产品，长度测试零零落落老地方</h4>
                            </div>
                        </a>
                    </div>
                </div>

                <div class="pages text_center" id="pageDiv">
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

        <!-- footer -->
        <jsp:include page="/WEB-INF/web/common/footer.jsp" />
        
         <form id="pageForm" action="<%=basePath %>order/orderList/orderList.shtml" method=GET>
		 	<input type="hidden" name="orderflag" value="${orderflag}" />
		 	<input type="hidden" id="pageNo" name="pageNostr" value="${page.pageno+1}" />
		 	<input type="hidden" id="pageSize" name="pageSizestr" value="${page.pagesize }" />
		</form>
        
        <script type="text/javascript">
	        laypage({
			    cont: $('#pageDiv'), //容器。值支持id名、原生dom对象，jquery对象,
			    pages: ${page.totalpage}, //总页数
			    curr: ${page.pageno+1},
			    skip: false, //是否开启跳页
			    groups: 5, //连续显示分页数
			    first: '首页', //若不显示，设置false即可
			    last: '尾页', //若不显示，设置false即可
			    prev: '<', //若不显示，设置false即可
			    next: '>', //若不显示，设置false即可
			    jump: function(obj){
			    	if(document.readyState == "complete" || document.readyState == "COMPLETE"){
			    		if(obj.curr > ${page.totalpage}){//判断下一页是否有效
			    			return;
			    		}
			    		$("#pageNo").val(obj.curr);
				    	$("#pageForm").submit();
			    	}
			    }
			});
	 	</script>
        
    </body>
</html>