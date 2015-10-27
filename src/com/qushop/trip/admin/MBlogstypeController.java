package com.qushop.trip.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.trip.entity.Blogstype;
import com.qushop.trip.service.BlogsService;
import com.qushop.trip.service.BlogsTypeService;


@Controller
@RequestMapping("/manage/blogstype")
public class MBlogstypeController {

	@Resource
	BlogsTypeService blogsTypeService;
	
	@Resource
	BlogsService blogsService;
	
	@RequestMapping("getBlogstypeList.do")
	public String getBlogstypeList(HttpServletRequest request)
	{
		List<Blogstype> blogstypesList = blogsTypeService.getBlogstypeByMethod(0, 0, PublicUtil.getOper(request));
		request.setAttribute("blogstypesList", blogstypesList);
		return "admin/blogstype";
	}
	
	@RequestMapping("toEditBlogsType.do")
	public String getBlogstypeDetail(Integer opFlag,String[] blogstypeid,HttpServletRequest request)
	{
		if(opFlag.equals(1))
		{
			request.setAttribute("action", "add");
		}
		else
		{
			List<Blogstype> blogstypesList = blogsTypeService.getBlogstypeByMethod(1, 0, PublicUtil.getOper(request), blogstypeid[0]);
			request.setAttribute("blogstype", blogstypesList.get(0));
			request.setAttribute("action", "update");
		}
		return "admin/editBlogstype";
	}
	
	@RequestMapping("updateBlogstype.do")
	@ResponseBody
	public Object updateBlogstype(Blogstype blogstype,HttpServletRequest request)
	{
		boolean bool = blogsTypeService.updateBlogstype(blogstype, PublicUtil.getOper(request));
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "blogstype");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("addBlogstype.do")
	@ResponseBody
	public Object addBlogstype(Blogstype blogstype,HttpServletRequest request)
	{
		boolean bool = blogsTypeService.addBlogstype(blogstype, PublicUtil.getOper(request));
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "blogstype");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("deleteBlogstype.do")
	@ResponseBody
	public Object deleteBlogs(String blogstypeid,HttpServletRequest request)
	{
		
		if(blogsService.getBlogsByMethod(2, 0, PublicUtil.getOper(request), blogstypeid).size()>0)
		{
			return DwzUtil.opFailed("该类型下已经含有游记不能删除", "");
		}
		boolean bool = blogsTypeService.deleteBlogstype(blogstypeid, PublicUtil.getOper(request));
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "blogstype");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
}
