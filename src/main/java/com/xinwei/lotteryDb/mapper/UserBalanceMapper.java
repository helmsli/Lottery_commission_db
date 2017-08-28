package com.xinwei.lotteryDb.mapper;

import java.util.List;

import com.xinwei.lotteryDb.domain.UserBalance;



public interface UserBalanceMapper {
	/**
	 * 插入新的扣费记录
	 * 
	 * @param userBalance
	 */
	public void insertUserBalance(UserBalance userBalance);
	/**
	 * 根据条件查询需要处理的扣费信息，处理状态为1为待处理的查询出来
	 * 
	 * @param userBalance
	 * @return
	 */
	public List<UserBalance> selectUserBalance(UserBalance userBalance);
	
	/**
	 * 更新扣费记录，更新处理状态和更新时间
	 * 
	 * @param userBalance
	 */
	public int updateUserBalance(UserBalance userBalance);

	/**
	 * 按照userID删除余额记录
	 * @param userBalance
	 * @return
	 */
	public int deleteUserBalance(UserBalance userBalance);
}
