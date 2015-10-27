package com.qushop.common.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tb_programa")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Programa implements Serializable{

	@Id
	@GeneratedValue(generator="assigned")
	@GenericGenerator(name="assigned",strategy="assigned")
	private Integer programaId;
	private Integer programaType;
	private String programaTitle;
	private String programaContent;
	
	
	public Integer getProgramaId() {
		return programaId;
	}
	public void setProgramaId(Integer programaId) {
		this.programaId = programaId;
	}
	public Integer getProgramaType() {
		return programaType;
	}
	public void setProgramaType(Integer programaType) {
		this.programaType = programaType;
	}
	public String getProgramaTitle() {
		return programaTitle;
	}
	public void setProgramaTitle(String programaTitle) {
		this.programaTitle = programaTitle;
	}
	public String getProgramaContent() {
		return programaContent;
	}
	public void setProgramaContent(String programaContent) {
		this.programaContent = programaContent;
	}
	
}
