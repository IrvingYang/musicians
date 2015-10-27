package com.qushop.common.service;

import java.util.List;

import com.qushop.common.entity.Sms_box;

public interface SmsCheckOutService {

	/**
	 * 根据动作查询指定内容
	 * @param type 1根据手机查询最新发送验证码信息 
	 * @param params
	 * @return
	 */
	public List<Sms_box> getSmsCheckOutByMethod(int type,String...params);
	
	/**
	 * 修改验证码发送信息
	 * @return
	 */
	public boolean updateSmsCheckOut(Sms_box checkcode);
	
	/**
	 * 新增验证码发送信息
	 * @return
	 */
	public boolean addCheckOutInfo(Sms_box checkcode);
	
}
