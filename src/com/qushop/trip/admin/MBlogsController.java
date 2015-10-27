package com.qushop.trip.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.trip.entity.Blogs;
import com.qushop.trip.service.BlogsService;


@Controller
@RequestMapping("/manage/blogs")
public class MBlogsController {

	@Resource
	BlogsService blogsService;
	
	@RequestMapping("getBlogsList.do")
	public String getBlogsList(String action,HttpServletRequest request)
	{
		
		List<Blogs> blogsList = blogsService.getBlogsByMethod(0, 0, PublicUtil.getOper(request));
		request.setAttribute("blogsList", blogsList);
		if("dialog".equals(action))
		{
			return "admin/lookDialog/lookUpblogs";
		}
		return "admin/blogsList";
	}
	
	@RequestMapping("getBlogsDetail.do")
	public String getBlogsDetail(String[] blogid,HttpServletRequest request)
	{
		List<Blogs> blogsList = blogsService.getBlogsByMethod(1, 0, PublicUtil.getOper(request), blogid[0]);
		request.setAttribute("blogs", blogsList.get(0));
		return "admin/validationBlogs";
	}
	
	@RequestMapping("validation.do")
	@ResponseBody
	public Object validation(String blogid,HttpServletRequest request)
	{
		List<Blogs> blogsList = blogsService.getBlogsByMethod(1, 0, PublicUtil.getOper(request), blogid);
		Blogs blogs = blogsList.get(0);
		blogs.setCheckflag((short)1);
		boolean bool = blogsService.updateBlogs(blogs, PublicUtil.getOper(request));
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "blogs");
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
	
	@RequestMapping("deleteBlogs.do")
	@ResponseBody
	public Object deleteBlogs(String[] blogid, HttpServletRequest request)
	{
		boolean bool = blogsService.deleteBlogs(blogid, PublicUtil.getOper(request));
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "blogs");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
}











