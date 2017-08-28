package com.xinwei.lotteryDb.service;

import java.util.List;

import com.xinwei.lotteryDb.domain.UserBalance;
import com.xinwei.lotteryDb.domain.UserBalanceApply;
import com.xinwei.lotteryDb.domain.UserBalanceApplyResult;

public interface ServiceUserBlance {
	/**
	 * 更新扣费记录，更新处理状态和更新时间
	 * 
	 * @param userBalance
	 */
	public UserBalanceApplyResult updateUserBalance(UserBalance nowUseBalance,UserBalanceApply userBalanceApply);
  
	/**
	 * 查询该笔业务交易执行情况
	 * @param userBalanceApply
	 * @return
	 */
	public UserBalanceApplyResult queryTransaction(UserBalanceApply userBalanceApply);

	

}
