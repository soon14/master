/*
Navicat MySQL Data Transfer

Source Server         : DeShi
Source Server Version : 50631
Source Host           : 192.168.192.230:3306
Source Database       : merchant

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2017-04-27 16:33:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for RP_CashDiffDetail
-- ----------------------------
DROP TABLE IF EXISTS `RP_CashDiffDetail`;
CREATE TABLE `RP_CashDiffDetail` (
  `DF_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NormalDate` date DEFAULT NULL COMMENT '统计日期',
  `PrizeTime` date DEFAULT NULL,
  `DF_ReportID` bigint(20) DEFAULT NULL COMMENT '总表ID',
  `TicketNo` varchar(100) DEFAULT NULL COMMENT '用户方案编号',
  `SendPrize` decimal(18,2) DEFAULT '0.00' COMMENT '线上派奖金额',
  `TicketPrize` decimal(18,2) DEFAULT '0.00' COMMENT '线上兑奖金额',
  `DiffMoney` decimal(18,2) DEFAULT '0.00' COMMENT '差异金额',
  `DF_InitDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  `DF_ProcessStauts` char(1) DEFAULT '1' COMMENT '处理状态',
  `DF_ProcessInfo` varchar(2000) DEFAULT NULL COMMENT '处理备注',
  PRIMARY KEY (`DF_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=132713 DEFAULT CHARSET=utf8;
