package com.xinwei.lotteryDb.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinwei.lotteryDb.Const.UserBalanceApplyConst;
import com.xinwei.lotteryDb.domain.UserBalance;
import com.xinwei.lotteryDb.domain.UserBalanceApply;
import com.xinwei.lotteryDb.domain.UserBalanceApplyResult;
import com.xinwei.lotteryDb.domain.UserBalanceLog;
import com.xinwei.lotteryDb.mapper.UserBalanceLogMapper;
import com.xinwei.lotteryDb.mapper.UserBalanceMapper;
import com.xinwei.lotteryDb.service.ServiceUserBlance;
@Service("serviceUserBalanceImpl")
public class ServiceUserBalanceImpl implements ServiceUserBlance {

	@Autowired
	private UserBalanceLogMapper userBalanceLogMapper;
	
	@Autowired
	private UserBalanceMapper userBalanceMapper;

	
	/**
	 * 如果账户不存在，初始化账户信息
	 * @param userBalanceApply
	 * @return
	 */
	public UserBalance initUserBalance(UserBalance nowUserBalance,UserBalanceApply userBalanceApply)
	{
		UserBalance userBalance = new UserBalance();
		//Date nowDate = Calendar.getInstance().getTime();
		userBalance.setUserId(userBalanceApply.getUserId());
		userBalance.setBalance(0d);
		userBalance.setUpdatetime(nowUserBalance.getUpdatetime());
		userBalance.setTransaction(nowUserBalance.getTransaction());
		userBalance.setExpiredata(nowUserBalance.getExpiredata());		
		
		String crc = this.getBalanceCrc(userBalance);
        
		userBalance.setBalanceext(crc);
		/*
		Calendar now = Calendar.getInstance();  
		now.setTime(userBalanceApply.getUpdatetime());
		now.add(Calendar.YEAR, 100);*/		
		return userBalance;
		
	}
	/**
	 * 校验用户发起请求的余额和当前余额是否合法
	 * @param nowUseBalance -- 请求修改余额方提交的最新余额信息
	 * @param dbUseBalance  -- 从数据库获取的余额信息
	 * @return  -- true-- 符合规则，
	 */
	protected int checkBalance(UserBalance nowUseBalance,UserBalance dbUseBalance)
	{
		
		//todo:校验请求的和是否合法，请求的验证算法和数据库的算法不应该一致；
		return nowUseBalance.eqaulBalanceOneUser(dbUseBalance);
		
	}
	/**
	 * 校验校验和是否合法。
	 * @param dbUseBalance
	 * @return
	 */
	protected int checkBalanceCrc(UserBalance dbUseBalance)
	{
		return UserBalanceApplyConst.RESULT_SUCCESS;
	}
	/**
	 * 创建CRC校验和
	 * @param nowUseBalance
	 * @return
	 */
	public String getBalanceCrc(UserBalance nowUseBalance)
	{
		return "defaultcrc";
	}
	/**
	 * 根据转账请求获取转账日志信息
	 * @param userBalanceApply --余额更新申请信息
	 * @return
	 */
	protected UserBalanceLog getFromUserBalanceApply(UserBalanceApply userBalanceApply)
	{
		UserBalanceLog userBalanceLog = new UserBalanceLog();
		userBalanceLog.setUserId(userBalanceApply.getUserId());
		userBalanceLog.setTransaction(userBalanceApply.getTransaction());
		userBalanceLog.setTransactionTime(userBalanceApply.getTransactionTime());
		userBalanceLog.setAmount(userBalanceApply.getAmount());		
		return userBalanceLog;
	}
	/**
	 * 为更新余额形成新的更新余额表格
	 * @param nowDbUserBalance --当前数据库中余额信息
	 * @param userBalanceApply--余额更新申请信息
	 * @return
	 */
	protected UserBalance createNewUserBalance(UserBalance nowDbUserBalance,UserBalanceApply userBalanceApply)
	{
		UserBalance newUserBalance = new UserBalance();
		newUserBalance.setUserId(userBalanceApply.getUserId());
		newUserBalance.setTransaction(userBalanceApply.getTransaction());
		newUserBalance.setUpdatetime(Calendar.getInstance().getTime());
		newUserBalance.setUpdatesource(userBalanceApply.getUpdatesource());
		newUserBalance.setAmount(userBalanceApply.getAmount());
		//设置失效时间
		if(userBalanceApply.getExpireDays()>0)
		{
			Calendar expiredata = Calendar.getInstance();
			expiredata.setTime(nowDbUserBalance.getExpiredata());
			expiredata.add(Calendar.DAY_OF_YEAR, userBalanceApply.getExpireDays());
			newUserBalance.setExpiredata(expiredata.getTime());
		}
		else
		{
			newUserBalance.setExpiredata(nowDbUserBalance.getExpiredata());
		}
	
		newUserBalance.setOldBalanceext(nowDbUserBalance.getBalanceext());
		newUserBalance.setBalance(nowDbUserBalance.getBalance());
		String newCrc=getBalanceCrc(newUserBalance);
		newUserBalance.setBalanceext(newCrc);
		return newUserBalance;
	}
	
