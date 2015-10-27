package com.qushop.prod.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.prod.entity.Product_ext_new;
import com.qushop.prod.service.Product_ext_newService;



@RequestMapping("/manage/newProduct")
@Controller
public class MNewProductController {

	@Resource
	Product_ext_newService newService;
	
	
	@RequestMapping("addNewProduct.do")
	@ResponseBody
	public Object addNewProduct(Product_ext_new ext_new,HttpServletRequest request){
		
		String productId = request.getParameter("product.productId");
		String productName = request.getParameter("product.productName");
		boolean bool=false;
		int count = Integer.parseInt(newService.getNewProductByMethod(3, productId).get(0)+"");
		if(count>0){
			return DwzUtil.opFailed("此新品已经存在，不能重复添加", "");
		}
		List<Product_ext_new> ext_newsList = newService.getNewProductByMethod(2, productId);
		if(ext_newsList!=null && ext_newsList.size()>0){
			Product_ext_new ext_news = ext_newsList.get(0);
			ext_news.setValidflag((short)1);
			ext_news.setLastUpdateTime(new Date());
			ext_news.setOperid(PublicUtil.getOper(request).getOperId());
			ext_news.setProductName(productName);
			bool = newService.updateProduct(ext_news);
		}else{
			ext_new.setProductId(productId);
			ext_new.setValidflag((short) 1);
			ext_new.setLastUpdateTime(new Date());
			ext_new.setOperid(PublicUtil.getOper(request).getOperId());
			ext_new.setProductName(productName);
			bool = newService.addNewProduct(ext_new);
		}
		if(bool){
			return DwzUtil.opSuccess("操作成功", "newProduct");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("updateNewProduct.do")
	@ResponseBody
	public Object updateNewProduct(Product_ext_new ext_new,HttpServletRequest request){

		String productId = request.getParameter("product.productId");
		boolean bool = false;
		ext_new.setProductId(productId);
		ext_new.setLastUpdateTime(new Date());
		ext_new.setValidflag((short) 1);
		ext_new.setOperid(PublicUtil.getOper(request).getOperId());
		bool = newService.updateProduct(ext_new);
		if(bool){
			return DwzUtil.opSuccess("操作成功", "newProduct");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("newProductList.do")
	public String newProductList(HttpServletRequest request){
		List<Product_ext_new> product_ext_newsList = newService.getNewProductByMethod(0);
		request.setAttribute("newProductList", product_ext_newsList);
		return "admin/newProductList";
	}
	
	@RequestMapping("toAddNewProduct.do")
	public String toAddNewProduct(HttpServletRequest request){
		request.setAttribute("action", "add");
		return "admin/editNewProduct";
	}
	@RequestMapping("toEditNewProduct.do")
	public String toEditNewProduct(String productId,HttpServletRequest request){
		
		List<Product_ext_new> ext_newsList = newService.getNewProductByMethod(1, productId);
		if(ext_newsList!=null && ext_newsList.size()>0){
			request.setAttribute("newProduct", ext_newsList.get(0));
		}
		request.setAttribute("action", "update");
		return "admin/editNewProduct";
	}
	
	@RequestMapping("deleteNewProduct.do")
	@ResponseBody
	public Object deleteNewProduct(String productId,HttpServletRequest request){
		
		boolean bool= newService.deleteNewProduct(productId,PublicUtil.getOper(request).getOperId());
		
		if(bool){
			return DwzUtil.opSuccess("操作成功", "newProduct");
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
	
}
