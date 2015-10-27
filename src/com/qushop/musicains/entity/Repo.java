package com.qushop.musicains.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.qushop.prod.entity.Product_ext_shop;
import com.qushop.user.entity.User;




/**
 * 回购信息表
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_repo")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Repo implements Serializable {

	private static final long serialVersionUID = 113432586690166151L;
	@Id
	@GeneratedValue(generator="repoId")
	@GenericGenerator(name="repoId",strategy="assigned")
	private String repoId;
	private String productId;
	private String providerId;
	private String userId;
	private short repoType;//回购类型 1提现 2折旧
	private Date createTime;
	/**
	00：回购已经取消
	01：已提交申请
    02：用户已经发货
    03：商家已收货
    04：商家已拨款
    05：回购完成
    06：超期取消
    */
	private String status;
	private double totalamt;//总价
	
	private Date deliveryTime;//收货时间
	private String shippingCompany;//快递公司
	
	private Date confirmReceiveTime;
	private Date endTime;
	private String operId;
	private Date lastUpdateTime;
	private short validflag;
	
	private String telephone;
	private String email;
	private String bankcard;
	private String cardname;
	
	@Transient
	private Product_ext_shop ext_shop;
	@Transient
	private User user;
	
	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}
	
	public String getShippingCompany() {
		return shippingCompany;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product_ext_shop getExt_shop() {
		return ext_shop;
	}
	public void setExt_shop(Product_ext_shop ext_shop) {
		this.ext_shop = ext_shop;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBankcard() {
		return bankcard;
	}
	public void setBankcard(String bankcard) {
		this.bankcard = bankcard;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getRepoId() {
		return repoId;
	}
	public void setRepoId(String repoId) {
		this.repoId = repoId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Short getRepoType() {
		return repoType;
	}
	public void setRepoType(short repoType) {
		this.repoType = repoType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getTotalamt() {
		return totalamt;
	}
	public void setTotalamt(double totalamt) {
		this.totalamt = totalamt;
	}
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public Date getConfirmReceiveTime() {
		return confirmReceiveTime;
	}
	public void setConfirmReceiveTime(Date confirmReceiveTime) {
		this.confirmReceiveTime = confirmReceiveTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Short getValidflag() {
		return validflag;
	}
	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
