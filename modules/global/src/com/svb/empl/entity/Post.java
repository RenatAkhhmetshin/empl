package com.svb.empl.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NamePattern("#getCaption|name")
@Table(name = "EMPL_POST")
@Entity(name = "empl_Post")
public class Post extends StandardEntity {
	@Column(name = "CODE")
	protected String code;
	
	@Column(name = "NAME", length = 130)
	protected String name;
	
	@Column(name = "EXTID", length = 30)
	protected String extid;
	
	@Column(name = "UNID", length = 40)
	protected String unid;
	
	public String getUnid() {
		return unid;
	}
	
	public void setUnid(String unid) {
		this.unid = unid;
	}
	
	public String getExtid() {
		return extid;
	}
	
	public void setExtid(String extid) {
		this.extid = extid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	
	private String getCaption () {
		return name;
	}
}