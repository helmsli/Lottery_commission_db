package com.xinwei.lotteryDb.Const;

public class UserBalanceApplyConst {
	
	/**
	 * 可以查询最后一次的transid
	 */
	public static final String queryLastTransaction="**01112410121312****00000000";
	//账户初始化的时候的transaction
	public static final String initZeroTransaction="00200008221011223333333";
	
	//余额修改成功
    public static final int RESULT_SUCCESS = 0;
    //当帐户不存在的时候，初始化余额0成功
    public static final int RESULT_SUCCESS_init = 1;
    //返回失败
    public static final int RESULT_FAILURE =-1;
    /**用户不存在*/
    public static final int ERROR_UID_NOTEXIST = 10;
    
    /**该笔业务已经执行，并且是最后一笔交易*/
    public static final int ERROR_TRANSACTION_HAVEDONE = 11;
    /**该笔业务已经执行，并且不是最后一笔交易*/
    public static final int ERROR_TRANSACTION_HAVEDONENotLast = 12;
    
    /**该笔业务已经执行，并且交易信息不正确*/
    public static final int ERROR_TRANSACTION_ERROR = 13;
    /**交易不存在*/
    public static final int ERROR_TRANSACTION_NOTFOUND = 14;
    /**返回最后的transid*/
    public static final int ERROR_TRANSACTION_LAST = 15;
     
    /**
     * 系统错误
     */
    //传输层校验crc错误
    public static final int ERROR_CheckCrc_ERROR = 1000;
    
    
    public static final int ERROR_UPDATEDB_ERROR = 1001;
    public static final int ERROR_INSERT_BALANCE = 1002;
    public static final int ERROR_SELETEDB_ERROR = 1003;
  //数据库校验crc错误
    public static final int ERROR_DBCheckCrc_ERROR = 1004;
    
    
    
    /**balance 校验结果*/
    //UID不相同
    public static final int ERROR_BALANCE_UID_NOTEQUAL=100;
    //余额不相同
    public static final int ERROR_BALANCE_NOTEQUAL=101;
    //更新时间不一致
    public static final int ERROR_UPDATETIME_NOTEQUAL=102;
    //到期时间不一致
    public static final int ERROR_EXPIRETIME_NOTEQUAL=103;
    //交易事务号不一致
    public static final int ERROR_TRANSACTION_NOTEQUAL=104;
    //校验结果不一致
    public static final int ERROR_CHECKSUM_NOTEQUAL=105;
    //校验和不正确
    public static final int ERROR_CHECKSUM_ERROR=106;
    
    
    
    
}
