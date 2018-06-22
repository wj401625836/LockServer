
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
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
CREATE TABLE `user` (
  `id` bigint not null AUTO_INCREMENT comment '表ID',
  `user_id` varchar (32) not null UNIQUE comment '手机号码',
  `channel_id` varchar (32)  comment '百度push Id',
  `user_name` varchar(64) not null comment '用户名字',
  `security_type` int default 0 comment '登录安全类型 0 lock无密码 1 密码锁 2 图案锁 3 指纹锁',
  `security_password` varchar(32) comment '设定的密码锁',
  `security_pattern` varchar(32) comment '设定的图案锁',
  `qq_number` varchar(32) UNIQUE comment '绑定的QQ号码',
  `weixin_number` varchar(32) UNIQUE comment '绑定的微信号码',
  `logout` int default 0 comment '是否已注销 0 未注销 1 已注销',
  `avatar` mediumtext comment '用户头像',
  `other_lock_ids` mediumtext comment '授权的用户锁',
  `other_lock_names` mediumtext comment '自定义授权锁昵称',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

