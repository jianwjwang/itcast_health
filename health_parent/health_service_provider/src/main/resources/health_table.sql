/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50027
Source Host           : localhost:3306
Source Database       : health

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2019-04-11 17:00:08
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `t_checkgroup`
-- ----------------------------
DROP TABLE IF EXISTS `t_checkgroup`;
CREATE TABLE `t_checkgroup` (
  `id` int(11) NOT NULL auto_increment,
  `code` varchar(32) default NULL,
  `name` varchar(32) default NULL,
  `helpCode` varchar(32) default NULL,
  `sex` char(1) default NULL,
  `remark` varchar(128) default NULL,
  `attention` varchar(128) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `t_checkgroup_checkitem`
-- ----------------------------
DROP TABLE IF EXISTS `t_checkgroup_checkitem`;
CREATE TABLE `t_checkgroup_checkitem` (
  `checkgroup_id` int(11) NOT NULL default '0',
  `checkitem_id` int(11) NOT NULL default '0',
  PRIMARY KEY  (`checkgroup_id`,`checkitem_id`),
  KEY `item_id` (`checkitem_id`),
  CONSTRAINT `group_id` FOREIGN KEY (`checkgroup_id`) REFERENCES `t_checkgroup` (`id`),
  CONSTRAINT `item_id` FOREIGN KEY (`checkitem_id`) REFERENCES `t_checkitem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_checkgroup_checkitem
-- ----------------------------


-- ----------------------------
-- Table structure for `t_checkitem`
-- ----------------------------
DROP TABLE IF EXISTS `t_checkitem`;
CREATE TABLE `t_checkitem` (
  `id` int(11) NOT NULL auto_increment,
  `code` varchar(16) default NULL,
  `name` varchar(32) default NULL,
  `sex` char(1) default NULL,
  `age` varchar(32) default NULL,
  `price` float default NULL,
  `type` char(1) default NULL COMMENT '查检项类型,分为检查和检验两种',
  `attention` varchar(128) default NULL,
  `remark` varchar(128) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_checkitem
-- ----------------------------

-- ----------------------------
-- Table structure for `t_member`
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member` (
  `id` int(11) NOT NULL auto_increment,
  `fileNumber` varchar(32) default NULL,
  `name` varchar(32) default NULL,
  `sex` varchar(8) default NULL,
  `idCard` varchar(18) default NULL,
  `phoneNumber` varchar(11) default NULL,
  `regTime` date default NULL,
  `password` varchar(32) default NULL,
  `email` varchar(32) default NULL,
  `birthday` date default NULL,
  `remark` varchar(128) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_member
-- ----------------------------

-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(128) default NULL,
  `linkUrl` varchar(128) default NULL,
  `path` varchar(128) default NULL,
  `priority` int(11) default NULL,
  `icon` varchar(64) default NULL,
  `description` varchar(128) default NULL,
  `parentMenuId` int(11) default NULL,
  `level` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Reference_13` (`parentMenuId`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`parentMenuId`) REFERENCES `t_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------


-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL auto_increment,
  `member_id` int(11) default NULL COMMENT '员会id',
  `orderDate` date default NULL COMMENT '约预日期',
  `orderType` varchar(8) default NULL COMMENT '约预类型 电话预约/微信预约',
  `orderStatus` varchar(8) default NULL COMMENT '预约状态（是否到诊）',
  `setmeal_id` int(11) default NULL COMMENT '餐套id',
  PRIMARY KEY  (`id`),
  KEY `key_member_id` (`member_id`),
  KEY `key_setmeal_id` (`setmeal_id`),
  CONSTRAINT `key_member_id` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`id`),
  CONSTRAINT `key_setmeal_id` FOREIGN KEY (`setmeal_id`) REFERENCES `t_setmeal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------


-- ----------------------------
-- Table structure for `t_ordersetting`
-- ----------------------------
DROP TABLE IF EXISTS `t_ordersetting`;
CREATE TABLE `t_ordersetting` (
  `id` int(11) NOT NULL auto_increment,
  `orderDate` date default NULL COMMENT '约预日期',
  `number` int(11) default NULL COMMENT '可预约人数',
  `reservations` int(11) default NULL COMMENT '已预约人数',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ordersetting
-- ----------------------------


-- ----------------------------
-- Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(32) default NULL,
  `keyword` varchar(64) default NULL,
  `description` varchar(128) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(32) default NULL,
  `keyword` varchar(64) default NULL,
  `description` varchar(128) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY  (`role_id`,`menu_id`),
  KEY `FK_Reference_10` (`menu_id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`menu_id`) REFERENCES `t_menu` (`id`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY  (`role_id`,`permission_id`),
  KEY `FK_Reference_12` (`permission_id`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------


-- ----------------------------
-- Table structure for `t_setmeal`
-- ----------------------------
DROP TABLE IF EXISTS `t_setmeal`;
CREATE TABLE `t_setmeal` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(128) default NULL,
  `code` varchar(8) default NULL,
  `helpCode` varchar(16) default NULL,
  `sex` char(1) default NULL,
  `age` varchar(32) default NULL,
  `price` float default NULL,
  `remark` varchar(128) default NULL,
  `attention` varchar(128) default NULL,
  `img` varchar(128) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_setmeal
-- ----------------------------


-- ----------------------------
-- Table structure for `t_setmeal_checkgroup`
-- ----------------------------
DROP TABLE IF EXISTS `t_setmeal_checkgroup`;
CREATE TABLE `t_setmeal_checkgroup` (
  `setmeal_id` int(11) NOT NULL default '0',
  `checkgroup_id` int(11) NOT NULL default '0',
  PRIMARY KEY  (`setmeal_id`,`checkgroup_id`),
  KEY `checkgroup_key` (`checkgroup_id`),
  CONSTRAINT `checkgroup_key` FOREIGN KEY (`checkgroup_id`) REFERENCES `t_checkgroup` (`id`),
  CONSTRAINT `setmeal_key` FOREIGN KEY (`setmeal_id`) REFERENCES `t_setmeal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_setmeal_checkgroup
-- ----------------------------


-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL auto_increment,
  `birthday` date default NULL,
  `gender` varchar(1) default NULL,
  `username` varchar(32) default NULL,
  `password` varchar(256) default NULL,
  `remark` varchar(32) default NULL,
  `station` varchar(1) default NULL,
  `telephone` varchar(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------


-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY  (`user_id`,`role_id`),
  KEY `FK_Reference_8` (`role_id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
