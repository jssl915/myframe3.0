/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 60011
Source Host           : localhost:3306
Source Database       : myframe

Target Server Type    : MYSQL
Target Server Version : 60011
File Encoding         : 65001

Date: 2015-09-09 15:58:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_s_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_s_detail`;
CREATE TABLE `t_s_detail` (
  `detail_id` int(16) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `dict_id` int(16) DEFAULT NULL COMMENT '参数ID',
  `detail_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '参数明细名称',
  `detail_value` int(16) DEFAULT NULL COMMENT '参数明细值',
  `detail_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '参数明细描述',
  `detail_status` int(16) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '新增时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_s_detail
-- ----------------------------
INSERT INTO `t_s_detail` VALUES ('8', '1', '启用', '1', '启用状态', '1', '2015-01-01 00:00:00', '2015-01-06 11:02:33');
INSERT INTO `t_s_detail` VALUES ('9', '1', '禁止', '2', '禁止状态', '1', '2015-01-01 00:00:00', '2015-01-04 14:04:39');
INSERT INTO `t_s_detail` VALUES ('13', '2', '男', '1', '男', '1', '2015-01-01 00:00:00', '2015-03-23 15:52:05');
INSERT INTO `t_s_detail` VALUES ('14', '2', '女', '0', '女', '1', '2015-01-01 00:00:00', '2015-03-23 15:52:20');
INSERT INTO `t_s_detail` VALUES ('15', '3', '菜单文件夹', '1', '', '1', '2015-01-01 00:00:00', '2015-03-23 15:53:10');
INSERT INTO `t_s_detail` VALUES ('16', '3', '页面菜单', '2', '', '1', '2015-01-01 00:00:00', '2015-03-23 15:53:26');
INSERT INTO `t_s_detail` VALUES ('17', '3', '功能按钮', '3', '', '1', '2015-01-01 00:00:00', '2015-03-23 15:53:42');

-- ----------------------------
-- Table structure for `t_s_dict`
-- ----------------------------
DROP TABLE IF EXISTS `t_s_dict`;
CREATE TABLE `t_s_dict` (
  `dict_id` int(16) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `dict_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '参数名称',
  `dict_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '参数描述',
  `dict_status` int(8) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '新增时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_s_dict
-- ----------------------------
INSERT INTO `t_s_dict` VALUES ('1', '状态', '1启用2禁止', '1', '2015-01-01 00:00:00', '2015-01-01 00:00:00');
INSERT INTO `t_s_dict` VALUES ('2', '性别', '性别:1男 2女', '1', '2015-01-01 00:00:00', '2015-01-01 00:00:00');
INSERT INTO `t_s_dict` VALUES ('3', '菜单类型', '1：菜单文件夹，2：页面菜单，3：功能按钮', '1', '2015-01-01 00:00:00', '2015-01-01 00:00:00');

-- ----------------------------
-- Table structure for `t_s_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_s_log`;
CREATE TABLE `t_s_log` (
  `log_id` int(16) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `action_url` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '访问URL',
  `log_time` datetime DEFAULT NULL COMMENT '访问时间',
  `user_ip` varchar(15) COLLATE utf8_bin DEFAULT '用户IP' COMMENT '用户IP',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `log_desc` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '日志描述',
  `user_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名称',
  `process_time` int(8) DEFAULT NULL COMMENT '操作响应时间',
  `controller_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '控制器名称',
  `controller_method` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '控制器方法名称',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_s_log
-- ----------------------------

-- ----------------------------
-- Table structure for `t_s_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_s_menu`;
CREATE TABLE `t_s_menu` (
  `menu_id` int(16) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `menu_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
  `menu_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单描述',
  `menu_url` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单URL',
  `menu_pid` int(8) DEFAULT NULL COMMENT '父菜单ID',
  `menu_type` int(8) DEFAULT NULL COMMENT '资源类型，1：文件夹菜单，2：功能菜单，3：功能按钮',
  `menu_status` int(8) DEFAULT NULL COMMENT '菜单状态',
  `menu_level` int(8) DEFAULT NULL COMMENT '菜单级别',
  `menu_icon` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单图标',
  `create_time` datetime DEFAULT NULL COMMENT '新增时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `menu_order` int(8) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_s_menu
-- ----------------------------
INSERT INTO `t_s_menu` VALUES ('-1', '资源树', '资源树', null, '-2', '1', '1', '0', null, '2015-01-01 00:00:00', '2015-01-01 00:00:00', '1');
INSERT INTO `t_s_menu` VALUES ('1', '系统管理', '系统管理', null, '-1', '1', '1', '1', null, '2015-01-01 00:00:00', '2015-01-01 00:00:00', '1');
INSERT INTO `t_s_menu` VALUES ('2', '系统管理员管理', '用户管理', '/system/prg/user/init', '1', '2', '1', '2', null, '2015-01-01 00:00:00', '2015-01-01 00:00:00', '1');
INSERT INTO `t_s_menu` VALUES ('3', '系统角色管理', '角色管理', '/system/prg/role/init', '1', '2', '1', '2', null, '2015-01-01 00:00:00', '2015-01-01 00:00:00', '3');
INSERT INTO `t_s_menu` VALUES ('4', '系统菜单管理', '菜单管理', '/system/prg/menu/init', '1', '2', '1', '2', null, '2015-01-01 00:00:00', '2015-01-01 00:00:00', '2');
INSERT INTO `t_s_menu` VALUES ('5', '系统参数管理', null, '/system/prg/dict/init', '1', '2', '1', '2', null, '2015-01-01 00:00:00', '2015-01-01 00:00:00', '4');
INSERT INTO `t_s_menu` VALUES ('6', '系统日志管理', '日志管理', '/system/prg/log/init', '1', '2', '1', '2', null, '2015-01-01 00:00:00', '2015-01-01 00:00:00', '5');

-- ----------------------------
-- Table structure for `t_s_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_s_role`;
CREATE TABLE `t_s_role` (
  `role_id` int(16) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `role_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  `role_order` int(8) DEFAULT NULL COMMENT '排序',
  `role_type` int(8) DEFAULT NULL COMMENT '角色类型',
  `role_status` int(8) DEFAULT NULL COMMENT '角色状态',
  `create_time` datetime DEFAULT NULL COMMENT '新增时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_s_role
