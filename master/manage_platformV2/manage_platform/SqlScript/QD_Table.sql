DROP TABLE IF EXISTS QD_MERCHANT;
Create table QD_MERCHANT(
M_ID bigint PRIMARY key  not null auto_increment,
M_CPUserid bigint not null COMMENT '彩票账户ID',
M_Name VARCHAR(200) not null COMMENT '商户名称',
M_ParentMerchant bigint  null COMMENT '上级商户ID',
M_Type char(1) null COMMENT '商户类型1=个人，2=机构', 
M_Level char(1) null COMMENT '商户等级',
M_Status char(1)  DEFAULT 0 COMMENT '商户状态0=未审核；1=一审；2=二审；3=拒绝', 
M_Barcode VARCHAR(200) null COMMENT '商户二维码', 
M_Mobile VARCHAR(11) null  COMMENT '商户手机号', 
M_Address VARCHAR(500) null  COMMENT '商户地址' ,
M_ContactUser VARCHAR(100) null  COMMENT '商户联系人姓名', 
M_ContactMobile VARCHAR(50) null  COMMENT '商户联系人电话', 
M_License VARCHAR(200) null COMMENT '商户三证照片', 
M_Idcard VARCHAR(20) null COMMENT '商户法人身份证', ,
M_introduce VARCHAR(2000) null COMMENT '商户介绍' ,
M_AccountID VARCHAR(50) null COMMENT '商户系统账户ID', 
M_CommionType char(1) null COMMENT '渠道返佣类型1=日返 2=周返 3=月返',
M_Info2 VARCHAR(50) null,
M_Info3 VARCHAR(50) null
);
alter table QD_MERCHANT AUTO_INCREMENT=100000000;

DROP TABLE IF EXISTS QD_BASECOMMISSION;
create table QD_BASECOMMISSION(
BC_ID int PRIMARY key not null auto_increment,
M_ID bigint not null,
BC_Type CHAR(1) null,
BC_RankMin int DEFAULT 0 not null COMMENT '最小值',
BC_RankMax int DEFAULT 0 not null COMMENT '最大值',
BC_Percent int DEFAULT 0 not null COMMENT '返佣百分比',
BC_IsEnable CHAR(1) DEFAULT 1  COMMENT '是否启用1=启用，2=禁用',
BC_CreateUser VARCHAR(20) null,
BC_CreateDate  datetime null
);
alter table QD_BASECOMMISSION AUTO_INCREMENT=100000000;


DROP TABLE IF EXISTS QD_COMMISSION;
CREATE TABLE QD_COMMISSION(
C_ID bigint PRIMARY key not null auto_increment,
M_ID bigint not null,
BC_ID int  not null,
C_BeginDate DATE null,
C_EndDate DATE null,
C_SellMoneyOnline DECIMAL(18,2) DEFAULT 0 not null COMMENT  '线上销售额',
C_SellMoneyOffline DECIMAL(18,2) DEFAULT 0 not null COMMENT  '线下销售额',
C_CommissionOnline DECIMAL(18,2)  DEFAULT 0 not null COMMENT  '线上佣金',
C_CommissionOffline DECIMAL(18,2)  DEFAULT 0 not null COMMENT  '线下佣金',
C_IsOver  CHAR(1) DEFAULT 2 COMMENT  '是否已返 1=已返 2=未返',
C_SendCommissionDate datetime null,
C_UserName VARCHAR(20) NULL
);
alter table QD_COMMISSION AUTO_INCREMENT=100000000;


DROP TABLE IF EXISTS QD_PAYABLE;
CREATE TABLE QD_PAYABLE(
P_ID bigint PRIMARY key not null auto_increment,
M_ID bigint not null ,
C_ID bigint not null COMMENT '结算佣金',
MA_ID int not null COMMENT '渠道账户信息',
P_Money DECIMAL(18,2)  DEFAULT 0 not null COMMENT '付款金额',
P_PayDate datetime  null,
P_User VARCHAR(20) null COMMENT '操作人',
P_Status CHAR(1) DEFAULT 1 not null COMMENT '付款单状态1=未付，2=已付款，3=作废'
);
alter table QD_PAYABLE AUTO_INCREMENT=100000000;


