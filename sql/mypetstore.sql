/*
 Navicat Premium Data Transfer

 Source Server         : mySQL
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : mypetstore

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 17/06/2020 04:46:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', 'admin', '111');
INSERT INTO `admin` VALUES ('yyx', 'yyx', '111');

-- ----------------------------
-- Table structure for cartitem
-- ----------------------------
DROP TABLE IF EXISTS `cartitem`;
CREATE TABLE `cartitem`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `itemid` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `quantity` int(10) NOT NULL,
  PRIMARY KEY (`username`, `itemid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cartitem
-- ----------------------------
INSERT INTO `cartitem` VALUES ('wx', 'EST-1', 7);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `categoryId` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`categoryId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('BIRDS', 'Birds');
INSERT INTO `category` VALUES ('CATS', 'Cats');
INSERT INTO `category` VALUES ('DOGS', 'Dogs');
INSERT INTO `category` VALUES ('FISH', 'Fish');
INSERT INTO `category` VALUES ('REPTILES', 'Reptiles');

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `itemid` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `productid` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` float(10, 2) NULL DEFAULT NULL,
  `attribute` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `quantity` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`itemid`) USING BTREE,
  INDEX `itemProd`(`productid`) USING BTREE,
  CONSTRAINT `fk_item_1` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('EST-1', 'FI-SW-01', NULL, 10.00, 'Large', NULL);
INSERT INTO `item` VALUES ('EST-10', 'K9-DL-01', NULL, 12.00, 'Spotted Adult Female', NULL);
INSERT INTO `item` VALUES ('EST-11', 'RP-SN-01', NULL, 12.00, 'Venomless', NULL);
INSERT INTO `item` VALUES ('EST-12', 'RP-SN-01', NULL, 12.00, 'Rattleless', NULL);
INSERT INTO `item` VALUES ('EST-13', 'RP-LI-02', NULL, 12.00, 'Green Adult', NULL);
INSERT INTO `item` VALUES ('EST-14', 'FL-DSH-01', NULL, 12.00, 'Tailless', NULL);
INSERT INTO `item` VALUES ('EST-15', 'FL-DSH-01', NULL, 12.00, 'With tail', NULL);
INSERT INTO `item` VALUES ('EST-16', 'FL-DLH-02', NULL, 12.00, 'Adult Female', NULL);
INSERT INTO `item` VALUES ('EST-17', 'FL-DLH-02', NULL, 12.00, 'Adult Male', NULL);
INSERT INTO `item` VALUES ('EST-18', 'AV-CB-01', NULL, 92.00, 'Adult Male', NULL);
INSERT INTO `item` VALUES ('EST-19', 'AV-SB-02', NULL, 2.00, 'Adult Male', NULL);
INSERT INTO `item` VALUES ('EST-2', 'FI-SW-01', NULL, 10.00, 'Small', NULL);
INSERT INTO `item` VALUES ('EST-20', 'FI-FW-02', NULL, 2.00, 'Adult Male', NULL);
INSERT INTO `item` VALUES ('EST-21', 'FI-FW-02', NULL, 1.00, 'Adult Female', NULL);
INSERT INTO `item` VALUES ('EST-22', 'K9-RT-02', NULL, 100.00, 'Adult Male', NULL);
INSERT INTO `item` VALUES ('EST-23', 'K9-RT-02', NULL, 100.00, 'Adult Female', NULL);
INSERT INTO `item` VALUES ('EST-24', 'K9-RT-02', NULL, 92.00, 'Adult Male', NULL);
INSERT INTO `item` VALUES ('EST-25', 'K9-RT-02', NULL, 90.00, 'Adult Female', NULL);
INSERT INTO `item` VALUES ('EST-26', 'K9-CW-01', NULL, 92.00, 'Adult Male', NULL);
INSERT INTO `item` VALUES ('EST-27', 'K9-CW-01', NULL, 90.00, 'Adult Female', NULL);
INSERT INTO `item` VALUES ('EST-28', 'K9-RT-01', NULL, 90.00, 'Adult Female', NULL);
INSERT INTO `item` VALUES ('EST-3', 'FI-SW-02', NULL, 12.00, 'Toothless', NULL);
INSERT INTO `item` VALUES ('EST-4', 'FI-FW-01', NULL, 12.00, 'Spotted', NULL);
INSERT INTO `item` VALUES ('EST-5', 'FI-FW-01', NULL, 12.00, 'Spotless', NULL);
INSERT INTO `item` VALUES ('EST-6', 'K9-BD-01', NULL, 12.00, 'Male Adult', NULL);
INSERT INTO `item` VALUES ('EST-7', 'K9-BD-01', NULL, 12.00, 'Female Puppy', NULL);
INSERT INTO `item` VALUES ('EST-8', 'K9-PO-02', NULL, 12.00, 'Male Puppy', NULL);
INSERT INTO `item` VALUES ('EST-9', 'K9-DL-01', NULL, 12.00, 'Spotless Male Puppy', NULL);

-- ----------------------------
-- Table structure for lineitem
-- ----------------------------
DROP TABLE IF EXISTS `lineitem`;
CREATE TABLE `lineitem`  (
  `orderid` int(11) NOT NULL,
  `linenum` int(11) NOT NULL,
  `itemid` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` float(10, 2) NOT NULL,
  PRIMARY KEY (`orderid`, `linenum`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `logid` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logdate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logtype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `objectid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`logid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 220 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES (13, 'j2ee', '2019-10-27 16:00:26', 'View Product', 'AV-SB-02');
INSERT INTO `log` VALUES (14, 'j2ee', '2019-10-27 16:00:29', 'Add Item', 'EST-19');
INSERT INTO `log` VALUES (15, 'j2ee', '2019-10-27 16:00:46', 'Pay Order', '1000');
INSERT INTO `log` VALUES (16, 'j2ee', '2019-10-27 16:14:42', 'View Item', 'EST-19');
INSERT INTO `log` VALUES (17, 'j2ee', '2019-10-27 16:14:52', 'Add Item', 'EST-19');
INSERT INTO `log` VALUES (18, 'j2ee', '2019-10-27 16:15:12', 'Pay Order', '1000');
INSERT INTO `log` VALUES (19, 'j2ee', '2019-10-27 16:24:09', 'View Product', 'K9-RT-01');
INSERT INTO `log` VALUES (20, 'j2ee', '2019-10-27 16:24:11', 'Add Item', 'EST-28');
INSERT INTO `log` VALUES (21, 'j2ee', '2019-10-27 16:24:24', 'Pay Order', '1000');
INSERT INTO `log` VALUES (22, 'j2ee', '2019-10-27 16:52:55', 'View Category', 'REPTILES');
INSERT INTO `log` VALUES (23, 'j2ee', '2019-10-27 16:52:57', 'View Product', 'RP-SN-01');
INSERT INTO `log` VALUES (24, 'j2ee', '2019-10-27 16:53:00', 'Add Item', 'EST-12');
INSERT INTO `log` VALUES (25, 'j2ee', '2019-10-27 16:53:21', 'Pay Order', '1000');
INSERT INTO `log` VALUES (26, 'j2ee', '2019-10-27 16:55:37', 'View Category', 'BIRDS');
INSERT INTO `log` VALUES (27, 'j2ee', '2019-10-27 16:55:39', 'View Product', 'AV-SB-02');
INSERT INTO `log` VALUES (28, 'j2ee', '2019-10-27 16:55:41', 'Add Item', 'EST-19');
INSERT INTO `log` VALUES (29, 'j2ee', '2019-10-27 17:11:31', 'View Category', 'BIRDS');
INSERT INTO `log` VALUES (30, 'j2ee', '2019-10-27 17:11:34', 'View Product', 'AV-SB-02');
INSERT INTO `log` VALUES (31, 'j2ee', '2019-10-27 17:11:36', 'Add Item', 'EST-19');
INSERT INTO `log` VALUES (32, 'j2ee', '2019-10-27 17:11:43', 'Pay Order', '1000');
INSERT INTO `log` VALUES (33, 'j2ee', '2019-10-27 17:13:51', 'View Category', 'DOGS');
INSERT INTO `log` VALUES (34, 'j2ee', '2019-10-27 17:13:54', 'View Product', 'K9-DL-01');
INSERT INTO `log` VALUES (35, 'j2ee', '2019-10-27 17:13:57', 'Add Item', 'EST-9');
INSERT INTO `log` VALUES (36, 'j2ee', '2019-10-27 17:16:42', 'Pay Order', '1000');
INSERT INTO `log` VALUES (37, 'j2ee', '2019-10-27 17:18:50', 'View Category', 'REPTILES');
INSERT INTO `log` VALUES (38, 'j2ee', '2019-10-27 17:18:52', 'View Product', 'RP-SN-01');
INSERT INTO `log` VALUES (39, 'j2ee', '2019-10-27 17:18:56', 'Add Item', 'EST-12');
INSERT INTO `log` VALUES (40, 'j2ee', '2019-10-27 17:19:57', 'Pay Order', '1000');
INSERT INTO `log` VALUES (41, 'j2ee', '2019-10-27 17:22:50', 'Pay Order', '1000');
INSERT INTO `log` VALUES (42, 'j2ee', '2019-10-27 17:35:49', 'Pay Order', '1000');
INSERT INTO `log` VALUES (43, 'j2ee', '2019-10-27 17:40:12', 'Pay Order', '1000');
INSERT INTO `log` VALUES (44, 'j2ee', '2019-10-27 17:57:45', 'View Category', 'REPTILES');
INSERT INTO `log` VALUES (45, 'j2ee', '2019-10-27 17:57:48', 'View Product', 'RP-SN-01');
INSERT INTO `log` VALUES (46, 'j2ee', '2019-10-27 17:58:01', 'View Item', 'EST-12');
INSERT INTO `log` VALUES (47, 'j2ee', '2019-10-27 17:58:03', 'Add Item', 'EST-12');
INSERT INTO `log` VALUES (48, 'j2ee', '2019-10-27 17:58:10', 'Create Order', '1000');
INSERT INTO `log` VALUES (49, 'j2ee', '2019-10-27 17:58:12', 'Pay Order', '1000');
INSERT INTO `log` VALUES (50, 'j2ee', '2019-10-27 18:07:20', 'View Category', 'BIRDS');
INSERT INTO `log` VALUES (51, 'j2ee', '2019-10-27 18:07:21', 'View Product', 'AV-SB-02');
INSERT INTO `log` VALUES (52, 'j2ee', '2019-10-27 18:07:23', 'View Item', 'EST-19');
INSERT INTO `log` VALUES (53, 'j2ee', '2019-10-27 18:07:25', 'Add Item', 'EST-19');
INSERT INTO `log` VALUES (54, 'j2ee', '2019-10-27 18:07:32', 'Create Order', '1001');
INSERT INTO `log` VALUES (55, 'j2ee', '2019-10-27 18:07:35', 'Pay Order', '1001');
INSERT INTO `log` VALUES (56, 'j2ee', '2019-10-27 18:22:41', 'View Category', 'DOGS');
INSERT INTO `log` VALUES (57, 'j2ee', '2019-10-27 18:22:43', 'View Product', 'K9-RT-02');
INSERT INTO `log` VALUES (58, 'j2ee', '2019-10-27 18:22:47', 'Add Item', 'EST-25');
INSERT INTO `log` VALUES (59, 'j2ee', '2019-10-27 18:22:52', 'Create Order', '1002');
INSERT INTO `log` VALUES (60, 'j2ee', '2019-10-27 18:22:55', 'Pay Order', '1002');
INSERT INTO `log` VALUES (61, 'j2ee', '2019-10-27 18:37:24', 'View Category', 'REPTILES');
INSERT INTO `log` VALUES (62, 'j2ee', '2019-10-27 18:37:26', 'View Product', 'RP-SN-01');
INSERT INTO `log` VALUES (63, 'j2ee', '2019-10-27 18:37:31', 'Add Item', 'EST-12');
INSERT INTO `log` VALUES (64, 'j2ee', '2019-10-27 18:37:35', 'View Category', 'BIRDS');
INSERT INTO `log` VALUES (65, 'j2ee', '2019-10-27 18:37:36', 'View Product', 'AV-SB-02');
INSERT INTO `log` VALUES (66, 'j2ee', '2019-10-27 18:37:38', 'Add Item', 'EST-19');
INSERT INTO `log` VALUES (67, 'j2ee', '2019-10-27 18:37:40', 'View Category', 'FISH');
INSERT INTO `log` VALUES (68, 'j2ee', '2019-10-27 18:37:41', 'View Product', 'FI-FW-02');
INSERT INTO `log` VALUES (69, 'j2ee', '2019-10-27 18:37:43', 'Add Item', 'EST-20');
INSERT INTO `log` VALUES (70, 'j2ee', '2019-10-27 18:37:51', 'Create Order', '1003');
INSERT INTO `log` VALUES (71, 'j2ee', '2019-10-27 18:37:54', 'Pay Order', '1003');
INSERT INTO `log` VALUES (72, 'j2ee', '2019-10-27 19:21:11', 'View Category', 'CATS');
INSERT INTO `log` VALUES (73, 'j2ee', '2019-10-27 19:21:13', 'View Product', 'FL-DSH-01');
INSERT INTO `log` VALUES (74, 'j2ee', '2019-10-27 19:21:15', 'Add Item', 'EST-15');
INSERT INTO `log` VALUES (75, 'j2ee', '2019-10-27 19:21:22', 'Create Order', '1004');
INSERT INTO `log` VALUES (76, 'j2ee', '2019-10-27 19:21:40', 'Create Order', '1005');
INSERT INTO `log` VALUES (77, 'j2ee', '2019-10-27 19:22:09', 'Pay Order', '1005');
INSERT INTO `log` VALUES (78, 'j2ee', '2019-10-27 20:43:49', 'View Category', 'BIRDS');
INSERT INTO `log` VALUES (79, 'j2ee', '2019-10-27 20:43:51', 'View Product', 'AV-SB-02');
INSERT INTO `log` VALUES (80, 'j2ee', '2019-10-27 20:43:53', 'Add Item', 'EST-19');
INSERT INTO `log` VALUES (81, 'j2ee', '2019-10-27 20:45:26', 'Create Order', '1006');
INSERT INTO `log` VALUES (82, 'j2ee', '2019-10-28 15:04:54', 'View Category', 'FISH');
INSERT INTO `log` VALUES (83, 'j2ee', '2019-10-28 15:04:57', 'View Product', 'FI-SW-01');
INSERT INTO `log` VALUES (84, 'j2ee', '2019-10-28 15:05:00', 'Add Item', 'EST-1');
INSERT INTO `log` VALUES (85, 'j2ee', '2019-10-28 15:07:52', 'View Category', 'BIRDS');
INSERT INTO `log` VALUES (86, 'j2ee', '2019-10-28 15:08:08', 'View Category', 'FISH');
INSERT INTO `log` VALUES (87, 'j2ee', '2019-10-28 15:08:10', 'View Product', 'FI-SW-02');
INSERT INTO `log` VALUES (88, 'j2ee', '2019-10-28 15:08:12', 'Add Item', 'EST-3');
INSERT INTO `log` VALUES (89, 'j2ee', '2019-10-28 15:33:47', 'View Category', 'REPTILES');
INSERT INTO `log` VALUES (90, 'j2ee', '2019-10-28 15:33:51', 'View Product', 'RP-SN-01');
INSERT INTO `log` VALUES (91, 'j2ee', '2019-10-28 15:33:53', 'Add Item', 'EST-12');
INSERT INTO `log` VALUES (92, 'j2ee', '2019-10-28 23:51:46', 'View Category', 'DOGS');
INSERT INTO `log` VALUES (93, 'j2ee', '2019-10-28 23:51:49', 'View Product', 'K9-RT-02');
INSERT INTO `log` VALUES (94, 'j2ee', '2019-10-28 23:51:52', 'Add Item', 'EST-23');
INSERT INTO `log` VALUES (95, 'j2ee', '2019-10-28 23:53:31', 'View Category', 'BIRDS');
INSERT INTO `log` VALUES (96, 'j2ee', '2019-10-28 23:53:33', 'View Product', 'AV-SB-02');
INSERT INTO `log` VALUES (97, 'j2ee', '2019-10-28 23:53:35', 'Add Item', 'EST-19');
INSERT INTO `log` VALUES (98, 'j2ee', '2019-10-28 23:54:50', 'View Category', 'CATS');
INSERT INTO `log` VALUES (99, 'j2ee', '2019-10-28 23:54:52', 'View Product', 'FL-DSH-01');
INSERT INTO `log` VALUES (100, 'j2ee', '2019-10-28 23:54:55', 'Add Item', 'EST-15');
INSERT INTO `log` VALUES (101, 'j2ee', '2019-10-28 23:57:46', 'View Category', 'CATS');
INSERT INTO `log` VALUES (102, 'j2ee', '2019-10-28 23:57:48', 'View Product', 'FL-DSH-01');
INSERT INTO `log` VALUES (103, 'j2ee', '2019-10-28 23:57:51', 'Add Item', 'EST-15');
INSERT INTO `log` VALUES (104, 'j2ee', '2019-10-29 00:03:05', 'View Category', 'BIRDS');
INSERT INTO `log` VALUES (105, 'j2ee', '2019-10-29 00:12:23', 'View Category', 'DOGS');
INSERT INTO `log` VALUES (106, 'j2ee', '2019-10-29 00:39:57', 'Create Order', '1007');
INSERT INTO `log` VALUES (107, 'j2ee', '2019-10-29 03:29:11', 'Create Order', '1008');
INSERT INTO `log` VALUES (108, 'j2ee', '2019-10-29 08:36:00', 'Create Order', '1009');
INSERT INTO `log` VALUES (109, 'j2ee', '2019-10-29 08:41:09', 'View Category', 'DOGS');
INSERT INTO `log` VALUES (110, 'j2ee', '2019-10-29 08:41:36', 'Create Order', '1010');
INSERT INTO `log` VALUES (111, 'j2ee', '2019-10-29 08:45:50', 'Create Order', '1011');
INSERT INTO `log` VALUES (112, 'j2ee', '2019-10-29 08:45:54', 'Pay Order', '1011');
INSERT INTO `log` VALUES (113, 'j2ee', '2019-10-29 10:43:52', 'View Category', 'BIRDS');
INSERT INTO `log` VALUES (114, 'j2ee', '2019-10-29 10:43:55', 'View Product', 'AV-SB-02');
INSERT INTO `log` VALUES (115, 'j2ee', '2019-10-29 10:43:57', 'Add Item', 'EST-19');
INSERT INTO `log` VALUES (116, 'j2ee', '2019-10-29 10:44:00', 'View Item', 'EST-19');
INSERT INTO `log` VALUES (117, 'j2ee', '2019-10-29 10:44:02', 'Add Item', 'EST-19');
INSERT INTO `log` VALUES (118, 'j2ee', '2019-10-29 10:44:04', 'View Item', 'EST-19');
INSERT INTO `log` VALUES (119, 'j2ee', '2019-10-29 10:44:08', 'View Product', 'AV-SB-02');
INSERT INTO `log` VALUES (120, 'j2ee', '2019-10-29 10:44:11', 'View Category', 'BIRDS');
INSERT INTO `log` VALUES (121, 'j2ee', '2019-10-29 10:44:13', 'View Product', 'AV-CB-01');
INSERT INTO `log` VALUES (122, 'j2ee', '2019-10-29 10:44:16', 'Add Item', 'EST-18');
INSERT INTO `log` VALUES (123, 'j2ee', '2019-10-29 10:44:19', 'View Category', 'REPTILES');
INSERT INTO `log` VALUES (124, 'j2ee', '2019-10-29 10:44:20', 'View Product', 'RP-SN-01');
INSERT INTO `log` VALUES (125, 'j2ee', '2019-10-29 10:44:22', 'Add Item', 'EST-12');
INSERT INTO `log` VALUES (126, 'j2ee', '2019-10-29 10:44:25', 'View Category', 'BIRDS');
INSERT INTO `log` VALUES (127, 'j2ee', '2019-10-29 10:44:27', 'View Product', 'AV-CB-01');
INSERT INTO `log` VALUES (128, 'j2ee', '2019-10-29 10:44:29', 'Add Item', 'EST-18');
INSERT INTO `log` VALUES (129, 'j2ee', '2019-10-29 10:44:50', 'Remove Item', 'EST-18');
INSERT INTO `log` VALUES (130, 'j2ee', '2019-10-29 10:44:51', 'Remove Item', 'EST-19');
INSERT INTO `log` VALUES (131, 'j2ee', '2019-10-29 10:54:21', 'View Product', 'FI-SW-01');
INSERT INTO `log` VALUES (132, 'j2ee', '2019-10-29 10:54:24', 'Add Item', 'EST-2');
INSERT INTO `log` VALUES (133, 'j2ee', '2019-10-29 10:58:26', 'View Product', 'FI-SW-01');
INSERT INTO `log` VALUES (134, 'j2ee', '2019-10-29 10:58:32', 'Add Item', 'EST-1');
INSERT INTO `log` VALUES (135, 'j2ee', '2019-10-29 11:02:29', 'View Category', 'CATS');
INSERT INTO `log` VALUES (136, 'j2ee', '2019-10-29 11:08:34', 'View Product', 'K9-CW-01');
INSERT INTO `log` VALUES (137, 'j2ee', '2019-10-29 11:08:38', 'Add Item', 'EST-26');
INSERT INTO `log` VALUES (138, 'j2ee', '2019-10-29 11:09:56', 'Add Item', 'EST-26');
INSERT INTO `log` VALUES (139, 'j2ee', '2019-10-29 11:14:56', 'View Product', 'K9-CW-01');
INSERT INTO `log` VALUES (140, 'j2ee', '2019-10-29 11:14:58', 'View Item', 'EST-27');
INSERT INTO `log` VALUES (141, 'j2ee', '2019-10-29 11:15:00', 'Add Item', 'EST-27');
INSERT INTO `log` VALUES (142, 'j2ee', '2019-10-29 11:18:50', 'View Category', 'BIRDS');
INSERT INTO `log` VALUES (143, 'j2ee', '2019-10-29 11:18:52', 'View Product', 'AV-CB-01');
INSERT INTO `log` VALUES (144, 'j2ee', '2019-10-29 11:18:54', 'View Item', 'EST-18');
INSERT INTO `log` VALUES (145, 'j2ee', '2019-10-29 11:18:57', 'Add Item', 'EST-18');
INSERT INTO `log` VALUES (146, 'j2ee', '2019-10-29 11:22:38', 'View Product', 'K9-CW-01');
INSERT INTO `log` VALUES (147, 'j2ee', '2019-10-29 11:22:41', 'Add Item', 'EST-27');
INSERT INTO `log` VALUES (148, 'j2ee', '2019-10-29 11:39:38', 'View Product', 'K9-CW-01');
INSERT INTO `log` VALUES (149, 'j2ee', '2019-10-29 11:39:41', 'View Item', 'EST-26');
INSERT INTO `log` VALUES (150, 'j2ee', '2019-10-30 23:44:59', 'Create Order', '1012');
INSERT INTO `log` VALUES (151, 'j2ee', '2019-10-30 23:45:06', 'Pay Order', '1012');
INSERT INTO `log` VALUES (152, 'j2ee', '2019-10-31 00:14:31', 'View Category', 'BIRDS');
INSERT INTO `log` VALUES (153, 'j2ee', '2019-10-31 00:14:33', 'View Product', 'AV-SB-02');
INSERT INTO `log` VALUES (154, 'j2ee', '2019-10-31 00:14:34', 'Add Item', 'EST-19');
INSERT INTO `log` VALUES (155, 'j2ee', '2019-10-31 00:14:37', 'View Category', 'BIRDS');
INSERT INTO `log` VALUES (156, 'j2ee', '2019-10-31 00:14:38', 'View Product', 'AV-CB-01');
INSERT INTO `log` VALUES (157, 'j2ee', '2019-10-31 00:14:41', 'Add Item', 'EST-18');
INSERT INTO `log` VALUES (158, 'j2ee', '2019-10-31 00:14:43', 'View Category', 'CATS');
INSERT INTO `log` VALUES (159, 'j2ee', '2019-10-31 00:14:44', 'View Product', 'FL-DLH-02');
INSERT INTO `log` VALUES (160, 'j2ee', '2019-10-31 00:14:46', 'View Item', 'EST-17');
INSERT INTO `log` VALUES (161, 'j2ee', '2019-10-31 00:14:48', 'Add Item', 'EST-17');
INSERT INTO `log` VALUES (162, 'j2ee', '2019-10-31 01:08:17', 'Create Order', '1013');
INSERT INTO `log` VALUES (163, 'j2ee', '2019-10-31 01:08:31', 'Pay Order', '1013');
INSERT INTO `log` VALUES (164, 'j2ee', '2019-10-31 01:54:58', 'View Category', 'REPTILES');
INSERT INTO `log` VALUES (165, 'j2ee', '2019-10-31 01:55:00', 'View Product', 'RP-LI-02');
INSERT INTO `log` VALUES (166, 'j2ee', '2019-10-31 01:55:03', 'Add Item', 'EST-13');
INSERT INTO `log` VALUES (167, 'j2ee', '2019-10-31 01:55:19', 'Remove Item', 'EST-13');
INSERT INTO `log` VALUES (168, 'j2ee', '2019-10-31 01:55:23', 'View Category', 'CATS');
INSERT INTO `log` VALUES (169, 'j2ee', '2019-10-31 01:55:24', 'View Product', 'FL-DSH-01');
INSERT INTO `log` VALUES (170, 'j2ee', '2019-10-31 01:55:26', 'Add Item', 'EST-15');
INSERT INTO `log` VALUES (171, 'j2ee', '2019-10-31 02:25:47', 'View Category', 'REPTILES');
INSERT INTO `log` VALUES (172, 'j2ee', '2019-10-31 02:25:48', 'View Product', 'RP-SN-01');
INSERT INTO `log` VALUES (173, 'j2ee', '2019-10-31 02:25:51', 'Add Item', 'EST-12');
INSERT INTO `log` VALUES (174, 'j2ee', '2019-10-31 02:25:58', 'Create Order', '1014');
INSERT INTO `log` VALUES (175, 'j2ee', '2019-10-31 02:26:01', 'Pay Order', '1014');
INSERT INTO `log` VALUES (176, 'j2ee', '2019-10-31 08:39:43', 'View Category', 'CATS');
INSERT INTO `log` VALUES (177, 'j2ee', '2019-10-31 08:39:45', 'View Product', 'FL-DSH-01');
INSERT INTO `log` VALUES (178, 'j2ee', '2019-10-31 08:39:48', 'Add Item', 'EST-14');
INSERT INTO `log` VALUES (179, 'j2ee', '2019-10-31 08:39:52', 'View Category', 'DOGS');
INSERT INTO `log` VALUES (180, 'j2ee', '2019-10-31 08:39:54', 'View Product', 'K9-CW-01');
INSERT INTO `log` VALUES (181, 'j2ee', '2019-10-31 08:39:56', 'Add Item', 'EST-27');
INSERT INTO `log` VALUES (182, 'j2ee', '2019-10-31 08:40:06', 'Create Order', '1015');
INSERT INTO `log` VALUES (183, 'j2ee', '2019-10-31 08:40:10', 'Pay Order', '1015');
INSERT INTO `log` VALUES (184, 'j2ee', '2019-11-01 22:41:50', 'Create Order', '1016');
INSERT INTO `log` VALUES (185, 'j2ee', '2019-11-01 22:41:53', 'Pay Order', '1016');
INSERT INTO `log` VALUES (186, 'j2ee', '2019-11-30 03:02:35', 'View Category', 'REPTILES');
INSERT INTO `log` VALUES (187, 'j2ee', '2019-11-30 03:02:38', 'View Product', 'RP-SN-01');
INSERT INTO `log` VALUES (188, 'j2ee', '2019-11-30 03:02:40', 'Add Item', 'EST-12');
INSERT INTO `log` VALUES (189, 'j2ee', '2019-11-30 03:21:06', 'Create Order', '1017');
INSERT INTO `log` VALUES (190, 'j2ee', '2019-11-30 03:21:45', 'Create Order', '1018');
INSERT INTO `log` VALUES (191, 'j2ee', '2020-03-29 17:57:55', 'View Category', 'BIRDS');
INSERT INTO `log` VALUES (192, 'j2ee', '2020-03-29 17:57:57', 'View Product', 'AV-CB-01');
INSERT INTO `log` VALUES (193, 'j2ee', '2020-03-29 17:58:00', 'Add Item', 'EST-18');
INSERT INTO `log` VALUES (194, 'j2ee', '2020-03-29 17:58:12', 'Create Order', '1019');
INSERT INTO `log` VALUES (195, 'j2ee', '2020-03-29 17:58:16', 'Pay Order', '1019');
INSERT INTO `log` VALUES (196, 'j2ee', '2020-03-29 17:58:38', 'View Category', 'FISH');
INSERT INTO `log` VALUES (197, 'j2ee', '2020-03-29 17:58:40', 'View Product', 'FI-FW-02');
INSERT INTO `log` VALUES (198, 'j2ee', '2020-03-29 17:58:42', 'Add Item', 'EST-21');
INSERT INTO `log` VALUES (199, 'j2ee', '2020-03-29 17:58:52', 'Create Order', '1020');
INSERT INTO `log` VALUES (200, 'j2ee', '2020-03-29 17:58:54', 'Pay Order', '1020');
INSERT INTO `log` VALUES (201, 'j2ee', '2020-03-29 22:51:55', 'View Category', 'FISH');
INSERT INTO `log` VALUES (202, 'j2ee', '2020-03-29 22:52:46', 'View Category', 'FISH');
INSERT INTO `log` VALUES (203, 'j2ee', '2020-03-29 22:52:52', 'View Product', 'K9-RT-02');
INSERT INTO `log` VALUES (204, 'j2ee', '2020-03-29 22:53:17', 'View Category', 'FISH');
INSERT INTO `log` VALUES (205, 'j2ee', '2020-03-29 22:53:19', 'View Category', 'BIRDS');
INSERT INTO `log` VALUES (206, 'j2ee', '2020-03-29 22:53:22', 'View Category', 'DOGS');
INSERT INTO `log` VALUES (207, 'j2ee', '2020-03-29 22:53:30', 'View Product', 'K9-RT-02');
INSERT INTO `log` VALUES (208, 'j2ee', '2020-06-03 21:58:13', 'View Category', 'BIRDS');
INSERT INTO `log` VALUES (209, 'j2ee', '2020-06-04 20:02:39', 'View Product', 'K9-RT-02');
INSERT INTO `log` VALUES (210, 'j2ee', '2020-06-04 20:02:41', 'Add Item', 'EST-23');
INSERT INTO `log` VALUES (211, 'j2ee', '2020-06-04 20:07:06', 'Create Order', '1021');
INSERT INTO `log` VALUES (212, 'j2ee', '2020-06-04 20:07:09', 'Pay Order', '1021');
INSERT INTO `log` VALUES (213, 'j2ee', '2020-06-04 20:08:37', 'View Category', 'CATS');
INSERT INTO `log` VALUES (214, 'j2ee', '2020-06-04 20:08:39', 'View Product', 'FL-DSH-01');
INSERT INTO `log` VALUES (215, 'j2ee', '2020-06-04 20:08:42', 'Add Item', 'EST-15');
INSERT INTO `log` VALUES (216, 'j2ee', '2020-06-04 20:09:06', 'Create Order', '1022');
INSERT INTO `log` VALUES (217, 'j2ee', '2020-06-04 20:09:09', 'Pay Order', '1022');
INSERT INTO `log` VALUES (218, 'j2ee', '2020-06-04 20:09:37', 'View Category', 'REPTILES');
INSERT INTO `log` VALUES (219, 'j2ee', '2020-06-04 20:09:39', 'View Product', 'RP-SN-01');
INSERT INTO `log` VALUES (220, 'j2ee', '2020-06-04 20:09:40', 'Add Item', 'EST-12');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `orderid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderdate` date NULL DEFAULT NULL,
  `totalprice` float(10, 2) NULL DEFAULT NULL,
  `paid` int(2) NULL DEFAULT NULL,
  `sendTo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`orderid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (999, 'j2ee', '2019-10-21', 19.20, NULL, NULL);
INSERT INTO `orders` VALUES (1000, 'j2ee', '2019-10-27', 39.50, NULL, NULL);
INSERT INTO `orders` VALUES (1001, 'j2ee', '2019-10-27', 23.50, NULL, NULL);
INSERT INTO `orders` VALUES (1002, 'j2ee', '2019-10-27', 23.50, NULL, NULL);
INSERT INTO `orders` VALUES (1003, 'j2ee', '2019-10-27', 15.50, NULL, NULL);
INSERT INTO `orders` VALUES (1004, 'j2ee', '2019-10-28', 276.99, NULL, NULL);
INSERT INTO `orders` VALUES (1005, 'j2ee', '2019-10-28', 276.99, NULL, NULL);
INSERT INTO `orders` VALUES (1006, 'j2ee', '2019-10-29', 276.99, NULL, NULL);
INSERT INTO `orders` VALUES (1007, 'j2ee', '2019-10-29', 276.99, NULL, NULL);
INSERT INTO `orders` VALUES (1008, 'j2ee', '2019-10-29', 276.99, NULL, NULL);
INSERT INTO `orders` VALUES (1009, 'j2ee', '2019-10-30', 963.87, NULL, NULL);
INSERT INTO `orders` VALUES (1010, 'j2ee', '2019-10-30', 302.50, NULL, NULL);
INSERT INTO `orders` VALUES (1011, 'j2ee', '2019-10-30', 18.50, NULL, NULL);
INSERT INTO `orders` VALUES (1012, 'j2ee', '2019-10-31', 58.50, NULL, NULL);
INSERT INTO `orders` VALUES (1013, 'j2ee', '2019-11-01', 621.16, NULL, NULL);
INSERT INTO `orders` VALUES (1014, 'j2ee', '2019-11-29', 0.00, NULL, NULL);
INSERT INTO `orders` VALUES (1015, 'j2ee', '2019-11-29', 0.00, NULL, NULL);
INSERT INTO `orders` VALUES (1016, 'j2ee', '2020-03-29', 0.00, NULL, NULL);
INSERT INTO `orders` VALUES (1017, 'j2ee', '2020-03-29', 23.79, NULL, NULL);
INSERT INTO `orders` VALUES (1018, 'j2ee', '2020-06-04', 360.00, NULL, NULL);
INSERT INTO `orders` VALUES (1019, 'j2ee', '2020-06-04', 380.00, NULL, NULL);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `productid` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `categoryid` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`productid`) USING BTREE,
  INDEX `productCat`(`categoryid`) USING BTREE,
  INDEX `productName`(`name`) USING BTREE,
  CONSTRAINT `fk_product_1` FOREIGN KEY (`categoryid`) REFERENCES `category` (`categoryId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('AV-CB-01', 'BIRDS', 'Amazon Parrot', 'Great companion for up to 75 years');
INSERT INTO `product` VALUES ('AV-SB-02', 'BIRDS', 'Finch', 'Great stress reliever');
INSERT INTO `product` VALUES ('FI-FW-01', 'FISH', 'Koi', 'Fresh Water fish from Japan');
INSERT INTO `product` VALUES ('FI-FW-02', 'FISH', 'Goldfish', 'Fresh Water fish from China');
INSERT INTO `product` VALUES ('FI-SW-01', 'FISH', 'Angelfish', 'Salt Water fish from Australia');
INSERT INTO `product` VALUES ('FI-SW-02', 'FISH', 'Tiger Shark', 'Salt Water fish from Australia');
INSERT INTO `product` VALUES ('FL-DLH-02', 'CATS', 'Persian', 'Friendly house cat, doubles as a princess');
INSERT INTO `product` VALUES ('FL-DSH-01', 'CATS', 'Manx', 'Great for reducing mouse populations');
INSERT INTO `product` VALUES ('K9-BD-01', 'DOGS', 'Bulldog', 'Friendly dog from England');
INSERT INTO `product` VALUES ('K9-CW-01', 'DOGS', 'Chihuahua', 'Great companion dog');
INSERT INTO `product` VALUES ('K9-DL-01', 'DOGS', 'Dalmation', 'Great dog for a Fire Station');
INSERT INTO `product` VALUES ('K9-PO-02', 'DOGS', 'Poodle', 'Cute dog from France');
INSERT INTO `product` VALUES ('K9-RT-01', 'DOGS', 'Golden Retriever', 'Great family dog');
INSERT INTO `product` VALUES ('K9-RT-02', 'DOGS', 'Labrador Retriever', 'Great hunting dog');
INSERT INTO `product` VALUES ('RP-LI-02', 'REPTILES', 'Iguana', 'Friendly green friend');
INSERT INTO `product` VALUES ('RP-SN-01', 'REPTILES', 'Rattlesnake', 'Doubles as a watch dog');

-- ----------------------------
-- Table structure for profile
-- ----------------------------
DROP TABLE IF EXISTS `profile`;
CREATE TABLE `profile`  (
  `userid` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `langpref` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `favcategory` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mylistopt` tinyint(1) NULL DEFAULT NULL,
  `banneropt` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of profile
-- ----------------------------
INSERT INTO `profile` VALUES ('a', 'japanese', 'DOGS', NULL, NULL);
INSERT INTO `profile` VALUES ('ACID', 'english', 'CATS', 1, 1);
INSERT INTO `profile` VALUES ('j2ee', 'English', 'DOGS', 1, 1);
INSERT INTO `profile` VALUES ('xyz', 'japanese', 'DOGS', NULL, NULL);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('a', 'a', 'a', 'a', 'a');
INSERT INTO `users` VALUES ('ACID', 'ACID', 'acid@yourdomain.com', '901 San Antonio Road', '555-555-5555');
INSERT INTO `users` VALUES ('j2ee', 'j2ee', 'j2ee@yourdomain.com', '902 San Antonio Road', '322-513-1654');
INSERT INTO `users` VALUES ('wx', 'wx', 'wx@csu.edu.cn', 'csu', '111');
INSERT INTO `users` VALUES ('xyz', 'xyz', 'xyz@csu.edu.cn', 'a', 'a');

SET FOREIGN_KEY_CHECKS = 1;
