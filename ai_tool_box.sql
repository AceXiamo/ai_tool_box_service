/*
 Navicat Premium Data Transfer

 Source Server         : 106.55.48.201
 Source Server Type    : MySQL
 Source Server Version : 50650 (5.6.50-log)
 Source Host           : 106.55.48.201:3306
 Source Schema         : ai_tool_box

 Target Server Type    : MySQL
 Target Server Version : 50650 (5.6.50-log)
 File Encoding         : 65001

 Date: 07/09/2023 10:57:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ma_config
-- ----------------------------
DROP TABLE IF EXISTS `ma_config`;
CREATE TABLE `ma_config` (
  `config_key` varchar(64) NOT NULL COMMENT 'key',
  `config_value` text NOT NULL COMMENT '配置内容(JSON字符串)',
  PRIMARY KEY (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='小程序配置表';

-- ----------------------------
-- Records of ma_config
-- ----------------------------
BEGIN;
INSERT INTO `ma_config` (`config_key`, `config_value`) VALUES ('cover', '{\"url\":\"https://image.qwq.link/images/2022/08/13/F5S7GZ62TXSCH464YO.png\",\"width\":929,\"height\":540,\"show\":true,\"desc\":\"——— 出自 《战斗员派遣中！》\"}');
INSERT INTO `ma_config` (`config_key`, `config_value`) VALUES ('item', '{\"showChat\":true,\"showTranslator\":true,\"showPolish\":true,\"showEat\":true,\"showHis\":true}');
INSERT INTO `ma_config` (`config_key`, `config_value`) VALUES ('main', '{\"show\":true,\"content\":[{\"tag\":\"🤣\",\"show\":true,\"items\":[{\"text\":\"“未备案的 APP / 小程序 不得从事互联网信息服务”\",\"class\":\"tip-dot font-bold\"},{\"text\":\"为响应号召，该小程序将在本月底停止服务 😋\",\"class\":\"tip-dot font-bold\"}]}]}');
COMMIT;

-- ----------------------------
-- Table structure for request_history
-- ----------------------------
DROP TABLE IF EXISTS `request_history`;
CREATE TABLE `request_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `open_id` varchar(64) DEFAULT NULL COMMENT 'openId',
  `user_history_id` int(11) DEFAULT NULL COMMENT '用户消息历史id',
  `message_content` longtext COMMENT '消息体',
  `result_content` longtext COMMENT '响应结果',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5729 DEFAULT CHARSET=utf8mb4 COMMENT='请求历史表';

-- ----------------------------
-- Table structure for user_history
-- ----------------------------
DROP TABLE IF EXISTS `user_history`;
CREATE TABLE `user_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `open_id` varchar(64) NOT NULL COMMENT 'openId',
  `type` varchar(64) DEFAULT NULL COMMENT '类型',
  `message_content` longtext NOT NULL COMMENT '消息题（JSON数组',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3295 DEFAULT CHARSET=utf8mb4 COMMENT='消息历史表';

-- ----------------------------
-- Table structure for wx_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user` (
  `open_id` varchar(64) NOT NULL COMMENT '用户open_id',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT '/avatar/64193f48e4b0fcfc58469980.jpg' COMMENT '头像',
  `is_ban` tinyint(1) DEFAULT '0' COMMENT '是否永久封禁',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ban_end_time` datetime DEFAULT NULL COMMENT '短时间封禁截止时间',
  `desc_of_ban` varchar(255) DEFAULT NULL COMMENT '封禁备注',
  PRIMARY KEY (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信用户表';
SET FOREIGN_KEY_CHECKS = 1;
