package com.svb.empl.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "EMPL_TEH_EMPL")
@Entity(name = "empl_TehEmpl")
public class TehEmpl extends StandardEntity {
	
	@Column(name = "EXTID", length = 100)
	protected String extid;
	
	@Column(name = "TABNUMBER", length = 100)
	protected String tabnumber;
	
	@JoinTable(name = "EMPL_TEH_EMPL_POST_LINK",
			joinColumns = @JoinColumn(name = "TEH_EMPL_ID"),
			inverseJoinColumns = @JoinColumn(name = "POST_ID"))
	@ManyToMany
	protected List<Post> posts;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "WORKDATE")
	protected Date workdate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DECRSTDATE")
	protected Date decrstdate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DECRENDDT")
	protected Date decrenddt;
	
	@Column(name = "DISMISS")
	protected Boolean dismiss;
	
	@Column(name = "DECREE")
	protected Boolean decree;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DISMISSDT")
	protected Date dismissdt;
	
	public List<Post> getPosts() {
		return posts;
	}
	
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public Date getDismissdt() {
		return dismissdt;
	}
	
	public void setDismissdt(Date dismissdt) {
		this.dismissdt = dismissdt;
	}
	
	public Boolean getDecree() {
		return decree;
	}
	
	public void setDecree(Boolean decree) {
		this.decree = decree;
	}
	
	public Boolean getDismiss() {
		return dismiss;
	}
	
	public void setDismiss(Boolean dismiss) {
		this.dismiss = dismiss;
	}
	
	public Date getDecrenddt() {
		return decrenddt;
	}
	
	public void setDecrenddt(Date decrenddt) {
		this.decrenddt = decrenddt;
	}
	
	public Date getDecrstdate() {
		return decrstdate;
	}
	
	public void setDecrstdate(Date decrstdate) {
		this.decrstdate = decrstdate;
	}
	
	public Date getWorkdate() {
		return workdate;
	}
	
	public void setWorkdate(Date workdate) {
		this.workdate = workdate;
	}
	
	public String getTabnumber() {
		return tabnumber;
	}
	
	public void setTabnumber(String tabnumber) {
		this.tabnumber = tabnumber;
	}
	
	public String getExtid() {
		return extid;
	}
	
	public void setExtid(String extid) {
		this.extid = extid;
	}
	
}