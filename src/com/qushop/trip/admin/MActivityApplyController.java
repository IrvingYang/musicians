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
import com.qushop.trip.service.ActivityapplyService;



@Controller
@RequestMapping("/manage/activityapply")
public class MActivityApplyController {

	@Resource
	ActivityapplyService activityapplyService;
	
	@RequestMapping("getActivityApplyListByActivityId.do")
	@ResponseBody
	public Object getActivityApplyListByActivityId(String activityid,HttpServletRequest request)
	{
		List<Activityapply> activityappliesList = activityapplyService.getActivityApplyByMethod(0, 0, null, activityid);
		return activityappliesList;
	}
	
	@RequestMapping("toActivityApplyPage.do")
	public String toActivityApplyPage()
	{
		return "admin/activityApplyList";
	}
	
	@RequestMapping("toUpdateActivityAppyList.do")
	public Object toUpdateActivityAppyList(String useridAndActivityid,HttpServletRequest request)
	{
		request.setAttribute("useridAndActivityids", useridAndActivityid);
		return "admin/tripDialog/updateActivityApplyStatus";
	}
	
	@RequestMapping("updateActivityStatus.do")
	@ResponseBody
	public Object updateActivityStatus(String[] useridAndActivityid,Integer status,HttpServletRequest request)
	{
		String message = activityapplyService.updateStatus(useridAndActivityid,status,PublicUtil.getOper(request));
		if("success".equals(message))
		{
			return DwzUtil.opSuccess("操作成功", "activityapply");
		}
		return DwzUtil.opFailed("操作失败:"+message, "");
	}
	
	@RequestMapping("deleteActivityApply.do")
	@ResponseBody
	public Object deleteActivityApply(String[] useridAndActivityid,HttpServletRequest request)
	{
		String message = "";
		String userid = useridAndActivityid[0].split("and")[0];
		String activityid = useridAndActivityid[0].split("and")[1];
		List<Activityapply> activityappliesList = activityapplyService.getActivityApplyByMethod(1, 0, null, userid, activityid);
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
				message = activityapplyService.deleteActivityApply(userid, activityid, PublicUtil.getOper(request));
//			}
		}
		//反之
		else
		{
			message = "no this info";
		}
		if("success".equals(message))
		{
			return DwzUtil.opSuccess("操作成功", "activityapply");
		}
		return DwzUtil.opFailed("操作失败:"+message, "");
	}
	
//	@RequestMapping("activityApplyBusiness.do")
//	@ResponseBody
//	public Object activityApplyBusiness(String action,String[] useridAndActivityid,HttpServletRequest request)
//	{
//		String message = "action error";
//		String userid = useridAndActivityid[0].split("and")[0];
//		String activityid = useridAndActivityid[0].split("and")[1];
//		//获取报名详细
//		List<Activityapply> activityappliesList = activityapplyService.getActivityApplyByMethod(1, 0, null, userid, activityid);
//		//有数据
//		if(activityappliesList.size()>0)
//		{
//			Activityapply activityapply = activityappliesList.get(0);
//			//已经付款
//			if("alreadyPay".equals(action))
//			{
//				if(activityapply.getStatus()!=0)
//				{
//					
//				}
//				activityapply.setStatus(1);
//			}
//			//申请退款
//			else if("applyRefund".equals(action))
//			{
//				if(activityapply.getStatus()!=1)
//				{
//					
//				}
//				activityapply.setStatus(2);
//			}
//			//同意退款
//			else if("acceptRefund".equals(action))
//			{
//				if(activityapply.getStatus()!=2)
//				{
//					
//				}
//				activityapply.setStatus(3);
//			}
//			//已经退款
//			else if("alreadyRefund".equals(action))
//			{
//				if(activityapply.getStatus()!=3)
//				{
//					
//				}
//				activityapply.setStatus(4);
//			}
//			//确认加入
//			else if("confirmJoin".equals(action))
//			{
//				if(activityapply.getStatus()!=4)
//				{
//					
//				}
//				activityapply.setStatus(5);
//			}
//			//结束
//			else if("end".equals(action))
//			{
//				if(activityapply.getStatus()!=5)
//				{
//					
//				}
//				activityapply.setStatus(6);
//			}
//			else
//			{
//				return DwzUtil.opFailed("操作失败:"+message, "");
//			}
//			message = activityapplyService.updateActivityApply(activityapply, PublicUtil.getOper(request));
//		}
//		//反之
//		else
//		{
//			message = "no this info";
//		}
//		if("success".equals(message))
//		{
//			return DwzUtil.opSuccess("操作成功", "activityapply");
//		}
//		return DwzUtil.opFailed("操作失败:"+message, "");
//		
//	}
	
}
