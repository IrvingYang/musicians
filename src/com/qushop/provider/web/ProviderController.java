package com.qushop.provider.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.MapTemp;
import com.qushop.prod.entity.Product;
import com.qushop.prod.service.ProductService;
import com.qushop.provider.entity.Provider;
import com.qushop.provider.service.ProviderImgService;
import com.qushop.provider.service.ProviderService;



@Controller
@RequestMapping("/provider/provider")
public class ProviderController {

	@Resource
	ProviderService providerService;
	
	@Resource
	ProviderImgService providerImgService;
	
	@Resource
	ProductService productService;
	
	@RequestMapping("getAllProvider.action")
	@ResponseBody
	public Object getAllProvider(Integer maxCount,HttpServletRequest request){
		
		List<Provider> providersList = providerService.getProviderByMethod(0,null);
		List<MapTemp> mapTempsList = new ArrayList<MapTemp>();
		if(providersList.size()>0){
			for (Provider provider : providersList) {
				MapTemp map = new MapTemp();
				map.setAddress(provider.getCity().getState().getStateName()+provider.getCity().getCityName()+provider.getStreet());
				map.setPoint(provider.getLocation().getLongitude()+","+provider.getLocation().getLatitude());
				map.setTel(provider.getTelephone());
				map.setTitle(provider.getName());
				mapTempsList.add(map);
			}
			
		}
		Map<String,Object> dmap = new HashMap<String,Object>();
		dmap.put("providerList", providersList);
		dmap.put("mapList", mapTempsList);
		return dmap;
		
	}
	
	@RequestMapping("getProviderDetail.action")
	@ResponseBody
	public Object getProviderDetail(String providerId,HttpServletRequest request,HttpServletResponse response){
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		List<Provider> providers = providerService.getProviderByMethod(1,null, providerId);
		if(providers.size()>0){
			providers.get(0).setProvider_image(providerImgService.getProviderImageByMethod(1,providerId, "2"));
			List<Product> productsList = productService.getProductListByMethod(3, null, providerId);
			providers.get(0).setProductList(productsList);
			return providers.get(0);
		}
		return null;		
	}
	
	@RequestMapping("getProviderProductdetail.action")
	@ResponseBody
	public Object getProviderProductdetail(String productId,HttpServletRequest request){
		
		List<Product> products = providerService.getProviderProductByMethod(0, productId);
		if(products!=null){
			return products.get(0);
		}
		return null;
	}
	
}
