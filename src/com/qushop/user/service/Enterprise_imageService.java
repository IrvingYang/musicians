package com.qushop.user.service;

import java.util.List;

import com.qushop.user.entity.Enterprise_image;

public interface Enterprise_imageService {

	
	public void addEnterprise_image(Enterprise_image image);
	
	public boolean deleteRellayImageByUserId(String userId);
	
	/**
	 * @param type 1通过用户id查询图片
	 * @param params
	 * @return
	 */
	public List<Enterprise_image> getImageByMethod(int type,String...params);
	
}
