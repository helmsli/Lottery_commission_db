package com.xinwei.lotteryDb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinwei.lotteryDb.domain.UserBalance;
import com.xinwei.lotteryDb.mapper.UserBalanceMapper;
import com.xinwei.lotteryDb.service.ServiceUserBalanceDb;


@Service("userBalanceMapperImpl")
public class UserBalanceMapperImpl implements ServiceUserBalanceDb {
	@Autowired
	private UserBalanceMapper userBalanceMapper;
	@Override
	public void insertUserBalance(UserBalance userBalance) {
		// TODO Auto-generated method stub
		userBalanceMapper.insertUserBalance(userBalance);
	}

	@Override
	public List<UserBalance> selectUserBalance(UserBalance userBalance) {
		// TODO Auto-generated method stub
		return userBalanceMapper.selectUserBalance(userBalance);
	}

	@Override
	public int updateUserBalance(UserBalance userBalance) {
		// TODO Auto-generated method stub
		return userBalanceMapper.updateUserBalance(userBalance);
	}

	@Override
	public int deleteUserBalance(UserBalance userBalance) {
		// TODO Auto-generated method stub
		 return userBalanceMapper.deleteUserBalance(userBalance);
	}

}
