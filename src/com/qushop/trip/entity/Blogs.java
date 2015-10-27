package com.qushop.trip.entity;

/***********************************************************************
 * Module:  tb_blogs.java
 * Author:  Administrator
 * Purpose: Defines the Class tb_blogs
 ***********************************************************************/

import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.qushop.user.entity.User;

/**
 * 游记列表
 * 
 */
@Entity
@Table(name="tb_blogs")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Blogs  implements Serializable{

	private String userid;
	@Id
	@GeneratedValue(generator="genericGenerator")
	@GenericGenerator(name="genericGenerator",strategy="assigned")
	private String blogid;
	private String blogstypeid;
	private String title;
	private String htmlpath;
	private int readcount;
	private String label;
	private Date createtime;
	private String coverimageid;
	private String operid;
	private Date lastUpdateTime;
	private short checkflag;
	private short validflag;
	
	@Transient
	private Blogstype blogstype;
	@Transient
	private List<Entityimage> entityimage;
	@Transient
	private User user;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getBlogstypeid() {
		return blogstypeid;
	}
	public void setBlogstypeid(String blogstypeid) {
		this.blogstypeid = blogstypeid;
	}
	public String getBlogid() {
		return blogid;
	}
	public void setBlogid(String blogid) {
		this.blogid = blogid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHtmlpath() {
		return htmlpath;
	}
	public void setHtmlpath(String htmlpath) {
		this.htmlpath = htmlpath;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getCoverimageid() {
		return coverimageid;
	}
	public void setCoverimageid(String coverimageid) {
		this.coverimageid = coverimageid;
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
	public short getCheckflag() {
		return checkflag;
	}
	public void setCheckflag(short checkflag) {
		this.checkflag = checkflag;
	}
	public short getValidflag() {
		return validflag;
	}
	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}
	public Blogstype getBlogstype() {
		return blogstype;
	}
	public void setBlogstype(Blogstype blogstype) {
		this.blogstype = blogstype;
	}
	public List<Entityimage> getEntityimage() {
		return entityimage;
	}
	public void setEntityimage(List<Entityimage> entityimage) {
		this.entityimage = entityimage;
	} 


}