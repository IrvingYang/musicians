package com.qushop.musicains.service;

import com.qushop.musicains.entity.Grade;

public interface GradeService {

	/**
	 * 删除评分
	 * @param grade
	 * @return
	 */
	public String saveGrade(Grade grade);
	
	public String deleteGrade(String gradeId);
	
}
