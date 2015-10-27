package com.qushop.common.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qushop.common.entity.Programa;
import com.qushop.common.service.ProgramaService;



@Controller
@RequestMapping("/common/programa")
public class ProgramaController {

	@Resource
	protected ProgramaService programaService;
	
	@ResponseBody
	@RequestMapping("getProgramaByProgramaId.action")
	public Object getProgramaByProgramaId(Integer programaTypeId,HttpServletRequest request)
	{
		List<Programa> programasList = programaService.getProgramaByProgramaType(programaTypeId);
		return programasList.get(0);
	}
}
