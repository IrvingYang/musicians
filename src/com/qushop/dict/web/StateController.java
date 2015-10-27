package com.qushop.dict.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.dict.entity.State;
import com.qushop.dict.service.StateService;


@Controller
@RequestMapping("/dict/state")
public class StateController {

	@Resource
	StateService service;
	
	
	@RequestMapping("getAllState.action")
	@ResponseBody
	public Object getAllState(){
		
		List<State> list =  service.getStateByMethod(1);
		return list;
	}
}
