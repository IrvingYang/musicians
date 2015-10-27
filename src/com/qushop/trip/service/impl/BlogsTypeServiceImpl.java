package com.qushop.trip.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.trip.entity.Blogstype;
import com.qushop.trip.service.BlogsTypeService;
import com.qushop.user.entity.Oper;


@Service
public class BlogsTypeServiceImpl implements BlogsTypeService {

	
	@Resource
	CommonDao<Blogstype> commonDao;
	
	

	@Override
	public List<Blogstype> getBlogstypeByMethod(int type, int maxCount,
			Oper oper, String... params) {
		
		List<Blogstype> blogstypesList = new ArrayList<Blogstype>();
		
		switch (type) {
		case 0:
			blogstypesList = commonDao.findByHql("from Blogstype where validflag=1");
			break;
		case 1:
			blogstypesList = commonDao.findByHql("from Blogstype where blogstypeid=? and  validflag=1",params[0]);
			break;

		default:
			break;
		}
		
		return blogstypesList;
	}

	@Override
	public boolean addBlogstype(Blogstype blogstype,Oper oper) {

		try {
			blogstype.setValidflag((short)1);
			blogstype.setOperid(oper.getOperId());
			blogstype.setLastUpdateTime(new Date());
			
			List<Blogstype> blogstypesList = commonDao.findPagingByHql("from Blogstype order by blogstypeid desc", 0,1);
			
			if(blogstypesList.size()<=0)
			{
				blogstype.setBlogstypeid("01");
			}
			else
			{
				String beginId = blogstypesList.get(0).getBlogstypeid();
				int middleId = Integer.parseInt(beginId)+1;
				String endId = "";
				for (int i = (middleId+"").length(); i < 2; i++) {
					endId+="0";
				}
				endId+=middleId;
				blogstype.setBlogstypeid(endId);
			}
			
			commonDao.insert(blogstype);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean updateBlogstype(Blogstype blogstype,Oper oper) {

		try {
			blogstype.setValidflag((short)1);
			blogstype.setOperid(oper.getOperId());
			blogstype.setLastUpdateTime(new Date());
			commonDao.update(blogstype);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteBlogstype(String blogstypeid,Oper oper) {

		try {
			String sql = "update tb_blogstype set validflag=0,operid=?,lastupdatetime=? where blogstypeid in ("+blogstypeid+")";
			commonDao.executeBySql(sql, oper.getOperId(),new Date());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
