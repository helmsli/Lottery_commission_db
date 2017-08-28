package com.xinwei.lotteryDb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinwei.lotteryDb.domain.UserBalanceLog;
import com.xinwei.lotteryDb.mapper.UserBalanceLogMapper;
import com.xinwei.lotteryDb.mapper.UserBalanceMapper;
@Service("userBalanceLogMapperImpl")
public class UserBalanceLogMapperImpl implements UserBalanceLogMapper {
	@Autowired
	private UserBalanceLogMapper userBalanceLogMapper;
	@Override
	public void insertUserBalanceLog(UserBalanceLog userBalanceLog) {
		// TODO Auto-generated method stub
		userBalanceLogMapper.insertUserBalanceLog(userBalanceLog);

	}

	@Override
	public List<UserBalanceLog> selectUserBalanceLog(UserBalanceLog userBalanceLog) {
		// TODO Auto-generated method stub
		return userBalanceLogMapper.selectUserBalanceLog(userBalanceLog);
	}

	@Override
	public int updateUserBalanceLog(UserBalanceLog userBalanceLog) {
		// TODO Auto-generated method stub
		return userBalanceLogMapper.updateUserBalanceLog(userBalanceLog);
	}

	@Override
	public int deleteUserBalanceLog(UserBalanceLog userBalanceLog) {
		// TODO Auto-generated method stub
		return userBalanceLogMapper.deleteUserBalanceLog(userBalanceLog);
	}

}
