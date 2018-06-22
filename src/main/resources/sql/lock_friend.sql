/*
Navicat MySQL Data Transfer

Source Server         : 本机数据库
Source Server Version : 80011
Source Host           : localhost:9520
Source Database       : lock

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2018-06-12 10:52:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for lock_friend
-- ----------------------------
DROP TABLE IF EXISTS `lock_friend`;
CREATE TABLE `lock_friend` (
  `id` bigint not null AUTO_INCREMENT comment '表ID',
  `user_id` varchar (32) not null comment '管理员ID （手机号码）',
  `fr_user_id` varchar (32) not null comment '朋友id',
  `fr_user_name` varchar (32) comment '自定义朋友昵称',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
  PRIMARY KEY (`id`)
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
