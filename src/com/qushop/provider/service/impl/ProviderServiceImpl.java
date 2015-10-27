package com.qushop.provider.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.Constants;
import com.qushop.common.util.PublicUtil;
import com.qushop.dict.entity.City;
import com.qushop.dict.entity.Location;
import com.qushop.dict.service.CityService;
import com.qushop.dict.service.LocationService;
import com.qushop.dict.service.StateService;
import com.qushop.prod.entity.Product;
import com.qushop.prod.service.ProductImgService;
import com.qushop.prod.service.ProductService;
import com.qushop.provider.entity.Provider;
import com.qushop.provider.entity.Provider_image;
import com.qushop.provider.service.ProviderImgService;
import com.qushop.provider.service.ProviderService;
import com.qushop.user.entity.Oper;
import com.qushop.user.service.OperService;
import com.qushop.user.service.RoleService;


@Service
public class ProviderServiceImpl implements ProviderService {

	@Resource
	CommonDao baseDao;
	
	@Resource
	LocationService locationService;
	
	@Resource
	StateService stateService;
	
	@Resource
	CityService cityService;
	
	@Resource
	ProviderImgService imgService;
	
	@Resource
	ProductService productService;

	@Resource
	ProductImgService productImgService;
	
	@Resource
	RoleService roleService;
	
	@Resource
	OperService operService;
	

	@Override
	public List<Provider> getProviderByMethod(int type,Oper oper, String... params) {
		

		List<Provider> providersList = new ArrayList();
		
		switch (type) {
		case 0:
			if(oper==null || oper.getPartnerflag()==0)
			{
				providersList = baseDao.findByHql("from Provider where providerId<>? and validflag=1 order by providerId desc",Constants.MAIN_PROVIDERID);
			}
			else
			{
				providersList = baseDao.findByHql("from Provider where providerId=? and validflag=1 order by providerId desc",oper.getProviderId());
			}
			break;
		case 1:
			providersList = baseDao.findByHql("from Provider where providerId=?  and superprovider!=1 and validflag = 1", params[0]);
		case 2:
			providersList = baseDao.findByHql("from Provider where providerId=?  and validflag = 1", params[0]);
			break;
		case 3:
			providersList = baseDao.findByHql("from Provider where providerId=?  and validflag = 1", params[0]);
			return providersList;
		case 4:
			providersList = baseDao.findByHql("from Provider where providerId<>? and validflag=1 order by providerId desc",Constants.MAIN_PROVIDERID);
			return providersList;
		case 5:
			providersList = baseDao.findByHql("from Provider where providerId=?", params[0]);
			break;
		}
		for (Provider provider : providersList) {
			List<Provider_image> provider_imagesList = imgService.getProviderImageByMethod(1, provider.getProviderId(),"1");
			List<Location> location = locationService.getLocationByMethod(1, "01",provider.getProviderId());
			List<City> city = cityService.getCityByMethod(4, provider.getCityId());
			if(location!=null && location.size()>0){
				provider.setLocation(location.get(0));
			}
			if(city!=null && city.size()>0){
				provider.setCity(city.get(0));
				provider.setStateId(city.get(0).getStateId());
			}
			provider.setProvider_image(provider_imagesList);
		}
		return providersList;
	}


