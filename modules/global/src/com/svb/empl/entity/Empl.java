package com.svb.empl.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NamePattern("#getCaption|user,postpath")
@Table(name = "EMPL_EMPL")
@Entity(name = "empl_Empl")
public class Empl extends StandardEntity {
	@Column(name = "NOTESNAME")
	protected String notesname;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	protected User user;

	@Column(name = "CITY", length = 100)
	protected String city;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH_ID")
	protected Branch branch;

	@Column(name = "OFFICE", length = 150)
	protected String office;

	@Column(name = "PHONE", length = 100)
	protected String phone;

	@Column(name = "EXTPHONE", length = 100)
	protected String extphone;

	@Column(name = "MOBILEPHONE")
	protected String mobilephone;

	@Column(name = "ROOM")
	protected String room;

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDATE")
	protected Date birthdate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CHIEF_ID")
	protected Empl chief;

	@JoinTable(name = "EMPL_EMPL_ORG_UNIT_LINK",
			joinColumns = @JoinColumn(name = "EMPL_ID"),
			inverseJoinColumns = @JoinColumn(name = "ORG_UNIT_ID"))
	@ManyToMany
	protected List<OrgUnit> orgunits;

	@Column(name = "SEX")
	protected String sex;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "")
	@JoinColumn(name = "PHOTO_ID")
	protected FileDescriptor photo;

	@Column(name = "POSTPATH")
	protected String postpath;

	@Column(name = "TABNUMBER", length = 40)
	protected String tabnumber;

	@Column(name = "EXTID", length = 50)
	protected String extid;

	public String getExtid() {
		return extid;
	}
	
	public void setExtid(String extid) {
		this.extid = extid;
	}
	
	public String getTabnumber() {
		return tabnumber;
	}
	
	public void setTabnumber(String tabnumber) {
		this.tabnumber = tabnumber;
	}
	
	public String getPostpath() {
		return postpath;
	}
	
	public void setPostpath(String postpath) {
		this.postpath = postpath;
	}
	
	public Sex getSex() {
		return sex == null ? null : Sex.fromId(sex);
	}
	
	public void setSex(Sex sex) {
		this.sex = sex == null ? null : sex.getId();
	}
	
	public List<OrgUnit> getOrgunits() {
		return orgunits;
		
		
	}
	
	public void setOrgunits(List<OrgUnit> orgunits) {
		this.orgunits = orgunits;
	}
	
	public FileDescriptor getPhoto() {
		return photo;
	}
	
	public void setPhoto(FileDescriptor photo) {
		this.photo = photo;
	}
	
	public Empl getChief() {
		return chief;
	}
	
	public void setChief(Empl chief) {
		this.chief = chief;
	}
	
	public Date getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getRoom() {
		return room;
	}
	
	public void setRoom(String room) {
		this.room = room;
	}
	
	public String getMobilephone() {
		return mobilephone;
	}
	
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	
	public String getExtphone() {
		return extphone;
	}
	
	public void setExtphone(String extphone) {
		this.extphone = extphone;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getOffice() {
		return office;
	}
	
	public void setOffice(String office) {
		this.office = office;
	}
	
	public Branch getBranch() {
		return branch;
	}
	
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
		
	}
	
	public String getNotesname() {
		return notesname;
	}
	
	public void setNotesname(String notesname) {
		this.notesname = notesname;
	}
	
	
	
	public String getCaption () {
		String caption = user.getFirstName()+" "+
				user.getMiddleName()+" "+ user.getLastName() + " "+"("+postpath+"\\"+user.getPosition()+")";
		
		return  caption;
	}
}