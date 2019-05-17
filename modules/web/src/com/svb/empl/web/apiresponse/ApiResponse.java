package com.svb.empl.web.apiresponse;


import java.io.Serializable;

public class ApiResponse implements Serializable {
	
	private String responseCode;
	private String responseErrorText;
	
	
	public ApiResponse() {
	
	
	}
	
	public ApiResponse(String responseCode, String responseErrorText) {
		this.responseCode = responseCode;
		this.responseErrorText = responseErrorText;
	}
	
	public String getResponseCode() {
		return responseCode;
	}
	
	public String getResponseErrorText() {
		return responseErrorText;
	}
	
	
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	
	public void setResponseErrorText(String responseErrorText) {
		this.responseErrorText = responseErrorText;
	}
	
	@Override
	public String toString() {
		return "ApiResponse{" +
				"responseCode='" + responseCode + '\'' +
				", responseErrorText='" + responseErrorText + '\'' +
				'}';
	}
}
