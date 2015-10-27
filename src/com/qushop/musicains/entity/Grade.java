package com.qushop.musicains.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 评分表
 * @author xie
 *
 */
@Entity
@Table(name="tb_grade")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Grade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 797244091727864169L;
	@Id
	@GenericGenerator(name="gradeId",strategy="assigned")
	@GeneratedValue(generator="gradeId")
	private String gradeId;//评分ID
	private String userId;//用户ID
	private Date gradeTime;//评分时间
	private Short gradeType;//评分类型 1表示回购 2租赁
	private Short score; //分数 1-5
	private String description;//说明（具体评价）
	private Short validflag;
	
	
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getGradeTime() {
		return gradeTime;
	}
	public void setGradeTime(Date gradeTime) {
		this.gradeTime = gradeTime;
	}
	public Short getGradeType() {
		return gradeType;
	}
	public void setGradeType(Short gradeType) {
		this.gradeType = gradeType;
	}
	public Short getScore() {
		return score;
	}
	public void setScore(Short score) {
		this.score = score;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Short getValidflag() {
		return validflag;
	}
	public void setValidflag(Short validflag) {
		this.validflag = validflag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
