package com.qushop.trip.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.trip.entity.Blogstype;
import com.qushop.trip.service.BlogsTypeService;

@Controller
@RequestMapping("/trip/blogstype")
public class BlogstypeController {
	
	
	@Resource
	BlogsTypeService blogsTypeService;

	@RequestMapping("getBlogstypeList.action")
	@ResponseBody
	public List<Blogstype> getBlogstypeList(HttpServletRequest request)
	{
		List<Blogstype> blogstypesList = blogsTypeService.getBlogstypeByMethod(0, 0, null);
		return blogstypesList;
	}
	
}
