package com.qushop.trip.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.trip.entity.Recommendline;
import com.qushop.trip.entity.Recommendlinetype;
import com.qushop.trip.service.RecommendlineService;
import com.qushop.trip.service.RecommendlinetypeService;


@Controller
@RequestMapping("/manage/recommendlinetype")
public class MRecommendlinetypeController {

	
	@Resource
	RecommendlinetypeService recommendlinetypeService;
	
	@Resource
	RecommendlineService recommendlineService;
	
	/**
	 * 列表
	 * @param action
	 * @param request
	 * @return
	 */
	@RequestMapping("getRecommendlinetype.do")
	public String getRecommendlinetype(String action,HttpServletRequest request)
	{
		List<Recommendlinetype> recommendlinetypesList = recommendlinetypeService.getRecommendlineTypeByMethod(0, null);
		request.setAttribute("recommendlinetypesList", recommendlinetypesList);
		if("page".equals(action))
		{
			return "admin/recommendlinetype";
		}
		else
		{
			return "admin/lookDialog/lookUpRecommendlinetype";
		}
	}
	
	/**
	 * 编辑（添加修改）页
	 * @param opFlag
	 * @param activityId
	 * @param request
	 * @return
	 */
	@RequestMapping("toEditRecommendlinetypePage.do")
	public String toEditRecommendlinetypePage(Integer opFlag,String[] linetypeid,HttpServletRequest request)
	{
		if(opFlag==1)
		{
			request.setAttribute("action", "add");
		}
		else if(opFlag==2)
		{
			List<Recommendlinetype> recommendlinetypesList = recommendlinetypeService.getRecommendlineTypeByMethod(1, null, linetypeid[0]);
			request.setAttribute("recommendlinetype", recommendlinetypesList.get(0));
			request.setAttribute("action", "update");
		}
		return "admin/editRecommendLinetype";
	}
	
	/**
	 * 删除多个
	 * @param activityId
	 * @param request
	 * @return
	 */
	@RequestMapping("deleteRecommendlinetype.do")
	@ResponseBody
	public Object deleteRecommendlinetype(String linetypeid,HttpServletRequest request)
	{
		
		List<Recommendline> list = recommendlineService.getRecommendlineByMethod(2, 0, PublicUtil.getOper(request), linetypeid);
		if(list.size()>0)
		{
			return DwzUtil.opFailed("操作失败该类型下含有线路，不能删除", "");
		}
		boolean bool = recommendlinetypeService.deleteRecommendlineType(linetypeid, PublicUtil.getOper(request));
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "recommendlinetype");
		}
		return DwzUtil.opFailed("操作失败", "");
	}

	/**
	 * 修改类型
	 * @param activity
	 * @param imagepath
	 * @param request
	 * @return
	 */
	@RequestMapping("updateRecommendlinetype.do")
	@ResponseBody
	public Object updateRecommendlinetype(Recommendlinetype recommendlinetype,String imagepath,HttpServletRequest request)
	{
		boolean bool = recommendlinetypeService.saveRecommendlineType("update", recommendlinetype, PublicUtil.getOper(request),imagepath);
		
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "recommendlinetype");
		}
		return DwzUtil.opFailed("操作失败", "");
	}

	/**
	 * 添加线路类型
	 * @param activity
	 * @param imagepath
	 * @param request
	 * @return
	 */
	@RequestMapping("addRecommendlinetype.do")
	@ResponseBody
	public Object addRecommendlinetype(Recommendlinetype recommendlinetype,String imagepath,HttpServletRequest request)
	{
		
		boolean bool = recommendlinetypeService.saveRecommendlineType("add", recommendlinetype, PublicUtil.getOper(request), imagepath);
		
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "recommendlinetype");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
}
