/*
Navicat MySQL Data Transfer

Source Server         : DeShi
Source Server Version : 50631
Source Host           : 192.168.192.230:3306
Source Database       : merchant

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2017-04-27 16:32:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for RP_CashAmountGap
-- ----------------------------
DROP TABLE IF EXISTS `RP_CashAmountGap`;
CREATE TABLE `RP_CashAmountGap` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `date` varchar(10) NOT NULL COMMENT '日期',
  `start_gap` decimal(8,2) DEFAULT NULL COMMENT '期初差异额',
  `online_redeem_amount` decimal(8,2) DEFAULT NULL COMMENT '线上兑奖总额',
  `big_amount` decimal(8,2) DEFAULT NULL COMMENT '大额兑奖',
  `online_sent_amount` decimal(8,2) DEFAULT NULL COMMENT '线上派奖金额',
  `redeem_sent_gap` decimal(8,2) DEFAULT NULL COMMENT '线上兑奖和派奖的差异',
  `end_gap` decimal(8,2) DEFAULT NULL COMMENT '期末差异额',
  `offline_redeem_amount` decimal(8,2) DEFAULT NULL COMMENT '线下兑奖总额',
  `total_redeem_amount` decimal(8,2) DEFAULT NULL COMMENT '总的兑奖金额',
  `isValid` int(1) DEFAULT NULL COMMENT '状态',
  `create_date` date DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(10) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
