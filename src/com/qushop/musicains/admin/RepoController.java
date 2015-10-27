package com.qushop.musicains.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qushop.common.util.DwzUtil;
import com.qushop.common.util.PublicUtil;
import com.qushop.musicains.entity.Repo;
import com.qushop.musicains.entity.RepoPojo;
import com.qushop.musicains.service.RepoDaoService;
import com.qushop.user.service.UserService;

@Controller
@RequestMapping("/manage/repo")
public class RepoController {

	@Resource
	private RepoDaoService repoService;

	@Resource
	private UserService userService;

	@RequestMapping("/createRopo.do")
	@ResponseBody
	public void createRepo(RepoPojo repoPojo, HttpServletRequest request) {
		Repo repo = new Repo();
		repo.setUserId(repoPojo.getUserId());
		repo.setBankcard(repoPojo.getBankcard());
		repo.setEmail(repoPojo.getEmail());
		repo.setRepoType(repoPojo.getRepoType());
		repo.setTelephone(repoPojo.getTelephone());
		repo.setProductId(repoPojo.getProductId());
		repo.setValidflag((short) 1);
		repo.setTotalamt(repoPojo.getTotalamt());
		repoService.saveRepo(repo);
	}

	// @RequestMapping("/business.do")
	// @ResponseBody
	// public Object business(String[] repoId,String action,HttpServletRequest
	// request){
	//
	// if(repoId==null || repoId.length<=0){
	//
	// return DwzUtil.opFailed("操作失败", "");
	// }
	// /* 01：已提交申请
	// 02：用户已经发货
	// 03：商家已收货
	// 04：商家已拨款
	// 05：回购完成*/
	// String opRepoId = repoId[0];
	// Repo repo = repoService.getRepoByRepoId(opRepoId);
	// String msg = "";
	// //确认收货
	// if("receiveGoods".equals(action))
	// {
	// if(!"02".equals(repo.getStatus())){
	// return DwzUtil.opFailed("用户并未发货", "");
	// }
	// if(repo.getRepoType()==2)
	// {
	// /**积分乐星币=================================================================================================*/
	// repo.setStatus("05");
	// }
	// else
	// {
	// repo.setStatus("03");
	// }
	// }
	// else if("remittance".equals(action))
	// {
	// if(!"03".equals(repo.getStatus())){
	// return DwzUtil.opFailed("未确认收货", "");
	// }
	// repo.setStatus("04");
	// }
	// else if("finishBusiness".equals(action))
	// {
	// if(!"04".equals(repo.getStatus())){
	// return DwzUtil.opFailed("未确认拨款", "");
	// }
	// repo.setStatus("05");
	// }
	// else if("cancel".equals(action)){
	//
	// if(!"01".equals(repo.getStatus()) && !"05".equals(repo.getStatus()) &&
	// !"06".equals(repo.getStatus())){
	// return DwzUtil.opFailed("订单交易进行中", "");
	// }
	// repo.setStatus("06");
	// }
	// else if("delete".equals(action)){
	// if(!"01".equals(repo.getStatus()) && !"05".equals(repo.getStatus()) &&
	// !"06".equals(repo.getStatus())){
	// return DwzUtil.opFailed("订单交易进行中", "");
	// }
	// repo.setValidflag((short)0);
	// }
	// else
	// {
	// return DwzUtil.opFailed("操作失败", "");
	// }
	// msg =
	// repoService.updateRepo(repo,PublicUtil.getOper(request).getOperId());
	// if("success".equals(msg)){
	// return DwzUtil.opSuccess("操作成功", "repo");
	// }
	// else{
	// return DwzUtil.opSuccess("操作失败："+msg, "");
	// }
	// }
	// @RequestMapping("/removeRepo.do")
	// @ResponseBody
	// public Object removeRepo(String repoIds,HttpServletRequest request){
	//
	// String msg =
	// repoService.deleteRepoByRepoIds(repoIds,PublicUtil.getOper(request).getOperId());
	// if("success".equals(msg)){
	// return DwzUtil.opSuccess("操作成功！", "");
	// }
	// else{
	// return DwzUtil.opSuccess("操作失败："+msg, "");
	// }
	//
	// }

	@RequestMapping("/getRepoList.do")
	public String getRepoList(HttpServletRequest request) {

		List list = repoService.getRepoList(PublicUtil.getOper(request).getProviderId());
		request.setAttribute("repoList", list);
		return "/admin/sRepoList";
	}
}
