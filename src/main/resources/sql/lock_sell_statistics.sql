
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `lock_sell_statistics`;

CREATE TABLE `lock_sell_statistics` (
  `id` bigint not null AUTO_INCREMENT comment '表ID',
  `lock_id` varchar (32) not null UNIQUE comment '锁ID',
  `cust_project_name` varchar (64) comment '客户项目名称',
  `intr_project_name` varchar(64) comment '内部项目名称',
  `verno` varchar(64) comment '版本号',
  `user_id` varchar (32) comment '管理员ID',
  `flag` int default 0 comment '是否已销售 0 未销售 1 已销售',
  `ip` varchar(64) comment '录入统计的IP',
  `country` varchar(64) comment '录入统计的国籍',
  `addr` varchar(64) comment '录入者的详细地址',
  `web_type` varchar(64) comment '录入者的网络类型',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

