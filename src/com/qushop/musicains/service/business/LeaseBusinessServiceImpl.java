package com.qushop.musicains.service.business;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.qushop.musicains.entity.Lease;
import com.qushop.musicains.entity.LeaseConfig;
import com.qushop.musicains.service.LeaseConfigService;
import com.qushop.musicains.service.LeaseDaoService;
import com.qushop.prod.entity.Product;
import com.qushop.prod.service.ProductService;
import com.qushop.user.entity.UserAddress;
import com.qushop.user.service.UserAddressService;

@Service
public class LeaseBusinessServiceImpl implements LeaseBusinessService {

	@Resource
	LeaseDaoService leaseDaoService;

	@Resource
	LeaseConfigService leaseConfigService;

	@Resource
	ProductService productService;
	
	@Resource
	UserAddressService userAddressService;

	@Override
	public double calculateTotalRentPrice(String productId, int count, int period,double yajin) {
		Product product = productService.getProductListByMethod(2, null, productId).get(0);
		LeaseConfig leaseConfig = leaseConfigService.getLeaseConfig(product.getProductTypeId(), count,
				period);
		// 计算租赁价格
		Double totalMoney = leaseConfig.getMoney() * count + yajin;
		return totalMoney;
	}
	
	public String assembleAddressFromUserAddress(String userId,String addressId){
		List<UserAddress> userAddressByMethod = userAddressService.getUserAddressByMethod(1,userId, addressId);
		UserAddress address=userAddressByMethod.get(0);
		String addressAsString=address.getStateId()+ " " +address.getCityId()+" "+ address.getStreet()+" "+address.getName()+" 收  电话："+address.getTelephone();
		return addressAsString;
	}

	
	public void startCountingRentDates(String leaseId){
		Lease leaseByLeaseId = leaseDaoService.getLeaseByLeaseId(leaseId);
		int leaseCycle = leaseByLeaseId.getLeaseCycle();
		DateTime now= DateTime.now();
		DateTime plusDays = now.plusDays(leaseCycle);
		leaseByLeaseId.setShouldEndTime(plusDays.toDate());
		leaseByLeaseId.setLastUpdateTime(now.toDate());
		leaseDaoService.updateLease(leaseByLeaseId);
	}
	
}
