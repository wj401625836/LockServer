
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for unlock_record
-- ----------------------------
DROP TABLE IF EXISTS `unlock_record`;
/*CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(64) not null comment '用户名字',
  `password` varchar (64) not null comment '用户密码',
  `user_id` varchar (32) comment '手机号码',
  `user_type` int default 0 comment '登录类型 0 手机 1 qq 2 微信',
  `nickname` varchar (64) comment '用户昵称',
  `id_no` varchar (32) comment '身份证号码',
  `logged` int default 0 comment '是否已注册 0 未注册 1 已注册',
  `logout` int default 0 comment '是否已注销 0 未注销 1 已注销',
  `avatar` varchar(1024) comment '用户头像',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;*/
CREATE TABLE `unlock_record` (
  `id` bigint not null AUTO_INCREMENT comment '表ID',
  `lock_id` varchar (32) not null comment '蓝牙指纹锁ID （蓝牙地址）',
  `unlock_user_id` varchar (32) not null comment '解锁用户，用户ID （手机号码）',
  `unlock_type` int default 0 comment '解锁方式 0．指纹解锁 1．蓝牙解锁',
  `unlock_time` varchar (32) not null  comment '解锁时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

