package com.qushop.dict.service;

import java.util.List;

import com.qushop.dict.entity.State;

public interface StateService {

	/**
	 * 
	 * @param type 1查询所有省份  2查询所有省份同时级联城市 3查询省级代码详细 4查询单个包含validflag=0
	 * @param params
	 * @return
	 */
	public List<State> getStateByMethod(int type,String ...params);
	
	public boolean addState(State state);
	
	public boolean deleteState(String stateId);
	
	public boolean updateState(State state);
	
}
