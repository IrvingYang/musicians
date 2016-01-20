package com.qushop.dict.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author irving
 *
 */
@Entity
@Table(name="tb_country")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator="COUNTRY_SEQ")
    @SequenceGenerator(name="COUNTRY_SEQ",sequenceName="COUNTRY_SEQ")
	private int countryId;
	private String countryName;
	private short validflag;
	
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	
	
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	public int getCountryId() {
		return countryId;
	}
	
	
	public String getCountryName() {
		return countryName;
	}
	
	
	public short getValidflag() {
		return validflag;
	}
	public void setValidflag(short validflag) {
		this.validflag = validflag;
	}
	
}
