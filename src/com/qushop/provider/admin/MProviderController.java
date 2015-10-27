package com.qushop.provider.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.dict.entity.Location;
import com.qushop.dict.service.LocationService;
import com.qushop.order.service.Order_listService;
import com.qushop.prod.service.ProductService;
import com.qushop.provider.entity.Provider;
import com.qushop.provider.entity.Provider_image;
import com.qushop.provider.service.ProviderImgService;
import com.qushop.provider.service.ProviderService;



@Controller
@RequestMapping("/manage/provider")
public class MProviderController {

	@Resource
	ProviderService providerService;
	
	@Resource
	ProviderImgService providerImgService;
	
	@Resource
	ProductService productService;
	
	@Resource
	LocationService locationService;
	
	@Resource
	Order_listService order_listService;
	
	
	
	@RequestMapping("getProviderList.do")
	public String getProviderList(String action,HttpServletRequest request){
		
		List<Provider> providersList = providerService.getProviderByMethod(0,PublicUtil.getOper(request));
		request.setAttribute("providersList", providersList);
		if("dialog".equals(action)){
			return "admin/lookDialog/lookUpProvider";
		}
		return "admin/providerList";
	}
	
	@RequestMapping("toEditProvider.do")
	public String toEditProvider(String[] providerId,HttpServletRequest request){
		
		List<Provider> providersList = providerService.getProviderByMethod(1,PublicUtil.getOper(request),providerId[0]);
		List<Provider_image> images = providerImgService.getProviderImageByMethod(2, providerId[0]);
		for (Provider_image provider_image : images) {
			request.setAttribute("imageURl"+provider_image.getImgType(), provider_image.getImagepath());
		}
		request.setAttribute("provider", providersList.get(0));
		request.setAttribute("action", "update");
		return "admin/editProvider";
	}
	
	@RequestMapping("saveProvider.do")
	@ResponseBody
	public Object addProvider(HttpServletRequest request,Location location,Provider provider){
		
		String stateId = request.getParameter("cityLookUp.stateId");
		String cityId = request.getParameter("cityLookUp.cityId");
		provider.setStateId(stateId);
		provider.setCityId(cityId);
		provider.setLastUpdateTime(new Date());
		provider.setOperid(PublicUtil.getOper(request).getOperId());
		boolean bool = providerService.addProvider(provider,request);

		location.setEntitytype("01");
		location.setLocationName(request.getParameter("cityLookUp.stateName")+request.getParameter("cityLookUp.cityName"));
		location.setEntityid(provider.getProviderId());

		locationService.addLocation(location);
		
		if(bool){
			return DwzUtil.opSuccess("操作成功", "provider");
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
	
	
	@RequestMapping("toAddProvider.do")
	public String toAddProvider(HttpServletRequest request){

		request.setAttribute("action", "add");
		
		return "admin/editProvider";
		
	}
	
	@RequestMapping("deleteProvider.do")
	@ResponseBody
	public Object deleteProvider(String providerId,HttpServletRequest request){
		
		/**
		 * 未来可能添加判断是否存在旅游板块产品
		 */
		List list = productService.getProductListByMethod(3, null, providerId);
		if(list!=null && list.size()>0){
			return DwzUtil.opFailed("该合作社下含有产品，操作拒绝", "");
		}
		if(order_listService.getOrder_listByMethod(PublicUtil.getOper(request), 13, providerId).size()>0){
			return DwzUtil.opFailed("该合作社下含有未完成订单，操作拒绝", "");
		}
		boolean bool = false;
		bool = providerService.deleteProvider(providerId,request);
		if(bool){
			return DwzUtil.opSuccess("操作成功", "provider");
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
	
	@RequestMapping("updateProvider.do")
	@ResponseBody
	public Object updateProvider(Provider provider,Location location,HttpServletRequest request){
		
		providerImgService.deleteProviderImgByProviderIdPhysically(provider.getProviderId());
		String stateId = request.getParameter("cityLookUp.stateId");
		String cityId = request.getParameter("cityLookUp.cityId");
		provider.setStateId(stateId);
		provider.setCityId(cityId);
		provider.setLastUpdateTime(new Date());
		provider.setOperid(PublicUtil.getOper(request).getOperId());
		
		boolean bool = providerService.update(provider,request);

		location.setEntitytype("01");
		location.setLocationName(request.getParameter("cityLookUp.stateName")+request.getParameter("cityLookUp.cityName"));
		location.setEntityid(provider.getProviderId());
		
		locationService.updateLocation(location);
		
		if(bool){
			return DwzUtil.opSuccess("操作成功", "provider");
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
	
	
}





