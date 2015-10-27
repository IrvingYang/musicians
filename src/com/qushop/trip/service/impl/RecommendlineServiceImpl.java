package com.qushop.trip.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.Hanyu;
import com.qushop.common.util.ImgCompress;
import com.qushop.common.util.UtilDate;
import com.qushop.provider.service.ProviderService;
import com.qushop.trip.entity.Entityimage;
import com.qushop.trip.entity.Recommendline;
import com.qushop.trip.entity.Recommendlinetype;
import com.qushop.trip.service.EntityimageService;
import com.qushop.trip.service.RecommendlineService;
import com.qushop.trip.service.RecommendlineapplyService;
import com.qushop.trip.service.RecommendlinetypeService;
import com.qushop.trip.service.TripModuleRecommendService;
import com.qushop.user.entity.Oper;


@Service
public class RecommendlineServiceImpl implements RecommendlineService {

	@Resource
	CommonDao<Recommendline> commonDao;

	@Resource
	EntityimageService entityimageService;
	
	@Resource
	ProviderService providerService;
	
	@Resource
	RecommendlinetypeService recommendlinetypeService;
	
	@Resource
	RecommendlineapplyService recommendlineapplyService;
	
	@Resource
	TripModuleRecommendService tripModuleRecommendService;
	
	@Override
	public List getRecommendlineByMethod(int type, int maxCount, Oper oper,
			String... params) {
		
		List<Recommendline> recommendlinesList = new ArrayList<Recommendline>();
		
		switch (type) {
		case 0:
			if(oper==null)
			{
				recommendlinesList = commonDao.findByHql("from Recommendline where status=1 and validflag=1");
			}
			else if(oper!=null && oper.getPartnerflag()==0)
			{
				recommendlinesList = commonDao.findByHql("from Recommendline where validflag=1");
			}
			else
			{
				recommendlinesList = commonDao.findByHql("from Recommendline where providerId=? and validflag=1", oper.getProviderId());
			}
			
			for (Recommendline recommendline : recommendlinesList) {
				List<Object[]> list = recommendlineapplyService.getRecommendlineApplyByMethod(3, 0, oper, recommendline.getRecommendlineid());
				short adult = 0;
				short child = 0;
				if(list.get(0)[0]!=null)
				{
					adult= Short.parseShort(list.get(0)[0]+"");
				}
				if(list.get(0)[1]!=null){

					child= Short.parseShort(list.get(0)[1]+"");
				}
				recommendline.setApplyadultCount(adult);
				recommendline.setApplychildCount(child);
				recommendline.setApplyCount((short)(adult+child));
			}
			
			break;
		case 1:
			recommendlinesList = commonDao.findByHql("from Recommendline where recommendlineid=? and validflag=1", params[0]);
			break;
		case 2:
			recommendlinesList = commonDao.findByHql("from Recommendline where linetypeid=? and validflag=1", params[0]);
			return recommendlinesList;
		case 3:
			recommendlinesList = commonDao.findByHql("from Recommendline where recommendlineid=?", params[0]);
			break;
		default:
			break;
		}
		
		for (Recommendline recommendline : recommendlinesList)
		{
			recommendline.setEntityimage(entityimageService.getEntityimagesByMethod(0, 0, oper, "02",recommendline.getRecommendlineid()));
			recommendline.setProvider(providerService.getProviderByMethod(5, oper, recommendline.getProviderId()).get(0));
			recommendline.setRecommendlinetype((Recommendlinetype) recommendlinetypeService.getRecommendlineTypeByMethod(1, oper, recommendline.getLinetypeid()).get(0));
		}
		
		return recommendlinesList;
	}


