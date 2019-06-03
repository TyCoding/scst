/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : shiro

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 16/03/2019 12:48:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `parent_id` bigint(20) NOT NULL COMMENT '上级部门ID',
  `name` varchar(20) NOT NULL COMMENT '部门名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of tb_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (1, 0, '开发部', '2019-01-01 00:00:00');
INSERT INTO `sys_dept` VALUES (2, 1, '开发一部', '2019-02-02 13:08:33');
INSERT INTO `sys_dept` VALUES (3, 1, '开发二部', '2019-02-02 13:08:57');
INSERT INTO `sys_dept` VALUES (4, 0, '测试部', '2019-01-01 00:00:00');
INSERT INTO `sys_dept` VALUES (5, 4, '测试一部', '2019-02-02 13:09:11');
INSERT INTO `sys_dept` VALUES (6, 0, '人事部', '2019-01-01 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for tb_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(20) DEFAULT NULL COMMENT '操作用户',
  `operation` varchar(20) DEFAULT NULL COMMENT '操作描述',
  `time` bigint(20) DEFAULT NULL COMMENT '耗时(毫秒)',
  `method` varchar(100) DEFAULT NULL COMMENT '操作方法',
  `params` varchar(255) DEFAULT NULL COMMENT '操作参数',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  `location` varchar(20) DEFAULT NULL COMMENT '操作地点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Records of tb_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_log` VALUES (1, 'admin', '查看用户列表', 20, 'cn.tycoding.system.controller.UserController.queryList()', ' queryPage\"QueryPage(pageCode=1, pageSize=6)\" user\"User(id=null, username=null, password=null, salt=null, deptId=null, deptName=null, createTime=null, modifyTime=null, avatar=null, phone=null, sex=null, description=null, status=null)\"', '127.0.0.1', '2019-03-13 00:42:34', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `sys_log` VALUES (7, 'admin', '更新用户', 83, 'cn.tycoding.system.controller.UserController.update()', ' user\"UserWithRole(roleId=1, roleIds=[1])\"', '127.0.0.1', '2019-03-13 01:21:48', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `sys_log` VALUES (10, 'admin', '删除用户', 65, 'cn.tycoding.system.controller.UserController.delete()', ' ids\"[9]\"', '127.0.0.1', '2019-03-13 05:00:56', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `sys_log` VALUES (11, 'admin', '删除用户', 9, 'cn.tycoding.system.controller.UserController.delete()', ' ids\"[9]\"', '127.0.0.1', '2019-03-13 05:01:18', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `sys_log` VALUES (12, 'admin', '删除登录日志', 39, 'cn.tycoding.monitor.controller.LoginLogController.delete()', ' ids\"[3]\"', '127.0.0.1', '2019-03-13 05:13:03', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `sys_log` VALUES (13, 'admin', '删除日志', 44, 'cn.tycoding.monitor.controller.LogController.delete()', ' ids\"[8]\"', '127.0.0.1', '2019-03-13 05:15:54', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `sys_log` VALUES (14, 'admin', '删除日志', 9, 'cn.tycoding.monitor.controller.LogController.delete()', ' ids\"[9]\"', '127.0.0.1', '2019-03-13 05:15:58', '内网IP|0|0|内网IP|内网IP');
COMMIT;

-- ----------------------------
-- Table structure for tb_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `location` varchar(255) DEFAULT NULL COMMENT '登录地点',
  `create_time` datetime DEFAULT NULL COMMENT '登录时间',
  `device` varchar(255) DEFAULT NULL COMMENT '登录设备',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_login_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_login_log` VALUES (1, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 04:36:13', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (4, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 06:15:56', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (5, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 08:05:34', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (6, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 08:52:32', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (7, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 18:31:09', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (8, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 20:33:47', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (9, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 21:32:03', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (10, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-14 01:03:48', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
COMMIT;

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资源编号',
  `name` varchar(20) NOT NULL COMMENT '资源名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `url` varchar(100) DEFAULT NULL COMMENT 'URL',
  `permission` text COMMENT '权限标识',
  `type` varchar(20) NOT NULL COMMENT '类型：如button按钮 menu菜单',
  `icon` varchar(30) DEFAULT NULL COMMENT '菜单图标',
  `component` bigint(20) DEFAULT NULL COMMENT 'Vue组件',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, NULL, NULL, 'menu', 'fa fa-gear', 'index', '2019-01-01 00:00:00');
INSERT INTO `sys_menu` VALUES (2, '用户管理', 1, '/system/user', 'user:list', 'menu', NULL, 'index', '2019-01-01 00:00:00');
INSERT INTO `sys_menu` VALUES (3, '角色管理', 1, '/system/role', 'role:list', 'menu', NULL, 'index', '2019-01-01 00:00:00');
INSERT INTO `sys_menu` VALUES (4, '菜单管理', 1, '/system/menu', 'menu:list', 'menu', NULL, 'index', '2019-01-01 00:00:00');
INSERT INTO `sys_menu` VALUES (5, '部门管理', 1, '/system/dept', 'dept:list', 'menu', NULL, 'index', '2019-02-14 13:19:56');
INSERT INTO `sys_menu` VALUES (6, '新增用户', 2, NULL, 'user:add', 'button', NULL, 'index', '2019-01-01 00:00:00');
INSERT INTO `sys_menu` VALUES (7, '修改用户', 2, NULL, 'user:update', 'button', NULL, 'index', '2019-01-01 00:00:00');
INSERT INTO `sys_menu` VALUES (8, '删除用户', 2, NULL, 'user:delete', 'button', NULL, 'index', '2019-01-01 00:00:00');
INSERT INTO `sys_menu` VALUES (9, '新增角色', 3, NULL, 'role:add', 'button', NULL, 'index', '2019-01-01 00:00:00');
INSERT INTO `sys_menu` VALUES (10, '修改角色', 3, NULL, 'role:update', 'button', NULL, 'index', '2019-03-15 13:46:26');
INSERT INTO `sys_menu` VALUES (11, '删除用户', 3, NULL, 'role:delete', 'button', NULL, 'index', '2019-03-15 13:47:14');
INSERT INTO `sys_menu` VALUES (12, '新增菜单', 4, NULL, 'menu:add', 'button', NULL, 'index', '2019-03-15 13:47:55');
INSERT INTO `sys_menu` VALUES (13, '修改菜单', 4, NULL, 'menu:update', 'button', NULL, 'index', '2019-03-15 13:47:55');
INSERT INTO `sys_menu` VALUES (14, '删除菜单', 4, NULL, 'menu:delete', 'button', NULL, 'index', '2019-03-15 13:47:14');
INSERT INTO `sys_menu` VALUES (15, '新增部门', 5, NULL, 'dept:add', 'button', NULL, 'index', '2019-03-15 13:47:14');
INSERT INTO `sys_menu` VALUES (16, '修改部门', 5, NULL, 'dept:update', 'button', NULL, 'index', '2019-03-15 13:47:14');
INSERT INTO `sys_menu` VALUES (17, '删除部门', 5, NULL, 'dept:delete', 'button', NULL, 'index', '2019-03-15 13:47:14');
INSERT INTO `sys_menu` VALUES (18, '系统监控', 0, NULL, NULL, 'menu', 'fa fa-shield', 'index', '2019-02-05 09:07:39');
INSERT INTO `sys_menu` VALUES (19, '在线用户', 18, '/monitor/online', 'online:list', 'menu', NULL, 'index', '2019-02-14 14:29:53');
INSERT INTO `sys_menu` VALUES (20, '登录日志', 18, '/monitor/loginlog', 'loginlog:list', 'menu', NULL, 'index', '2019-02-14 14:31:54');
INSERT INTO `sys_menu` VALUES (21, '系统日志', 18, '/monitor/log', 'log:list', 'menu', NULL, 'index', '2019-02-05 09:09:36');
INSERT INTO `sys_menu` VALUES (22, 'Redis监控', 18, '/monitor/redis/monitor', 'redis:list', 'menu', NULL, 'index', '2019-02-05 09:10:28');
INSERT INTO `sys_menu` VALUES (23, 'Druid监控', 18, '/monitor/druid', 'druid:list', 'menu', NULL, 'index', '2019-03-14 13:07:56');
INSERT INTO `sys_menu` VALUES (24, '踢出用户', 19, NULL, 'online:delete', 'button', NULL, 'index', '2019-02-05 09:23:24');
INSERT INTO `sys_menu` VALUES (25, '删除日志', 20, NULL, 'loginlog:delete', 'button', NULL, 'index', '2019-02-05 09:23:24');
INSERT INTO `sys_menu` VALUES (26, '删除日志', 21, NULL, 'log:delete', 'button', NULL, 'index', '2019-02-05 09:23:24');
INSERT INTO `sys_menu` VALUES (27, '任务调度', 0, NULL, NULL, 'menu', 'fa fa-bell', 'index', '2019-01-01 00:00:00');
INSERT INTO `sys_menu` VALUES (28, '定时任务', 27, '/task', 'task', 'menu', NULL, 'index', '2019-02-05 09:23:28');
INSERT INTO `sys_menu` VALUES (29, '对象储存', 0, NULL, NULL, 'menu', 'fa fa-cloud', 'index', '2019-02-05 08:49:45');
INSERT INTO `sys_menu` VALUES (30, '七牛云', 29, '/storage/qiniu', 'qiniu:list', 'menu', '', 'index', '2019-02-05 08:51:30');
INSERT INTO `sys_menu` VALUES (31, '上传文件', 30, NULL, 'qiniu:upload', 'button', NULL, 'index', '2019-03-14 11:38:01');
INSERT INTO `sys_menu` VALUES (32, '修改文件', 30, NULL, 'qiniu:update', 'button', NULL, 'index', '2019-03-14 11:38:01');
INSERT INTO `sys_menu` VALUES (33, '删除文件', 30, NULL, 'qiniu:delete', 'button', NULL, 'index', '2019-03-14 11:38:01');
INSERT INTO `sys_menu` VALUES (34, '网络资源', 0, NULL, NULL, 'menu', 'fa fa-support', 'index', '2019-02-05 09:23:24');
INSERT INTO `sys_menu` VALUES (35, '天气查询', 34, '/web/weather', 'weather:list', 'menu', NULL, 'index', '2019-02-05 09:25:06');
INSERT INTO `sys_menu` VALUES (36, '影视资讯', 34, '/web/movie', 'movie:list', 'menu', NULL, 'index', '2019-03-14 11:38:01');
COMMIT;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '管理员', '2019-01-01 00:00:00', '管理员');
INSERT INTO `sys_role` VALUES (2, '测试账号', '2019-01-01 00:00:00', '测试，可查看所有页面，但无操作权限');
INSERT INTO `sys_role` VALUES (3, '用户管理员', '2019-01-01 00:00:00', '负责用户的增删改查操作');
INSERT INTO `sys_role` VALUES (4, '系统监控员', '2019-02-14 08:51:48', '可查看系统监控信息');
INSERT INTO `sys_role` VALUES (5, '天气预报员', '2019-02-14 02:54:56', '可查看天气预报信息');
INSERT INTO `sys_role` VALUES (6, '用户查看', '2019-02-14 02:59:17', '查看用户，但无操作权限');
COMMIT;

-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单/按钮ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源关联表';

-- ----------------------------
-- Records of tb_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 6);
INSERT INTO `sys_role_menu` VALUES (1, 7);
INSERT INTO `sys_role_menu` VALUES (1, 8);
INSERT INTO `sys_role_menu` VALUES (1, 9);
INSERT INTO `sys_role_menu` VALUES (1, 10);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (1, 13);
INSERT INTO `sys_role_menu` VALUES (1, 14);
INSERT INTO `sys_role_menu` VALUES (1, 15);
INSERT INTO `sys_role_menu` VALUES (1, 16);
INSERT INTO `sys_role_menu` VALUES (1, 17);
INSERT INTO `sys_role_menu` VALUES (1, 18);
INSERT INTO `sys_role_menu` VALUES (1, 19);
INSERT INTO `sys_role_menu` VALUES (1, 20);
INSERT INTO `sys_role_menu` VALUES (1, 21);
INSERT INTO `sys_role_menu` VALUES (1, 22);
INSERT INTO `sys_role_menu` VALUES (1, 23);
INSERT INTO `sys_role_menu` VALUES (1, 24);
INSERT INTO `sys_role_menu` VALUES (1, 25);
INSERT INTO `sys_role_menu` VALUES (1, 26);
INSERT INTO `sys_role_menu` VALUES (1, 27);
INSERT INTO `sys_role_menu` VALUES (1, 28);
INSERT INTO `sys_role_menu` VALUES (1, 29);
INSERT INTO `sys_role_menu` VALUES (1, 30);
INSERT INTO `sys_role_menu` VALUES (1, 31);
INSERT INTO `sys_role_menu` VALUES (1, 32);
INSERT INTO `sys_role_menu` VALUES (1, 33);
INSERT INTO `sys_role_menu` VALUES (1, 34);
INSERT INTO `sys_role_menu` VALUES (1, 35);
INSERT INTO `sys_role_menu` VALUES (1, 36);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 5);
INSERT INTO `sys_role_menu` VALUES (2, 18);
INSERT INTO `sys_role_menu` VALUES (2, 19);
INSERT INTO `sys_role_menu` VALUES (2, 20);
INSERT INTO `sys_role_menu` VALUES (2, 21);
INSERT INTO `sys_role_menu` VALUES (2, 22);
INSERT INTO `sys_role_menu` VALUES (2, 23);
INSERT INTO `sys_role_menu` VALUES (2, 27);
INSERT INTO `sys_role_menu` VALUES (2, 28);
INSERT INTO `sys_role_menu` VALUES (2, 29);
INSERT INTO `sys_role_menu` VALUES (2, 30);
INSERT INTO `sys_role_menu` VALUES (2, 34);
INSERT INTO `sys_role_menu` VALUES (2, 35);
INSERT INTO `sys_role_menu` VALUES (2, 36);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 6);
INSERT INTO `sys_role_menu` VALUES (3, 7);
INSERT INTO `sys_role_menu` VALUES (3, 8);
INSERT INTO `sys_role_menu` VALUES (4, 18);
INSERT INTO `sys_role_menu` VALUES (4, 19);
INSERT INTO `sys_role_menu` VALUES (4, 20);
INSERT INTO `sys_role_menu` VALUES (4, 21);
INSERT INTO `sys_role_menu` VALUES (4, 22);
INSERT INTO `sys_role_menu` VALUES (4, 23);
INSERT INTO `sys_role_menu` VALUES (5, 34);
INSERT INTO `sys_role_menu` VALUES (5, 35);
INSERT INTO `sys_role_menu` VALUES (6, 2);
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(100) NOT NULL COMMENT '盐值',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `sex` varchar(1) DEFAULT NULL COMMENT '性别 0男 1女',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态 0锁定 1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '123', '4bb248295857990678f15b9410e78e4d', 3, '2019-01-01 00:00:00', '2019-02-05 02:46:38', '/img/avatar/default.jpg', '19809587839', '0', '管理员', 1);
INSERT INTO `sys_user` VALUES (2, 'tycoding', '123', 'ab2874470d60b14ed8f1248ab0e527ad', 5, '2019-01-01 00:00:00', '2019-02-05 02:47:26', '/img/avatar/20180414165754.jpg', '18798797687', '0', '测试账号，可查看所有页面', 1);
INSERT INTO `sys_user` VALUES (3, 'tumo', '123', 'a9d14a4b3c80b20c3042842393623384', 6, '2019-02-03 03:37:34', '2019-02-03 07:37:12', '/img/avatar/20180414165827.jpg', '781797907', '1', '用户管理员，负责用户增删改查操作', 1);
INSERT INTO `sys_user` VALUES (4, 'monitor', '123', '9be3bd45b443283cc63ebc6a0b0c0cc9', 1, '2019-02-03 03:37:34', '2019-02-03 07:37:12', '/img/avatar/20180414165827.jpg', '18798797687', '1', '系统监控员，查看系统监控', 1);
INSERT INTO `sys_user` VALUES (5, 'synoptic', '123', '850ff5eb7d00cdd36a0e1f98d4a11553', 1, '2019-02-03 03:37:34', '2019-02-03 07:37:12', '/img/avatar/20180414165827.jpg', '18798797687', '1', '天气预报员，查看天气预报信息', 0);
INSERT INTO `sys_user` VALUES (6, 'user', '123', 'aa8d31f90f27ef28fe16f0b0c670540d', 1, '2019-02-03 03:37:34', '2019-02-03 07:37:12', '/img/avatar/20180414165827.jpg', '18798797687', '1', '用户查看，尽可查看用户信息', 0);
COMMIT;

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (3, 3);
INSERT INTO `sys_user_role` VALUES (3, 4);
INSERT INTO `sys_user_role` VALUES (4, 4);
INSERT INTO `sys_user_role` VALUES (4, 5);
INSERT INTO `sys_user_role` VALUES (5, 5);
INSERT INTO `sys_user_role` VALUES (6, 6);
INSERT INTO `sys_user_role` VALUES (7, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
