package com.qushop.dict.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.dict.entity.State;
import com.qushop.dict.service.CityService;
import com.qushop.dict.service.StateService;


@Controller
@RequestMapping("/manage/state")
public class MStateController {

	@Resource
	StateService stateService;
	
	@Resource
	CityService cityService;
	
	
	@RequestMapping("getStateConnectCityList.do")
	public String getStateConnectCityList(HttpServletRequest request){
		
		List<State> statesList = stateService.getStateByMethod(2);
		request.setAttribute("statesList", statesList);
		return "admin/lookDialog/lookUpStateConnectCity";
	}
	
	@RequestMapping("getStateList.do")
	public String getStateList(String action,HttpServletRequest request){
		List<State> statesList = stateService.getStateByMethod(1);
		request.setAttribute("statesList", statesList);
		if("dialog".equals(action)){
			return "admin/lookDialog/lookState";
		}
		return "admin/vstateList";
	}
	
	@RequestMapping("toEditState.do")
	public String toEditState(String action,String[] stateId,HttpServletRequest request){
		
		if("add".equals(action)){
			request.setAttribute("action", "add");
		}
		else if("update".equals(action)){
			request.setAttribute("action", "update");
			List<State> statesList = stateService.getStateByMethod(3, stateId[0]);
			request.setAttribute("state", statesList.get(0));
		}

		return "admin/editvState";
	}
	
	@RequestMapping("saveOrUpdate.do")
	@ResponseBody
	public Object saveOrUpdate(String action,State state,HttpServletRequest request){
		
		boolean bool = false;
		state.setValidflag((short)1);
		state.setCountryId("086");
		if("add".equals(action)){
			bool = stateService.addState(state);
		}
		else if("update".equals(action)){
			bool = stateService.updateState(state);
		}

		if(bool){
			return DwzUtil.opFailed("操作成功", "state");
		}
		return DwzUtil.opFailed("操作失败", "");
	}
	
	@RequestMapping("deleteState.do")
	@ResponseBody
	public Object deleteState(String stateId,HttpServletRequest request){

		boolean bool = false;
		if(cityService.getCityByMethod(2, stateId).size()>0){
			return DwzUtil.opSuccess("该省下含有城市，不能删除", "");
		}
		
		bool = stateService.deleteState(stateId);

		if(bool){
			return DwzUtil.opSuccess("操作成功", "state");
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
	
}