-- ----------------------------
INSERT INTO `t_s_role` VALUES ('1', '超级管理员', '最高权限管理员', '1', null, '1', '2015-01-01 00:00:00', '2015-01-01 00:00:00');

-- ----------------------------
-- Table structure for `t_s_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_s_role_menu`;
CREATE TABLE `t_s_role_menu` (
  `role_menu_id` int(16) NOT NULL AUTO_INCREMENT COMMENT '角色菜单ID',
  `role_id` int(16) DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(16) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_role_menu
-- ----------------------------
INSERT INTO `t_s_role_menu` VALUES ('1', '1', '-1');
INSERT INTO `t_s_role_menu` VALUES ('2', '1', '1');
INSERT INTO `t_s_role_menu` VALUES ('3', '1', '2');
INSERT INTO `t_s_role_menu` VALUES ('4', '1', '3');
INSERT INTO `t_s_role_menu` VALUES ('5', '1', '4');
INSERT INTO `t_s_role_menu` VALUES ('6', '1', '5');
INSERT INTO `t_s_role_menu` VALUES ('7', '1', '6');

-- ----------------------------
-- Table structure for `t_s_role_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_s_role_user`;
CREATE TABLE `t_s_role_user` (
  `role_user_id` int(16) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` int(16) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(16) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`role_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_s_role_user
-- ----------------------------
INSERT INTO `t_s_role_user` VALUES ('64', '192', '1');
INSERT INTO `t_s_role_user` VALUES ('65', '1', '1');

-- ----------------------------
-- Table structure for `t_s_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_s_user`;
CREATE TABLE `t_s_user` (
  `user_id` int(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名称',
  `user_order` int(8) DEFAULT NULL COMMENT '排序',
  `user_type` int(8) DEFAULT NULL COMMENT '用户类型',
  `user_status` int(8) DEFAULT NULL COMMENT '状态',
  `user_pwd` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '新增时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `real_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `sex` int(1) DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=193 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_s_user
-- ----------------------------
INSERT INTO `t_s_user` VALUES ('1', 'super', '1', '1', '1', 'FBADE9E36A3F36D3D676C1B808451DD7', '2015-01-01 00:00:00', '2015-01-01 00:00:00', 'super', null, null, null);
INSERT INTO `t_s_user` VALUES ('190', '测试', null, '1', '1', '21218CCA77804D2BA1922C33E0151105', '2015-08-05 11:24:42', '2015-08-05 11:29:09', 'test', null, null, null);
INSERT INTO `t_s_user` VALUES ('192', 'lj', null, '1', '1', 'FBADE9E36A3F36D3D676C1B808451DD7', '2015-08-05 13:48:31', '2015-08-05 17:38:09', 'lj', null, null, '1');