	/**
	 * 为创建余额记录形成新的更新记录信息
	 * @param nowDbUserBalance --当前数据库中余额信息
	 * @param newUserBalance  --即将更新的余额信息
	 * @param userBalanceApply --余额更新申请信息
	 * @return
	 */
	protected UserBalanceLog createNewUserBalanceLog(UserBalance nowDbUserBalance,UserBalance newUserBalance,UserBalanceApply userBalanceApply)
	{
		UserBalanceLog newUserBalanceLog = new UserBalanceLog();
		newUserBalanceLog.setUserId(userBalanceApply.getUserId());
		newUserBalanceLog.setTransaction(newUserBalance.getTransaction());
		newUserBalanceLog.setUpdatetime(newUserBalance.getUpdatetime());
		newUserBalanceLog.setUpdatesource(newUserBalance.getUpdatesource());
		newUserBalanceLog.setAmount(newUserBalance.getAmount());
		newUserBalanceLog.setBeginningbalance(nowDbUserBalance.getBalance());
		newUserBalanceLog.setBeginningexpiretimes(nowDbUserBalance.getExpiredata());
		newUserBalanceLog.setTransactionTime(userBalanceApply.getTransactionTime());
		newUserBalanceLog.setRemark(userBalanceApply.getRemark());		
		return newUserBalanceLog;
	}
	
