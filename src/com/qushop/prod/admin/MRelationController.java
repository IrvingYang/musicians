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
import com.qushop.prod.entity.Product_ext_relation;
import com.qushop.prod.service.Product_ext_relationService;
import com.qushop.prod.service.Product_ext_shopService;


@RequestMapping("/manage/relation")
@Controller
public class MRelationController {

	@Resource
	Product_ext_relationService ext_relationService;

	@Resource
	Product_ext_shopService ext_shopService;
	
	
	@RequestMapping("addRelationProduct.do")
	@ResponseBody
	public Object addRelationProduct(Product_ext_relation product_ext_relation,HttpServletRequest request){
		
		String productId1 = request.getParameter("relation1.productId");
		String[] productId2s = request.getParameter("relation2.productId").split(",");
		
		boolean bool = false;
		for (String productId2 : productId2s) {
			List<Product_ext_relation> ext_relationsList = ext_relationService.getRelationProductByMethod(1, productId1,productId2);
			if(ext_relationsList!=null && ext_relationsList.size()>0){
				Product_ext_relation ext_relation = ext_relationsList.get(0);
				ext_relation.setOperid(PublicUtil.getOper(request).getOperId());
				ext_relation.setValidflag((short)1);
				ext_relation.setLastUpdateTime(new Date());
				bool = ext_relationService.updateProduct_ext_relation(ext_relation);
			}else{
				product_ext_relation.setProductId1(productId1);
				product_ext_relation.setProductId2(productId2);
				product_ext_relation.setLastUpdateTime(new Date());
				product_ext_relation.setValidflag((short)1);
				product_ext_relation.setOperid(PublicUtil.getOper(request).getOperId());
				bool = ext_relationService.addRelation(product_ext_relation);
			}
		}
		if(bool){
			return DwzUtil.opSuccess("操作成功", "relation");
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
	
	@RequestMapping("getAllRelationProduct.do")
	public String getAllRelationProduct(HttpServletRequest request){
		
		List<Product_ext_relation> relationsList = ext_relationService.getRelationProductByMethod(0);
		request.setAttribute("relations", relationsList);
		return "admin/relationProductList";
	}
	
	@RequestMapping("deleteRelationProduct.do")
	@ResponseBody
	public Object deleteRelationProduct(HttpServletRequest request,String[] productId){
		
		boolean bool = ext_relationService.deleteRelationProduct(productId,PublicUtil.getOper(request).getOperId());
		if(bool){
			return DwzUtil.opSuccess("操作成功", "relation");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("toAddRelationProduct.do")
	public String toAddRelationProduct(HttpServletRequest request){
		
		return "admin/addRelationProduct";
	}
	
	public static void main(String[] args) {
		for(int i=0;i<9;i++){
			System.err.print(i);
			for(int j=1;j<4;j++){
				if(i!=0){
					System.err.print(i*j);
				}else{
					System.err.print(i+j);
				}
			}
			System.err.println();
		}
	}
	
}
