package com.qushop.common.service;

import java.util.List;

import com.qushop.common.entity.Programa;

public interface ProgramaService {

	public List<Programa> getProgramaByProgramaType(Integer typeId);
	
	public void updateProgramaObject(Programa programa);
	
}