	/**
	 * 根据最新的余额获取余额修改应答
	 * @param newUserBalance
	 * @param userBalanceApplyResult
	 */
	protected void createApplyResult(UserBalance newUserBalance,UserBalanceApplyResult userBalanceApplyResult)
	{
		userBalanceApplyResult.setUserId(newUserBalance.getUserId());
		userBalanceApplyResult.setAmount(newUserBalance.getAmount());
		userBalanceApplyResult.setBalance(newUserBalance.getBalance());
		userBalanceApplyResult.setBalanceext(newUserBalance.getBalanceext());
		userBalanceApplyResult.setExpiredata(newUserBalance.getExpiredata());
		userBalanceApplyResult.setTransaction(newUserBalance.getTransaction());
		userBalanceApplyResult.setUpdatesource(newUserBalance.getUpdatesource());
		userBalanceApplyResult.setUpdatetime(newUserBalance.getUpdatetime());
		
	}
	
	
	
	
	/**
	 * 完成数据库的修改实现
	 * @param userBalance
	 * @param userBalanceLog
	 * @return
	 */
	@Transactional
	public int updateBalance(UserBalance userBalance,UserBalanceLog userBalanceLog)
	{
		int updateRow = this.userBalanceMapper.updateUserBalance(userBalance);
		if(updateRow>0)
		{
			this.userBalanceLogMapper.insertUserBalanceLog(userBalanceLog);
			userBalance.setBalance(userBalance.getBalance() -  userBalance.getAmount());
		}
		return updateRow;
	}
	
	
	@Override
	public UserBalanceApplyResult updateUserBalance(UserBalance nowUseBalance, UserBalanceApply userBalanceApply) {
		// TODO Auto-generated method stub
		/**
		 * 
		 * 1.先检查该事务订单是否已经被提交过，如果已经被操作过，直接返回
		 * 2.首先根据查询的订单获取最新的余额，校验数据是否正确。如果当前余额为0，如果时候初始状态，客户端返回特定的余额。
		 * 3、如果校验数据不正确，并且是减少余额，返回成功；结果为需要成功，需要校验
		 * 4.更新当前的余额和系统记录，记录最新的余额和校验码，返回给请求者。
		 */
		int result = UserBalanceApplyConst.RESULT_FAILURE;
		UserBalanceApplyResult userBalanceApplyResult = new UserBalanceApplyResult();
		userBalanceApplyResult.setResult(UserBalanceApplyConst.RESULT_FAILURE);
		userBalanceApplyResult.setError(0);
		
		/**
		 *判断交易是否已经执行过了
		 */
		UserBalanceLog userBalanceLog = getFromUserBalanceApply(userBalanceApply);
		List<UserBalanceLog> queryUserBalanceLogList=this.userBalanceLogMapper.selectUserBalanceLog(userBalanceLog);
		if(queryUserBalanceLogList!=null && queryUserBalanceLogList.size()>0)
		{
			UserBalanceLog havePaidLog = queryUserBalanceLogList.get(0);
			//如果金额一致
			if(havePaidLog.getAmount()==userBalanceApply.getAmount())
			{
				userBalanceApplyResult.setError(UserBalanceApplyConst.ERROR_SELETEDB_ERROR);
				//获取当前余额，确保超时后能够继续后续操作
				List<UserBalance> queryUserBalanceList= userBalanceMapper.selectUserBalance(nowUseBalance);
				if(queryUserBalanceList.size()>0)
				{
					UserBalance dbUserBalance = queryUserBalanceList.get(0);
					//如果交易是最后一笔交易
					if(dbUserBalance.getTransaction().equalsIgnoreCase(havePaidLog.getTransaction()))
					{
						userBalanceApplyResult.setError(UserBalanceApplyConst.ERROR_TRANSACTION_HAVEDONE);
						createApplyResult(dbUserBalance,userBalanceApplyResult);	
					}
					//如果不是最后一笔交易，返回最后一笔交易的交易号
					else
					{
						userBalanceApplyResult.setTransaction(dbUserBalance.getTransaction());
						userBalanceApplyResult.setError(UserBalanceApplyConst.ERROR_TRANSACTION_HAVEDONENotLast);
						
					}
				}				   				
					
			}
			//如果金额不一致
			else
			{
				userBalanceApplyResult.setError(UserBalanceApplyConst.ERROR_TRANSACTION_ERROR);				
			}
			return userBalanceApplyResult;
		}
		//如果交易没有执行过，从数据库查询当前余额
		List<UserBalance> queryUserBalanceList= userBalanceMapper.selectUserBalance(nowUseBalance);
		UserBalance initUserBalance =null;
		
		//数据库是否存在当前用户账户		
		if(queryUserBalanceList.size()==0)
		{
			//如果客户端请求时通知没有数据，需要插入数据
			if(!nowUseBalance.isUserIDExist())
			{
				 initUserBalance = initUserBalance(nowUseBalance,userBalanceApply);
				 try {
					 //初始余额必须大于等于0；
					
						 userBalanceMapper.insertUserBalance(initUserBalance);
						 createApplyResult(initUserBalance,userBalanceApplyResult);
						 userBalanceApplyResult.setResult(UserBalanceApplyConst.RESULT_SUCCESS_init);
						 return userBalanceApplyResult;
					 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					userBalanceApplyResult.setError(UserBalanceApplyConst.ERROR_INSERT_BALANCE);
					return userBalanceApplyResult;
				}
			}
			//否则返回用户不存在
			else
			{
				userBalanceApplyResult.setError(UserBalanceApplyConst.ERROR_UID_NOTEXIST);
				return userBalanceApplyResult;
			}
		}
		//数据库中存在当前账户
		else
		{
			//校验余额大于0
			
			UserBalance dbUserBalance = queryUserBalanceList.get(0);
			result = checkBalanceCrc(dbUserBalance);
			if(result != UserBalanceApplyConst.RESULT_SUCCESS)
			{
				userBalanceApplyResult.setResult(UserBalanceApplyConst.RESULT_FAILURE);
				userBalanceApplyResult.setError(result);
				return userBalanceApplyResult;
			}
			//end of 校验是否库校验和是否合法
			
			//比较请求数据和当前是否是否一致
			result = checkBalance(dbUserBalance,nowUseBalance);
			//校验数据是否正确,如果不正确，返回
			if(result != UserBalanceApplyConst.RESULT_SUCCESS)
			{
				userBalanceApplyResult.setResult(UserBalanceApplyConst.RESULT_FAILURE);
				userBalanceApplyResult.setError(result);
				return userBalanceApplyResult;
			}
			//进行数据库更新操作
			UserBalance newUserBalance = createNewUserBalance(dbUserBalance,userBalanceApply);
			UserBalanceLog newUserBalanceLog =  createNewUserBalanceLog(newUserBalance,dbUserBalance,userBalanceApply);
			int updateRow=0;
			try {
				int runTimes=0;
				
				do
				{
					updateRow = updateBalance(newUserBalance,newUserBalanceLog);
					runTimes++;
				}while(updateRow<=0&&runTimes<3);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				userBalanceApplyResult.setResult(UserBalanceApplyConst.RESULT_FAILURE);
				userBalanceApplyResult.setError(UserBalanceApplyConst.ERROR_UPDATEDB_ERROR);
				return userBalanceApplyResult;
			}
			//如果更新到记录，构造结果返回数值，
			if(updateRow>0)
			{
				userBalanceApplyResult.setResult(UserBalanceApplyConst.RESULT_SUCCESS);
				userBalanceApplyResult.setError(0);
				createApplyResult(newUserBalance,userBalanceApplyResult);
			}
			//如果更新记录失败，返回数据库操作失败异常
			else
			{
				userBalanceApplyResult.setResult(UserBalanceApplyConst.RESULT_FAILURE);
				userBalanceApplyResult.setError(UserBalanceApplyConst.ERROR_UPDATEDB_ERROR);
				
			}			
			return userBalanceApplyResult;
			
		}
		

	}
	
