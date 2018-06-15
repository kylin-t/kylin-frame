/*
Navicat MySQL Data Transfer

Source Server         : localMysql
Source Server Version : 50533
Source Host           : localhost:3306
Source Database       : kylin_frame

Target Server Type    : MYSQL
Target Server Version : 50533
File Encoding         : 65001

Date: 2018-06-15 18:03:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `dept_num` varchar(255) NOT NULL,
  `dept_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '大世界', null, '0', '001', null);
INSERT INTO `sys_dept` VALUES ('2', '1', '阿拉德大陆', null, '0', '002', 'SUB');
INSERT INTO `sys_dept` VALUES ('3', '1', '瓦诺兰大陆', null, '0', '003', null);
INSERT INTO `sys_dept` VALUES ('4', '2', '西海岸', null, '0', '001', null);
INSERT INTO `sys_dept` VALUES ('5', '2', '赫顿玛尔', null, '0', '阿拉德大陆', null);
INSERT INTO `sys_dept` VALUES ('6', '2', '天界', null, '0', '003', null);
INSERT INTO `sys_dept` VALUES ('7', '3', '均衡教派', null, '0', '', null);
INSERT INTO `sys_dept` VALUES ('8', '3', '恕瑞玛', null, '0', 'shuruima', 'OFFICE');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATA_KEY` varchar(255) NOT NULL,
  `DATA_VALUE` varchar(255) DEFAULT NULL,
  `TYPE_KEY` varchar(255) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES ('1', '1', '一级角色', 'ROLE-LEVEL', '2018-06-15 15:37:55', null, '1');
INSERT INTO `sys_dict_data` VALUES ('2', '2', '二级角色', 'ROLE-LEVEL', '2018-06-15 15:38:12', null, '1');
INSERT INTO `sys_dict_data` VALUES ('3', '3', '三级角色', 'ROLE-LEVEL', '2018-06-15 15:38:32', null, '1');
INSERT INTO `sys_dict_data` VALUES ('4', 'GROUP', '集团', 'DEPT-TYPE', '2018-06-15 15:39:45', null, '1');
INSERT INTO `sys_dict_data` VALUES ('5', 'SUB', '分公司', 'DEPT-TYPE', '2018-06-15 15:39:51', null, '1');
INSERT INTO `sys_dict_data` VALUES ('6', 'OFFICE', '办事处', 'DEPT-TYPE', '2018-06-15 15:39:54', null, '1');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_KEY` varchar(255) DEFAULT NULL,
  `TYPE_VALUE` varchar(255) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('1', 'ROLE-LEVEL', '角色级别', '角色级别', '2018-06-15 15:36:16', null, '1');
INSERT INTO `sys_dict_type` VALUES ('2', 'DEPT-TYPE', '部门类型', null, '2018-06-15 15:36:57', '2018-06-15 15:37:01', '1');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', 'admin', '删除部门', 'com.kylin.modules.system.controller.SysDeptController.delete()', '5', '12', '0:0:0:0:0:0:0:1', '2018-06-15 15:21:51');
INSERT INTO `sys_log` VALUES ('2', 'admin', '保存菜单', 'com.kylin.modules.system.controller.SysMenuController.save()', '{\"parentId\":1,\"menuName\":\"系统配置\",\"menuUrl\":\"\",\"permission\":\"\",\"menuType\":0,\"icon\":\"fa fa-wrench\",\"orderNum\":1}', '13', '0:0:0:0:0:0:0:1', '2018-06-15 15:29:04');
INSERT INTO `sys_log` VALUES ('3', 'admin', '保存菜单', 'com.kylin.modules.system.controller.SysMenuController.save()', '{\"parentId\":41,\"menuName\":\"数据字典\",\"menuUrl\":\"sys/dict/index.html\",\"permission\":\"\",\"menuType\":1,\"icon\":\"fa fa-book\",\"orderNum\":0}', '6', '0:0:0:0:0:0:0:1', '2018-06-15 15:30:25');
INSERT INTO `sys_log` VALUES ('4', 'admin', '保存菜单', 'com.kylin.modules.system.controller.SysMenuController.save()', '{\"parentId\":42,\"menuName\":\"查看\",\"menuUrl\":\"\",\"permission\":\"sys:value:list\",\"menuType\":2,\"icon\":\"\",\"orderNum\":0}', '6', '0:0:0:0:0:0:0:1', '2018-06-15 15:31:36');
INSERT INTO `sys_log` VALUES ('5', 'admin', '保存菜单', 'com.kylin.modules.system.controller.SysMenuController.save()', '{\"parentId\":1,\"menuName\":\"定时任务\",\"menuUrl\":\"\",\"permission\":\"fa fa-bell\",\"menuType\":0,\"icon\":\"\",\"orderNum\":2}', '17', '0:0:0:0:0:0:0:1', '2018-06-15 15:41:15');
INSERT INTO `sys_log` VALUES ('6', 'admin', '保存菜单', 'com.kylin.modules.system.controller.SysMenuController.save()', '{\"parentId\":44,\"menuName\":\"任务管理\",\"menuUrl\":\"schedule/job/index.html\",\"permission\":\"\",\"menuType\":1,\"icon\":\"fa fa-bell\",\"orderNum\":0}', '5', '0:0:0:0:0:0:0:1', '2018-06-15 15:42:03');
INSERT INTO `sys_log` VALUES ('7', 'admin', '保存菜单', 'com.kylin.modules.system.controller.SysMenuController.save()', '{\"parentId\":44,\"menuName\":\"任务日志\",\"menuUrl\":\"schedule/log/index.html\",\"permission\":\"sys:schedule:log\",\"menuType\":1,\"icon\":\"fa fa-paste\",\"orderNum\":1}', '7', '0:0:0:0:0:0:0:1', '2018-06-15 15:42:56');
INSERT INTO `sys_log` VALUES ('8', 'admin', '保存菜单', 'com.kylin.modules.system.controller.SysMenuController.save()', '{\"parentId\":1,\"menuName\":\"系统日志\",\"menuUrl\":\"\",\"permission\":\"\",\"menuType\":0,\"icon\":\"fa fa-file\",\"orderNum\":4}', '6', '0:0:0:0:0:0:0:1', '2018-06-15 15:44:22');
INSERT INTO `sys_log` VALUES ('9', 'admin', '保存菜单', 'com.kylin.modules.system.controller.SysMenuController.save()', '{\"parentId\":47,\"menuName\":\"操作日志\",\"menuUrl\":\"sys/log/index.html\",\"permission\":\"sys:log:list\",\"menuType\":1,\"icon\":\"fa fa-code\",\"orderNum\":0}', '7', '0:0:0:0:0:0:0:1', '2018-06-15 15:45:16');
INSERT INTO `sys_log` VALUES ('10', 'admin', '修改菜单', 'com.kylin.modules.system.controller.SysMenuController.update()', '{\"menuId\":44,\"parentId\":1,\"menuName\":\"定时任务\",\"menuUrl\":\"\",\"permission\":\"fa fa-bell\",\"menuType\":0,\"icon\":\"fa fa-bell\",\"orderNum\":2}', '13', '0:0:0:0:0:0:0:1', '2018-06-15 15:45:42');
INSERT INTO `sys_log` VALUES ('11', 'admin', '保存菜单', 'com.kylin.modules.system.controller.SysMenuController.save()', '{\"parentId\":45,\"menuName\":\"查看\",\"menuUrl\":\"\",\"permission\":\"sys:schedule:list\",\"menuType\":2,\"icon\":\"\",\"orderNum\":0}', '8', '0:0:0:0:0:0:0:1', '2018-06-15 15:46:45');
INSERT INTO `sys_log` VALUES ('12', 'admin', '保存菜单', 'com.kylin.modules.system.controller.SysMenuController.save()', '{\"parentId\":45,\"menuName\":\"新增\",\"menuUrl\":\"\",\"permission\":\"sys:schedule:save\",\"menuType\":2,\"icon\":\"\",\"orderNum\":1}', '15', '0:0:0:0:0:0:0:1', '2018-06-15 15:47:01');
INSERT INTO `sys_log` VALUES ('13', 'admin', '保存菜单', 'com.kylin.modules.system.controller.SysMenuController.save()', '{\"parentId\":45,\"menuName\":\"修改\",\"menuUrl\":\"\",\"permission\":\"sys:schedule:info,sys:schedule:update\",\"menuType\":2,\"icon\":\"\",\"orderNum\":2}', '6', '0:0:0:0:0:0:0:1', '2018-06-15 15:47:23');
INSERT INTO `sys_log` VALUES ('14', 'admin', '保存菜单', 'com.kylin.modules.system.controller.SysMenuController.save()', '{\"parentId\":45,\"menuName\":\"删除\",\"menuUrl\":\"\",\"permission\":\"sys:schedule:delete\",\"menuType\":2,\"icon\":\"\",\"orderNum\":3}', '2280', '0:0:0:0:0:0:0:1', '2018-06-15 15:47:38');
INSERT INTO `sys_log` VALUES ('15', 'admin', '保存菜单', 'com.kylin.modules.system.controller.SysMenuController.save()', '{\"parentId\":45,\"menuName\":\"删除\",\"menuUrl\":\"\",\"permission\":\"sys:schedule:delete\",\"menuType\":2,\"icon\":\"\",\"orderNum\":3}', '9', '0:0:0:0:0:0:0:1', '2018-06-15 15:47:38');
INSERT INTO `sys_log` VALUES ('16', 'admin', '保存菜单', 'com.kylin.modules.system.controller.SysMenuController.save()', '{\"parentId\":45,\"menuName\":\"恢复\",\"menuUrl\":\"\",\"permission\":\"sys:schedule:resume\",\"menuType\":2,\"icon\":\"\",\"orderNum\":5}', '6', '0:0:0:0:0:0:0:1', '2018-06-15 15:49:09');
INSERT INTO `sys_log` VALUES ('17', 'admin', '保存菜单', 'com.kylin.modules.system.controller.SysMenuController.save()', '{\"parentId\":45,\"menuName\":\"立即执行\",\"menuUrl\":\"\",\"permission\":\"sys:schedule:run\",\"menuType\":2,\"icon\":\"\",\"orderNum\":6}', '5', '0:0:0:0:0:0:0:1', '2018-06-15 15:49:30');
INSERT INTO `sys_log` VALUES ('18', 'admin', '修改菜单', 'com.kylin.modules.system.controller.SysMenuController.update()', '{\"menuId\":34,\"parentId\":31,\"menuName\":\"修改\",\"menuUrl\":\"\",\"permission\":\"sys:dept:info,sys:dept:update\",\"menuType\":2,\"icon\":\"\",\"orderNum\":2}', '7', '0:0:0:0:0:0:0:1', '2018-06-15 16:02:41');
INSERT INTO `sys_log` VALUES ('19', 'admin', '修改部门', 'com.kylin.modules.system.controller.SysDeptController.update()', '{\"deptId\":8,\"parentId\":3,\"deptName\":\"恕瑞玛\",\"parentName\":\"\",\"deptNum\":\"shuruima\",\"deptType\":\"OFFICE\"}', '11', '0:0:0:0:0:0:0:1', '2018-06-15 16:24:14');
INSERT INTO `sys_log` VALUES ('20', 'admin', '修改部门', 'com.kylin.modules.system.controller.SysDeptController.update()', '{\"deptId\":2,\"parentId\":1,\"deptName\":\"阿拉德大陆\",\"parentName\":\"大世界\",\"deptNum\":\"002\",\"deptType\":\"SUB\"}', '11', '0:0:0:0:0:0:0:1', '2018-06-15 16:33:10');
INSERT INTO `sys_log` VALUES ('21', 'admin', '修改菜单', 'com.kylin.modules.system.controller.SysMenuController.update()', '{\"menuId\":26,\"parentId\":2,\"menuName\":\"角色管理\",\"menuUrl\":\"sys/role/index.html\",\"permission\":\"sys:menu:select\",\"menuType\":1,\"icon\":\"fa fa-shield\",\"orderNum\":3}', '11', '0:0:0:0:0:0:0:1', '2018-06-15 17:29:53');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `permission` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `menu_type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'fa fa-cog', '0', null);
INSERT INTO `sys_menu` VALUES ('2', '1', '基础功能', 'main.html', null, '0', 'fa fa-archive', '1', null);
INSERT INTO `sys_menu` VALUES ('3', '2', '用户管理', 'sys/user/index.html', null, '1', 'fa fa-user', '2', null);
INSERT INTO `sys_menu` VALUES ('4', '3', '查看', '', 'sys:user:list', '2', '', '0', null);
INSERT INTO `sys_menu` VALUES ('23', '3', '新增', null, 'sys:menu:save', '2', null, '1', null);
INSERT INTO `sys_menu` VALUES ('24', '3', '修改', null, 'sys:user:info,sys:user:update', '2', null, '2', null);
INSERT INTO `sys_menu` VALUES ('25', '3', '删除', null, 'sys:user:delete', '2', null, '3', null);
INSERT INTO `sys_menu` VALUES ('26', '2', '角色管理', 'sys/role/index.html', 'sys:menu:select', '1', 'fa fa-shield', '3', null);
INSERT INTO `sys_menu` VALUES ('27', '26', '查看', '', 'sys:role:list', '2', '', '0', null);
INSERT INTO `sys_menu` VALUES ('28', '26', '新增', '', 'sys:role:save', '2', '', '1', null);
INSERT INTO `sys_menu` VALUES ('29', '26', '修改', '', 'sys:role:info,sys:role:update', '2', '', '2', null);
INSERT INTO `sys_menu` VALUES ('30', '26', '删除', null, 'sys:role:delete', '2', null, '3', null);
INSERT INTO `sys_menu` VALUES ('31', '2', '部门管理', 'sys/org/index.html', null, '1', 'fa fa-building', '4', null);
INSERT INTO `sys_menu` VALUES ('32', '31', '查看', null, 'sys:dept:list', '2', null, '0', null);
INSERT INTO `sys_menu` VALUES ('33', '31', '新增', null, 'sys:dept:save', '2', null, '1', null);
INSERT INTO `sys_menu` VALUES ('34', '31', '修改', '', 'sys:dept:info,sys:dept:update', '2', '', '2', null);
INSERT INTO `sys_menu` VALUES ('35', '31', '删除', null, 'sys:dept:delete', '2', null, '3', null);
INSERT INTO `sys_menu` VALUES ('36', '2', '菜单管理', 'sys/menu/index.html', null, '1', 'fa fa-align-justify', '5', null);
INSERT INTO `sys_menu` VALUES ('37', '36', '查看', null, 'sys:menu:list', '2', null, '0', null);
INSERT INTO `sys_menu` VALUES ('38', '36', '新增', null, 'sys:menu:save', '2', null, '1', null);
INSERT INTO `sys_menu` VALUES ('39', '36', '修改', null, 'sys:menu:info,sys:menu:update', '2', null, '2', null);
INSERT INTO `sys_menu` VALUES ('40', '36', '删除', null, 'sys:menu:delete', '2', null, '3', null);
INSERT INTO `sys_menu` VALUES ('41', '1', '系统配置', '', '', '0', 'fa fa-wrench', '1', null);
INSERT INTO `sys_menu` VALUES ('42', '41', '数据字典', 'sys/dict/index.html', '', '1', 'fa fa-book', '0', null);
INSERT INTO `sys_menu` VALUES ('43', '42', '查看', '', 'sys:value:list', '2', '', '0', null);
INSERT INTO `sys_menu` VALUES ('44', '1', '定时任务', '', 'fa fa-bell', '0', 'fa fa-bell', '2', null);
INSERT INTO `sys_menu` VALUES ('45', '44', '任务管理', 'schedule/job/index.html', '', '1', 'fa fa-bell', '0', null);
INSERT INTO `sys_menu` VALUES ('46', '44', '任务日志', 'schedule/log/index.html', 'sys:schedule:log', '1', 'fa fa-paste', '1', null);
INSERT INTO `sys_menu` VALUES ('47', '1', '系统日志', '', '', '0', 'fa fa-file', '4', null);
INSERT INTO `sys_menu` VALUES ('48', '47', '操作日志', 'sys/log/index.html', 'sys:log:list', '1', 'fa fa-code', '0', null);
INSERT INTO `sys_menu` VALUES ('49', '45', '查看', '', 'sys:schedule:list', '2', '', '0', null);
INSERT INTO `sys_menu` VALUES ('50', '45', '新增', '', 'sys:schedule:save', '2', '', '1', null);
INSERT INTO `sys_menu` VALUES ('51', '45', '修改', '', 'sys:schedule:info,sys:schedule:update', '2', '', '2', null);
INSERT INTO `sys_menu` VALUES ('52', '45', '删除', '', 'sys:schedule:delete', '2', '', '3', null);
INSERT INTO `sys_menu` VALUES ('53', '45', '暂停', '', 'sys:schedule:pause', '2', '', '4', null);
INSERT INTO `sys_menu` VALUES ('54', '45', '恢复', '', 'sys:schedule:resume', '2', '', '5', null);
INSERT INTO `sys_menu` VALUES ('55', '45', '立即执行', '', 'sys:schedule:run', '2', '', '6', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(100) DEFAULT NULL COMMENT '角色代码',
  `status` tinyint(4) DEFAULT NULL COMMENT '角色状态 0：禁用  1：正常',
  `role_level` varchar(100) DEFAULT NULL COMMENT '角色级别',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('14', '战士', 'R001', '1', '001', '呵');
INSERT INTO `sys_role` VALUES ('15', '法师', 'R002', '1', '003', '');
INSERT INTO `sys_role` VALUES ('16', '刺客', 'R003', '1', '002', '');
INSERT INTO `sys_role` VALUES ('17', 'ADC', 'R004', '1', '004', '');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'kylin!@aliyun.com', '18802945808', '1', '1', '2018-05-11 11:11:11');
INSERT INTO `sys_user` VALUES ('11', 'kylin', '7915c8b9d3221f434f907eacb5d6e6eb7a11a6fd152d1b6f1db6040d3fdc81cf', 'gRzpJKy1a8gRKkI1GDMB', 'kylin_@aliyun.com', '18802945808', '1', '1', '2018-05-31 17:37:12');
INSERT INTO `sys_user` VALUES ('14', 'www', '08daefb3427020dd59b739ffcea06514976f43585f3bab3761a4b937cf1d410e', '0rSliSfnNXpbwN7HV3Qn', '2', '1', '1', '1', '2018-05-31 18:03:34');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('11', '11', '16');
INSERT INTO `sys_user_role` VALUES ('15', '14', '14');
INSERT INTO `sys_user_role` VALUES ('16', '14', '15');
INSERT INTO `sys_user_role` VALUES ('17', '14', '16');
INSERT INTO `sys_user_role` VALUES ('18', '14', '17');

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES ('1', 'ed62c1c3badb9e32069733db47c3478a', '2018-06-16 05:21:30', '2018-06-15 17:21:30');
