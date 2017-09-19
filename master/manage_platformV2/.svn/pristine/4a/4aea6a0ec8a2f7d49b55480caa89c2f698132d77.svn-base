/*
Navicat MySQL Data Transfer

Source Server         : DeShi
Source Server Version : 50631
Source Host           : 192.168.192.230:3306
Source Database       : merchant

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2017-04-27 16:32:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for TS_CashPrize
-- ----------------------------
DROP TABLE IF EXISTS `TS_CashPrize`;
CREATE TABLE `TS_CashPrize` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `batch_no` varchar(50) NOT NULL COMMENT '批次号',
  `pay_way` varchar(10) NOT NULL COMMENT '批次号',
  `machine_number` varchar(50) NOT NULL COMMENT '机器编号',
  `ticket_number` varchar(100) NOT NULL COMMENT '用户方案编号',
  `system_serial_number` varchar(50) NOT NULL COMMENT '系统流水号',
  `machine_after_tax_bonus` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '票机税后奖金',
  `net_befor_tax_bonus` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '网站税前奖金',
  `net_after_tax_bonus` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '网站税后奖金',
  `prize_type` varchar(10) DEFAULT NULL COMMENT '大小奖',
  `prize_date` datetime DEFAULT NULL COMMENT '兑奖时间',
  `issue_number` varchar(20) DEFAULT NULL COMMENT '期次',
  `betting_amount` decimal(8,2) DEFAULT NULL COMMENT '投注金额',
  `tick_date` datetime DEFAULT NULL COMMENT '出票成功时间',
  `create_date` date DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(10) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7454 DEFAULT CHARSET=utf8;
