package com.xinwei.lotteryDb.controller.rest;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.xinwei.lotteryDb.domain.UserBalance;
import com.xinwei.lotteryDb.domain.UserBalanceApply;

public class UpdateBalRequest implements Serializable {
	private UserBalance nowUseBalance;
	private UserBalanceApply userBalanceApply;
	public UserBalance getNowUseBalance() {
		return nowUseBalance;
	}
	public void setNowUseBalance(UserBalance nowUseBalance) {
		this.nowUseBalance = nowUseBalance;
	}
	public UserBalanceApply getUserBalanceApply() {
		return userBalanceApply;
	}
	public void setUserBalanceApply(UserBalanceApply userBalanceApply) {
		this.userBalanceApply = userBalanceApply;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
