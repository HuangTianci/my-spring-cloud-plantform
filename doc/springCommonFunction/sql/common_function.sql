/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50637
Source Host           : localhost:3306
Source Database       : common_function

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2017-11-26 21:22:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for excel_mapping
-- ----------------------------
DROP TABLE IF EXISTS `excel_mapping`;
CREATE TABLE `excel_mapping` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL,
  `prop_name` varchar(64) NOT NULL,
  `prop_index` varchar(5) NOT NULL DEFAULT '100',
  `prop_type` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of excel_mapping
-- ----------------------------
INSERT INTO `excel_mapping` VALUES ('1', '姓名', 'tax', 'name', '1000', 'string');
INSERT INTO `excel_mapping` VALUES ('2', '身份证号', 'tax', 'idNumber', '200', 'string');
INSERT INTO `excel_mapping` VALUES ('3', '发放金额', 'tax', 'amount', '300', 'bigdecimal');
INSERT INTO `excel_mapping` VALUES ('4', '订单时间', 'tax', 'orderDate', '900', 'date');
INSERT INTO `excel_mapping` VALUES ('5', '本月累计发放金额', 'tax', 'totalAmount', '500', 'bigdecimal');
INSERT INTO `excel_mapping` VALUES ('6', '本月累计扣税金额', 'tax', 'totalDeductAmount', '600', 'bigdecimal');
INSERT INTO `excel_mapping` VALUES ('7', '本次扣税金额', 'tax', 'thisTimeDeductAmount', '700', 'bigdecimal');
INSERT INTO `excel_mapping` VALUES ('8', '本次实发金额', 'tax', 'thisTimePayAmount', '800', 'bigdecimal');

-- ----------------------------
-- Table structure for tax
-- ----------------------------
DROP TABLE IF EXISTS `tax`;
CREATE TABLE `tax` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `id_number` varchar(18) DEFAULT NULL,
  `amount` decimal(11,2) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `version` int(3) DEFAULT NULL,
  `total_amount` decimal(11,2) DEFAULT NULL,
  `total_deduct_amount` decimal(11,2) DEFAULT NULL,
  `this_time_deduct_amount` decimal(11,2) DEFAULT NULL,
  `this_time_pay_amount` decimal(11,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=980 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tax
-- ----------------------------
