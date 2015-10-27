package com.qushop.trip.service;

import java.util.List;

import com.qushop.trip.entity.Blogstype;
import com.qushop.user.entity.Oper;

public interface BlogsTypeService {

	/**
	 * 
	 * @param type 0查询所有游记类型；1查询类型详细；
	 * @param maxCount
	 * @param oper
	 * @param params
	 * @return
	 */
	public List<Blogstype> getBlogstypeByMethod(int type,int maxCount,Oper oper,String ...params);
	
	public boolean addBlogstype(Blogstype blogstype,Oper oper);
	
	public boolean updateBlogstype(Blogstype blogstype,Oper oper);

	public boolean deleteBlogstype(String blogstypeid,Oper oper);
	
}
