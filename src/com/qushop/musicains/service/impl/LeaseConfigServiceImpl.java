package com.qushop.musicains.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.musicains.entity.LeaseConfig;
import com.qushop.musicains.service.LeaseConfigService;
import com.qushop.prod.entity.ProductType;
import com.qushop.prod.service.ProductTypeService;

@Service
public class LeaseConfigServiceImpl implements LeaseConfigService {

	@Resource
	CommonDao<LeaseConfig> commonDao;
	
	@Resource
	ProductTypeService productTypeService;
	
	@Override
	public String saveLeaseConfig(LeaseConfig leaseConfig) {
		commonDao.insert(leaseConfig);
		return "success";
	}

	public List<LeaseConfig> getLeaseConfigList(String productTypeId) {
		List<LeaseConfig> leaseList = commonDao.findByHql("from LeaseConfig where validflag=1 and productTypeId=?",
				productTypeId);
		return leaseList;
	}

	@Override
	public LeaseConfig getLeaseConfig(String productTypeId, int count, int period) {
		List<LeaseConfig> leaseList = commonDao
				.findByHql("from LeaseConfig where validflag=1 and productTypeId=? and day=?", productTypeId, period);
		if (leaseList == null) {
			throw new IllegalStateException("productType =" + productTypeId + " day = " + period + "结果为空");
		} else if (leaseList.size() > 1) {
			throw new IllegalStateException("productType =" + productTypeId + " day = " + period + "结果找到多条相应记录");
		} else {
			return leaseList.get(0);
		}
	}
	
	

	@Override
	public List<LeaseConfig> getAllLeaseConfigList() {
		List<LeaseConfig> leaseList = commonDao.findByHql("from LeaseConfig where validflag=1 order by productTypeId");
		for (LeaseConfig leaseConfig : leaseList) {
			List<ProductType> productTypeByMethod = productTypeService.getProductTypeByMethod(8, leaseConfig.getProductTypeId());
			leaseConfig.setProductType(productTypeByMethod.get(0));
		}
		
		return leaseList;
	}

	@Override
	public LeaseConfig getLeaseConfig(String lcId) {
		List<LeaseConfig> leaseList = commonDao
				.findByHql("from LeaseConfig where validflag=1 and lcId=?",lcId);
		if (leaseList == null) {
			throw new IllegalStateException("leaseConfigId =" + lcId + "结果为空");
		} else {
			LeaseConfig leaseConfig = leaseList.get(0);
			List<ProductType> productTypeByMethod = productTypeService.getProductTypeByMethod(8, leaseConfig.getProductTypeId());
			leaseConfig.setProductType(productTypeByMethod.get(0));
			return leaseConfig;
		}
	}

	@Override
	public String updateLeaseConfig(LeaseConfig leaseConfig) {
		commonDao.update(leaseConfig);
		return "success";
	}

}
