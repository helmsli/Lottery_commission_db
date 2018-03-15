SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS user_balance;
DROP TABLE IF EXISTS user_balanceLog;




/* Create Tables */

-- 按照用户分表(用户采用Hash分区)
CREATE TABLE user_balance
(
	user_id bigint(8) unsigned NOT NULL,
	telphoneNum varchar(20),
	updateTime datetime,
	balance decimal(19,4),
	balanceExt varchar(128),
	expireData date,
	transaction varchar(64) NOT NULL,
	updatesource varchar(64),
	CONSTRAINT idx_balance_userid UNIQUE (user_id)
) ENGINE = InnoDB COMMENT = '按照用户分表(用户采用Hash分区)' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci
PARTITION BY KEY(user_id)
PARTITIONS 2;


-- 分区:先按照transactionTime星期分区,按照星期分为7个分区(list),再按照用户KEY分区,如果还不能满
CREATE TABLE user_balanceLog
(
	transactionTime datetime,
	user_id bigint(8) unsigned,
	updateTime datetime,
	transaction varchar(64),
	updatesource varchar(64),
	beginningBalance decimal(19,4),
	beginningExpireTimes datetime,
	amount decimal(19,4),
	remark varchar(128),
	CONSTRAINT idx_blog_user_transtime UNIQUE (transactionTime, user_id, transaction)
) ENGINE = InnoDB COMMENT = '分区:先按照transactionTime星期分区,按照星期分为7个分区(list),再按照用户KEY分区,如果还不能满' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci
PARTITION BY LIST( dayofweek(transactionTime))
    SUBPARTITION BY KEY(user_id)
    SUBPARTITIONS 10
 (
        PARTITION pSun VALUES  IN (1),
	PARTITION pMon VALUES  IN (2),
	PARTITION pTue VALUES  IN (3),
	PARTITION pWed VALUES  IN (4),
	PARTITION pThu VALUES  IN (5),
	PARTITION pFri VALUES  IN (6),
	PARTITION pSat VALUES  IN (7)
  );



