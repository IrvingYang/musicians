package com.qushop.trip.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.trip.entity.Activity;
import com.qushop.trip.entity.Blogs;
import com.qushop.trip.entity.Entityimage;
import com.qushop.trip.entity.Recommendline;
import com.qushop.trip.entity.TripModuleRecommend;
import com.qushop.trip.service.ActivityService;
import com.qushop.trip.service.BlogsService;
import com.qushop.trip.service.EntityimageService;
import com.qushop.trip.service.RecommendlineService;
import com.qushop.trip.service.TripModuleRecommendService;
import com.qushop.user.entity.Oper;

@Service
public class TripModuleRecommendServiceImpl implements TripModuleRecommendService{

	
	@Resource
	CommonDao commonDao;
	
	@Resource
	ActivityService activityService;
	
	@Resource
	RecommendlineService recommendlineService;
	
	@Resource
	BlogsService blogsService;
	
	@Resource
	EntityimageService entityimageService;
	
	@SuppressWarnings("all")
	public List<TripModuleRecommend> getTripModuleRecommendByMethod(int type,int maxcount,Oper oper,String ...params){
		
		List<TripModuleRecommend> moduleRecommendsList = new ArrayList();
		switch (type) {
		case 0:
			if(oper==null)
			{
				moduleRecommendsList = commonDao.findPagingBySql("select * from tb_trip_Module_Recommend where recommEndTime>now() and validflag=1 order by entitytype,entityid desc"
								,0,maxcount,TripModuleRecommend.class);
			}
			else
			{
				moduleRecommendsList = commonDao.findBySql("select * from tb_trip_Module_Recommend where validflag=1 order by entitytype,entityid desc",TripModuleRecommend.class);
			}
			break;
		case 1:
			moduleRecommendsList = commonDao.findByHql("from TripModuleRecommend where entitytype=? and entityid=? and validflag=1", params[0], params[1]);
			break;

		default:
			break;
		}
		
		for (TripModuleRecommend tripModuleRecommend : moduleRecommendsList) {
			
			String entityImageHql = "from Entityimage where imageid=? and imgType=5";
			
			if(tripModuleRecommend.getEntitytype().equals("01")){
				List<Activity> list = activityService.getActivityByMethod(1, 0, oper, tripModuleRecommend.getEntityid());
				tripModuleRecommend.setActivitiesList(list);
			}else if(tripModuleRecommend.getEntitytype().equals("02")){
				List<Recommendline> list = recommendlineService.getRecommendlineByMethod(1, 0, oper, tripModuleRecommend.getEntityid());
				tripModuleRecommend.setRecommendlinesList(list);
			}else if(tripModuleRecommend.getEntitytype().equals("03")){
				List<Blogs> list = blogsService.getBlogsByMethod(1, 0, oper, tripModuleRecommend.getEntityid());
				tripModuleRecommend.setBlogsList(list);
			}
		}
		
		return moduleRecommendsList;
	}

	@Override
	public boolean addTripModuleRecommend(TripModuleRecommend moduleRecommend,Oper oper,String recommendImage) {
		
		moduleRecommend.setLastUpdateTime(new Date());
		moduleRecommend.setOperid(oper.getOperId());
		moduleRecommend.setValidflag((short)1);
		
		Entityimage entityimage = new Entityimage();
		entityimage.setImagepath(recommendImage);
		entityimage.setEntitytype(moduleRecommend.getEntitytype());
		entityimage.setImgType((short)5);
		entityimage.setEntityid(moduleRecommend.getEntityid());
		entityimage.setImageid(oper.getProviderId()+entityimage.getEntitytype()+(new Random().nextInt(8999)+1000));
		entityimage.setUploadtime(new Date());
		
		try {
			commonDao.insert(entityimage);
			commonDao.insert(moduleRecommend);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteTripModuleRecommend(String[] entitytypeAndentityid,Oper oper) {
		
		try {
			for (String eandid : entitytypeAndentityid) {
				String[] typeandid = eandid.split("and");
				String sql = "update tb_trip_Module_Recommend set validflag = 0,operid=?,lastupdatetime=? where entitytype=? and entityid=? and validflag=1";
				commonDao.executeBySql(sql,oper.getOperId(),new Date(),typeandid[0],typeandid[1]);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public boolean updateTripModuleRecommend(
			TripModuleRecommend moduleRecommend,String recommendImage, Oper oper) {
		
		moduleRecommend.setLastUpdateTime(new Date());
		moduleRecommend.setOperid(oper.getOperId());
		moduleRecommend.setValidflag((short)1);
		
		Entityimage entityimage = new Entityimage();
		
		List<Entityimage> entityimagesList = entityimageService.getEntityimagesByMethod(0, 0, oper,moduleRecommend.getEntitytype(),moduleRecommend.getEntityid());
		if(entityimagesList.size()>0)
		{
			entityimage = entityimagesList.get(0);
			entityimage.setImagepath(recommendImage);
			entityimage.setUploadtime(new Date());
			commonDao.update(entityimage);
		}else
		{
			entityimage.setImagepath(recommendImage);
			entityimage.setEntitytype(moduleRecommend.getEntitytype());
			entityimage.setImgType((short)5);
			entityimage.setEntityid(moduleRecommend.getEntityid());
			entityimage.setImageid(oper.getProviderId()+entityimage.getEntitytype()+(new Random().nextInt(8999)+1000));
			entityimage.setUploadtime(new Date());
			commonDao.insert(entityimage);
		}
		try {
			commonDao.update(moduleRecommend);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}





















