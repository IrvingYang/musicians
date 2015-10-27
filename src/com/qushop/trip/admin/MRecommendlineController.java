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
import com.qushop.trip.entity.Recommendline;
import com.qushop.trip.service.RecommendlineService;
import com.qushop.trip.service.RecommendlineapplyService;
import com.qushop.user.entity.Oper;


@Controller
@RequestMapping("/manage/recommendline")
public class MRecommendlineController {
	
	@Resource
	RecommendlineService recommendlineService;
	
	@Resource
	RecommendlineapplyService recommendlineapplyService;

	/**
	 * 列表
	 * @param action
	 * @param request
	 * @return
	 */
	@RequestMapping("getRecommendlineList.do")
	public String getRecommendlineList(String action,HttpServletRequest request)
	{
		List<Recommendline> recommendlinesList = recommendlineService.getRecommendlineByMethod(0, 0, PublicUtil.getOper(request));
		request.setAttribute("recommendlinesList", recommendlinesList);
		
		if("page".equals(action))
		{
			return "admin/recommendlineList";
		}
		else
		{
			return "admin/lookDialog/lookUpRecommendlineList";
		}
	}
	
	/**
	 * 编辑（添加修改）页
	 * @param opFlag
	 * @param activityId
	 * @param request
	 * @return
	 */
	@RequestMapping("toEditRecommendlinePage.do")
	public String toEditRecommendlinePage(Integer opFlag,String[] recommendlineid,HttpServletRequest request)
	{
		if(opFlag==1)
		{
			request.setAttribute("action", "add");
		}
		else if(opFlag==2)
		{
			List<Recommendline> activitiesList = recommendlineService.getRecommendlineByMethod(1, 0, PublicUtil.getOper(request), recommendlineid[0]);
			request.setAttribute("recommendline", activitiesList.get(0));
			request.setAttribute("action", "update");
		}
		return "admin/editRecommendline";
	}
	
	/**
	 * 删除多个
	 * @param activityId
	 * @param request
	 * @return
	 */
	@RequestMapping("deleteRecommendline.do")
	@ResponseBody
	public Object deleteRecommendline(String[] recommendlineid,HttpServletRequest request)
	{
		
		for (String rlid : recommendlineid) {
			
			List<Recommendline> recommendlinesList = recommendlineService.getRecommendlineByMethod(1, 0, PublicUtil.getOper(request), rlid);
			if(recommendlinesList.size()>0){
				if(recommendlinesList.get(0).getStatus()!=0 && recommendlinesList.get(0).getStatus()!=4)
				{
					List<Long> countList =  recommendlineapplyService.getRecommendlineApplyByMethod(2, 0, PublicUtil.getOper(request), rlid);

					if(!recommendlinesList.get(0).getProviderId().equals(PublicUtil.getOper(request).getProviderId()))
					{
						return DwzUtil.opFailed("操作失败:选中活动中包含非自己合作社活动", "");
					}
					if(countList.get(0).intValue()>0)
					{
						return DwzUtil.opFailed("操作失败:此线路已有用户报名", "");
					}	
				}
			}
		}
		
		boolean bool = recommendlineService.deleteRecommendline(recommendlineid, PublicUtil.getOper(request));
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "recommendline");
		}
		return DwzUtil.opFailed("操作失败", "");
	}

	/**
	 * 修改活动
	 * @param activity
	 * @param imagepath
	 * @param request
	 * @return
	 */
	@RequestMapping("updateRecommendline.do")
	@ResponseBody
	public Object updateRecommendline(Recommendline recommendline,String imagepath,HttpServletRequest request)
	{

		String linetypeid = request.getParameter("recommendlinetype.linetypeid");
		String cbeginapplytime = request.getParameter("cbeginapplytimes");
		String cendapplytime = request.getParameter("cendapplytimes");
		String departuretime = request.getParameter("departuretimes");
		recommendline.setBeginapplytime(UtilDate.parseDateForDatepatternString(cbeginapplytime));
		recommendline.setEndapplytime(UtilDate.parseDateForDatepatternString(cendapplytime));
		recommendline.setDeparturetime(UtilDate.parseDateForDatepatternString(departuretime));
		recommendline.setLinetypeid(linetypeid);
		boolean bool = recommendlineService.saveRecommendline("update", recommendline, imagepath,
				PublicUtil.getOper(request), PublicUtil.getRootFileDirectory(request));
		
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "recommendline");
		}
		return DwzUtil.opFailed("操作失败", "");
	}

	/**
	 * 添加活动
	 * @param activity
	 * @param imagepath
	 * @param request
	 * @return
	 */
	@RequestMapping("addRecommendline.do")
	@ResponseBody
	public Object addRecommendline(Recommendline recommendline,String imagepath,HttpServletRequest request)
	{
		String linetypeid = request.getParameter("recommendlinetype.linetypeid");
		String cbeginapplytime = request.getParameter("cbeginapplytimes");
		String cendapplytime = request.getParameter("cendapplytimes");
		String departuretime = request.getParameter("departuretimes");
		recommendline.setBeginapplytime(UtilDate.parseDateForDatepatternString(cbeginapplytime));
		recommendline.setEndapplytime(UtilDate.parseDateForDatepatternString(cendapplytime));
		recommendline.setDeparturetime(UtilDate.parseDateForDatepatternString(departuretime));
		recommendline.setLinetypeid(linetypeid);
		
		boolean bool = recommendlineService.saveRecommendline("add", recommendline, imagepath,
				PublicUtil.getOper(request), PublicUtil.getRootFileDirectory(request));
		
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "recommendline");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	/**
	 * 活动业务流程
	 * @param action
	 * @param activityid
	 * @param request
	 * @return
	 */
	@RequestMapping("recommendlineBusiness.do")
	@ResponseBody
	public Object recommendlineBusiness(String action,String recommendlineid,HttpServletRequest request)
	{
		
		boolean bool = false;
		Oper oper = PublicUtil.getOper(request);
		List<Recommendline> recommendlinesList = recommendlineService.getRecommendlineByMethod(1, 0, oper, recommendlineid);
		
		if(recommendlinesList.size()>0)
		{
			Recommendline recommendline = recommendlinesList.get(0);
			if(!recommendline.getProviderId().equals(oper.getProviderId()))
			{
				return DwzUtil.opFailed("操作失败:您不是此合作社管理员", "");
			}
			if("signupStart".equals(action))
			{
				if(recommendline.getStatus()!=0)
				{
					return DwzUtil.opFailed("操作失败:此旅游线路不是未开始状态", "");
				}
				recommendline.setStatus((short)1);
			}
			else if("signupEnd".equals(action))
			{
				if(recommendline.getStatus()!=1)
				{
					return DwzUtil.opFailed("操作失败:此旅游线路不是报名状态", "");
				}
				recommendline.setStatus((short)2);
			}
			else if("begin".equals(action))
			{
				if(recommendline.getStatus()!=2)
				{
					return DwzUtil.opFailed("操作失败:此旅游线路不是结束报名状态", "");
				}
				recommendline.setStatus((short)3);
			}
			else if("end".equals(action))
			{
				if(recommendline.getStatus()!=3)
				{
					return DwzUtil.opFailed("操作失败:此旅游线路不是开始状态", "");
				}
				recommendline.setStatus((short)4);
			}
			else
			{
				return DwzUtil.opFailed("操作失败", "");
			}
			bool = recommendlineService.saveRecommendline("update", recommendline, null, oper, null);
		}
		else
		{
			bool = false;
		}
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "recommendline");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
}
