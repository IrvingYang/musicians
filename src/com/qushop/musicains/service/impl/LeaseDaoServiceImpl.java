package com.qushop.musicains.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.PagePojo;
import com.qushop.musicains.entity.Lease;
import com.qushop.musicains.entity.LeaseConfig;
import com.qushop.musicains.entity.Repo;
import com.qushop.musicains.service.LeaseDaoService;
import com.qushop.order.entity.Order_detail;
import com.qushop.order.entity.Order_list;
import com.qushop.order.service.Order_detailService;
import com.qushop.order.service.Order_listService;
import com.qushop.prod.service.Product_ext_shopService;
import com.qushop.user.entity.User;
import com.qushop.user.service.UserService;

@Service
public class LeaseDaoServiceImpl implements LeaseDaoService {

	@Resource
	CommonDao<Lease> commonDao;

	@Resource
	Product_ext_shopService ext_shopService;

	@Resource
	UserService userService;
	
	
	@Resource
	Order_detailService order_detailService;

	@Override
	public void saveLease(Lease lease) {
		Date now=new Date();
		lease.setCreateTime(now);
		//lease.setLastUpdateTime(now);
		lease.setStatus("01");
		lease.setValidflag(1);
		commonDao.insert(lease);
	}

	@Override
	public String updateLease(Lease lease) {
		commonDao.update(lease);
		return "success";
	}

	@Override
	public List<Lease> getLeaseList() {
		List<Lease> reposList = commonDao.findByHql("from Lease where validflag=1");
		for (Lease lease : reposList) {
			lease.setExt_shop(ext_shopService.getShopProductByMethod(5, lease.getProductId()).get(0));
			lease.setUser((User) userService.getUserByMethod(6, lease.getUserId()+"").get(0));
			Order_detail leasedetail = order_detailService.getOrderdetail(lease.getProductId(),lease.getOrderId(),(short)100);
			lease.setOrder_detail(leasedetail);
		}
		return reposList;
	}

	@Override
	public void deleteLeaseByLeaseId(String leaseId) {
		Lease lease = this.getLeaseByLeaseId(leaseId);
		if (lease != null) {
			lease.setValidflag((short) 0);
			lease.setLastUpdateTime(new Date());
			commonDao.update(lease);
		}
	}

	@Override
	public List<Lease> getLeaseListByUserId(String userId) {
		List<Lease> reposList = commonDao.findByHql("from Lease where userId=? and validflag=1", userId);
		for (Lease lease : reposList) {
			lease.setExt_shop(ext_shopService.getShopProductByMethod(5, lease.getProductId()).get(0));
			lease.setUser((User) userService.getUserByMethod(6, lease.getUserId()).get(0));
			Order_detail leasedetail = order_detailService.getOrderdetail(lease.getProductId(),lease.getOrderId(),(short)100);
			lease.setOrder_detail(leasedetail);
		}
		return reposList;
	}

	@Override
	public List<Lease> getLeasePagingListByUserId(String userId, Integer pageNo, Integer pageSize) {
		String sql = "from Lease where userId=? and validflag=1";

		List countList = commonDao.getSession().createSQLQuery("select count(*) " + sql).list();
		int totalCount = Integer.parseInt(countList.get(0) + "");
		PagePojo pagePojo = new PagePojo();
		pagePojo.setPagesize(pageSize);
		pagePojo.setTotalcount(totalCount);

		// if (pageNo <= 0) {
		// pageNo = 1;
		// }
		// if (pageNo >= pagePojo.getTotalpage()) {
		// pageNo = pagePojo.getTotalpage();
		// }
		// pagePojo.setPageno(pageNo);

		List<Lease> leaseRepos = commonDao.findPagingBySql("select * " + sql, pageNo - 1, pageSize, Lease.class);
		return leaseRepos;
	}

	@Override
	public Lease getLeaseByLeaseId(String leaseId) {
		List<Lease> leaseList = commonDao.findByHql("from Lease where validflag=1 and leaseId=?", Integer.valueOf(leaseId).intValue());
		for (Lease lease : leaseList) {
			lease.setExt_shop(ext_shopService.getShopProductByMethod(5, lease.getProductId()).get(0));
			lease.setUser((User) userService.getUserByMethod(6, lease.getUserId()+"").get(0));
			Order_detail leasedetail = order_detailService.getOrderdetail(lease.getProductId(),lease.getOrderId(),(short)100);
			lease.setOrder_detail(leasedetail);
		}
		return leaseList.get(0);
	}
	
}
