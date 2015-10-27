package com.qushop.trip.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.UtilDate;
import com.qushop.provider.entity.Provider;
import com.qushop.provider.service.ProviderService;
import com.qushop.trip.entity.Activity;
import com.qushop.trip.entity.Entityimage;
import com.qushop.trip.service.ActivityService;
import com.qushop.trip.service.ActivityapplyService;
import com.qushop.trip.service.EntityimageService;
import com.qushop.trip.service.TripModuleRecommendService;
import com.qushop.user.entity.Oper;


@Service
public class ActivityServiceImpl implements ActivityService {
	
	@Resource
	CommonDao commonDao;

	@Resource
	ProviderService providerService;
	
	@Resource
	ActivityapplyService activityapplyService;
	
	@Resource
	EntityimageService entityimageService;
	
	@Resource
	TripModuleRecommendService tripModuleRecommendService;
	
	@Override
	public List<Activity> getActivityByMethod(int type, int maxCount,
			Oper oper, String... params) {

		List<Activity> activitiesList = null;
		switch (type) 
		{
			case 0:
				
				if(oper==null)
				{
					activitiesList = commonDao.findByHql("from Activity where status=1 and validflag=1 order by lastUpdateTime desc");
				}
				else if(oper!=null && oper.getPartnerflag()==0)
				{
					activitiesList = commonDao.findByHql("from Activity where validflag=1");
				}
				else
				{
					activitiesList = commonDao.findByHql("from Activity where providerId=? and validflag=1",oper.getProviderId());
				}
				
				for (Activity activity : activitiesList) {
					List<Object[]> list = activityapplyService.getActivityApplyByMethod(3, 0, oper, activity.getActivityid());
					short adult = 0;
					short child = 0;
					//下标0表示成人
					if(list.get(0)[0]!=null)
					{
						adult= Short.parseShort(list.get(0)[0]+"");
					}
					//下标为1表示小孩
					if(list.get(0)[1]!=null){

						child= Short.parseShort(list.get(0)[1]+"");
					}
					activity.setApplyadultCount(adult);
					activity.setApplychildCount(child);
					activity.setApplyCount((short)(adult+child));
				}
				
				break;
			case 1:
				activitiesList = commonDao.findByHql("from Activity where activityid=? and validflag=1", params[0]);
				break;
			case 2:
				activitiesList = commonDao.findByHql("from Activity where activityid=?", params[0]);
				break;
	
			default:
				break;
		}
		for (Activity activity : activitiesList) 
		{
			List<Provider> providersList = providerService.getProviderByMethod(5, oper, activity.getProviderId());
			if (providersList.size()>0) 
			{
				activity.setProvider(providersList.get(0));
				activity.setEntityimage(entityimageService.getEntityimagesByMethod(0, 0, oper,"01", activity.getActivityid()));
			}
		}
		
		return activitiesList;
	}

	@Override
	public boolean updateActivity(Activity activity,Oper oper,String imagepath) {
		
		try {
			
			//当路劲为空，表示业务状态修改
			if(imagepath!=null && !"".equals(imagepath.trim()))
			{
				//不为空则详细信息修改
				//查询关联图片
				List<Entityimage> dimagesList = entityimageService.getEntityimagesByMethod(0, 0, null, "01", activity.getActivityid());
				for (Entityimage entityimage : dimagesList) {
					//真实删除
					entityimageService.actualDelete(entityimage);
				}
				Entityimage entityimage = new Entityimage();
				entityimage.setImagepath(imagepath);
				entityimage.setEntitytype("01");
				entityimage.setImgType((short)1);
				entityimage.setEntityid(activity.getActivityid());
				entityimage.setImageid(oper.getProviderId()+entityimage.getEntitytype()+(new Random().nextInt(8999)+1000));
				entityimage.setUploadtime(new Date());
				
				commonDao.insert(entityimage);
				activity.setCoverimageid(entityimage.getImageid());
			}
			
			activity.setLastUpdateTime(new Date());
			activity.setValidflag((short)1);
			activity.setOperid(oper.getOperId());
			
			commonDao.update(activity);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				throw new RuntimeException("修改活动出现异常");
			} catch (Exception e1) {
				return false;
			}
		}
	}

	@Override
	public boolean addActivity(Activity activity,Oper oper,String imagepath) {
		
		try {
			
			//查询当前日期编号最大活动编号
			String activitySql = "select * from tb_activity where activityid like '"+(oper.getProviderId()+UtilDate.getNowDateNo_())+"%' order by activityid desc";
			List<Activity> activitiesList = commonDao.findPagingBySql(activitySql, 0, 1, Activity.class);
			if(activitiesList.size()<=0)
			{
				activity.setActivityid(oper.getProviderId()+UtilDate.getNowDateNo_()+"01");
			}
			else
			{
				Activity act = activitiesList.get(0);
				String beginSid = act.getActivityid().substring(12);
				Integer beginIid = Integer.parseInt(beginSid)+1;
				String endId = "";
				for (int i = (beginIid+"").length(); i < 2; i++) {
					endId += "0";
				}
				endId += beginIid;
				activity.setActivityid(oper.getProviderId()+UtilDate.getNowDateNo_()+endId);
			}
			activity.setProviderId(oper.getProviderId());
			activity.setLastUpdateTime(new Date());
			activity.setValidflag((short)1);
			activity.setOperid(oper.getOperId());
			

			Entityimage entityimage = new Entityimage();
			entityimage.setImagepath(imagepath);
			entityimage.setEntitytype("01");
			entityimage.setImgType((short)1);
			entityimage.setEntityid(activity.getActivityid());
			entityimage.setImageid(oper.getProviderId()+entityimage.getEntitytype()+(new Random().nextInt(8999)+1000));
			entityimage.setUploadtime(new Date());
			
			activity.setCoverimageid(entityimage.getImageid());

			commonDao.insert(entityimage);
			commonDao.insert(activity);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				throw new RuntimeException("添加活动出现异常");
			} catch (Exception e1) {
				return false;
			}
		}
	}

	@Override
	public boolean deleteActivity(String[] activityid,Oper oper) {
		
		try {
			for(int i=0;i<activityid.length;i++) {
				commonDao.executeBySql("update tb_activity set validflag=0,lastUpdateTime=?,operid=? where activityid =?", new Date(),oper.getOperId(),activityid[i]);
				activityid[i]="01and"+activityid[i];
			}
			tripModuleRecommendService.deleteTripModuleRecommend(activityid, oper);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
