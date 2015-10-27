package com.qushop.trip.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.trip.entity.Activityapply;
import com.qushop.trip.service.ActivityService;
import com.qushop.trip.service.ActivityapplyService;
import com.qushop.user.entity.Oper;
import com.qushop.user.entity.User;
import com.qushop.user.service.UserService;


@Service
public class ActivityapplyServiceImpl implements ActivityapplyService {
	
	@Resource
	CommonDao commonDao;
	
	@Resource
	ActivityService activityService;
	
	@Resource
	UserService userService;

	@Override
	public List getActivityApplyByMethod(int type, int maxCount,
			Oper oper, String... params) {

		List<Activityapply> activityappliesList = null;
		switch (type) {
		
		case 0:
			activityappliesList = commonDao.findByHql("from Activityapply where activityid=? and validflag=1", params[0]);
			break;
		case 1:
			activityappliesList = commonDao.findByHql("from Activityapply where userid=? and activityid=? and validflag=1", params[0],params[1]);
			break;
		case 2:
			return commonDao.findByHql("select count(*) from Activityapply where activityid=?and status not in(3,4)  and validflag=1", params[0]);
		case 3:
			return commonDao.findBySql("select sum(adultcount),sum(childcount) from tb_activityapply where activityid=? and status not in(3,4) and validflag=1", null, params[0]);
		default:
			break;
		}
		for (Activityapply activityapply : activityappliesList) {
			activityapply.setActivity(activityService.getActivityByMethod(2, 0, oper, activityapply.getActivityid()).get(0));
			activityapply.setUser((User)userService.getUserByMethod(11, activityapply.getUserid()).get(0));
		}
		return activityappliesList;
	}

	@Override
	public String addActivityApply(Activityapply activityapply,Oper oper) {
		
		try {
			activityapply.setValidflag((short)1);
			activityapply.setStatus(0);
			activityapply.setCreatetime(new Date());
			activityapply.setLastUpdateTime(new Date());
			commonDao.insert(activityapply);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public String updateActivityApply(Activityapply activityapply,Oper oper) {

		try {
			activityapply.setValidflag((short)1);
			activityapply.setLastUpdateTime(new Date());
			activityapply.setOperid(oper.getOperId());
			commonDao.update(activityapply);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public String deleteActivityApply(String userid, String activityId, Oper oper) {
		
		try {
			commonDao.executeBySql("update tb_activityapply set validflag=0,lastupdatetime=?,operid=? where userid=? and activityid=?",new Date(),oper.getOperId(),userid,activityId);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public String updateStatus(String[] useridAndActivityid,Integer status,Oper oper) {
		
		try {
			for (String uas : useridAndActivityid) {
				String[] ua = uas.split("and");
				commonDao.executeBySql("update tb_activityapply set status=?,lastupdatetime=?,operid=? where userid=? and activityid=?",
										status,new Date(),oper.getOperId(),ua[0],ua[1]);
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
}
