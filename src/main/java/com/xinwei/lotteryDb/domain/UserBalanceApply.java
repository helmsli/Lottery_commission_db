package com.xinwei.lotteryDb.domain;

import java.io.Serializable;
import java.util.Date;

public class UserBalanceApply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 用户的UID. */
	private long userId;

	/** 用户的电话号码. */
	private String telphonenum;

	/** 事物号. */
	private String transaction;

	/**事务时间*/
	private Date transactionTime;
	
	/** 余额变化订单号. */
	private String orderId;
	
	/** 转账金额. */
	private double amount;

	/** 备注. */
	private String remark;
	
	/** 扩展余额信息. */
	private String balanceext;

	private String bizType;
	
	private String operType;
	
	/** 最后更新时间. */
	private Date updatetime;

	/** 更新来源. */
	private String updatesource;
	
	/***/
	private int expireDays;
	

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getTelphonenum() {
		return telphonenum;
	}

	public void setTelphonenum(String telphonenum) {
		this.telphonenum = telphonenum;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBalanceext() {
		return balanceext;
	}

	public void setBalanceext(String balanceext) {
		this.balanceext = balanceext;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getUpdatesource() {
		return updatesource;
	}

	public void setUpdatesource(String updatesource) {
		this.updatesource = updatesource;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	public int getExpireDays() {
		return expireDays;
	}

	public void setExpireDays(int expireDays) {
		this.expireDays = expireDays;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserBalanceApply [userId=" + userId + ", telphonenum=" + telphonenum + ", transaction=" + transaction
				+ ", transactionTime=" + transactionTime + ", orderId=" + orderId + ", amount=" + amount + ", remark="
				+ remark + ", balanceext=" + balanceext + ", bizType=" + bizType + ", operType=" + operType
				+ ", updatetime=" + updatetime + ", updatesource=" + updatesource + ", expireDays=" + expireDays + "]";
	}

   
	
}
