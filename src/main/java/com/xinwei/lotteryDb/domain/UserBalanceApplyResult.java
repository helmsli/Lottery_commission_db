package com.xinwei.lotteryDb.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

public class UserBalanceApplyResult  extends UserBalance{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 结果
	 */
	private int result;
	
	/**
	 * 错误码
	 */
	private int error;
	
	/**
	 * 错误信息，便于维护人员定位用
	 */
	private String errorMessage;
	
	
	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	
	
	
	
}
