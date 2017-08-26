package com.jpro.studentsmeetbackend.model;

import javax.persistence.Transient;

public class BaseDomain {
	
	@Transient
	private String errorCode;
	
	@Transient
	private String errorMsg;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


}
