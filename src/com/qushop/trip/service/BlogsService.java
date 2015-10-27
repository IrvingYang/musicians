package com.qushop.trip.service;

import java.util.List;

import com.qushop.trip.entity.Blogs;
import com.qushop.user.entity.Oper;

public interface BlogsService {
	
	/**
	 * 
	 * @param type 0查询游记列表；1查询游记详细；2通过类型查询游记
	 * @param maxCount
	 * @param oper
	 * @param params
	 * @return
	 */
	public List<Blogs> getBlogsByMethod(int type,int maxCount,Oper oper,String ...params);
	
	public boolean addBlogs(Blogs blogs, String imagepath, Oper oper);
	
	public boolean updateBlogs(Blogs blogs,Oper oper);
	
	public boolean deleteBlogs(String[] blogid,Oper oper);
	
}
