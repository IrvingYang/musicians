package com.qushop.trip.entity;

/***********************************************************************
 * Module:  tb_recommendlinedetails.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_recommendlinedetails
 ***********************************************************************/

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 推荐线路详情
 * 
 */
@Entity
@Table(name="tb_recommendlinedetails")
public class Recommendlinedetails {

	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String recommendlineid;
	private short date;
	private String breakfast;
	private String viewspot1;
	private String imageid1;
	private String imageid2;
	private String imageid3;
	private String lunch;
	private String viewspot2;
	private String imageid4;
	private String imageid5;
	private String imageid6;
	private String dinner;
	private String viewspot3;
	private String imageid7;
	private String imageid8;
	private String imageid9;
	private String hotel;
	private String image10;
	private String image11;
	private String image12;
	private String operid;
	private Date lastUpdateTime;
	private short validflag;
	@Transient
	private Recommendline recommendline;
	@Transient
	private List<Entityimage> entityimage;
	
	public String getRecommendlineid() {
		return recommendlineid;
	}
	public void setRecommendlineid(String recommendlineid) {
		this.recommendlineid = recommendlineid;
	}
	public short getDate() {
		return date;
	}
	public void setDate(short date) {
		this.date = date;
	}
	public String getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}
	public String getViewspot1() {
		return viewspot1;
	}
	public void setViewspot1(String viewspot1) {
		this.viewspot1 = viewspot1;
	}
	public String getImageid1() {
		return imageid1;
	}
	public void setImageid1(String imageid1) {
		this.imageid1 = imageid1;
	}
	public String getImageid2() {
		return imageid2;
	}
	public void setImageid2(String imageid2) {
		this.imageid2 = imageid2;
	}
	public String getImageid3() {
		return imageid3;
	}
	public void setImageid3(String imageid3) {
		this.imageid3 = imageid3;
	}
	public String getLunch() {
		return lunch;
	}
	public void setLunch(String lunch) {
		this.lunch = lunch;
	}
	public String getViewspot2() {
		return viewspot2;
	}
	public void setViewspot2(String viewspot2) {
		this.viewspot2 = viewspot2;
	}
	public String getImageid4() {
		return imageid4;
	}
	public void setImageid4(String imageid4) {
		this.imageid4 = imageid4;
	}
	public String getImageid5() {
		return imageid5;
	}
	public void setImageid5(String imageid5) {
		this.imageid5 = imageid5;
	}
	public String getImageid6() {
		return imageid6;
	}
	public void setImageid6(String imageid6) {
		this.imageid6 = imageid6;
	}
	public String getDinner() {
		return dinner;
	}
	public void setDinner(String dinner) {
		this.dinner = dinner;
	}
	public String getViewspot3() {
		return viewspot3;
	}
	public void setViewspot3(String viewspot3) {
		this.viewspot3 = viewspot3;
	}
	public String getImageid7() {
		return imageid7;
	}
	public void setImageid7(String imageid7) {
		this.imageid7 = imageid7;
	}
	public String getImageid8() {
		return imageid8;
	}
	public void setImageid8(String imageid8) {
		this.imageid8 = imageid8;
	}
	public String getImageid9() {
		return imageid9;
	}
	public void setImageid9(String imageid9) {
		this.imageid9 = imageid9;
	}
	public String getHotel() {
		return hotel;
	}
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
	public String getImage10() {
		return image10;
	}
	public void setImage10(String image10) {
		this.image10 = image10;
	}
	public String getImage11() {
		return image11;
	}
	public void setImage11(String image11) {
		this.image11 = image11;
	}
	public String getImage12() {
		return image12;
	}
	public void setImage12(String image12) {
		this.image12 = image12;
	}
	public String getOperid() {
		return operid;
	}
	public void setOperid(String operid) {
		this.operid = operid;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public short getValidflag() {
		return validflag;
	}
	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}
	public Recommendline getRecommendline() {
		return recommendline;
	}
	public void setRecommendline(Recommendline recommendline) {
		this.recommendline = recommendline;
	}
	public List<Entityimage> getEntityimage() {
		return entityimage;
	}
	public void setEntityimage(List<Entityimage> entityimage) {
		this.entityimage = entityimage;
	} 


}