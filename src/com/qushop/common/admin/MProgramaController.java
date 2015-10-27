package com.qushop.common.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qushop.common.entity.Programa;
import com.qushop.common.service.ProgramaService;
import com.qushop.common.util.DwzUtil;



@Controller
@RequestMapping("/manage/programa")
public class MProgramaController {

	@Resource
	protected ProgramaService programaService;
	
	@RequestMapping("getProgramaByProgramaId.do")
	public ModelAndView getProgramaByProgramaId(Integer programaTypeId,HttpServletRequest request)
	{
		List<Programa> programasList = programaService.getProgramaByProgramaType(programaTypeId);
		ModelAndView mv = new ModelAndView("admin/editPrograma");
		if(programasList!=null && programasList.size()>0)
		{
			mv.addObject("programa", programasList.get(0));
		}
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("updatePrograma.do")
	public Object updatePrograma(Programa programa){
		
		try {
			programaService.updateProgramaObject(programa);
			return DwzUtil.opSuccess("操作成功", "");
		} catch (Exception e) {
			e.printStackTrace();
			return DwzUtil.opFailed("操作失败", "");
		}
	}
}
