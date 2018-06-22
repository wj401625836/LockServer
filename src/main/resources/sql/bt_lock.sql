
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `bt_lock`;

CREATE TABLE `bt_lock` (
  `id` bigint not null AUTO_INCREMENT comment '表ID',
  `lock_id` varchar (32) not null UNIQUE comment '锁ID',
  `lock_name` varchar (32) not null comment '锁名字',
  `user_id` varchar (32) not null comment '管理员ID',
  `authorized_ids` mediumtext comment '授权用户ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