	@Override
	public boolean deleteRecommendline(String[] recommendlineid, Oper oper) {

		try {
			for(int i=0;i<recommendlineid.length;i++){
				String sql = "update tb_recommendline set validflag=0,operid=?,lastUpdatetime=? where recommendlineid=?";
				commonDao.executeBySql(sql, oper.getOperId(),new Date(),recommendlineid[i]);
				recommendlineid[i]="02and"+recommendlineid[i];
			}
			tripModuleRecommendService.deleteTripModuleRecommend(recommendlineid, oper);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				throw new RuntimeException("删除线路出线异常："+e.getMessage());
			} catch (Exception e2) {
				return false;
			}
		}
	}


	@Override
	public boolean saveRecommendline(String action, Recommendline recommendline,
			String imagepath, Oper oper, String rootPath) {
		
		try {
			recommendline.setOperid(oper.getOperId());
			recommendline.setLastUpdateTime(new Date());
			recommendline.setValidflag((short)1);
			
			if("update".equals(action) && imagepath!=null && !"".equals(imagepath))
			{
				List<Entityimage> entityimagesList = entityimageService.getEntityimagesByMethod(0, 0, oper, "02", recommendline.getRecommendlineid());
				
				if(entityimagesList.size()>0)
				{
					for (Entityimage entityimage : entityimagesList) 
					{
						commonDao.delete(entityimage);
					}
				}
			}
			else if("add".equals(action))
			{
				//查询当前日期编号最大活动编号
				String sql = "select * from tb_recommendline where recommendlineid like '"+(oper.getProviderId()+UtilDate.getNowDateNo_())+"%' order by recommendlineid desc";
				List<Recommendline> recommendlinesList = commonDao.findPagingBySql(sql, 0, 1, Recommendline.class);
				if(recommendlinesList.size()<=0)
				{
					recommendline.setRecommendlineid(oper.getProviderId()+UtilDate.getNowDateNo_()+"01");
				}
				else
				{
					Recommendline rcl = recommendlinesList.get(0);
					String beginSid = rcl.getRecommendlineid().substring(12);
					Integer beginIid = Integer.parseInt(beginSid)+1;
					String endId = "";
					for (int i = (beginIid+"").length(); i < 2; i++) {
						endId += "0";
					}
					endId += beginIid;
					recommendline.setRecommendlineid(oper.getProviderId()+UtilDate.getNowDateNo_()+endId);
				}
			}
			if(imagepath!=null && !"".equals(imagepath))
			{
				Entityimage entityimage = new Entityimage();
				entityimage.setImagepath(imagepath);
				entityimage.setEntitytype("02");
				entityimage.setImgType((short)2);
				entityimage.setEntityid(recommendline.getRecommendlineid());
				entityimage.setImageid(oper.getProviderId()+entityimage.getEntitytype()+(new Random().nextInt(8999)+1000));
				entityimage.setUploadtime(new Date());
				
				ImgCompress compress = new ImgCompress(rootPath+imagepath);
				String preffix = imagepath.substring(0, imagepath.lastIndexOf("/"));
				String suffix = imagepath.substring(imagepath.lastIndexOf("."));
				String filePath = rootPath + preffix + "/" + new Hanyu().getStringPinYin(recommendline.getTitle()) + suffix;
				File outputFile = new File(filePath);
				if(!outputFile.exists())
				{
					outputFile.createNewFile();
				}
				compress.resizeFix(340, 210, outputFile);
				
				Entityimage entityimage1 = new Entityimage();
				entityimage1.setImagepath(preffix + "/" + new Hanyu().getStringPinYin(recommendline.getTitle()) + suffix);
				entityimage1.setEntitytype("02");
				entityimage1.setImgType((short)3);
				entityimage1.setEntityid(recommendline.getRecommendlineid());
				entityimage1.setImageid(oper.getProviderId()+entityimage.getEntitytype()+(new Random().nextInt(8999)+1000));
				entityimage1.setUploadtime(new Date());
				recommendline.setCoverimageid(entityimage1.getImageid());

				commonDao.insert(entityimage1);
				commonDao.insert(entityimage);
				
			}
			
			if("update".equals(action))
			{
				commonDao.update(recommendline);
			}
			else if("add".equals(action))
			{
				recommendline.setStatus((short)0);
				recommendline.setProviderId(oper.getProviderId());
				commonDao.insert(recommendline);
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				throw new RuntimeException("新增线路出线异常："+e.getMessage());
			} catch (Exception e2) {
				return false;
			}
		}
	}
}
