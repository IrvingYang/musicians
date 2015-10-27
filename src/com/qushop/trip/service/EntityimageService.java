package com.qushop.trip.service;

import java.util.List;

import com.qushop.trip.entity.Entityimage;
import com.qushop.user.entity.Oper;

public interface EntityimageService {

	/**
	 * @param type 0通过entitytype,entityid查询图片列表 ； 1通过imageid查询图片信息 ；
	 * 	
	 * 
	 * @see
	 * 	1：活动大图(长方形)(730X360)
	 *	2：线路大图(长方形)(730X360)
	 *	3：线路中图(长方形)(340X210)
	 *	4：游记中图(长方形)(340X210)
	 *	5：推荐展示(正方形)(242X242)
	 * 
	 * 
	 * @param maxCount
	 * @param oper
	 * @param params
	 * @return
	 */
	public List<Entityimage> getEntityimagesByMethod(int type,int maxCount,Oper oper,String...params);
	
	/**
	 * 真实删除图片
	 * @param entityimage
	 * @return
	 */
	public String actualDelete(Entityimage entityimage);
	
	/**
	 * 新增图片数据
	 * @param entityimage
	 * @return
	 */
	public String addEntityImage(Entityimage entityimage);
	
}
