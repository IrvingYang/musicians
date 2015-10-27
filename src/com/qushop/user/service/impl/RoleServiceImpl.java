package com.qushop.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.Constants;
import com.qushop.common.util.PublicUtil;
import com.qushop.provider.entity.Provider;
import com.qushop.provider.service.ProviderService;
import com.qushop.user.entity.Oper;
import com.qushop.user.entity.Role;
import com.qushop.user.entity.RoleAuthority;
import com.qushop.user.entity.UrlList;
import com.qushop.user.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	CommonDao commonDao;
	
	@Resource
	ProviderService providerService;
	
	@Override
	public List<Role> getRoleList(String type,HttpServletRequest request,String...params) {
		// TODO Auto-generated method stub
		List<Role> rolelist = null;
		String providerid = PublicUtil.getOper(request).getProviderId();
		
		switch (Integer.valueOf(type)){
			case 1:
				if(PublicUtil.getOper(request).getPartnerflag() == 1){
					rolelist = commonDao.findByHql("from Role where providerid = ? and validflag = 1 order by providerId asc", providerid);
				}else{
					rolelist = commonDao.findByHql("from Role where validflag = 1");
				}
				break;
			case 2:
				String[] roleIdAndProviderId = params[0].split("and");
				rolelist = commonDao.findByHql("from Role where  roleId = ? and providerId=? and validflag = 1", roleIdAndProviderId[0],roleIdAndProviderId[1]);
				//循环查找Role的权限
				for (Role role : rolelist) {
					List<RoleAuthority> roleauthoritylist = commonDao.findByHql("from RoleAuthority where roleid = ? and providerId=? and  validflag=1", role.getRoleId(),role.getProviderId());
					//循环读取UrlLink
					for(RoleAuthority roleauthority: roleauthoritylist){
						UrlList urllist = (UrlList) commonDao.findByHql("from UrlList where urlId = ? and validflag = 1", roleauthority.getUrlId()).get(0);
						roleauthority.setUrllist(urllist);
					}
					role.setRoleauthoritiesList(roleauthoritylist);					
				}
				break;
			case 3:
				String[] roleIdAndProviderId1 = params[0].split("and");
				rolelist = commonDao.findByHql("from Role where  roleId = ? and providerId=? and validflag = 0", roleIdAndProviderId1[0],roleIdAndProviderId1[1]);
				break;
			default:
				break;
		}
		for (Role role : rolelist) {
			Provider provider = providerService.getProviderByMethod(3,null, role.getProviderId()).get(0);
			role.setProvider(provider);				
		}

		return rolelist;
	}

	@Override
	@SuppressWarnings("all")
	public List getUrlList(List list, UrlList currurllist) {
		String parentid;
		if(currurllist==null){
			parentid=Constants.TOP_ROLEAUTHORITY_PARENT;
		}else{
			parentid = currurllist.getUrlId();
		}
		List<UrlList> urlList = commonDao.findByHql("from UrlList where parentId=? and validflag=1",parentid);


		for (UrlList url : urlList) {

			Map tempmap = new HashMap();
			if(currurllist == null){
				tempmap.put("root",url);
			}else{
				tempmap.put(currurllist.getParentId(),url);
			}
			
			list.add(tempmap);
			getUrlList(list, url);

		}
		return list;
	}
	
	@Override
	@SuppressWarnings("all")
	public List getUrlList2(List list,UrlList currentUrlList,Oper oper) {
		String parentid;
		if(currentUrlList==null){
			parentid=Constants.TOP_ROLEAUTHORITY_PARENT;
		}else{
			parentid = currentUrlList.getUrlId();
		}
		List<UrlList> urlList = commonDao.findByHql("from UrlList where parentId=? and validflag=1",parentid);
		List<UrlList> endurlList = new ArrayList();
		if(currentUrlList!=null && urlList!=null && urlList.size()>0){
			
			for (UrlList ulist : urlList) {
				for (RoleAuthority roleAuthority : oper.getRole().getRoleauthoritiesList()) {
					if(roleAuthority.getUrlId().equals(ulist.getUrlId()) && roleAuthority.getValidflag()==1)
					{
						endurlList.add(ulist);
					}
				}
			}
			currentUrlList.setUrlList(endurlList);
		}
		if(urlList.size()>0){
			for (UrlList url : urlList) {
				if(url.getParentId().equals(Constants.TOP_ROLEAUTHORITY_PARENT)){
					if(oper.getPartnerflag()==0)
					{
						list.add(url);
					}else
					{
						for (RoleAuthority roleAuthority : oper.getRole().getRoleauthoritiesList()) {
							if(roleAuthority.getUrlId().equals(url.getUrlId()) && roleAuthority.getValidflag()==1)
							{
								list.add(url);
							}
						}
					}
				}
				getUrlList2(list,url,oper);
			}
		}
		return list;
	}

	@Override
	public String addRole(Role role, Oper oper) {
		
		List<Role> urlLists = commonDao.findPagingByHql("from Role where providerId=? order by roleId desc",0,1,role.getProviderId());
		if(urlLists!=null && urlLists.size()>0){
			Role r = urlLists.get(0);
			Integer roleId = Integer.parseInt(r.getRoleId());
			if(roleId.equals(99)){
				return "idoverflow";
			}
			roleId+=1;
			String endRoleId = "";
			for (int i=(roleId+"").length();i<2;i++) {
				endRoleId+="0";
			}
			endRoleId+=roleId;
			role.setRoleId(endRoleId);
		}else{
			role.setRoleId("01");
		}
		try {
			commonDao.insert(role);
			List<UrlList> urlListsList = commonDao.findByHql("from UrlList");
			String providerId = role.getProviderId();
			String roleId = role.getRoleId();
			for (UrlList urlList : urlListsList) {
				RoleAuthority authority = new RoleAuthority();
				authority.setProviderId(providerId);
				authority.setUrlId(urlList.getUrlId());
				authority.setRoleId(roleId);
				authority.setValidflag((short)0);
				commonDao.insert(authority);
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public String deleteRole(String roleId) {
		
		//多个参数逗号拆分 例：“ 12345645and123456,123456and654321 ”
		String roleIds[] = roleId.split(",");
		for (String string : roleIds) {
			//然后拆分角色id和合作社id
			String[] key = string.split("and");
			try {
				commonDao.executeBySql("update tb_role set validflag=0 where roleId=? and providerId=?", key[0],key[1]);
				commonDao.executeBySql("update tb_role_authority set validflag=0 where roleId=? and providerId=?", key[0],key[1]);
			} catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
		}
		return "success";
	}

	@Override
	public String updateRolePermissions(String urlId, String roleId,
			String providerId, HttpServletRequest request) {

		try {
			if(urlId==null || "".equals(urlId)){
				commonDao.executeBySql("update tb_role_authority set validflag=0 where roleId=? and providerId=?",roleId,providerId);
			}else{
				commonDao.executeBySql("update tb_role_authority set validflag=0 where urlId not in("+urlId+") and roleId=? and providerId=?",roleId,providerId);
				commonDao.executeBySql("update tb_oper set loginStatus=0 where roleId=? and providerId=?", roleId,providerId);
				commonDao.executeBySql("update tb_role_authority set validflag=1 where urlId in("+urlId+") and roleId=? and providerId=?",roleId,providerId);
//				commonDao.findByHql("from RoleAuthority where urlId=? and roleId=? and providerId=?", params);
//				RoleAuthority authority = new RoleAuthority();
//				authority.setProviderId(providerId);
//				authority.setUrlId(urlId);
//				authority.setRoleId(roleId);
//				authority.setValidflag((short)0);
//				commonDao.insert(authority);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "success";
	}

	@Override
	public String deleteRoleByProviderId(String providerIds) {
		
		boolean bool = commonDao.executeBySql("update tb_role set validflag=0 where providerId in ("+providerIds+")");
		try {
			if(bool){
				return "success";
			}
			return "op failed";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
