package com.qushop.trip.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qushop.common.util.Hanyu;
import com.qushop.common.util.PublicUtil;
import com.qushop.trip.entity.Blogs;
import com.qushop.trip.service.BlogsService;
import com.qushop.user.entity.User_Ext_Personal;


@Controller
@RequestMapping("/trip/blogs")
public class BlogsController {

	@Resource
	BlogsService blogsService;
	
	
	@RequestMapping("getBlogsList.action")
	@ResponseBody
	public List<Blogs> getBlogsList(HttpServletRequest request)
	{
		
		List<Blogs> blogsList = blogsService.getBlogsByMethod(0, 0, null);
		return blogsList;
	}

	@RequestMapping("getBlogsDetail.action")
	@ResponseBody
	public Blogs getBlogs(String blogid, HttpServletRequest request)
	{
		List<Blogs> blogsList = blogsService.getBlogsByMethod(1, 0, null, blogid);
		return blogsList.get(0);
	}
	
	@RequestMapping(value="addBlogs.action",method=RequestMethod.POST)
	@ResponseBody
	public Object addBlogs(Blogs blogs, HttpServletRequest  request) throws IOException
	{
		User_Ext_Personal user_Ext_Personal = PublicUtil.getUserOfSession(request);
		if(user_Ext_Personal==null){
			return "needLogin";
		}
		blogs.setUserid(user_Ext_Personal.getUserId());
		
		MultipartHttpServletRequest httpServletRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = httpServletRequest.getFile("image");
		String fileName = file.getOriginalFilename();
		byte[] b = file.getBytes();
		File dirFile = new File(PublicUtil.getRootFileDirectory(request)+"/media_upload/personal_upload");
		if(!dirFile.exists())
		{
			dirFile.mkdirs();
		}
		String imagepath = "/media_upload/personal_upload/"+new Hanyu().getStringPinYin(blogs.getTitle())+fileName;
		File files = new File(PublicUtil.getRootFileDirectory(request)+imagepath);
		if(!files.exists())
		{
			dirFile.createNewFile();
		}
		
		FileCopyUtils.copy(b,files);
		
		boolean bool = blogsService.addBlogs(blogs,imagepath, null);
		
		return bool+"";
	}
	
	@RequestMapping("publishBlogs.html")
	public Object publishBlogs(){
		
		return "web/publishblogs";
	}
	
}
