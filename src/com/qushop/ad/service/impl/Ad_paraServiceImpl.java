package com.qushop.ad.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.qushop.ad.entity.Ad_image;
import com.qushop.ad.entity.Ad_para;
import com.qushop.ad.service.Ad_paraService;
import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.Constants;
import com.qushop.common.util.PublicUtil;
import com.qushop.common.util.UtilDate;

@Service
public class Ad_paraServiceImpl implements Ad_paraService {

	@Resource
	CommonDao<Ad_para> commonDao;
	
	@Resource
	CommonDao<Ad_image> adImageDao;


	@Override
	public List<Ad_para> getAd_ParaByArea(Integer type,String typeId,Integer maxCount) {

		List<Ad_para> ad_parasList = new ArrayList<Ad_para>();
		switch (type) {
		case 0:
			ad_parasList = commonDao.findByHql("from Ad_para where adarea=? and sysdate() between adbegintime and adendtime and validflag=?",typeId,(short)1);
			for (Ad_para ad_para : ad_parasList) {
				List<Ad_image> imagesList = adImageDao.findByHql("from Ad_image where imageid=?", ad_para.getAdimageid());
				if(imagesList!=null && imagesList.size()>0){
					ad_para.setAd_imagesList(imagesList);
				}
			}
			break;

		default:
			break;
		}
		
		return ad_parasList;
	}

	@Override
	public List<Ad_para> getAd_ParaByMethod(int type, String... params) {
		
		List<Ad_para> ad_parasList = new ArrayList<Ad_para>();
		switch (type) {
		case 0:
			ad_parasList = commonDao.findByHql("from Ad_para where validflag=1");
			break;
		case 1:
			ad_parasList = commonDao.findByHql("from Ad_para where  adid=? and validflag=1", params[0]);
			for (Ad_para ad_para : ad_parasList) {
				List<Ad_image> ad_imagesList = adImageDao.findByHql("from Ad_image where imageid=?", ad_para.getAdimageid());
				if(ad_imagesList!=null && ad_imagesList.size()>0){
					ad_para.setAd_imagesList(ad_imagesList);
				}
			}
			break;
		case 2:
			ad_parasList = commonDao.findByHql("from Ad_para where adarea=? and validflag=1", params[0]);
		}
		return ad_parasList;
	}
	@Override
	public boolean addAdPara(Ad_para ad_para, HttpServletRequest request) {
		
		int result = 0;
		
		String imageurl = request.getParameter("image1");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		Ad_image image = new Ad_image();
		List<Ad_para> ad_parasList = commonDao.findByHql("from Ad_para where adid like '"+UtilDate.getNowDateNo_()+"%' order by adid desc");
		if(ad_parasList.size()<=0){
			ad_para.setAdid(UtilDate.getNowDateNo_()+"01");
		}
		else{
			String id = ad_parasList.get(0).getAdid().substring(8);
			String endId = "";
			int nowId = Integer.parseInt(id);
			if(nowId>=99){
				return false;
			}else{
				nowId++;
				endId = (nowId+"");
				if(endId.length()<2){
					endId = "0"+endId;
				}
				ad_para.setAdid(UtilDate.getNowDateNo_()+endId);
			}
		}
		image.setAdid(ad_para.getAdid());
		image.setImageid(UtilDate.getNowDateNo_()+(new Random().nextInt(89)+10));
		image.setImagepath(imageurl);
		image.setUploadtime(new Date());
		try {
			ad_para.setAdimageid(image.getImageid());
			ad_para.setLastUpdateTime(new Date());
			ad_para.setAdbegintime(UtilDate.parseDateForDatepatternString(startTime));
			ad_para.setAdendtime(UtilDate.parseDateForDatepatternString(endTime));
			ad_para.setOperid(PublicUtil.getOper(request).getOperId());
			int r = Arrays.binarySearch(Constants.SERIALNUMBER_ARRAY, ad_para.getAdarea());
			if(r>=0){
				List<Ad_para> adlist = commonDao.findByHql("from Ad_para where adarea = ? and validflag = 1 order by adserial asc", ad_para.getAdarea());
				List<Integer> seriallist = new ArrayList<Integer>();
				for (Ad_para adpara : adlist) {
					seriallist.add((int)adpara.getAdserial());
				}
				//寻找最小未占用的滚动广告序列号
				result = PublicUtil.whichOneIsEmpty(seriallist);
				if(result == 0){
					return false;
				}else{
					ad_para.setAdserial((short)result);
				}
			}else{
				ad_para.setAdserial((short)1);
			}
			adImageDao.insert(image);
			commonDao.insert(ad_para);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	@Override
	public boolean updateAdPara(Ad_para ad_para, HttpServletRequest request) {
		
		String imageurl = request.getParameter("image1");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		Ad_image image = new Ad_image();
		image.setImageid(UtilDate.getNowDateNo_()+(new Random().nextInt(89)+10));
		image.setAdid(ad_para.getAdid());
		image.setImagepath(imageurl);
		image.setUploadtime(new Date());
		try {
			ad_para.setAdimageid(image.getImageid());
			ad_para.setLastUpdateTime(new Date());
			ad_para.setAdbegintime(UtilDate.parseDateForDatepatternString(startTime));
			ad_para.setAdendtime(UtilDate.parseDateForDatepatternString(endTime));
			ad_para.setOperid(PublicUtil.getOper(request).getOperId());
			adImageDao.insert(image);
			commonDao.update(ad_para);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean deleteAdPara(String adid, HttpServletRequest request) {
		
		String sql = "update tb_ad_para set validflag=0,lastUpdateTime=?,operid=? where adid in("+adid+")";
		return commonDao.executeBySql(sql, new Date(),PublicUtil.getOper(request).getOperId());
	}
	
}
