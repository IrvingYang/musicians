package com.qushop.musicains.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qushop.common.dao.CommonDao;
import com.qushop.musicains.entity.Grade;
import com.qushop.musicains.service.GradeService;

@Service
public class GradeServiceImpl implements GradeService {

	@Resource
	CommonDao<Grade> commonDao;
	
	@Override
	public String saveGrade(Grade grade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteGrade(String gradeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
