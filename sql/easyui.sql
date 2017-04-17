/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : easyui

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-04-17 15:28:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `ID` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `ICON` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '图标',
  `NAME` varchar(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '名称',
  `TYPE` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '类型（0:菜单；1:按钮）',
  `SEQ` int(11) DEFAULT NULL COMMENT '排序',
  `URL` varchar(200) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '链接',
  `FLAG` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '标识位',
  `PID` varchar(36) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '父ID',
  `REMARK` varchar(200) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注',
  `CREATE_USER` varchar(36) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `CREATE_TM` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` varchar(36) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '修改人',
  `UPDATE_TM` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`),
  KEY `FK_m0i6pj14hcg1mleojnl7igg6o` (`PID`),
  KEY `FK_6w0sgqbec1o81uqqjsmejg57p` (`TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('buggl', 'bug', 'BUG管理', '0', '4', '/bugController/manager', null, 'xtgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('bugglAdd', 'bug_add', '添加BUG', '1', '3', '/bugController/add', null, 'buggl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('bugglAddPage', 'bug_add', '添加BUG页面', '1', '2', '/bugController/addPage', null, 'buggl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('bugglDateGrid', 'bug_link', 'BUG表格', '1', '1', '/bugController/dataGrid', null, 'buggl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('bugglDelete', 'bug_delete', '删除BUG', '1', '6', '/bugController/delete', null, 'buggl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('bugglEdit', 'bug_edit', '编辑BUG', '1', '5', '/bugController/edit', null, 'buggl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('bugglEditPage', 'bug_edit', '编辑BUG页面', '1', '4', '/bugController/editPage', null, 'buggl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('bugglView', 'bug_link', '查看BUG', '1', '7', '/bugController/view', null, 'buggl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('chart', 'chart_bar', '图表管理', '0', '7', null, null, null, null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('jeasyuiApi', 'book', 'EasyUI API', '0', '1000', 'http://jeasyui.com/documentation', null, null, null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('jsgl', 'tux', '角色管理', '0', '2', '/roleController/manager', null, 'xtgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('jsglAdd', 'wrench', '添加角色', '1', '3', '/roleController/add', null, 'jsgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('jsglAddPage', 'wrench', '添加角色页面', '1', '2', '/roleController/addPage', null, 'jsgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('jsglDelete', 'wrench', '删除角色', '1', '6', '/roleController/delete', null, 'jsgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('jsglEdit', 'wrench', '编辑角色', '1', '5', '/roleController/edit', null, 'jsgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('jsglEditPage', 'wrench', '编辑角色页面', '1', '4', '/roleController/editPage', null, 'jsgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('jsglGrant', 'wrench', '角色授权', '1', '8', '/roleController/grant', null, 'jsgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('jsglGrantPage', 'wrench', '角色授权页面', '1', '7', '/roleController/grantPage', null, 'jsgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('jsglTreeGrid', 'wrench', '角色表格', '1', '1', '/roleController/treeGrid', null, 'jsgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('sjygl', 'server_database', '数据源管理', '0', '5', '/druidController/druid', null, 'xtgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('userCreateDatetimeChart', 'chart_curve', '用户图表', '0', '1', '/chartController/userCreateDatetimeChart', null, 'chart', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('wjgl', 'server_database', '文件管理', '1', '6', '', null, 'xtgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('wjglUpload', 'server_database', '上传文件', '1', '2', '/fileController/upload', null, 'wjgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('wjglView', 'server_database', '浏览服务器文件', '1', '1', '/fileController/fileManage', null, 'wjgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('xtgl', 'plugin', '系统管理', '0', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('yhgl', 'status_online', '用户管理', '0', '3', '/userController/manager', null, 'xtgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('yhglAdd', 'wrench', '添加用户', '1', '3', '/userController/add', null, 'yhgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('yhglAddPage', 'wrench', '添加用户页面', '1', '2', '/userController/addPage', null, 'yhgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('yhglBatchDelete', 'wrench', '批量删除用户', '1', '7', '/userController/batchDelete', null, 'yhgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('yhglDateGrid', 'wrench', '用户表格', '1', '1', '/userController/dataGrid', null, 'yhgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('yhglDelete', 'wrench', '删除用户', '1', '6', '/userController/delete', null, 'yhgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('yhglEdit', 'wrench', '编辑用户', '1', '5', '/userController/edit', null, 'yhgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('yhglEditPage', 'wrench', '编辑用户页面', '1', '4', '/userController/editPage', null, 'yhgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('yhglEditPwd', 'wrench', '用户修改密码', '1', '11', '/userController/editPwd', null, 'yhgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('yhglEditPwdPage', 'wrench', '用户修改密码页面', '1', '10', '/userController/editPwdPage', null, 'yhgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('yhglGrant', 'wrench', '用户授权', '1', '9', '/userController/grant', null, 'yhgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('yhglGrantPage', 'wrench', '用户授权页面', '1', '8', '/userController/grantPage', null, 'yhgl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('zygl', 'database_gear', '资源管理', '0', '1', '/resourceController/manager', null, 'xtgl', '管理系统中所有的菜单或功能', null, null, null, null);
INSERT INTO `sys_resource` VALUES ('zyglAdd', 'wrench', '添加资源', '1', '4', '/resourceController/add', null, 'zygl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('zyglAddPage', 'wrench', '添加资源页面', '1', '3', '/resourceController/addPage', null, 'zygl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('zyglDelete', 'wrench', '删除资源', '1', '7', '/resourceController/delete', null, 'zygl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('zyglEdit', 'wrench', '编辑资源', '1', '6', '/resourceController/edit', null, 'zygl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('zyglEditPage', 'wrench', '编辑资源页面', '1', '5', '/resourceController/editPage', null, 'zygl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('zyglMenu', 'wrench', '功能菜单', '1', '2', '/resourceController/tree', null, 'zygl', null, null, null, null, null);
INSERT INTO `sys_resource` VALUES ('zyglTreeGrid', 'wrench', '资源表格', '1', '1', '/resourceController/treeGrid', null, 'zygl', '显示资源列表', null, null, null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ID` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `NAME` varchar(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '角色名',
  `REMARK` varchar(200) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注',
  `SEQ` int(11) DEFAULT NULL COMMENT '排序',
  `FLAG` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '标识',
  `PID` varchar(36) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '父ID',
  `CREATE_USER` varchar(36) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `CREATE_TM` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` varchar(36) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '修改人',
  `UPDATE_TM` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`),
  KEY `FK_3qq9eyhwbhblv83lt270gxbik` (`PID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('0', '超管', '超级管理员角色，拥有系统中所有的资源访问权限', '0', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('bugAdmin', 'BUG管理员', null, '5', null, '0', null, null, null, null);
INSERT INTO `sys_role` VALUES ('guest', 'Guest', '只拥有只看的权限', '1', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('jsAdmin', '角色管理员', null, '2', null, '0', null, null, null, null);
INSERT INTO `sys_role` VALUES ('sjyAdmin', '数据源管理员', null, '4', null, '0', null, null, null, null);
INSERT INTO `sys_role` VALUES ('yhAdmin', '用户管理员', null, '3', null, '0', null, null, null, null);
INSERT INTO `sys_role` VALUES ('zyAdmin', '资源管理员', null, '1', null, '0', null, null, null, null);

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `SYS_ROLE_ID` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT 'sys_role表主键',
  `SYS_RESOURCE_ID` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT 'sys_resource表主键',
  PRIMARY KEY (`SYS_RESOURCE_ID`,`SYS_ROLE_ID`),
  KEY `FK_18oms8g4ib4h67dqx0f3fwaed` (`SYS_RESOURCE_ID`),
  KEY `FK_aunc1ssqh18meky8cxl48i4m9` (`SYS_ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('0', 'buggl');
INSERT INTO `sys_role_resource` VALUES ('bugAdmin', 'buggl');
INSERT INTO `sys_role_resource` VALUES ('0', 'bugglAdd');
INSERT INTO `sys_role_resource` VALUES ('bugAdmin', 'bugglAdd');
INSERT INTO `sys_role_resource` VALUES ('0', 'bugglAddPage');
INSERT INTO `sys_role_resource` VALUES ('bugAdmin', 'bugglAddPage');
INSERT INTO `sys_role_resource` VALUES ('0', 'bugglDateGrid');
INSERT INTO `sys_role_resource` VALUES ('bugAdmin', 'bugglDateGrid');
INSERT INTO `sys_role_resource` VALUES ('0', 'bugglDelete');
INSERT INTO `sys_role_resource` VALUES ('bugAdmin', 'bugglDelete');
INSERT INTO `sys_role_resource` VALUES ('0', 'bugglEdit');
INSERT INTO `sys_role_resource` VALUES ('bugAdmin', 'bugglEdit');
INSERT INTO `sys_role_resource` VALUES ('0', 'bugglEditPage');
INSERT INTO `sys_role_resource` VALUES ('bugAdmin', 'bugglEditPage');
INSERT INTO `sys_role_resource` VALUES ('0', 'bugglView');
INSERT INTO `sys_role_resource` VALUES ('bugAdmin', 'bugglView');
INSERT INTO `sys_role_resource` VALUES ('0', 'chart');
INSERT INTO `sys_role_resource` VALUES ('0', 'jeasyuiApi');
INSERT INTO `sys_role_resource` VALUES ('guest', 'jeasyuiApi');
INSERT INTO `sys_role_resource` VALUES ('0', 'jsgl');
INSERT INTO `sys_role_resource` VALUES ('guest', 'jsgl');
INSERT INTO `sys_role_resource` VALUES ('jsAdmin', 'jsgl');
INSERT INTO `sys_role_resource` VALUES ('0', 'jsglAdd');
INSERT INTO `sys_role_resource` VALUES ('jsAdmin', 'jsglAdd');
INSERT INTO `sys_role_resource` VALUES ('0', 'jsglAddPage');
INSERT INTO `sys_role_resource` VALUES ('jsAdmin', 'jsglAddPage');
INSERT INTO `sys_role_resource` VALUES ('0', 'jsglDelete');
INSERT INTO `sys_role_resource` VALUES ('jsAdmin', 'jsglDelete');
INSERT INTO `sys_role_resource` VALUES ('0', 'jsglEdit');
INSERT INTO `sys_role_resource` VALUES ('jsAdmin', 'jsglEdit');
INSERT INTO `sys_role_resource` VALUES ('0', 'jsglEditPage');
INSERT INTO `sys_role_resource` VALUES ('jsAdmin', 'jsglEditPage');
INSERT INTO `sys_role_resource` VALUES ('0', 'jsglGrant');
INSERT INTO `sys_role_resource` VALUES ('jsAdmin', 'jsglGrant');
INSERT INTO `sys_role_resource` VALUES ('0', 'jsglGrantPage');
INSERT INTO `sys_role_resource` VALUES ('jsAdmin', 'jsglGrantPage');
INSERT INTO `sys_role_resource` VALUES ('0', 'jsglTreeGrid');
INSERT INTO `sys_role_resource` VALUES ('guest', 'jsglTreeGrid');
INSERT INTO `sys_role_resource` VALUES ('jsAdmin', 'jsglTreeGrid');
INSERT INTO `sys_role_resource` VALUES ('0', 'sjygl');
INSERT INTO `sys_role_resource` VALUES ('sjyAdmin', 'sjygl');
INSERT INTO `sys_role_resource` VALUES ('0', 'userCreateDatetimeChart');
INSERT INTO `sys_role_resource` VALUES ('0', 'wjgl');
INSERT INTO `sys_role_resource` VALUES ('0', 'wjglUpload');
INSERT INTO `sys_role_resource` VALUES ('0', 'wjglView');
INSERT INTO `sys_role_resource` VALUES ('0', 'xtgl');
INSERT INTO `sys_role_resource` VALUES ('guest', 'xtgl');
INSERT INTO `sys_role_resource` VALUES ('0', 'yhgl');
INSERT INTO `sys_role_resource` VALUES ('guest', 'yhgl');
INSERT INTO `sys_role_resource` VALUES ('yhAdmin', 'yhgl');
INSERT INTO `sys_role_resource` VALUES ('0', 'yhglAdd');
INSERT INTO `sys_role_resource` VALUES ('yhAdmin', 'yhglAdd');
INSERT INTO `sys_role_resource` VALUES ('0', 'yhglAddPage');
INSERT INTO `sys_role_resource` VALUES ('yhAdmin', 'yhglAddPage');
INSERT INTO `sys_role_resource` VALUES ('0', 'yhglBatchDelete');
INSERT INTO `sys_role_resource` VALUES ('yhAdmin', 'yhglBatchDelete');
INSERT INTO `sys_role_resource` VALUES ('0', 'yhglDateGrid');
INSERT INTO `sys_role_resource` VALUES ('guest', 'yhglDateGrid');
INSERT INTO `sys_role_resource` VALUES ('yhAdmin', 'yhglDateGrid');
INSERT INTO `sys_role_resource` VALUES ('0', 'yhglDelete');
INSERT INTO `sys_role_resource` VALUES ('yhAdmin', 'yhglDelete');
INSERT INTO `sys_role_resource` VALUES ('0', 'yhglEdit');
INSERT INTO `sys_role_resource` VALUES ('yhAdmin', 'yhglEdit');
INSERT INTO `sys_role_resource` VALUES ('0', 'yhglEditPage');
INSERT INTO `sys_role_resource` VALUES ('yhAdmin', 'yhglEditPage');
INSERT INTO `sys_role_resource` VALUES ('0', 'yhglEditPwd');
INSERT INTO `sys_role_resource` VALUES ('yhAdmin', 'yhglEditPwd');
INSERT INTO `sys_role_resource` VALUES ('0', 'yhglEditPwdPage');
INSERT INTO `sys_role_resource` VALUES ('yhAdmin', 'yhglEditPwdPage');
INSERT INTO `sys_role_resource` VALUES ('0', 'yhglGrant');
INSERT INTO `sys_role_resource` VALUES ('yhAdmin', 'yhglGrant');
INSERT INTO `sys_role_resource` VALUES ('0', 'yhglGrantPage');
INSERT INTO `sys_role_resource` VALUES ('yhAdmin', 'yhglGrantPage');
INSERT INTO `sys_role_resource` VALUES ('0', 'zygl');
INSERT INTO `sys_role_resource` VALUES ('guest', 'zygl');
INSERT INTO `sys_role_resource` VALUES ('zyAdmin', 'zygl');
INSERT INTO `sys_role_resource` VALUES ('0', 'zyglAdd');
INSERT INTO `sys_role_resource` VALUES ('zyAdmin', 'zyglAdd');
INSERT INTO `sys_role_resource` VALUES ('0', 'zyglAddPage');
INSERT INTO `sys_role_resource` VALUES ('zyAdmin', 'zyglAddPage');
INSERT INTO `sys_role_resource` VALUES ('0', 'zyglDelete');
INSERT INTO `sys_role_resource` VALUES ('zyAdmin', 'zyglDelete');
INSERT INTO `sys_role_resource` VALUES ('0', 'zyglEdit');
INSERT INTO `sys_role_resource` VALUES ('zyAdmin', 'zyglEdit');
INSERT INTO `sys_role_resource` VALUES ('0', 'zyglEditPage');
INSERT INTO `sys_role_resource` VALUES ('zyAdmin', 'zyglEditPage');
INSERT INTO `sys_role_resource` VALUES ('0', 'zyglMenu');
INSERT INTO `sys_role_resource` VALUES ('zyAdmin', 'zyglMenu');
INSERT INTO `sys_role_resource` VALUES ('0', 'zyglTreeGrid');
INSERT INTO `sys_role_resource` VALUES ('guest', 'zyglTreeGrid');
INSERT INTO `sys_role_resource` VALUES ('zyAdmin', 'zyglTreeGrid');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `NAME` varchar(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(100) NOT NULL,
  `CREATE_USER` varchar(36) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `CREATE_TM` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` varchar(36) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '修改人',
  `UPDATE_TM` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_doflky41g81kf7ydx6y0a99nm` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('0', '孙宇', 'e10adc3949ba59abbe56e057f20f883e', null, '2017-04-13 17:27:48', null, null);
INSERT INTO `sys_user` VALUES ('1', 'admin1', 'e10adc3949ba59abbe56e057f20f883e', null, '2017-04-13 17:27:48', null, null);
INSERT INTO `sys_user` VALUES ('2', 'admin2', 'e10adc3949ba59abbe56e057f20f883e', null, '2017-04-13 17:27:48', null, null);
INSERT INTO `sys_user` VALUES ('3', 'admin3', 'e10adc3949ba59abbe56e057f20f883e', null, '2017-04-13 17:27:48', null, null);
INSERT INTO `sys_user` VALUES ('4', 'admin4', 'e10adc3949ba59abbe56e057f20f883e', null, '2017-04-13 17:27:48', null, null);
INSERT INTO `sys_user` VALUES ('5', 'admin5', 'e10adc3949ba59abbe56e057f20f883e', null, '2017-04-13 17:27:48', null, null);
INSERT INTO `sys_user` VALUES ('guest', 'guest', 'e10adc3949ba59abbe56e057f20f883e', null, '2017-04-13 17:27:48', null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `SYS_USER_ID` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT 'sys_user表主键',
  `SYS_ROLE_ID` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT 'sys_role表主键',
  PRIMARY KEY (`SYS_ROLE_ID`,`SYS_USER_ID`),
  KEY `FK_30t0khk63muiwisjpp0h7e57l` (`SYS_ROLE_ID`),
  KEY `FK_mipcojqd9xymdghov18fobf7e` (`SYS_USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('0', '0');
INSERT INTO `sys_user_role` VALUES ('0', 'bugAdmin');
INSERT INTO `sys_user_role` VALUES ('5', 'bugAdmin');
INSERT INTO `sys_user_role` VALUES ('0', 'guest');
INSERT INTO `sys_user_role` VALUES ('guest', 'guest');
INSERT INTO `sys_user_role` VALUES ('0', 'jsAdmin');
INSERT INTO `sys_user_role` VALUES ('2', 'jsAdmin');
INSERT INTO `sys_user_role` VALUES ('0', 'sjyAdmin');
INSERT INTO `sys_user_role` VALUES ('4', 'sjyAdmin');
INSERT INTO `sys_user_role` VALUES ('0', 'yhAdmin');
INSERT INTO `sys_user_role` VALUES ('3', 'yhAdmin');
INSERT INTO `sys_user_role` VALUES ('0', 'zyAdmin');
INSERT INTO `sys_user_role` VALUES ('1', 'zyAdmin');
