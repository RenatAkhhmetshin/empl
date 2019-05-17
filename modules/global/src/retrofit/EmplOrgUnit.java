package retrofit;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Данные по подразделению
 */
public class EmplOrgUnit implements Serializable {
	private String orgUnitUnid;
	private String fullName;
	private String shortName;
	private String code;
	private EmplOrgUnit parentOrgUnit;
	public EmplOrgUnit() {
	
	}
	
	public String getOrgUnitUnid() {
		return orgUnitUnid;
	}
	
	public void setOrgUnitUnid(String orgUnitUnid) {
		this.orgUnitUnid = orgUnitUnid;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public EmplOrgUnit getParentOrgUnit() {
		return parentOrgUnit;
	}
	
	public void setParentOrgUnit(EmplOrgUnit parentEmplOrgUnit) {
		this.parentOrgUnit = parentEmplOrgUnit;
	}
	
	@Override
	public String toString() {
		return "EmplOrgUnit{" +
				"orgUnitUnid='" + orgUnitUnid + '\'' +
				", fullName='" + fullName + '\'' +
				", shortName='" + shortName + '\'' +
				", code='" + code + '\'' +
				", parentOrgUnit=" + parentOrgUnit +
				'}';
	}
}
