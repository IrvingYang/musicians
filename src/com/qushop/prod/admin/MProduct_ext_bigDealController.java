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
import com.qushop.common.util.UtilDate;
import com.qushop.prod.entity.ProductImg;
import com.qushop.prod.entity.Product_ext_bigDeal;
import com.qushop.prod.service.ProductImgService;
import com.qushop.prod.service.Product_ext_bigDealService;
import com.qushop.user.entity.Oper;

@RequestMapping("/manage/productbigdeal")
@Controller
public class MProduct_ext_bigDealController {

	@Resource
	Product_ext_bigDealService bigDealService;
	
	@Resource
	ProductImgService imgService;

	@RequestMapping("getAllBigDeal.do")
	public String getAllBigDeal(HttpServletRequest request) {

		Oper oper = PublicUtil.getOper(request);
		
		List<Product_ext_bigDeal> bigDealsList = bigDealService.getbigDealProductByMethod(4,oper.getPartnerflag()+"",oper.getProviderId());
		
		request.setAttribute("bigDealsList", bigDealsList);
		
		return "admin/productBigDealList";
	}

	@RequestMapping("toAddBigDeal.do")
	public String toAddBigDeal(HttpServletRequest request) {
		request.setAttribute("action", "add");
		return "admin/editProductBigDeal";
	}

	@RequestMapping("toEditBigDeal.do")
	public String toEditBigDeal(String[] bigDealId, HttpServletRequest request) {
		request.setAttribute("action", "update");
		List<Product_ext_bigDeal> bigDealsList = bigDealService.getbigDealProductByMethod(3,bigDealId[0],"1");
		if(bigDealsList!=null && bigDealsList.size()>0){
			request.setAttribute("bigdeal", bigDealsList.get(0));
		}
		return "admin/editProductBigDeal";
	}

	@RequestMapping("deleteBigDeal.do")
	@ResponseBody
	public Object deleteBigDeal(String bigDealId, HttpServletRequest request) {

		boolean bool = bigDealService.deleteProductBigDeal(bigDealId, request);

		if(bool){
			return DwzUtil.opSuccess("操作成功", "bigdeal");
		}
		return DwzUtil.opFailed("操作失败", "");
	}

	@RequestMapping("addBigDeal.do")
	@ResponseBody
	public Object addBigDeal(Product_ext_bigDeal bigDeal,
			HttpServletRequest request) {
		
		String productId = request.getParameter("bigdeal.productId");
		List<ProductImg> imgsList = imgService.getProductImgByMethod(2,productId);
		if(imgsList==null || imgsList.size()<=0){
			return DwzUtil.opFailed("大宗交易图片，请添加该商品相关图片", "");
		}
		bigDeal.setBigdealId(productId+UtilDate.getNowDateNo_());
		bigDeal.setProductId(productId);
		bigDeal.setOperid(PublicUtil.getOper(request).getOperId());
		bigDeal.setLastUpdateTime(new Date());
		bigDeal.setValidflag((short)1);
		
		List<Product_ext_bigDeal> bList = bigDealService.getbigDealProductByMethod(3, bigDeal.getBigdealId(),"1");
		boolean bool = false;
		if(bList!=null && bList.size()>0)
		{
			Product_ext_bigDeal ext_bigDeal = bList.get(0);
			if(ext_bigDeal.getValidflag()==(short)1)
			{
				return DwzUtil.opFailed("已经有此次大宗交易信息", "");
			}
			ext_bigDeal.setValidflag((short)1);
			bool = bigDealService.updateProductBigDeal(ext_bigDeal);
		}
		else
		{
			bool = bigDealService.addProductBigDeal(bigDeal);
		}
		if(bool){
			return DwzUtil.opSuccess("操作成功", "bigdeal");
		}
		return DwzUtil.opFailed("操作失败", "");
	}

	@RequestMapping("updateBigDeal.do")
	@ResponseBody
	public Object updateBigDeal(Product_ext_bigDeal bigDeal,
			HttpServletRequest request) {
		
		String productId = request.getParameter("bigdeal.productId");
		
		bigDeal.setProductId(productId);
		bigDeal.setOperid(PublicUtil.getOper(request).getOperId());
		bigDeal.setLastUpdateTime(new Date());
		bigDeal.setValidflag((short)1);
		boolean bool = bigDealService.updateProductBigDeal(bigDeal);
		if(bool){
			return DwzUtil.opSuccess("操作成功", "bigdeal");
		}
		return DwzUtil.opFailed("操作失败", "");
	}

}
