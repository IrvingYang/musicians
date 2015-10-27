<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <div class="header_bottomList">
      <div class="content_maxwidth">
          <ul id="drop_item" class="sed_navfst">
              <li class="drop_item">
                  <a href="eshop/shopProduct/shopProduct.shtml?action=list">所有商品</a>
                  <ul class="sed_navItem">
                  
                  	<c:forEach items="${producttypelist}" var="fproduct">
                  		 <li>
                          <span>${fproduct.typeName}</span>
                          <ul class="thr_navItem">
                          	<c:forEach items="${fproduct.childTypeList}" var="sproduct" varStatus="sStatus">
	                              <div class="nav_leftBox">
	                                  <div class="navitem_box">
	                                      <a href="javascript:;" class="item_title">${sproduct.typeName}</a>
	                                      <ul>
                          					 <c:forEach items="${sproduct.childTypeList}" var="tproduct">
	                                          <li><a href="javascript:;">${tproduct.typeName }</a></li>
	                                          </c:forEach>
	                                      </ul>
	                                  </div>
	                              </div>
                              </c:forEach>
<!--                               <div class="nav_rightBox"> -->
<!--                                   <a href="javascript:;" class="item_title">推荐商品</a> -->
<!--                                   <div class="many_chose"> -->
<!--                                       <a href="javascript:;">山品</a>| -->
<!--                                       <a href="javascript:;">吉他</a>| -->
<!--                                       <a href="javascript:;">二胡</a>| -->
<!--                                       <a href="javascript:;">手风琴</a>| -->
<!--                                       <a href="javascript:;">钢琴</a>| -->
<!--                                       <a href="javascript:;">小提琴</a>| -->
<!--                                       <a href="javascript:;">大提琴</a>| -->
<!--                                       <a href="javascript:;">手风琴</a>| -->
<!--                                       <a href="javascript:;">古筝</a>| -->
<!--                                       <a href="javascript:;">琵琶</a>| -->
<!--                                       <a href="javascript:;">山品</a>| -->
<!--                                       <a href="javascript:;">吉他</a>| -->
<!--                                       <a href="javascript:;">二胡</a>| -->
<!--                                       <a href="javascript:;">手风琴</a>| -->
<!--                                       <a href="javascript:;">钢琴</a>| -->
<!--                                       <a href="javascript:;">小提琴</a>| -->
<!--                                       <a href="javascript:;">大提琴</a>| -->
<!--                                       <a href="javascript:;">手风琴</a>| -->
<!--                                       <a href="javascript:;">古筝</a>| -->
<!--                                       <a href="javascript:;">琵琶</a> -->
<!--                                   </div> -->
<!--                               </div> -->
                              <div class="navImg">
                                  <a href="href:javascript:;"><img src="resources/images/nav_img01.gif"></a>
                                  <a href="href:javascript:;"><img src="resources/images/nav_img01.gif"></a>
                                  <a href="href:javascript:;"><img src="resources/images/nav_img01.gif"></a>
                              </div>
                          </ul>
                      </li>
                  	</c:forEach>
                  </ul>
              </li>
             <c:choose>
               	<c:when test="${empty groupActivity}">
	              <li><a href="javascript:;">论坛</a></li>
	              <li><a href="javascript:;">教育</a></li>
               	</c:when>
               	<c:when test="${groupActivity eq 'y'}">
	              <li><a href="eshop/groupBuyProduct/groupBuyList.shtml">团购</a></li>
	              <li><a href="javascript:;">音乐活动</a></li>
               	</c:when>
             </c:choose>
              <div class="clear"></div>
          </ul>
      </div>
  </div>