CREATE TABLE `DC_CP_TranceOrderInfo` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `sycDate` date DEFAULT NULL COMMENT '同步日期',
  `userId` bigint(10) DEFAULT NULL COMMENT '彩票系统用户ID',
  `lotteryName` varchar(30) DEFAULT NULL COMMENT '彩种名称',
  `playType` int(3) DEFAULT NULL COMMENT '玩法',
  `issue` varchar(10) DEFAULT NULL COMMENT '彩种期次',
  `schemeId` bigint(10) DEFAULT NULL COMMENT '方案id',
  `schemeExtendId` bigint(10) DEFAULT NULL COMMENT '方案扩展id',
  `money` double DEFAULT NULL COMMENT '购彩金额',
  `buyTime` datetime DEFAULT NULL COMMENT '购彩时间',
  `buyWay` int(1) DEFAULT '1' COMMENT '购买方式( 1:委托出票；2:自己取票)',
  `online` int(1) DEFAULT '1' COMMENT '购彩渠道(1:线上   0:线下)',
  `status` int(3) DEFAULT NULL COMMENT '购彩状态 1：支付成功   2:支付失败  3:出票成功 4:出票失败',
  `orderNo` varchar(30) DEFAULT NULL COMMENT '商户订单号',
  `transNo` varchar(30) DEFAULT NULL COMMENT '交易流水号',
  `isValid` int(1) DEFAULT '1' COMMENT '是否有效(1:有效  0:无效)',
  `ticketTime` int(11) DEFAULT NULL COMMENT '出票时间 ',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `schemeExtendType` int(5) DEFAULT NULL COMMENT '22:代购追号扩展方案 23:合买追号扩展方案',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60147 DEFAULT CHARSET=utf8;

