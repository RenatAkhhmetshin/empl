package com.svb.empl.web.apirequest;

import java.io.Serializable;

public class EmplPost implements Serializable {
	
	private String emplPostCode;
	private String emplPostUnid;
	private String emplPostExtID;
	private String emplPostName;
	
	public EmplPost() {
	
	}
	
	
	public EmplPost(String emplPostCode, String emplPostUnid, String emplPostExtID, String emplPostName) {
		this.emplPostCode = emplPostCode;
		this.emplPostUnid = emplPostUnid;
		this.emplPostExtID = emplPostExtID;
		this.emplPostName = emplPostName;
	}
	
	
	@Override
	public String toString() {
		return "EmplPost{" +
				"emplPostCode='" + emplPostCode + '\'' +
				", emplPostUnid='" + emplPostUnid + '\'' +
				", emplPostExtID='" + emplPostExtID + '\'' +
				", emplPostName='" + emplPostName + '\'' +
				'}';
	}
	
	
	public String getEmplPostCode() {
		return emplPostCode;
	}
	
	public String getEmplPostUnid() {
		return emplPostUnid;
	}
	
	public String getEmplPostExtID() {
		return emplPostExtID;
	}
	
	public String getEmplPostName() {
		return emplPostName;
	}
}
