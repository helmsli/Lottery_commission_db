package com.xinwei.lotteryDb.mapper;

import java.util.List;


import com.xinwei.lotteryDb.domain.UserBalanceLog;

public interface UserBalanceLogMapper {

	
	/**
	 * 插入新的扣费记录
	 * 
	 * @param accountWoffInfo
	 */
	public void insertUserBalanceLog(UserBalanceLog userBalanceLog);
	/**
	 * 根据条件查询需要处理的扣费信息，处理状态为1为待处理的查询出来
	 * 
	 * @param accountWoffInfo
	 * @return
	 */
	public List<UserBalanceLog> selectUserBalanceLog(UserBalanceLog userBalanceLog);
	
	/**
	 * 更新扣费记录，更新处理状态和更新时间
	 * 
	 * @param accountWoffInfo
	 */
	public int updateUserBalanceLog(UserBalanceLog userBalanceLog);
	
	/**
	 * 删除日志记录
	 * @param userBalanceLog
	 * @return
	 */
	public int deleteUserBalanceLog(UserBalanceLog userBalanceLog);
}
