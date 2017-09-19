/*出票系统派奖明细*/
DROP TABLE IF EXISTS DZ_TT_TICKETPRIZE;
create TABLE DZ_TT_TICKETPRIZE(
TT_ID bigint PRIMARY key not null auto_increment,
TT_TicketNo VARCHAR(100) not null COMMENT '用户方案编号',
TT_TicketMoney DECIMAL(18,2)  DEFAULT 0 not null COMMENT '票面金额',
TT_PrizeMoney DECIMAL(18,2)  DEFAULT 0 not null COMMENT '兑奖金额（税后）',
TT_PrizeDate datetime  null COMMENT '兑奖时间',
TT_TickDate datetime null COMMENT '出票成功时间',
TT_IssueNo VARCHAR(20) null COMMENT '奖期',
TT_InitDate datetime DEFAULT NOW() COMMENT '录入时间'
);

/*投注系统派奖明细*/
DROP TABLE IF EXISTS DZ_BT_TICKETPRIZE;
create TABLE DZ_BT_TICKETPRIZE(
BT_ID bigint PRIMARY key not null auto_increment,
BT_TicketNo VARCHAR(100) not null COMMENT '用户方案编号',
BT_TicketMoney DECIMAL(18,2)  DEFAULT 0 not null COMMENT '票面金额',
BT_PrizeMoney DECIMAL(18,2)  DEFAULT 0 not null COMMENT '兑奖金额（税后）',
BT_PrizeDate datetime  null COMMENT '兑奖时间',
BT_TickDate datetime null COMMENT '出票成功时间',
BT_IssueNo VARCHAR(20) null COMMENT '奖期',
BT_LotteryName VARCHAR(50) NULL COMMENT '彩种名称',
BT_UserID VARCHAR(50) NULL COMMENT '彩票系统账户ID',
BT_Schemeid bigint null DEFAULT 0 COMMENT '方案ID',
BT_InitDate datetime DEFAULT NOW()  COMMENT '录入时间'
);

/*兑奖差异明细*/
DROP TABLE IF EXISTS DZ_DF_TICKETPRIZE;
create TABLE DZ_DF_TICKETPRIZE(
DF_ID bigint PRIMARY key not null auto_increment,
DF_NormalDate date NULL COMMENT '统计日期',
DF_ReportID BIGINT NOT NULL COMMENT '总表ID',
DF_TicketNo VARCHAR(100) not null COMMENT '用户方案编号',
BT_TicketPrize DECIMAL(18,2)  DEFAULT 0 not null COMMENT '投注派奖金额',
TT_TicketPrize DECIMAL(18,2)  DEFAULT 0 not null COMMENT '出票派奖金额',
DF_DiffMoney DECIMAL(18,2) DEFAULT 0 COMMENT '差异金额',
DF_InitDate datetime DEFAULT NOW()  COMMENT '录入时间',
DF_ProcessStauts CHAR(1) DEFAULT 1 COMMENT '处理状态',
DF_ProcessInfo VARCHAR(2000) NULL COMMENT '处理备注'
);