/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 127.0.0.1:3306
 Source Schema         : sharePlatform

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 01/07/2021 18:15:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart_item
-- ----------------------------
DROP TABLE IF EXISTS `cart_item`;
CREATE TABLE `cart_item` (
  `cart_item_id` int NOT NULL AUTO_INCREMENT COMMENT '购物项id',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `goods_id` int DEFAULT NULL COMMENT '物品id',
  `goods_count` int DEFAULT NULL COMMENT '物品数量',
  `is_deleted` int DEFAULT NULL COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `price` double DEFAULT NULL COMMENT '单项总价',
  `goods_cover_img` varchar(255) DEFAULT NULL COMMENT '物品图片',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '物品名称',
  PRIMARY KEY (`cart_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart_item
-- ----------------------------
BEGIN;
INSERT INTO `cart_item` VALUES (1, 10, 1, 10, 0, '2021-06-15 19:00:00', '2021-06-29 19:00:00', NULL, NULL, NULL);
INSERT INTO `cart_item` VALUES (3, 3, 1, 10, 0, '2021-06-15 19:00:00', '2021-06-29 19:00:00', NULL, NULL, NULL);
INSERT INTO `cart_item` VALUES (19, 12, 1, 1, 0, NULL, NULL, 100, '12', 'iphone12 手机');
INSERT INTO `cart_item` VALUES (22, 12, 9, 1, 0, NULL, NULL, 110, '8c5d5f5f-14e9-43b5-9d41-b69b31f2859a', 'rtx3080ti显卡');
COMMIT;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `category_level` varchar(5) NOT NULL COMMENT '分类级别',
  `parent_id` int NOT NULL COMMENT '父分类ID',
  `category_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分类名称',
  `is_deleted` int DEFAULT NULL COMMENT '删除标识（0-未删除，1-已删除）',
  `category_img` varchar(255) DEFAULT NULL COMMENT '图片id',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES (1, '1', 0, '手机相关', 0, '2');
INSERT INTO `category` VALUES (2, '1', 0, '电脑相关', 0, '2');
INSERT INTO `category` VALUES (3, '2', 1, '手机', 0, '2');
INSERT INTO `category` VALUES (4, '2', 1, '充电器', 0, '2');
INSERT INTO `category` VALUES (5, '2', 1, '数据线', 0, '2');
INSERT INTO `category` VALUES (6, '2', 2, '键盘', 0, '2');
INSERT INTO `category` VALUES (7, '2', 2, '鼠标', 0, '2');
INSERT INTO `category` VALUES (8, '2', 2, '显示器', 0, '2');
INSERT INTO `category` VALUES (9, '1', 0, '相机相关', 0, '2');
INSERT INTO `category` VALUES (10, '1', 0, '电视相关', 0, '2');
INSERT INTO `category` VALUES (11, '1', 0, '影音设备', 0, '2');
INSERT INTO `category` VALUES (12, '1', 0, '网络设备', 0, '2');
INSERT INTO `category` VALUES (13, '2', 1, '手机贴膜', 0, NULL);
INSERT INTO `category` VALUES (14, '2', 1, '手机壳', 0, NULL);
INSERT INTO `category` VALUES (15, '2', 1, '手机支架', 0, NULL);
INSERT INTO `category` VALUES (16, '2', 1, '苹果周边', 0, NULL);
INSERT INTO `category` VALUES (17, '2', 1, 'iphone', 0, NULL);
INSERT INTO `category` VALUES (18, '2', 2, '显卡', 0, NULL);
INSERT INTO `category` VALUES (19, '2', 2, '硬盘', 0, NULL);
INSERT INTO `category` VALUES (20, '2', 2, 'cpu', 0, NULL);
INSERT INTO `category` VALUES (21, '2', 2, '主板', 0, NULL);
INSERT INTO `category` VALUES (22, '2', 2, '内存', 0, NULL);
INSERT INTO `category` VALUES (23, '2', 9, '数码相机', 0, NULL);
INSERT INTO `category` VALUES (24, '2', 9, '微单相机', 0, NULL);
INSERT INTO `category` VALUES (25, '2', 9, '单反相机', 0, NULL);
INSERT INTO `category` VALUES (26, '2', 9, '存储卡', 0, NULL);
INSERT INTO `category` VALUES (27, '2', 9, '云台', 0, NULL);
INSERT INTO `category` VALUES (28, '2', 9, '索尼', 0, NULL);
INSERT INTO `category` VALUES (29, '2', 10, 'OLED电视', 0, NULL);
INSERT INTO `category` VALUES (30, '2', 11, '电脑音响', 0, NULL);
INSERT INTO `category` VALUES (31, '2', 12, '路由器', 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for entrust
-- ----------------------------
DROP TABLE IF EXISTS `entrust`;
CREATE TABLE `entrust` (
  `entrust_id` int NOT NULL AUTO_INCREMENT COMMENT '委托id',
  `e_goods_name` varchar(255) DEFAULT NULL COMMENT '委托物品名称',
  `e_goods_intro` varchar(255) DEFAULT NULL COMMENT '物品简介',
  `e_goods_category` int DEFAULT NULL COMMENT '物品分类id',
  `e_goods_cover_img` varchar(255) DEFAULT NULL COMMENT '物品主图片',
  `e_goods_carousel` varchar(255) DEFAULT NULL COMMENT '物品轮播图片',
  `e_goods_detail_content` text COMMENT '物品详情',
  `e_goods_price` double(10,2) DEFAULT NULL COMMENT '委托租赁单价 元/天',
  `e_stock_num` int DEFAULT NULL COMMENT '委托数量',
  PRIMARY KEY (`entrust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entrust
-- ----------------------------
BEGIN;
INSERT INTO `entrust` VALUES (1, 'qqq11', 'qqq', 3, '173f2300-83a2-45ea-b613-09a894ab3be0', '8378118b-2727-46eb-82a9-c537b3d0fc7c', 'qwe', 123.00, 1);
INSERT INTO `entrust` VALUES (18, '索尼微单相机', '索尼微单相机', 24, 'b70a9afb-af55-46ea-b465-f557bed735da', '', '索尼微单相机', 100.00, 1);
COMMIT;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goods_id` int NOT NULL AUTO_INCREMENT COMMENT '物品ID',
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '物品名称',
  `goods_intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '物品简介',
  `goods_category_id` int NOT NULL COMMENT '物品分类id',
  `goods_cover_img` varchar(255) DEFAULT NULL COMMENT '物品主图片',
  `goods_carousel` varchar(500) DEFAULT NULL COMMENT '物品轮播图片',
  `goods_detail_content` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '物品详情',
  `goods_price` double(10,2) DEFAULT NULL COMMENT '物品价格',
  `stock_num` int DEFAULT NULL COMMENT '物品数量',
  `goods_status` int DEFAULT NULL COMMENT '物品上架状态（0下架，1上架）',
  `goods_score` double DEFAULT NULL COMMENT '物品评价分数',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
BEGIN;
INSERT INTO `goods` VALUES (1, 'iphone12 手机', '苹果最新款手机', 3, '12', '21', '苹果最新款手机', 100.00, 100, 1, 3.4);
INSERT INTO `goods` VALUES (2, 'iphone11 手机', 'iphone11', 3, '13', '21', '111', 100.00, 100, 1, 1.3);
INSERT INTO `goods` VALUES (3, 'iphone12 pro 手机', 'iphone', 3, '19', '21', '111', 100.00, 100, 1, 4.9);
INSERT INTO `goods` VALUES (4, 'qqq11', 'qqq', 3, '173f2300-83a2-45ea-b613-09a894ab3be0', '8378118b-2727-46eb-82a9-c537b3d0fc7c', 'qwe', 123.00, 1, 1, 3.7);
INSERT INTO `goods` VALUES (5, 'rtx3060显卡', 'rtx3060显卡', 18, '984fb033-eea8-4174-ae17-025012ed3a4e', 'e6d78f17-267a-4173-88e6-93c330a9bfc4', 'rtx3060显卡', 50.00, 1, 1, 3.7);
INSERT INTO `goods` VALUES (6, 'rtx3060显卡', 'rtx3060显卡', 18, '984fb033-eea8-4174-ae17-025012ed3a4e', 'e6d78f17-267a-4173-88e6-93c330a9bfc4', 'rtx3060显卡', 50.00, 1, 1, 3.7);
INSERT INTO `goods` VALUES (7, 'rtx3070显卡', 'rtx3070显卡', 18, '3f41b68f-60d4-48fd-a079-ed9354111bea', '5a586e79-eb60-484c-b0cd-d003c2efad8e', 'rtx3070显卡', 80.00, 1, 1, 3.7);
INSERT INTO `goods` VALUES (8, 'rtx3080显卡', 'rtx3080显卡', 18, '4e8b8334-cfb7-451a-a673-91ba66c88411', '562e46cb-80c6-4691-b7f2-17c3fec80774', 'rtx3080显卡', 100.00, 1, 1, 3.7);
INSERT INTO `goods` VALUES (9, 'rtx3080ti显卡', 'rtx3080ti显卡', 18, '8c5d5f5f-14e9-43b5-9d41-b69b31f2859a', '9ab74473-df00-45e5-a894-0432289d84ac', 'rtx3080ti显卡', 110.00, 1, 1, 3.7);
INSERT INTO `goods` VALUES (10, '6700xt 显卡', '6700xt显卡', 18, '6700', '76fdb9d7-d7af-467c-8b7f-075cfb108faf', '6700xt', 80.00, 1, 1, 3.7);
INSERT INTO `goods` VALUES (11, '6800xt 显卡', '6800xt显卡', 18, '6800', 'cce21e0c-743b-46c0-b87d-9fa66483414b', '6800xt', 90.00, 1, 1, 3.7);
INSERT INTO `goods` VALUES (12, '6800xt 显卡', '6800xt显卡', 18, '6800', 'cce21e0c-743b-46c0-b87d-9fa66483414b', '6800xt', 90.00, 1, 1, 3.7);
INSERT INTO `goods` VALUES (13, '6900xt 显卡', '6900xt显卡', 18, '6900', '0afe5d80-3d83-4ea9-8d5b-60e5b45337e5', '6900xt', 110.00, 1, 1, 3.7);
INSERT INTO `goods` VALUES (14, 'rtx3060显卡', 'rtx3060显卡', 18, '3060', 'cebf2e7b-2828-4616-b15f-62fb372f6788', 'rtx3060显卡', 80.00, 1, 1, 3.7);
INSERT INTO `goods` VALUES (15, 'rtx3060显卡', 'rtx3060显卡', 18, '3060', 'd92b8308-7e39-4352-9dac-bff0919b6e4d', 'rtx3060显卡', 90.00, 1, 1, 3.7);
INSERT INTO `goods` VALUES (16, 'rtx3080显卡', 'rtx3080显卡', 18, '3080', '7d6267ef-762d-4038-9ff7-d5248c3e5a0e', 'rtx3080显卡', 110.00, 1, 1, 3.7);
INSERT INTO `goods` VALUES (17, 'rtx3080显卡', 'rtx3080显卡', 18, '3080', 'de29277a-32d5-4117-8de7-81e9882c634b', 'rtx3080显卡', 110.00, 1, 1, 3.7);
INSERT INTO `goods` VALUES (18, 'rtx3090显卡', 'rtx3090显卡', 18, '3090', '736d461f-fe38-43e3-a850-889dadb4cbd4', 'rtx3090显卡', 130.00, 1, 1, 3.7);
INSERT INTO `goods` VALUES (19, 'ipad 2021 pro平板电脑', 'ipad 2021 pro平板电脑', 16, 'ipad', 'ipadDetail', 'ipad 2021 pro平板电脑', 110.00, 1, 1, 3.7);
INSERT INTO `goods` VALUES (20, '索尼微单 A74', '索尼微单 A74', 24, 'a7r4', 'a7r4Detail', '索尼微单 A74', 190.00, 1, 1, 3.7);
INSERT INTO `goods` VALUES (21, '富士微单相机 xt4', '富士微单相机 xt4', 24, 'fujixt4', 'xt4Detail', '富士微单相机 xt4', 150.00, 1, 1, 3.7);
COMMIT;

-- ----------------------------
-- Table structure for index_config
-- ----------------------------
DROP TABLE IF EXISTS `index_config`;
CREATE TABLE `index_config` (
  `config_id` int NOT NULL AUTO_INCREMENT COMMENT '首页配置项id',
  `config_name` varchar(100) DEFAULT NULL COMMENT '显示字符',
  `config_type` int DEFAULT NULL COMMENT '1-今日推荐 2-今日优惠 3-为您推荐',
  `goods_id` int NOT NULL COMMENT '物品id',
  `is_deleted` int DEFAULT NULL COMMENT '删除标识字段(0-未删除 1-已删除)',
  `goods_cover_img` varchar(255) DEFAULT NULL COMMENT '物品图片',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of index_config
-- ----------------------------
BEGIN;
INSERT INTO `index_config` VALUES (1, 'iphone', 1, 0, 0, '3');
INSERT INTO `index_config` VALUES (2, 'ipad', 1, 0, 0, '4');
INSERT INTO `index_config` VALUES (3, '索尼微单', 2, 0, 0, '23');
INSERT INTO `index_config` VALUES (4, '富士微单', 2, 0, 0, '22');
INSERT INTO `index_config` VALUES (5, '戴尔显示器', 3, 0, 0, '24');
INSERT INTO `index_config` VALUES (6, 'LG显示器', 3, 0, 0, '25');
INSERT INTO `index_config` VALUES (7, 'macbook', 3, 0, 0, 'macbook');
INSERT INTO `index_config` VALUES (8, 'macmini', 3, 0, 0, 'macmini');
INSERT INTO `index_config` VALUES (9, 'xdr', 3, 0, 0, 'xdr');
COMMIT;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `order_item_id` int NOT NULL AUTO_INCREMENT COMMENT '订单项id',
  `order_id` int DEFAULT NULL COMMENT '关联订单id',
  `goods_id` int DEFAULT NULL COMMENT '关联物品id',
  `goods_name` varchar(100) DEFAULT NULL COMMENT '物品名称',
  `goods_cover_img` varchar(255) DEFAULT NULL COMMENT '物品主图',
  `price` double(10,2) DEFAULT NULL COMMENT '物品价格',
  `goods_count` int DEFAULT NULL COMMENT '物品数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束租赁时间',
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_item
-- ----------------------------
BEGIN;
INSERT INTO `order_item` VALUES (3, 7, 1, 'iphone12', '1', 20970.00, 1, '2021-06-16 00:00:00', '2021-09-14 00:00:00', 10);
INSERT INTO `order_item` VALUES (4, 7, 1, 'iphone12', '1', 20000.00, 1, '2021-06-16 20:50:31', '2021-06-25 20:50:34', 10);
INSERT INTO `order_item` VALUES (5, 8, 1, 'iphone12', '12', 20970.00, 1, '2021-06-16 00:00:00', '2021-09-14 00:00:00', 11);
INSERT INTO `order_item` VALUES (6, 9, 2, 'iphone11', '13', 20970.00, 1, '2021-06-16 00:00:00', '2021-09-14 00:00:00', 12);
INSERT INTO `order_item` VALUES (7, 10, 3, 'iphone12 pro', '19', 12815.00, 1, '2021-06-16 00:00:00', '2021-08-10 00:00:00', 12);
INSERT INTO `order_item` VALUES (8, 11, 1, 'iphone12', '1', 11111.00, 1, '2021-06-17 11:27:51', '2021-06-17 11:27:54', 10);
INSERT INTO `order_item` VALUES (9, 11, 2, 'iphone11', '13', 13333.00, 1, '2021-06-17 11:28:17', '2021-06-17 11:28:20', 10);
INSERT INTO `order_item` VALUES (10, 12, 1, 'iphone12', '12', 20970.00, 1, '2021-06-17 00:00:00', '2021-09-15 00:00:00', 12);
INSERT INTO `order_item` VALUES (11, 19, 1, 'iphone12', '12', 1200.00, 1, '2021-06-17 00:00:00', '2021-06-29 00:00:00', NULL);
INSERT INTO `order_item` VALUES (12, 20, 1, 'iphone12', '12', 1200.00, 1, '2021-06-17 00:00:00', '2021-06-29 00:00:00', 12);
INSERT INTO `order_item` VALUES (13, 21, 1, 'iphone12', '12', 6000.00, 5, '2021-06-17 00:00:00', '2021-06-29 00:00:00', 12);
INSERT INTO `order_item` VALUES (14, 21, 2, 'iphone11', '13', 7200.00, 6, '2021-06-17 00:00:00', '2021-06-29 00:00:00', 12);
INSERT INTO `order_item` VALUES (15, 22, 5, 'rtx3060显卡', '984fb033-eea8-4174-ae17-025012ed3a4e', 9087.00, 1, '2021-06-18 00:00:00', '2021-07-27 00:00:00', 12);
INSERT INTO `order_item` VALUES (16, 23, 5, 'rtx3060显卡', '984fb033-eea8-4174-ae17-025012ed3a4e', 8621.00, 1, '2021-06-18 00:00:00', '2021-07-25 00:00:00', 12);
INSERT INTO `order_item` VALUES (17, 24, 7, 'rtx3070显卡', '3f41b68f-60d4-48fd-a079-ed9354111bea', 960.00, 1, '2021-06-17 00:00:00', '2021-06-29 00:00:00', 12);
INSERT INTO `order_item` VALUES (18, 25, 7, 'rtx3070显卡', '3f41b68f-60d4-48fd-a079-ed9354111bea', 960.00, 1, '2021-06-17 00:00:00', '2021-06-29 00:00:00', 12);
INSERT INTO `order_item` VALUES (19, 26, 7, 'rtx3070显卡', '3f41b68f-60d4-48fd-a079-ed9354111bea', 960.00, 1, '2021-06-17 00:00:00', '2021-06-29 00:00:00', 12);
INSERT INTO `order_item` VALUES (20, 27, 7, 'rtx3070显卡', '3f41b68f-60d4-48fd-a079-ed9354111bea', 2880.00, 3, '2021-06-17 00:00:00', '2021-06-29 00:00:00', 12);
INSERT INTO `order_item` VALUES (21, 28, 7, 'rtx3070显卡', '3f41b68f-60d4-48fd-a079-ed9354111bea', 960.00, 1, '2021-06-17 00:00:00', '2021-06-29 00:00:00', 12);
INSERT INTO `order_item` VALUES (22, 29, 1, 'iphone12 手机', '12', 4000.00, 5, '2021-06-21 00:00:00', '2021-06-29 00:00:00', 12);
INSERT INTO `order_item` VALUES (23, 30, 5, 'rtx3060显卡', '984fb033-eea8-4174-ae17-025012ed3a4e', 1500.00, 1, '2021-06-28 00:00:00', '2021-07-28 00:00:00', 12);
INSERT INTO `order_item` VALUES (24, 30, 5, 'rtx3060显卡', '984fb033-eea8-4174-ae17-025012ed3a4e', 1500.00, 1, '2021-06-28 00:00:00', '2021-07-28 00:00:00', 12);
INSERT INTO `order_item` VALUES (25, 31, 1, 'iphone12 手机', '12', 15000.00, 5, '2021-06-30 00:00:00', '2021-07-30 00:00:00', 11);
INSERT INTO `order_item` VALUES (26, 31, 2, 'iphone11 手机', '13', 15000.00, 5, '2021-06-30 00:00:00', '2021-07-30 00:00:00', 11);
INSERT INTO `order_item` VALUES (27, 32, 1, 'iphone12 手机', '12', 9000.00, 3, '2021-06-30 00:00:00', '2021-07-30 00:00:00', 15);
INSERT INTO `order_item` VALUES (28, 33, 1, 'iphone12 手机', '12', 2900.00, 1, '2021-06-30 00:00:00', '2021-07-29 00:00:00', 15);
INSERT INTO `order_item` VALUES (29, 34, 20, '索尼微单 A74', 'a7r4', 20970.00, 1, '2021-07-01 00:00:00', '2021-09-29 00:00:00', 15);
INSERT INTO `order_item` VALUES (30, 35, 19, 'ipad 2021 pro平板电脑', 'ipad', 3300.00, 1, '2021-06-30 00:00:00', '2021-07-30 00:00:00', 15);
INSERT INTO `order_item` VALUES (31, 35, 20, '索尼微单 A74', 'a7r4', 5700.00, 1, '2021-06-30 00:00:00', '2021-07-30 00:00:00', 15);
INSERT INTO `order_item` VALUES (32, 36, 5, 'rtx3060显卡', '984fb033-eea8-4174-ae17-025012ed3a4e', 20970.00, 1, '2021-07-01 00:00:00', '2021-09-29 00:00:00', 15);
COMMIT;

-- ----------------------------
-- Table structure for orderKK
-- ----------------------------
DROP TABLE IF EXISTS `orderKK`;
CREATE TABLE `orderKK` (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单表id',
  `order_num` varchar(50) DEFAULT NULL COMMENT '订单号',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `total_price` double(10,2) DEFAULT NULL COMMENT '订单总价',
  `pay_status` int DEFAULT NULL COMMENT '支付状态:0.未支付,1.支付成功,-1:支付失败',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `order_status` int DEFAULT NULL COMMENT '订单状态:0.待支付 1.已支付 2.配货完成 3:出库成功 4.交易成功 -1.手动关闭 -2.超时关闭 -3.商家关闭',
  `extra_info` varchar(100) DEFAULT NULL COMMENT '订单内容描述',
  `user_name` varchar(255) DEFAULT NULL COMMENT '收货人姓名',
  `user_phone` varchar(255) DEFAULT NULL COMMENT '收货人手机号',
  `user_address` varchar(255) DEFAULT NULL COMMENT '收货人地址',
  `is_deleted` int DEFAULT NULL COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束租赁时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderKK
-- ----------------------------
BEGIN;
INSERT INTO `orderKK` VALUES (7, '111', 10, 20970.00, 1, '2021-06-16 00:00:00', 1, NULL, 'test2', '111', '金湾区', NULL, '2021-06-16 00:00:00', '2021-09-14 00:00:00');
INSERT INTO `orderKK` VALUES (8, NULL, 11, 20970.00, 1, '2021-06-16 00:00:00', 1, NULL, 'test3', '111', 'qqq', NULL, '2021-06-16 00:00:00', '2021-09-14 00:00:00');
INSERT INTO `orderKK` VALUES (9, NULL, 12, 20970.00, 1, '2021-06-16 00:00:00', 1, NULL, 'test233', '15302634390', '金湾区', NULL, '2021-06-16 00:00:00', '2021-09-14 00:00:00');
INSERT INTO `orderKK` VALUES (10, NULL, 12, 12815.00, 1, '2021-06-16 00:00:00', 1, NULL, 'test233', '15302634390', '金湾区', NULL, '2021-06-16 00:00:00', '2021-08-10 00:00:00');
INSERT INTO `orderKK` VALUES (11, '113', 10, 20970.00, 1, '2021-06-17 11:26:05', 1, NULL, 'test2', '111', '金湾区', NULL, '2021-06-17 11:26:17', '2021-06-17 11:26:20');
INSERT INTO `orderKK` VALUES (12, '20210617185717383140', 12, 20970.00, 1, '2021-06-17 00:00:00', 1, NULL, 'test233', '15302634390', '金湾区', NULL, '2021-06-17 00:00:00', '2021-09-15 00:00:00');
INSERT INTO `orderKK` VALUES (19, '20210618002946125022', 12, 1200.00, 1, '2021-06-18 12:29:46', 1, NULL, 'test233', '15302634390', '金湾区', NULL, '2021-06-18 12:29:46', NULL);
INSERT INTO `orderKK` VALUES (20, '20210618003322619466', 12, 1200.00, 1, '2021-06-18 12:33:22', 1, NULL, 'test233', '15302634390', '金湾区', NULL, '2021-06-18 12:33:22', NULL);
INSERT INTO `orderKK` VALUES (21, '20210618003606983028', 12, 13200.00, 1, '2021-06-18 12:36:06', 1, NULL, 'test233', '15302634390', '金湾区', NULL, '2021-06-18 12:36:06', NULL);
INSERT INTO `orderKK` VALUES (22, '20210618013201115016', 12, 9087.00, 1, '2021-06-18 00:00:00', 1, NULL, 'test233', '15302634390', '金湾区', NULL, '2021-06-18 00:00:00', '2021-07-27 00:00:00');
INSERT INTO `orderKK` VALUES (23, '2021061801325558720', 12, 8621.00, 1, '2021-06-18 00:00:00', 1, NULL, 'test233', '15302634390', '金湾区', NULL, '2021-06-18 00:00:00', '2021-07-25 00:00:00');
INSERT INTO `orderKK` VALUES (24, '20210618013702386573', 12, 960.00, 1, '2021-06-18 01:37:02', 1, NULL, 'test233', '15302634390', '金湾区', NULL, '2021-06-18 01:37:02', NULL);
INSERT INTO `orderKK` VALUES (25, '20210618013703934027', 12, 960.00, 1, '2021-06-18 01:37:03', 1, NULL, 'test233', '15302634390', '金湾区', NULL, '2021-06-18 01:37:03', NULL);
INSERT INTO `orderKK` VALUES (26, '2021061801370464186', 12, 960.00, 1, '2021-06-18 01:37:04', 1, NULL, 'test233', '15302634390', '金湾区', NULL, '2021-06-18 01:37:04', NULL);
INSERT INTO `orderKK` VALUES (27, '20210618013803831934', 12, 2880.00, 1, '2021-06-18 01:38:03', 1, NULL, 'test233', '15302634390', '金湾区', NULL, '2021-06-18 01:38:03', NULL);
INSERT INTO `orderKK` VALUES (28, '20210618013841663674', 12, 960.00, 1, '2021-06-18 01:38:41', 1, NULL, 'test233', '15302634390', '金湾区', NULL, '2021-06-18 01:38:41', NULL);
INSERT INTO `orderKK` VALUES (29, '20210622223223241262', 12, 4000.00, 1, '2021-06-22 10:32:23', 1, NULL, 'test233', '15302634390', '金湾区', NULL, '2021-06-22 10:32:23', NULL);
INSERT INTO `orderKK` VALUES (30, '2021062915572843465', 12, 3000.00, 1, '2021-06-29 03:57:28', 1, NULL, 'test233', '15302634390', '金湾区', NULL, '2021-06-29 03:57:28', NULL);
INSERT INTO `orderKK` VALUES (31, '20210701162638816095', 11, 30000.00, 1, '2021-07-01 04:26:38', 1, NULL, 'test3', '66677788876', '333', NULL, '2021-07-01 04:26:38', NULL);
INSERT INTO `orderKK` VALUES (32, '20210701162950954060', 15, 9000.00, 1, '2021-07-01 04:29:50', 1, NULL, 'test232', '10086', 'qqq', NULL, '2021-07-01 04:29:50', NULL);
INSERT INTO `orderKK` VALUES (33, '20210701163001303859', 15, 2900.00, 1, '2021-07-01 04:30:01', 1, NULL, 'test232', '10086', 'qqq', NULL, '2021-07-01 04:30:01', NULL);
INSERT INTO `orderKK` VALUES (34, '20210701163027237993', 15, 20970.00, 1, '2021-07-01 00:00:00', 1, NULL, 'test232', '10086', 'qqq', NULL, '2021-07-01 00:00:00', '2021-09-29 00:00:00');
INSERT INTO `orderKK` VALUES (35, '20210701163057424313', 15, 9000.00, 1, '2021-07-01 04:30:57', 1, NULL, 'test232', '10086', 'qqq', NULL, '2021-07-01 04:30:57', NULL);
INSERT INTO `orderKK` VALUES (36, '20210701163116942651', 15, 20970.00, 1, '2021-07-01 00:00:00', 1, NULL, 'test232', '10086', 'qqq', NULL, '2021-07-01 00:00:00', '2021-09-29 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `url` varchar(255) DEFAULT NULL COMMENT '接口路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` VALUES (1, 'memberPermission', '');
INSERT INTO `permission` VALUES (2, 'adminPermission', NULL);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, 'member', '普通会员');
INSERT INTO `role` VALUES (2, 'admin', '管理员');
COMMIT;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL COMMENT '角色id',
  `permission_id` int DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
BEGIN;
INSERT INTO `role_permission` VALUES (1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户昵称',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话号码',
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录用户名',
  `passWord` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'MD5加密后的密码',
  `address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `createTime` datetime DEFAULT NULL COMMENT '注册时间',
  `salt` varchar(255) DEFAULT NULL COMMENT '加密盐',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'jjk', '男', '133', 'jjk', '123456', NULL, NULL, 'jjk');
INSERT INTO `user` VALUES (2, NULL, NULL, NULL, 'jjk332', '123456', NULL, NULL, NULL);
INSERT INTO `user` VALUES (3, NULL, NULL, NULL, 'jjk233', 'fe3cc6652d0e45d9afbac817cde3ead5', NULL, NULL, NULL);
INSERT INTO `user` VALUES (4, NULL, NULL, NULL, 'jjk233', 'ebd4a2f568c43c2efd338446ed97bab8', NULL, NULL, NULL);
INSERT INTO `user` VALUES (5, NULL, NULL, NULL, 'jjk233', '6fa88c345337dce86a7dca5b82a42567', NULL, NULL, 'e410dff56897cc44bf37e9cde02d4a3c');
INSERT INTO `user` VALUES (6, NULL, NULL, NULL, 'jjk233', 'ec91eb3b49981268db4431fdf689e2e9', NULL, NULL, '70fbbcdea6150cce9e2c1f2cc3fd576b');
INSERT INTO `user` VALUES (7, NULL, NULL, NULL, 'jjk333', '9cc4e2abfec86d7ff3986b83e41b2d9e', NULL, NULL, '370434581cb6b0cbdb36caf5392d6832');
INSERT INTO `user` VALUES (8, '111', '男', '133', '111', '6f34cfc9032f744f3f4646c37e3e3383', '金湾区', '2021-06-14 23:57:26', '948fe69811fb7fdb26e51eab890fdfd1');
INSERT INTO `user` VALUES (9, 'test1', '男', '13632926406', 'test1', '1d74cde43ded7b91b4f2eb5a6da43efe', '金湾区', '2021-06-15 00:05:29', '23e6e8b12170867dbd462a866a95a54e');
INSERT INTO `user` VALUES (10, 'test2', '男', '1112435', 'test2', 'b5b0aa536a42b1585e183d51991f3e35', '金湾区', '2021-06-15 00:20:23', '8aa297f32a77cc58467977e7f2cedb7e');
INSERT INTO `user` VALUES (11, 'test3', '男', '66677788876', 'test3', 'c8b3d9163f72f9d0bceee0e30262c16a', '333', '2021-06-16 23:42:45', 'f4836e5acf60176419a5a6726346a699');
INSERT INTO `user` VALUES (12, 'test233', '男', '15302634390', 'test233', 'eab10ebab97b024ef47549d553664abd', '金湾区', '2021-06-16 23:55:00', 'b535741de2dd33287fd882a9a1cacdb6');
INSERT INTO `user` VALUES (13, 'test23333', '男', '123', 'test23333', '85992e28350824b398c4ae9d3e21b66c', 'qqq', '2021-06-22 22:19:17', '7f63d1c9f1f00db2bb497bad1cedc456');
INSERT INTO `user` VALUES (14, 'test233333', '男', '1111111', 'test233333', 'ca855d84405295841d252468b3a44813', 'qqq', '2021-06-22 22:20:24', 'eae27637af7f7f21928f766521a154e0');
INSERT INTO `user` VALUES (15, 'test232', '男', '10086', 'test232', '0f7791c2f6ede4d964ad3bdbb195a678', 'qqq', '2021-06-22 22:21:08', 'e5e14c39ac108ffedb5024a5748cd4ba');
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL COMMENT '角色id',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (1, 1, 1, '111');
INSERT INTO `user_role` VALUES (2, 2, 12, NULL);
INSERT INTO `user_role` VALUES (3, 1, 13, NULL);
INSERT INTO `user_role` VALUES (4, 1, 14, NULL);
INSERT INTO `user_role` VALUES (5, 1, 15, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
