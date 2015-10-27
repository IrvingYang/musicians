package com.qushop.trip.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.common.util.UtilDate;
import com.qushop.trip.entity.Entityimage;
import com.qushop.trip.entity.TripModuleRecommend;
import com.qushop.trip.service.EntityimageService;
import com.qushop.trip.service.TripModuleRecommendService;


@Controller
@RequestMapping("/trip/modulerecommend")
public class MTripModuleRecommendController {

	@Resource
	TripModuleRecommendService moduleRecommendService;
	
	@Resource
	EntityimageService entityimageService;
	
	@RequestMapping("getTripModuleRecommendList.do")
	public String getTripModuleRecommendList(HttpServletRequest request)
	{
		List<TripModuleRecommend> moduleRecommendsList = moduleRecommendService.getTripModuleRecommendByMethod(0, 0, PublicUtil.getOper(request));
		request.setAttribute("moduleRecommendsList", moduleRecommendsList);
		return "admin/tripModuleRecommend";
	}
	
	@RequestMapping("toEdit.do")
	public String toEdit(String action,String[] entitytypeAndentityid,HttpServletRequest request)
	{
		if("update".equals(action))
		{
			String[] typeAndId = entitytypeAndentityid[0].split("and");
			List<TripModuleRecommend> moduleRecommendsList = moduleRecommendService.getTripModuleRecommendByMethod(1, 0, PublicUtil.getOper(request), typeAndId[0],typeAndId[1]);
			List<Entityimage> entityimagesList = entityimageService.getEntityimagesByMethod(0, 0,  PublicUtil.getOper(request), typeAndId[0],typeAndId[1]);
			if(entityimagesList.size()>0)
			{
				request.setAttribute("imagepath", entityimagesList.get(0).getImagepath());
			}
			request.setAttribute("moduleRecommend", moduleRecommendsList.get(0));
			request.setAttribute("action", "update");
		}
		else if("add".equals(action))
		{
			request.setAttribute("action", "add");
		}
		return "admin/editTripModuleRecommend";
	}

	@RequestMapping("addModuleRecommendline.do")
	@ResponseBody
	public Object addModuleRecommendline(TripModuleRecommend moduleRecommend,String recommendImage,HttpServletRequest request)
	{
		String entityId = request.getParameter("entity.entityid");
		String starttime = request.getParameter("startTime");
		String endtime = request.getParameter("endTime");
		
		List<TripModuleRecommend> moduleRecommendsList = moduleRecommendService.getTripModuleRecommendByMethod(1, 
				0, PublicUtil.getOper(request), moduleRecommend.getEntitytype(),entityId);

		boolean bool = false;
		
		if(moduleRecommendsList.size()>0)
		{
			TripModuleRecommend tripModuleRecommend = moduleRecommendsList.get(0);
			tripModuleRecommend.setRecommBeginTime(UtilDate.parseDateForDatepatternString(starttime));
			tripModuleRecommend.setRecommEndTime(UtilDate.parseDateForDatepatternString(endtime));
			bool = moduleRecommendService.updateTripModuleRecommend(tripModuleRecommend,recommendImage, PublicUtil.getOper(request));
		}
		else
		{
			moduleRecommend.setEntityid(entityId);
			moduleRecommend.setRecommBeginTime(UtilDate.parseDateForDatepatternString(starttime));
			moduleRecommend.setRecommEndTime(UtilDate.parseDateForDatepatternString(endtime));
			bool = moduleRecommendService.addTripModuleRecommend(moduleRecommend, PublicUtil.getOper(request), recommendImage);
		}
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "activity");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("updateModuleRecommendline.do")
	@ResponseBody
	public Object updateModuleRecommendline(TripModuleRecommend moduleRecommend,String recommendImage,HttpServletRequest request)
	{
		String entityId = request.getParameter("entity.entityid");
		String starttime = request.getParameter("startTime");
		String endtime = request.getParameter("endTime");
		
		moduleRecommend.setEntityid(entityId);
		moduleRecommend.setRecommBeginTime(UtilDate.parseDateForDatepatternString(starttime));
		moduleRecommend.setRecommEndTime(UtilDate.parseDateForDatepatternString(endtime));
		boolean bool = false;
		bool = moduleRecommendService.updateTripModuleRecommend(moduleRecommend,recommendImage, PublicUtil.getOper(request));
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "moduleRecommend");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("deleteModuleRecommendline.do")
	@ResponseBody
	public Object deleteModuleRecommendline(String[] entitytypeAndentityid,HttpServletRequest request)
	{
		boolean bool = false;
		bool = moduleRecommendService.deleteTripModuleRecommend(entitytypeAndentityid, PublicUtil.getOper(request));
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "moduleRecommend");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
}
