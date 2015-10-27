<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="accordion" fillSpace="sideBar">
	<div class="accordionHeader">
		<h2><span>Folder</span>商品管理</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			
			<li><a href="manage/productType/getAllProductType.do" target="navTab" rel="productType">商品类型</a></li>
			<li><a href="manage/product/productList.do" target="navTab" rel="product">商品管理</a></li>
			<li><a href="manage/productshop/queryAllProductShop.do?typeId=0" target="navTab" rel="productShop">商城商品</a></li>
			<li><a href="manage/newProduct/newProductList.do" target="navTab" rel="newProduct">最新商品</a></li>
			<li><a href="manage/recommendProduct/recommendProductList.do" target="navTab" rel="recommendProduct">推荐商品</a></li>
			<li><a href="manage/promote/getAllPromoteProduct.do" target="navTab" rel="promote">商品促销</a></li>
			<li><a href="manage/relation/getAllRelationProduct.do" target="navTab" rel="relation">关联商品</a></li>
			<li><a href="manage/review/getAllProductReview.do" target="navTab" rel="review">商品评价</a></li>
			
		</ul>
	</div>
</div>
