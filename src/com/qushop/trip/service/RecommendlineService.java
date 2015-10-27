package com.qushop.trip.service;

import java.util.List;

import com.qushop.trip.entity.Recommendline;
import com.qushop.user.entity.Oper;

public interface RecommendlineService {

	/**
	 * 
	 * @param type 0查询线路列表 ；1查询线路详细；2通过线路类型查询线路信息	3查询详细包含已删除
	 * @param maxCount
	 * @param oper
	 * @param params
	 * @return
	 */
	public List getRecommendlineByMethod(int type,int maxCount,Oper oper,String ...params);
	
	/**
	 * @param action “update”更新线路 “add”新增线路
	 * @param recommendline
	 * @param entityimage
	 * @param oper
	 * @return
	 */
	public boolean saveRecommendline(String action,Recommendline recommendline,String imagepath,Oper oper, String rootPath);
	
	
	/**
	 * 删除线路
	 * @param recommendlineid
	 * @param oper
	 * @return
	 */
	public boolean deleteRecommendline(String[] recommendlineid,Oper oper); 
	
}