DROP TABLE IF EXISTS MP_PrePaymentInfo;
CREATE TABLE MP_PrePaymentInfo(
Pp_ID bigint PRIMARY key not null auto_increment,
M_ID bigint not null,
Pp_MerchantName VARCHAR(200) not null,
Pp_PayMoney DECIMAL(18,2)   DEFAULT 0 not null,
Pp_PayType  CHAR(1) DEFAULT 1 COMMENT '预收款类型1=出票预售，2=其他',
PP_Balance DECIMAL(18,2)   DEFAULT 0 not null COMMENT'余额',
PP_WaringMoney DECIMAL(18,2) DEFAULT 0 not null COMMENT'预警金额',
Pp_UserName VARCHAR(20) null COMMENT'操作人',
Pp_ChangeDate datetime  null,
pp_Status CHAR(1) DEFAULT 1 null
);
alter table MP_PrePaymentInfo AUTO_INCREMENT=100000000;

DROP TABLE IF EXISTS QD_MERCHANTACCOUNT;
create table QD_MERCHANTACCOUNT(
MA_ID int  PRIMARY key not null auto_increment,
M_ID bigint not null,
MA_Account VARCHAR(50) not null,
MA_AccountType CHAR(1) DEFAULT 1 not null COMMENT '1=彩票账户 2=微信 3=支付宝 4=银行账户',
MA_IsEnable  CHAR(1) DEFAULT 1 not null COMMENT '1=启用，2=禁用',
MA_UserName	VARCHAR(100) not null COMMENT '账户姓名',
MA_AccountName VARCHAR(100) null COMMENT '账户名称',
MA_BankName VARCHAR(100) null COMMENT '银行名称',
MA_SubBankName VARCHAR(100) null COMMENT '支行名称'
);

DROP TABLE IF EXISTS QD_TICKETINFO;
create TABLE QD_TICKETINFO(
T_ID bigint PRIMARY key not null auto_increment,
T_BatchNo varchar(50) null COMMENT '批次号',
T_TicketNo VARCHAR(100) not null,
T_LotteryName VARCHAR(50) null,
T_TicketMoney DECIMAL(18,2)  DEFAULT 0 not null,
T_MachionNo VARCHAR(20) null COMMENT '投注机编号',
T_PlayWay VARCHAR(50) null COMMENT '玩法',
T_TickDate datetime null,
T_IssueNo VARCHAR(20) null,
T_IsOnline CHAR(1) null COMMENT '1=Online 2=offline',
T_MID bigint null
);
DROP TABLE IF EXISTS PT_TRANSFILERECORDS;
Create TABLE PT_TRANSFILERECORDS(
TF_ID	INT PRIMARY KEY NOT NULL auto_increment,
TF_FileName	VARCHAR(200) NULL COMMENT '交易文件名',
TF_TransTime	Datetime DEFAULT NOW() COMMENT '交易时间',
TF_TransType CHAR(1) NULL COMMENT '交易类型1=投注，2=出票，3=银企直连，4=第三方支付',
TF_TransStatus CHAR(1) DEFAULT 1 COMMENT '交易状态1=成功，2=失败',
TF_UserName VARCHAR(50) not null COMMENT '操作人'
);
alter table QD_BASECOMMISSION modify column BC_OnePercent double;
alter table QD_BASECOMMISSION modify column BC_TwoPercent double;
alter table QD_BASECOMMISSION modify column BC_ThreePercent double;
alter table QD_BASECOMMISSION rename MP_BaseCommission;
alter table QD_MERCHANT rename MP_MerchantInfo;



