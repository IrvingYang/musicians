package com.qushop.user.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.Constants;
import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.user.entity.Oper;
import com.qushop.user.entity.Role;
import com.qushop.user.service.OperService;
import com.qushop.user.service.RoleService;


@Controller
@RequestMapping("/manage/oper")
public class OperController {

	@Resource
	OperService operService;
	
	@Resource
	RoleService roleService;
	
	@RequestMapping(value="login.action",method=RequestMethod.POST)
	public String login(Oper oper, HttpServletRequest request,HttpServletResponse response){
		
		oper.setPassword(DigestUtils.md5Hex(oper.getPassword()+Constants.MAIN_KEY));
		Oper op = operService.login(oper, request);
		if(op==null){
			request.setAttribute("msg", "用户名或密码错误");
			return "admin/login";
		}
		request.getSession().setAttribute(Constants.OPER_USER,op);
		return "redirect:/manage/index.do";
	}
	@RequestMapping("logout.do")
	public String loginOut(HttpServletRequest request){
		Oper oper = (Oper) request.getSession().getAttribute(Constants.OPER_USER);
		if(oper!=null){
			operService.logout(oper);
			request.getSession().invalidate();
		}
		return "redirect:/manage/index.do";
	}
	@RequestMapping(value="toLogin.action",method=RequestMethod.GET)
	public String toLogin(HttpServletRequest request,HttpServletResponse response){
		return "admin/login";
	}
	@RequestMapping("forbidden.do")
	public String forbidden(HttpServletRequest request){
		return "admin/forbidden";
	}
	
	@RequestMapping("operList.do")
	public String operList(HttpServletRequest request){
		Oper oper = PublicUtil.getOper(request);
		List<Oper> opersList = operService.getOperByMethod(0,PublicUtil.getOper(request),oper.getProviderId());
		request.setAttribute("operList", opersList);
		return "admin/OperList";
	}
	
	@RequestMapping("toAddOper.do")
	public Object toAddOper(Oper oper,HttpServletRequest request){

		List<Role> roleList = roleService.getRoleList("1", request);
		request.setAttribute("roleList", roleList);
		return "admin/addOper";
	}
	@RequestMapping("updatePassword.do")
	@ResponseBody
	public Object updatePassword(HttpServletRequest request,String oldpwd,String newpwd,String confirmpwd){
		
		if(!newpwd.equals(confirmpwd)){
			return DwzUtil.opFailed("两次密码输入不一致", "");
		}
		Oper oper = PublicUtil.getOper(request);
		String oldpwdsigned = DigestUtils.md5Hex(oldpwd+Constants.MAIN_KEY);
		if(!oper.getPassword().equals(oldpwdsigned)){
			return DwzUtil.opFailed("旧密码输入有误", "");
		}
		oper.setPassword(DigestUtils.md5Hex(newpwd+Constants.MAIN_KEY));
		if(operService.updateLoginPassword(oper)){
			oper.setPassword(DigestUtils.md5Hex(newpwd+Constants.MAIN_KEY));
			request.getSession().setAttribute(Constants.OPER_USER, oper);
			return DwzUtil.opSuccess("操作成功", "");
		}
		return DwzUtil.opFailed("操作失败", "");
		
	}
	
	@RequestMapping("toEditPassword.do")
	public Object toEditPassword(){
		return "admin/editPassword";
	}
	
	@RequestMapping("toEditInfo.do")
	public Object toEditDetailInfo(HttpServletRequest request){
		
		List<Oper> list= operService.getOperByMethod(1, PublicUtil.getOper(request));
		request.setAttribute("oper", list.get(0));
		return "admin/editDetailInfo";
	}
	
	
	@RequestMapping("addOper.do")
	@ResponseBody
	public Object addOper(Oper oper,HttpServletRequest request){

		if(operService.operExists(oper.getOperName())){
			return DwzUtil.opFailed("此操作员已经存在", "");
		}
		String roleId = request.getParameter("providerlookup.roleId");
		String providerId = request.getParameter("providerlookup.providerId");
		oper.setRoleId(roleId);
		oper.setProviderId(providerId);
		String rStr = operService.addOper(oper);
		if("success".equals(rStr)){
			return DwzUtil.opSuccess("操作成功", "OpertoRole");
		}
		return DwzUtil.opFailed(rStr, "");
	}
	
	@RequestMapping("deleteOper.do")
	@ResponseBody
	public Object deleteOper(String operId,HttpServletRequest request){

		Oper oper = PublicUtil.getOper(request);
		List<Oper> opersList = operService.getOperByMethod(1, oper, operId);
		if(opersList.size()>0){
			if(opersList.get(0).getOperName().trim().equals(oper.getOperName().trim())){
				return DwzUtil.opFailed("操作员不可操作自己", "OpertoRole");
			}
		}
		
		String rStr = operService.deleteOper(operId);
		if("success".equals(rStr)){
			return DwzUtil.opSuccess("操作成功", "OpertoRole");
		}
		return DwzUtil.opFailed(rStr, "");
	}
	@RequestMapping("updateDetailInfo.do")
	@ResponseBody
	public Object updateDetailInfo(String email,String telephone,String sex,HttpServletRequest request){
		
		boolean bool = operService.updateDetailInfo(email, sex, telephone, PublicUtil.getOper(request).getOperId());
		if(bool){
			return DwzUtil.opSuccess("操作成功", "OpertoInfo");
		}
		return DwzUtil.opFailed("操作失败", "OpertoInfo");
	}
	
	
	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex("123456"+Constants.MAIN_KEY));
	}
}
