package com.qushop.dict.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.dict.entity.State;
import com.qushop.dict.service.CityService;
import com.qushop.dict.service.StateService;

@Service
public class StateServiceImpl implements StateService {

	@Resource
	CommonDao commonDao;
	
	@Resource
	CityService cityService;
	

	@Override
	public List<State> getStateByMethod(int type, String... params) {

		List<State> statesList = new ArrayList();
		
		switch (type) {
		case 1:
			statesList = commonDao.findByHql("from State where validflag=1");
			break;
		case 2:
			statesList = commonDao.findByHql("from State where validflag=1");
			for (State state : statesList) {
				List citys = commonDao.findByHql("from City where stateId = ? and validflag=1", state.getStateId());
				state.setCitysList(citys);
			}
			break;
		case 3:
			statesList = commonDao.findByHql("from State where stateId=? and validflag=1",params[0]);
			break;
		case 4:
			statesList = commonDao.findByHql("from State where stateId=?",params[0]);
			break;
				
		}
		return statesList;
	}


	@Override
	public boolean addState(State state) {

		List<State> statesList = commonDao.findPagingByHql("from State where order by stateId desc", 0, 1);
		if(statesList.size()<=0){
			state.setStateId("01");
		}else{
			State s = statesList.get(0);
			String bId = s.getStateId();
			int id = Integer.parseInt(bId)+1;
			String endId = "";
			for (int i = (id+"").length(); i < 2; i++) {
				endId+="0";
			}
			endId+=id;
			state.setStateId(endId);
		}
		try {
			commonDao.insert(state);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean deleteState(String stateId) {

		boolean bool = false;
		String sql = "update tb_state set validflag=0 where stateId in ("+stateId+")";
		bool = commonDao.executeBySql(sql);
		return bool;
	}


	@Override
	public boolean updateState(State state) {

		try {
			commonDao.update(state);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
