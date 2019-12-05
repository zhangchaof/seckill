/*
Navicat MySQL Data Transfer

Source Server         : 5.7localhost
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : seckill

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2019-12-05 09:49:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `good_code` varchar(32) NOT NULL COMMENT '商品编码',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `goods_title` varchar(255) DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(64) DEFAULT NULL COMMENT '商品图片',
  `goods_detail` longtext COMMENT '商品的详情介绍',
  `goods_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品单价',
  `goods_stock` int(11) DEFAULT '0' COMMENT '商品库存，-1表示没有限制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '0001', 'iphoneX', 'Apple iPhone X(A1865)64GB 银色 移动联通电信4G手机', '/img/iphoneX.png', 'Apple iPhoneX(A1865) 64GB银色 移动联通电信4G手机', '8765.00', '100');
INSERT INTO `goods` VALUES ('2', '0002', '华为Mate9', '华为Mate9 4GB + 32GB', '/img/meta9.png', '华为Mate9 4GB + 32GB', '3212.00', '10');

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'order ID',
  `order_no` varchar(32) NOT NULL COMMENT '订单编号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `good_code` varchar(32) DEFAULT NULL COMMENT '商品编码',
  `delivery_addr_id` bigint(20) DEFAULT NULL COMMENT '收货地址',
  `goods_name` varchar(16) DEFAULT NULL COMMENT '商品名称',
  `goods_count` int(11) DEFAULT '0' COMMENT '商品数量',
  `goods_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品单价',
  `order_channel` tinyint(4) DEFAULT '0' COMMENT '1pc,2android,3ios',
  `status` tinyint(4) DEFAULT '0' COMMENT '0新建未支付，2已支付，3已发货4，已收货，5已完成',
  `create_date` datetime DEFAULT NULL COMMENT '订单创建时间',
  `pay_date` datetime DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order_info
-- ----------------------------

-- ----------------------------
-- Table structure for seckill_goods
-- ----------------------------
DROP TABLE IF EXISTS `seckill_goods`;
CREATE TABLE `seckill_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `good_code` varchar(32) NOT NULL COMMENT '商品编码',
  `seckill_price` decimal(10,2) DEFAULT '0.00' COMMENT '秒杀价',
  `stock_count` int(11) DEFAULT '0' COMMENT '库存数量',
  `start_date` datetime DEFAULT NULL COMMENT '秒杀开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '秒杀结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of seckill_goods
-- ----------------------------
INSERT INTO `seckill_goods` VALUES ('1', '0001', '0.01', '4', '2019-12-05 09:48:33', '2019-12-05 09:48:33');
INSERT INTO `seckill_goods` VALUES ('2', '0002', '0.01', '9', '2019-12-05 09:48:33', '2019-12-05 09:48:33');

-- ----------------------------
-- Table structure for seckill_order
-- ----------------------------
DROP TABLE IF EXISTS `seckill_order`;
CREATE TABLE `seckill_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀 order ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `order_no` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `goods_code` varchar(32) DEFAULT NULL COMMENT '商品编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of seckill_order
-- ----------------------------

-- ----------------------------
-- Table structure for seckill_user
-- ----------------------------
DROP TABLE IF EXISTS `seckill_user`;
CREATE TABLE `seckill_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `nickname` varchar(255) NOT NULL,
  `password` varchar(32) DEFAULT NULL COMMENT 'MD5(MD5(pass明文+固定salt) + salt)',
  `salt` varchar(10) DEFAULT NULL,
  `head` varchar(128) DEFAULT NULL COMMENT '头像，云存储的ID',
  `register_date` datetime DEFAULT NULL COMMENT '注册时间',
  `last_login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `login_count` int(11) DEFAULT NULL COMMENT '登录次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18721259144 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of seckill_user
-- ----------------------------
INSERT INTO `seckill_user` VALUES ('18721259143', 'zcf', 'b7797cce01b4b131b433b6acf4add449', '1a2b3c4d', null, '2019-12-03 09:52:32', '2019-12-03 09:52:32', '1');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `age` int(4) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('2', 'name', '10');
INSERT INTO `test` VALUES ('3', 'test', '10');
INSERT INTO `test` VALUES ('4', 'test', '10');
INSERT INTO `test` VALUES ('5', 'test20', '20');
INSERT INTO `test` VALUES ('6', 'test30', '30');
INSERT INTO `test` VALUES ('7', 'test40', '40');
INSERT INTO `test` VALUES ('8', 'test40', '40');
INSERT INTO `test` VALUES ('9', 'test40', '40');
INSERT INTO `test` VALUES ('10', 'test60', '60');