package com.svb.empl.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@NamePattern("%s|name")
@Table(name = "EMPL_BRANCH")
@Entity(name = "empl_Branch")
public class Branch extends StandardEntity {
	@Column(name = "ORGNAME")
	protected String orgname;
	
	@Column(name = "NAME")
	protected String name;
	
	@Column(name = "COUNTRY")
	protected String country;
	
	@Column(name = "MAINOFFICE")
	protected String mainoffice;
	
	@Column(name = "NUMBER_")
	protected String number;
	
	@Column(name = "REGNUMBER")
	protected String regnumber;
	
	@Column(name = "CODE")
	protected String code;
	
	@Column(name = "BIK")
	protected String bik;
	
	@Column(name = "REGCODE")
	protected String regcode;
	
	public String getRegcode() {
		return regcode;
	}
	
	public void setRegcode(String regcode) {
		this.regcode = regcode;
	}
	
	public String getBik() {
		return bik;
	}
	
	public void setBik(String bik) {
		this.bik = bik;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getRegnumber() {
		return regnumber;
	}
	
	public void setRegnumber(String regnumber) {
		this.regnumber = regnumber;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getMainoffice() {
		return mainoffice;
	}
	
	public void setMainoffice(String mainoffice) {
		this.mainoffice = mainoffice;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOrgname() {
		return orgname;
	}
	
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
}