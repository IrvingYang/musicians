package com.qushop.dict.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.dict.entity.Express_vendor;
import com.qushop.dict.service.ExpressService;


@Service
public class ExpressServiceImpl implements ExpressService {
	
	@Resource
	CommonDao commonDao;

	@Override
	public boolean addExpress(Express_vendor express) {
		
		List<Express_vendor> expressList = commonDao.findPagingByHql("from Express_vendor order by vendorid desc", 0, 1);
		if(expressList.size()<=0){
			express.setVendorid("01");
		}else{
			Express_vendor e = expressList.get(0);
			String bId = e.getVendorid();
			int id = Integer.parseInt(bId)+1;
			String endId = "";
			for (int i = (id+"").length(); i < 2; i++) {
				endId+="0";
			}
			endId+=id;
			express.setVendorid(endId);
		}
		try {
			commonDao.insert(express);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteExpress(String vendorid) {
		boolean bool = false;
		String sql = "update tb_express_vendor set validflag=0 where vendorid in ("+vendorid+")";
		bool = commonDao.executeBySql(sql);
		return bool;
	}

	@Override
	public boolean updateExpress(Express_vendor express) {
		try {
			commonDao.update(express);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Express_vendor> getExpressByMethod(int type, String... params) {
		
		List<Express_vendor> vendorsList = new ArrayList();
		
		switch (type) {
		case 0:
			vendorsList = commonDao.findByHql("from Express_vendor where validflag=1");
			break;
		case 1:
			vendorsList = commonDao.findByHql("from Express_vendor where vendorid = ? and validflag=1", params[0]);
			break;
		case 2:
			vendorsList = commonDao.findByHql("from Express_vendor where vendorid = ?", params[0]);
			break;
		}
		return vendorsList;
	}

}