	@Override
	public boolean addProvider(Provider provider,HttpServletRequest request) {

		String endProviderId = "";
		List<Provider> list = baseDao.findPagingByHql("from Provider where providerId<>? order by providerId desc",0,1,Constants.MAIN_PROVIDERID);
		
		int providerId = 0;
		if(list==null || list.size()<=0){
			providerId = Integer.parseInt(Constants.MAIN_PROVIDERID);
		}else{
			Provider pro = list.get(0);
			providerId = Integer.parseInt(pro.getProviderId());
		}
		
		if(providerId==9999){
			return false;
		}
		providerId++;
		for (int i = (providerId+"").length(); i < 4; i++) {
			endProviderId+="0";
		}
		endProviderId+=providerId;
		provider.setProviderId(endProviderId);
		
		try {
			String image2 = request.getParameter("image1");
			String image1 = request.getParameter("image2");
			String image3 = request.getParameter("image3");
			String image4 = request.getParameter("image4");
			String image5 = request.getParameter("image5");
			Provider_image pimage1 = new Provider_image();
			pimage1.setImageid(endProviderId+"01"+(new Random().nextInt(8999)+1000));
			pimage1.setImagepath(image1);
			pimage1.setProviderId(endProviderId);
			pimage1.setUploadtime(new Date());
			pimage1.setImgType((short)1);
			
			Provider_image pimage2 = new Provider_image();
			pimage2.setImageid(endProviderId+"02"+(new Random().nextInt(8999)+1000));
			pimage2.setImagepath(image2);
			pimage2.setProviderId(endProviderId);
			pimage2.setUploadtime(new Date());
			pimage2.setImgType((short)2);
			
			Provider_image pimage3 = new Provider_image();
			pimage3.setImageid(endProviderId+"03"+(new Random().nextInt(8999)+1000));
			pimage3.setImagepath(image3);
			pimage3.setProviderId(endProviderId);
			pimage3.setUploadtime(new Date());
			pimage3.setImgType((short)3);
			
			Provider_image pimage4 = new Provider_image();
			pimage4.setImageid(endProviderId+"04"+(new Random().nextInt(8999)+1000));
			pimage4.setImagepath(image4);
			pimage4.setProviderId(endProviderId);
			pimage4.setUploadtime(new Date());
			pimage4.setImgType((short)4);
			
			Provider_image pimage5 = new Provider_image();
			pimage5.setImageid(endProviderId+"05"+(new Random().nextInt(8999)+1000));
			pimage5.setImagepath(image5);
			pimage5.setProviderId(endProviderId);
			pimage5.setUploadtime(new Date());
			pimage5.setImgType((short)5);
			
			provider.setProviderimageid(pimage1.getImageid());
			provider.setCertid1imageid(pimage3.getImageid());
			provider.setCertid2imageid(pimage4.getImageid());
			provider.setCertid3imageid(pimage5.getImageid());
			baseDao.insert(provider);
			baseDao.insert(pimage1);
			baseDao.insert(pimage2);
			baseDao.insert(pimage3);
			baseDao.insert(pimage4);
			baseDao.insert(pimage5);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


	@Override
	public boolean deleteProvider(String providerIds,HttpServletRequest request) {
		boolean bool =  baseDao.executeBySql("update tb_provider set validflag=0,lastUpdateTime=?,operid=? where providerId in ("+providerIds+") and validflag=1",new Date(),PublicUtil.getOper(request).getOperId());
		roleService.deleteRoleByProviderId(providerIds);
		operService.deleteOperByProviderId(providerIds);
		locationService.deleteLocationPhysically(providerIds, "01");
		bool = imgService.deleteProviderImgByProviderIdPhysically(providerIds);
		return bool;
	}


	@Override
	public boolean update(Provider provider,HttpServletRequest request) {

		try {
			String endProviderId = provider.getProviderId();
			String image2 = request.getParameter("image1");
			String image1 = request.getParameter("image2");
			String image3 = request.getParameter("image3");
			String image4 = request.getParameter("image4");
			String image5 = request.getParameter("image5");
			Provider_image pimage1 = new Provider_image();
			pimage1.setImageid(endProviderId+"01"+(new Random().nextInt(8999)+1000));
			pimage1.setImagepath(image1);
			pimage1.setProviderId(endProviderId);
			pimage1.setUploadtime(new Date());
			pimage1.setImgType((short)1);
			
			Provider_image pimage2 = new Provider_image();
			pimage2.setImageid(endProviderId+"02"+(new Random().nextInt(8999)+1000));
			pimage2.setImagepath(image2);
			pimage2.setProviderId(endProviderId);
			pimage2.setUploadtime(new Date());
			pimage2.setImgType((short)2);
			
			Provider_image pimage3 = new Provider_image();
			pimage3.setImageid(endProviderId+"03"+(new Random().nextInt(8999)+1000));
			pimage3.setImagepath(image3);
			pimage3.setProviderId(endProviderId);
			pimage3.setUploadtime(new Date());
			pimage3.setImgType((short)3);
			
			Provider_image pimage4 = new Provider_image();
			pimage4.setImageid(endProviderId+"04"+(new Random().nextInt(8999)+1000));
			pimage4.setImagepath(image4);
			pimage4.setProviderId(endProviderId);
			pimage4.setUploadtime(new Date());
			pimage4.setImgType((short)4);
			
			Provider_image pimage5 = new Provider_image();
			pimage5.setImageid(endProviderId+"05"+(new Random().nextInt(8999)+1000));
			pimage5.setImagepath(image5);
			pimage5.setProviderId(endProviderId);
			pimage5.setUploadtime(new Date());
			pimage5.setImgType((short)5);
			
			provider.setProviderimageid(pimage1.getImageid());
			provider.setCertid1imageid(pimage3.getImageid());
			provider.setCertid2imageid(pimage4.getImageid());
			provider.setCertid3imageid(pimage5.getImageid());
		
			baseDao.update(provider);
			baseDao.insert(pimage1);
			baseDao.insert(pimage2);
			baseDao.insert(pimage3);
			baseDao.insert(pimage4);
			baseDao.insert(pimage5);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public List<Product> getProviderProductByMethod(int type, String... params) {
		
		List<Product> productsList = new ArrayList<Product>();
		switch (type) {
		case 0:
			productsList = productService.getProductListByMethod(2, null, params[0]);
			for (Product product : productsList) {
				product.setProductimglist(productImgService.getProductImgByMethod(4, product.getProductId()));
			}
			break;

		default:
			break;
		}
		return productsList;
	}


	@Override
	public Integer getProviderByCityId(String cityId) {
		List cityCount =	baseDao.findBySql("select count(*) from tb_provider where cityId in ("+cityId+") and validflag=1", null);
		return Integer.valueOf(cityCount.get(0)+"");
	}

}
