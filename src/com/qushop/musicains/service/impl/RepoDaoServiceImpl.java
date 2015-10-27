package com.qushop.musicains.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.PagePojo;
import com.qushop.musicains.entity.Repo;
import com.qushop.musicains.service.RepoDaoService;
import com.qushop.prod.entity.Product_ext_shop;
import com.qushop.prod.service.Product_ext_shopService;
import com.qushop.user.entity.User;
import com.qushop.user.service.UserService;

@Service
public class RepoDaoServiceImpl implements RepoDaoService {

	@Resource
	CommonDao<Repo> commonDao;

	@Resource
	Product_ext_shopService ext_shopService;

	@Resource
	UserService userService;

	@Override
	public void saveRepo(Repo repo) {
		repo.setLastUpdateTime(new Date());
		commonDao.insert(repo);
	}

	@Override
	public void approveRepo(Repo repo, String operId) {
		repo.setStatus("01");
		repo.setOperId(operId);
		repo.setLastUpdateTime(new Date());
		commonDao.update(repo);
	}
	
	@Override
	public void declineRepo(Repo repo, String operId) {
		String delcine="00";
		repo.setStatus(delcine);
		repo.setOperId(operId);
		repo.setLastUpdateTime(new Date());
		commonDao.update(repo);
	}
	
	@Override
	public void shippingRepo(Repo repo) {
		String shipping="02";
		repo.setStatus(shipping);
		repo.setLastUpdateTime(new Date());
		commonDao.update(repo);
	}
	
	@Override
	public void finishRepo(Repo repo,String operId) {
		String shipping="05";
		repo.setStatus(shipping);
		repo.setOperId(operId);
		repo.setLastUpdateTime(new Date());
		commonDao.update(repo);
	}

	@Override
	public Repo getRepoByRepoId(String repoId) {
		List<Repo> reposList = commonDao.findByHql("from Repo where validflag=1 and repoId=?", repoId);
		if (reposList != null && reposList.size() > 0) {
			return reposList.get(0);
		}
		return null;
	}

	@Override
	public void deleteRepoByRepoIds(String repoId, String operId) {
		Repo repo = this.getRepoByRepoId(repoId);
		if (repo != null) {
			repo.setValidflag((short) 0);
			repo.setLastUpdateTime(new Date());
			commonDao.update(repo);
		}
	}

	@Override
	public List<Repo> getRepoList(String providerId) {
		List<Repo> reposList = commonDao.findByHql("from Repo where providerId=? and validflag=1", providerId);
		for (Repo repo : reposList) {
			repo.setExt_shop(ext_shopService.getShopProductByMethod(5, repo.getProductId()).get(0));
			repo.setUser((User) userService.getUserByMethod(6, repo.getUserId()).get(0));
		}
		return reposList;
	}

	@Override
	public List<Repo> getRepoPagingListByUserId(String userId, int pageNo, int pageSize) {
		String sql = "from Repo where userId=? and validflag=1";

		List countList = commonDao.getSession().createSQLQuery("select count(*) " + sql).list();
		int totalCount = Integer.parseInt(countList.get(0) + "");
		PagePojo pagePojo = new PagePojo();
		pagePojo.setPagesize(pageSize);
		pagePojo.setTotalcount(totalCount);

//		if (pageNo <= 0) {
//			pageNo = 1;
//		}
//		if (pageNo >= pagePojo.getTotalpage()) {
//			pageNo = pagePojo.getTotalpage();
//		}
//		pagePojo.setPageno(pageNo);

		List<Repo> userRepos = commonDao.findPagingBySql("select * " + sql, pageNo - 1, pageSize, Repo.class);
		return userRepos;
	}

}
