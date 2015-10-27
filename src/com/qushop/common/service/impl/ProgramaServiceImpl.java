package com.qushop.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.common.entity.Programa;
import com.qushop.common.service.ProgramaService;

/**
 * 
 * @author xie
 *
 */
@Service
public class ProgramaServiceImpl implements ProgramaService {

	@Resource
	CommonDao<Programa> commonDao;
	
	@Override
	public List<Programa> getProgramaByProgramaType(Integer typeId) {
		
		List<Programa> programasList = commonDao.findByHql("FROM Programa where programaType=?", typeId);
		
		return programasList;
	}

	@Override
	public void updateProgramaObject(Programa programa) {
		commonDao.update(programa);
	}

}
