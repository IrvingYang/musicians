package com.qushop.trip.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.trip.entity.Recommendline;
import com.qushop.trip.entity.Recommendlineapply;
import com.qushop.trip.service.RecommendlineService;
import com.qushop.trip.service.RecommendlineapplyService;
import com.qushop.user.entity.Oper;
import com.qushop.user.entity.User;
import com.qushop.user.service.UserService;

@Service
public class RecommendlineapplyServiceImpl implements RecommendlineapplyService{

	
	@Resource
	CommonDao commonDao;
	
	@Resource
	RecommendlineService recommendlineService;
	
	@Resource
	UserService userService;
	
	@Override
	public List getRecommendlineApplyByMethod(int type, int maxCount,
			Oper oper, String... params) {

		List<Recommendlineapply> recommendlineappliesList = new ArrayList<Recommendlineapply>();
		
		switch (type) {
		case 0:
			recommendlineappliesList = commonDao.findByHql("from Recommendlineapply where recommendlineid=? and validflag=1", params[0]);
			break;
		case 1:
			recommendlineappliesList = commonDao.findByHql("from Recommendlineapply where userid=? and recommendlineid=? and validflag=1", params[0], params[1]);
			break;
		case 2:
			return commonDao.findByHql("select count(*) from Recommendlineapply where recommendlineid=? and status not in(3,4) and validflag=1", params[0]);
		case 3:
			return commonDao.findBySql("select sum(adultcount),sum(childcount) from tb_recommendlineapply where recommendlineid=? and status not in(3,4) and validflag=1", null, params[0]);
		default:
			break;
		}
		for (Recommendlineapply recommendlineapply : recommendlineappliesList) {
			recommendlineapply.setRecommendline((Recommendline) recommendlineService.getRecommendlineByMethod(3, 0, oper, recommendlineapply.getRecommendlineid()).get(0));
			recommendlineapply.setUser((User)userService.getUserByMethod(11, recommendlineapply.getUserid()).get(0));
		}
		return recommendlineappliesList;
	}

	@Override
	public boolean addRecommendlineApply(Recommendlineapply recommendlineapply,
			Oper oper) {
		
		try {
			recommendlineapply.setLastUpdateTime(new Date());
			recommendlineapply.setValidflag((short)1);
			recommendlineapply.setCreatetime(new Date());
			commonDao.insert(recommendlineapply);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				throw new RuntimeException();
			} catch (Throwable e1) {
				e1.printStackTrace();
				return false;
			}
		}
	}

	@Override
	public boolean deleteRecommendlineapply(String userid,
			String recommendlineid, Oper oper) {
		
		try {
			String sql = "update tb_recommendlineapply set validflag=0,operid=?,lastUpdateTime=? where userid=? and recommendlineid=?";
			commonDao.executeBySql(sql, oper.getOperId(),new Date(),userid,recommendlineid);
			return true;
		} catch (Exception e) {
			try {
				throw new RuntimeException();
			} catch (Throwable e1) {
				e1.printStackTrace();
				return false;
			}
		}
	}

	@Override
	public boolean updateStatus(String[] useridAndRecommendlineid,
			Integer status, Oper oper) {
		
		try {
			
			for (String uas : useridAndRecommendlineid) {
				String[] ua = uas.split("and");
				String sql = "update tb_recommendlineapply set status=?,operid=?,lastUpdateTime=? where userid=? and recommendlineid=?";
				commonDao.executeBySql(sql,status,oper.getOperId(),new Date(),ua[0],ua[1]);
			}
			
			return true;
		} catch (Exception e) {
			try {
				throw new RuntimeException();
			} catch (Throwable e1) {
				e1.printStackTrace();
				return false;
			}
		}
	}
}
