package com.qushop.trip.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.util.UtilDate;
import com.qushop.trip.entity.Blogs;
import com.qushop.trip.entity.Entityimage;
import com.qushop.trip.service.BlogsService;
import com.qushop.trip.service.BlogsTypeService;
import com.qushop.trip.service.EntityimageService;
import com.qushop.trip.service.TripModuleRecommendService;
import com.qushop.user.entity.Oper;
import com.qushop.user.entity.User;
import com.qushop.user.service.UserService;


@Service
public class BlogsServiceImpl implements BlogsService {

	@Resource
	CommonDao<Blogs> commonDao;
	
	@Resource
	BlogsTypeService blogsTypeService;
	
	@Resource
	EntityimageService entityimageService;
	
	@Resource
	UserService userService;
	
	@Resource
	TripModuleRecommendService tripModuleRecommendService;
	
	@Override
	public List<Blogs> getBlogsByMethod(int type, int maxCount, Oper oper,
			String... params) {
		
		List<Blogs> blogsList = new ArrayList<Blogs>();
		
		switch (type) {
		case 0:
			if(oper!=null)
			{
				blogsList = commonDao.findByHql("from Blogs where validflag = 1");
			}
			else
			{
				blogsList = commonDao.findByHql("from Blogs where checkflag=1 and validflag = 1");
			}
			break;
		case 1:
			blogsList = commonDao.findByHql("from Blogs where blogid = ? and validflag = 1", params[0]);
			break;
		case 2:
			blogsList = commonDao.findByHql("from Blogs where blogstypeid in("+params[0]+") and validflag = 1");
			break;
		default:
			break;
		}
		
		for (Blogs blogs : blogsList) {
			blogs.setBlogstype(blogsTypeService.getBlogstypeByMethod(1, 0, oper, blogs.getBlogstypeid()).get(0));
			blogs.setEntityimage(entityimageService.getEntityimagesByMethod(0, 0, oper, "03", blogs.getBlogid()));
			blogs.setUser((User) userService.getUserByMethod(11, blogs.getUserid()).get(0));
		}
		
		return blogsList;
	}

	@Override
	public boolean addBlogs(Blogs blogs, String imagepath, Oper oper) {
		
		try {
			Entityimage entityimage = new Entityimage();
			
			entityimage.setEntitytype("03");
			entityimage.setImagepath(imagepath);
			entityimage.setImgType((short)4);
			entityimage.setUploadtime(new Date());
			
			blogs.setBlogid(UtilDate.getNowDateNo_()+blogs.getBlogstypeid()+(new Random().nextInt(8999)+1000));
			blogs.setReadcount((short)0);
			blogs.setCreatetime(new Date());
			blogs.setCheckflag((short)0);
			blogs.setLastUpdateTime(new Date());

			entityimage.setImageid("03"+(new Random().nextInt(8999)+1000)+""+(new Random().nextInt(8999)+1000));
			entityimage.setEntityid(blogs.getBlogid());
			
			blogs.setCoverimageid(entityimage.getImageid());
			blogs.setValidflag((short)1);
			
			commonDao.insert(entityimage);
			commonDao.insert(blogs);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateBlogs(Blogs blogs, Oper oper) {
		
		try {
			blogs.setOperid(oper.getOperId());
			blogs.setLastUpdateTime(new Date());
			commonDao.update(blogs);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteBlogs(String[] blogid,Oper oper) {
		
		try {
			for(int i=0;i<blogid.length;i++) {
				commonDao.executeBySql("update tb_blogs set validflag=0,operid=?,lastUpdateTime=? where blogid=?", oper.getOperId(),new Date(),blogid[i]);
				blogid[i]="03and"+blogid[i];
			}
			tripModuleRecommendService.deleteTripModuleRecommend(blogid, oper);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
