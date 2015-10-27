package com.qushop.ad.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.qushop.ad.entity.Ad_para;

public interface Ad_paraService {

	/**
	 * 
	 * @param type 0：通过广告位查询广告
	 * @param adarea
	 * @param maxCount
	 * @return
	 */
	public List<Ad_para> getAd_ParaByArea(Integer type,String adarea,Integer maxCount);
	
	/**
	 * 
	 * @param type 0查询所有广告   1查询广告详细  2判断非滚动广告位占用情况
	 * @param params
	 * @return
	 */
	public List<Ad_para> getAd_ParaByMethod(int type,String...params);
	
	public boolean addAdPara(Ad_para ad_para,HttpServletRequest request);
	
	public boolean updateAdPara(Ad_para ad_para,HttpServletRequest request);
	
	public boolean deleteAdPara(String adid,HttpServletRequest request);

}
