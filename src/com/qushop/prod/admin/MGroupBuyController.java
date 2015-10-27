package com.qushop.prod.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.qushop.order.service.Order_listService;
import com.qushop.prod.entity.ProductImg;
import com.qushop.prod.entity.Product_ext_groupBuy;
import com.qushop.prod.service.ProductImgService;
import com.qushop.prod.service.Product_ext_groupBuyService;
import com.qushop.user.entity.Oper;


@Controller
@RequestMapping("/manage/groupbuy")
public class MGroupBuyController {

	@Resource
	Product_ext_groupBuyService buyService;

	@Resource
	ProductImgService imgService;
	
	@Resource
	Order_listService orderService;
	
	
	@RequestMapping("getAllProductGroupBuy.do")
	public String getAllProductGroupBuy(String action,HttpServletRequest request){
		
		Oper oper = PublicUtil.getOper(request);
		
		List<Product_ext_groupBuy> groupBuysList = buyService.getgroupBuyProductByMethod(4,oper.getPartnerflag()+"",oper.getProviderId()+"");
		
		request.setAttribute("groupBuysList", groupBuysList);
		if("dialog".equals(action)){
			return "admin/lookDialog/lookProductGroupbuy";
		}
		return "admin/productGroupbuyList";
		
	}
	
	@RequestMapping("toAddGroupBuy.do")
	public String toAddGroupBuy(HttpServletRequest request){
		request.setAttribute("action", "add");
		return "admin/editProductGroupBuy";
	}
	
	@RequestMapping("toEditGroupBuy.do")
	public String toEditGroupBuy(String[] groupbuyId,HttpServletRequest request){

		request.setAttribute("action", "update");
		List<Product_ext_groupBuy> groupBuysList = buyService.getgroupBuyProductByMethod(3, groupbuyId[0],"1");
		if(groupBuysList!=null && groupBuysList.size()>0){
			request.setAttribute("groupbuy", groupBuysList.get(0));
		}
		request.setAttribute("action", "update");
		return "admin/editProductGroupBuy";
	}
	
	@RequestMapping("deleteGroupBuy.do")
	@ResponseBody
	public Object deleteGroupBuy(String groupbuyId[],HttpServletRequest request){
		
		List list = orderService.getOrder_listByMethod(PublicUtil.getOper(request),6,null,groupbuyId);
		if(list!=null && list.size()>0){
			return DwzUtil.opFailed("此团购已经含有未完成的订单，不能删除", "");
		}
		boolean bool = buyService.deleteProduct_ext_group(groupbuyId[0],request);
		if(bool){
			return DwzUtil.opSuccess("操作成功", "groupbuy");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("updateGroupBuy.do")
	@ResponseBody
	public Object updateGroupBuy(Product_ext_groupBuy groupBuy,HttpServletRequest request) throws ParseException{

		String productId = request.getParameter("groupbuy.productId");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String img = request.getParameter("img");
		String groupBuyType = request.getParameter("groupBuyType");
		if(groupBuyType.equals("1")){
			groupBuy.setOpenresult((short) 1);
		}
		groupBuy.setProductId(productId);
		groupBuy.setGroupBuyStartTime(UtilDate.parseDateForDatepatternString(startTime));
		groupBuy.setGroupBuyEndTime(UtilDate.parseDateForDatepatternString(endTime));
		groupBuy.setOperid(PublicUtil.getOper(request).getOperId());
		groupBuy.setValidflag((short)1);
		groupBuy.setLastUpdateTime(new Date());
		
		boolean bool = buyService.updateProduct_ext_group(groupBuy);
		if(bool){
			return DwzUtil.opSuccess("操作成功", "groupbuy");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("addGroupBuy.do")
	@ResponseBody
	public Object addGroupBuy(Product_ext_groupBuy groupBuy,HttpServletRequest request) throws ParseException, IOException{
		
		String productId = request.getParameter("groupbuy.productId");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		
		List<ProductImg> imgsList = imgService.getProductImgByMethod(2,productId);
		if(imgsList==null || imgsList.size()<=0){
			return DwzUtil.opFailed("无团购图片，请添加该商品相关图片", "");
		}
		String groupBuyType = request.getParameter("groupBuyType");
		if(groupBuyType.equals("1")){
			groupBuy.setOpenresult((short) 1);
		}
		groupBuy.setProductId(productId);
		groupBuy.setGroupBuyStartTime(UtilDate.parseDateForDatepatternString(startTime));
		groupBuy.setGroupBuyEndTime(UtilDate.parseDateForDatepatternString(endTime));
		groupBuy.setOperid(PublicUtil.getOper(request).getOperId());
		groupBuy.setValidflag((short)1);
		groupBuy.setLastUpdateTime(new Date());
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		groupBuy.setGroupbuyid(groupBuy.getProductId()+format.format(groupBuy.getGroupBuyStartTime()));
		List<Product_ext_groupBuy> gList = buyService.getgroupBuyProductByMethod(5, groupBuy.getGroupbuyid());
		boolean bool = false;
		if(gList!=null && gList.size()>0)
		{
			Product_ext_groupBuy ext_groupBuy = gList.get(0);
			if(ext_groupBuy.getValidflag()==(short)1)
			{
				return DwzUtil.opFailed("已经有此次团购信息", "");
			}
			ext_groupBuy.setValidflag((short)1);
			bool = buyService.updateProduct_ext_group(ext_groupBuy);
		}
		else
		{
			bool = buyService.addProduct_ext_group(groupBuy);
		}
		if(bool){
			return DwzUtil.opSuccess("操作成功", "groupbuy");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	

	@RequestMapping("openGroupBuy.do")
	@ResponseBody
	public Object openGroupBuy(String[] groupbuyId,Integer statusId,HttpServletRequest request){

		List<Product_ext_groupBuy> groupBuysList = buyService.getgroupBuyProductByMethod(3, groupbuyId[0],"1");
		if(groupBuysList!=null && groupBuysList.size()>0){
			Product_ext_groupBuy groupBuy = groupBuysList.get(0);
			if(groupBuy.getOpenresult()!=null){
				return DwzUtil.opFailed("该团购已经发团或者取消发团，拒绝操作", "");
			}
		}
		boolean bool = buyService.openGroupBuy(groupbuyId[0], statusId, request);
		if(bool){
			return DwzUtil.opSuccess("操作成功", "groupbuy");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
}