	protected boolean isQueryLastTransaction(UserBalanceApply userBalanceApply)
	{
		if(UserBalanceApplyConst.queryLastTransaction.equalsIgnoreCase(userBalanceApply.getTransaction()))
		{
			return true;
		}
		return false;
	}
	
	
	@Override
	public UserBalanceApplyResult queryTransaction(UserBalanceApply userBalanceApply) {
		int result = UserBalanceApplyConst.RESULT_FAILURE;
		UserBalanceApplyResult userBalanceApplyResult = new UserBalanceApplyResult();
		userBalanceApplyResult.setResult(UserBalanceApplyConst.RESULT_FAILURE);
		userBalanceApplyResult.setError(0);
		
		
		//从当前余额中获取最终的transid
		if(isQueryLastTransaction(userBalanceApply))
		{
			UserBalance userBalance = new UserBalance();
			userBalance.setUserId(userBalanceApply.getUserId());
			List<UserBalance> dbUserBalances = userBalanceMapper.selectUserBalance(userBalance);
			if(dbUserBalances!=null&&dbUserBalances.size()==0)
			{
				
				userBalanceApplyResult.setError(UserBalanceApplyConst.ERROR_TRANSACTION_LAST);
				
				userBalanceApplyResult.setTransaction(dbUserBalances.get(0).getTransaction());				
				
			}
			else
			{
				userBalanceApplyResult.setError(UserBalanceApplyConst.ERROR_UID_NOTEXIST);
				
			}
			return userBalanceApplyResult;
		}
		
		
		UserBalanceLog userBalanceLog = getFromUserBalanceApply(userBalanceApply);
		List<UserBalanceLog> queryUserBalanceLogList=this.userBalanceLogMapper.selectUserBalanceLog(userBalanceLog);
		if(queryUserBalanceLogList!=null && queryUserBalanceLogList.size()>0)
		{
			UserBalanceLog havePaidLog = queryUserBalanceLogList.get(0);
			//如果金额一致
			if(havePaidLog.getAmount()==userBalanceApply.getAmount())
			{
				userBalanceApplyResult.setResult(UserBalanceApplyConst.RESULT_SUCCESS);
				userBalanceApplyResult.setError(0);
			}
			//如果金额不一致
			else
			{
				userBalanceApplyResult.setError(UserBalanceApplyConst.ERROR_TRANSACTION_ERROR);				
			}
			
		}
		else
		{
			userBalanceApplyResult.setError(UserBalanceApplyConst.ERROR_TRANSACTION_NOTFOUND);
			
		}
		return userBalanceApplyResult;
	}

}
