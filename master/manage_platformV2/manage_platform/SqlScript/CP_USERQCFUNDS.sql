
DROP TABLE IF EXISTS `CP_USERQCFUNDS`;
CREATE TABLE `CP_USERQCFUNDS` (
  `qcDate` date NOT NULL COMMENT '期初余额的日期',
  `userId` bigint(10) NOT NULL COMMENT '投注系统用户ID',
  `funds` double NOT NULL DEFAULT '0' COMMENT '剩余资金(包含冻结资金)',
  `transTime` datetime NOT NULL COMMENT '最后交易日期时间',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `isValid` int(1) NOT NULL DEFAULT '1' COMMENT '是否有效(0:无效 1:有效)',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=26612 DEFAULT CHARSET=utf8;