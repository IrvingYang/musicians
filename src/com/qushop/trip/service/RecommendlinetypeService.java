package com.qushop.trip.service;

import java.util.List;

import com.qushop.trip.entity.Recommendlinetype;
import com.qushop.user.entity.Oper;

public interface RecommendlinetypeService {

	/**
	 * 
	 * @param type 0查询全部线路类型；1查询指定类型详细；
	 * @param oper
	 * @param params
	 * @return
	 */
	public List getRecommendlineTypeByMethod(int type,Oper oper,String...params);
	
	/**
	 * 保存线路类型
	 * @param action “update”更新；“add”新增
	 * @param recommendlinetype
	 * @param oper
	 * @return
	 */
	public boolean saveRecommendlineType(String action,Recommendlinetype recommendlinetype,Oper oper,String imagepath);
	
	/**
	 * 删除线路类型
	 * @param linetypeid
	 * @param oper
	 * @return
	 */
	public boolean deleteRecommendlineType(String linetypeid,Oper oper);
	
}
