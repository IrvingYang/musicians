package com.qushop.prod.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.prod.entity.ProductType;
import com.qushop.prod.service.ProductService;
import com.qushop.prod.service.ProductTypeService;


@Controller
@RequestMapping("/manage/productType")
public class MProductTypeController {

	@Resource
	ProductTypeService productTypeService;
	
	@Resource
	ProductService productService;
	
	
	
	@ResponseBody
	@RequestMapping("getProductTypeDetail.do")
	public Object getProductTypeDetail(String productTypeId){
		
		List<ProductType> productTypesList = productTypeService.getProductTypeByMethod(0, productTypeId);
		if(productTypesList!=null && productTypesList.size()>0){
			ProductType productType = productTypesList.get(0);
			return productType;
		}
		return null;
	}
	
	
	
	@RequestMapping("getAllProductType.do")
	public ModelAndView getAllProductType(String action,Integer level,HttpServletRequest request){
		
		List productTypeList = null;
		ModelAndView mv = null;
		if("dialog".equals(action)){
			productTypeList = productTypeService.getProductTypeByMethod(3, (level*2)+"");
			mv = new ModelAndView("admin/lookDialog/lookThirdProductType");
			mv.addObject("productTypes", productTypeList);
		}else{
			productTypeList = productTypeService.getProductTypeByMethod(7);
			mv = new ModelAndView("admin/productTypeList");
			mv.addObject("productTypes", productTypeList);
		}
		return mv;
		
	}
	@RequestMapping("toAddProductType.do")
	public ModelAndView toAddProduct(String productTypeId){
		
		ModelAndView mv = new ModelAndView("admin/editProductType");
		List<ProductType> productTypes = productTypeService.getProductTypeByMethod(7);
		mv.addObject("productTypeList", productTypes);
		mv.addObject("action", "add");
		return mv;
	}
	@RequestMapping("toEditProductType.do")
	public ModelAndView toEditProductType(String productTypeId[]){
		
		ModelAndView mv = new ModelAndView("admin/editProductType");
		ProductType productType = productTypeService.getProductTypeByMethod(0, productTypeId[0]).get(0);
		String properties = productType.getProperties();
		if(properties!=null && !properties.trim().equals("")){
			productType.setProperties(properties.replace(",", "\n"));
		}
		mv.addObject("productType", productType);
		mv.addObject("action", "update");
		return mv;
	}
	
	@RequestMapping("updateProductType.do")
	@ResponseBody
	public Object update(ProductType productType,HttpServletRequest request){
		String properties = request.getParameter("properties");
		String parentId = request.getParameter("productType.productTypeId");
		if(properties!=null && !properties.trim().equals("")){
			properties = properties.replace("\n", ",");
			properties = properties.replace("\r", "");
			properties = properties.replace(" ", "");
			productType.setProperties(properties);
		}
		if(parentId!=null && !parentId.equals("")){
			productType.setParentId(parentId);
		}
		productType.setLastUpdateTime(new Date());
		productType.setOperid(PublicUtil.getOper(request).getOperId());
		boolean	bool = productTypeService.updateProductType(productType);
		if(bool){
			return DwzUtil.opSuccess("操作成功", "productType");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("saveProductType.do")
	@ResponseBody
	public Object save(ProductType productType,HttpServletRequest request){
		
		String properties = request.getParameter("properties");
		String parentId = request.getParameter("productType.productTypeId");
		
		if(properties!=null && !properties.trim().equals("")){
			properties = properties.replace("\n", ",");
			properties = properties.replace("\r", "");
			properties = properties.replace(" ", "");
			productType.setProperties(properties);
		}
		if(parentId!=null && !parentId.equals("")){
			productType.setParentId(parentId);
		}

//		List<ProductType> typesList = productTypeService.getProductTypeByMethod(4, productType.getTypeName());
//		ProductType type = null;
		boolean bool = false;
//		if(typesList!=null && typesList.size()>0){
//			type = typesList.get(0);
//			type.setDescription(productType.getDescription());
//			type.setLastUpdateTime(new Date());
//			type.setLogImagePath(productType.getLogImagePath());
//			type.setOperid(PublicUtil.getOper(request).getOperId());
//			type.setParentId(productType.getParentId());
//			type.setTypeName(productType.getTypeName());
//			type.setValidflag((short)1);
//			type.setProperties(productType.getProperties());
//			bool = productTypeService.updateProductType(type);
//		}else{
			productType.setLastUpdateTime(new Date());
			productType.setOperid(PublicUtil.getOper(request).getOperId());
			bool = productTypeService.addProductType(productType);
//		}
		
		if(bool){
			return DwzUtil.opSuccess("操作成功", "productType");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	
	@RequestMapping("deleteProductType.do")
	@ResponseBody
	public Object deleteProductType(String productTypeId,HttpServletRequest request){
		
		if(productTypeService.existsChildrenProductOfProductTypeId(productTypeId)){
			return DwzUtil.opFailed("选中类型下含有子类型，操作拒绝", "");
		}
		else if(productService.existsProductInProductType(productTypeId)){
			return DwzUtil.opFailed("选中类型下含有商品信息，操作拒绝", "");
		}
		boolean bool = productTypeService.deleteProductType(productTypeId,PublicUtil.getOper(request).getOperId());
		if(bool){
			return DwzUtil.opSuccess("操作成功", "productType");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
}
