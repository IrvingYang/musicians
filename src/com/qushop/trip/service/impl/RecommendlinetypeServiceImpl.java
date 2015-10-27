package com.qushop.trip.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.trip.entity.Recommendlinetype;
import com.qushop.trip.service.RecommendlinetypeService;
import com.qushop.user.entity.Oper;


@Service
public class RecommendlinetypeServiceImpl implements RecommendlinetypeService {

	
	@Resource
	CommonDao commonDao;
	
	@Override
	public List getRecommendlineTypeByMethod(int type, Oper oper,
			String... params) {

		List<Recommendlinetype> recommendlinetypes = new ArrayList();
		
		switch (type) {
		case 0:
			recommendlinetypes = commonDao.findByHql("from Recommendlinetype where validflag=1");
			break;
		case 1:
			recommendlinetypes = commonDao.findByHql("from Recommendlinetype where linetypeid=? and validflag=1",params[0]);
			break;

		default:
			break;
		}
		
		return recommendlinetypes;
	}

	@Override
	public boolean saveRecommendlineType(String action,
			Recommendlinetype recommendlinetype, Oper oper, String imagepath) {
		
		try {

			recommendlinetype.setOperid(oper.getOperId());
			recommendlinetype.setLastUpdateTime(new Date());
			recommendlinetype.setValidflag((short)1);
			
			if("update".equals(action))
			{
				commonDao.update(recommendlinetype);
			}
			else if("add".equals(action))
			{
				List<Recommendlinetype> recommendlinetypes = commonDao.findPagingByHql("from Recommendlinetype order by linetypeid desc", 0, 1);
				String endId = "01";
				if(recommendlinetypes.size()>0)
				{
					endId = "";
					String typeId = recommendlinetypes.get(0).getLinetypeid();
					int plusid = Integer.parseInt(typeId)+1;
					for (int i = (plusid+"").length(); i < 2; i++) {
						endId += "0";
					}
					endId += plusid;
				}
				recommendlinetype.setLinetypeid(endId);
				commonDao.insert(recommendlinetype);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				throw new RuntimeException("保存信息出线问题");
			} catch (Exception e2) {
				return false;
			}
		}
		
	}

	@Override
	public boolean deleteRecommendlineType(String linetypeid, Oper oper) {

		try {
			commonDao.executeBySql("update tb_recommendlinetype set validflag=0,operid=?,lastUpdateTime=? where linetypeid in("+linetypeid+")", oper.getOperId(),new Date());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
