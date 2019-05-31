package retrofit;

import java.io.Serializable;

/**
 * Данные по месторасположению
 * @author adms-Ahmetshin-RM
 */
public class Placement implements Serializable {
	
	private String city;
	private String branchCode;
	private String office;
	private String phone;
	private String extPhone;
	private String mobilePhone;
	private String room;
	
	public Placement() {
	
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getBranchCode() {
		return branchCode;
	}
	
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	public String getOffice() {
		return office;
	}
	
	public void setOffice(String office) {
		this.office = office;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getExtPhone() {
		return extPhone;
	}
	
	public void setExtPhone(String extPhone) {
		this.extPhone = extPhone;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	public String getRoom() {
		return room;
	}
	
	public void setRoom(String room) {
		this.room = room;
	}
}
