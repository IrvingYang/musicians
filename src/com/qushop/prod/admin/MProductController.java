package com.qushop.prod.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.dict.service.Brand_vendorService;
import com.qushop.dict.service.StateService;
import com.qushop.prod.entity.Product;
import com.qushop.prod.entity.ProductImg;
import com.qushop.prod.service.ProductImgService;
import com.qushop.prod.service.ProductService;
import com.qushop.prod.service.ProductTypeService;
import com.qushop.prod.service.Product_ext_bigDealService;
import com.qushop.prod.service.Product_ext_groupBuyService;
import com.qushop.prod.service.Product_ext_shopService;


@Controller
@RequestMapping("/manage/product")
public class MProductController {

	@Resource
	ProductService productService;
	
	@Resource
	StateService stateService;
	
	@Resource
	Brand_vendorService brand_vendorService;
	
	@Resource
	ProductTypeService productTypeService;
	
	@Resource
	Product_ext_shopService shopService;
	
	@Resource
	ProductImgService productImgService;
	
	@Resource
	Product_ext_bigDealService bigDealService;
	
	@Resource
	Product_ext_groupBuyService groupBuyService;
	
	
	@RequestMapping("productList.do")
	public String productList(Integer typeId,String action,HttpServletRequest request){
		if("dialog".equals(action)){
			List<Product> productsList = productService.getAllowProducts(typeId,PublicUtil.getOper(request).getProviderId());
			request.setAttribute("productsList", productsList);
			return "admin/lookDialog/lookProduct";
		}else{

			List<Product> productsList = productService.getProductListByMethod(1, PublicUtil.getOper(request));
			request.setAttribute("productsList", productsList);
			return "admin/productList";
		}
	}
	
	@RequestMapping("toAddProduct.do")
	public String toAddProduct(HttpServletRequest request){
		request.setAttribute("action", "add");
		return "admin/editProduct";
	}
	
	@RequestMapping("updateProduct.do")
	@ResponseBody
	public Object editProduct(Product product,String[] image,String propertyName,String[] property,HttpServletRequest request) throws IOException, ParseException{
		
		if(!product.getProviderId().equals(PublicUtil.getOper(request).getProviderId())){
			return DwzUtil.opFailed("操作失败,不能修改合作社商品", "product");
		}
		
		String groupbigdealimg = request.getParameter("groupbigdealimg");
		if(image==null || image.length<=0){
			return DwzUtil.opFailed("产品图片不能为空", "product");
		}
		if(groupbigdealimg==null || groupbigdealimg.equals(""))
		{
			return DwzUtil.opFailed("团购（大宗交易）图片不能为空", "product");
		}
		String cityId = request.getParameter("city.cityId");
		String productTypdId = request.getParameter("productType.productTypeId");
		String brandid = request.getParameter("vendor.brandid");
		String ontimes = request.getParameter("ontimes");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-HH HH:mm:ss");
		
		StringBuffer propertiest = new StringBuffer("[");
		String[] propName = propertyName.split(",");
		for (int i = 0; i < propName.length; i++) {
			propertiest.append("{propertyName:\""+propName[i]+"\",propertyValue:\""+property[i]+"\"},");
		}
		
		product.setOnTime(format.parse(ontimes));
		propertiest.substring(0, propertiest.lastIndexOf(","));
		propertiest.append("]");
		product.setAttribute(propertiest.toString());
		product.setProductCityId(cityId);
		product.setProductTypeId(productTypdId);
		product.setBrandid(brandid);
		
		boolean bool = productService.addorupdate(1, image, product, request);
		if(bool){
			return DwzUtil.opSuccess("操作成功", "product");
		}
		return DwzUtil.opFailed("操作失败", "product");
	}
	
	@RequestMapping("saveProduct.do")
	@ResponseBody
	public Object saveProduct(Product product,String[] image,String propertyName,String[] property,HttpServletRequest request){
		
		String groupbigdealimg = request.getParameter("groupbigdealimg");
		if(image==null || image.length<=0){
			return DwzUtil.opFailed("产品图片不能为空", "product");
		}
		if(groupbigdealimg==null || groupbigdealimg.equals(""))
		{
			return DwzUtil.opFailed("团购（大宗交易）图片不能为空", "product");
		}
		product.setProductGrade((short)4);
		String cityId = request.getParameter("city.cityId");
		String productTypdId = request.getParameter("productType.productTypeId");
		String brandid = request.getParameter("vendor.brandid");
		
		if(propertyName!=null && !propertyName.equals("")){
			StringBuffer propertiest = new StringBuffer("[");
			String[] propName = propertyName.split(",");
			for (int i = 0; i < propName.length; i++) {
				propertiest.append("{propertyName:\""+propName[i]+"\",propertyValue:\""+property[i]+" "+"\"},");
			}
			propertiest.substring(0, propertiest.lastIndexOf(","));
			propertiest.append("]");
			product.setAttribute(propertiest.toString());
		}
		product.setProductCityId(cityId);
		product.setProductTypeId(productTypdId);
		product.setBrandid(brandid);
		product.setProviderId(PublicUtil.getOper(request).getProviderId());
		
		boolean bool = productService.addorupdate(0, image, product, request);
		if(bool){
			return DwzUtil.opSuccess("操作成功", "product");
		}
		return DwzUtil.opFailed("操作失败", "product");
	}
	
	@RequestMapping("toEditPage.do")
	public Object toEditPage(String productId,HttpServletRequest request){

		List<Product> productsList =  productService.getProductListByMethod(2, null, productId);
		if(productsList!=null && productsList.size()>0){
			List<ProductImg> imgsList = productImgService.getProductImgByMethod(1, productsList.get(0).getProductId(),"1");
			List<ProductImg> imgsList1 = productImgService.getProductImgByMethod(1, productsList.get(0).getProductId(),"5");
			if(imgsList1.size()>0){
				request.setAttribute("productimg", imgsList1.get(0));
			}
			productsList.get(0).setProductimglist(imgsList);
			request.setAttribute("product", productsList.get(0));
		}
		request.setAttribute("action", "update");
		return "admin/editProduct";
	}
	
	@RequestMapping("deleteProduct.do")
	@ResponseBody
	public Object deleteProduct(String productId,HttpServletRequest request){
		System.out.println(productId);
		if(shopService.existsExtShopProductInProductId(productId)){
			return DwzUtil.opFailed("选中商品含有上架商品，拒绝操作", "");
		}
		if(bigDealService.getbigDealProductByMethod(6, productId).size()>0){
			return DwzUtil.opFailed("选中商品含有大宗交易商品，拒绝操作", "");
		}
		if(groupBuyService.getgroupBuyProductByMethod(6, productId).size()>0){
			return DwzUtil.opFailed("选中商品含有团购商品，拒绝操作", "");
		}
		boolean bool = productService.deleteProduct(productId,PublicUtil.getOper(request));
		if(bool){
			return DwzUtil.opSuccess("操作成功", "product");
		}
		return DwzUtil.opFailed("操作失败", "product");
		
	}
	
}
