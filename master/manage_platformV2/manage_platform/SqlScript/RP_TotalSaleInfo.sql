DROP TABLE IF EXISTS `RP_TotalSaleInfo` ;

CREATE TABLE `RP_TotalSaleInfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `betting_amount` decimal(8,2) DEFAULT NULL COMMENT '投注金额',
  `ticket_amount` decimal(8,2) DEFAULT NULL COMMENT '出票金额',
  `betting_number` int(8) DEFAULT NULL COMMENT '投注条数',
  `ticket_number` int(8) DEFAULT NULL COMMENT '出票条数',
  `statistics_date` date DEFAULT NULL COMMENT '统计时间',
  `total_aumone` decimal(8,2) DEFAULT NULL COMMENT '总金额',
  `amount_difference` decimal(8,2) DEFAULT NULL COMMENT '差异金额',
  `number_difference` int(8) DEFAULT NULL COMMENT '差异条数',
  `offline_volume` decimal(8,2) DEFAULT NULL COMMENT '线下销量',
  `create_date` date DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(10) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=664 DEFAULT CHARSET=utf8;
