package com.svb.empl.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "EMPL_ORG_UNIT")
@Entity(name = "empl_OrgUnit")
public class OrgUnit extends StandardEntity {
	@Column(name = "EXTID", length = 100)
	protected String extid;
	
	@Column(name = "FULLNAME")
	protected String fullname;
	
	@Column(name = "SHORTNAME")
	protected String shortname;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	protected OrgUnit parent;
	
	@Column(name = "TYPEID")
	protected Integer typeid;
	
	public void setParent(OrgUnit parent) {
		this.parent = parent;
	}
	
	public OrgUnit getParent() {
		return parent;
	}
	
	public OrgType getTypeid() {
		return typeid == null ? null : OrgType.fromId(typeid);
	}
	
	public void setTypeid(OrgType typeid) {
		this.typeid = typeid == null ? null : typeid.getId();
	}
	
	public String getShortname() {
		return shortname;
	}
	
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	
	public String getFullname() {
		return fullname;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public String getExtid() {
		return extid;
	}
	
	public void setExtid(String extid) {
		this.extid = extid;
	}
}