package com.qushop.trip.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.trip.entity.Activityapply;
import com.qushop.trip.entity.Recommendline;
import com.qushop.trip.entity.Recommendlineapply;
import com.qushop.trip.service.RecommendlineService;
import com.qushop.trip.service.RecommendlineapplyService;


@Controller
@RequestMapping("/manage/recommendlineapply")
public class MRecommendlineApplyController {
	
	@Resource
	RecommendlineapplyService recommendlineapplyService;
	
	@Resource
	RecommendlineService recommendlineService;
	
	@RequestMapping("getRecommendlineApplyListByRecommendlineId.do")
	@ResponseBody
	public Object getActivityApplyListByActivityId(String recommendlineid,HttpServletRequest request)
	{
		List<Recommendlineapply> recommendlineappliesList = 
				recommendlineapplyService.getRecommendlineApplyByMethod(0, 0, PublicUtil.getOper(request), recommendlineid);
		return recommendlineappliesList;
	}
	
	@RequestMapping("toRecommendapplyPage.do")
	public String toRecommendapplyPage()
	{
		return "admin/recommendapplyList";
	}
	
	@RequestMapping("toUpdateRecommendlineAppyList.do")
	public Object toUpdateActivityAppyList(String useridAndRecommendlineid,HttpServletRequest request)
	{
		request.setAttribute("useridAndRecommendlineids", useridAndRecommendlineid);
		return "admin/tripDialog/updateRecommendlineApplyStatus";
	}
	
	@RequestMapping("updateRecommendlineApplyStatus.do")
	@ResponseBody
	public Object updateActivityStatus(String[] useridAndRecommendlineid,Integer status,HttpServletRequest request)
	{
		String recommendlineid = useridAndRecommendlineid[0].split("and")[1];
		Recommendline recommendline = (Recommendline) recommendlineService.getRecommendlineByMethod(1, 0, PublicUtil.getOper(request), recommendlineid).get(0);
		if(!recommendline.getProviderId().equals(PublicUtil.getOper(request).getProviderId()))
		{
			return DwzUtil.opFailed("操作失败:您不是此合作社管理员", "");
		}
		boolean bool = recommendlineapplyService.updateStatus(useridAndRecommendlineid, status, PublicUtil.getOper(request));
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "activityapply");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("deleteRecommendlineApply.do")
	@ResponseBody
	public Object deleteActivityApply(String[] useridAndRecommendlineid,HttpServletRequest request)
	{
		boolean bool = false;
		String userid = useridAndRecommendlineid[0].split("and")[0];
		String recommendlineid = useridAndRecommendlineid[0].split("and")[1];
		List<Activityapply> activityappliesList = recommendlineapplyService.getRecommendlineApplyByMethod(1, 0, PublicUtil.getOper(request), userid, recommendlineid);
		//有数据
		if(activityappliesList.size()>0)
		{
//			Activityapply activityapply = activityappliesList.get(0);
//			//不是业务结束状态
//			if(activityapply.getStatus()!=4 && activityapply.getStatus()!=6)
//			{
//				return DwzUtil.opFailed("操作失败:此报名信息是未完成状态", "");
//			}
//			else
//			{
			bool = recommendlineapplyService.deleteRecommendlineapply(userid, recommendlineid, PublicUtil.getOper(request));
//			}
		}
		//反之
		else
		{
			bool = false;
		}
		if(bool)
		{
			return DwzUtil.opSuccess("操作成功", "activityapply");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
}
