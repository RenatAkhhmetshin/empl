package com.svb.empl.web.apirequest;

import java.io.Serializable;

public class BranchRequest implements Serializable {
	
	private String orgName;
	private String name;
	private String country;
	private String mainOffice;
	private String number;
	private String regNumber;
	private String regCode;
	private String code;
	private String bik;
	
	
	public BranchRequest() {
	
	}
	
	public BranchRequest(String orgName, String name, String country,
	                     String mainOffice, String number,
	                     String regNumber, String regCode, String code, String bik) {
		this.orgName = orgName;
		this.name = name;
		this.country = country;
		this.mainOffice = mainOffice;
		this.number = number;
		this.regNumber = regNumber;
		this.regCode = regCode;
		this.code = code;
		this.bik = bik;
	}
	
	
	public String getOrgName() {
		return orgName;
	}
	
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getMainOffice() {
		return mainOffice;
	}
	
	public void setMainOffice(String mainOffice) {
		this.mainOffice = mainOffice;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getRegNumber() {
		return regNumber;
	}
	
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	
	public String getRegCode() {
		return regCode;
	}
	
	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getBik() {
		return bik;
	}
	
	public void setBik(String bik) {
		this.bik = bik;
	}
}
