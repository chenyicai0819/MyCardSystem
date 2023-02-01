/*
 Navicat Premium Data Transfer

 Source Server         : 8.129.212.155
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : 8.129.212.155:3306
 Source Schema         : mycard

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 01/02/2023 17:18:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_BLOB_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_CALENDARS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIRED_TIME` bigint NOT NULL,
  `SCHED_TIME` bigint NOT NULL,
  `PRIORITY` int NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint NOT NULL,
  `CHECKIN_INTERVAL` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint NOT NULL,
  `REPEAT_INTERVAL` bigint NOT NULL,
  `TIMES_TRIGGERED` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_SIMPLE_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int NULL DEFAULT NULL,
  `INT_PROP_2` int NULL DEFAULT NULL,
  `LONG_PROP_1` bigint NULL DEFAULT NULL,
  `LONG_PROP_2` bigint NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_SIMPROP_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint NULL DEFAULT NULL,
  `PRIORITY` int NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint NOT NULL,
  `END_TIME` bigint NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME` ASC, `JOB_NAME` ASC, `JOB_GROUP` ASC) USING BTREE,
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for ad
-- ----------------------------
DROP TABLE IF EXISTS `ad`;
CREATE TABLE `ad`  (
  `adId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告id主键',
  `adName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告名',
  `adText` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告主题内容',
  `adImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告图片url',
  `adUpDate` datetime NULL DEFAULT NULL COMMENT '广告上架时间',
  `adDownDate` datetime NULL DEFAULT NULL COMMENT '广告下架时间',
  PRIMARY KEY (`adId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ad
-- ----------------------------
INSERT INTO `ad` VALUES ('a1', '测试', '测试简介', 'https://z3.ax1x.com/2021/11/03/IVGUJg.png', '2022-12-01 08:00:00', '2022-12-03 08:00:00');
INSERT INTO `ad` VALUES ('a2', '测试2', '测试简介2', 'https://z3.ax1x.com/2021/11/03/IVGUJg.png', '2021-11-10 16:00:00', '2021-12-12 16:00:00');
INSERT INTO `ad` VALUES ('a3', '广告11', '测试广告内容', 'https://z3.ax1x.com/2021/11/03/IVGUJg.png', '2022-02-06 16:00:00', '2022-02-13 16:00:00');
INSERT INTO `ad` VALUES ('a4', '我叫刘世鹏', '我叫刘世鹏，我是桂电第一帅哥', 'https://cdn.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20211206122142.png', '2022-05-22 16:00:00', '2022-05-23 16:00:00');
INSERT INTO `ad` VALUES ('a5', '我叫刘世鹏', '我叫刘世鹏，我是桂电第一帅哥', 'https://fastly.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20211206122142.png', '2022-06-16 00:00:00', '2022-06-23 00:00:00');
INSERT INTO `ad` VALUES ('a6', '平板', '平板测评', '', '2022-12-09 08:00:00', '2022-12-16 08:00:00');
INSERT INTO `ad` VALUES ('a7', '小米妙想中心，上新了！', '详情请关注 MIUI 公众号', 'https://s1.ax1x.com/2023/01/20/pS87URx.jpg', '2023-01-20 00:00:00', '2023-02-01 00:00:00');

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card`  (
  `cardId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'card主键id',
  `cardName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'card名称',
  `cardLink` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'card指向地址',
  `cardImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'card图标url',
  PRIMARY KEY (`cardId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES ('c1', '我的个人Blog', 'https://cyc0819.top', 'https://z3.ax1x.com/2021/09/30/4oKMVI.jpg');
INSERT INTO `card` VALUES ('c2', '书签集', '/#/home/bookmarks', NULL);
INSERT INTO `card` VALUES ('c3', '网站管理', '/#/login', NULL);
INSERT INTO `card` VALUES ('c4', '下载中心', '/#/', 'http://www.cyc292.top/download');
INSERT INTO `card` VALUES ('c5', '学生宿舍管理系统', 'http://student.cyc292.top', '');
INSERT INTO `card` VALUES ('c6', '骏泰理发店管理系统', 'http://jt.cyc292.top', '');

-- ----------------------------
-- Table structure for coll
-- ----------------------------
DROP TABLE IF EXISTS `coll`;
CREATE TABLE `coll`  (
  `collId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'coll分类主键id',
  `collName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名',
  `collImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类图标url地址',
  `collText` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类简介',
  PRIMARY KEY (`collId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coll
-- ----------------------------
INSERT INTO `coll` VALUES ('o1', '在线学习网站', '', '');
INSERT INTO `coll` VALUES ('o10', '地方政务系统', NULL, NULL);
INSERT INTO `coll` VALUES ('o11', '快递查询', NULL, NULL);
INSERT INTO `coll` VALUES ('o12', '游戏娱乐', NULL, NULL);
INSERT INTO `coll` VALUES ('o13', '百科资料', NULL, NULL);
INSERT INTO `coll` VALUES ('o2', '参考文档/API', NULL, NULL);
INSERT INTO `coll` VALUES ('o3', '编程学习', NULL, NULL);
INSERT INTO `coll` VALUES ('o4', '前端工具', NULL, NULL);
INSERT INTO `coll` VALUES ('o5', '博客论坛', NULL, NULL);
INSERT INTO `coll` VALUES ('o6', '邮件通讯', NULL, NULL);
INSERT INTO `coll` VALUES ('o7', '名企官网', NULL, NULL);
INSERT INTO `coll` VALUES ('o8', '名校官网', NULL, NULL);
INSERT INTO `coll` VALUES ('o9', '学术论文', NULL, NULL);

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `classMethod` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `args` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of logs
-- ----------------------------
INSERT INTO `logs` VALUES (1, '新增', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.CardController.addCard', '[c8, 测试, /#/, http://www.cyc292.top/download]', '/card/add', '2022-12-15 21:49:40');
INSERT INTO `logs` VALUES (2, '新增', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.CardController.addCard', '[c8, 测试, /#/, http://www.cyc292.top/download]', '/card/add', '2022-12-16 21:49:46');
INSERT INTO `logs` VALUES (3, '删除', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.CardController.delCard', '[c7]', '/card/del', '2022-12-16 21:49:50');
INSERT INTO `logs` VALUES (4, '删除', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.CardController.delCard', '[c8]', '/card/del', '2022-12-16 21:49:53');
INSERT INTO `logs` VALUES (5, '登录', '2', '103.181.164.91', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@58dbbfdc]', '/user/login', '2022-12-16 21:49:57');
INSERT INTO `logs` VALUES (6, '登录', '1', '103.181.164.91', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@1754c0e]', '/user/login', '2022-12-16 21:50:02');
INSERT INTO `logs` VALUES (7, '修改', '1', '103.181.164.91', 'com.chen.mycardsystembackstage.controller.CardController.upCard', '[c4, 下载中心, /#/1, http://www.cyc292.top/download]', '/card/up', '2022-12-16 21:50:05');
INSERT INTO `logs` VALUES (8, '修改', '1', '103.181.164.91', 'com.chen.mycardsystembackstage.controller.CardController.upCard', '[c4, 下载中心, /#/, http://www.cyc292.top/download]', '/card/up', '2022-12-16 21:50:08');
INSERT INTO `logs` VALUES (9, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@620bcd9f]', '/user/login', '2022-12-16 21:50:12');
INSERT INTO `logs` VALUES (10, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@5850e1a6]', '/user/login', '2022-12-16 21:50:16');
INSERT INTO `logs` VALUES (11, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@5092b033]', '/user/login', '2022-12-17 21:50:20');
INSERT INTO `logs` VALUES (12, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@77c96191]', '/user/login', '2022-12-17 21:50:24');
INSERT INTO `logs` VALUES (13, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@66c3b3fc]', '/user/login', '2022-12-17 21:50:28');
INSERT INTO `logs` VALUES (14, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@629a1bd]', '/user/login', '2022-12-17 21:50:32');
INSERT INTO `logs` VALUES (15, '登录', '2', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@59cbb5f8]', '/user/login', '2022-12-17 21:50:35');
INSERT INTO `logs` VALUES (16, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@5beb8199]', '/user/login', '2022-12-18 21:50:40');
INSERT INTO `logs` VALUES (17, '登录', '1', '223.198.60.252', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@7eafeada]', '/user/login', '2022-12-18 21:50:43');
INSERT INTO `logs` VALUES (18, '登录', '1', '140.240.17.131', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@539e7158]', '/user/login', '2022-12-18 21:50:48');
INSERT INTO `logs` VALUES (19, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@79cda2e0]', '/user/login', '2022-12-18 21:50:50');
INSERT INTO `logs` VALUES (20, '测试', '3', '127.0.0.1', 'com.chen.www', '[test]', '/test', '2022-12-18 22:00:29');
INSERT INTO `logs` VALUES (21, '测试', '3', '127.0.0.1', 'com.chen.www', '[test]', '/test', '2022-12-18 22:39:20');
INSERT INTO `logs` VALUES (22, '测试', '3', '127.0.0.1', 'com.chen.www', '[test]', '/test', '2022-12-18 22:42:18');
INSERT INTO `logs` VALUES (23, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@39247e9]', '/user/login', '2022-12-18 22:41:28');
INSERT INTO `logs` VALUES (24, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@722dac59]', '/user/login', '2022-12-22 23:42:20');
INSERT INTO `logs` VALUES (25, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@11611f38]', '/user/login', '2022-12-23 21:54:09');
INSERT INTO `logs` VALUES (26, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@447b8382]', '/user/login', '2022-12-24 10:26:44');
INSERT INTO `logs` VALUES (27, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@3be07219]', '/user/login', '2022-12-24 22:45:15');
INSERT INTO `logs` VALUES (28, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@5b98b288]', '/user/login', '2022-12-24 23:00:30');
INSERT INTO `logs` VALUES (29, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@2ec352ec]', '/user/login', '2022-12-24 23:45:33');
INSERT INTO `logs` VALUES (30, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@5a356337]', '/user/login', '2022-12-25 00:49:18');
INSERT INTO `logs` VALUES (31, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@295bd2ee]', '/user/login', '2022-12-25 00:54:43');
INSERT INTO `logs` VALUES (32, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@257ffd6a]', '/user/login', '2022-12-25 01:05:07');
INSERT INTO `logs` VALUES (33, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@732dabd9]', '/user/login', '2022-12-25 10:40:18');
INSERT INTO `logs` VALUES (34, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@741561f7]', '/user/login', '2022-12-25 22:52:51');
INSERT INTO `logs` VALUES (35, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@209d8b80]', '/user/login', '2022-12-29 21:01:50');
INSERT INTO `logs` VALUES (36, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[com.chen.mycardsystembackstage.entity.User@267f7654]', '/user/login', '2022-12-31 11:47:02');
INSERT INTO `logs` VALUES (37, '新增', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.MorkController.addMork', '[4, 中文stackoverflow, https://stackoom.com/, null, , 中文版的stackoverflow，翻译了原网站的内容]', '/mork/add', '2022-12-31 11:59:42');
INSERT INTO `logs` VALUES (38, '修改', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.MorkController.upMork', '[76, 中文stackoverflow, https://stackoom.com/, 博客论坛, , 中文版的stackoverflow，翻译了原网站的内容]', '/mork/up', '2022-12-31 12:00:16');
INSERT INTO `logs` VALUES (39, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null), 9291]', '/user/login', '2023-01-18 16:43:34');
INSERT INTO `logs` VALUES (40, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null), 9292]', '/user/login', '2023-01-18 16:43:47');
INSERT INTO `logs` VALUES (41, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=11, openid=null, email=null), 9292]', '/user/login', '2023-01-18 16:43:58');
INSERT INTO `logs` VALUES (42, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null), 9291]', '/user/login', '2023-01-18 16:44:07');
INSERT INTO `logs` VALUES (43, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null), 7210]', '/user/login', '2023-01-18 23:13:32');
INSERT INTO `logs` VALUES (44, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null), 7219]', '/user/login', '2023-01-18 23:13:38');
INSERT INTO `logs` VALUES (45, '登录', '1', '119.41.229.178', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null), 2712]', '/user/login', '2023-01-19 16:00:57');
INSERT INTO `logs` VALUES (46, '登录', '1', '119.41.229.178', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null), 2717]', '/user/login', '2023-01-19 11:50:29');
INSERT INTO `logs` VALUES (47, '登录', '4', '119.41.229.178', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=4, userPass=4, openid=null, email=null), 2576]', '/user/login', '2023-01-19 16:26:22');
INSERT INTO `logs` VALUES (48, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), 8716]', '/user/login', '2023-01-19 23:48:47');
INSERT INTO `logs` VALUES (49, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), 8716]', '/user/login', '2023-01-19 23:49:00');
INSERT INTO `logs` VALUES (50, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), 8716]', '/user/login', '2023-01-19 23:49:20');
INSERT INTO `logs` VALUES (51, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), 8716]', '/user/login', '2023-01-19 23:49:26');
INSERT INTO `logs` VALUES (52, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-19 23:49:37');
INSERT INTO `logs` VALUES (53, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), 8716]', '/user/login', '2023-01-19 23:50:23');
INSERT INTO `logs` VALUES (54, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 00:27:23');
INSERT INTO `logs` VALUES (55, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 00:28:08');
INSERT INTO `logs` VALUES (56, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 00:30:04');
INSERT INTO `logs` VALUES (57, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 00:30:58');
INSERT INTO `logs` VALUES (58, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 00:31:42');
INSERT INTO `logs` VALUES (59, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 00:32:31');
INSERT INTO `logs` VALUES (60, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 00:33:06');
INSERT INTO `logs` VALUES (61, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 00:34:17');
INSERT INTO `logs` VALUES (62, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 00:37:57');
INSERT INTO `logs` VALUES (63, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 00:43:10');
INSERT INTO `logs` VALUES (64, '登录', '2', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=2, userPass=2, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 00:44:05');
INSERT INTO `logs` VALUES (65, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 00:45:35');
INSERT INTO `logs` VALUES (66, '登录', '2', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=2, userPass=2, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 00:45:46');
INSERT INTO `logs` VALUES (67, '登录', '3', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=3, userPass=3, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 00:46:10');
INSERT INTO `logs` VALUES (68, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 09:01:38');
INSERT INTO `logs` VALUES (69, '登录', '2', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=2, userPass=2, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 09:02:07');
INSERT INTO `logs` VALUES (70, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 09:03:49');
INSERT INTO `logs` VALUES (71, '登录', '3', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=3, userPass=3, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 09:04:00');
INSERT INTO `logs` VALUES (72, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 09:06:17');
INSERT INTO `logs` VALUES (73, '登录', '3', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=3, userPass=3, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 09:06:43');
INSERT INTO `logs` VALUES (74, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 09:15:28');
INSERT INTO `logs` VALUES (75, '登录', '2', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=2, userPass=2, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 09:15:48');
INSERT INTO `logs` VALUES (76, '登录', '2', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=2, userPass=2, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 09:16:54');
INSERT INTO `logs` VALUES (77, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 09:45:37');
INSERT INTO `logs` VALUES (78, '登录', '3', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=3, userPass=3, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 09:46:03');
INSERT INTO `logs` VALUES (79, '登录', '2', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=2, userPass=2, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 09:48:56');
INSERT INTO `logs` VALUES (80, '登录', '2', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=2, userPass=2, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 09:54:57');
INSERT INTO `logs` VALUES (81, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 09:55:10');
INSERT INTO `logs` VALUES (82, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 10:18:23');
INSERT INTO `logs` VALUES (83, '登录', '2', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=2, userPass=2, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 10:20:25');
INSERT INTO `logs` VALUES (84, '登录', '2', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 10:52:27');
INSERT INTO `logs` VALUES (85, '登录', '2', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 10:52:31');
INSERT INTO `logs` VALUES (86, '登录', '2', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 10:52:33');
INSERT INTO `logs` VALUES (87, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 10:52:51');
INSERT INTO `logs` VALUES (88, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 10:54:26');
INSERT INTO `logs` VALUES (89, '新增', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.AdController.addAd', '[, 小米妙想中心，上新了！, 详情请关注：https://mp.weixin.qq.com/s/fMg_bRiNzQoe4kdyUX6YiA, https://imgse.com/i/pS87URx, 2023-01-20 00:00:00, 2023-02-01 00:00:00]', '/ad/add', '2023-01-20 10:55:38');
INSERT INTO `logs` VALUES (90, '登录', '2', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=2, userPass=2, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-20 10:56:18');
INSERT INTO `logs` VALUES (91, '修改', '2', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.AdController.upAd', '[a7, 小米妙想中心，上新了！, 详情请关注：https://mp.weixin.qq.com/s/fMg_bRiNzQoe4kdyUX6YiA, https://s1.ax1x.com/2023/01/20/pS87URx.jpg, null, null]', '/ad/up', '2023-01-20 10:56:50');
INSERT INTO `logs` VALUES (92, '修改', '2', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.AdController.upAd', '[a7, 小米妙想中心，上新了！, 详情请关注：https://mp.weixin.qq.com/s/fMg_bRiNzQoe4kdyUX6YiA, https://s1.ax1x.com/2023/01/20/pS87URx.jpg, 2023-01-20 00:00:00, 2023-02-01 00:00:00]', '/ad/up', '2023-01-20 10:56:56');
INSERT INTO `logs` VALUES (93, '修改', '2', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.AdController.upAd', '[a7, 小米妙想中心，上新了！, 详情请关注 MIUI 公众号, https://s1.ax1x.com/2023/01/20/pS87URx.jpg, 2023-01-20 00:00:00, 2023-02-01 00:00:00]', '/ad/up', '2023-01-20 10:58:04');
INSERT INTO `logs` VALUES (94, '登录', '3', '140.240.25.163', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=3, userPass=3, openid=null, email=null, userName=null), 6705]', '/user/login', '2023-01-20 11:28:02');
INSERT INTO `logs` VALUES (95, '登录', NULL, '121.58.21.75', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), ]', '/user/login', '2023-01-31 15:39:22');
INSERT INTO `logs` VALUES (96, '登录', '1', '121.58.21.75', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-31 15:39:30');
INSERT INTO `logs` VALUES (97, '登录', '3', '140.240.19.30', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=3, userPass=3, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-31 21:24:04');
INSERT INTO `logs` VALUES (98, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-31 21:48:31');
INSERT INTO `logs` VALUES (99, '登录', '2', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=2, userPass=2, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-31 21:51:34');
INSERT INTO `logs` VALUES (100, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-31 21:57:00');
INSERT INTO `logs` VALUES (101, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-31 21:58:07');
INSERT INTO `logs` VALUES (102, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-31 22:00:54');
INSERT INTO `logs` VALUES (103, '登录', '1', '140.240.19.30', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-31 22:05:17');
INSERT INTO `logs` VALUES (104, '登录', '3', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=3, userPass=3, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-31 22:06:32');
INSERT INTO `logs` VALUES (105, '登录', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.UserController.login', '[User(userId=1, userPass=1, openid=null, email=null, userName=null), wechat]', '/user/login', '2023-01-31 23:04:06');
INSERT INTO `logs` VALUES (106, '修改', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.FileController.uploadFile', '[org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@50bba0cc]', '/file/uploadfile', '2023-02-01 11:10:57');
INSERT INTO `logs` VALUES (107, '修改', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.FileController.uploadFile', '[org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@2a710c16]', '/file/uploadfile', '2023-02-01 11:18:30');
INSERT INTO `logs` VALUES (108, '修改', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.FileController.uploadFile', '[org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@13d41c9f]', '/file/uploadfile', '2023-02-01 11:36:43');
INSERT INTO `logs` VALUES (109, '修改', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.FileController.uploadFile', '[org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@73b045bf]', '/file/uploadfile', '2023-02-01 11:39:08');
INSERT INTO `logs` VALUES (110, '修改', '1', '0:0:0:0:0:0:0:1', 'com.chen.mycardsystembackstage.controller.FileController.uploadFile', '[org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@50096479]', '/file/uploadfile', '2023-02-01 11:46:23');

-- ----------------------------
-- Table structure for manages
-- ----------------------------
DROP TABLE IF EXISTS `manages`;
CREATE TABLE `manages`  (
  `manaId` int NOT NULL,
  `manaName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `manaLink` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`manaId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manages
-- ----------------------------
INSERT INTO `manages` VALUES (1, 'Home页面内容管理', NULL);
INSERT INTO `manages` VALUES (2, '书签内容管理', NULL);
INSERT INTO `manages` VALUES (3, '书签分类管理', NULL);
INSERT INTO `manages` VALUES (4, '广告管理', NULL);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `keyid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` int NOT NULL COMMENT '1：广告，2：网站',
  `isRead` int NOT NULL COMMENT '0:未读，1：已读',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `showdate` datetime NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `checkdate` datetime NULL DEFAULT NULL COMMENT '处理消息时间',
  `isPass` int NULL DEFAULT 0 COMMENT '是否通过，0：未处理，1：通过，2：不通过',
  PRIMARY KEY (`id`, `keyid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, 'test', 1, 1, '测试', '测试', '测试', '2022-12-24 23:41:54', '测试', '测试', '测试', '测试', '2022-12-25 22:38:37', 2);
INSERT INTO `message` VALUES (2, 'CHEN8199665df0674c00ba233049126a1cd120221225010354', 1, 1, '耐克', '耐克广告投资', '1000', '2022-12-25 01:03:54', '15500932012', '2495842975@qq.com', NULL, NULL, '2022-12-25 22:37:50', 2);
INSERT INTO `message` VALUES (3, 'CHENf5bc9909359b441a8f4d885d0f157de220221225010449', 1, 1, '阿迪达斯', '阿迪达斯广告投资', '1000', '2022-12-25 01:04:50', '15500932012', '2495842975@qq.com', NULL, NULL, '2022-12-25 22:33:10', 2);
INSERT INTO `message` VALUES (4, 'CHEN94482d4135b645ca8174b8d76336e7c520221225225144', 2, 1, '中文stackoverflow', '中文版的stackoverflow，翻译了原网站的内容', NULL, NULL, NULL, '2495842975@qq.com', 'https://stackoom.com/', '陈益财', '2022-12-31 11:59:41', 1);
INSERT INTO `message` VALUES (5, 'CHEN91e6bebf952f45d28e02a3026d17738f20230120105405', 1, 1, '小米妙想中心，上新了', '详情请关注：https://mp.weixin.qq.com/s/fMg_bRiNzQoe4kdyUX6YiA', '100/天', '2023-01-20 10:54:06', '15500932013', '2495842975@qq.com', NULL, NULL, '2023-01-20 10:54:47', 1);

-- ----------------------------
-- Table structure for mork
-- ----------------------------
DROP TABLE IF EXISTS `mork`;
CREATE TABLE `mork`  (
  `morkId` int NOT NULL AUTO_INCREMENT COMMENT 'mork书签主键id',
  `morkName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书签名称',
  `morkLink` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书签链接',
  `morkType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书签类型',
  `morkImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书签图片地址url',
  `morkText` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书签介绍',
  PRIMARY KEY (`morkId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mork
-- ----------------------------
INSERT INTO `mork` VALUES (1, 'bilibili', 'https://www.bilibili.com/', '在线学习网站', 'https://fastly.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20220511171926.png', 'bilibili是国内知名的视频弹幕网站,这里有及时的动漫新番,活跃的ACG氛围,有创意的Up主。大家可以在这里找到许多欢乐。');
INSERT INTO `mork` VALUES (2, 'Vue官方文档', 'https://v3.cn.vuejs.org/', '参考文档/API', 'https://fastly.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20220511173215.png', 'Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。');
INSERT INTO `mork` VALUES (3, '中国大学mooc', 'https://www.icourse163.org/', '在线学习网站', 'https://z3.ax1x.com/2021/10/21/5ygfC8.png', '中国大学MOOC(慕课) 是国内优质的中文MOOC学习平台,由爱课程网携手网易云课堂打造。');
INSERT INTO `mork` VALUES (4, '超星学习通', 'http://i.chaoxing.com/', '在线学习网站', 'https://fastly.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20220511172232.png', '超星学习通是面向智能手机、平板电脑等移动终端的移动学习专业平台。');
INSERT INTO `mork` VALUES (5, '蓝桥云课', 'https://www.lanqiao.cn/', '在线学习网站', 'https://z3.ax1x.com/2021/10/21/5y2Qat.png', '蓝桥云课是国内领先的IT在线编程及在线实训学习平台,专业导师提供精选的实践项目,创新的技术使得学习者无需配置繁琐的本地环境,随时在线流畅使用。');
INSERT INTO `mork` VALUES (6, '网易公开课', 'https://open.163.com/', '在线学习网站', 'https://fastly.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20220511172814.png', '网易公开课汇集清华、北大、哈佛、耶鲁等世界名校共上千门课程,覆盖科学、经济、人文、哲学等22个领域,在这里你可以开拓视野看世界,获取有深度的好知识。');
INSERT INTO `mork` VALUES (7, 'React官方文档', 'https://react.docschina.org/', '参考文档/API', 'https://fastly.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20220511172933.png', 'React是用于构建用户界面的JavaScript库，起源于Facebook的内部项目，该公司对市场上所有 JavaScript MVC框架都不满意，决定自行开发一套，用于架设Instagram的网站。');
INSERT INTO `mork` VALUES (8, 'Taro官方文档', 'https://taro-docs.jd.com/taro/docs/README/index.html', '参考文档/API', 'https://z3.ax1x.com/2021/10/21/5yR3wR.png', 'Taro是一个开放式跨端跨框架解决方案，支持使用React、Vue.js、Nerv等框架来开发小程序、H5、react Native等应用。');
INSERT INTO `mork` VALUES (9, 'MyBatis官方API', 'https://mybatis.org/mybatis-3/zh/index.html', '参考文档/API', 'https://z3.ax1x.com/2021/10/21/5yRcff.png', 'MyBatis 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。');
INSERT INTO `mork` VALUES (10, 'MyBatis-Plus官方文档', 'https://mp.baomidou.com/', '参考文档/API', 'https://z3.ax1x.com/2021/10/21/5yR7t0.png', 'MyBatis的升级版，是一个开源框架');
INSERT INTO `mork` VALUES (11, 'Spring官网', 'https://spring.io/projects/spring-boot', '参考文档/API', 'https://spring.io/images/spring-initializr-4291cc0115eb104348717b82161a81de.svg', 'Spring框架是由于软件开发的复杂性而创建的。Spring使用的是基本的JavaBean来完成以前只可能由EJB完成的事情。然而，Spring的用途不仅仅限于服务器端的开发。从简单性、可测试性和松耦合性角度而言，绝大部分Java应用都可以从Spring中受益。');
INSERT INTO `mork` VALUES (12, 'Spring-Cloud中文网', 'https://www.springcloud.cc/', '参考文档/API', 'https://z3.ax1x.com/2021/10/21/5yWsu4.png', '\r\nSpring Cloud是一系列框架的有序集合。它利用Spring Boot的开发便利性巧妙地简化了分布式系统基础设施的开发，如服务发现注册、配置中心、消息总线、负载均衡、断路器、数据监控等，都可以用Spring Boot的开发风格做到一键启动和部署。');
INSERT INTO `mork` VALUES (13, '桂林电子科技大学', 'http://www.gliet.edu.cn/', '名校官网', 'https://www.guet.edu.cn/images/bottomLogon3.png', '桂林电子科技大学（Guilin University Of Electronic Technology），简称“桂电”，坐落于世界著名山水旅游名城--桂林市，由工业和信息化部、国家国防科技工业局与广西壮族自治区人民政府共建。');
INSERT INTO `mork` VALUES (14, '清华大学', 'https://www.tsinghua.edu.cn/', '名校官网', 'https://z3.ax1x.com/2021/10/22/5yftMD.png', '清华大学（Tsinghua University），简称“清华”，位于北京市海淀区，是中华人民共和国教育部直属的全国重点大学，位列国家“双一流”、 [89]  “985工程”、“211工程”');
INSERT INTO `mork` VALUES (15, '北京大学', 'https://www.pku.edu.cn/', '名校官网', 'https://z3.ax1x.com/2021/10/22/5yfdZd.png', '北京大学（Peking University），简称“北大”，是中华人民共和国教育部直属的全国重点大学，位列“双一流”、“211工程”、“985工程”');
INSERT INTO `mork` VALUES (16, '华南农业大学', 'https://www.scau.edu.cn/', '名校官网', 'https://z3.ax1x.com/2021/10/22/5yfcQS.jpg', '华南农业大学（South China Agricultural University），简称“华农”，位于广东省广州市，全国重点大学、国家“双一流”建设高校，是一所以农业科学和生命科学为优势，广东省人民政府和农业农村部共建的省部共建大学');
INSERT INTO `mork` VALUES (17, '海南大学', 'https://ha.hainanu.edu.cn/home2020/', '名校官网', 'https://z3.ax1x.com/2021/10/22/5yf2LQ.png', '海南大学（Hainan University），简称海大，坐落于海南省海口市，是教育部与海南省人民政府“部省合建高校”、海南省人民政府与财政部共建高校、海南省属综合性重点大学、海南省“国内一流大学建设”重点支持高校，位列国家“双一流”、“211工程”');
INSERT INTO `mork` VALUES (18, '电子科技大学', 'https://www.uestc.edu.cn/', '名校官网', 'https://z3.ax1x.com/2021/10/22/5yfIJ0.jpg', '电子科技大学（University of Electronic Science and Technology of China），简称“电子科大”，坐落于四川省成都市，是中华人民共和国教育部直属高校，由教育部、工业和信息化部、四川省和成都市共建；位列国家“双一流”、“985工程”、“211工程”');
INSERT INTO `mork` VALUES (19, '西安电子科技大学', 'https://www.xidian.edu.cn/', '名校官网', 'https://z3.ax1x.com/2021/10/22/5yhiOe.jpg', '西安电子科技大学（Xidian University），简称“西电”或“西军电”，位于陕西省西安市，是中央部属高校，直属于教育部，为全国重点大学，由教育部与工业和信息化部、国家国防科技工业局、中国电子科技集团公司共建，位列国家“双一流”、“211工程”');
INSERT INTO `mork` VALUES (20, '杭州电子科技大学', 'https://www.hdu.edu.cn/', '名校官网', 'https://z3.ax1x.com/2021/10/22/5yhM6S.jpg', '杭州电子科技大学（Hangzhou Dianzi University），简称“杭电”，位于杭州市，是浙江省人民政府与国防科技工业局共建高校，是一所电子信息特色突出，经管学科优势明显，工、理、经、管、文、法、艺等多学科相互渗透的教学研究型大学，是浙江省首批重点建设的5所高校之一');
INSERT INTO `mork` VALUES (21, '海南省文昌中学', 'http://www.hnwczx.com/', '名校官网', 'https://z3.ax1x.com/2021/10/22/5yhD0J.jpg', '海南省文昌中学（Wenchang Middle school），是由文昌市教育局主管的一所公办完全中学、海南省完全中学一级（甲等）学校、全国青少年校园足球特色学校、海南省中小学教育信息化应用示范校培育入围学校。');
INSERT INTO `mork` VALUES (22, '桂林市教育局', 'http://jyj.guilin.gov.cn/', '地方政务系统', 'https://z3.ax1x.com/2021/10/22/5yhq9P.png', '桂林市教育局');
INSERT INTO `mork` VALUES (23, '广西教育厅', 'http://jyt.gxzf.gov.cn/', '地方政务系统', 'https://z3.ax1x.com/2021/10/22/5yhL1f.png', '广西壮族自治区教育厅');
INSERT INTO `mork` VALUES (24, '海南省教育厅', 'http://edu.hainan.gov.cn/', '地方政务系统', 'https://z3.ax1x.com/2021/10/22/5yhvng.png', '海南省教育厅');
INSERT INTO `mork` VALUES (25, '文昌市教育局', 'http://wenchang.hainan.gov.cn/wcsjy/wcbindex.shtml', '地方政务系统', 'https://z3.ax1x.com/2021/10/22/5y49Nn.png', '文昌市教育局');
INSERT INTO `mork` VALUES (26, 'CSDN', 'https://www.csdn.net/', '博客论坛', 'https://z3.ax1x.com/2021/10/22/5y4NEd.png', 'CSDN是全球知名中文IT技术交流平台,创建于1999年,包含原创博客、精品问答、职业培训、技术论坛、资源下载等产品服务,提供原创、优质、完整内容的专业IT技术开发社区');
INSERT INTO `mork` VALUES (27, '博客园', 'https://www.cnblogs.com/', '博客论坛', 'https://z3.ax1x.com/2021/10/22/5y4wCt.png', '博客园是一个面向开发者的知识分享社区。自创建以来,博客园一直致力并专注于为开发者打造一个纯净的技术交流社区,推动并帮助开发者通过互联网分享知识,从而让更多开发者从中受益。');
INSERT INTO `mork` VALUES (28, '掘金社区', 'https://juejin.cn/', '博客论坛', 'https://z3.ax1x.com/2021/10/22/5y4sKS.png', '掘金是一个帮助开发者成长的社区,是给开发者用的 Hacker News,给设计师用的 Designer News,和给产品经理用的 Medium。掘金的技术文章由稀土上聚集的技术大牛和极客共同编辑为你筛选出最优质的干货...');
INSERT INTO `mork` VALUES (29, '知乎', 'https://www.zhihu.com/', '博客论坛', 'https://www.hualigs.cn/image/617195cadd89e.jpg', '\r\n知乎，是一个中文互联网高质量的问答社区和创作者聚集的原创内容平台，于2011年1月正式上线，以“让人们更好地分享知识、经验和见解，找到自己的解答”为品牌使命。');
INSERT INTO `mork` VALUES (30, '思否', 'https://segmentfault.com/', '博客论坛', 'https://z3.ax1x.com/2021/10/22/5y5Qaj.png', 'SegmentFault 思否是中国领先的开发者技术社区。我们以技术问答、技术专栏、技术课程、技术资讯为核心的产品形态,为开发者提供纯粹、高质的技术交流平台。');
INSERT INTO `mork` VALUES (31, '开源中国', 'https://www.oschina.net/', '博客论坛', 'https://z3.ax1x.com/2021/10/22/5y5FIA.png', 'OSCHINA.NET 是目前领先的中文开源技术社区。我们传播开源的理念,推广开源项目,为 IT 开发者提供了一个发现、使用、并交流开源技术的平台');
INSERT INTO `mork` VALUES (32, 'GitHub', 'https://github.com/', '博客论坛', 'https://z3.ax1x.com/2021/10/22/5y5Zxf.png', 'GitHub是一个面向开源及私有软件项目的托管平台，因为只支持Git作为唯一的版本库格式进行托管，故名GitHub。');
INSERT INTO `mork` VALUES (33, 'Gitee', 'https://gitee.com/', '博客论坛', 'https://gitee.com/static/images/logo-black.svg?t=158106664', 'Gitee.com(码云) 是 OSCHINA.NET 推出的代码托管平台,支持 Git 和 SVN,提供免费的私有仓库托管。目前已有超过 800 万的开发者选择 Gitee。');
INSERT INTO `mork` VALUES (34, '菜鸟裹裹', 'https://www.cainiao.com/', '快递查询', 'https://www.hualigs.cn/image/6171989b49e15.jpg', '菜鸟裹裹是阿里巴巴旗下菜鸟网络提供快递服务的应用软件，提供寄快递、查快递、取快递功能服务。');
INSERT INTO `mork` VALUES (35, '顺丰速运', 'https://www.sf-express.com/cn/sc/', '快递查询', 'https://www.hualigs.cn/image/617198e4193ba.jpg', '顺丰是国内的快递物流综合服务商，总部位于深圳，经过多年发展，已初步建立为客户提供一体化综合物流解决方案的能力，不仅提供配送端的物流服务，还延伸至价值链前端的产、供、销、配等环节，从消费者需求出发，以数据为牵引，利用大数据分析和云计算技术，为客户提供仓储管理、销售预测、大数据分析、金融管理等一揽子解决方案。');
INSERT INTO `mork` VALUES (36, '京东物流', 'https://www.jdl.cn/', '快递查询', 'https://fastly.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20220511173043.png', '京东集团2007年开始自建物流，2017年4月正式成立京东物流集团，2021年5月，京东物流于香港联交所主板上市。京东物流是中国领先的技术驱动的供应链解决方案及物流服务商，以“技术驱动，引领全球高效流通和可持续发展”为使命，致力于成为全球最值得信赖的供应链基础设施服务商。');
INSERT INTO `mork` VALUES (37, '邮政速递EMS', 'https://www.ems.com.cn/', '快递查询', 'https://z3.ax1x.com/2021/10/22/5y5IJI.png', 'EMS拥有首屈一指的航空和陆路运输网络以及200多个高效发达的邮件处理中心,国内范围通达全覆盖,并拥有多种不同的快递产品和增值服务,满足客户多样化、个性化的寄件需求。');
INSERT INTO `mork` VALUES (38, '百度百科', 'https://baike.baidu.com/', '百科资料', 'https://z3.ax1x.com/2021/10/22/5y5by8.png', '百度百科是一部内容开放、自由的网络百科全书，旨在创造一个涵盖所有领域知识，服务所有互联网用户的中文知识性百科全书。在这里你可以参与词条编辑，分享贡献你的知识。');
INSERT INTO `mork` VALUES (39, 'Wiki百科', 'https://zh.wikipedia.org/', '百科资料', 'https://z3.ax1x.com/2021/10/22/5y5xFs.png', '维基百科（英语：Wikipedia，英语音标：/ˌwɪkᵻˈpiːdiə/ 或 /ˌwɪkiˈpiːdiə/），总部位于美国，是一个基于维基技术的多语言百科全书式的协作计划，是用多种语言编写而成的网络百科全书。');
INSERT INTO `mork` VALUES (40, '力扣', 'https://leetcode-cn.com/', '编程学习', 'https://www.hualigs.cn/image/61719bcb78965.jpg', '力扣（LeetCode）是领扣网络旗下专注于程序员技术成长和企业技术人才服务的品牌。源自美国硅谷，力扣为全球程序员提供了专业的IT技术职业化提升平台，有效帮助程序员实现快速进步和长期成长。');
INSERT INTO `mork` VALUES (41, '牛客网', 'https://www.nowcoder.com/', '编程学习', 'https://www.hualigs.cn/image/61719c5419e94.jpg', '牛客网，是一个集笔面试系统、题库、课程教育、社群交流、招聘内推于一体的招聘类网站。');
INSERT INTO `mork` VALUES (42, 'Hutool类库', 'https://www.hutool.cn/docs/#/', '参考文档/API', 'https://www.hualigs.cn/image/6172183cbf718.jpg', 'Hutool只是一个JAVA工具包，有助于简化代码，避免重复造轮子，每个程序员都有自己的工具包，它代表了你工作的积累，Hutool只是收集了大家积累的工具，Hutool几乎涵盖了工作中大部分业务的工具。');
INSERT INTO `mork` VALUES (43, '中国知网', 'https://www.cnki.net/', '学术论文', 'https://z3.ax1x.com/2021/10/22/569iPP.png', '中国知网，始建于1999年6月，是中国核工业集团资本控股有限公司控股的同方股份有限公司旗下的学术平台。知网是国家知识基础设施的概念，由世界银行于1998年提出。CNKI工程是以实现全社会知识资源传播共享与增值利用为目标的信息化建设项目。');
INSERT INTO `mork` VALUES (44, '百度学术', 'https://xueshu.baidu.com/', '学术论文', 'https://z3.ax1x.com/2021/10/22/569ebj.png', '百度学术搜索，是一个提供海量中英文文献检索的学术资源搜索平台，涵盖了各类学术期刊、学位、会议论文，旨在为国内外学者提供最好的科研体验。');
INSERT INTO `mork` VALUES (45, 'ElementUI', 'https://element.eleme.cn/#/zh-CN/component/layout', '前端工具', 'https://z3.ax1x.com/2021/10/22/569bZj.png', 'element-ui是由饿了么前端团队推出的一套为开发者、设计师和产品经理准备的基于Vue.js 2.0的桌面组件库,而手机端有对应框架是Mint UI。');
INSERT INTO `mork` VALUES (46, 'Element-Plus UI', 'https://element-plus.gitee.io/zh-CN/component/border.html', '前端工具', 'https://z3.ax1x.com/2021/10/22/569qds.png', 'element-plus是由饿了么前端团队推出的一套为开发者、设计师和产品经理准备的基于Vue.js 3.0的桌面组件库。');
INSERT INTO `mork` VALUES (47, 'Bootstrap', 'https://v3.bootcss.com/', '前端工具', 'https://z3.ax1x.com/2021/10/22/56CVW6.png', 'Bootstrap是Twitter推出的一个用于前端开发的开源工具包。它由Twitter的设计师Mark Otto和Jacob Thornton合作开发,是一个CSS/HTML框架。');
INSERT INTO `mork` VALUES (48, '万方数据', 'http://www.wanfangdata.com.cn/', '学术论文', 'https://z3.ax1x.com/2021/10/22/56CWm4.jpg', '万方数据知识服务平台-中外学术论文、中外标准、中外专利、科技成果、政策法规等科技文献的在线服务平台。');
INSERT INTO `mork` VALUES (49, '菜鸟教程', 'https://www.runoob.com/', '编程学习', 'https://z3.ax1x.com/2021/10/22/56CH1K.png', '菜鸟教程提供了编程的基础技术教程, 介绍了HTML、CSS、Javascript、Python,Java,Ruby,C,PHP , MySQL等各种编程语言的基础知识。');
INSERT INTO `mork` VALUES (50, 'W3school', 'https://www.w3school.com.cn/', '编程学习', 'https://fastly.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20220511214857.png', '领先的Web技术教程在w3school，你可以找到你所需要的所有的网站建设教程。从基础的 HTML 到 CSS，乃至进阶的XML、SQL、JS、PHP 和 ASP.NET。');
INSERT INTO `mork` VALUES (51, 'W3Cschool', 'https://www.w3cschool.cn/', '编程学习', 'https://www.hualigs.cn/image/61721cdb3abef.jpg', 'w3cschool官网提供优质、系统化的教程、经验文档、参考手册,为开发者节省时间,提高效率。');
INSERT INTO `mork` VALUES (52, 'AngularJS中文文档', 'https://www.angularjs.net.cn/', '参考文档/API', 'https://z3.ax1x.com/2021/10/22/56i9a9.jpg', 'AngularJS诞生于2009年，由Misko Hevery 等人创建，是一款构建用户界面的前端框架，后为Google所收购。');
INSERT INTO `mork` VALUES (53, '桂林电子科技大学图书馆', 'https://www.guet.edu.cn/lib/', '学术论文', 'https://z3.ax1x.com/2021/10/22/562kff.png', '桂林电子科技大学图书馆始建于1960年，前身分别是桂林无线电专科学校图书馆和桂林电子工业学院图书馆。分为金鸡岭校区图书馆和花江校区图书馆，其中花江校区图书馆是我国高校单体建筑面积最大之一的图书馆');
INSERT INTO `mork` VALUES (54, 'NutUI - 移动端组件库', 'https://nutui.jd.com/#/intro', '前端工具', 'https://s3.bmp.ovh/imgs/2021/11/08bab10a68fb9ba7.png', 'NutUI 是一套移动端轻量级 Vue 组件库');
INSERT INTO `mork` VALUES (55, 'Apache ECharts', 'https://echarts.apache.org/zh/index.html', '前端工具', 'https://s3.bmp.ovh/imgs/2021/11/3fba762cbb77e895.png', 'ECharts是一款基于JavaScript的数据可视化图表库，提供直观，生动，可交互，可个性化定制的数据可视化图表。ECharts最初由百度团队开源，并于2018年初捐赠给Apache基金会，成为ASF孵化级项目。');
INSERT INTO `mork` VALUES (56, '谷粉学术', 'https://gfsoso.99lb.net/', '学术论文', 'https://i.bmp.ovh/imgs/2021/11/4acfb070a6b7b76c.png', '谷粉学术:由学术文献研究者联合建立的文献检索服务,方便大家稳定快速地利用谷歌学术搜索查找文献进行学术研究.您所在区域google学术无法访问时用谷粉学术进行文献查找就对了。');
INSERT INTO `mork` VALUES (57, '阮一峰的博客', 'http://www.ruanyifeng.com/home.html', '博客论坛', 'https://z3.ax1x.com/2021/11/01/IpvFbt.jpg', '阮一峰，70后，英文名Frank。上海财经大学世界经济博士研究生。主要研究宏观金融、货币政策与美国经济。');
INSERT INTO `mork` VALUES (58, '阿里巴巴矢量图标库', 'https://www.iconfont.cn/', '前端工具', 'https://z3.ax1x.com/2021/11/01/IpvYPU.png', 'iconfont-国内功能很强大且图标内容很丰富的矢量图标库,提供矢量图标下载、在线存储、格式转换等功能。阿里巴巴体验团队倾力打造,设计和前端开发的便捷工具');
INSERT INTO `mork` VALUES (59, '开源中国在线API', 'https://tool.oschina.net/apidocs', '参考文档/API', 'https://z3.ax1x.com/2021/10/22/5y5FIA.png', '开源中国出品的在线API平台');
INSERT INTO `mork` VALUES (60, '美团技术团队', 'https://tech.meituan.com/', '编程学习', 'https://z3.ax1x.com/2021/11/01/IpvWMd.png', '人是美团最重要的资源。美团技术团队以浓厚的学习和分享氛围享誉业界：每天不止一场内部技术讲座与分享，还有荟萃业界专家的TopTalk，藏书近万的P2P图书馆，帮助同学拓展视野，追求卓越；每年定期举行的Hackathon、Ideathon和MDD Cup算法大赛，激发无限创意；当然还有业内已经有口皆碑的美团技术团队博客与微信公众号（meituantech），每月一次公开的技术沙龙，与同行切磋交流，不亦乐乎……');
INSERT INTO `mork` VALUES (61, 'Google 学术搜索', 'https://scholar.google.com/', '学术论文', 'https://z3.ax1x.com/2021/11/01/IpvqzQ.png', '借助Google 学术搜索,您可以轻松地大范围搜索学术文献。搜索范围囊括众多知识领域和来源:文章、论文、图书、摘要和法院判决意见书。');
INSERT INTO `mork` VALUES (62, 'Gmail', 'https://mail.google.com/mail/', '邮件通讯', 'https://z3.ax1x.com/2021/11/01/IpxZe1.png', 'Gmail 是一款直观、高效、实用的电子邮件应用。免费提供15GB存储空间,垃圾邮件更少,而且支持移动访问。 一个帐户,畅享 Google所有服务! ');
INSERT INTO `mork` VALUES (63, '网易163邮箱', 'https://mail.163.com/', '邮件通讯', 'https://z3.ax1x.com/2021/11/01/IpxKJO.png', '网易163免费邮箱--中文邮箱第一品牌。容量自动翻倍,支持50兆附件,免费开通手机号码邮箱赠送3G超大附件服务。支持各种客户端软件收发,垃圾邮件拦截率超过98%。');
INSERT INTO `mork` VALUES (64, 'JavaFX中文网', 'https://openjfx.cn/', '编程学习', 'https://z3.ax1x.com/2021/11/01/I98RNq.png', 'JavaFX JavaFX 是一个开源的下一代客户端应用平台,适用于基于Java构建的桌面、移动端和嵌入式系统。');
INSERT INTO `mork` VALUES (67, 'Pure-Color', 'https://mhg-https://mhg-lab.github.io/pages/color/', '前端工具', 'https://fastly.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20220206231231.png', '一个实用的取色网站');
INSERT INTO `mork` VALUES (68, 'Young Kbt’s 首页', 'https://www.youngkbt.cn/', '博客论坛', 'https://www.youngkbt.cn/index_static/assets/images/logo-image.jpg', '这是基于 Nginx 搭建的 Young Kbt 的首页，提供项目入口，笔记博客入口，也是一个好看的 Nginx 首页，支持响应式');
INSERT INTO `mork` VALUES (69, 'QQ邮箱', 'https://mail.qq.com/', '邮件通讯', 'https://fastly.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20220512230847.png', 'QQ邮箱是腾讯公司2002年推出，向用户提供安全、稳定、快速、便捷电子邮件服务的邮箱产品，已为超过1亿的邮箱用户提供免费和增值邮箱服务。');
INSERT INTO `mork` VALUES (70, 'Sa-Token', 'https://sa-token.dev33.cn/doc/index.html#/', '编程学习', 'https://fastly.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20220512231234.png', '一个轻量级 Java 权限认证框架，让鉴权变得简单、优雅！');
INSERT INTO `mork` VALUES (71, '源码阅读网-Maven搜索', 'http://mvn.coderead.cn/', '编程学习', 'https://fastly.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20220512231728.png', 'Maven搜索-最快捷的Maven搜索-由源码阅读网提供技术服务');
INSERT INTO `mork` VALUES (72, '微信公众平台测试号', 'https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login', '编程学习', 'https://fastly.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20220512232259.png', '微信公众平台测试号可以方便开发人员对微信公众开发平台的开发');
INSERT INTO `mork` VALUES (73, '智慧树网', 'https://www.zhihuishu.com/', '在线学习网站', 'https://fastly.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20220512232914.png', '智慧树是全球大型的学分课程运营服务平台,在线教育平台拥有海量大学高品质课程,网络教育在线完美支持跨校授课,学分认证,名师名课名校,在线互动教育学堂,体验VIP级课程学习,让你成为学堂中的MVP。');
INSERT INTO `mork` VALUES (74, '网易云课堂', 'https://study.163.com/', '在线学习网站', 'https://fastly.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20220512233233.png', '网易云课堂，是网易公司打造的在线实用技能学习平台，该平台于2012年12月底正式上线，主要为学习者提供海量、优质的课程，用户可以根据自身的学习程度，自主安排学习进度。');
INSERT INTO `mork` VALUES (75, '腾讯课堂', 'https://ke.qq.com/', '在线学习网站', 'https://fastly.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20220512233411.png', '腾讯课堂是腾讯推出的综合性在线终身学习平台，聚合大量优质教育机构和名师，下设职业培训、公务员考试、托福雅思、考证考级、英语口语等众多在线学习精品课程，打造老师在线上课教学、学生及时互动学习的课堂。');
INSERT INTO `mork` VALUES (76, '中文stackoverflow', 'https://stackoom.com/', '博客论坛', '', '中文版的stackoverflow，翻译了原网站的内容');

-- ----------------------------
-- Table structure for start
-- ----------------------------
DROP TABLE IF EXISTS `start`;
CREATE TABLE `start`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of start
-- ----------------------------
INSERT INTO `start` VALUES (1, '0:0:0:0:0:0:0:1', '1');
INSERT INTO `start` VALUES (8, '103.181.164.91', '1');
INSERT INTO `start` VALUES (9, '223.198.60.252', '1');
INSERT INTO `start` VALUES (10, '140.240.17.131', '1');
INSERT INTO `start` VALUES (11, '119.41.229.178', '4');
INSERT INTO `start` VALUES (12, '140.240.25.163', '3');
INSERT INTO `start` VALUES (13, '121.58.21.75', '1');
INSERT INTO `start` VALUES (14, '140.240.19.30', '1');

-- ----------------------------
-- Table structure for sys_schedule_trigger
-- ----------------------------
DROP TABLE IF EXISTS `sys_schedule_trigger`;
CREATE TABLE `sys_schedule_trigger`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务分组名称',
  `job_class` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务类：填写Job类的完整类名',
  `job_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务类：填写的调用类中的方法名',
  `job_params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务类中的参数',
  `job_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务描述',
  `cron` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Cron表达式',
  `trigger_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '触发器名称',
  `trigger_group` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '触发器分组名称',
  `trigger_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '触发器描述',
  `concurrent` int NOT NULL COMMENT '是否并发执行（0允许 1禁止）',
  `status` int NOT NULL COMMENT '状态：0禁用 1启用',
  `account` int NOT NULL COMMENT '添加人：这里需要与用户表建立主外键关系',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `job_name`(`job_name` ASC) USING BTREE,
  UNIQUE INDEX `trigger_name`(`trigger_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_schedule_trigger
-- ----------------------------
INSERT INTO `sys_schedule_trigger` VALUES (1, '测试1', '测试组', 'com.chen.mycardsystembackstage.utils.QuartUtils.PutChats', 'test', NULL, '测试', '0/30 * * * * ? *', '测试触发器', 'trigger1', '测试1', 0, 1, 1, '2022-12-29 10:02:49');
INSERT INTO `sys_schedule_trigger` VALUES (2, '提醒管理员注意账号安全，修改密码', '消息组', 'com.chen.mycardsystembackstage.utils.QuartUtils.PutChats', 'updataPass', NULL, '提醒管理员注意账号安全，修改密码', '0 0 10 1 * ? *', '消息触发器', 'trigger2', '给用户发送消息', 0, 1, 1, '2023-02-01 15:56:30');
INSERT INTO `sys_schedule_trigger` VALUES (8, '给一个特定管理员发送消息', '消息组', 'com.chen.mycardsystembackstage.utils.QuartUtils.PutChats', 'chatsForOne', NULL, '给一个特定管理员发送消息', '0 0 10 1 * ? *', '消息触发器2', 'trigger3', '给特定用户发送消息', 0, 1, 1, '2023-02-01 16:00:48');
INSERT INTO `sys_schedule_trigger` VALUES (9, '启动时显示Swagger的地址', '后台组', 'com.chen.mycardsystembackstage.utils.QuartUtils.PutChats', 'showSwaggerHost', NULL, '启动时显示Swagger的地址', '0 0 10 1 * ? *', 'Swagger触发器', 'trigger4', '启动时显示Swagger的地址', 0, 0, 1, '2023-02-01 16:03:27');

-- ----------------------------
-- Table structure for sys_schedule_trigger_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_schedule_trigger_log`;
CREATE TABLE `sys_schedule_trigger_log`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '日志的id',
  `job_id` int NOT NULL COMMENT '任务的id',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务名称',
  `status` int NOT NULL COMMENT '发送状态：0失败1成功',
  `time` datetime NOT NULL COMMENT '发送的时间',
  `recipient` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接受消息的用户，写id',
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_schedule_trigger_log
-- ----------------------------
INSERT INTO `sys_schedule_trigger_log` VALUES (1, 1, '测试1', 1, '2023-02-01 17:12:30', '后台通知消息', '测试的定时任务');
INSERT INTO `sys_schedule_trigger_log` VALUES (2, 1, '测试1', 1, '2023-02-01 17:13:00', '后台通知消息', '测试的定时任务');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userPass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `openid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'system', '1', 'o_pA75h_jMAXIGlITHs3XNX-ykuA', '2495842975@qq.com');
INSERT INTO `user` VALUES (2, 'george', '2', 'o_pA75h_jMAXIGlITHs3XNX-ykuA', '2495842975@qq.com');
INSERT INTO `user` VALUES (3, 'admin', '3', '', '2495842975@qq.com');
INSERT INTO `user` VALUES (4, 'fuduoxun', '4', NULL, '2425367947@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
